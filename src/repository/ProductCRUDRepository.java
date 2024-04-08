package repository;

import entity.Product;
import entity.User;

import java.util.List;

public interface ProductCRUDRepository {
    void addProduct(Product product);

    Product findByID(String id);

    List<Product> findAll(String id);

    void updateProduct(Product product);

    void deleteProduct(String id);
}

