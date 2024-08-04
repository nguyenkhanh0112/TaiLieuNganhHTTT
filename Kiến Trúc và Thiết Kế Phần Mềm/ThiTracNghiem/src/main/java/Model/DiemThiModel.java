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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Khoahihi79
 */
public class DiemThiModel {
    
    public DiemThiModel() {}
    
    public void luuKetQuaThi(String idSinhVien, String idMonThi, Date ngayThi, double diemThi, String idLop, String idDeThi) {
        Connection conn = null;
        boolean result = false;
        try {
            conn = new ConnectDB().getConnect();
            if(conn != null) {
                String query = "INSERT INTO KETQUATHI VALUES(?, ?, ?, ?, ?, ?)";
                PreparedStatement stmt  = conn.prepareStatement(query);
                stmt.setString(1, idSinhVien);
                stmt.setString(2, idMonThi);
                stmt.setDate(3, ngayThi);
                stmt.setDouble(4, diemThi);
                stmt.setString(5, idLop);
                stmt.setString(6, idDeThi);
                stmt.executeUpdate();
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
    }
    
    public List<Object[]> showKetQuaThi(String username) {
        Connection conn = null;
        List<Object[]> listKetQua = new ArrayList<>();
        try {
            conn = new ConnectDB().getConnect();
            if(conn != null) {
                String query = "Call showInformationStudent1(?)";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, username);
                ResultSet rs = stmt.executeQuery();
                while(rs.next()) {
                    Object[] diem = new Object[] {
                        rs.getDate("NgayThi"),
                        rs.getString("TenMonThi"),
                        rs.getDouble("DiemThi"),
                    };
                    listKetQua.add(diem);
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
        return listKetQua;
    }
    
    public List<Object[]> DsDiemTheoMonThi(String idMonThi) {
        Connection conn = null;
        List<Object[]> listDiem = new ArrayList<>();
        try {
            conn = new ConnectDB().getConnect();
            if(conn != null) {
                String query = "Call showResult2(?)";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, idMonThi);
                ResultSet rs = stmt.executeQuery();
                while(rs.next()) {
                    Object[] diem = new Object[] {
                        rs.getString("IdSinhVien"),
                        rs.getString("TenSinhVien"),
                        rs.getString("TenLop"),
                        rs.getDouble("DiemThi"),
                        rs.getString("TenMonThi"),
                    };
                    listDiem.add(diem);
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
        return listDiem;
    }
    
    public List<Object[]> dsDiemTheoLopVaMon(String idLop, String idMonThi) {
        Connection conn = null;
        List<Object[]> listDiem = new ArrayList<>();
        try {
            conn = new ConnectDB().getConnect();
            if(conn != null) {
                String query = "Call showResult1(?,?)";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, idLop);
                stmt.setString(2, idMonThi);
                ResultSet rs = stmt.executeQuery();
                while(rs.next()) {
                    Object[] diem = new Object[] {
                        rs.getString("IdSinhVien"),
                        rs.getString("TenSinhVien"),
                        rs.getString("TenLop"),
                        rs.getDouble("DiemThi"),
                        rs.getString("TenMonThi"),
                    };
                    listDiem.add(diem);
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
        return listDiem;
    }
    
    public List<Object[]> timKiemDiem(String idMonThi, String idLop, double diemThi1, double diemThi2) {
        Connection conn = null;
        List<Object[]> listDiem = new ArrayList<>();
        try {
            conn = new ConnectDB().getConnect();
            if(conn != null) {
                String query = "Call showResult3(?,?,?,?)";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, idMonThi);
                stmt.setString(2, idLop);
                stmt.setDouble(3, diemThi1);
                stmt.setDouble(4, diemThi2);
                ResultSet rs = stmt.executeQuery();
                while(rs.next()) {
                    Object[] diem = new Object[] {
                        rs.getString("IdSinhVien"),
                        rs.getString("TenSinhVien"),
                        rs.getString("TenLop"),
                        rs.getDouble("DiemThi"),
                        rs.getString("TenMonThi"),
                    };
                    listDiem.add(diem);
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
        return listDiem;
    }
}
