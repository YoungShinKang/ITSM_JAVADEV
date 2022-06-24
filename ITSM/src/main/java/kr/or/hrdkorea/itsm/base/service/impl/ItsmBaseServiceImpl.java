/**
 * 
 */
package kr.or.hrdkorea.itsm.base.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import kr.or.hrdkorea.itsm.base.dao.ItsmBaseDao;
import kr.or.hrdkorea.itsm.base.service.ItsmBaseService;


/**
 * @author "ysKang"
 *
 */
@Service("itsmBaseService")
public class ItsmBaseServiceImpl implements ItsmBaseService {
	
	@Resource(name="itsmBaseDao")
	public ItsmBaseDao itsmBaseDao;	
	
	public List searchServiceCombo(ModelMap paramMap) throws Exception {
		return this.itsmBaseDao.searchServiceCombo(paramMap);
	}
	
	public List searchServiceSubCombo(ModelMap paramMap) throws Exception {
		return this.itsmBaseDao.searchServiceSubCombo(paramMap);
	}
	
	public List searchServiceTypeCombo(ModelMap paramMap) throws Exception {
		return this.itsmBaseDao.searchServiceTypeCombo(paramMap);
	}

}
