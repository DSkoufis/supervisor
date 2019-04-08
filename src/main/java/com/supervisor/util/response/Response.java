package com.supervisor.util.response;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public abstract class Response {
    ActionResultAware result;

    abstract HttpStatus getHttpStatus();
    abstract void addModelAttributes(ModelAndView model);

    private void setHttpStatus(HttpServletResponse servletResponse) {
        servletResponse.setStatus(this.getHttpStatus().value());
    }

    public Response build(HttpServletResponse response) {
        this.setHttpStatus(response);
        return this;
    }

    public ModelAndView asModel(String viewName) {
        ModelAndView model = new ModelAndView(viewName);
        model.setStatus(this.getHttpStatus());

        this.addModelAttributes(model);
        return model;
    }

    @JsonValue
    public ObjectNode toJson() {
        Map results = getCommonJsonElements();
        results.put("results", result.getResults());
        return new ObjectMapper().valueToTree(results);
    }

    private Map getCommonJsonElements() {
        Map commonElements = new HashMap<>();
        HttpStatus status = getHttpStatus();
        commonElements.put("status", status.value());
        commonElements.put("status_description", status.name());
        return commonElements;
    }
}
