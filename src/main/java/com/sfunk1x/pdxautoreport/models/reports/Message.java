package com.sfunk1x.pdxautoreport.models.reports;

public class Message {

    private String legalName;
    private String telephoneNumber;
    private String closestAddress;
    private String vehicleMake;
    private String vehicleColor;
    private String vehicleBodyStyle;
    private String vehicleLicensePlate;
    private String vehicleDescription;

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getClosestAddress() {
        return closestAddress;
    }

    public void setClosestAddress(String closestAddress) {
        this.closestAddress = closestAddress;
    }

    public String getVehicleMake() {
        return vehicleMake;
    }

    public void setVehicleMake(String vehicleMake) {
        this.vehicleMake = vehicleMake;
    }

    public String getVehicleColor() {
        return vehicleColor;
    }

    public void setVehicleColor(String vehicleColor) {
        this.vehicleColor = vehicleColor;
    }

    public String getVehicleBodyStyle() {
        return vehicleBodyStyle;
    }

    public void setVehicleBodyStyle(String vehicleBodyStyle) {
        this.vehicleBodyStyle = vehicleBodyStyle;
    }

    public String getVehicleLicensePlate() {
        return vehicleLicensePlate;
    }

    public void setVehicleLicensePlate(String vehicleLicensePlate) {
        this.vehicleLicensePlate = vehicleLicensePlate;
    }

    public String getVehicleDescription() {
        return vehicleDescription;
    }

    public void setVehicleDescription(String vehicleDescription) {
        this.vehicleDescription = vehicleDescription;
    }

    @Override
    public String toString() {
        return legalName + "\n" +
        telephoneNumber + "\n" +
        closestAddress + "\n" +
        vehicleMake + "\n" +
        vehicleColor + "\n" +
        vehicleBodyStyle + "\n" +
        vehicleLicensePlate + "\n" +
        vehicleDescription;
    }
}
