package kr.or.hrdkorea.itsm.user.model;

import java.util.List;
import org.apache.commons.lang.builder.ToStringBuilder;

import kr.or.hrdkorea.itsm.base.util.StringUtil;

public class UserVO {
	private String user_id;
	private String user_nm;
	private String cust_id;
	private String cust_nm;
	private String pw;
	private String cust_op_type;
	private String tel_no;
	private String hp_no;
	private String email;
	private String position;
	private String absent_id;
	private String sid;
	private String emp_id;
	private String fax_no;
	private String msg_id;
	private String esign_id;
	private String home_tel_no;
	private String tel_ext;
	private String rel_path;
	private String use_yn;
	private String upd_user_id;
	private String upd_id;
	private String locale;
	private long login_time;
	private String securedUserId;
	private String securedPassWord;
	private List sysAuthList;
	private List processAuthList;
	private List grpAuthList;
	private String vendor_id;
	private String jdeg_cd;
	private String retir_ymd;
	private String retir_rsn_cd;
	private String apptsayu_nam;
	private String home_zipcd;
	private String home_addr1;
	private String home_addr2;
	private String cc;
	private String sub_cc;
	private String load_date;
	private String load_time;
	private String emp_num_clss_cd;
	private String usergrp;

	public String getUser_id() {
		return this.user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_nm() {
		return this.user_nm;
	}

	public void setUser_nm(String user_nm) {
		this.user_nm = user_nm;
	}

	public String getCust_id() {
		return this.cust_id;
	}

	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}

	public String getPw() {
		return this.pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getCust_op_type() {
		return this.cust_op_type;
	}

	public void setCust_op_type(String cust_op_type) {
		this.cust_op_type = cust_op_type;
	}

	public String getTel_no() {
		return this.tel_no;
	}

	public void setTel_no(String tel_no) {
		this.tel_no = tel_no;
	}

	public String getHp_no() {
		return this.hp_no;
	}

	public void setHp_no(String hp_no) {
		this.hp_no = hp_no;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getAbsent_id() {
		return this.absent_id;
	}

	public void setAbsent_id(String absent_id) {
		this.absent_id = absent_id;
	}

	public String getSid() {
		return this.sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getEmp_id() {
		return this.emp_id;
	}

	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}

	public String getFax_no() {
		return this.fax_no;
	}

	public void setFax_no(String fax_no) {
		this.fax_no = fax_no;
	}

	public String getMsg_id() {
		return this.msg_id;
	}

	public void setMsg_id(String msg_id) {
		this.msg_id = msg_id;
	}

	public String getEsign_id() {
		return this.esign_id;
	}

	public void setEsign_id(String esign_id) {
		this.esign_id = esign_id;
	}

	public String getHome_tel_no() {
		return this.home_tel_no;
	}

	public void setHome_tel_no(String home_tel_no) {
		this.home_tel_no = home_tel_no;
	}

	public String getTel_ext() {
		return this.tel_ext;
	}

	public void setTel_ext(String tel_ext) {
		this.tel_ext = tel_ext;
	}

	public String getRel_path() {
		return this.rel_path;
	}

	public void setRel_path(String rel_path) {
		this.rel_path = rel_path;
	}

	public String getUse_yn() {
		return this.use_yn;
	}

	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}

	public String getUpd_user_id() {
		return this.upd_user_id;
	}

	public void setUpd_user_id(String upd_user_id) {
		this.upd_user_id = upd_user_id;
	}

	public String getUpd_id() {
		return this.upd_id;
	}

	public void setUpd_id(String upd_id) {
		this.upd_id = upd_id;
	}

	public String getCust_nm() {
		return this.cust_nm;
	}

	public void setCust_nm(String cust_nm) {
		this.cust_nm = cust_nm;
	}

	public String getDetailInfo() {
		return getCust_nm() + "(" + StringUtil.replaceNullToNA(getTel_no())
				+ " / " + StringUtil.replaceNullToNA(getEmail()) + ")";
	}

	public List getSysAuthList() {
		return this.sysAuthList;
	}

	public void setSysAuthList(List sysAuthList) {
		this.sysAuthList = sysAuthList;
	}

	public List getProcessAuthList() {
		return this.processAuthList;
	}

	public void setProcessAuthList(List processAuthList) {
		this.processAuthList = processAuthList;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public String getSecuredUserId() {
		return this.securedUserId;
	}

	public void setSecuredUserId(String securedUserId) {
		this.securedUserId = securedUserId;
	}

	public String getSecuredPassWord() {
		return this.securedPassWord;
	}

	public void setSecuredPassWord(String securedPassWord) {
		this.securedPassWord = securedPassWord;
	}

	public List getGrpAuthList() {
		return this.grpAuthList;
	}

	public void setGrpAuthList(List grpAuthList) {
		this.grpAuthList = grpAuthList;
	}

	public String getLocale() {
		return this.locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getVendor_id() {
		return this.vendor_id;
	}

	public void setVendor_id(String vendor_id) {
		this.vendor_id = vendor_id;
	}

	public String getJdeg_cd() {
		return this.jdeg_cd;
	}

	public void setJdeg_cd(String jdeg_cd) {
		this.jdeg_cd = jdeg_cd;
	}

	public String getRetir_ymd() {
		return this.retir_ymd;
	}

	public void setRetir_ymd(String retir_ymd) {
		this.retir_ymd = retir_ymd;
	}

	public String getRetir_rsn_cd() {
		return this.retir_rsn_cd;
	}

	public void setRetir_rsn_cd(String retir_rsn_cd) {
		this.retir_rsn_cd = retir_rsn_cd;
	}

	public String getApptsayu_nam() {
		return this.apptsayu_nam;
	}

	public void setApptsayu_nam(String apptsayu_nam) {
		this.apptsayu_nam = apptsayu_nam;
	}

	public String getHome_zipcd() {
		return this.home_zipcd;
	}

	public void setHome_zipcd(String home_zipcd) {
		this.home_zipcd = home_zipcd;
	}

	public String getHome_addr1() {
		return this.home_addr1;
	}

	public void setHome_addr1(String home_addr1) {
		this.home_addr1 = home_addr1;
	}

	public String getHome_addr2() {
		return this.home_addr2;
	}

	public void setHome_addr2(String home_addr2) {
		this.home_addr2 = home_addr2;
	}

	public String getCc() {
		return this.cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getSub_cc() {
		return this.sub_cc;
	}

	public void setSub_cc(String sub_cc) {
		this.sub_cc = sub_cc;
	}

	public String getLoad_date() {
		return this.load_date;
	}

	public void setLoad_date(String load_date) {
		this.load_date = load_date;
	}

	public String getLoad_time() {
		return this.load_time;
	}

	public void setLoad_time(String load_time) {
		this.load_time = load_time;
	}

	public long getLogin_time() {
		return this.login_time;
	}

	public void setLogin_time(long login_time) {
		this.login_time = login_time;
	}

	public String getEmp_num_clss_cd() {
		return this.emp_num_clss_cd;
	}

	public void setEmp_num_clss_cd(String emp_num_clss_cd) {
		this.emp_num_clss_cd = emp_num_clss_cd;
	}

	public String getUserGrp() {
		return this.usergrp;
	}

	public void setgetUserGrp(String usergrp) {
		this.usergrp = usergrp;
	}
}