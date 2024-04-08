package repository;

import entity.Product;
import entity.User;

import java.util.List;

public interface userCRUDRepository {

    void addUser(User user);

    User findByUserName(String name);

    List<User> findAll();

    void updateUser(User user);

    void deleteProduct(String id);

}
