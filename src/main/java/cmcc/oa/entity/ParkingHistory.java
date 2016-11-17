package cmcc.oa.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ParkingHistory {
    private String id;

    private String carNumber;

    private Integer carOwnerType;

    private Long appointmentDetailId;

    private Integer areaId;

    private String spaceId;

    private Date enterTime;

    private String enterDevice;

    private Integer enterStatus;

    private Date exitTime;

    private String exitDevice;

    private Integer exitStatus;

    private BigDecimal price;

    private Integer status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber == null ? null : carNumber.trim();
    }

    public Integer getCarOwnerType() {
        return carOwnerType;
    }

    public void setCarOwnerType(Integer carOwnerType) {
        this.carOwnerType = carOwnerType;
    }

    public Long getAppointmentDetailId() {
        return appointmentDetailId;
    }

    public void setAppointmentDetailId(Long appointmentDetailId) {
        this.appointmentDetailId = appointmentDetailId;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(String spaceId) {
        this.spaceId = spaceId == null ? null : spaceId.trim();
    }

    public Date getEnterTime() {
        return enterTime;
    }

    public void setEnterTime(Date enterTime) {
        this.enterTime = enterTime;
    }

    public String getEnterDevice() {
        return enterDevice;
    }

    public void setEnterDevice(String enterDevice) {
        this.enterDevice = enterDevice == null ? null : enterDevice.trim();
    }

    public Integer getEnterStatus() {
        return enterStatus;
    }

    public void setEnterStatus(Integer enterStatus) {
        this.enterStatus = enterStatus;
    }

    public Date getExitTime() {
        return exitTime;
    }

    public void setExitTime(Date exitTime) {
        this.exitTime = exitTime;
    }

    public String getExitDevice() {
        return exitDevice;
    }

    public void setExitDevice(String exitDevice) {
        this.exitDevice = exitDevice == null ? null : exitDevice.trim();
    }

    public Integer getExitStatus() {
        return exitStatus;
    }

    public void setExitStatus(Integer exitStatus) {
        this.exitStatus = exitStatus;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}