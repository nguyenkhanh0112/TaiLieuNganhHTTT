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
public class KhoaModel {
    
    public KhoaModel() {}
    
    public List<String> getAllKhoa() { 
        Connection conn = null;
        List<String> listKhoa = new ArrayList<>();
        try {
            conn = new ConnectDB().getConnect();
            if(conn != null) {
                String query = "Select TenKhoa From Khoa";
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();
                while(rs.next()) {
                    String tenKhoa = rs.getString(1);
                    listKhoa.add(tenKhoa);
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
        return listKhoa;
    }
    
    public String getIdByName(String tenKhoa) {
        Connection conn = null;
        String idKhoa = "";
        try {
            conn = new ConnectDB().getConnect();
            if(conn != null) {
                String query = "Select IdKhoa From Khoa WHERE TenKhoa = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, tenKhoa);
                ResultSet rs = stmt.executeQuery();
                while(rs.next()) {
                    idKhoa = rs.getString(1);
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
        return idKhoa;
    }
}
