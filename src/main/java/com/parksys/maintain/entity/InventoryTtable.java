package com.parksys.maintain.entity;

public class InventoryTtable {
    private Integer id;

    private String carNumber;

    private String inventoryTime;

    private String inventoryPersonnel;

    private String regisTime;

    private String inventoryStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber == null ? null : carNumber.trim();
    }

    public String getInventoryTime() {
        return inventoryTime;
    }

    public void setInventoryTime(String inventoryTime) {
        this.inventoryTime = inventoryTime == null ? null : inventoryTime.trim();
    }

    public String getInventoryPersonnel() {
        return inventoryPersonnel;
    }

    public void setInventoryPersonnel(String inventoryPersonnel) {
        this.inventoryPersonnel = inventoryPersonnel == null ? null : inventoryPersonnel.trim();
    }

    public String getRegisTime() {
        return regisTime;
    }

    public void setRegisTime(String regisTime) {
        this.regisTime = regisTime == null ? null : regisTime.trim();
    }

    public String getInventoryStatus() {
        return inventoryStatus;
    }

    public void setInventoryStatus(String inventoryStatus) {
        this.inventoryStatus = inventoryStatus == null ? null : inventoryStatus.trim();
    }
}