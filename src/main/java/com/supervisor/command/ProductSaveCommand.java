package com.supervisor.command;

import javax.validation.constraints.NotBlank;

public class ProductSaveCommand {

    @NotBlank(message = "{product.Product.name.notBlank}")
    private String name;

    public ProductSaveCommand() {
    }

    public ProductSaveCommand(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null) {
            name = name.trim();
        }
        this.name = name;
    }
}
