package com.supervisor.util.response;

public class SuccessResult extends AbstractResult {

    SuccessResult() { super(null, null, null); }

    SuccessResult(String field, String code, String message) { super(field, code, message); }

    @Override
    public boolean isSuccess() { return true; }

    @Override
    public boolean isError() { return !isSuccess(); }
}
