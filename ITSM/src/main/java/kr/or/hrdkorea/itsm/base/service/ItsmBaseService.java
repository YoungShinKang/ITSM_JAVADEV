package kr.or.hrdkorea.itsm.base.service;

import org.springframework.ui.ModelMap;

import java.util.List;

public interface ItsmBaseService {	
	public abstract List searchServiceCombo(ModelMap paramModelMap)
			throws Exception;
	
	public abstract List searchServiceSubCombo(ModelMap paramModelMap)
			throws Exception;
	
	public abstract List searchServiceTypeCombo(ModelMap paramModelMap)
			throws Exception;

}
