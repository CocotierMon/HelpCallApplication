package com.helpcall.HelpCallApp.domain;

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
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    public String password;

    @Column(name = "latitude")
    public String lat;

    @Column(name = "longitude")
    public String lon;

    @Column(name = "description")
    public String description;

    @ManyToMany
    @JoinColumn(name = "needs")
    List<Need> needs = new ArrayList<>();

    public Volunteer(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
