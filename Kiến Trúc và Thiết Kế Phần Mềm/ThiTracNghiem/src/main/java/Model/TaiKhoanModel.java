/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import Connection.ConnectDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaiKhoanModel {
   
    public TaiKhoanModel() {}

    public List<Object[]> getAllTaiKhoan() {
        Connection conn = null;
        List<Object[]> listTaiKhoan = new ArrayList<>();
        try {
            conn = new ConnectDB().getConnect();
            if (conn != null) {
                String query = "Call hienthi_TK2";
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Object[] row = new Object[]{
                        rs.getString("TaiKhoan"),
                        rs.getString("MatKhau"),
                        rs.getString("Email"),
                        rs.getString("Ten_PhanQuyen"),
                    };
                    listTaiKhoan.add(row);
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
        return listTaiKhoan;
    } 
    
    public boolean addTaiKhoan(String taiKhoan, String matKhau, String email, int role) {
        Connection conn = null;
        try {
            conn = new ConnectDB().getConnect();
            if(conn != null) {
                String query = "Call them_TK1(?,?,?,?)";
                PreparedStatement stmt  = conn.prepareStatement(query);
                stmt.setString(1, taiKhoan);
                stmt.setString(2, matKhau);
                stmt.setString(3, email);
                stmt.setInt(4, role);
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
    
    public boolean check_tk(String taiKhoan, String email) {
        boolean result = false;
        Connection conn = null;
        try {
            conn = new ConnectDB().getConnect();
            if(conn != null) {
                String query = "SELECT COUNT(*) FROM TAI_KHOAN WHERE TaiKhoan = ? OR Email = ?";
                PreparedStatement stmt  = conn.prepareStatement(query);
                stmt.setString(1, taiKhoan);
                stmt.setString(2, email);
                ResultSet rs = stmt.executeQuery();
                if(rs.next()) {
                    int count = rs.getInt(1);
                    result = count > 0;
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
        return result;
    }
    
    public boolean updateTaiKhoan(String taiKhoan, String matKhau, String email, int role) {
        Connection conn = null;
        try {
            conn = new ConnectDB().getConnect();
            if(conn != null) {
                String query = "Call update_TK(?,?,?,?)";
                PreparedStatement stmt  = conn.prepareStatement(query);
                stmt.setString(1, taiKhoan);
                stmt.setString(2, matKhau);
                stmt.setString(3, email);
                stmt.setInt(4, role);
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
    
    public boolean deleteTaiKhoan(String taiKhoan) {
        Connection conn = null;
        try {
            conn = new ConnectDB().getConnect();
            if(conn != null) {
                String query = "Call delete_TK(?)";
                PreparedStatement stmt  = conn.prepareStatement(query);
                stmt.setString(1, taiKhoan);
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
    
    public List<Object[]> searchTaiKhoan(String taiKhoan, String email) {
        Connection conn = null;
        List<Object[]> listTaiKhoan = new ArrayList<>();
        try {
            conn = new ConnectDB().getConnect();
            if(conn != null) {
                String query = "Call timkiem_TK(?,?)";
                PreparedStatement stmt  = conn.prepareStatement(query);
                stmt.setString(1, taiKhoan);
                stmt.setString(2, email);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Object[] row = new Object[]{
                        rs.getString("TaiKhoan"),
                        rs.getString("MatKhau"),
                        rs.getString("Email"),
                        rs.getString("Ten_PhanQuyen"),
                    };
                    listTaiKhoan.add(row);
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
        return listTaiKhoan;
    }
    
    public boolean login(String username, String password) {
        Connection conn = null; 
        try {
            conn = new ConnectDB().getConnect();
            if(conn != null) {
                String query = "Select TaiKhoan, MatKhau FROM Tai_Khoan WHERE TaiKhoan = ? AND MatKhau = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, username);
                stmt.setString(2, password);
                ResultSet rs = stmt.executeQuery();
                if(rs.next()) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if(conn != null) {
                try {
                conn.close();
                } catch (SQLException e) {
                e.printStackTrace();
                }
            }
        }
        return false;
    }
    
    public int phanQuyen(String username) {
        Connection conn = null;
        int loai_TK = 0;
        try {
            conn = new ConnectDB().getConnect();
            if(conn != null) {
                String query = "Select ID_PhanQuyen From Tai_Khoan Where TaiKhoan = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, username);
                ResultSet rs = stmt.executeQuery();
                if(rs.next()) {
                    loai_TK = rs.getInt(1);
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(conn != null) {
                try {
                conn.close();
                } catch (SQLException e) {
                e.printStackTrace();
                }
            }
        }
        return loai_TK;
    }
    
    public List<String> getALlRole() {
        Connection conn = null;
        List<String> listRole = new ArrayList<>();
        try {
            conn = new ConnectDB().getConnect();
            if(conn != null) {
                String query = "Select Ten_PhanQuyen From PhanQuyen";
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();
                while(rs.next()) {
                    String tenRole = rs.getString(1);
                    listRole.add(tenRole);
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
        return listRole;
    }
    
     public int getIdByName(String tenRole) {
        Connection conn = null;
        int idRole = 0;
        try {
            conn = new ConnectDB().getConnect();
            if(conn != null) {
                String query = "Select ID_PhanQuyen From PhanQuyen WHERE Ten_PhanQuyen = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, tenRole);
                ResultSet rs = stmt.executeQuery();
                while(rs.next()) {
                    idRole = rs.getInt(1);
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
        return idRole;
    }
}
