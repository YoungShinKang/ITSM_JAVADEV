package kr.or.hrdkorea.itsm.user.model;

import java.io.Serializable;

public class AuthResponse implements Serializable {

	private static final long serialVersionUID = 1820823630527368834L;
	
	private final String authToken;
	
	public AuthResponse(String authToken) {
        this.authToken = authToken;
    }

    public String getToken() {
        return this.authToken;
    }


}
