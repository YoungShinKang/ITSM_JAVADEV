/**
 * 
 */
package kr.or.hrdkorea.itsm.base.dao;

import org.springframework.stereotype.Repository;
import org.springframework.ui.ModelMap;
import java.sql.SQLException;
import java.util.List;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

/**
 * @author "ysKang"
 *
 */
@Repository("itsmBaseDao")
public class ItsmBaseDao extends EgovAbstractMapper {

	public List searchServiceCombo(ModelMap paramMap) throws SQLException {	  	
		return selectList("ITSM_BASE.searchServiceCombo", paramMap);
	}
	
	public List searchServiceSubCombo(ModelMap paramMap) throws SQLException {	  	
		return selectList("ITSM_BASE.searchServiceSubCombo", paramMap);
	}
	
	public List searchServiceTypeCombo(ModelMap paramMap) throws SQLException {	  	
		
		if("1".equals(paramMap.get("member_check"))){
			return selectList("ITSM_BASE.searchServiceTypeCombo", paramMap);
		}else{
			return selectList("ITSM_BASE.searchServiceTypeCombo_Push", paramMap);
		}
	}
	

	
	
	
	
  
}
