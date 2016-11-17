package cmcc.oa.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TempCarInfo {
    private Integer id;

    private Integer clientId;

    private String carNumber;

    private Integer carType;

    private String carUserName;

    private String carUserDept;

    private String carUserPhone;

    private String visitUserName;

    private String visitUserComp;

    private String visitUserPhone;

    private String regUserName;

    private String regUserDept;

    private String regUserPhone;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date regInTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date regOutTime;

    private String retMsg;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber == null ? null : carNumber.trim();
    }

    public Integer getCarType() {
        return carType;
    }

    public void setCarType(Integer carType) {
        this.carType = carType;
    }

    public String getCarUserName() {
        return carUserName;
    }

    public void setCarUserName(String carUserName) {
        this.carUserName = carUserName == null ? null : carUserName.trim();
    }

    public String getCarUserDept() {
        return carUserDept;
    }

    public void setCarUserDept(String carUserDept) {
        this.carUserDept = carUserDept == null ? null : carUserDept.trim();
    }

    public String getCarUserPhone() {
        return carUserPhone;
    }

    public void setCarUserPhone(String carUserPhone) {
        this.carUserPhone = carUserPhone == null ? null : carUserPhone.trim();
    }

    public String getVisitUserName() {
        return visitUserName;
    }

    public void setVisitUserName(String visitUserName) {
        this.visitUserName = visitUserName == null ? null : visitUserName.trim();
    }

    public String getVisitUserComp() {
        return visitUserComp;
    }

    public void setVisitUserComp(String visitUserComp) {
        this.visitUserComp = visitUserComp == null ? null : visitUserComp.trim();
    }

    public String getVisitUserPhone() {
        return visitUserPhone;
    }

    public void setVisitUserPhone(String visitUserPhone) {
        this.visitUserPhone = visitUserPhone == null ? null : visitUserPhone.trim();
    }

    public String getRegUserName() {
        return regUserName;
    }

    public void setRegUserName(String regUserName) {
        this.regUserName = regUserName == null ? null : regUserName.trim();
    }

    public String getRegUserDept() {
        return regUserDept;
    }

    public void setRegUserDept(String regUserDept) {
        this.regUserDept = regUserDept == null ? null : regUserDept.trim();
    }

    public String getRegUserPhone() {
        return regUserPhone;
    }

    public void setRegUserPhone(String regUserPhone) {
        this.regUserPhone = regUserPhone == null ? null : regUserPhone.trim();
    }

    public Date getRegInTime() {
        return regInTime;
    }

    public void setRegInTime(Date regInTime) {
        this.regInTime = regInTime;
    }

    public Date getRegOutTime() {
        return regOutTime;
    }

    public void setRegOutTime(Date regOutTime) {
        this.regOutTime = regOutTime;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg == null ? null : retMsg.trim();
    }
}