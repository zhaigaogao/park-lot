package cmcc.oa.vo;

import java.util.Date;

import cmcc.oa.entity.AppointmentDetail;

public class AppointmentDetailVo extends AppointmentDetail {

	private Date enterTime;

	private String search;

	private String createUserId;
	private String createUserName;
	private String createUserMobile;
	private String createUserOrgId;
	private String createUserAllOrgName;

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getCreateUserMobile() {
		return createUserMobile;
	}

	public void setCreateUserMobile(String createUserMobile) {
		this.createUserMobile = createUserMobile;
	}

	public String getCreateUserAllOrgName() {
		return createUserAllOrgName;
	}

	public void setCreateUserAllOrgName(String createUserAllOrgName) {
		this.createUserAllOrgName = createUserAllOrgName;
	}

	public String getCreateUserOrgId() {
		return createUserOrgId;
	}

	public void setCreateUserOrgId(String createUserOrgId) {
		this.createUserOrgId = createUserOrgId;
	}

	public Date getEnterTime() {
		return enterTime;
	}

	public void setEnterTime(Date enterTime) {
		this.enterTime = enterTime;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

}
