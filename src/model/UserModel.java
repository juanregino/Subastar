package model;

import database.ConfigDB;
import entity.Role;
import entity.User;
import repository.userCRUDRepository;

import java.io.ObjectInputFilter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserModel implements userCRUDRepository {
    @Override
    public void addUser(User user) {
        //1. Abrimos la conexión
        Connection objConnection = ConfigDB.openConnection();
        //try-catch ya que puede fallar
        try {
            //2. Sentencia SQL
            String sql = "INSERT INTO users (email,name,email,password,role) VALUES (?,?,?,?,?);";
            //3. Preparar e statement
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);

            //Asignar los '?'
            objPrepared.setString(1, user.getId());
            objPrepared.setString(2, user.getName());
            objPrepared.setString(3, user.getEmail());
            objPrepared.setString(4, user.getPassword());
            objPrepared.setString(5, String.valueOf(user.getRole()));

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
    public User findByUserName(String email) {
        //1. Abrimos la conexion
        Connection objconnection = ConfigDB.openConnection();
        //Instancio un usuario en null para o vacío para llenarlo con la respuesta
        User objUser = new User();
        //try-catch ya que algo puede fallar
        try {
            //3. Creo la sentencia SQL
            String sql = "SELECT * FROM users WHERE email = ?; ";
            //4. Preparo el statement
            PreparedStatement objPrepared = objconnection.prepareStatement(sql);
            //5. Damos valor al '?'
            objPrepared.setString(1, email);
            //6. Ejecutamos el query
            ResultSet objResult = objPrepared.executeQuery();
            while (objResult.next()) {
                objUser.setId(objResult.getString("id"));
                objUser.setName(objResult.getString("name"));
                objUser.setEmail(objResult.getString("email"));
                objUser.setPassword(objResult.getString("password"));
                objUser.setRole(Role.valueOf(objResult.getString("role")));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        ConfigDB.closeConnection();
        return objUser;
    }

    @Override
    public List<User> findAll() {

        //1. Abrimos la conexion
        Connection objConnection = ConfigDB.openConnection();
        //2. instanciamos la lista
        List<User> listUser = new ArrayList<>();
        //Try-catch ya que puede fallar
        try {
            //3. Sentencia SQL
            String sql = "SELECT * FROM users;";
            //4. Preparamos el statement
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);
            //5 Ejecuto el query y lo guardo en un resulset
            ResultSet objResult = objPrepared.executeQuery();
            //6. Recorremos la lista o el resulSet para crear un user y adicionarlo a la lista
            while (objResult.next()) {
                User objUser = new User();
                objUser.setId(objResult.getString("id"));
                objUser.setName(objResult.getString("name"));
                objUser.setEmail(objResult.getString("email"));
                objUser.setPassword(objResult.getString("password"));
                objUser.setRole(Role.valueOf(objResult.getString("role")));
                //Finalmente insertamos este usuario a la lista
                listUser.add(objUser);

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //7. Cerramos la conexion
        ConfigDB.closeConnection();
        return listUser;
    }

    @Override
    public void updateUser(User user) {
        //1. Abro la conexion
        Connection objConnection = ConfigDB.openConnection();
        //Try-catch ya que puede fallar
        try {
            //2. Sentencia sql
            String sql = "UPDATE users SET name = ? , email = ? , password = ?, role = ?  WHERE users.id = ? ;";
            //3. Preparar el estado
            PreparedStatement objPrepared = objConnection.prepareStatement(sql);
            // 4. Damos valor a '?'
            objPrepared.setString(1, user.getName());
            objPrepared.setString(2, user.getEmail());
            objPrepared.setString(3, user.getPassword());
            objPrepared.setString(4, String.valueOf(user.getRole()));
            objPrepared.setString(5, user.getId());


            //5. Ejecutamos el query
            int rowAffect = objPrepared.executeUpdate();
            System.out.println("Llega" + rowAffect);
            System.out.println(user.getEmail());
            if (rowAffect > 0) {
                System.out.println("Se actualzo correctamente");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //6. Cerramos la conexion
        ConfigDB.closeConnection();
    }

    @Override
    public void deleteProduct(String email) {
        //1. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();
        // Try-catch ya que puede fallar

        try {
          //2. Escribir la sentencia sql
            String sql = "DELETE FROM users WHERE email = ? ;";
           //3. Preparamos el statement
           PreparedStatement objPrepared = objConnection.prepareStatement(sql);

           //4. Asignamos el valor al ?
            objPrepared.setString(1,email);

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
