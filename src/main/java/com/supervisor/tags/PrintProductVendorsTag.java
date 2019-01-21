package com.supervisor.tags;

import com.supervisor.domain.product.Vendor;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.Collection;

public class PrintProductVendorsTag extends SimpleTagSupport {

    private static final String VENDOR_URL = "/vendors/";

    private Collection<Vendor> vendors;
    private boolean asList = false;
    private boolean inLine = true;
    private VendorField field = VendorField.ID;


    public void setVendors(final Collection<Vendor> vendors) {
        this.vendors = vendors;
    }

    public void setAsList(final boolean asList) {
        this.asList = asList;
    }

    public void setInLine(final boolean inLine) {
        this.inLine = inLine;
    }

    public void setField(final String fieldName) {
        this.field = VendorField.findByValue(fieldName);
    }

    @Override
    public void doTag() throws JspException, IOException {
        // TODO DoTag
        super.doTag();
    }

    private enum VendorField {
        ID("id"),
        NAME("name");

        private String fieldName;

        VendorField(String fieldName) {
            this.fieldName = fieldName;
        }

        public static VendorField findByValue(String value) {
            if (value == null || value.trim().equals("")) {
                return VendorField.ID;
            }

            for (VendorField field : values()) {
                if (field.fieldName.equals(value.toLowerCase().trim())) {
                    return field;
                }
            }
            return VendorField.ID;
        }
    }
}
