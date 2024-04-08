package model;

import database.ConfigDB;
import entity.Product;
import entity.Role;
import entity.State;
import entity.User;
import repository.ProductCRUDRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductModel implements ProductCRUDRepository {

    @Override
    public void addProduct(Product product) {
        //1. Abrimos la conexión
        Connection objConnection = ConfigDB.openConnection();
        //try-catch ya que puede fallar
        try {
            //2. Sentencia SQL
            String sql = "INSERT INTO product (id,name,description,initialPrice,finalPrice,sellerId,state) VALUES(?,?,?,?,?,?,?);";
            //3. Preparar e statement
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);

            //Asignar los '?'
            objPrepared.setString(1, product.getId());
            objPrepared.setString(2, product.getName());
            objPrepared.setString(3, product.getDescription());
            objPrepared.setDouble(4, product.getInitialPrice());
            objPrepared.setDouble(5, product.getFinalPrice());
            objPrepared.setString(6, product.getSellerId());
            objPrepared.setString(7, String.valueOf(product.getState()));

            //4. Ejecutamos el query o 'consulta'
            objPrepared.execute();

            System.out.println("User insertion completed successfully");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //Cerrar la conexion
        ConfigDB.closeConnection();
    }

    @Override
    public Product findByID(String id) {
        //1. Abrimos la conexion
        Connection objconnection = ConfigDB.openConnection();
        //Instancio un product en null para o vacío para llenarlo con la respuesta
        Product objProduct  = new Product();
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

            objProduct.setId(objResult.getString("id"));
            objProduct.setName(objResult.getString("name"));
            objProduct.setDescription(objResult.getString("description"));
            objProduct.setInitialPrice(objResult.getDouble("initialPrice"));
            objProduct.setFinalPrice(objResult.getDouble("finalPrice"));
            objProduct.setSellerId(objResult.getString("sellerId"));
            objProduct.setState(State.valueOf(objResult.getString("state")));


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        ConfigDB.closeConnection();
        return objProduct;

    }

    @Override
    public List<Product> findAll(String idUser) {
        //1. Abrimos la conexion
        Connection objconnection = ConfigDB.openConnection();
        //Instancio un product en null para o vacío para llenarlo con la respuesta
        List<Product > lisProducts = new ArrayList<>();
        //try-catch ya que algo puede fallar
        try {
            //3. Creo la sentencia SQL
            String sql = "select * from product inner join users on users.id = product.sellerId WHERE users.id = ?;";
            //4. Preparo el statement
            PreparedStatement objPrepared = objconnection.prepareStatement(sql);
            //5 asignamos valor a ?
            objPrepared.setString(1,idUser);
            //6. Ejecutamos el query
            ResultSet objResult = objPrepared.executeQuery();
           while (objResult.next()) {
               Product objProduct = new Product();
               objProduct.setId(objResult.getString("id"));
               objProduct.setName(objResult.getString("name"));
               objProduct.setDescription(objResult.getString("description"));
               objProduct.setInitialPrice(objResult.getDouble("initialPrice"));
               objProduct.setFinalPrice(objResult.getDouble("finalPrice"));
               objProduct.setSellerId(objResult.getString("sellerId"));
               objProduct.setState(State.valueOf(objResult.getString("state")));

               lisProducts.add(objProduct);
           }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
         ConfigDB.closeConnection();
        return lisProducts;

    }

    @Override
    public void updateProduct(Product product) {
        //1. Abro la conexion
        Connection objConnection = ConfigDB.openConnection();
        //Try-catch ya que puede fallar
        try {
            //2. Sentencia sql
            String sql = "UPDATE product SET name = ? , description = ? , initialPrice = ?, finalPrice = ? , sellerId = ? , state = ? WHERE id = ? ;";
            //3. Preparar el estado
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);
            // 4. Damos valor a '?'
            objPrepared.setString(1, product.getId());
            objPrepared.setString(2, product.getName());
            objPrepared.setString(3, product.getDescription());
            objPrepared.setDouble(4, product.getInitialPrice());
            objPrepared.setDouble(5, product.getFinalPrice());
            objPrepared.setString(6, product.getSellerId());
            objPrepared.setString(7, String.valueOf(product.getState()));

            //5. Ejecutamos el query
            int rowAffect = objPrepared.executeUpdate();
            if (rowAffect > 0) {
                System.out.println("Se actualzo correctsmne");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //6. Cerramos la conexion
        ConfigDB.closeConnection();
    }

    @Override
    public void deleteProduct(String id) {
//1. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();
        // Try-catch ya que puede fallar

        try {
            //2. Escribir la sentencia sql
            String sql = "DELETE FROM product WHERE id = ? ;";
            //3. Preparamos el statement
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);

            //4. Asignamos el valor al ?
            objPrepared.setString(1,id);

            //5. Ejecutamos el query
            objPrepared.execute();

            System.out.println("Delete successfully");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        //6. Cerramos la conexión
        ConfigDB.closeConnection();
    }
}
