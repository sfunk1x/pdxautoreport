package com.sfunk1x.pdxautoreport.models.responses;

import com.sfunk1x.pdxautoreport.models.Category;

import java.util.List;

public class CategoryResponse {

    private String status;
    private List<Category> categories;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
