/**
 * 
 */
package kr.or.hrdkorea.itsm.issue.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import kr.or.hrdkorea.itsm.base.util.MapDataResultHandler;

/**
 * @author "ysKang"
 *
 */
@Repository("itsmIssueDao")
public class ItsmIssueDao extends EgovAbstractMapper {
	
	@Autowired private SqlSessionFactory sqlSessionFactory;
	
	/** 
	 * 실질적으로 proc_master를 모두 불러오는 메소드
	 * 
	 * @param sr_id
	 * @return
	 * @throws SQLException
	 * @throws Exception 
	 */
	
	public Map selectProcessDetail(String sr_id) throws SQLException, Exception
	{
		MapDataResultHandler handler = new MapDataResultHandler();		
		this.getSqlSession().select("ITSM_ISSUE.selectProcessDetail", sr_id, handler);
		return handler.getReturnMap();
		
		/*
		MapDataResultHandler handler = new MapDataResultHandler();
		try {

			SqlSession sqlSession = sqlSessionFactory.openSession();
					

			try {
				sqlSession.select("ITSM_ISSUE.selectProcessDetail", sr_id, handler);
			} finally {
				sqlSession.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		return handler.getReturnMap();
		*/
		//return selectMap("ITSM_ISSUE.selectProcessDetail", sr_id);
	}
	
	/**
	 * proc_service를 모두 불러오는 메소드
	 * 
	 * @param queryKey
	 * @param paramMap
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public Map selectProcessDetailInfo(String sr_id) throws SQLException, Exception	{
		
		MapDataResultHandler handler = new MapDataResultHandler();		
		this.getSqlSession().select("ITSM_ISSUE.selectPROC_SERVICE", sr_id, handler);
		return handler.getReturnMap();
	}
	
	
	public List searchTaskCommentList(String sr_id) throws SQLException, Exception {
	    
	    MapDataResultHandler handler = new MapDataResultHandler();		
		this.getSqlSession().select("ITSM_ISSUE.searchTaskCommentList", sr_id, handler);
		return handler.getReturnList();
	  }
	
	

}
