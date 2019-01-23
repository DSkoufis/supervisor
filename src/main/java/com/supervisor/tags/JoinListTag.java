package com.supervisor.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

public class JoinListTag extends SimpleTagSupport {

    private Iterator iterator;
    private String delimiter;
    private String var;

    public void setList(Collection list) {
        if (list.size() > 0) {
            this.iterator = list.iterator();
        }
    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    public void setVar(String var) {
        this.var = var;
    }

    @Override
    public void doTag() throws JspException, IOException {
        if (iterator == null) {
            return;
        }

        while (iterator.hasNext()) {
            getJspContext().setAttribute(var, iterator.next());
            getJspBody().invoke(null);

            if (iterator.hasNext()) {
                getJspContext().getOut().print(delimiter);
            }
        }
    }
}
