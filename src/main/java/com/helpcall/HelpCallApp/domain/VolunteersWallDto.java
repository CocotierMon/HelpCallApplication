package com.helpcall.HelpCallApp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@JsonPropertyOrder({"Title", "InstitutionName", "EndTime"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class VolunteersWallDto {

    @JsonProperty("Title")
    private String title;
    @JsonProperty("EndTime")
    private LocalDate endTime;
    @JsonProperty("InstitutionName")
    private String institutionName;

    public VolunteersWallDto(String title, LocalDate endTime, String institutionName) {
        this.title = title;
        this.endTime = endTime;
        this.institutionName = institutionName;
    }

    @Override
    public String toString() {
        return "VolunteersWallDto{" +
                "title='" + title + '\'' +
                ", endTime=" + endTime +
                ", institutionName='" + institutionName + '\'' +
                '}';
    }
}
