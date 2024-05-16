package com.yb.wealth.care.ui.resource.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExpenseCategoryBaseDto {
    @NotBlank(message = "name cannot be empty")
    private String name;
    @NotBlank(message = "icon cannot be empty")
    private String icon;
}
