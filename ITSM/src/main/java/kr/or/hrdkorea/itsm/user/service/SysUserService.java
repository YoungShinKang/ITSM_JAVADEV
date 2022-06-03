package kr.or.hrdkorea.itsm.user.service;

import java.util.List;
import java.util.Map;

import kr.or.hrdkorea.itsm.user.model.SysUserVO;
import kr.or.hrdkorea.itsm.user.model.UserVO;

public interface SysUserService {
	
	public SysUserVO selectLogin(SysUserVO sysUserVO);
	
	
	public UserVO selectUserInfo(String userId);
}
