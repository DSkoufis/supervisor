package com.supervisor.util.response;

import java.util.List;

public interface ActionResultAware<T> {

    List<T> getResults();

    boolean isError();

    boolean isSuccess();
}
