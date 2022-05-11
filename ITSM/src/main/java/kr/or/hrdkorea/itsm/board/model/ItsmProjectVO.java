package kr.or.hrdkorea.itsm.board.model;

import java.util.List;

import kr.or.hrdkorea.itsm.issue.model.ItsmIssueVO;

public class ItsmProjectVO {
	
	private ItsmBoardVO itsmBoardInfo;
	
	private List<ItsmIssueVO> issues;

	/**
	 * @return the itsmBoardInfo
	 */
	public ItsmBoardVO getItsmBoardInfo() {
		return itsmBoardInfo;
	}

	/**
	 * @param itsmBoardInfo the itsmBoardInfo to set
	 */
	public void setItsmBoardInfo(ItsmBoardVO itsmBoardInfo) {
		this.itsmBoardInfo = itsmBoardInfo;
	}

	/**
	 * @return the issues
	 */
	public List<ItsmIssueVO> getIssues() {
		return issues;
	}

	/**
	 * @param issues the issues to set
	 */
	public void setIssues(List<ItsmIssueVO> issues) {
		this.issues = issues;
	}
	
	

}
