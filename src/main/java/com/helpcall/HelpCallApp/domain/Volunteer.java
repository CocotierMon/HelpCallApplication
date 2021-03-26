package com.helpcall.HelpCallApp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "volunteers")
public class Volunteer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "latitude")
    private String lat;

    @Column(name = "longitude")
    private String lon;

    @Column(name = "description")
    private String description;

    @ManyToMany(cascade = CascadeType.PERSIST)
 //   @JoinColumn(name = "needs")
    private List<Need> needs;

    public Volunteer(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Volunteer(String name, String email, String password, String lat, String lon, String description, List<Need> needs) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.lat = lat;
        this.lon = lon;
        this.description = description;
        this.needs = needs;
    }

    @Override
    public String toString() {
        return "Volunteer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                ", description='" + description + '\'' +
               // ", needs=" + needs +
                '}';
    }
}
