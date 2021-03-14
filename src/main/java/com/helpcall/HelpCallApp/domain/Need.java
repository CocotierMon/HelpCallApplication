package com.helpcall.HelpCallApp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
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
    private String lat;

    @Column(name = "longitude")
    private String lon;

    @Column(name = "end_time")
    private LocalDate endTime;

    @ManyToOne
    @JoinColumn(name = "institution_id")
    private Institution institution;

    @ManyToMany
    @JoinColumn(name = "volunteers")
    private List<Volunteer> volunteers = new ArrayList<>();

    @ManyToMany
    @JoinColumn(name = "needsboards")
    private List<NeedsBoard> needsBoards = new ArrayList<>();

    public Need(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
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
                ", needsBoards=" + needsBoards +
                '}';
    }
}
