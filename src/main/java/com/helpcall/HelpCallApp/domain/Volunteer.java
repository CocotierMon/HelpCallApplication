package com.helpcall.HelpCallApp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "volunteers")
public class Volunteer{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("vol_id")
    private Long id;

    @JsonProperty("vol_name")
    @Column(name = "name")
    private String name;

    @JsonProperty("vol_email")
    @Column(name = "email")
    private String email;

    @ManyToMany
    @JoinColumn(name = "needs")
    List<Need> needs = new ArrayList<>();

    public Volunteer(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
