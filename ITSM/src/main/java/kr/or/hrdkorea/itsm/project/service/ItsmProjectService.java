package kr.or.hrdkorea.itsm.project.service;

import org.springframework.ui.ModelMap;

import java.util.List;

import kr.or.hrdkorea.itsm.board.model.ItsmBoardVO;
import kr.or.hrdkorea.itsm.user.model.SysUserVO;


public interface ItsmProjectService {
	
	public abstract List searchUserSystemAuthList(ModelMap paramModelMap)
			throws Exception;	
	
	public abstract List personalStatisticsList(ModelMap paramModelMap)
			throws Exception;	
	
	public abstract List searchBoradList(ModelMap paramModelMap)
			throws Exception;	
	
	public abstract int searchBoradListCount(ModelMap paramMap)
			throws Exception;
	

}
