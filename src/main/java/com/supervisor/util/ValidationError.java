package com.supervisor.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.supervisor.configuration.SpringApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ValidationError {

    private final MessageSource messageSource;

    private static final ValidationError staticAccessor = new ValidationError();

    private List<Map<String, String>> errors = new ArrayList<>();

    private ValidationError() {
        this.messageSource = SpringApplicationContext.getBean(MessageSource.class);
    }

    public String build(HttpServletResponse httpResponse) {
        try {
            httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            String data = new ObjectMapper().writeValueAsString(errors);
            httpResponse.getWriter().print(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ValidationError from(List<ObjectError> errors) {
        List<Map<String, String>> errorsList = new ArrayList<>();
        for (ObjectError error : errors) {
            String field = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errorsList.add(getFields(field, message, ""));
        }

        return ValidationError.buildValidationErrorFromErrors(errorsList);
    }

    public static ValidationError from(BindingResult bind) {
        return from(bind.getAllErrors());
    }

    public static ValidationError from(Set<ConstraintViolation<?>> violations) {
        List<Map<String, String>> errors = new ArrayList<>();
        for (ConstraintViolation<?> violation : violations) {
            String field = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            String code = violation.getMessageTemplate();
            Object[] args = new Object[]{violation.getInvalidValue()};
            errors.add(getFields(field, message, code, args));
        }

        return ValidationError.buildValidationErrorFromErrors(errors);
    }

    public static ValidationError from(String field, String message, String code) {
        List<Map<String, String>> errors = new ArrayList<>();
        errors.add(getFields(field, message, code));

        return ValidationError.buildValidationErrorFromErrors(errors);
    }

    public static ValidationError from(ConstraintViolationException exception) {
        return from(exception.getConstraintViolations());
    }

    private static Map<String, String> getFields(String field, String message, String code) {
        return getFields(field, message, code, null);
    }

    private static Map<String, String> getFields(String field, String message, String code, Object[] args) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("field", field);

        if (message == null) {
            errorMap.put("message", staticAccessor.getMessageFromCode(code, args));
        } else if (!message.equals(code)) {
            errorMap.put("message", message);
        } else {
            errorMap.put("message", staticAccessor.getMessageFromCode(code, message, args));
        }
        errorMap.put("code", code);
        return errorMap;
    }

    private void addErrors(List<Map<String, String>> errors) {
        this.errors.addAll(errors);
    }

    private static ValidationError buildValidationErrorFromErrors(List<Map<String, String>> errors) {
        ValidationError response = new ValidationError();
        response.addErrors(errors);
        return response;
    }

    private String getMessageFromCode(String code, Object[] args) {
        return getMessageFromCode(code, null, args);
    }

    private String getMessageFromCode(String code, String defaultMessage, Object[] args) {
        return messageSource.getMessage(code, args, defaultMessage, LocaleContextHolder.getLocale());
    }
}
