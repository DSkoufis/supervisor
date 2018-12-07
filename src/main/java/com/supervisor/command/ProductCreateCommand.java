package com.supervisor.command;

import com.supervisor.domain.product.Vendor;

import java.util.ArrayList;
import java.util.List;

public class ProductCreateCommand implements ProductCreateParams {

    private String name;
    private List<Vendor> vendors;

    public ProductCreateCommand(String name) {
        this.name = name;
        this.vendors = new ArrayList<Vendor>();
    }

    public ProductCreateCommand(String name, List<Vendor> vendors) {
        this(name);
        this.vendors = vendors;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public List<Vendor> getVendors() {
        return vendors;
    }

    @Override
    public void setVendors(List<Vendor> vendors) {
        this.vendors = vendors;
    }

    @Override
    public boolean validate() {
        if (name == null || name.trim().equals("")) {
            return false;
        }
        return true;
    }
}
