package com.sfunk1x.pdxautoreport.models.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ValidateUserResponse {

    @JsonProperty("phone_num")
    private String phoneNumber;

    @JsonProperty("created_date")
    private String createdDate;

    @JsonProperty("fax_num")
    private String faxNumber;

    @JsonProperty("middle_initial")
    private String middleInitial;

    @JsonProperty("zipcode")
    private int zipcode;

    @JsonProperty("loginvalid")
    private boolean loginValid;

    @JsonProperty("state")
    private String state;

    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("status")
    private String status;

    @JsonProperty("city")
    private String city;

    @JsonProperty("address")
    private String address;

    @JsonProperty("updated_date")
    private String updatedDate;

    @JsonProperty("contact_id")
    private int contactId;

    @JsonProperty("mobile_num")
    private String mobileNumber;

    @JsonProperty("error")
    private SubmissionError error;

    public ValidateUserResponse () {}

    public ValidateUserResponse (String phoneNumber,
                                String createdDate,
                                String faxNumber,
                                String middleInitial,
                                int zipcode,
                                boolean loginValid,
                                String state,
                                String userName,
                                String email,
                                String lastName,
                                String firstName,
                                String status,
                                String city,
                                String address,
                                String updatedDate,
                                int contactId,
                                String mobileNumber,
                                SubmissionError error) {
        this.phoneNumber = phoneNumber;
        this.createdDate = createdDate;
        this.faxNumber = faxNumber;
        this.middleInitial = middleInitial;
        this.zipcode = zipcode;
        this.loginValid = loginValid;
        this.state = state;
        this.userName = userName;
        this.email = email;
        this.lastName = lastName;
        this.firstName = firstName;
        this.status = status;
        this.city = city;
        this.address = address;
        this.updatedDate = updatedDate;
        this.contactId = contactId;
        this.mobileNumber = mobileNumber;
        this.error = error;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getMiddleInitial() {
        return middleInitial;
    }

    public void setMiddleInitial(String middleInitial) {
        this.middleInitial = middleInitial;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public boolean isLoginValid() {
        return loginValid;
    }

    public void setLoginValid(boolean loginValid) {
        this.loginValid = loginValid;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public SubmissionError getError() {
        return error;
    }

    public void setError(SubmissionError error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "ValidateUserResponse{\"phone_num\":\"" + phoneNumber + "\"" +
                ", \"created_date\":\"" + createdDate + "\"" +
                ", \"fax_num=\":\"" + faxNumber + "\"" +
                ", \"middle_initial\":\"" + middleInitial + "\"" +
                ", \"zipcode\":" + zipcode +
                ", \"loginvalid\":" + loginValid +
                ", \"state\":\"" + state + "\"" +
                ", \"user_name\":\"" + userName + "\"" +
                ", \"email\":\"" + email + "\"" +
                ", \"last_name\":\"" + lastName + "\"" +
                ", \"first_name\":\"" + firstName + "\"" +
                ", \"status\":\"" + status + "\"" +
                ", \"city\":\"" + city + "\"" +
                ", \"address\":\"" + address + "\"" +
                ", \"contact_id\":" + contactId +
                ", \"mobile_num\":\"" + mobileNumber + "\"" +
                '}';
    }
}
