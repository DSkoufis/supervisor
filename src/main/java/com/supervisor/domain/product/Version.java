package com.supervisor.domain.product;

import javax.persistence.Embedded;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@MappedSuperclass
public abstract class Version<T extends Version> implements VersionCommonInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private ProductVersionCommonInfo productInfo;

    @ManyToOne
    @JoinColumn(name = "precedent")
    private T precedent;

    @OneToMany(mappedBy = "precedent")
    private Set<T> updates = new HashSet<>();

    protected Version() {
    }

    protected Version(String version) {
        this.productInfo = new ProductVersionCommonInfo(version);
    }

    protected Version(String version, String description) {
        this.productInfo = new ProductVersionCommonInfo(version, description);
    }

    protected Version(String version, String description, String notes) {
        this.productInfo = new ProductVersionCommonInfo(version, description, notes);
    }

    protected Version(String version, T precedent) {
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

    public T getPrecedent() {
        return precedent;
    }

    public void setPrecedent(T precedent) {
        this.precedent = precedent;
        if (!precedent.getUpdates().contains(this)) precedent.addUpdate(this);
    }

    public Set<T> getUpdates() {
        return updates;
    }

    public void addUpdate(T update) {
        this.updates.add(update);
        if (update.getPrecedent() != this) update.setPrecedent(this);
    }
}
