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
public class ChuongModel {
    
    public ChuongModel() {}
    
    public List<String> getAllChuongByMonThi(String idMonThi) {
        Connection conn = null;
        List<String> listChuong = new ArrayList<>();
        try {
            conn = new ConnectDB().getConnect();
            if(conn != null) {
                String query = "Select TenChuong From Chuong WHERE IdMonThi = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, idMonThi);
                ResultSet rs = stmt.executeQuery();
                while(rs.next()) {
                    String tenChuong = rs.getString(1);
                    listChuong.add(tenChuong);
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
        return listChuong;
    }
    
    public List<String> getAllChuong() {
        Connection conn = null;
        List<String> listChuong = new ArrayList<>();
        try {
            conn = new ConnectDB().getConnect();
            if(conn != null) {
                String query = "Select TenChuong From Chuong";
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();
                while(rs.next()) {
                    String tenChuong = rs.getString(1);
                    listChuong.add(tenChuong);
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
        return listChuong;
    }
    
    public String getIdByName(String tenChuong, String idMonThi) {
        Connection conn = null;
        String idChuong = "";
        try {
            conn = new ConnectDB().getConnect();
            if(conn != null) {
                String query = "Select IdChuong From Chuong WHERE TenChuong = ? AND IdMonThi = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, tenChuong);
                stmt.setString(2, idMonThi);
                ResultSet rs = stmt.executeQuery();
                while(rs.next()) {
                    idChuong = rs.getString(1);
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
        return idChuong;
    }
    
    
}
