package kr.or.hrdkorea.itsm.user.model;

import java.io.Serializable;

public class AuthResponse implements Serializable {

	private static final long serialVersionUID = 1820823630527368834L;
	
	private final String authToken;
	private final String username;
	
	public AuthResponse(String authToken,String username) {
        this.authToken = authToken;
        this.username = username;
    }

    public String getUsername() {
		return username;
	}

	public String getToken() {
        return this.authToken;
    }


}
