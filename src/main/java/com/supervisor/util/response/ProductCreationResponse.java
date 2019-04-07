package com.supervisor.util.response;

import com.supervisor.domain.product.Product;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;

import javax.validation.ConstraintViolationException;
import java.util.List;

public class ProductCreationResponse extends Response {

    public static ProductCreationResponse from(List<ObjectError> errors) {
        return from(ValidationError.from(errors));
    }

    public static ProductCreationResponse from(ConstraintViolationException exception) {
        return from(ValidationError.from(exception));
    }

    public static ProductCreationResponse from(String field, String message, String code) {
        return from(ValidationError.from(field, message, code));
    }

    public static ProductCreationResponse from(Product product) {
        return from(new ActionSuccess<>(product));
    }

    private static ProductCreationResponse from(ActionResultAware results) {
        ProductCreationResponse response = getInstance();
        response.result = results;
        return response;
    }

    private static ProductCreationResponse getInstance() {
        return new ProductCreationResponse();
    }

    @Override
    public HttpStatus getHttpStatus() {
        return result.isError() ? HttpStatus.BAD_REQUEST : HttpStatus.OK;
    }
}