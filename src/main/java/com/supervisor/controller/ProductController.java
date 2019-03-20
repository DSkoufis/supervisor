package com.supervisor.controller;

import com.supervisor.command.ProductSaveCommand;
import com.supervisor.domain.product.Product;
import com.supervisor.service.ProductService;
import com.supervisor.util.response.ProductCreationResponse;
import com.supervisor.util.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

import static com.supervisor.util.constant.ViewMapping.PRODUCT_VIEW_PATH;

@Controller
@RequestMapping(value = "/product")
public class ProductController {

    private ProductService productService;

    public ProductController() {}

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

    @ResponseBody
    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response createProduct(@Valid @ModelAttribute("command") ProductSaveCommand cmd, BindingResult result,
                                HttpServletResponse httpServletResponse) {
        if (result.hasErrors()) {
            return ProductCreationResponse.from(result.getAllErrors()).build(httpServletResponse);
        }

        ProductCreationResponse response;
        try {
            Product product = productService.saveProduct(cmd);
            return ProductCreationResponse.from(product).build(httpServletResponse);
        } catch (javax.validation.ConstraintViolationException ex) {
            response = ProductCreationResponse.from(ex);
        } catch (org.springframework.dao.DataIntegrityViolationException ex) {
            response = ProductCreationResponse.from("name", "product.Product.name.unique", "product.Product.name.unique");
        }
        return response.build(httpServletResponse);
    }
}
