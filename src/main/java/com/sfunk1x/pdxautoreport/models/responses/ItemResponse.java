package com.sfunk1x.pdxautoreport.models.responses;

import com.sfunk1x.pdxautoreport.models.Item;
import java.util.List;

public class ItemResponse {

    private String status;
    private List<Item> items;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
