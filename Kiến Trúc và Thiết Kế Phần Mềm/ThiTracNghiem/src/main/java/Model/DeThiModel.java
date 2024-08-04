package Model;

import java.util.List;
import Connection.ConnectDB;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
/**
 *
 * @author Khoahihi79
 */
public class DeThiModel {
    
    public DeThiModel() {}
    
    public Object[] getDeThiByMaDe(String idDeThi) {
        Connection conn = null;
        Object[] deThi = null;
        try {
            conn = new ConnectDB().getConnect();
            if(conn != null) {
                String query = "Select TenDeThi, DiemToiDa, SoLuongCauHoi, ThoiGianThi from DETHI where IdDeThi = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, idDeThi);
                ResultSet rs = stmt.executeQuery();
                while(rs.next()) {
                        deThi = new Object[] {
                        rs.getString("TenDeThi"),
                        rs.getInt("DiemToiDa"),
                        rs.getInt("SoLuongCauHoi"),
                        rs.getInt("ThoiGianThi")
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
        return deThi;
    }
    
    public List<Integer> getListCauHoi(String idDeThi) {
        Connection conn = null;
        List<Integer> listCauHoi = new ArrayList<>();
        try {
            conn = new ConnectDB().getConnect();
            if(conn != null) {
                String query = "Select IdCauHoi From ChiTietDeThi WHERE IdDeThi = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, idDeThi);
                ResultSet rs = stmt.executeQuery();
                while(rs.next()) {
                    int cauHoi = rs.getInt(1);
                    listCauHoi.add(cauHoi);
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
    public List<Object[]> getDeThiByMonThi(String idMonThi) {
        Connection conn = null;
        List<Object[]> listDeThi = new ArrayList<>();
        try {
            conn = new ConnectDB().getConnect();
            if(conn != null) {
                String query = "Call display_dethi(?)";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, idMonThi);
                ResultSet rs = stmt.executeQuery();
                while(rs.next()) {
                    Object[] deThi = new Object[] {
                        rs.getString("IdDeThi"),
                        rs.getString("TenDeThi"),
                        rs.getDate("NgayTao"),
                        rs.getInt("DiemToiDa"),
                        rs.getString("TenMonThi"),
                        rs.getInt("SoLuongCauHoi"),
                        rs.getInt("ThoiGianThi")
                    };
                    listDeThi.add(deThi);
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
        return listDeThi;
    }
    
    //Hiển thị các câu hỏi trong 1 đề thi
    public List<Object[]> showQuestionsInExam(String idDeThi) {
        Connection conn = null;
        List<Object[]> listCauHoi = new ArrayList<>();
        try {
            conn = new ConnectDB().getConnect();
            if(conn != null) {
                String query = "Call hienthi_question(?)";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, idDeThi);
                ResultSet rs = stmt.executeQuery();
                while(rs.next()) {
                    Object[] cauHoi = new Object[] {
                        rs.getInt("IdCauHoi"),
                        rs.getString("CauHoi"),
                        rs.getString("Dap_An_A"),
                        rs.getString("Dap_An_B"),
                        rs.getString("Dap_An_C"),
                        rs.getString("Dap_An_D"),
                        rs.getString("DapAnDung"),
                        rs.getString("TenMucDo"),
                        rs.getString("TenChuong")
                    };
                    listCauHoi.add(cauHoi);
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
    
    public boolean addDeThi(String idDeThi, String tenDeThi, Date ngayTao, int diemToiDa, String idMonThi, int soLuongCau, int thoiGianThi) {
        Connection conn = null;
        try {
            conn = new ConnectDB().getConnect();
            if(conn != null) {
                String query = "Call add_dethi(?,?,?,?,?,?,?)";
                PreparedStatement stmt  = conn.prepareStatement(query);
                stmt.setString(1, idDeThi);
                stmt.setString(2, tenDeThi);
                stmt.setDate(3, ngayTao);
                stmt.setInt(4, diemToiDa);
                stmt.setString(5, idMonThi);
                stmt.setInt(6, soLuongCau);
                stmt.setInt(7, thoiGianThi);
                if(!check_made(idDeThi)) {
                    stmt.executeUpdate();
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
    
    public boolean updateDeThi(String idDeThi, String tenDeThi, Date ngayTao, int diemToiDa, String idMonThi, int soLuongCau, int thoiGianThi) {
        Connection conn = null;
        try {
            conn = new ConnectDB().getConnect();
            if(conn != null) {
                String query = "Call update_dethi(?,?,?,?,?,?,?)";
                PreparedStatement stmt  = conn.prepareStatement(query);
                stmt.setString(1, idDeThi);
                stmt.setString(2, tenDeThi);
                stmt.setDate(3, ngayTao);
                stmt.setInt(4, diemToiDa);
                stmt.setString(5, idMonThi);
                stmt.setInt(6, soLuongCau);
                stmt.setInt(7, thoiGianThi);
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
    
    public boolean deleteDeThi(String idDeThi) {
        Connection conn = null;
        try {
            conn = new ConnectDB().getConnect();
            if(conn != null) {
                String query = "DELETE FROM DeThi WHERE IdDeThi = ?";
                PreparedStatement stmt  = conn.prepareStatement(query);
                stmt.setString(1, idDeThi);
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
    
    public boolean check_made(String idDeThi) {
        Connection conn = null;
        try {
            conn = new ConnectDB().getConnect();
            if(conn != null) {
                String query = "Select COUNT(*) From DeThi WHERE IdDeThi = ?";
                PreparedStatement stmt  = conn.prepareStatement(query);
                stmt.setString(1, idDeThi);
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
    
    public List<Object[]> searchDeThi(String idDeThi, String tenDeThi) {
        Connection conn = null;
        List<Object[]> deThi = new ArrayList<>();
        try {
            conn = new ConnectDB().getConnect();
            if(conn != null) {
                String query = "Call search_dethi(?,?)";
                PreparedStatement stmt  = conn.prepareStatement(query);
                stmt.setString(1, idDeThi);
                stmt.setString(2,tenDeThi);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Object[] row = new Object[]{
                        rs.getString("IdDeThi"),
                        rs.getString("TenDeThi"),
                        rs.getDate("NgayTao"),
                        rs.getInt("DiemToiDa"),
                        rs.getString("TenMonThi"),
                        rs.getInt("SoLuongCauHoi"),
                        rs.getInt("ThoiGianThi")
                    };
                    deThi.add(row);
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
        return deThi;
    }
    
    public void addChiTietDeThi(String idDeThi, int idCauHoi) {
        Connection conn = null;
        try {
            conn = new ConnectDB().getConnect();
            if(conn != null) {
                String query = "INSERT INTO ChiTietDeThi VALUES(?,?)";
                PreparedStatement stmt  = conn.prepareStatement(query);
                stmt.setString(1, idDeThi);
                stmt.setInt(2, idCauHoi);
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
    
    public boolean deleteChiTietDeThi(String idDeThi) {
        Connection conn = null;
        try {
            conn = new ConnectDB().getConnect();
            if(conn != null) {
                String query = "DELETE FROM ChiTietDeThi WHERE IdDeThi = ?";
                PreparedStatement stmt  = conn.prepareStatement(query);
                stmt.setString(1, idDeThi);
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
