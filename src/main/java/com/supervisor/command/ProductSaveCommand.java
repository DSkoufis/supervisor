package com.supervisor.command;

import com.supervisor.domain.product.Vendor;

import java.util.ArrayList;
import java.util.List;

public class ProductSaveCommand {

    private String name;
    private List<Vendor> vendors;

    public ProductSaveCommand() {
        this.vendors = new ArrayList<Vendor>();
    }

    public ProductSaveCommand(String name) {
        this();
        this.name = name;
    }

    public ProductSaveCommand(String name, List<Vendor> vendors) {
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
