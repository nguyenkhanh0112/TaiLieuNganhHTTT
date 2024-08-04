/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Connection.ConnectDB;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Khoahihi79
 */
public class LichThiModel {
    
    public LichThiModel() {}
    
    public List<Object[]> getLichThiByMonThi(String idMonThi) {
        Connection conn = null;
        List<Object[]> dsLichThi = new ArrayList<>();
        try {
            conn = new ConnectDB().getConnect();
            if(conn != null) {
                String query = "Call showLichThi1(?)";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, idMonThi);
                ResultSet rs = stmt.executeQuery();
                while(rs.next()) {
                    Object[] lichThi = new Object[] {
                        rs.getInt("IdLichThi"),
                        rs.getString("TenMonThi"),
                        rs.getDate("NgayLamBai"),
                        rs.getTime("GioBatDau"),
                        rs.getTime("GioKetThuc")
                    };
                    dsLichThi.add(lichThi);
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
        return dsLichThi;
    }
    
    public boolean addLichThi(String idMonThi, Time gioBatDau, Time gioKetThuc, Date ngayLamBai) {
        Connection conn = null;
        try {
            conn = new ConnectDB().getConnect();
            if(conn != null) {
                String query = "Call addLichThi(?,?,?,?)";
                PreparedStatement stmt  = conn.prepareStatement(query);
                stmt.setString(1, idMonThi);
                stmt.setTime(2, gioBatDau);
                stmt.setTime(3, gioKetThuc);
                stmt.setDate(4, ngayLamBai);
                int row = stmt.executeUpdate();
                return row > 0;
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
        return false;
    }
    
    public boolean updateLichThi(int idLichThi, Time gioBatDau, Time gioKetThuc, Date ngayLamBai) {
        Connection conn = null;
        try {
            conn = new ConnectDB().getConnect();
            if(conn != null) {
                String query = "Call updateLichThi(?,?,?,?)";
                PreparedStatement stmt  = conn.prepareStatement(query);
                stmt.setInt(1, idLichThi);
                stmt.setTime(2, gioBatDau);
                stmt.setTime(3, gioKetThuc);
                stmt.setDate(4, ngayLamBai);
                int row = stmt.executeUpdate();
                return row > 0;
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
        return false;
    }
    
    public boolean deleteLichThi(int idLichThi) {
        Connection conn = null;
        try {
            conn = new ConnectDB().getConnect();
            if(conn != null) {
                String query = "Call deleteLichThi(?)";
                PreparedStatement stmt  = conn.prepareStatement(query);
                stmt.setInt(1, idLichThi);
                int row = stmt.executeUpdate();
                return (row > 0);
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
        return false;
    }
}
