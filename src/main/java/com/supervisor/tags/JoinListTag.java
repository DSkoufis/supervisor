package com.supervisor.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Collection;
import java.util.Iterator;

public class JoinListTag extends SimpleTagSupport {

    private StringWriter sw = new StringWriter();

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

            String body = getTrimmedBody();
            printOut(body);

            if (iterator.hasNext()) {
                printOut(delimiter);
            }

            prepareForNextIteration();
        }
    }

    private String getTrimmedBody() throws IOException, JspException {
        getJspBody().invoke(sw);
        return sw.toString().replaceAll("[\r\n\t]*", "").trim();
    }

    private void printOut(String out) throws IOException {
        getJspContext().getOut().print(out);
    }

    private void prepareForNextIteration() {
        sw.getBuffer().setLength(0);
    }
}
