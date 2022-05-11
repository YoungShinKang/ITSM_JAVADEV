package kr.or.hrdkorea.itsm.am.model;

import java.util.List;

public class AmAppVO {
	
	private String confId;
	private String appDesc;
	private String updId;
	private String updDt;
	private String createId;
	private String createDt;
	
	private List<AmServiceVO> amServices;

	/**
	 * @return the confId
	 */
	public String getConfId() {
		return confId;
	}

	/**
	 * @param confId the confId to set
	 */
	public void setConfId(String confId) {
		this.confId = confId;
	}

	/**
	 * @return the appDesc
	 */
	public String getAppDesc() {
		return appDesc;
	}

	/**
	 * @param appDesc the appDesc to set
	 */
	public void setAppDesc(String appDesc) {
		this.appDesc = appDesc;
	}

	/**
	 * @return the updId
	 */
	public String getUpdId() {
		return updId;
	}

	/**
	 * @param updId the updId to set
	 */
	public void setUpdId(String updId) {
		this.updId = updId;
	}

	/**
	 * @return the updDt
	 */
	public String getUpdDt() {
		return updDt;
	}

	/**
	 * @param updDt the updDt to set
	 */
	public void setUpdDt(String updDt) {
		this.updDt = updDt;
	}

	/**
	 * @return the createId
	 */
	public String getCreateId() {
		return createId;
	}

	/**
	 * @param createId the createId to set
	 */
	public void setCreateId(String createId) {
		this.createId = createId;
	}

	/**
	 * @return the createDt
	 */
	public String getCreateDt() {
		return createDt;
	}

	/**
	 * @param createDt the createDt to set
	 */
	public void setCreateDt(String createDt) {
		this.createDt = createDt;
	}

	/**
	 * @return the amServices
	 */
	public List<AmServiceVO> getAmServices() {
		return amServices;
	}

	/**
	 * @param amServices the amServices to set
	 */
	public void setAmServices(List<AmServiceVO> amServices) {
		this.amServices = amServices;
	}
	

}
