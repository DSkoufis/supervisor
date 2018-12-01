package com.supervisor.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_product")
    private Product parentProduct;

    @OneToMany(mappedBy = "parentProduct")
    private Set<Product> updates = new HashSet<Product>(0);

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Product getParentProduct() {
        return parentProduct;
    }

    public void setParentProduct(Product parentProduct) {
        this.parentProduct = parentProduct;
    }

    public Set<Product> getUpdates() {
        return updates;
    }

    public void setUpdate(Product update) {
        update.parentProduct = this;
        if (!updates.contains(update)) {
            updates.add(update);
        }
    }
}
