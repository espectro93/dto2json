package com.ggpstudio.parser;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class NestedTestclassDto {
    @JsonProperty("name")
    private String name;
    @JsonProperty("interest")
    private String interest;
}
