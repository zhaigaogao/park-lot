package cmcc.oa.entity;

public class Organization {
    private String id;

    private String creatTime;

    private String orgName;

    private String status;

    private String updateTime;

    private Integer showindex;

    private String previousId;

    private String orgFullname;

    private String haschildorg;

    private String companyId;

    private String vId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime == null ? null : creatTime.trim();
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }

    public Integer getShowindex() {
        return showindex;
    }

    public void setShowindex(Integer showindex) {
        this.showindex = showindex;
    }

    public String getPreviousId() {
        return previousId;
    }

    public void setPreviousId(String previousId) {
        this.previousId = previousId == null ? null : previousId.trim();
    }

    public String getOrgFullname() {
        return orgFullname;
    }

    public void setOrgFullname(String orgFullname) {
        this.orgFullname = orgFullname == null ? null : orgFullname.trim();
    }

    public String getHaschildorg() {
        return haschildorg;
    }

    public void setHaschildorg(String haschildorg) {
        this.haschildorg = haschildorg == null ? null : haschildorg.trim();
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    public String getvId() {
        return vId;
    }

    public void setvId(String vId) {
        this.vId = vId == null ? null : vId.trim();
    }
}