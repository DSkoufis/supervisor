package com.supervisor.utils.builder;

public class AbstractBuilder<T> {

    private T model;

    AbstractBuilder(Class<T> aClass) {
        try {
            model = aClass.newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            throw new RuntimeException(e);
        }
    }

    T getModel() {
        return model;
    }

    public T build() {
        return model;
    }
}
