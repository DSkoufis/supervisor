package com.supervisor.command;

import com.supervisor.domain.product.Product;
import com.supervisor.domain.product.Vendor;

import java.util.List;

public interface ProductCreateParams extends CoreParams<Product> {
    String getName();

    void setName(String name);

    List<Vendor> getVendors();

    void setVendors(List<Vendor> vendors);
}
