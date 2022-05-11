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
		  
//		UserVO userVO = (UserVO)EgovUserDetailsHelper.getAuthenticatedUser();
//		String userId = userVO.getUser_id();
//		
//		JSONObject obj = JSONObject.fromObject(JSONSerializer.toJSON(userVO.getGrpAuthList().get(0)));
//	    String user_grp_id = obj.get("USER_GRP_ID").toString();//paramMap.get("user_grp_id").toString();
				
		String userId = "frazer93";
		String user_grp_id = "";
		
		paramMap.put("user_id", userId);
		paramMap.put("user_grp_id", user_grp_id);
		
	    GridVO gridVO = new GridVO();
	    ResultVO resultVO = new ResultVO();
	    
	    try
	    {
	      int totalCount = 0;

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
