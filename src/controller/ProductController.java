package controller;

import entity.Product;
import entity.State;
import model.ProductModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductController {
    List<Product> listProduct = new ArrayList<>();
    ProductModel objProductModel = new ProductModel();

    public void listAllProducts(String id) {
        List<Product> listTemp = objProductModel.findAll(id);
        for (Product productTemp : listTemp) {
            System.out.println(productTemp.toString() + "\n");
        }
    }

    public void addProduct(Scanner scanner ,String userId) {
        Product productToUpdate = new Product();
        scanner.nextLine();
        System .out.println("Insert the Product id ");
        String id = scanner.nextLine();
        System.out.println("Insert the Product Name ");
        String name = scanner.nextLine();
        System.out.println("Insert the Product Description ");
        String description = scanner.nextLine();
        System.out.println("Insert the Product initial price ");
        double initialPrice = scanner.nextDouble();
        System.out.println("Insert the state of this product  ");
        String state = scanner.next();
        if (state.equalsIgnoreCase("sold")){
            productToUpdate.setState(State.SOLD);
        } else if (state.equalsIgnoreCase("active")) {
            productToUpdate.setState(State.ACTIVE);
        }else{
            System.out.println("invalid status");
        }
        productToUpdate.setId(id);
        productToUpdate.setName(name);
        productToUpdate.setDescription(description);
        productToUpdate.setInitialPrice(initialPrice);
        productToUpdate.setSellerId(userId);
        objProductModel.addProduct(productToUpdate);
    }

    public void deleteProduct(Scanner scanner , String idUser){
        this.listAllProducts(idUser);

        System.out.println("insert id to delete ");
        String id = scanner.next();

        objProductModel.deleteProduct(id);
    }

    public void updateProduct (Scanner scanner , String idUser){
        this.listAllProducts(idUser);
        System .out.println("Insert the Product id to Update ");
        String idUpdate = scanner.next();

        Product productToUpdate = objProductModel.findByID(idUpdate);

        System.out.println(productToUpdate.toString());
        System.out.println("Actualicemos este producto >>");
        System.out.println("Insert the Product Name ");
        String name = scanner.next();
        System.out.println("Insert the Product Description ");
        String description = scanner.next();
        System.out.println("Insert the Product initial price ");
        double initialPrice = scanner.nextDouble();
        System.out.println("Insert the state of this product  ");
        String state = scanner.next();
        if (state.equalsIgnoreCase("sold")){
            productToUpdate.setState(State.SOLD);
        } else if (state.equalsIgnoreCase("active")) {
            productToUpdate.setState(State.ACTIVE);
        }else{
            System.out.println("invalid status");
        }

        productToUpdate.setName(name);
        productToUpdate.setDescription(description);
        productToUpdate.setInitialPrice(initialPrice);

        objProductModel.updateProduct(productToUpdate);
    }
}
