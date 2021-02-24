package com.helpcall.HelpCallApp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NeedDto {
    private Long id;
    private String title;
    private String description;
    private String location;
    private LocalDate endTime;
    private Institution institution;
    private List<Volunteer> volunteers;
    private NeedsBoard needsBoard;

    public NeedDto(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }
}
