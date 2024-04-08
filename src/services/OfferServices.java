package services;

import database.ConfigDB;
import entity.Product;
import entity.State;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OfferServices {

    public List<Product> listActiveProdcuts() {
        //1. Abrimos la conexion
        Connection objconnection = ConfigDB.openConnection();
        //Instancio un product en null para o vacío para llenarlo con la respuesta
        List<Product> lisProducts = new ArrayList<>();
        //try-catch ya que algo puede fallar
        try {
            //3. Creo la sentencia SQL
            String sql = "select * from product  WHERE state = 'active' ;";
            //4. Preparo el statement
            PreparedStatement objPrepared = objconnection.prepareStatement(sql);
            //5 asignamos valor a ?

            //6. Ejecutamos el query
            ResultSet objResult = objPrepared.executeQuery();
            while (objResult.next()) {
                Product objProduct = new Product();
                objProduct.setId(objResult.getString("id"));
                objProduct.setName(objResult.getString("name"));

                lisProducts.add(objProduct);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        ConfigDB.closeConnection();
        return lisProducts;

    }

    public Product listProductById(String id) {
        //1. Abrimos la conexion
        System.out.println(id);
        Connection objconnection = ConfigDB.openConnection();
        //Instancio un product en null para o vacío para llenarlo con la respuesta
        Product objProduct = new Product();
        //try-catch ya que algo puede fallar
        try {
            //3. Creo la sentencia SQL
            String sql = "SELECT * FROM product WHERE id =  ?; ";
            //4. Preparo el statement
            PreparedStatement objPrepared = objconnection.prepareStatement(sql);
            //5. Damos valor al '?'
            objPrepared.setString(1, id);
            //6. Ejecutamos el query
            ResultSet objResult = objPrepared.executeQuery();

            while (objResult.next()) {
                objProduct.setId(objResult.getString("id"));
                objProduct.setName(objResult.getString("name"));
                objProduct.setDescription(objResult.getString("description"));
                objProduct.setInitialPrice(objResult.getDouble("initialPrice"));
                objProduct.setFinalPrice(objResult.getDouble("finalPrice"));
                objProduct.setSellerId(objResult.getString("sellerId"));
                objProduct.setState(State.valueOf(objResult.getString("state")));
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        ConfigDB.closeConnection();
        return objProduct;

    }

    public String calcOfferActuallity(String id) {
        //1. Abrimos la conexion
        double precioOfertaActual;
        String str;
        Connection objconnection = ConfigDB.openConnection();
        //Instancio un product en null para o vacío para llenarlo con la respuesta

        //try-catch ya que algo puede fallar
        try {
            //3. Creo la sentencia SQL
            String sql = "SELECT * FROM product  WHERE state = 'ACTIVE' and id = ? ORDER BY finalPrice DESC LIMIT 1  ;";
            //4. Preparo el statement
            PreparedStatement objPrepared = objconnection.prepareStatement(sql);
            //5. le doyt valor al ?
            objPrepared.setString(1, id);
            //6. Ejecutamos el query
            ResultSet objResult = objPrepared.executeQuery();

            if (objResult.next()) {
                precioOfertaActual = objResult.getDouble("finalPrice");
                // Mostrar el precio de la oferta actual
                str =  ("Precio de la oferta actual: " + precioOfertaActual);
            } else {
                // No hay ofertas disponibles
                precioOfertaActual = objResult.getDouble("initialPrice");
                str = ("Precio de la oferta actual: " + precioOfertaActual);
            }


            return str;


        } catch (Exception e) {
            str = e.getMessage();
        }

        ConfigDB.closeConnection();
       return str;
    }


}
