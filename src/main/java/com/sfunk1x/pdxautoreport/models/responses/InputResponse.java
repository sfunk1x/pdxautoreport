package com.sfunk1x.pdxautoreport.models.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InputResponse {

    @JsonProperty("status")
    private String status;

    @JsonProperty("item_id")
    private String itemId;

    @JsonProperty("error")
    private SubmissionError submissionError;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public SubmissionError getSubmissionError() {
        return submissionError;
    }

    public void setSubmissionError(SubmissionError submissionError) {
        this.submissionError = submissionError;
    }
}
