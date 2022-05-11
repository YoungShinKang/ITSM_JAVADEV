package kr.or.hrdkorea.itsm.base.util;

import org.springframework.ui.ModelMap;

public class PagingUtil
{
  public static ModelMap getFristEndNum(ModelMap paramMap)
    throws Exception
  {
    int start = ((Integer)paramMap.get("start")).intValue();
    int page = ((Integer)paramMap.get("page")).intValue();
    int limit = ((Integer)paramMap.get("limit")).intValue();
    int end = limit * page;

    paramMap.put("first_num", Integer.valueOf(start + 1));
    paramMap.put("last_num", Integer.valueOf(end));   

    return paramMap;
  }

  
}