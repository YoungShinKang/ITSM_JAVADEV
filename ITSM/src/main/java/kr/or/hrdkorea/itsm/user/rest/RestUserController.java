package kr.or.hrdkorea.itsm.user.rest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.ModelMap;

import kr.or.hrdkorea.itsm.base.model.GridVO;
import kr.or.hrdkorea.itsm.base.model.ResultVO;
import kr.or.hrdkorea.itsm.board.model.ItsmProjectVO;
import kr.or.hrdkorea.itsm.project.service.ItsmProjectService;
import kr.or.hrdkorea.itsm.user.cmmn.AuthTokenUtil;
import kr.or.hrdkorea.itsm.user.model.AuthRequest;
import kr.or.hrdkorea.itsm.user.model.AuthResponse;
import kr.or.hrdkorea.itsm.user.model.SysUserVO;
import kr.or.hrdkorea.itsm.user.model.UserVO;
import kr.or.hrdkorea.itsm.user.service.SysUserService;
import kr.or.hrdkorea.itsm.user.service.impl.ItsmUserDetailsService;
import kr.or.hrdkorea.test.service.RestTestVO;

@CrossOrigin("*")
@RestController
@RequestMapping(path="/user")
public class RestUserController {
	
	@Autowired
	private SysUserService sysUserService;
	
	@Resource(name = "itsmProjectService")
	ItsmProjectService itsmProjectService;
	
	/**
	 * user role만 들고오는 메소드인데, 아래의 user info와 통합되어 효용이 없어서 depricate하는게 옳을 것 같다.
	 * @param userId
	 * @return
	 */
	
	@GetMapping("/role/{userId}")
	public ResultVO searchAuthList(@PathVariable("userId") String userId) 
	{
		ResultVO resultVO = new ResultVO();
		try {
			
			ModelMap paramMap = new ModelMap();		
			paramMap.put("user_id", userId);
			
			HashMap resultMap = new HashMap();
			List sysAuthList = this.itsmProjectService.searchUserSystemAuthList(paramMap);
			  
			resultMap.put("sysAuthList", sysAuthList);
			  
			
			resultVO.setResultMap(resultMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultVO;
	}
	
	@GetMapping("/info/{userId}")
	public UserVO searchUserInfo(@PathVariable("userId") String userId) 
	{
		UserVO userVO = null;
		try {			
			userVO = sysUserService.selectUserInfo(userId);	
			
			
			//user role을 받아오는 부분
			ModelMap paramMap = new ModelMap();		
			paramMap.put("user_id", userId);			
			HashMap resultMap = new HashMap();
			List sysAuthList = this.itsmProjectService.searchUserSystemAuthList(paramMap);
			userVO.setSysAuthList(sysAuthList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userVO;
	}
}
