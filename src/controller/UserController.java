package controller;

import entity.Role;
import entity.User;
import model.UserModel;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserController {

    UserModel objUserModel = new UserModel();
    List<User> listUsers = objUserModel.findAll();

    public void registerUser(Scanner scanner) {

        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{4,}$";

        boolean flagEmail;

        User objUser = new User();

        System.out.println("Insert Your Id");
        String id = scanner.next();
        objUser.setId(id);

        System.out.println("Insert Your Name");
        String name = scanner.next();
        objUser.setName(name);

        System.out.println("Insert Your Role >> SELLER or BUYER");
        String role = scanner.next();
        if (role.equalsIgnoreCase("seller")){
            objUser.setRole(Role.seller);
        } else if (role.equalsIgnoreCase("buyer")) {
            objUser.setRole(Role.buyer);
        }else {
            System.out.println("Rol no valido");
            return;
        }


        //User Name validation
        do {
            flagEmail = false;
            System.out.println("Insert your email");
            String Email = scanner.next();
            for (User userTemp : listUsers) {
                if (userTemp.getEmail().equalsIgnoreCase(Email)) {
                    flagEmail = true;
                    System.out.println("This Email is already taken, try with another one");
                    break;
                }
            }
            objUser.setEmail(Email);

        } while (flagEmail);

        boolean flagPassword;
        do {
            System.out.println("""
                     Insert your password. This password requires:
                                         - At least 4 characters
                                         - 1 uppercase letter
                                         - 1 special character
                                         - 1 number
                    """);
            String password = scanner.next();

            flagPassword = validatePassword(password, regex);
            if (!flagPassword) {
                System.out.println("The password requires: at least 4 characters, 1 uppercase letter, 1 special character and 1 number. Please try again.");

            }
            objUser.setPassword(password);

        } while (!flagPassword);
        objUserModel.addUser(objUser);
    }

    public boolean validatePassword(String password, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);

        return matcher.matches();
    }

    public void listAllUsers (){
        if (listUsers.isEmpty()){
            System.out.println("No hay nada");
        }else {
            for (User userTemp : listUsers){
                System.out.println(userTemp.toString() + "\n");
            }
        }

    }

    public void deleteUser(Scanner scanner){
        this.listAllUsers();

        System.out.println("insert email at delete");
        String emailDelete = scanner.next();

        objUserModel.deleteProduct(emailDelete);
        System.out.println("Delete Successfully");
    }

    public void updateUser(Scanner scanner){
        this.listAllUsers();
        System.out.println("Insert email a Update");
        String emailUpdate = scanner.next();
        User objUserUpdate = objUserModel.findByUserName(emailUpdate);
        System.out.println(objUserUpdate.toString());
        if (objUserUpdate == null){
            System.out.println("User NotFound");
        }
        else{


            System.out.println("Insert your name");
            String name = scanner.next();
            System.out.println("Insert your email");
            String email = scanner.next();
            System.out.println("Insert your password");
            String password = scanner.next();
            System.out.println("Insert Your Role >> SELLER or BUYER");
            String role = scanner.next();


            objUserUpdate.setName(name);
            objUserUpdate.setEmail(email);
            objUserUpdate.setPassword(password);
            objUserUpdate.setRole(Role.valueOf(role));

            objUserModel.updateUser(objUserUpdate);
        }

    }
}

