package kr.or.hrdkorea.itsm.am.model;

import java.util.List;

import kr.or.hrdkorea.itsm.user.model.SysUserVO;

public class AmServiceVO {
	
	private String serviceId;
	private String serviceNm;
	private String ipServiceId;
	private int displayNo;
	private String useYn;
	private String serviceDesc;
	private String serviceImg;
	private String updId;
	private String updDt;
	private String createId;
	private String createDt;
	
	private List<SysUserVO> managers;
	/**
	 * @return the managers
	 */
	public List<SysUserVO> getManagers() {
		return managers;
	}
	/**
	 * @param managers the managers to set
	 */
	public void setManagers(List<SysUserVO> managers) {
		this.managers = managers;
	}
	/**
	 * @return the serviceId
	 */
	public String getServiceId() {
		return serviceId;
	}
	/**
	 * @param serviceId the serviceId to set
	 */
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	/**
	 * @return the serviceNm
	 */
	public String getServiceNm() {
		return serviceNm;
	}
	/**
	 * @param serviceNm the serviceNm to set
	 */
	public void setServiceNm(String serviceNm) {
		this.serviceNm = serviceNm;
	}
	/**
	 * @return the ipServiceId
	 */
	public String getIpServiceId() {
		return ipServiceId;
	}
	/**
	 * @param ipServiceId the ipServiceId to set
	 */
	public void setIpServiceId(String ipServiceId) {
		this.ipServiceId = ipServiceId;
	}
	/**
	 * @return the displayNo
	 */
	public int getDisplayNo() {
		return displayNo;
	}
	/**
	 * @param displayNo the displayNo to set
	 */
	public void setDisplayNo(int displayNo) {
		this.displayNo = displayNo;
	}
	/**
	 * @return the useYn
	 */
	public String getUseYn() {
		return useYn;
	}
	/**
	 * @param useYn the useYn to set
	 */
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	/**
	 * @return the serviceDesc
	 */
	public String getServiceDesc() {
		return serviceDesc;
	}
	/**
	 * @param serviceDesc the serviceDesc to set
	 */
	public void setServiceDesc(String serviceDesc) {
		this.serviceDesc = serviceDesc;
	}
	/**
	 * @return the serviceImg
	 */
	public String getServiceImg() {
		return serviceImg;
	}
	/**
	 * @param serviceImg the serviceImg to set
	 */
	public void setServiceImg(String serviceImg) {
		this.serviceImg = serviceImg;
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
	
	
	

}

