/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Connection;

import java.sql.Connection;
import java.sql.DriverManager;


/**
 *
 * @author Khoahihi79
 */
public class ConnectDB {
        String hostName = "localhost:3308";
        String user = "root";
        String pass = "123456";
        String dbName = "thitracnghiem";
        String url = "jdbc:mysql://"+hostName+"/"+dbName;
        Connection conn = null;
    
        public ConnectDB() {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(url, user, pass);
            } catch(Exception e) {
                e.printStackTrace();
            } 
        }
        public Connection getConnect() {
            try {
                if(conn == null) {
                    conn = DriverManager.getConnection(url, user, pass);
                }
            } catch(Exception e) {
                e.printStackTrace();
            } 
            return conn;
        }   
}
   

