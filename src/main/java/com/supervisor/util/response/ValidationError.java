package com.supervisor.util.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.supervisor.configuration.SpringApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

class ValidationError implements ActionResultAware<ValidationError.Result> {

    private static final ValidationError staticAccessor = new ValidationError();

    private final MessageSource messageSource;
    private List<Result> errors = new ArrayList<>();

    private ValidationError() {
        this.messageSource = SpringApplicationContext.getBean(MessageSource.class);
    }

    public static ValidationError from(List<ObjectError> errors) {
        List<Result> errorsList = new ArrayList<>();
        for (ObjectError error : errors) {
            String field = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errorsList.add(getFields(field, message, "", null));
        }

        return ValidationError.buildValidationErrorFromErrors(errorsList);
    }

    public static ValidationError from(BindingResult bind) {
        return from(bind.getAllErrors());
    }

    public static ValidationError from(Set<ConstraintViolation<?>> violations) {
        List<Result> errors = new ArrayList<>();
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
        List<Result> errors = new ArrayList<>();
        errors.add(getFields(field, message, code, null));

        return ValidationError.buildValidationErrorFromErrors(errors);
    }

    public static ValidationError from(ConstraintViolationException exception) {
        return from(exception.getConstraintViolations());
    }

    private static Result getFields(String field, String message, String code, Object[] args) {
        if (message == null) {
            message = staticAccessor.getMessageFromCode(code, args);
        } else if (message.equals(code)) {
            message = staticAccessor.getMessageFromCode(code, message, args);
        }
        return new Result(field, code, message);
    }

    private void addErrors(List<Result> errors) {
        this.errors.addAll(errors);
    }

    private static ValidationError buildValidationErrorFromErrors(List<Result> errors) {
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

    @Override
    public List<Result> getResults() { return this.errors; }

    @Override
    public void addModelAttributes(ModelAndView model) {
        model.addObject("validationErrors", errors);
    }

    @Override
    public boolean isError() { return !isSuccess(); }

    @Override
    public boolean isSuccess() { return this.errors.isEmpty(); }

    static class Result {
        private String field;
        private String code;
        private String message;

        private Result(String field, String code, String message) {
            this.field = field;
            this.code = code;
            this.message = message;
        }

        @JsonProperty("field")
        public String getField() { return this.field; }

        @JsonProperty("code")
        public String getCode() { return code; }

        @JsonProperty("message")
        public String getMessage() { return message; }
    }
}
