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
public class InstitutionDto {

    @JsonProperty("Id")
    private Long id;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Email")
    private String email;
    @JsonProperty("Password")
    private String password;
    @JsonProperty("Latitude")
    private String lat;
    @JsonProperty("Longitude")
    private String lon;
    @JsonProperty("Description")
    private String description;
    @JsonProperty("IsInstitution")
    private String isInstitution;
    @JsonProperty("Needs")
    private List<NeedDto> needs = new ArrayList<>();

    public InstitutionDto(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public InstitutionDto(Long id, String name, String email, String password, String lat, String lon,
                          String description, String isInstitution) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.lat = lat;
        this.lon = lon;
        this.description = description;
        this.isInstitution = isInstitution;
    }

    @Override
    public String toString() {
        return "InstitutionDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                ", description='" + description + '\'' +
                ", isInstitution='" + isInstitution + '\'' +
                ", needs=" + needs +
                '}';
    }
}
