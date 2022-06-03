/**
 * 
 */
package kr.or.hrdkorea.itsm.user.dao;

import org.springframework.stereotype.Repository;
import org.springframework.ui.ModelMap;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

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
	
	/**
	 * 
	 * @param userId
	 * @return
	 */
	public UserVO selectUserInfo(String userId) {
		return selectOne("SYS_USER.selectUserInfo", userId);
	}

}
