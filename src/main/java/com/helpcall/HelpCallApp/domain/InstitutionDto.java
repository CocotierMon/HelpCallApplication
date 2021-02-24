package com.helpcall.HelpCallApp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InstitutionDto {

    private Long id;
    private String name;
    private String email;
    private List<Need> needs = new ArrayList<>();

    public InstitutionDto(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
