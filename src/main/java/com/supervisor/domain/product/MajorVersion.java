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
@Table(name = "product_major_version")
public class MajorVersion implements VersionCommonInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private ProductVersionCommonInfo productInfo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "precedent")
    private MajorVersion precedent;

    @OneToMany(mappedBy = "precedent")
    private Set<MajorVersion> updates = new HashSet<>();

    @OneToMany(mappedBy = "majorVersion", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MinorVersion> minorVersions = new HashSet<>();

    public MajorVersion() {
    }

    public MajorVersion(String version) {
        this.productInfo = new ProductVersionCommonInfo(version);
    }

    public MajorVersion(String version, String description) {
        this.productInfo = new ProductVersionCommonInfo(version, description);
    }

    public MajorVersion(String version, String description, String notes) {
        this.productInfo = new ProductVersionCommonInfo(version, description, notes);
    }

    public MajorVersion(String version, MajorVersion precedent) {
        this.productInfo = new ProductVersionCommonInfo(version);
        this.precedent = precedent;
        this.product = precedent.getProduct();
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
        if (!product.getVersions().contains(this)) product.addVersion(this);
    }

    public MajorVersion getPrecedent() {
        return precedent;
    }

    public void setPrecedent(MajorVersion precedent) {
        this.precedent = precedent;
        if (!precedent.getUpdates().contains(this)) precedent.addUpdate(this);
    }

    public Set<MajorVersion> getUpdates() {
        return updates;
    }

    public void addUpdate(MajorVersion update) {
        this.updates.add(update);
        if (update.getPrecedent() != this) update.setPrecedent(this);
    }

    public Set<MinorVersion> getMinorVersions() {
        return minorVersions;
    }

    public void addMinorVersion(MinorVersion minorVersion) {
        this.minorVersions.add(minorVersion);
        if (minorVersion.getMajorVersion() != this) minorVersion.setMajorVersion(this);
    }
}
