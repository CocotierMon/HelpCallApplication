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
    private Institution institution;
    @JsonProperty("Volunteers")
    private List<Volunteer> volunteers = new ArrayList<>();
    @JsonProperty("NeedsBoards")
    private List<NeedsBoard> needsBoards = new ArrayList<>();

    public NeedDto(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }
}
