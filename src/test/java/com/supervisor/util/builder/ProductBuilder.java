package com.supervisor.util.builder;

import com.supervisor.domain.product.MajorVersion;
import com.supervisor.domain.product.Product;
import com.supervisor.domain.product.Vendor;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Collection;

public class ProductBuilder extends AbstractBuilder<Product> {

    public ProductBuilder() {
        super(Product.class);
    }

    public ProductBuilder withId(Long id) {
        ReflectionTestUtils.setField(getModel(), "id", id);
        return this;
    }

    public ProductBuilder withName(String name) {
        getModel().setName(name);
        return this;
    }

    public ProductBuilder withVendors(Collection<Vendor> vendors) {
        getModel().addVendors(vendors);
        return this;
    }

    public ProductBuilder addVendor(Vendor vendor) {
        getModel().addVendor(vendor);
        return this;
    }

    public ProductBuilder withMajorVersions(Collection<MajorVersion> majorVersions) {
        getModel().addMajorVersions(majorVersions);
        return this;
    }

    public ProductBuilder addMajorVersion(MajorVersion majorVersion) {
        getModel().addMajorVersion(majorVersion);
        return this;
    }
}
