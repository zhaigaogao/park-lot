package com.parksys.maintain.entity;

public class ParkMeter {
    private Integer id;

    private String parkNumber;

    private String parkPostion;

    private String parkFee;

    private String useState;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getParkNumber() {
        return parkNumber;
    }

    public void setParkNumber(String parkNumber) {
        this.parkNumber = parkNumber == null ? null : parkNumber.trim();
    }

    public String getParkPostion() {
        return parkPostion;
    }

    public void setParkPostion(String parkPostion) {
        this.parkPostion = parkPostion == null ? null : parkPostion.trim();
    }

    public String getParkFee() {
        return parkFee;
    }

    public void setParkFee(String parkFee) {
        this.parkFee = parkFee == null ? null : parkFee.trim();
    }

    public String getUseState() {
        return useState;
    }

    public void setUseState(String useState) {
        this.useState = useState == null ? null : useState.trim();
    }
}