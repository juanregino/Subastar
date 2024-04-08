package model;

import database.ConfigDB;
import entity.Offer;
import entity.Role;
import entity.User;
import repository.OfferCRUDRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OfferModel implements OfferCRUDRepository {
    @Override
    public void addOffer(Offer offer) {
        //1. Abrimos la conexión
        Connection objConnection = ConfigDB.openConnection();
        //try-catch ya que puede fallar
        try {
            //2. Sentencia SQL
            String sql = "INSERT INTO offer (id,amount,buyerId,productId) VALUES(?,?,?,?);";
            //3. Preparar e statement
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);

            //Asignar los '?'
            objPrepared.setString(1, offer.getId());
            objPrepared.setDouble(2, offer.getAmount());
            objPrepared.setString(3, offer.getBuyerId());
            objPrepared.setString(4, offer.getProductId());


            //4. Ejecutamos el query o 'consulta'
            objPrepared.execute();

            System.out.println("Offer insertion completed successfully");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //Cerrar la conexion
        ConfigDB.closeConnection();
    }

    @Override
    public Offer findByID(String id) {
        //1. Abrimos la conexion
        Connection objconnection = ConfigDB.openConnection();
        //Instancio un usuario en null para o vacío para llenarlo con la respuesta
        Offer objOffer = new Offer();
        //try-catch ya que algo puede fallar
        try {
            //3. Creo la sentencia SQL
            String sql = "SELECT * FROM offer WHERE id = ?; ";
            //4. Preparo el statement
            PreparedStatement objPrepared = objconnection.prepareStatement(sql);
            //5. Damos valor al '?'
            objPrepared.setString(1, id);
            //6. Ejecutamos el query
            ResultSet objResult = objPrepared.executeQuery();

            objOffer.setId(objResult.getString("id"));
            objOffer.setAmount(objResult.getDouble("amount"));
            objOffer.setBuyerId(objResult.getString("buyerId"));
            objOffer.setProductId(objResult.getString("productId"));



        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        ConfigDB.closeConnection();
        return objOffer;
    }

    @Override
    public List<Offer> findAll() {
        //1. Abrimos la conexion
        Connection objconnection = ConfigDB.openConnection();
        //Instancio un usuario en null para o vacío para llenarlo con la respuesta
       List<Offer>  listOffers = new ArrayList<>();
        //try-catch ya que algo puede fallar
        try {
            //3. Creo la sentencia SQL
            String sql = "SELECT * FROM offer; ";
            //4. Preparo el statement
            PreparedStatement objPrepared = objconnection.prepareStatement(sql);

            //6. Ejecutamos el query
            ResultSet objResult = objPrepared.executeQuery();
            while (objResult.next()) {
                Offer objOffer = new Offer();
                objOffer.setId(objResult.getString("id"));
                objOffer.setAmount(objResult.getDouble("amount"));
                objOffer.setBuyerId(objResult.getString("buyerId"));
                objOffer.setProductId(objResult.getString("productId"));
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        ConfigDB.closeConnection();
        return listOffers;
    }

    @Override
    public void updateOffer(Offer offer) {
        //1. Abro la conexion
        Connection objConnection = ConfigDB.openConnection();
        //Try-catch ya que puede fallar
        try {
            //2. Sentencia sql
            String sql = "UPDATE offer SET amount = ? , buyerId = ? , productId = ? WHERE id = ? ;";
            //3. Preparar el estado
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);
            // 4. Damos valor a '?'
            objPrepared.setDouble(1, offer.getAmount());
            objPrepared.setString(2, offer.getBuyerId());
            objPrepared.setString(3, offer.getProductId());


            //5. Ejecutamos el query
            int rowAffect = objPrepared.executeUpdate();
            if (rowAffect > 0) {
                System.out.println("Se actualizo correctamente");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //6. Cerramos la conexion
        ConfigDB.closeConnection();
    }

    @Override
    public void deleteOffer(String id) {
        //1. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();
        // Try-catch ya que puede fallar

        try {
            //2. Escribir la sentencia sql
            String sql = "DELETE FROM offer WHERE id = ? ;";
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
