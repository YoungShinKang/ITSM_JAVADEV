/**
 * 
 */
package kr.or.hrdkorea.test.service;

import java.io.Serializable;

/**
 * @author ntek01
 *
 */
public class RestTestVO implements Serializable {
	
	private String reponseMessage = "";

	public String getReponseMessage() {
		return reponseMessage;
	}

	public void setReponseMessage(String reponseMessage) {
		this.reponseMessage = reponseMessage;
	}

}
