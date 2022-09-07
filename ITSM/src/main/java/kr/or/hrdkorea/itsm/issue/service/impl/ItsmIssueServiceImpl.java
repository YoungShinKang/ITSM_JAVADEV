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
import kr.or.hrdkorea.itsm.nbpm.dao.ItsmNbpmDao;
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
	
	@Resource(name="itsmNbpmDao")
	public ItsmNbpmDao itsmNbpmDao;

	
	public Map selectIssueDetail(ModelMap param) throws Exception {
			
		String sr_id = (String)param.get("sr_id");
		
		//먼저 Proc_master 정보를 불러온다.		
		Map masterData = this.itsmIssueDao.selectProcessDetail(sr_id);		
		
		//Proc_service 정보를 불러온다.
		Map detailData = this.itsmIssueDao.selectProcessDetailInfo(sr_id);
		masterData.putAll(detailData);
		
		
		//여기에서 필요없다고 OperList를 만드는 부분을 삭제했는데 이게 필요하다.
		List operList = this.itsmNbpmDao.selectProcessOperList(sr_id);
		
		
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
		
		masterData.put("operList", operList);
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

}
