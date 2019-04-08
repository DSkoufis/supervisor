package com.supervisor.util.response;

import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public interface ActionResultAware<T> {

    List<T> getResults();

    void addResultModelAttribute(String attributeName, ModelAndView model);

    boolean isError();

    boolean isSuccess();
}
