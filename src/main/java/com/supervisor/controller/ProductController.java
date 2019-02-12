package com.supervisor.controller;

import com.supervisor.command.ProductSaveCommand;
import com.supervisor.domain.product.Product;
import com.supervisor.service.ProductService;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.supervisor.util.constant.ControllerMapping.PRODUCT_CONTROLLER_ROOT;
import static com.supervisor.util.constant.ViewMapping.PRODUCT_VIEW_PATH;

@Controller
@RequestMapping(value = PRODUCT_CONTROLLER_ROOT)
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = {"", "/"})
    public ModelAndView index() {
        ModelAndView model = new ModelAndView(PRODUCT_VIEW_PATH + "productList");
        List<Product> products = productService.getAllProducts();
        model.addObject("products", products);
        return model;
    }

    @PostMapping(value = "/save")
    public ResponseEntity createProduct(@Valid @ModelAttribute("command") ProductSaveCommand cmd, BindingResult result) {

        // Return the errors
        if (result.hasErrors()) {
            List<Map<String, String>> errors = new ArrayList<>();
            for (ObjectError error : result.getAllErrors()) {
                Map<String, String> errorMap = new HashMap<>();
                errorMap.put("field", ((FieldError) error).getField());
                errorMap.put("message", error.getDefaultMessage());
                errorMap.put("code", "");
                errors.add(errorMap);
            }
            //return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        try {
            productService.saveProduct(cmd);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (javax.validation.ConstraintViolationException ex) {
            ex.getConstraintViolations().forEach(constraintViolation ->
                    System.out.println(((PathImpl)constraintViolation.getPropertyPath()).getLeafNode().asString()));
        } catch (org.springframework.dao.DataIntegrityViolationException ex) {
        }
        return null;
    }
}
