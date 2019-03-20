package com.supervisor.util.response;

import java.util.ArrayList;
import java.util.List;

public class ActionSuccess<T> implements ActionResultAware<T> {

    private T object;

    ActionSuccess(T object) {
        this.object = object;
    }

    @Override
    public List<T> getResults() {
        List<T> resultsList = new ArrayList<>();
        resultsList.add(object);
        return resultsList;
    }

    @Override
    public boolean isError() {
        return !this.isSuccess();
    }

    @Override
    public boolean isSuccess() {
        return this.object != null;
    }
}
