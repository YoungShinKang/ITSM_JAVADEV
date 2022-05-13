package kr.or.hrdkorea.itsm.user.cmmn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import kr.or.hrdkorea.itsm.user.service.impl.ItsmUserDetailsService;
import kr.or.hrdkorea.itsm.base.util.CryptionChange;

@Component 
public class CustomAuthenticationProvider implements AuthenticationProvider { 
	       
    @Autowired 
    private ItsmUserDetailsService itsmUserDetailsService; 
    
    
    @Autowired 
    private CryptionChange cryptionChange;
    
    @Override 
    public Authentication authenticate(Authentication authentication) throws AuthenticationException { 
        if(authentication == null){ 
            throw new InternalAuthenticationServiceException("Authentication is null"); 
        } 
        String username = authentication.getName(); 
        if(authentication.getCredentials() == null){ 
            throw new AuthenticationCredentialsNotFoundException("Credentials is null"); 
        } 
        
        UserDetails loadedUser = itsmUserDetailsService.loadUserByUsername(username);
           
        if(loadedUser == null){ 
            throw new InternalAuthenticationServiceException("UserDetailsService returned null, which is an interface contract violation"); 
        } 
        /* checker */ 
        if(!loadedUser.isAccountNonLocked()){ 
            throw new LockedException("User account is locked"); 
        } 
        if(!loadedUser.isEnabled()){ 
            throw new DisabledException("User is disabled"); 
        } 
        if(!loadedUser.isAccountNonExpired()){ 
            throw new AccountExpiredException("User account has expired"); 
        } 
        
        /* password 인증 */
        //여기에서 전달받은 loadedUser의 pw와 전달받은 pw의 encryt를 비교한다.
        String password = authentication.getCredentials().toString();
        String encPassword = cryptionChange.encryptPassWord(password);
        
        if(!encPassword.equals(loadedUser.getPassword())) {
        	throw new BadCredentialsException("Password does not match stored value"); 
        }
        /* 실질적인 인증 */ 
        /*
        if(!passwordEncoder.matches(password, loadedUser.getPassword())){ 
            throw new BadCredentialsException("Password does not match stored value"); 
        } 
        */
        /* checker */ 
        if(!loadedUser.isCredentialsNonExpired()){ 
            throw new CredentialsExpiredException("User credentials have expired"); 
        } 
        /* 인증 완료 */ 
        UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(loadedUser, null, loadedUser.getAuthorities()); 
        result.setDetails(authentication.getDetails()); 
        return result; 
    } 
    @Override public boolean supports(Class<?> authentication) { 
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication); 
        //return authentication.equals(UsernamePasswordAuthenticationToken.class); 
    } 
}
