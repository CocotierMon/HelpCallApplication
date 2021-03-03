package com.helpcall.HelpCallApp.domain;

import com.sun.istack.NotNull;
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

    @OneToMany(targetEntity = Need.class, mappedBy = "institution", fetch = FetchType.LAZY)
    private List<Need> needs = new ArrayList<>();

    public Institution(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
