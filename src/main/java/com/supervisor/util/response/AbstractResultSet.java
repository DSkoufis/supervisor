package com.supervisor.util.response;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashSet;

public class AbstractResultSet extends HashSet<AbstractResult> {

    @Override
    public boolean add(@NotNull AbstractResult abstractResult) {
        if (abstractResult.isError() && this.isAllError()) {
            return super.add(abstractResult);
        } else if (abstractResult.isSuccess() && this.isAllSuccess()) {
            return super.add(abstractResult);
        } else {
            throw new IllegalArgumentException("Can't mix results in the set");
        }
    }

    @Override
    public boolean addAll(@NotNull Collection<? extends AbstractResult> c) {
        boolean isAllError = c.stream().allMatch(AbstractResult::isError);
        boolean isAllSuccess = c.stream().allMatch(AbstractResult::isSuccess);

        if (isAllError && this.isAllError()) {
            return super.addAll(c);
        } else if (isAllSuccess && this.isAllSuccess()) {
            return super.addAll(c);
        } else {
            throw new IllegalArgumentException("Can't mix results in the set");
        }
    }

    boolean isError() {
        if (super.isEmpty()) {
            throw new RuntimeException("Can't check empty Set");
        } else {
            return isAllError();
        }
    }

    private boolean isAllError() {
        return super.stream().allMatch(AbstractResult::isError);
    }

    private boolean isAllSuccess() {
        return super.stream().allMatch(AbstractResult::isSuccess);
    }
}
