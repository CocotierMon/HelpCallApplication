package com.helpcall.HelpCallApp.domain;

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
public class NeedDto {

    @JsonProperty("Id")
    public Long id;
    @JsonProperty("Title")
    public String title;
    @JsonProperty("Description")
    public String description;
    @JsonProperty("Latitude")
    public String lat;
    @JsonProperty("Longitude")
    public String lon;
    @JsonProperty("EndTime")
    public LocalDate endTime;
    @JsonProperty("Institution")
    public Institution institution;
    @JsonProperty("Volunteers")
    public List<Volunteer> volunteers = new ArrayList<>();
    @JsonProperty("NeedsBoards")
    public List<NeedsBoard> needsBoards = new ArrayList<>();

    public NeedDto(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }
}
