/**
 * 
 */
package kr.or.hrdkorea.itsm.project.dao;

import org.springframework.stereotype.Repository;
import org.springframework.ui.ModelMap;

import java.sql.SQLException;
import java.util.List;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

/**
 * @author "ysKang"
 *
 */
@Repository("itsmProjectDao")
public class ItsmProjectDao extends EgovAbstractMapper {

	public List searchUserSystemAuthList(ModelMap paramMap) throws SQLException {	  	
		return selectList("ITSM_PROJECT.searchUserSystemAuthList", paramMap);
	}
	
	public List personalStatisticsList(ModelMap paramMap) throws SQLException {	  	
		return selectList("ITSM_PROJECT.personalStatisticsList", paramMap);
	}
	
	public List searchBoradList(ModelMap paramMap) throws SQLException {	  	
		return selectList("ITSM_PROJECT.searchBoardList", paramMap);
	}
	
	public int searchBoradListCount(ModelMap paramMap) throws SQLException {
		return (Integer)selectOne("ITSM_PROJECT.searchBoardListCount", paramMap);	  	
	}
	
	
	
	
  
}
