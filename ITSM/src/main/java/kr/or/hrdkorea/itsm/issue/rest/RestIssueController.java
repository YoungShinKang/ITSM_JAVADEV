package kr.or.hrdkorea.itsm.issue.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
@RequestMapping(path="/issue")
public class RestIssueController {
	
	@Resource(name = "itsmIssueService")
	ItsmIssueService itsmIssueService;
	
	@GetMapping("/viewIssue/{userId}")
	public ResultVO selectProcessDetail(@PathVariable("userId") String userId)
	//@PostMapping("/viewIssue")
	//public ResultVO selectProcessDetail(@RequestBody ModelMap paramMap)
	{  
		Map resultMap = null;
		ResultVO resultVO = new ResultVO();
		try {
			
			//차후 인증부분이 완성되면 아래 작업을 처리한다.
			//그런데 userId 자체가 필요가 없을 것 같다.쿼리를 한번 보고 필요없으면 지운다.
			
			//UserVO userVO = (UserVO)EgovUserDetailsHelper.getAuthenticatedUser();			
			//String sr_id = (String)paramMap.get("sr_id");
			
			
			ModelMap paramMap = new ModelMap();		
			//sr_id
			paramMap.put("sr_id", "SR220421_0003");
			
			
			//String userId = "adminmgt";			
			
			resultMap = itsmIssueService.selectIssueDetail(paramMap, userId);
			resultVO.setResultMap((HashMap)resultMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultVO;
	} 

}
