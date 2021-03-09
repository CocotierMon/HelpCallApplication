package com.helpcall.HelpCallApp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NeedDto {

    @JsonProperty("Id")
    private Long id;
    @JsonProperty("Title")
    private String title;
    @JsonProperty("Description")
    private String description;
    @JsonProperty("Location")
    private String location;
    @JsonProperty("EndTime")
    private LocalDate endTime;
    @JsonProperty("Institution")
    private Institution institution;
    @JsonProperty("Volunteers")
    private List<Volunteer> volunteers;
    @JsonProperty("NeedsBoards")
    private List<NeedsBoard> needsBoards;

    public NeedDto(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }
}
