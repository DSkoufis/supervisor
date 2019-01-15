package com.supervisor.domain.product;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private Long id;

    @NotNull
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(name = "product_vendor",
            joinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "vendor_id", referencedColumnName = "id")})
    private Set<Vendor> vendors = new HashSet<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MajorVersion> majorVersions = new HashSet<>();

    public Product() {
    }

    public Product(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Vendor> getVendors() {
        return vendors;
    }

    public void addVendor(Vendor vendor) {
        vendors.add(vendor);
        if (!vendor.getProducts().contains(this)) {
            vendor.addProduct(this);
        }
    }

    public void addVendors(List<Vendor> vendors) {
        this.vendors.addAll(vendors);
        for (Vendor vendor : this.vendors) {
            vendor.addProduct(this);
        }
    }

    public Set<MajorVersion> getVersions() {
        return majorVersions;
    }

    public void addVersion(MajorVersion majorVersions) {
        this.majorVersions.add(majorVersions);
        if (majorVersions.getProduct() != this) majorVersions.setProduct(this);
    }
}
