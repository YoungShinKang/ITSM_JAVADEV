package kr.or.hrdkorea.itsm.board.model;

import java.util.ArrayList;
import java.util.List;

import kr.or.hrdkorea.itsm.issue.model.ItsmIssueVO;
import kr.or.hrdkorea.itsm.user.model.SysUserVO;

public class ItsmBoardVO {
	
	private String name;
	
	private ArrayList <SysUserVO> users;
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the users
	 */
	public ArrayList<SysUserVO> getUsers() {
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(ArrayList<SysUserVO> users) {
		this.users = users;
	}


}
