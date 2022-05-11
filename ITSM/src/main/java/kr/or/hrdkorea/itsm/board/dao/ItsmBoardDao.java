/**
 * 
 */
package kr.or.hrdkorea.itsm.board.dao;

import org.springframework.stereotype.Repository;
import org.springframework.ui.ModelMap;

import java.sql.SQLException;
import java.util.List;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

/**
 * @author "ysKang"
 *
 */
@Repository("itsmBoardDao")
public class ItsmBoardDao extends EgovAbstractMapper {
	
	public int searchServiceRequestListCount(ModelMap paramMap) throws SQLException {
		return (Integer)selectOne("ITSM_BOARD.searchServiceRequestListCount", paramMap);	  	
	}
	
	public List searchServiceRequestList(ModelMap paramMap) throws SQLException {	  	
		return selectList("ITSM_BOARD.searchServiceRequestList", paramMap);
	}
  
}
