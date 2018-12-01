package com.supervisor.domain.project;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "supervisor")
public class Project {

    @Id
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;
// TODO a supervisor has Phases - look @OrderedColumn annotation

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
