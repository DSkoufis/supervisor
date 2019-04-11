package com.supervisor.util.response;

import com.supervisor.configuration.SpringApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class Response<T extends Response, R> {

    private final MessageSource messageSource;

    private AbstractResultSet results;
    private Map<String, Object> extraModelAttributes;

    Response() {
        this.results = new AbstractResultSet();
        this.extraModelAttributes = new HashMap<>();
        this.messageSource = SpringApplicationContext.getBean(MessageSource.class);
    }

    abstract T returnThis();

    abstract HttpStatus getHttpStatus();

    abstract void addModelAttributes(ModelAndView model);

    public ModelAndView asModel(String viewName) {
        return this.asModel(viewName, null);
    }

    public ModelAndView asModel(String viewName, Map<String, ?> extraModelAttributes) {
        ModelAndView model = new ModelAndView(viewName);
        model.setStatus(this.getHttpStatus());

        this.addModelAttributes(model);

        addExtraAttributesToModel(model, extraModelAttributes);
        return model;
    }

    private void addExtraAttributesToModel(ModelAndView model, Map<String, ?> extraArgs) {
        if (extraArgs != null) {
            extraArgs.values().removeAll(Collections.singleton(null));
            model.addAllObjects(extraArgs);
        }

        model.addAllObjects(this.extraModelAttributes);
    }

    public T andModelAttribute(String attribute, Object object) {
        this.extraModelAttributes.put(attribute, object);
        return returnThis();
    }

    boolean isError() { return results.isError(); }

    void addToResults(AbstractResult result) {
        results.add(result);
    }

    AbstractResultSet getResults() { return results; }

    public abstract T withSuccess(R result);

    public T withSuccess() {
        this.addToResults(new SuccessResult());
        return returnThis();
    }

    public T withErrors(List<ObjectError> errors) {
        for (ObjectError error : errors) {
            String field = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            this.addToResults(getErrorResultFrom(field, message, "", null));
        }
        return returnThis();
    }

    public T withErrors(ConstraintViolationException exception) { return withErrors(exception.getConstraintViolations()); }

    public T withErrors(String field, String message, String code) {
        this.addToResults(getErrorResultFrom(field, message, code, null));
        return returnThis();
    }

    public T withErrors(BindingResult bind) { return withErrors(bind.getAllErrors()); }

    public T withErrors(Set<ConstraintViolation<?>> violations) {
        for (ConstraintViolation<?> violation : violations) {
            String field = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            String code = violation.getMessageTemplate();
            Object[] args = new Object[]{violation.getInvalidValue()};
            this.addToResults(getErrorResultFrom(field, message, code, args));
        }
        return returnThis();
    }

    private AbstractResult getErrorResultFrom(String field, String message, String code, Object[] args) {
        if (message == null) {
            message = getMessageFromCode(code, args);
        } else if (message.equals(code)) {
            message = getMessageFromCode(code, message, args);
        }
        return new ErrorResult(field, code, message);
    }

    private String getMessageFromCode(String code, Object[] args) { return getMessageFromCode(code, null, args); }

    private String getMessageFromCode(String code, String defaultMessage, Object[] args) {
        return messageSource.getMessage(code, args, defaultMessage, LocaleContextHolder.getLocale());
    }
}
