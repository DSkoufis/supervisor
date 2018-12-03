package com.supervisor.domain.product;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "product_minor_version")
public class MinorVersion implements VersionCommonInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private ProductVersionCommonInfo productInfo;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "major_version_id", nullable = false)
    private MajorVersion majorVersion;

    @ManyToOne
    @JoinColumn(name = "precedent")
    private MinorVersion precedent;

    @OneToMany(mappedBy = "precedent")
    private Set<MinorVersion> updates = new HashSet<>();

    public MinorVersion() {
    }

    public MinorVersion(String version) {
        this.productInfo = new ProductVersionCommonInfo(version);
    }

    public MinorVersion(String version, String description) {
        this.productInfo = new ProductVersionCommonInfo(version, description);
    }

    public MinorVersion(String version, String description, String notes) {
        this.productInfo = new ProductVersionCommonInfo(version, description, notes);
    }

    public MinorVersion(String version, MinorVersion precedent) {
        this.productInfo = new ProductVersionCommonInfo(version);
        this.precedent = precedent;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getVersion() {
        return productInfo.getVersion();
    }

    @Override
    public void setVersion(String version) {
        productInfo.setVersion(version);
    }

    @Override
    public String getDescription() {
        return productInfo.getDescription();
    }

    @Override
    public void setDescription(String description) {
        productInfo.setDescription(description);
    }

    @Override
    public LocalDate getReleaseDate() {
        return productInfo.getReleaseDate();
    }

    @Override
    public void setReleaseDate(LocalDate releaseDate) {
        productInfo.setReleaseDate(releaseDate);
    }

    @Override
    public String getNotes() {
        return productInfo.getNotes();
    }

    @Override
    public void setNotes(String notes) {
        productInfo.setNotes(notes);
    }

    public MajorVersion getMajorVersion() {
        return majorVersion;
    }

    public void setMajorVersion(MajorVersion majorVersion) {
        this.majorVersion = majorVersion;
        if (!majorVersion.getMinorVersions().contains(this)) majorVersion.addMinorVersion(this);
    }

    public MinorVersion getPrecedent() {
        return precedent;
    }

    public void setPrecedent(MinorVersion precedent) {
        this.precedent = precedent;
        if (!precedent.getUpdates().contains(this)) precedent.addUpdate(this);
    }

    public Set<MinorVersion> getUpdates() {
        return updates;
    }

    public void addUpdate(MinorVersion update) {
        this.updates.add(update);
        if (update.getPrecedent() != this) update.setPrecedent(this);
    }
}
