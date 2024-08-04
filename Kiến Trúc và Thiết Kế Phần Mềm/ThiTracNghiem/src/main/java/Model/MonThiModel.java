package Model;

import Connection.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MonThiModel {
    
    public MonThiModel() {}

    public List<Object[]> getListMonThi() {
        Connection conn = null;
        List<Object[]> listMonThi = new ArrayList<>();
        try {
            conn = new ConnectDB().getConnect();
            if(conn != null) {
                String query = "Select * From MonThi";
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();
                while(rs.next()) {
                    Object[] monThi = {
                        rs.getString("IdMonThi"),
                        rs.getString("TenMonThi")
                    };
                    listMonThi.add(monThi);
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
        return listMonThi;
    }
    
    public boolean addMonThi(String idMonThi, String tenMonThi) {
        Connection conn = null;
        try {
            conn = new ConnectDB().getConnect();
            if(conn != null) {
                String query = "Call Them_MonThi(?,?)";
                PreparedStatement stmt  = conn.prepareStatement(query);
                stmt.setString(1, idMonThi);
                stmt.setString(2, tenMonThi);
                if(!check_monthi(idMonThi)) {
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
    
    public boolean check_monthi(String idMonThi) {
        Connection conn = null;
        try {
            conn = new ConnectDB().getConnect();
            if(conn != null) {
                String query = "Select COUNT(*) From MonThi WHERE IdMonThi = ?";
                PreparedStatement stmt  = conn.prepareStatement(query);
                stmt.setString(1, idMonThi);
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
    
    public boolean updateMonThi(String idMonThi, String tenMonThi) {
        Connection conn = null;
        try {
            conn = new ConnectDB().getConnect();
            if(conn != null) {
                String query = "Call Sua_MonThi(?,?)";
                PreparedStatement stmt  = conn.prepareStatement(query);
                stmt.setString(1, idMonThi);
                stmt.setString(2, tenMonThi);
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
    
    public boolean deleteMonThi(String idMonThi) {
        Connection conn = null;
        try {
            conn = new ConnectDB().getConnect();
            if(conn != null) {
                String query = "Call Xoa_MonThi(?)";
                PreparedStatement stmt  = conn.prepareStatement(query);
                stmt.setString(1, idMonThi);
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
    
    //Lấy IdMonThi dựa vào TenMonThi
    public String getIdByName(String tenMonThi) {
        Connection conn = null;
        String idMonThi = "";
        try {
            conn = new ConnectDB().getConnect();
            if(conn != null) {
                String query = "Select IdMonThi From MonThi WHERE TenMonThi = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, tenMonThi);
                ResultSet rs = stmt.executeQuery();
                while(rs.next()) {
                    idMonThi = rs.getString(1);
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
     return idMonThi;
    }
    
    public List<String> getAllMonThi() {
        Connection conn = null;
        List<String> listMonThi = new ArrayList<>();
        try {
            conn = new ConnectDB().getConnect();
            if(conn != null) {
                String query = "Select TenMonThi From MonThi";
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();
                while(rs.next()) {
                    String tenMonThi = rs.getString(1);
                    listMonThi.add(tenMonThi);
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
        return listMonThi;
    }
}

