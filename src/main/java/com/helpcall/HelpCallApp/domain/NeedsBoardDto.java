package com.helpcall.HelpCallApp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NeedsBoardDto {
    private Long id;
    private List<Need> needs = new ArrayList<>();

    public NeedsBoardDto(Long id) {
        this.id = id;
    }
}
