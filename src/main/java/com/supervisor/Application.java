package com.supervisor;

import com.supervisor.domain.project.Project;
import com.supervisor.domain.Product;
import com.supervisor.repository.ProductRepository;
import com.supervisor.repository.project.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Iterator;
import java.util.Set;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    ProductRepository productRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Project project = new Project();
        project.setName("Test");
        projectRepository.save(project);

        Iterable<Project> projects = projectRepository.findAll();

        Product p1 = new Product();
        p1.setName("p1");
        Product p2 = new Product();
        p2.setName("p2");
        Product p3 = new Product();
        p3.setName("p3");

        p1.setUpdate(p2);
        p1.setUpdate(p3);

        productRepository.save(p1);
        productRepository.save(p2);
        productRepository.save(p3);

        Product p4 = new Product();
        p4.setName("p4");
        p3.setUpdate(p4);
        productRepository.save(p4);

        Product p5 = new Product();
        p5.setName("p5");
        productRepository.save(p5);

        p4.setUpdate(p5);
        productRepository.save(p5);

        Iterable<Product> products = productRepository.findAll();

        Iterator<Product> it = products.iterator();
        while (it.hasNext()) {
            Product testP = it.next();
            Set<Product> updates = testP.getUpdates();

            for (Product update : updates) {
                System.out.println(update.getName());
            }
            String b = "B";
        }
        String a = "A";
    }
}
