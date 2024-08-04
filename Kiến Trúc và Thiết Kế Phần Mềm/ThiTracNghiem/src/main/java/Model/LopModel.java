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
public class LopModel {
    
    public LopModel() {}
    
    public List<String> getAllLop() {
        Connection conn = null;
        List<String> dsLop = new ArrayList<>();
        try {
            conn = new ConnectDB().getConnect();
            if (conn != null) {
                String query = "Select TenLop From Lop";
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    String lop = rs.getString(1);
                    dsLop.add(lop);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return dsLop;
    }
    
    public List<String> getLopByKhoa(String idKhoa) {
        Connection conn = null;
        List<String> dsLop = new ArrayList<>();
        try {
            conn = new ConnectDB().getConnect();
            if (conn != null) {
                String query = "Select TenLop From Lop WHERE IdKhoa = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, idKhoa);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    String lop = rs.getString(1);
                    dsLop.add(lop);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return dsLop;
    }
    
    public String getIdByName(String tenLop) {
        Connection conn = null;
        String idLop = "";
        try {
            conn = new ConnectDB().getConnect();
            if(conn != null) {
                String query = "Select IdLop From Lop WHERE TenLop = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, tenLop);
                ResultSet rs = stmt.executeQuery();
                while(rs.next()) {
                    idLop = rs.getString(1);
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
        return idLop;
    }
}
