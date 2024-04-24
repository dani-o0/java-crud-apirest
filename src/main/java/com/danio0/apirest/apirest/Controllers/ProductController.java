package com.danio0.apirest.apirest.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danio0.apirest.apirest.Entities.Product;
import com.danio0.apirest.apirest.Respositories.ProductRespository;



@RestController
@RequestMapping("/products")
public class ProductController 
{
    @Autowired
    private ProductRespository productRespository;

    @GetMapping
    public List<Product> getAllProducts()
    {
        return productRespository.findAll();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id)
    {
        return productRespository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se ha podido encontrar el producto con el id: " + id));
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product)
    {
        return productRespository.save(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product productDetails)
    {
        Product product = productRespository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se ha podido encontrar el producto con el id: " + id));

        product.setName(productDetails.getName());
        product.setPrice(productDetails.getPrice());

        return productRespository.save(product);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id)
    {
        Product product = productRespository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se ha podido encontrar el producto con el id: " + id));

        productRespository.delete(product);
        return "El product con el id:" + id + "ha sido eliminado exitosamente!";
    }
}
