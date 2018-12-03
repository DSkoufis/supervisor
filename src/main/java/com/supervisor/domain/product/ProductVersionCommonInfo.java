package com.supervisor.domain.product;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
class ProductVersionCommonInfo implements VersionCommonInfo{

    @Column(name = "version", nullable = false)
    private String version;

    @Column(name = "description")
    private String description;

    @Basic
    private LocalDate releaseDate;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

    public ProductVersionCommonInfo() {
    }

    public ProductVersionCommonInfo(String version) {
        this.version = version;
    }

    public ProductVersionCommonInfo(String version, String description) {
        this(version);
        this.description = description;
    }

    public ProductVersionCommonInfo(String version, String description, String notes) {
        this(version, description);
        this.notes = notes;
    }

    public ProductVersionCommonInfo(String version, String description, LocalDate releaseDate, String notes) {
        this(version, description, notes);
        this.releaseDate = releaseDate;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
