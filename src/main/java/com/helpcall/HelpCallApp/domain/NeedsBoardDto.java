package com.helpcall.HelpCallApp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class NeedsBoardDto {

    @JsonProperty("Id")
    private Long id;
    @JsonProperty("Title")
    private String title;
    @JsonProperty("Needs")
    private List<Need> needs = new ArrayList<>();

    public NeedsBoardDto(Long id) {
        this.id = id;
    }
}
