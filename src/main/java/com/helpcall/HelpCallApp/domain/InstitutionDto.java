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
public class InstitutionDto {

    @JsonProperty("Id")
    private Long id;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Email")
    private String email;
    @JsonProperty("Needs")
    private List<Need> needs = new ArrayList<>();

    public InstitutionDto(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
