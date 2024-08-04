/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Connection.ConnectDB;
import com.mysql.cj.jdbc.Blob;
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
public class ThiSinhModel {
    
    public ThiSinhModel() {}
    
    public Object[] showThongTinSinhVien(String username) {
        Connection conn = null;
        Object[] infos = null;
        try {
            conn = new ConnectDB().getConnect();
            if(conn != null) {
                String query = "Call showInformationStudent(?)";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, username);
                ResultSet rs = stmt.executeQuery();
                while(rs.next()) {
                        infos  = new Object[] {
                        rs.getString("IdSinhVien"),
                        rs.getString("TenSinhVien"),
                        rs.getString("TenLop"),
                        rs.getDate("NgaySinh"),
                        rs.getString("GioiTinh"),
                        rs.getString("DiaChi"),
                        rs.getString("SDT"),
                        rs.getBytes("AnhSinhVien")
                    };
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
        return infos;
    }
    
    public String getTenSVById(String idSinhVien) {
        Connection conn = null;
        String tenSV = "";
        try {
            conn = new ConnectDB().getConnect();
            if(conn != null) {
                String query = "Select TenSinhVien from SinhVien Where TaiKhoan = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, idSinhVien);
                ResultSet rs = stmt.executeQuery();
                while(rs.next()) {
                    tenSV = rs.getString(1);
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
        return tenSV;
    }
    
    public List<Object[]> getDsSvByKhoa(String idKhoa) { 
        Connection conn = null;
        List<Object[]> dsSinhVien = new ArrayList<>();
        try {
            conn = new ConnectDB().getConnect();
            if(conn != null) {
                String query = "Call hienthi_sinhvienn(?)";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, idKhoa);
                ResultSet rs = stmt.executeQuery();
                while(rs.next()) {
                    Object[] sinhVien = new Object[] {
                        rs.getString("IdSinhVien"),
                        rs.getString("TenSinhVien"),
                        rs.getString("TenLop"),
                        rs.getString("TenKhoa"),
                        rs.getDate("NgaySinh"),
                        rs.getString("GioiTinh"),
                        rs.getString("SDT"),
                        rs.getString("DiaChi")
                    };
                    dsSinhVien.add(sinhVien);
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
        return dsSinhVien;
    }
    
    public List<Object[]> getDsSvByLop(String idLop) {
        Connection conn = null;
        List<Object[]> dsSinhVien = new ArrayList<>();
        try {
            conn = new ConnectDB().getConnect();
            if(conn != null) {
                String query = "Call hienthisv_lop(?)";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, idLop);
                ResultSet rs = stmt.executeQuery();
                while(rs.next()) {
                    Object[] sinhVien = new Object[] {
                        rs.getString("IdSinhVien"),
                        rs.getString("TenSinhVien"),
                        rs.getString("TenLop"),
                        rs.getString("TenKhoa"),
                        rs.getDate("NgaySinh"),
                        rs.getString("GioiTinh"),
                        rs.getString("SDT"),
                        rs.getString("DiaChi")
                    };
                    dsSinhVien.add(sinhVien);
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
        return dsSinhVien;
    }
    
    public byte[] getImageStudent(String idSinhVien) {
        Connection conn = null;
        byte[] imageData = null;
        try {
            conn = new ConnectDB().getConnect();
            if(conn != null) {
                String query = "Select AnhSinhVien From SinhVien Where IdSinhVien = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, idSinhVien);
                ResultSet rs = stmt.executeQuery();
                if(rs.next()) {
                    imageData = rs.getBytes(1);
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
        return imageData;
    }
    
    public boolean updateImage(byte[] anhSinhVien, String username) {
        Connection conn = null;
        try {
            conn = new ConnectDB().getConnect();
            if(conn != null) {
                String query = "UPDATE SinhVien SET AnhSinhVien = ? WHERE TaiKhoan = ?";
                PreparedStatement stmt  = conn.prepareStatement(query);
                stmt.setBytes(1, anhSinhVien);
                stmt.setString(2, username);
                int row = stmt.executeUpdate();
                if(row > 0) {
                    return true;
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
        return false;
    }
      
    public boolean addThiSinh(String idSinhVien, String tenSinhVien, String idLop, String idKhoa, Date ngaySinh, int gioiTinh, String diaChi, String sdt) {
        Connection conn = null;
        try {
            conn = new ConnectDB().getConnect();
            if(conn != null) {
                String query = "Call add_sv1(?,?,?,?,?,?,?,?)";
                PreparedStatement stmt  = conn.prepareStatement(query);
                stmt.setString(1, idSinhVien);
                stmt.setString(2, tenSinhVien);
                stmt.setString(3, idLop);
                stmt.setString(4, idKhoa);
                stmt.setDate(5, ngaySinh);
                stmt.setInt(6, gioiTinh);
                stmt.setString(7, diaChi);
                stmt.setString(8, sdt);
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
    
    public boolean updateThiSinh(String idSinhVien, String tenSinhVien, String idLop, String idKhoa, Date ngaySinh, int gioiTinh, String diaChi, String sdt) {
        Connection conn = null;
        try {
            conn = new ConnectDB().getConnect();
            if(conn != null) {
                String query = "Call update_sv(?,?,?,?,?,?,?,?)";
                PreparedStatement stmt  = conn.prepareStatement(query);
                stmt.setString(1, idSinhVien);
                stmt.setString(2, tenSinhVien);
                stmt.setString(3, idLop);
                stmt.setString(4, idKhoa);
                stmt.setDate(5, ngaySinh);
                stmt.setInt(6, gioiTinh);
                stmt.setString(7, diaChi);
                stmt.setString(8, sdt);
                int row = stmt.executeUpdate();
                if(row > 0) {
                    return true;
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
        return false;
    }
    
    public boolean deleteThiSinh(String idSinhVien) {
        Connection conn = null;
        try {
            conn = new ConnectDB().getConnect();
            if(conn != null) {
                String query = "DELETE FROM SinhVien WHERE IdSinhVien = ?";
                PreparedStatement stmt  = conn.prepareStatement(query);
                stmt.setString(1, idSinhVien);
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
    
    public List<Object[]> searchThiSinh(String idThiSinh, String tenThiSinh) {
        Connection conn = null;
        List<Object[]> dsThiSinh = new ArrayList<>();
        try {
            conn = new ConnectDB().getConnect();
            if(conn != null) {
                String query = "Call timkiem_sv(?,?)";
                PreparedStatement stmt  = conn.prepareStatement(query);
                stmt.setString(1, idThiSinh);
                stmt.setString(2,tenThiSinh);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Object[] thiSinh = new Object[]{
                        rs.getString("IdSinhVien"),
                        rs.getString("TenSinhVien"),
                        rs.getString("TenLop"),
                        rs.getString("TenKhoa"),
                        rs.getDate("NgaySinh"),
                        rs.getString("GioiTinh"),
                        rs.getString("SDT"),
                        rs.getString("DiaChi")
                    };
                    dsThiSinh.add(thiSinh);
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
        return dsThiSinh;
    }
    
    public boolean checkMaSV(String idSinhVien) {
        Connection conn = null;
        try {
            conn = new ConnectDB().getConnect();
            if(conn != null) {
                String query = "Select COUNT(*) From SinhVien WHERE IdSinhVien = ?";
                PreparedStatement stmt  = conn.prepareStatement(query);
                stmt.setString(1, idSinhVien);
                ResultSet rs = stmt.executeQuery();
                if(rs.next()) {
                    int count = rs.getInt(1);
                    return (count > 0);
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
        return false;
    }
    
 }
