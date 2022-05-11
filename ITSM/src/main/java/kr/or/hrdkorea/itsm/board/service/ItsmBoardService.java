package kr.or.hrdkorea.itsm.board.service;

import org.springframework.ui.ModelMap;

import java.util.List;

import kr.or.hrdkorea.itsm.board.model.ItsmBoardVO;
import kr.or.hrdkorea.itsm.user.model.SysUserVO;


public interface ItsmBoardService {
	
	public ItsmBoardVO selectItsmBoardInfo(String userId);
	
	public abstract int searchServiceRequestListCount(ModelMap paramModelMap)
			throws Exception;

	public abstract List searchServiceRequestList(ModelMap paramModelMap)
			throws Exception;

}
