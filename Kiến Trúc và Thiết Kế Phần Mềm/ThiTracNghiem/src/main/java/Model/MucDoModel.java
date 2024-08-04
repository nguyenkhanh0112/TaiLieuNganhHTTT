/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Connection.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Khoahihi79
 */
public class MucDoModel {
    
    public MucDoModel() {}
    
    public List<String> getAllMucDo() {
        Connection conn = null;
        List<String> listMucDo = new ArrayList<>();
        try {
            conn = new ConnectDB().getConnect();
            if(conn != null) {
                String query = "Select TenMucDo From MucDo";
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();
                while(rs.next()) {
                    String tenMucDo = rs.getString(1);
                    listMucDo.add(tenMucDo);
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return listMucDo;
    }
    
    public String getIdByName(String tenMucDo) {
        Connection conn = null;
        String idMucDo = "";
        try {
            conn = new ConnectDB().getConnect();
            if(conn != null) {
                String query = "Select IdMucDo From MucDo WHERE TenMucDo = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, tenMucDo);
                ResultSet rs = stmt.executeQuery();
                while(rs.next()) {
                    idMucDo = rs.getString(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return idMucDo;
    }
}
