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
import kr.or.hrdkorea.itsm.nbpm.model.NbpmProcessVO;
import kr.or.hrdkorea.itsm.nbpm.service.ItsmNbpmService;
import kr.or.hrdkorea.itsm.nbpm.util.NbpmUtil;
import kr.or.hrdkorea.itsm.user.model.SysUserVO;
import kr.or.hrdkorea.itsm.user.service.SysUserService;
import kr.or.hrdkorea.test.service.RestTestVO;

@CrossOrigin("*")
@RestController
@RequestMapping(path="/issue")
public class RestIssueController {
	
	@Resource(name = "itsmIssueService")
	ItsmIssueService itsmIssueService;
	
	@Resource(name = "itsmNbpmService")
	ItsmNbpmService itsmNbpmService;
	
	@PostMapping("/viewIssue")
	public ResultVO selectProcessDetail(@RequestBody ModelMap paramMap)
	{  
		Map resultMap = null;
		ResultVO resultVO = new ResultVO();
		//신규 주석 처리
		try {		
			resultMap = itsmIssueService.selectIssueDetail(paramMap);
			resultVO.setResultMap((HashMap)resultMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultVO;
		
	} 

	@PostMapping("/getInsertPageInfo")
	public ResultVO getInsertPageInfo(@RequestBody ModelMap paramMap)
	{
	    ResultVO resultVO = new ResultVO();
	    try {
	      HashMap dataMap = new HashMap();

	      String processType = (String)paramMap.get("process_type"); //파라미터로 SERVICE가 넘어옴

	      String sr_id = (String)paramMap.get("sr_id");
	      String up_sr_id = (String)paramMap.get("up_sr_id");


	      String userId = (String)paramMap.get("user_id");

	      NbpmProcessVO processVO = this.itsmNbpmService.selectFinalVersionProcessInfo(processType);
	      //서비스 타입으로 하면 결국 SERVICE_v_1, 서비스관리 라는 값이 넘어온다.
	      //process가 의미하는것, 멀티태스크, 문제관리, 서비스관리, 변경관리, 장애관리 와 같이 ITSM이 제공하는 서비스를 말하는것이면 현재는
	      //서비스 관리 , SERVICE_v_1 만 사용된다.

	      String version = processVO.getVersion(); //1이 넘어온다.
	      
	      
	      if ((sr_id == null) || ("".equals(sr_id))) {
    		  sr_id = this.itsmNbpmService.createSrId();
    	  }

	      dataMap.put("sr_id", sr_id);
	      dataMap.put("up_sr_id", up_sr_id);
	      dataMap.put("nbpm_user_id", userId);
	      dataMap.put("nbpm_task_name", "start");
	      dataMap.put("nbpm_node_name", "서비스등록");
	      dataMap.put("nbpm_process_type", processVO.getProcess_type()); //SERVICE
	      dataMap.put("nbpm_processId", processVO.getProcessId()); //SERVICE_v_1
	      dataMap.put("nbpm_processInstanceId", Long.valueOf(processVO.getProcessInstanceId()));
	      dataMap.put("nbpm_version", processVO.getVersion()); //1
	      dataMap.put("req_type", processType); //SERVICE 이건 좀 중복일 수 있나?

	      resultVO.setResultMap((HashMap)dataMap);
	      
	    } catch (Exception e) {
	    	e.printStackTrace();
		}
		return resultVO;
	}
	
	
	@PostMapping("/processRequest")
	public ResultVO processRequest(@RequestBody ModelMap paramMap)
	{
		ResultVO resultVO = new ResultVO();
		String message = "";
		try {
			String loginUserId = (String)paramMap.get("user_id");
			
			
			NbpmProcessVO processVO = NbpmUtil.setProcessParameter(paramMap);
			processVO.setWorker_user_id(loginUserId);
			  
			
			this.itsmNbpmService.registerUser(processVO, paramMap);
			
			//저장하는 작업 수행
			Map result = this.itsmNbpmService.executeTask(processVO, paramMap);
			
			
			//일단 이 동작은 굳이 분석이 필요하지 않다.
			processVO.setWork_state("REQUEST");
			processVO.setLogin_user_id(loginUserId);
			
			//메일 혹은 SMS 보내기.
			this.itsmNbpmService.notifyTask(processVO, result);
			
			resultVO.setResultMsg("등록되었습니다.");
			resultVO.setResultMap((HashMap)result);
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultVO;
	}
}
