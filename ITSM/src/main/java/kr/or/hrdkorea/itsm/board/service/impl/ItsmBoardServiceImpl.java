/**
 * 
 */
package kr.or.hrdkorea.itsm.board.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import kr.or.hrdkorea.itsm.base.util.PagingUtil;
import kr.or.hrdkorea.itsm.board.dao.ItsmBoardDao;
import kr.or.hrdkorea.itsm.board.model.ItsmBoardVO;
import kr.or.hrdkorea.itsm.board.service.ItsmBoardService;
import kr.or.hrdkorea.itsm.user.model.SysUserVO;

/**
 * @author "ysKang"
 *
 */
@Service("itsmBoardService")
public class ItsmBoardServiceImpl implements ItsmBoardService {
	
	@Resource(name="itsmBoardDao")
	public ItsmBoardDao itsmBoardDao;

	@Override
	public ItsmBoardVO selectItsmBoardInfo(String userId) {
		
		// TODO Auto-generated method stub
		
		//먼저 해당 사용자가 소속된 system의 목록을 불러온다.
		
		//소속 시스템의 유지보수 담당자 목록을 불러온다.
		
		
		// 임의로 데이터를 VO에 만들어 넣어서 전달해 준다.
		ItsmBoardVO itsmBoardVO = new ItsmBoardVO();
		
		//보니깐 시스템 목록도 리스트여야 되네...일단 임시로 해보자
		itsmBoardVO.setName("itsm");
		
		//필요 없을지도 모르지만 일단 시스템별 유지보수 사용자 추정				
		ArrayList <SysUserVO> users = new ArrayList <SysUserVO>();
		
		//= Arrays.asList("frazer93", "frazer94", "adminmgt", "frazer95");
		SysUserVO user1 = new SysUserVO();
		user1.setUserId("frazer93");
		user1.setUserNm("frazer93");
		users.add(user1);
		
		SysUserVO user2 = new SysUserVO();
		user2.setUserId("frazer94");
		user2.setUserNm("frazer94");
		users.add(user2);
		
		SysUserVO user3 = new SysUserVO();
		user3.setUserId("adminmgt");
		user3.setUserNm("adminmgt");
		users.add(user3);
		
		SysUserVO user4 = new SysUserVO();
		user4.setUserId("frazer95");
		user4.setUserNm("frazer95");
		users.add(user4);
		
		itsmBoardVO.setUsers(users);
		
		
		return itsmBoardVO;
	}
	
	public int searchServiceRequestListCount(ModelMap paramMap) throws Exception
	{
		return this.itsmBoardDao.searchServiceRequestListCount(paramMap);
	}
	
	public List searchServiceRequestList(ModelMap paramMap) throws Exception {
		
		//만약 start와 ,page,limit가 설정되지 않았으면 값을 넣어준다.
		if(paramMap.get("start") == null) paramMap.put("start", 1);
		if(paramMap.get("page") == null) paramMap.put("page", 1);
		if(paramMap.get("limit") == null) paramMap.put("limit", paramMap.get("total_count"));
		
		PagingUtil.getFristEndNum(paramMap);      
      
		return this.itsmBoardDao.searchServiceRequestList(paramMap);
	}
	


}
