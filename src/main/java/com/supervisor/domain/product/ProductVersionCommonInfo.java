package com.supervisor.domain.product;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
class ProductVersionCommonInfo implements VersionCommonInfo {

    @NotBlank
    @Column(name = "version", nullable = false)
    private String version;

    @Size(max = 255)
    @Column(name = "description")
    private String description;

    @Basic
    private LocalDate releaseDate;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

    ProductVersionCommonInfo() {}

    @Override public String getVersion() {
        return version;
    }

    @Override public void setVersion(String version) {
        this.version = version;
    }

    @Override public String getDescription() {
        return description;
    }

    @Override public void setDescription(String description) {
        this.description = description;
    }

    @Override public LocalDate getReleaseDate() {
        return releaseDate;
    }

    @Override public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override public String getNotes() {
        return notes;
    }

    @Override public void setNotes(String notes) {
        this.notes = notes;
    }
}
