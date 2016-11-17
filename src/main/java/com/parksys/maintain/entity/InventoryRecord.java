package com.parksys.maintain.entity;

public class InventoryRecord {
    private Integer id;

    private String inventoryNumber;

    private String inventoryStartTime;

    private String inventoryEndTime;

    private String regisStartTime;

    private String regisEndTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInventoryNumber() {
        return inventoryNumber;
    }

    public void setInventoryNumber(String inventoryNumber) {
        this.inventoryNumber = inventoryNumber == null ? null : inventoryNumber.trim();
    }

    public String getInventoryStartTime() {
        return inventoryStartTime;
    }

    public void setInventoryStartTime(String inventoryStartTime) {
        this.inventoryStartTime = inventoryStartTime == null ? null : inventoryStartTime.trim();
    }

    public String getInventoryEndTime() {
        return inventoryEndTime;
    }

    public void setInventoryEndTime(String inventoryEndTime) {
        this.inventoryEndTime = inventoryEndTime == null ? null : inventoryEndTime.trim();
    }

    public String getRegisStartTime() {
        return regisStartTime;
    }

    public void setRegisStartTime(String regisStartTime) {
        this.regisStartTime = regisStartTime == null ? null : regisStartTime.trim();
    }

    public String getRegisEndTime() {
        return regisEndTime;
    }

    public void setRegisEndTime(String regisEndTime) {
        this.regisEndTime = regisEndTime == null ? null : regisEndTime.trim();
    }
}