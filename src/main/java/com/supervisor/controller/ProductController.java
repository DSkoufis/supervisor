package com.supervisor.controller;

import com.supervisor.bean.DataIntegrityViolationExceptionTranslator;
import com.supervisor.command.ProductSaveCommand;
import com.supervisor.domain.product.Product;
import com.supervisor.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

import static com.supervisor.util.constant.ControllerMapping.PRODUCT_CONTROLLER_ROOT;
import static com.supervisor.util.constant.ViewMapping.PRODUCT_VIEW_PATH;

@Controller
@RequestMapping(value = PRODUCT_CONTROLLER_ROOT)
public class ProductController {

    private final ProductService productService;
    private final DataIntegrityViolationExceptionTranslator exceptionTranslator;

    @Autowired
    public ProductController(ProductService productService, DataIntegrityViolationExceptionTranslator exceptionTranslator) {
        this.productService = productService;
        this.exceptionTranslator = exceptionTranslator;
    }

    @GetMapping(value = {"", "/"})
    public ModelAndView index() {
        ModelAndView model = new ModelAndView(PRODUCT_VIEW_PATH + "productList");
        List<Product> products = productService.getAllProducts();
        model.addObject("products", products);
        return model;
    }

    @PostMapping(value = "/save")
    public Product createProduct(@Valid @ModelAttribute("command") ProductSaveCommand cmd, BindingResult result) {
        if (result.hasErrors()) {
            System.out.println("Test");
        }

        try {
            return productService.saveProduct(cmd);
        } catch (javax.validation.ConstraintViolationException ex) {
            ex.getConstraintViolations().forEach(constraintViolation -> System.out.println(constraintViolation.getMessage()));
        } catch (org.springframework.dao.DataIntegrityViolationException ex) {
            exceptionTranslator.getCode(ex);
        }
        return null;
    }
}
