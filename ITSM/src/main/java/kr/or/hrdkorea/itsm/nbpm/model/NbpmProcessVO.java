package kr.or.hrdkorea.itsm.nbpm.model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;



public class NbpmProcessVO {
	
	private String sr_id = null;
	private List<LinkedHashMap<String, String>> operList = null;
	private List itemList = null;
	private String req_user_id = null;
	private String worker_user_id = null;
	private long task_id = 0L;
	private String processId = null;
	private long processInstanceId = 0L;
	private String task_name = null;
	private String process_name = null;
	private String process_type = null;
	private String version = null;
	private String next_node_id = null;
	private String workType = null;	
	private String comment = null;
	private String work_state = null;
	private Map expansionParamMap = null;
	private String nodename = null;
	private String current_role_id = null;
	private String current_worker_id = null;
	private String status = null;
	private List gridMapList = null;
	//20150626 허신행 END_TYPE 추가
	private String end_Type = "";
	private String first_yn = "";
	
	//2016-02-25 허신행 서비스 타입 및 서브 워크 추가
	private String service_type = "";
	private String sub_work = "";
	private String usergrp = "";
	private String login_user_id = ""; 
	
	
	public String getService_type() {
		return service_type;
	}
	public void setService_type(String service_type) {
		this.service_type = service_type;
	}
	public String getSub_work() {
		return sub_work;
	}
	public void setSub_work(String sub_work) {
		this.sub_work = sub_work;
	}
	public String getUsergrp() {
		return usergrp;
	}
	public void setUsergrp(String usergrp) {
		this.usergrp = usergrp;
	}	
	public String getEnd_Type() {
		return end_Type;
	}
	public void setEnd_Type(String end_Type) {
		this.end_Type = end_Type;
	}

	public String getFirst_yn() {
		return first_yn;
	}
	public void setFirst_yn(String first_yn) {
		this.first_yn = first_yn;
	}	
	public List getGridMapList()
	{
		return this.gridMapList;
	}
	public void setGridMapList(List gridMapList) {
		this.gridMapList = gridMapList;
	}
	public String getCurrent_worker_id() {
		return this.current_worker_id;
	}
	public void setCurrent_worker_id(String current_worker_id) {
		this.current_worker_id = current_worker_id;
	}
	public String getNodename() {
		return this.nodename;
	}
	public void setNodename(String nodename) {
		this.nodename = nodename;
	}
	public String getWork_state() {
		return this.work_state;
	}
	public void setWork_state(String work_state) {
		this.work_state = work_state;
	}
	public String getReq_user_id() {
		return this.req_user_id;
	}
	public void setReq_user_id(String req_user_id) {
		this.req_user_id = req_user_id;
	}
	public String getWorker_user_id() {
		return this.worker_user_id;
	}
	public void setWorker_user_id(String worker_user_id) {
		this.worker_user_id = worker_user_id;
	}
	public List getItemList() {
		return this.itemList;
	}
	public void setItemList(List itemList) {
		this.itemList = itemList;
	}
	public List<LinkedHashMap<String, String>> getOperList() {
		return this.operList;
	}
	public void setOperList(List<LinkedHashMap<String, String>> operList) {
		this.operList = operList;
	}
	public String getSr_id() {
		return this.sr_id;
	}
	public void setSr_id(String sr_id) {
		this.sr_id = sr_id;
	}
	public long getTask_id() {
		return this.task_id;
	}
	public void setTask_id(long task_id) {
		this.task_id = task_id;
	}
	public String getTask_name() {
		return this.task_name;
	}
	public void setTask_name(String task_name) {
		this.task_name = task_name;
	}
	public String getProcess_name() {
		return this.process_name;
	}
	public void setProcess_name(String process_name) {
		this.process_name = process_name;
	}
	public String getVersion() {
		return this.version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getProcess_type() {
		return this.process_type;
	}
	public void setProcess_type(String process_type) {
		this.process_type = process_type;
	}
	public String getProcessId() {
		return this.processId;
	}
	public void setProcessId(String processId) {
		this.processId = processId;
	}
	public long getProcessInstanceId() {
		return this.processInstanceId;
	}
	public void setProcessInstanceId(long processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	public String getNext_node_id() {
		return this.next_node_id;
	}
	public void setNext_node_id(String next_node_id) {
		this.next_node_id = next_node_id;
	}
	public String getWorkType() {
		return this.workType;
	}
	public void setWorkType(String workType) {
		this.workType = workType;
	}
	public String getComment() {
		return this.comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Map getExpansionParamMap() {
		return this.expansionParamMap;
	}
	public void setExpansionParamMap(Map expansionParamMap) {
		this.expansionParamMap = expansionParamMap;
	}
	public String getCurrent_role_id() {
		return this.current_role_id;
	}
	public void setCurrent_role_id(String current_role_id) {
		this.current_role_id = current_role_id;
	}
	public String getStatus() {
		return this.status;
	}
	public void setStatus(String status) {
		this.status = status;
	}	
	public String getLogin_user_id() {
		return this.login_user_id;
	}
	public void setLogin_user_id(String login_user_id) {
		this.login_user_id = login_user_id;
	}

}
