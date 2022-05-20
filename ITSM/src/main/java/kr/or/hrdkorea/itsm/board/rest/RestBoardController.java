package kr.or.hrdkorea.itsm.board.rest;

import java.util.ArrayList;
import java.util.Arrays;
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
import kr.or.hrdkorea.itsm.user.model.SysUserVO;
import kr.or.hrdkorea.itsm.user.service.SysUserService;
import kr.or.hrdkorea.test.service.RestTestVO;

@CrossOrigin("*")
@RestController
@RequestMapping(path="/board")
public class RestBoardController {
	
	/*
	 * @Resource(name = "sysUserService")
	SysUserService sysUserService;
	 */
	
	@Resource(name = "itsmBoardService")
	ItsmBoardService itsmBoardService;
	
	@Resource(name = "itsmIssueService")
	ItsmIssueService itsmIssueService;
	
	
	/**
	 * 임시 구현상태
	 * @param userId
	 * @return
	 */
	@GetMapping("/list/{userId}")
	public ItsmProjectVO testCall(@PathVariable("userId") String userId) {
		ItsmProjectVO pjt = new ItsmProjectVO();
		pjt.setItsmBoardInfo(itsmBoardService.selectItsmBoardInfo(userId));
		pjt.setIssues(itsmIssueService.selectItsmIssues(userId));		
		return pjt;
	}
	
	@PostMapping("/createIssue")
	public ResponseEntity<ItsmIssueVO> cityAdd(@RequestBody ItsmIssueVO itsmIssueVO) {
		//log.debug("itsmIssueVO = {}", itsmIssueVO.toString());
		return new ResponseEntity<>(itsmIssueVO, HttpStatus.OK);
	}
	
	
	@PostMapping("/searchServiceRequestList")
	public ResultVO searchServicedeskList(@RequestBody ModelMap paramMap) throws Exception
	  {		  
		//임시로 셋팅
		String userId = "frazer93";		
		paramMap.put("user_id", userId);
		
	    GridVO gridVO = new GridVO();
	    ResultVO resultVO = new ResultVO();
	    
	    try
	    {
	      int totalCount = 0;

	      //이 리스트는 클라이언트에서 넘어와야 함
	      List <String> workState = Arrays.asList("REQUEST","SERVICE_GROUP");
	      
	      paramMap.put("work_state_list", workState);

	      PagingUtil.getFristEndNum(paramMap);
	      
	      
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

}
