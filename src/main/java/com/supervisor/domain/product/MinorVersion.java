package com.supervisor.domain.product;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "product_minor_version")
public class MinorVersion extends Version<MinorVersion> {

    @ManyToOne(optional = false, cascade = CascadeType.ALL, targetEntity = MajorVersion.class)
    @JoinColumn(name = "major_version_id", nullable = false, referencedColumnName = "id")
    private MajorVersion majorVersion;

    public MinorVersion() {
        super();
    }

    public MinorVersion(String version) {
        super(version);
    }

    public MinorVersion(String version, String description) {
        super(version, description);
    }

    public MinorVersion(String version, String description, String notes) {
        super(version, description, notes);
    }

    public MinorVersion(String version, MinorVersion precedent) {
        super(version, precedent);
    }

    public MinorVersion(String version, MajorVersion majorVersion) {
        super(version);
        this.majorVersion = majorVersion;
    }

    public MajorVersion getMajorVersion() {
        return majorVersion;
    }

    public void setMajorVersion(MajorVersion majorVersion) {
        this.majorVersion = majorVersion;
        if (!majorVersion.getMinorVersions().contains(this)) majorVersion.addMinorVersion(this);
    }
}
