package kr.or.hrdkorea.itsm.user.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import kr.or.hrdkorea.itsm.user.model.SysUserVO;
import kr.or.hrdkorea.itsm.user.service.SysUserService;

import org.springframework.security.core.GrantedAuthority;




@Service
public class ItsmUserDetailsService implements UserDetailsService {
	
	@Resource(name = "sysUserService")
	SysUserService sysUserService;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
    	
    	SysUserVO paramVO = new SysUserVO();
    	paramVO.setUserId(userId);    	
    	SysUserVO sysUserVO = sysUserService.selectLogin(paramVO);
    	
    	
    	if(sysUserVO != null) {
    		return new User(sysUserVO.getUserId(), sysUserVO.getPw(),
                    getAuthorities());
    	} else {
            throw new UsernameNotFoundException("User not found with username: " + userId);
        }
        
    }  
    
    public Collection<GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();

        list.add(new SimpleGrantedAuthority("ROLE_USER"));

        return list;
    }
}


