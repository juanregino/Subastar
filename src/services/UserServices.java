package services;

import entity.User;
import model.UserModel;

import java.util.Scanner;

public class UserServices {

    public User login(String email , String password){

        //instancio un userModel
        UserModel objUserModel = new UserModel();
        User objUser = objUserModel.findByUserName(email);
        if ( objUser != null && objUser.getPassword().equals(password)){
           return  objUser;

        }
        else{
            System.out.println("Email or Password incorrect , please verify");
        }
        return null;
    }


}
