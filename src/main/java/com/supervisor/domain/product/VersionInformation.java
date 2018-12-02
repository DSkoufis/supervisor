package com.supervisor.domain.product;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
class VersionInformation {

    @Column(name = "version", nullable = false)
    private String version;

    @Column(name = "description")
    private String description;

    @Basic
    private LocalDate releaseDate;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;
}
