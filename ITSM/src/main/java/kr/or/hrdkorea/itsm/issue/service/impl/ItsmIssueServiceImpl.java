/**
 * 
 */
package kr.or.hrdkorea.itsm.issue.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import kr.or.hrdkorea.itsm.base.util.StringUtil;
import kr.or.hrdkorea.itsm.issue.dao.ItsmIssueDao;
import kr.or.hrdkorea.itsm.issue.model.ItsmIssueVO;
import kr.or.hrdkorea.itsm.issue.service.ItsmIssueService;
import kr.or.hrdkorea.itsm.user.dao.SysUserDao;
import kr.or.hrdkorea.itsm.user.model.UserVO;

/**
 * @author "ysKang"
 *
 */
@Service("itsmIssueService")
public class ItsmIssueServiceImpl implements ItsmIssueService {
	
	@Resource(name="itsmIssueDao")
	public ItsmIssueDao itsmIssueDao;
	
	@Resource(name="sysUserDao")
	public SysUserDao sysUserDao;
	
	@Override
	public List<ItsmIssueVO> selectItsmIssues(String userId) {
		// TODO Auto-generated method stub
		
		//임시로 mock 함수로 데이터를 셋팅한다.
		return getMockItsmIssues();	
		
	}
	
	public Map selectIssueDetail(ModelMap param, String userId) throws Exception {
			
		String sr_id = (String)param.get("sr_id");		
		param.put("user_id", userId);		
		
		//먼저 Proc_master 정보를 불러온다.		
		Map masterData = this.itsmIssueDao.selectProcessDetail(sr_id);		
		
		//Proc_service 정보를 불러온다.
		Map detailData = this.itsmIssueDao.selectProcessDetailInfo(sr_id);
		masterData.putAll(detailData);
		
		
		//Proc_service 정보를 불러온다.
		//이때 실제로  블러오는 것은 comment 위주로 불러온다.		
		List taskCommentList = this.itsmIssueDao.searchTaskCommentList(sr_id);
		
		//불러온 코멘트를 코멘트 리스트로 생성한다.
		List commentHistory = new ArrayList();
		for (int i = 0; (taskCommentList != null) && (i < taskCommentList.size()); i++) {
			Map commentMap = new HashMap();
			commentMap.putAll((HashMap)taskCommentList.get(i));
			String comment = (String)commentMap.get("TEXT");
			if (comment == null)
			continue;
			comment = StringUtil.changeLineAlignmentForHtml(comment);
			commentMap.put("TEXT", comment);
			commentHistory.add(commentMap);
		}
		
		masterData.put("commentHistory", commentHistory);
		masterData.put("nbpm_commentList", taskCommentList);	
		
		String reqUserId = (String)masterData.get("REQ_USER_ID");
		
		
		UserVO reqUserVO = sysUserDao.selectUserInfo(reqUserId);
		
		if (reqUserVO != null) {
			String userDept = reqUserVO.getDetailInfo();
			masterData.put("REQ_USER_DETAIL", userDept);
			masterData.put("REQ_USER_NM", reqUserVO.getUser_nm());
			masterData.put("REQ_USER_ID", reqUserId);
		}
	
		return masterData;
	}
	
	
	
	
	public List<ItsmIssueVO> getMockItsmIssues() {
		// 임시로 만들어 보자...
			List<ItsmIssueVO> itsmIssues = new ArrayList<ItsmIssueVO>();
			
			

			ItsmIssueVO itsmIssueVO1 = new ItsmIssueVO();

			List<String> users1 = Arrays.asList("frazer91", "frazer92", "frazer93");
			itsmIssueVO1.setStatus("TEMP");
			itsmIssueVO1.setId("ITSM001");
			itsmIssueVO1.setTitle("ITSM 테스트1");
			itsmIssueVO1.setUserIds(users1);
			itsmIssueVO1.setType("01");
			itsmIssueVO1.setPriority("1");
			
			ItsmIssueVO itsmIssueVO2 = new ItsmIssueVO();

			List<String> users2 = Arrays.asList("frazer91", "frazer96");
			itsmIssueVO2.setStatus("REQUEST");
			itsmIssueVO2.setId("ITSM002");
			itsmIssueVO2.setTitle("ITSM 테스트2");
			itsmIssueVO2.setUserIds(users2);
			itsmIssueVO2.setType("02");
			itsmIssueVO2.setPriority("1");
			
			ItsmIssueVO itsmIssueVO3 = new ItsmIssueVO();

			List<String> users3 = Arrays.asList("frazer91", "frazer94", "frazer96");
			itsmIssueVO3.setStatus("REQUEST");
			itsmIssueVO3.setId("ITSM003");
			itsmIssueVO3.setTitle("ITSM 테스트3");
			itsmIssueVO3.setUserIds(users3);
			itsmIssueVO3.setType("03");
			itsmIssueVO3.setPriority("1");
			
			ItsmIssueVO itsmIssueVO4 = new ItsmIssueVO();

			List<String> users4 = Arrays.asList("frazer93", "frazer94", "frazer95", "frazer96");
			itsmIssueVO4.setStatus("SERVICE_CHECK");
			itsmIssueVO4.setId("ITSM004");
			itsmIssueVO4.setTitle("ITSM 테스트4");
			itsmIssueVO4.setUserIds(users4);
			itsmIssueVO4.setType("04");
			itsmIssueVO4.setPriority("2");
			
			ItsmIssueVO itsmIssueVO5 = new ItsmIssueVO();

			List<String> users5 = Arrays.asList("frazer95", "frazer96");
			itsmIssueVO5.setStatus("SERVICE_CHECK");
			itsmIssueVO5.setId("ITSM005");
			itsmIssueVO5.setTitle("ITSM 테스트5");
			itsmIssueVO5.setUserIds(users5);
			itsmIssueVO5.setType("05");
			itsmIssueVO5.setPriority("2");
			

			
			ItsmIssueVO itsmIssueVO6 = new ItsmIssueVO();
		    
			List<String> users6 = Arrays.asList("frazer91", "frazer94", "frazer95");
			itsmIssueVO6.setStatus("SERVICE_GROUP");
			itsmIssueVO6.setId("ITSM006");
			itsmIssueVO6.setTitle("ITSM 테스트6");
			itsmIssueVO6.setUserIds(users6);
			itsmIssueVO6.setType("06");
			itsmIssueVO6.setPriority("1");
			
			ItsmIssueVO itsmIssueVO7 = new ItsmIssueVO();
		    
			List<String> users7 = Arrays.asList("frazer91", "frazer94", "frazer95", "frazer96");
			itsmIssueVO7.setStatus("SERVICE_GROUP");
			itsmIssueVO7.setId("ITSM007");
			itsmIssueVO7.setTitle("ITSM 테스트7");
			itsmIssueVO7.setUserIds(users7);
			itsmIssueVO7.setType("07");
			itsmIssueVO7.setPriority("1");
			
			ItsmIssueVO itsmIssueVO8 = new ItsmIssueVO();
		    
			List<String> users8 = Arrays.asList("frazer91", "frazer95", "frazer96");
			itsmIssueVO8.setStatus("SERVICE_GROUP");
			itsmIssueVO8.setId("ITSM008");
			itsmIssueVO8.setTitle("ITSM 테스트8");
			itsmIssueVO8.setUserIds(users8);
			itsmIssueVO8.setType("08");
			itsmIssueVO8.setPriority("2");


			ItsmIssueVO itsmIssueVO9 = new ItsmIssueVO();
		    
			List<String> users9 = Arrays.asList("frazer96");
			itsmIssueVO9.setStatus("SERVICE_GROUP");
			itsmIssueVO9.setId("ITSM009");
			itsmIssueVO9.setTitle("ITSM 테스트9");
			itsmIssueVO9.setUserIds(users9);
			itsmIssueVO9.setType("09");
			itsmIssueVO9.setPriority("2");
			
			ItsmIssueVO itsmIssueV10 = new ItsmIssueVO();
		    
			List<String> users10 = Arrays.asList("frazer93", "frazer96");
			itsmIssueV10.setStatus("SERVICE_GROUP");
			itsmIssueV10.setId("ITSM010");
			itsmIssueV10.setTitle("ITSM 테스트10");
			itsmIssueV10.setUserIds(users10);
			itsmIssueV10.setType("01");
			itsmIssueV10.setPriority("3");
			
			ItsmIssueVO itsmIssueV11 = new ItsmIssueVO();
		    //{status : '',id : '',title : '',
			//userIds : [,''] ,type : '', priority:'' },

			List<String> users11 = Arrays.asList("frazer96");
			itsmIssueV11.setStatus("RESULT");
			itsmIssueV11.setId("ITSM011");
			itsmIssueV11.setTitle("ITSM 테스트11");
			itsmIssueV11.setUserIds(users11);
			itsmIssueV11.setType("02");
			itsmIssueV11.setPriority("3");
			
			ItsmIssueVO itsmIssueV12 = new ItsmIssueVO();
		    
			List<String> users12 = Arrays.asList("frazer91", "frazer95", "frazer96");
			itsmIssueV12.setStatus("RESULT");
			itsmIssueV12.setId("ITSM012");
			itsmIssueV12.setTitle("ITSM 테스트12");
			itsmIssueV12.setUserIds(users12);
			itsmIssueV12.setType("04");
			itsmIssueV12.setPriority("1");
			
			ItsmIssueVO itsmIssueV13 = new ItsmIssueVO();
		    
			List<String> users13 = Arrays.asList("frazer93", "frazer94", "frazer96");
			itsmIssueV13.setStatus("RESULT");
			itsmIssueV13.setId("ITSM013");
			itsmIssueV13.setTitle("ITSM 테스트13");
			itsmIssueV13.setUserIds(users13);
			itsmIssueV13.setType("05");
			itsmIssueV13.setPriority("4");
			
			ItsmIssueVO itsmIssueV14 = new ItsmIssueVO();
		    
			List<String> users14 = Arrays.asList("frazer91");
			itsmIssueV14.setStatus("END");
			itsmIssueV14.setId("ITSM014");
			itsmIssueV14.setTitle("ITSM 테스트14");
			itsmIssueV14.setUserIds(users14);
			itsmIssueV14.setType("05");
			itsmIssueV14.setPriority("4");
			
			
			itsmIssues.add(itsmIssueVO1);
			itsmIssues.add(itsmIssueVO2);
			itsmIssues.add(itsmIssueVO3);
			itsmIssues.add(itsmIssueVO4);
			itsmIssues.add(itsmIssueVO5);
			itsmIssues.add(itsmIssueVO6);
			itsmIssues.add(itsmIssueVO7);
			itsmIssues.add(itsmIssueVO8);
			itsmIssues.add(itsmIssueVO9);
			itsmIssues.add(itsmIssueV10);
			itsmIssues.add(itsmIssueV11);
			itsmIssues.add(itsmIssueV12);
			itsmIssues.add(itsmIssueV13);
			itsmIssues.add(itsmIssueV14);
			   
			return itsmIssues;
	}
	
	
	

}
