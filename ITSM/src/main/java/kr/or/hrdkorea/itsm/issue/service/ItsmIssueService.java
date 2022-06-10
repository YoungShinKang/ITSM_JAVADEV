package kr.or.hrdkorea.itsm.issue.service;

import java.util.List;
import java.util.Map;

import org.springframework.ui.ModelMap;

import kr.or.hrdkorea.itsm.board.model.ItsmBoardVO;
import kr.or.hrdkorea.itsm.issue.model.ItsmIssueVO;

public interface ItsmIssueService {
	
	public List<ItsmIssueVO> selectItsmIssues(String userId);
	
	public Map selectIssueDetail(ModelMap paramMap) throws Exception;

}
