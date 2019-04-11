package com.supervisor.util.response;

import com.supervisor.configuration.SpringApplicationContext;
import com.supervisor.dao.ProductDao;
import com.supervisor.domain.product.Product;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

public class ProductResponse extends Response<ProductResponse, Product> {

    private final ProductDao productRepository;

    private Product product;
    private HttpStatus status;

    public ProductResponse() {
        super();
        productRepository = SpringApplicationContext.getBean(ProductDao.class);
    }

    public ProductResponse withStatus(HttpStatus status) {
        this.status = status;
        return this;
    }

    @Override
    public ProductResponse withSuccess(Product product) {
        this.product = product;
        this.addToResults(new SuccessResult());
        return this;
    }

    @Override
    ProductResponse returnThis() {
        return this;
    }

    @Override
    HttpStatus getHttpStatus() {
        if (status != null) {
            return status;
        }
        return this.isError() ? HttpStatus.BAD_REQUEST : HttpStatus.OK;
    }

    @Override
    void addModelAttributes(ModelAndView model) {
        if (isError()) {
            model.addObject("errors", getResults());
        } else {
            Map<String, Long> loopInfo = new HashMap<>();
            loopInfo.put("count", productRepository.count());
            model.addObject("loopInfo", loopInfo);
            model.addObject("product", this.product);
        }
    }
}
