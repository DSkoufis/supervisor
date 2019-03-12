package com.supervisor.controller;

import com.supervisor.command.ProductSaveCommand;
import com.supervisor.domain.product.Product;
import com.supervisor.service.ProductService;
import com.supervisor.util.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

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
        ModelAndView model = new ModelAndView(PRODUCT_VIEW_PATH + "productsPage");
        List<Product> products = productService.getAllProducts();
        model.addObject("products", products);
        return model;
    }

    @PostMapping(value = "/save")
    public String createProduct(@Valid @ModelAttribute("command") ProductSaveCommand cmd, BindingResult result,
                                HttpServletResponse httpServletResponse) {
        if (result.hasErrors()) {
            return ValidationError.from(result.getAllErrors()).build(httpServletResponse);
        }

        ValidationError validationError;
        try {
            productService.saveProduct(cmd);
            return "redirect:" + PRODUCT_CONTROLLER_ROOT + "/";
        } catch (javax.validation.ConstraintViolationException ex) {
            validationError = ValidationError.from(ex);
        } catch (org.springframework.dao.DataIntegrityViolationException ex) {
            validationError = ValidationError.from("name", "product.Product.name.unique", "product.Product.name.unique");
        }
        return validationError.build(httpServletResponse);
    }
}
