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
public class VolunteerDto {

    @JsonProperty("Id")
    private Long id;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Email")
    private String email;
    @JsonProperty("Password")
    private String password;
    @JsonProperty("Latitude")
    private double lat;
    @JsonProperty("Longitude")
    private double lon;
    @JsonProperty("Description")
    private String description;
    @JsonProperty("Needs")
    private List<NeedDto> needs = new ArrayList<>();

    public VolunteerDto(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public VolunteerDto(Long id, String name, String email, double lat, double lon) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.lat = lat;
        this.lon = lon;
    }
}
