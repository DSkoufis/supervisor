package com.supervisor.util.response;

public class ErrorResult extends AbstractResult {

    ErrorResult(String field, String code, String message) { super(field, code, message); }

    @Override
    public boolean isSuccess() { return !isError(); }

    @Override
    public boolean isError() { return true; }
}
