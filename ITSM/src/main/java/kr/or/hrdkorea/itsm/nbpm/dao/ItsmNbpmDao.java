/**
 * 
 */
package kr.or.hrdkorea.itsm.nbpm.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import kr.or.hrdkorea.itsm.base.util.MapDataResultHandler;
import kr.or.hrdkorea.itsm.nbpm.model.NbpmProcessVO;

/**
 * @author "ysKang"
 *
 */
@Repository("itsmNbpmDao")
public class ItsmNbpmDao extends EgovAbstractMapper {
	
	public NbpmProcessVO selectFinalVersionProcessInfo(String process_type) throws SQLException, Exception 
	{
		return (NbpmProcessVO)this.getSqlSession().selectOne("ITSM_NBPM.selectFinalVersionProcessInfo", 
				process_type);
	}
	
	public String createSrId() throws SQLException, Exception 
	{
		return (String)this.getSqlSession().selectOne("ITSM_NBPM.createSrId");
	}
	
	public int isExistNbpmUser(String userId) throws SQLException, Exception 
	{
		return ((Integer)this.getSqlSession().selectOne("ITSM_NBPM.isExistNbpmUser", userId)).intValue();
	}
	
	public void insertNbpmUser(String userId) throws SQLException, Exception 
	{
		this.getSqlSession().insert("ITSM_NBPM.insertNbpmUser", userId);
	}
	
	public Map selectProcessInfo(NbpmProcessVO processVO) throws SQLException, Exception {
		return (Map)this.getSqlSession().selectOne("ITSM_NBPM.selectProcessInfo", processVO);
	}
	
	public List searchNbpmProcessVariable(String processId) throws SQLException, Exception {
		return this.getSqlSession().selectList("ITSM_NBPM.searchNbpmProcessVariable", processId);
	}
	
	public int selectExistSrId(String srId) throws SQLException, Exception {
		return ((Integer)this.getSqlSession().selectOne("ITSM_NBPM.selectExistSrId", srId)).intValue();
	}
	
	public void insertProcessMaster(Map paramMap) throws SQLException, Exception
	{
		this.getSqlSession().insert("ITSM_NBPM.insertProcessMaster", paramMap);
	}
	
	public void insertProcessOrerator(Map operInfo) throws SQLException, Exception
	{
		this.getSqlSession().insert("ITSM_NBPM.insertProcessOrerator", operInfo);
	}
	
	public void insertProcessItem(Map itemInfo) throws SQLException, Exception
	{
		this.getSqlSession().insert("ITSM_NBPM.insertProcessItem", itemInfo);
	}
	
	public void updateProcessMaster(Map paramMap) throws SQLException, Exception
	{
		this.getSqlSession().update("ITSM_NBPM.updateProcessMaster", paramMap);
	}
	
	public void deleteProcessOrerator(Map operInfo) throws SQLException, Exception {
		this.getSqlSession().delete("ITSM_NBPM.deleteProcessOrerator", operInfo);
	}
	
	public void deleteProcessItem(String srId) throws SQLException, Exception {
		this.getSqlSession().insert("ITSM_NBPM.deleteProcessItem", srId);
	}
	
	public int selectExistSrIdInDetailTable(String checkQuery, Map paramMap) throws SQLException, Exception	{
		return ((Integer)this.getSqlSession().selectOne("ProcDetail." + checkQuery, paramMap)).intValue();
	}
	
	public void excuteProcessDetail(String queryKey, Map paramMap) throws SQLException, Exception {
		this.getSqlSession().update("ProcDetail." + queryKey, paramMap);
	}
	
	public void deleteGridData(String gridDeleteQueryKey, Map param) throws SQLException, Exception {
		this.getSqlSession().update("ProcDetail." + gridDeleteQueryKey, param);
	}
	
	public void insertGridData(String gridInsertQueryKey, Map param) throws SQLException, Exception {
		this.getSqlSession().update("ProcDetail." + gridInsertQueryKey, param);
	}
	
	public void insertProcessDetail(Map detailInfo) throws SQLException, Exception {
		this.getSqlSession().insert("ITSM_NBPM.insertProcessDetail", detailInfo);
	}
	
	public void updateEndWorkState(Map paramMap) throws SQLException, Exception {
		this.getSqlSession().update("ITSM_NBPM.updateEndWorkState", paramMap);
	}
	
	public void updateAtchFileState(Map paramMap) throws SQLException, Exception {
		this.getSqlSession().update("ITSM_NBPM.updateAtchFileState", paramMap);
	}
	
	public void updateProcessDetailReject(Map detailInfo) throws SQLException, Exception {
		this.getSqlSession().update("ITSM_NBPM.updateProcessDetailReject", detailInfo);
	}
	
	public void updateProcessDetailRecheck(Map detailInfo) throws SQLException, Exception
	{
		this.getSqlSession().update("ITSM_NBPM.updateProcessDetailRecheck", detailInfo);
	}

	public void updateProcessDetailFirst(Map detailInfo) throws SQLException, Exception
	{
		this.getSqlSession().update("ITSM_NBPM.updateProcessDetailFirst", detailInfo);
	}
	
	public void updateWorkState(Map paramMap) throws SQLException, Exception {
		this.getSqlSession().update("ITSM_NBPM.updateWorkState", paramMap);
	}
	
	public List searchRelIncList(String sr_id) {
		return this.getSqlSession().selectList("ITSM_NBPM.searchRelIncList", sr_id);
	}	
	
	public List searchNbpmAlamList(NbpmProcessVO processVO) throws SQLException, Exception 	{
		if("REQUEST".equals(processVO.getWork_state())) {
		return this.getSqlSession().selectList("ITSM_NBPM.searchNbpmAlamListRequest", processVO);
		} else if("SERVICE_CHECK".equals(processVO.getWork_state()) && !"08".equals(processVO.getService_type())) {
		return this.getSqlSession().selectList("ITSM_NBPM.searchNbpmAlamListCheck", processVO);
		} else {
		return this.getSqlSession().selectList("ITSM_NBPM.searchNbpmAlamList", processVO);
		}
	}

}
