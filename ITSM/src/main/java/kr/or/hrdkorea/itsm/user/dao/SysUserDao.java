/**
 * 
 */
package kr.or.hrdkorea.itsm.user.dao;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import kr.or.hrdkorea.itsm.user.model.SysUserVO;
import kr.or.hrdkorea.itsm.user.model.UserVO;

/**
 * @author "ysKang"
 *
 */
@Repository("sysUserDao")
public class SysUserDao extends EgovAbstractMapper {
	
	/**
	 * @param sysUserVO
	 * @return
	 */
	public SysUserVO selectLogin(SysUserVO sysUserVO) {
		return selectOne("SYS_USER.selectLogin", sysUserVO);
	}
	
	public UserVO selectUserInfo(String userId) {
		return selectOne("SYS_USER.selectUserInfo", userId);
	}

}
