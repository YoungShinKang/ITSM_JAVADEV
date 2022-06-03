package kr.or.hrdkorea.itsm.project.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.hrdkorea.itsm.base.model.GridVO;
import kr.or.hrdkorea.itsm.base.model.ResultVO;
import kr.or.hrdkorea.itsm.base.util.PagingUtil;
import kr.or.hrdkorea.itsm.board.model.ItsmBoardVO;
import kr.or.hrdkorea.itsm.board.model.ItsmProjectVO;
import kr.or.hrdkorea.itsm.board.service.ItsmBoardService;
import kr.or.hrdkorea.itsm.issue.model.ItsmIssueVO;
import kr.or.hrdkorea.itsm.issue.service.ItsmIssueService;
import kr.or.hrdkorea.itsm.project.service.ItsmProjectService;
import kr.or.hrdkorea.itsm.user.model.SysUserVO;
import kr.or.hrdkorea.itsm.user.service.SysUserService;
import kr.or.hrdkorea.test.service.RestTestVO;

@CrossOrigin("*")
@RestController
@RequestMapping(path="/dashBoard")
public class RestProjectController {
	
	@Resource(name = "itsmProjectService")
	ItsmProjectService itsmProjectService;
	
	@Resource(name = "itsmBoardService")
	ItsmBoardService itsmBoardService;
	
		
	@PostMapping("/searchBoradList")
	public ResultVO searchBoradList(@RequestBody ModelMap paramMap) throws Exception
	//@GetMapping("/searchBoradList")
	//public ResultVO searchBoradList(ModelMap paramMap) throws Exception	
	{
		ResultVO resultVO = new ResultVO();
		try {
			
			HashMap resultMap = new HashMap();
			
			/*
			 * 임시 테스트용 값 설정
			 */
			paramMap.put("start", 1);
			paramMap.put("page", 1);
			paramMap.put("limit", 100);
			
			PagingUtil.getFristEndNum(paramMap);
			List boardList = this.itsmProjectService.searchBoradList(paramMap);			  
			resultMap.put("boardList", boardList);
			  
			
			resultVO.setResultMap(resultMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultVO;
	}
	
	@PostMapping("/searchTodayServiceRequestList")
	public ResultVO searchTodayServiceRequestList(@RequestBody ModelMap paramMap) throws Exception
	  {		  
		
		String userId = "frazer93";
		String user_grp_id = "";
		
		paramMap.put("user_id", userId);
		
	    GridVO gridVO = new GridVO();
	    ResultVO resultVO = new ResultVO();
	    
	    try
	    {
	      int totalCount = 0;

	      List <String> workState = Arrays.asList("REQUEST","SERVICE_GROUP");    
	            
	      totalCount = this.itsmBoardService.searchServiceRequestListCount(paramMap);
	      List resultList = this.itsmBoardService.searchServiceRequestList(paramMap);
	      	      
	      gridVO.setTotalCount(totalCount);
	      gridVO.setRows(resultList);

	      resultVO.setGridVO(gridVO);
	      resultVO.setSuccess(true);
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return resultVO; 
	  } 
	
	
	@GetMapping("/statistics/requestSimple/{userId}")
	public ResultVO personalStatistics(@PathVariable("userId") String userId) 
	{
		ResultVO resultVO = new ResultVO();
		try {
			
			ModelMap paramMap = new ModelMap();		
			paramMap.put("user_id", userId);
			
			HashMap resultMap = new HashMap();
			List requestStatisticsList = this.itsmProjectService.personalStatisticsList(paramMap);
			  
			resultMap.put("requestStatistics", requestStatisticsList);
			  
			
			resultVO.setResultMap(resultMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultVO;
	}
	
	

}
