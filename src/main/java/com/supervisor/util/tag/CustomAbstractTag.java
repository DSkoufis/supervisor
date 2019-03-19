package com.supervisor.util.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public abstract class CustomAbstractTag extends SimpleTagSupport {
    @Override
    public abstract void doTag() throws JspException, IOException;

    void printOut(String out) throws IOException {
        getJspContext().getOut().print(out);
    }
}
