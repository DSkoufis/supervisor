package com.supervisor.domain.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "vendor")
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "website")
    private String website;

    @ManyToMany(mappedBy = "vendors")
    private Set<Product> products = new HashSet<>();

    public Vendor() {
    }

    public Vendor(String name) {
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

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        products.add(product);
        if (!product.getVendors().contains(this)) {
            product.addVendor(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vendor)) return false;
        Vendor vendor = (Vendor) o;
        return id.equals(vendor.id) &&
                name.equals(vendor.name) &&
                Objects.equals(website, vendor.website) &&
                Objects.equals(products, vendor.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, website, products);
    }
}
