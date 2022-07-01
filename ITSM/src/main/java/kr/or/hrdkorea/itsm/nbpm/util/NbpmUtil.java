package kr.or.hrdkorea.itsm.nbpm.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import kr.or.hrdkorea.itsm.nbpm.model.NbpmProcessVO;


public class NbpmUtil {


	public static NbpmProcessVO setProcessParameter(Map paramMap) {
	
		String sr_id = (String)paramMap.get("sr_id");
		String processId = (String)paramMap.get("nbpm_processId");
		String processInstanceId = (String)paramMap.get("nbpm_processInstanceId");
		String task_id = (String)paramMap.get("nbpm_task_id");
		String task_name = (String)paramMap.get("nbpm_task_name");
		String process_nm = (String)paramMap.get("nbpm_process_nm");
		String process_type = (String)paramMap.get("nbpm_process_type");
		List operList = (List)paramMap.get("nbpm_operList");
		List itemList = (List)paramMap.get("nbpm_itemList");
		String version = (String)paramMap.get("nbpm_version");
		String comment = (String)paramMap.get("nbpm_comment");
		String workType = (String)paramMap.get("workType");
		String reqUesrId = (String)paramMap.get("req_user_id");
		String workerUserId = (String)paramMap.get("workerUserId");
		List gridMapList = (List)paramMap.get("gridMapList");
		
		NbpmProcessVO processVO = new NbpmProcessVO();
		
		if ((processInstanceId != null) && (!"".equals(processInstanceId))) {
			processVO.setProcessInstanceId(Long.parseLong(processInstanceId));
		}
		
		if ((task_id != null) && (!"".equals(task_id))) {
			processVO.setTask_id(Long.parseLong(task_id));
		}
		
		processVO.setSr_id(sr_id);
		processVO.setProcessId(processId);
		processVO.setTask_name(task_name);
		processVO.setOperList(operList);
		processVO.setWorker_user_id(workerUserId);
		processVO.setItemList(itemList);
		processVO.setProcess_name(process_nm);
		processVO.setProcess_type(process_type);
		processVO.setVersion(version);
		processVO.setWorkType(workType);
		processVO.setComment(comment);
		processVO.setReq_user_id(reqUesrId);
		processVO.setGridMapList(gridMapList);
		
		return processVO;
	}

	public static String getCommentHistory(List taskCommentList)
	{
		StringBuffer commentHistory = new StringBuffer();
		if (taskCommentList != null) {
			for (int i = 0; i < taskCommentList.size(); i++) {
				HashMap taksComment = (HashMap)taskCommentList.get(i);
				String workUserNm = (String)taksComment.get("WORK_USER_NM");
				String workDate = (String)taksComment.get("WORK_DATE");
				String nodeName = (String)taksComment.get("NODENAME");
				String str1 = (String)taksComment.get("TEXT");
			}
		}
		return commentHistory.toString();
	}

	public static NbpmProcessVO setRegisterForData(NbpmProcessVO processVO, String reqUserId, String loginUserId) {
		processVO.setReq_user_id(reqUserId);
		processVO.setWorker_user_id(loginUserId);
		processVO.setTask_name("start");
		processVO.setWorkType("comp");
		
		List operList = new ArrayList();
		LinkedHashMap requestor = new LinkedHashMap();
		requestor.put("oper_type", "SERVICEDESK");
		requestor.put("oper_user_id", loginUserId);
		operList.add(requestor);
		
		processVO.setOperList(operList);
		return processVO;
	}
}
