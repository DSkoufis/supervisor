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
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "product_minor_version")
public class MinorVersion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private VersionInformation productInfo;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "major_version_id", nullable = false)
    private MajorVersion majorVersion;

    @ManyToOne
    @JoinColumn(name = "precedent")
    private MinorVersion precedent;

    @OneToMany(mappedBy = "precedent")
    private Set<MinorVersion> updates = new HashSet<>();
}
