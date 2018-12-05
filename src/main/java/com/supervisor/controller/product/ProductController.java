package com.supervisor.controller.product;

import com.supervisor.dao.product.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductDao productDao;

    @RequestMapping(value = {"index", "/"})
    public String index(Model model) {
        return "";
    }
}
