package com.juan.vicarius.entity;

import lombok.Builder;

@Builder
public record ESIndex(String sku, String name, double price) {

}
