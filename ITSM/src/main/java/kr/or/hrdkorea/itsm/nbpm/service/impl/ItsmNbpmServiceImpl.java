package kr.or.hrdkorea.itsm.nbpm.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.hrdkorea.itsm.issue.dao.ItsmIssueDao;
import kr.or.hrdkorea.itsm.nbpm.dao.ItsmNbpmDao;
import kr.or.hrdkorea.itsm.nbpm.model.NbpmProcessVO;
import kr.or.hrdkorea.itsm.nbpm.service.ItsmNbpmService;

/**
 * @author "ysKang"
 *
 */
@Service("itsmNbpmService")
public class ItsmNbpmServiceImpl implements ItsmNbpmService {
	
	@Resource(name="itsmNbpmDao")
	public ItsmNbpmDao itsmNbpmDao;
	
	@Resource(name="itsmIssueDao")
	public ItsmIssueDao itsmIssueDao;
	
	public NbpmProcessVO selectFinalVersionProcessInfo(String process_type) throws Exception
	{
		return this.itsmNbpmDao.selectFinalVersionProcessInfo(process_type);
	}
	
	public String createSrId() throws Exception {
		String sr_id = this.itsmNbpmDao.createSrId();
		return sr_id;
	}	
	
	
	public Map executeTask(NbpmProcessVO processVO, Map paramMap) throws Exception
	{
		Map returnMap = new HashMap();
		try {
		
			returnMap = workTask(processVO, paramMap);
				
			//frazer93. 아래 동작은 TASK 테이블과 조인되기 때문에 실질적인 동작은 절대로
			//발생하지 않는다.
			//추가로 분석할 필요가 없음 (주석달아도 됨). 리스트 없읍
			List relIncList = searchRelIncList(processVO.getSr_id());
			for (int i = 0; i < relIncList.size(); i++) {
				NbpmProcessVO relSr = (NbpmProcessVO)relIncList.get(i);
				relSr.setWorker_user_id("rel_inc");
				Map param = new HashMap();
				param.put("sr_id", relSr.getSr_id());
				workTask(relSr, param);
			}
		} catch (Exception e) {
			throw e;
		}

		return returnMap;
	}
	
	private List searchRelIncList(String sr_id) {
		List relIncList = this.itsmNbpmDao.searchRelIncList(sr_id);
		return relIncList;
	}


	public void registerUser(NbpmProcessVO processVO, Map paramMap) throws Exception
	{
		try {
		
			String requestor = processVO.getReq_user_id();
			if ((requestor != null) && (!"".equals(requestor))) {
				int count = this.itsmNbpmDao.isExistNbpmUser(requestor);
				if (count == 0) {
					this.itsmNbpmDao.insertNbpmUser(requestor);
				}
			}
			
			String loginId = processVO.getWorker_user_id();
			if ((loginId != null) && (!"".equals(loginId))) {
				int count = this.itsmNbpmDao.isExistNbpmUser(loginId);
				if (count == 0) {
					this.itsmNbpmDao.insertNbpmUser(loginId);
				}
			}
			for (int i = 0; (processVO.getOperList() != null) && (i < processVO.getOperList().size()); i++) {
				Object operUserId = ((LinkedHashMap)processVO.getOperList().get(i)).get("oper_user_id");
				if ((operUserId instanceof String)) {
					if ((operUserId != null) && (!"".equals((String)operUserId))) {
						int count = this.itsmNbpmDao.isExistNbpmUser((String)operUserId);
					if (count == 0)
						this.itsmNbpmDao.insertNbpmUser((String)operUserId);
					}
				} else if ((operUserId instanceof List)) {
					for (int j = 0; j < ((List)operUserId).size(); j++) {
						String userId = (String)((List)operUserId).get(j);
						if ((userId != null) && (!"".equals(userId))) {
							int count = this.itsmNbpmDao.isExistNbpmUser(userId);
							if (count == 0) {
								this.itsmNbpmDao.insertNbpmUser(userId);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	private boolean checkExistSrId(String srId) throws SQLException, Exception
	{
		boolean result = false;
		int count = this.itsmNbpmDao.selectExistSrId(srId);
		if (count > 0) {
			result = true;
		}
		return result;
	}
	
	public Map workTask(NbpmProcessVO processVO, Map paramMap) throws Exception {

		Map resultMap = new HashMap();
		try	{
			String task_name = processVO.getTask_name();
	
			//이 동작들은 뭘까? itemList, operList
			//item 리스트는 항상 없었음. oper list는 max 2 (requestor, sdk)
			List itemList = processVO.getItemList();
			List operList = processVO.getOperList();
	
	
			String queryKey = (String)paramMap.get("queryKey");
	
			Map processInfo = this.itsmNbpmDao.selectProcessInfo(processVO);
			//이조건도 보면 processInfo는 항상 null이다. TASK 에 ID가 항상 0 으로 넘어가기 때문이다.
			if (processInfo != null) {
				processVO.setCurrent_role_id((String)processInfo.get("ROLE_ID"));
			}
			//SERVICE_v_1 에 해당하는 USER 유형인것 같음 [REQUESTOR, SERVICEDESK, ROLE1, ROLE2, workType, approval] 인데, 이게 paramMap에 key가 됨.
			//이관단계에서는 이 값이 null 임.processid가 공백이다.
			List extParamNameList = this.itsmNbpmDao.searchNbpmProcessVariable(processVO.getProcessId());
			HashMap extParamMap = new HashMap();
	
			for (int i = 0; i < extParamNameList.size(); i++) {
				String paramValue = (String)paramMap.get((String)extParamNameList.get(i));
				extParamMap.put((String)extParamNameList.get(i), paramValue);
			}
			processVO.setExpansionParamMap(extParamMap);
	
			//마스터를 검색한다. SRID로 
			boolean isExist = checkExistSrId(processVO.getSr_id());
	
			if (!isExist) {
				paramMap.put("reg_user_id", processVO.getWorker_user_id());
				this.itsmNbpmDao.insertProcessMaster(paramMap);  //
		
				Map requestorInfo = new HashMap();
				requestorInfo.put("oper_user_id", processVO.getWorker_user_id());
				requestorInfo.put("sr_id", processVO.getSr_id());
				requestorInfo.put("oper_type", "REQUESTOR");
				requestorInfo.put("select_type", "SINGLE");
				this.itsmNbpmDao.insertProcessOrerator(requestorInfo); 
		
				for (int i = 0; (operList != null) && (i < operList.size()); i++) {
					Map operInfo = (Map)operList.get(i);
					operInfo.put("sr_id", processVO.getSr_id());
					this.itsmNbpmDao.insertProcessOrerator(operInfo);
				}
		
				int i = 0;
				do {
					if (itemList == null) break;
			
					Map itemInfo = (Map)itemList.get(i);
					itemInfo.put("sr_id", processVO.getSr_id());
					this.itsmNbpmDao.insertProcessItem(itemInfo);
			
					i++; 
				} while (i < itemList.size());
			} else {
				paramMap.put("upd_user_id", processVO.getWorker_user_id());
				//paramMap.put("atch_file_id", paramMap.get("atch_file_id"));
				this.itsmNbpmDao.updateProcessMaster(paramMap);
		
				checkReassignOperUser(operList);
		
				updateOperatorInfo(processVO, operList);
		
				//실질적으로 사용하지 않음
				if (itemList != null) {
					updateProcessItemInfo(processVO, itemList);
				}
			}
	
			if (queryKey != null) {
				updateProcDetailTable(queryKey, paramMap); //INSERT INTO PROC_SERVICE이 사용된다. queryKey 주의
			}
	
			if ((processVO.getGridMapList() != null) && (processVO.getGridMapList().size() > 0)) {
				updateGridMapData(processVO);
			}
	
	
			HashMap param = new HashMap();
			param.put("sr_id", processVO.getSr_id());
	
			if ("END".equals(paramMap.get("proc_state"))) {
				Map detailInfo = new HashMap();
				detailInfo.put("sr_id", processVO.getSr_id());
				detailInfo.put("actualowner_id", processVO.getWorker_user_id());
				detailInfo.put("work_state", paramMap.get("next_task"));
		
				if(paramMap.get("content") == null){
					detailInfo.put("comment_text", paramMap.get("nbpm_comment")); 
				}else{
					detailInfo.put("comment_text", paramMap.get("content")); 
				}
		
				this.itsmNbpmDao.insertProcessDetail(detailInfo);
				detailInfo.put("work_state", "END");
				this.itsmNbpmDao.insertProcessDetail(detailInfo);
		
				boolean isReject = "REJECT".equals(paramMap.get("end_type"));
				paramMap.put("work_state", "END");
				paramMap.put("sr_id", processVO.getSr_id());
				paramMap.put("upd_user_id", processVO.getWorker_user_id());
				paramMap.put("end_type", (isReject ? "REJECT" : "COMP"));
				this.itsmNbpmDao.updateEndWorkState(paramMap);
		
				// ## 첨부파일 상태처리 - task가 없으므로.
				this.itsmNbpmDao.updateAtchFileState(paramMap);
	
			} else {
	
				boolean isIng = "ING".equals(paramMap.get("proc_state"));  //최조 저장할때는 proc_state 자체가 없다.
		
				Map detailInfo = new HashMap();
				detailInfo.put("sr_id", processVO.getSr_id());
				detailInfo.put("actualowner_id", processVO.getWorker_user_id());
				detailInfo.put("work_state", isIng ? paramMap.get("next_task") : "REQUEST");
		
				detailInfo.put("reject_work_state", processVO.getTask_name());
				//의견란이 있으면 의견내용을 넣고, 의견란이 없으면 내용을 넣는다...
				if(paramMap.get("content") == null){
					detailInfo.put("comment_text", paramMap.get("nbpm_comment")); 
				}else{
					detailInfo.put("comment_text", paramMap.get("content")); 
				}
		
				this.itsmNbpmDao.insertProcessDetail(detailInfo); //INSERT INTO PROC_DETAIL (
		
				if("REJECT".equals(paramMap.get("end_type"))) { //일반적인 저장에서는 end_type을 넣지 않는다.
					this.itsmNbpmDao.updateProcessDetailReject(detailInfo);
				} 
		
				if("RECHECK".equals(paramMap.get("end_type"))) {
					this.itsmNbpmDao.updateProcessDetailRecheck(detailInfo);
				}
		
				if("Y".equals(paramMap.get("first_yn"))) {
					this.itsmNbpmDao.updateProcessDetailFirst(detailInfo);
		
				}
		
				param.put("work_state", isIng ? paramMap.get("next_task") : "REQUEST"); //isIng 확인 필요. 왜나면 여기서 설정된 work_state가 this.postWorkDAO.updateWorkState(param);에서 사용됨
		
				param.put("atch_file_id", paramMap.get("atch_file_id"));
				this.itsmNbpmDao.updateWorkState(param); //work_state를 업데이트 해 준다.
		
				// ## 첨부파일 상태처리 - task가 없으므로.
				param.put("atch_file_id", paramMap.get("atch_file_id"));
				this.itsmNbpmDao.updateAtchFileState(detailInfo);
			}
		}
		catch (Exception e)
		{
		throw e;
		}

		return resultMap;
	}
	
	
	private void checkReassignOperUser(List operList)
	{
		ArrayList removeIndex = new ArrayList();
		for (int i = 0; (operList != null) && (i < operList.size()); i++) {
			Map checkOperInfo = (Map)operList.get(i);
			String checkOperType = (String)checkOperInfo.get("oper_type");
			Object tempOperUserId = checkOperInfo.get("oper_user_id");
			if ((!(tempOperUserId instanceof String)) || (checkOperType.indexOf("re_") == -1)) continue;
			
			checkOperType = checkOperType.replace("re_", "");
			boolean changeFlag = false;
			for (int j = 0; j < operList.size(); j++) {
				Map operInfo = (Map)operList.get(j);
				String operType = (String)operInfo.get("oper_type");
				if (checkOperType.equals(operType)) {
					changeFlag = true;
					removeIndex.add(Integer.valueOf(j));
					checkOperInfo.put("oper_type", checkOperType);
				}
			}
			if (!changeFlag) {
				checkOperInfo.put("oper_type", checkOperType);
			}

		}
	
		for (int i = 0; i < removeIndex.size(); i++) {
		int idx = ((Integer)removeIndex.get(i)).intValue();
		operList.remove(idx);
		}
	}
	
	
	private void updateOperatorInfo(NbpmProcessVO processVO, List operList) throws SQLException, Exception {
		for (int i = 0; (operList != null) && (i < operList.size()); i++) {
			Map operInfo = (Map)operList.get(i);
			operInfo.put("sr_id", processVO.getSr_id());
			this.itsmNbpmDao.deleteProcessOrerator(operInfo);
		}

		for (int i = 0; (operList != null) && (i < operList.size()); i++) {
			Map operInfo = (Map)operList.get(i);
			Object tempOperUserId = operInfo.get("oper_user_id");
			if ((tempOperUserId instanceof String)) {
				operInfo.put("sr_id", processVO.getSr_id());
				this.itsmNbpmDao.insertProcessOrerator(operInfo); 
			} else {
				if (!(tempOperUserId instanceof List)) continue;
				List operUserIdList = (List)tempOperUserId;
				for (int j = 0; j < operUserIdList.size(); j++) {		
					Map multiOperUser = new HashMap();
					String operUserId = (String)operUserIdList.get(j);
					multiOperUser.put("oper_type", operInfo.get("oper_type"));
					multiOperUser.put("sr_id", processVO.getSr_id());
					multiOperUser.put("oper_user_id", operUserId);
					multiOperUser.put("select_type", operInfo.get("select_type"));
					this.itsmNbpmDao.insertProcessOrerator(multiOperUser);
				}
			}
		}
	}
	
	private void updateProcessItemInfo(NbpmProcessVO processVO, List itemList) throws SQLException, Exception {
		this.itsmNbpmDao.deleteProcessItem(processVO.getSr_id());
		for (int i = 0; (itemList != null) && (i < itemList.size()); i++) {
			Map itemInfo = (Map)itemList.get(i);
			itemInfo.put("sr_id", processVO.getSr_id());
			this.itsmNbpmDao.insertProcessItem(itemInfo);
			}
	}
	
	private void updateProcDetailTable(String queryKey, Map paramMap) throws SQLException, Exception {
		
		String[] querySet = queryKey.split(",");
		String checkQuery = null;
		String insertQuery = null;
		String updateQuery = null;
		
		for (int i = 0; i < querySet.length; i++) {
			if (querySet[i].indexOf("count") != -1)
				checkQuery = querySet[i];
			else if (querySet[i].indexOf("insert") != -1)
				insertQuery = querySet[i];
			else if (querySet[i].indexOf("update") != -1) {
				updateQuery = querySet[i];
			}
		}
		boolean isExist = true;
		if ((checkQuery != null) && (!"".equals(checkQuery))) {
			isExist = checkExistSrIdInDetailTable(checkQuery, paramMap);
		}
		if ((isExist) && (updateQuery != null))
		{
			this.itsmNbpmDao.excuteProcessDetail(updateQuery, paramMap);
		}
		else if ((insertQuery != null) && (!"".equals(insertQuery)))
			this.itsmNbpmDao.excuteProcessDetail(insertQuery, paramMap);
	}
	
	private boolean checkExistSrIdInDetailTable(String checkQuery, Map paramMap) throws SQLException, Exception {
		boolean result = false;
			int count = this.itsmNbpmDao.selectExistSrIdInDetailTable(checkQuery, paramMap);
		if (count > 0) {
			result = true;
		}
		return result;
	}
	
	private void updateGridMapData(NbpmProcessVO processVO)throws Exception	{
		
		List gridMapList = processVO.getGridMapList();
		for (int i = 0; i < gridMapList.size(); i++) {
			Map gridDataMap = (Map)gridMapList.get(i);
			String gridInsertQueryKey = (String)gridDataMap.get("gridInsertQueryKey");
			String gridDeleteQueryKey = (String)gridDataMap.get("gridDeleteQueryKey");
			List gridDataList = (List)gridDataMap.get("gridData");
			HashMap paramMap = new HashMap();
			paramMap.put("sr_id", processVO.getSr_id());
			
			if ((gridInsertQueryKey != null) && (!"".equals(gridInsertQueryKey))) {
				this.itsmNbpmDao.deleteGridData(gridDeleteQueryKey, paramMap);
				for (int j = 0; j < gridDataList.size(); j++) {
					Map gridData = (Map)gridDataList.get(j);
					gridData.put("sr_id", processVO.getSr_id());
					gridData.put("upd_user_id", processVO.getWorker_user_id());
					this.itsmNbpmDao.insertGridData(gridInsertQueryKey, gridData);
				}
			}
		}
	}
	
	public void notifyTask(NbpmProcessVO processVO, Map result) throws Exception {
		try {
			
			String loginUserId = processVO.getLogin_user_id();

			Map processDetail = this.itsmIssueDao.selectProcessDetail(processVO.getSr_id());
			List alamList = this.itsmNbpmDao.searchNbpmAlamList(processVO);

			for (int j = 0; j < alamList.size(); j++) {
				Map alamInfo = (Map)alamList.get(j);
				String emailSend = (String)alamInfo.get("EMAIL_SEND");
				String smsSend = (String)alamInfo.get("SMS_SEND");
				String nodeName = (String)alamInfo.get("NODENAME");
				alamInfo.put("LOGIN_USER_ID", loginUserId);
				String userId = (String)alamInfo.get("TARGET_USER_ID");
	
				if ("Y".equals(emailSend)) {
					//데이터 입력이 관료되면 이후 사항을 처리한다. 2022-07-01 yskang
					//NbpmEmailSender emailSender = new NbpmEmailSender(processDetail, alamInfo, nodeName);
					//emailSender.sendMail();
				}
			}

		} catch (Exception e) {
			throw e;
		}
	}
	
	public boolean checkTaskStatus(NbpmProcessVO processVO) throws Exception
	{
		boolean result = true;
		int count = this.itsmNbpmDao.checkTaskStatus(processVO);
		if (count > 0) {
			result = false;
		}
		return result;
	}



}
