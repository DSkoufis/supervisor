package com.supervisor.util.response;

import com.supervisor.configuration.SpringApplicationContext;
import com.supervisor.dao.ProductDao;
import com.supervisor.domain.product.Product;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductCreationResponse extends Response {

    private final ProductDao productRepository;

    private ProductCreationResponse() {
        productRepository = SpringApplicationContext.getBean(ProductDao.class);
    }

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

    @Override
    void addModelAttributes(ModelAndView model) {
        Map<String, Long> loopInfo = new HashMap<>();
        loopInfo.put("count", productRepository.count());
        model.addObject("loopInfo", loopInfo);
        this.result.addResultModelAttribute("product", model);
    }
}
