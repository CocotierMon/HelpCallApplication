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
    public Long id;
    @JsonProperty("Name")
    public String name;
    @JsonProperty("Email")
    public String email;
    @JsonProperty("Password")
    public String password;
    @JsonProperty("Latitude")
    public String lat;
    @JsonProperty("Longitude")
    public String lon;
    @JsonProperty("Description")
    public String description;
    @JsonProperty("IsInstitution")
    public String isInstitution;
    @JsonProperty("Needs")
    public List<Need> needs = new ArrayList<>();

    public InstitutionDto(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
