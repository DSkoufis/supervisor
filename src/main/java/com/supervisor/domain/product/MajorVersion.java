package com.supervisor.domain.product;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "product_major_version")
public class MajorVersion extends Version<MajorVersion> {

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @OneToMany(mappedBy = "majorVersion", cascade = CascadeType.ALL, orphanRemoval = true, targetEntity = MinorVersion.class)
    private Set<MinorVersion> minorVersions = new HashSet<>();

    public MajorVersion() {
        super();
    }

    public MajorVersion(String version) {
        super(version);
    }

    public MajorVersion(String version, String description) {
        super(version, description);
    }

    public MajorVersion(String version, String description, String notes) {
        super(version, description, notes);
    }

    public MajorVersion(String version, MajorVersion precedent) {
        super(version, precedent);
        this.product = precedent.getProduct();
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
        if (!product.getVersions().contains(this)) product.addVersion(this);
    }

    public Set<MinorVersion> getMinorVersions() {
        return minorVersions;
    }

    public void addMinorVersion(MinorVersion minorVersion) {
        this.minorVersions.add(minorVersion);
        if (minorVersion.getMajorVersion() != this) minorVersion.setMajorVersion(this);
    }
}
