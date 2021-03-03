package com.helpcall.HelpCallApp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NeedsBoardDto {

    @JsonProperty("Id")
    private Long id;
    @JsonProperty("Needs")
    private List<Need> needs = new ArrayList<>();

    public NeedsBoardDto(Long id) {
        this.id = id;
    }
}
