import controller.OfferController;
import controller.ProductController;
import controller.UserController;
import database.ConfigDB;
import entity.Offer;
import entity.User;
import model.UserModel;
import services.OfferServices;
import services.UserServices;

import java.sql.Connection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserController objUserController = new UserController();
        UserServices objUserService = new UserServices();
        ProductController objProductController = new ProductController();
        OfferController objOfferController = new OfferController();
        int option = -1;
        int optionOffer = -1;
        String idUserLogged ;
        Scanner scanner = new Scanner(System.in);
        do {

            System.out.println("""
                    MENU
                                        
                    1. Login 
                    2. Register
                    3. Exit
                    """);
            System.out.println("option >>");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Insert Your Email");
                    String email = scanner.next();
                    System.out.println("Insert Your Password");
                    String password = scanner.next();
                    User objUserLogged = objUserService.login(email, password);
                    if (objUserLogged != null) {
                        System.out.println("Loggin succesfull");
                    } else {
                        System.out.println("user notfound");
                        break;
                    }
                   idUserLogged = objUserLogged.getId();
                    String role = String.valueOf(objUserLogged.getRole());
                    if (role.equalsIgnoreCase("seller")) {
                        System.out.println("Eres un seller");
                        do {
                            int optionSeller = -1;
                            System.out.println("""
                                    1. Update yout profile
                                    2. Delete this profile
                                    3. List yours products
                                    4. Add Product 
                                    5. Delete Product
                                    6. Update Product
                                    7. Exit 
                                    """);
                            System.out.println("Option >>"+ "");
                            optionSeller = scanner.nextInt();

                            switch (optionSeller){
                                case 1 :
                                    objUserController.updateUser(scanner);
                                    break;

                                case 2 :
                                    objUserController.deleteUser(scanner);
                                    break;
                                case 3 :
                                    objProductController.listAllProducts(idUserLogged);
                                    break;
                                case 4 :
                                    objProductController.addProduct(scanner,idUserLogged);
                                    break;
                                case 5 :
                                    objProductController.deleteProduct(scanner,idUserLogged);
                                    break;
                                case 6 :
                                    objProductController.updateProduct(scanner, idUserLogged);
                                    break;

                            }
                        }while (option != 7);

                    } else if (role.equalsIgnoreCase("buyer")) {
                        System.out.println("Eres un buyer");
                        ///NOTA: Por cada oferta se crea una nueva row

                            //Calcular el precio de la oferta actual, traigo todas las ofertas y muestro la 1 en DESC

                            //Selecciona 1 producto y ya lo muestro completo


                            //Y si oferta se crea una nueva oferta
                        //Listar los productos pero solo los activos // solo el nombre y id
                         objOfferController.listAllActiveProducts();

                         //Para seleccionar el producto inserta el id
                        System.out.println("To select the product, insert the id");
                        String idProduct = scanner.next();
                        objOfferController.listProductId(idProduct);

                        do {
                            System.out.println("""
                                    Do you want to offer
                                    1. Si
                                    2. No
                                    """);
                            optionOffer = scanner.nextInt();
                            switch (optionOffer){
                                case 1 :
                                     String actPrice = objOfferController.calcOffers(idProduct);
                                    System.out.println("This is actually offer" + actPrice);

                                    Offer objOffer = new Offer();
                                    System.out.println("Ingresa el monto de tu oferta");
                                    double ammount = scanner.nextDouble();
                                    objOffer.setAmount(ammount);
                                    objOffer.setBuyerId(idUserLogged);
                                    objOffer.setProductId(idProduct);

                                    objOfferController.addOffer(objOffer);
                                    break;
                            }
                        }while (optionOffer != 2);



                    } else {
                        System.out.println("No tienes ningun role");
                    }

                    break;
                case 2:
                    System.out.println("entre");
                    objUserController.registerUser(scanner);
                    break;
            }
        } while (option != 3);
//Culminar mostrando cuantas ofertas ha recibido un producto

    }
}
