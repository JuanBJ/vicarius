package com.juan.vicarius.request;

import jakarta.validation.constraints.NotNull;

public class DocumentRequest {

    @NotNull
    private String id;

    @NotNull
    private String name;

    @NotNull
    private String summary;

    @NotNull
    private Double price;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSummary() {
        return summary;
    }

    public Double getPrice() {
        return price;
    }
}
