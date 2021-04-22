package com.helpcall.HelpCallApp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@NamedNativeQuery(
        name = "VolunteersWall.getVolunteersNeeds",
        query = "select needs.id, needs.title, needs.end_time, institutions.name from needs_volunteers, needs, institutions" +
                " where needs_volunteers.volunteers_id = :id and needs.id = needs_volunteers.needs_id" +
                " and institutions.id = needs.institution_id;",
        resultClass = VolunteersWall.class)

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "wall")
public class VolunteersWall {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "end_time")
    private LocalDate endTime;
    @Column(name = "name")
    private String institutionName;

    public VolunteersWall(String title, LocalDate endTime, String institutionName) {
        this.title = title;
        this.endTime = endTime;
        this.institutionName = institutionName;
    }

    @Override
    public String toString() {
        return "VolunteersWall{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", endTime=" + endTime +
                ", institutionName='" + institutionName + '\'' +
                '}';
    }
}
