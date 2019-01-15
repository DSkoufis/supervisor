package com.supervisor.command;

import com.supervisor.domain.product.Vendor;

import java.util.ArrayList;
import java.util.List;

public class ProductCreateCommand {

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Vendor> getVendors() {
        return vendors;
    }

    public void setVendors(List<Vendor> vendors) {
        this.vendors = vendors;
    }
}
