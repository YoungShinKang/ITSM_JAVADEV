/**
 * 
 */
package kr.or.hrdkorea.itsm.user.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import kr.or.hrdkorea.itsm.user.dao.SysUserDao;
import kr.or.hrdkorea.itsm.user.model.SysUserVO;
import kr.or.hrdkorea.itsm.user.service.SysUserService;

/**
 * @author ysKang
 *
 */

@Service("sysUserService")
public class SysUserServiceImpl extends EgovAbstractServiceImpl implements SysUserService {
	
	@Resource(name="sysUserDao")
	public SysUserDao sysUserDao;

	@Override
	public SysUserVO selectLogin(SysUserVO sysUserVO) {
		return sysUserDao.selectLogin(sysUserVO);
	}

}
