package kr.or.hrdkorea.itsm.user.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

import kr.or.hrdkorea.itsm.user.cmmn.AuthTokenUtil;
import kr.or.hrdkorea.itsm.user.model.AuthRequest;
import kr.or.hrdkorea.itsm.user.model.AuthResponse;
import kr.or.hrdkorea.itsm.user.model.SysUserVO;
import kr.or.hrdkorea.itsm.user.service.impl.ItsmUserDetailsService;
import kr.or.hrdkorea.test.service.RestTestVO;

@CrossOrigin("*")
@RestController
@RequestMapping(path="/auth")
public class RestAuthenticationController {
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
	@Autowired
    private ItsmUserDetailsService userDetailsService;
	
	@Autowired
    private AuthTokenUtil authTokenUtil;
	
	@PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authenticationRequest) throws Exception {
        
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService
            .loadUserByUsername(authenticationRequest.getUsername());

        final String token = authTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(token));
    }
	
	
	
	/**
	 * @param authenticationRequest
	 * @return
	 * @throws Exception
	 * 상단의 로그인의 실 버전으로 내용은 동일하다. 다만 파라미터 VO가 다르다
	 */
	@PostMapping("/authenticate")
    public ResponseEntity<?> userAuthneicate(@RequestBody SysUserVO sysUserVO) throws Exception {
        
		authenticate(sysUserVO.getUserId(), sysUserVO.getPw());

        final UserDetails userDetails = userDetailsService
            .loadUserByUsername(sysUserVO.getUserId());
        
        final String token = authTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(token));
    }
	
	private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
	
}
