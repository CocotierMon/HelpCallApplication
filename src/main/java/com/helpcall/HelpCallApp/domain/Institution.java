package com.helpcall.HelpCallApp.domain;

import com.sun.istack.NotNull;
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
@Entity(name = "institutions")
public class Institution{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
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

    @Column(name = "isInstitution")
    private String isInstitution;

    @OneToMany(mappedBy = "institution", targetEntity = Need.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Need> needs;

    public Institution(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Institution(String name, String email, String password, String lat, String lon, String description,
                       String isInstitution, List<Need> needs) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.lat = lat;
        this.lon = lon;
        this.description = description;
        this.isInstitution = isInstitution;
        this.needs = needs;
    }

    public Institution(Long id, String name, String email, String lat, String lon) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.lat = lat;
        this.lon = lon;
    }

    @Override
    public String toString() {
        return "Institution{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                ", description='" + description + '\'' +
                ", isInstitution='" + isInstitution + '\'' +
                ", needs=" + needs +
                '}';
    }
}
