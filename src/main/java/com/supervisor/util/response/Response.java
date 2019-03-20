package com.supervisor.util.response;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public abstract class Response {
    ActionResultAware result;

    abstract int getHttpStatusCode();

    private void setHttpStatus(HttpServletResponse servletResponse) {
        servletResponse.setStatus(this.getHttpStatusCode());
    }

    public Response build(HttpServletResponse response) {
        this.setHttpStatus(response);
        return this;
    }

    @JsonValue
    public String toJson() throws JsonProcessingException {
        Map results = getCommonJsonElements();
        results.put("results", result.getResults());
        return new ObjectMapper().writeValueAsString(results);
    }

    private Map getCommonJsonElements() {
        Map commonElements = new HashMap<>();
        commonElements.put("status", getHttpStatusCode());
        return commonElements;
    }
}
