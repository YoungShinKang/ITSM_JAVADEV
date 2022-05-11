package kr.or.hrdkorea.itsm.base.util;

import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;

import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MapDataResultHandler implements ResultHandler {
	
	private List returnList = new ArrayList();
	
	public Map getReturnMap() throws SQLException {
		
		
	    Map returnMap = null;
	    if ((this.returnList != null) && (this.returnList.size() > 0)) {
	      if (this.returnList.size() > 1) {
	        throw new SQLException("Data is not map");
	      }
	      returnMap = (Map)this.returnList.get(0);
	    }

	    return returnMap;
	  }
	
	public List getReturnList() {
		return this.returnList;
	}
	
	@Override
	  public void handleResult(ResultContext rc) {
		
		
	    @SuppressWarnings("unchecked")  
	    Map<String,Object> rowMap = (Map<String,Object>)rc.getResultObject();
	    
	    if (rowMap != null) {
	        Map returnMap = new HashMap();
	        if ((rowMap instanceof Map)) {
	          Map tempMap = (Map)rowMap;

	          Iterator keySet = tempMap.keySet().iterator();
	          while (keySet.hasNext()) {
	            String key = (String)keySet.next();
	            Object rowValue = tempMap.get(key);
	            if ((rowValue instanceof Clob)) {
	              Clob clobData = (Clob)rowValue;
	              String clobAsString = "";
	              Reader in = null;
	              StringWriter strWriter = new StringWriter();
	              try {
	                in = clobData.getCharacterStream();
	                IOUtils.copy(in, strWriter);
	                clobAsString = strWriter.toString();
	              } catch (Exception e) {
	                e.printStackTrace();

	                if (in != null)
	                  try {
	                    in.close();
	                    strWriter.close();
	                  } catch (IOException ioe) {
	                    e.printStackTrace();
	                  }
	              }
	              finally
	              {
	                if (in != null) {
	                  try {
	                    in.close();
	                    strWriter.close();
	                  } catch (IOException e) {
	                    e.printStackTrace();
	                  }
	                }
	              }
	              if (clobAsString != null)
	                returnMap.put(key, clobAsString);
	              else
	                returnMap.put(key, "");
	            }
	            else if ((rowValue instanceof BigDecimal)) {
	              BigDecimal bigDValue = (BigDecimal)rowValue;
	              if (rowValue != null)
	                returnMap.put(key, Long.valueOf(bigDValue.longValue()));
	              else {
	                returnMap.put(key, null);
	              }
	            }
	            else if (rowValue != null) {
	              returnMap.put(key, rowValue);
	            } else {
	              returnMap.put(key, "");
	            }
	          }

	        }

	        this.returnList.add(returnMap);
		    
		  }
	
	}
}
