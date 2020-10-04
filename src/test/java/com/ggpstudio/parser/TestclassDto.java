package com.ggpstudio.parser;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TestclassDto {
    @JsonProperty("id")
    private String id;
    @NotNull
    @JsonProperty("name")
    private String name;
    @Size(min = 3, max = 15)
    @JsonProperty("street")
    private String street;
    @NotNull
    @JsonProperty("nestedTestclass")
    private NestedTestclassDto nestedTestclass;
    @Positive
    @JsonProperty("streetNumber")
    private int streetNumber;
    @Positive
    @Size(max = 99999)
    @JsonProperty("zip")
    private int zip;
    @Size(max = 10)
    @JsonProperty("orders")
    private List<String> orders;
    @PositiveOrZero
    @JsonProperty("cashBalance")
    private BigDecimal cashBalance;
}
