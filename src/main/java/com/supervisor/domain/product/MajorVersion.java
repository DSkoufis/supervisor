package com.supervisor.domain.product;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "product_major_version")
public class MajorVersion extends Version<MajorVersion> {

    @NotNull
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
        if (!product.getVersions().contains(this)) product.addMajorVersion(this);
    }

    public Set<MinorVersion> getMinorVersions() {
        return minorVersions;
    }

    public void addMinorVersion(MinorVersion minorVersion) {
        this.minorVersions.add(minorVersion);
        if (minorVersion.getMajorVersion() != this) minorVersion.setMajorVersion(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MajorVersion)) return false;
        MajorVersion version = (MajorVersion) o;
        return super.equals(version) &&
                product.equals(version.product) &&
                minorVersions.equals(version.minorVersions);
    }

    @Override
    public int hashCode() {
        return super.hashCode() + Objects.hash(product, minorVersions);
    }
}
