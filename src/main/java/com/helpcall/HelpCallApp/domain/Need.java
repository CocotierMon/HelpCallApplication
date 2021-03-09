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

    @Column(name = "location")
    private String location;

    @Column(name = "end_time")
    private LocalDate endTime;

    @ManyToOne
    @JoinColumn(name = "institution_id")
    public Institution institution;

    @ManyToMany
    @JoinColumn(name = "volunteers")
    public List<Volunteer> volunteers;

    @ManyToMany
    @JoinColumn(name = "needsboards")
    public List<NeedsBoard> needsBoards;

    public Need(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }
}
