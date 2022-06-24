package kr.or.hrdkorea.itsm.base.rest;

import javax.annotation.Resource;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.hrdkorea.itsm.base.model.GridVO;
import kr.or.hrdkorea.itsm.base.model.ResultVO;
import kr.or.hrdkorea.itsm.base.service.ItsmBaseService;


@CrossOrigin("*")
@RestController
@RequestMapping(path="/base")
public class RestBaseController {
	
	@Resource(name = "itsmBaseService")
	ItsmBaseService itsmBaseService;
		

	@GetMapping("/searchServiceCombo/{userId}")
	public ResultVO searchServiceCombo(@PathVariable("userId") String userId) 
	{
		ResultVO result = new ResultVO();
	    GridVO gridVO = new GridVO();
	    
	    ModelMap paramMap = new ModelMap();		
		paramMap.put("user_id", userId);
	    
	    try {
	      gridVO.setRows(this.itsmBaseService.searchServiceCombo(paramMap));
	      result.setGridVO(gridVO);
	    } catch (Exception e) {
	    }
	    return result;
	}
	
	@GetMapping("/searchServiceCombo/{codeId}")
	public ResultVO searchServiceSubCombo(@PathVariable("codeId") String codeId) 
	{
		ResultVO result = new ResultVO();
	    GridVO gridVO = new GridVO();
	    
	    ModelMap paramMap = new ModelMap();		
		paramMap.put("up_node_id", codeId);
	    
	    try {
	      gridVO.setRows(this.itsmBaseService.searchServiceSubCombo(paramMap));
	      result.setGridVO(gridVO);
	    } catch (Exception e) {
	    }
	    return result;
	}
	
	
	@GetMapping("/searchServiceTypeCombo/{memberType}")
	public ResultVO searchServiceTypeCombo(@PathVariable("memberType") String memberType) 
	{
		ResultVO result = new ResultVO();
	    GridVO gridVO = new GridVO();
	    
	    ModelMap paramMap = new ModelMap();		
		paramMap.put("member_check", memberType);
	    
	    try {
	      gridVO.setRows(this.itsmBaseService.searchServiceTypeCombo(paramMap));
	      result.setGridVO(gridVO);
	    } catch (Exception e) {
	    }
	    return result;
	}
	
	

}
