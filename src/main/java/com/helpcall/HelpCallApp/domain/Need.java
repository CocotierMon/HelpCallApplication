package com.helpcall.HelpCallApp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "needs")
public class Need {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "latitude")
    private double lat;

    @Column(name = "longitude")
    private double lon;

    @Column(name = "end_time")
    private LocalDate endTime;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "institution_id")
    private Institution institution;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Volunteer> volunteers;

    public Need(String title, String description, double lat, double lon, LocalDate endTime, Institution institution,
                List<Volunteer> volunteers) {
        this.title = title;
        this.description = description;
        this.lat = lat;
        this.lon = lon;
        this.endTime = endTime;
        this.institution = institution;
        this.volunteers = volunteers;
    }

    public Need(Long id, String title, String description, double lat, double lon, LocalDate endTime, List<Volunteer> volunteers) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.lat = lat;
        this.lon = lon;
        this.endTime = endTime;
        this.volunteers = volunteers;
    }

    public Need(Long id, String title, String description, double lat, double lon, LocalDate endTime, Institution institution) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.lat = lat;
        this.lon = lon;
        this.endTime = endTime;
        this.institution = institution;
    }

    public Need(Long id, String title, String description, double lat, double lon, LocalDate endTime) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.lat = lat;
        this.lon = lon;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Need{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                ", endTime=" + endTime +
               // ", institution=" + institution +
                ", volunteers=" + volunteers +
                '}';
    }
}
