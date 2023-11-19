package com.juan.vicarius.request;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;


public record ESIndexRequest(@NotNull @Length(min = 3, max = 20) String sku,
                             @NotNull @Length(min = 2, max = 50) String name, @NotNull double price) {

}
