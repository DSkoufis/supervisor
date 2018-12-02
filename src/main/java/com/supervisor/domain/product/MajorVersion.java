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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "product_major_version")
public class MajorVersion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private VersionInformation productInfo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "precedent")
    private MajorVersion precedent;

    @OneToMany(mappedBy = "precedent")
    private Set<MajorVersion> updates = new HashSet<>();

    @OneToMany(mappedBy = "majorVersion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MinorVersion> minorVersions = new ArrayList<>();
}
