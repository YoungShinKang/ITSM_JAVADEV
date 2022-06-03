/**
 * 
 */
package kr.or.hrdkorea.itsm.project.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import kr.or.hrdkorea.itsm.board.dao.ItsmBoardDao;
import kr.or.hrdkorea.itsm.board.model.ItsmBoardVO;
import kr.or.hrdkorea.itsm.board.service.ItsmBoardService;
import kr.or.hrdkorea.itsm.project.dao.ItsmProjectDao;
import kr.or.hrdkorea.itsm.project.service.ItsmProjectService;
import kr.or.hrdkorea.itsm.user.model.SysUserVO;

/**
 * @author "ysKang"
 *
 */
@Service("itsmProjectService")
public class ItsmProjectServiceImpl implements ItsmProjectService {
	
	@Resource(name="itsmProjectDao")
	public ItsmProjectDao itsmProjectDao;	
	
	public List searchUserSystemAuthList(ModelMap paramMap) throws Exception {
		return this.itsmProjectDao.searchUserSystemAuthList(paramMap);
	}
	
	public List personalStatisticsList(ModelMap paramMap) throws Exception {
		return this.itsmProjectDao.searchUserSystemAuthList(paramMap);
	}
	
	public List searchBoradList(ModelMap paramMap) throws Exception {
		return this.itsmProjectDao.searchBoradList(paramMap);
	}
	
	public int searchBoradListCount(ModelMap paramMap) throws Exception
	{
		return this.itsmProjectDao.searchBoradListCount(paramMap);
	}


}
