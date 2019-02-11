package com.supervisor.bean;

import com.supervisor.util.constant.UniqueKeyConstraintMapper;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataIntegrityViolationExceptionTranslator {

    private static final Pattern uniqueKeyPattern = Pattern.compile("^Duplicate entry.*for key '(.*)'$");

    private static final String UNKNOWN_ERROR = "sql.generic.error";

    public String getCode(DataIntegrityViolationException exception) {
        String message = getMessageFromException(exception);

        Matcher matcher = uniqueKeyPattern.matcher(message);
        if (matcher.find()) {
            String uniqueKey = matcher.group(1);
            return UniqueKeyConstraintMapper.getCode(uniqueKey);
        } else {
            return UNKNOWN_ERROR;
        }
    }

    private String getMessageFromException(DataIntegrityViolationException exception) {
        return Objects.requireNonNull(exception.getRootCause()).getMessage();
    }
}
