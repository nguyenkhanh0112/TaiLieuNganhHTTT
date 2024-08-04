/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Connection.ConnectDB;
import com.mysql.cj.xdevapi.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
public class CauHoiModel {
    
    public CauHoiModel() {}
    
    public List<Object[]> danhSachCauHoi(String maDeThi) {
        Connection conn = null;
        List<Object[]> listCauHoi = new ArrayList<>();
        try {
            conn = new ConnectDB().getConnect();
            if (conn != null) {
                String query = "Call hienthi_question2(?)";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, maDeThi);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Object[] row = new Object[]{
                        rs.getString("QuestionIndex"),
                        rs.getInt("IdCauHoi"),
                        rs.getString("CauHoi"),
                        rs.getString("Dap_An_A"),
                        rs.getString("Dap_An_B"),
                        rs.getString("Dap_An_C"),
                        rs.getString("Dap_An_D"),
                        rs.getString("DapAnDung"),

                    };
                    listCauHoi.add(row);
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
        return listCauHoi;
    }
    
    public List<Object[]> danhSachCauHoi1(String maDeThi) {
        Connection conn = null;
        List<Object[]> listCauHoi = new ArrayList<>();
        try {
            conn = new ConnectDB().getConnect();
            if (conn != null) {
                String query = "Call hienthi_question2(?)";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, maDeThi);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Object[] row = new Object[]{
                        rs.getString("QuestionIndex"),
                        rs.getString("DapAnDung"),

                    };
                    listCauHoi.add(row);
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
        return listCauHoi;
    }
    
    public Object[] chiTietCauHoi(int idCauHoi) {
        Connection conn = null;
        Object[] cauHoi = null;
        try {
            conn = new ConnectDB().getConnect();
            if (conn != null) {
                String query = "Call displayQuestionDetail(?)";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setInt(1, idCauHoi);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                        cauHoi = new Object[]{
                        rs.getString("CauHoi"),
                        rs.getString("Dap_An_A"),
                        rs.getString("Dap_An_B"),
                        rs.getString("Dap_An_C"),
                        rs.getString("Dap_An_D"),
                    };
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
        return cauHoi;
    }
    
    public List<Object[]> getAllCauHoi() { 
        Connection conn = null;
        List<Object[]> rows = new ArrayList<>();
        try {
            conn = new ConnectDB().getConnect();
            if (conn != null) {
                String query = "Call hienthi_cauhoi()";
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Object[] row = new Object[]{
                        rs.getInt("IdCauHoi"),
                        rs.getString("CauHoi"),
                        rs.getString("Dap_An_A"),
                        rs.getString("Dap_An_B"),
                        rs.getString("Dap_An_C"),
                        rs.getString("Dap_An_D"),
                        rs.getString("DapAnDung"),
                        rs.getString("TenMonThi"),
                        rs.getString("TenMucDo"),
                        rs.getString("TenChuong")
                    };
                    rows.add(row);
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
       return rows;
    }   
    
    public List<Object[]> getAllCauHoiTheoMon(String idMonThi) {
        Connection conn = null;
        List<Object[]> rows = new ArrayList<>();
        try {
            conn = new ConnectDB().getConnect();
            if (conn != null) {
                String query = "Call hienthi_cauhoi_1(?)";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, idMonThi);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Object[] row = new Object[]{
                        rs.getInt("IdCauHoi"),
                        rs.getString("CauHoi"),
                        rs.getString("Dap_An_A"),
                        rs.getString("Dap_An_B"),
                        rs.getString("Dap_An_C"),
                        rs.getString("Dap_An_D"),
                        rs.getString("DapAnDung"),
                        rs.getString("TenMonThi"),
                        rs.getString("TenMucDo"),
                        rs.getString("TenChuong")
                    };
                    rows.add(row);
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
        return rows;
    }
    
    public List<Object[]> getAllCauHoiTheoChuong(String idChuong, String idMonThi) {
        Connection conn = null;
        List<Object[]> rows = new ArrayList<>();
        try {
            conn = new ConnectDB().getConnect();
            if (conn != null) {
                String query = "Call hienthi_cauhoi_3(?, ?)";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, idChuong);
                stmt.setString(2, idMonThi);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Object[] row = new Object[]{
                        rs.getInt("IdCauHoi"),
                        rs.getString("CauHoi"),
                        rs.getString("Dap_An_A"),
                        rs.getString("Dap_An_B"),
                        rs.getString("Dap_An_C"),
                        rs.getString("Dap_An_D"),
                        rs.getString("DapAnDung"),
                        rs.getString("TenMonThi"),
                        rs.getString("TenMucDo"),
                        rs.getString("TenChuong")
                    };
                    rows.add(row);
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
        return rows;
    }
    
    public List<Object[]> getAllCauHoiTheoChuongVaMucDo(String idChuong, String idMonThi, String idMucDo) {
        Connection conn = null;
        List<Object[]> rows = new ArrayList<>();
        try {
            conn = new ConnectDB().getConnect();
            if (conn != null) {
                String query = "Call hienthi_cauhoi_4(?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, idChuong);
                stmt.setString(2, idMonThi);
                stmt.setString(3, idMucDo);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Object[] row = new Object[]{
                        rs.getInt("IdCauHoi"),
                        rs.getString("CauHoi"),
                        rs.getString("Dap_An_A"),
                        rs.getString("Dap_An_B"),
                        rs.getString("Dap_An_C"),
                        rs.getString("Dap_An_D"),
                        rs.getString("DapAnDung"),
                        rs.getString("TenMonThi"),
                        rs.getString("TenMucDo"),
                        rs.getString("TenChuong")
                    };
                    rows.add(row);
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
        return rows;
    }
    
    public List<Object[]> getAllCauHoiTheoMucDo(String idMucDo, String idMonThi) {
        Connection conn = null;
        List<Object[]> rows = new ArrayList<>();
        try {
            conn = new ConnectDB().getConnect();
            if (conn != null) {
                String query = "Call hienthi_cauhoi_2(?, ?)";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, idMucDo);
                stmt.setString(2, idMonThi);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Object[] row = new Object[]{
                        rs.getInt("IdCauHoi"),
                        rs.getString("CauHoi"),
                        rs.getString("Dap_An_A"),
                        rs.getString("Dap_An_B"),
                        rs.getString("Dap_An_C"),
                        rs.getString("Dap_An_D"),
                        rs.getString("DapAnDung"),
                        rs.getString("TenMonThi"),
                        rs.getString("TenMucDo"),
                        rs.getString("TenChuong")
                    };
                    rows.add(row);
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
        return rows;
    }
    
    public boolean addCauHoi(String cauHoi, String dapAnA, String dapAnB, String dapAnC, String dapAnD, String dapAnDung, String idMonThi, String idMucDo, String idChuong) {
        Connection conn = null;
        try {
            conn = new ConnectDB().getConnect();
            if(conn != null) {
                String query = "Call up_cauhoii(?,?,?,?,?,?,?,?,?)";
                PreparedStatement stmt  = conn.prepareStatement(query);
                stmt.setString(1, cauHoi);
                stmt.setString(2, dapAnA);
                stmt.setString(3, dapAnB);
                stmt.setString(4, dapAnC);
                stmt.setString(5, dapAnD);
                stmt.setString(6, dapAnDung);
                stmt.setString(7, idMonThi);
                stmt.setString(8, idMucDo);
                stmt.setString(9, idChuong);
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
    
    public boolean check_ch(String cauhoi) {
        boolean result = false;
        Connection conn = null;
        try {
            conn = new ConnectDB().getConnect();
            if(conn != null) {
                String query = "Select COUNT(*) From CauHoi WHERE CauHoi = ?";
                PreparedStatement stmt  = conn.prepareStatement(query);
                stmt.setString(1, cauhoi);
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
    
    public boolean updateCauHoi(int idCauHoi, String cauHoi, String dapAnA, String dapAnB, String dapAnC, String dapAnD, String dapAnDung, String idMonThi, String idMucDo, String idChuong) {
        Connection conn = null;     
        try {
            conn = new ConnectDB().getConnect();
            if(conn != null) {
                String query = "Call update_CauHoii(?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement stmt  = conn.prepareStatement(query);
                stmt.setInt(1, idCauHoi);
                stmt.setString(2, cauHoi);
                stmt.setString(3, dapAnA);
                stmt.setString(4, dapAnB);
                stmt.setString(5, dapAnC);
                stmt.setString(6, dapAnD);
                stmt.setString(7, dapAnDung);
                stmt.setString(8, idMonThi);
                stmt.setString(9, idMucDo);
                stmt.setString(10, idChuong);
                int rows = stmt.executeUpdate();
                if(rows > 0) {
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
    
    public List<Object[]> searchCauHoi(String cauHoi) {
        Connection conn = null;
        List<Object[]> listCauHoi = new ArrayList<>();
        try {
            conn = new ConnectDB().getConnect();
            if(conn != null) {
                String query = "Call timkiem_CauHoii(?)";
                PreparedStatement stmt  = conn.prepareStatement(query);
                stmt.setString(1, cauHoi);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Object[] row = new Object[]{
                        rs.getInt("IdCauHoi"),
                        rs.getString("CauHoi"),
                        rs.getString("Dap_An_A"),
                        rs.getString("Dap_An_B"),
                        rs.getString("Dap_An_C"),
                        rs.getString("Dap_An_D"),
                        rs.getString("DapAnDung"),
                        rs.getString("TenMonThi"),
                        rs.getString("TenMucDo"),
                        rs.getString("TenChuong")
                    };
                    listCauHoi.add(row);
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
        return listCauHoi;
    } 
    
    public boolean deleteCauHoi(String cauHoi) {
        Connection conn = null;
        try {
            conn = new ConnectDB().getConnect();
            if(conn != null) {
                String query = "Call xoa_cauHoii(?)";
                PreparedStatement stmt  = conn.prepareStatement(query);
                stmt.setString(1, cauHoi);
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
}
