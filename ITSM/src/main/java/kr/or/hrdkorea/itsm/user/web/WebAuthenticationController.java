package kr.or.hrdkorea.itsm.user.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class WebAuthenticationController {
		
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String loginView( ) {
		return "user/webJsonLogin";
	}

}
