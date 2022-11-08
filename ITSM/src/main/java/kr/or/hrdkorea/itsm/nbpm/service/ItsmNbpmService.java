package kr.or.hrdkorea.itsm.nbpm.service;

import java.util.Map;

import kr.or.hrdkorea.itsm.nbpm.model.NbpmProcessVO;

public interface ItsmNbpmService {
	
	public abstract NbpmProcessVO selectFinalVersionProcessInfo(String paramString)
			throws Exception;	
	
	public abstract String createSrId()
			throws Exception;	
	
	
	public abstract void registerUser(NbpmProcessVO processVO, Map paramMap)
			throws Exception;
	
	public abstract Map executeTask(NbpmProcessVO processVO, Map paramMap)
			throws Exception;
	
	public abstract void notifyTask(NbpmProcessVO processVO, Map result) 
			throws Exception;
	
	public abstract boolean checkTaskStatus(NbpmProcessVO paramNbpmProcessVO)
		    throws Exception;


}
