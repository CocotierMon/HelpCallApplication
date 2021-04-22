package com.helpcall.HelpCallApp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class NeedDto {

    @JsonProperty("Id")
    private Long id;
    @JsonProperty("Title")
    private String title;
    @JsonProperty("Description")
    private String description;
    @JsonProperty("Latitude")
    private String lat;
    @JsonProperty("Longitude")
    private String lon;
    @JsonProperty("EndTime")
    private LocalDate endTime;
    @JsonProperty("Institution")
    private InstitutionDto institution;
    @JsonProperty("Volunteers")
    private List<VolunteerDto> volunteers = new ArrayList<>();

    public NeedDto(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public NeedDto(Long id, String title, String description, String lat, String lon, LocalDate endTime,
                   InstitutionDto institution) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.lat = lat;
        this.lon = lon;
        this.endTime = endTime;
        this.institution = institution;
    }

    public NeedDto(Long id, String title, String description, String lat, String lon, LocalDate endTime) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.lat = lat;
        this.lon = lon;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "NeedDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                ", endTime=" + endTime +
              //  ", institution=" + institution +
                ", volunteers=" + volunteers +
                '}';
    }
}
