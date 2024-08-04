/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.DeThiModel;
import Model.LichThiModel;
import Model.MonThiModel;
import Model.ThiSinhModel;
import View.fBaiThi;
import View.fGiangVien;
import View.fSinhVien;
import View.fThi;
import com.mysql.cj.jdbc.Blob;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author Khoahihi79
 */
public class BaiThiController {
    private fBaiThi form;
    private DeThiModel deThiModel;
    private String username;
    private String maDeThi;

    public BaiThiController() {}
    
    public BaiThiController(fBaiThi form, DeThiModel deThiModel, String username) {
        this.username = username;
        this.form = form;
        this.deThiModel = deThiModel;
        showComboBox();
        showThongTinSv();
        comboBox_monthi_selectedValue();
        button_vaothi_actionPerformed();
    }
    
     private void showComboBox() {
        //combobox monthi
        List<String> listMonThi = new MonThiModel().getAllMonThi();
        JComboBox comboBoxMonThi = form.getComboBoxMonThi();
        if(listMonThi != null) {
            for(String monthi: listMonThi) {
                comboBoxMonThi.addItem(monthi);
            }
        }
     }
     
    public void showThongTinSv() {
        Object[] infos = new ThiSinhModel().showThongTinSinhVien(username);
        if(infos != null) {
            String tensv = infos[0].toString();
            form.setTextFieldMaSV(tensv);
            form.setTextFieldTenSV(infos[1].toString());
            form.setTextFieldLop(infos[2].toString());
             // Ép kiểu và hiển thị ảnh
            if(form.getJLabelImage().getIcon() != null) {
                form.setImage(null);
            }
            byte[] imageBytes = (byte[])infos[7];
            if(imageBytes != null) {
                form.setImage(imageBytes);
            }
        }
    }
   
    public void comboBox_monthi_selectedValue() {
        form.getComboBoxMonThi().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED) {
                   String idMonThi = new MonThiModel().getIdByName((String)form.getComboBoxMonThi().getSelectedItem().toString());
                   List<String> danhSachIdDeThi = danhSachIdDeThi(idMonThi);
                   maDeThi = randomDeThi(danhSachIdDeThi);
                }
            }
        });
    }
    
    public List<String> danhSachIdDeThi(String idMonThi) {
        List<String> listIdDeThi = new ArrayList<>();
        List<Object[]> listDeThi = deThiModel.getDeThiByMonThi(idMonThi);
        if(listDeThi != null) {
            for(Object[] deThi : listDeThi) {
                listIdDeThi.add(deThi[0].toString());
            }
        }
        return listIdDeThi;
    }
    
    public String randomDeThi(List<String> danhSachDeThi) {
        if(danhSachDeThi == null) {
            return null;
        }
        Random random = new Random();
        int index = random.nextInt(danhSachDeThi.size());
        return danhSachDeThi.get(index);
    }
    
    private void button_vaothi_actionPerformed() {
        form.getButtonStart().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {  
               String tenMonThi = (String)form.getComboBoxMonThi().getSelectedItem().toString();
               String idMonThi = new MonThiModel().getIdByName(tenMonThi);
               List<Object[]> dsLichThi = new LichThiModel().getLichThiByMonThi(idMonThi);
               if(dsLichThi != null) {
                   Object[] lichThi = dsLichThi.get(0);
                   
                   LocalDate ngayThi = ((java.sql.Date) lichThi[2]).toLocalDate();

                    // Lấy ra phần giờ từ giờ bắt đầu và giờ kết thúc
                    LocalTime gioBatDau = ((java.sql.Time) lichThi[3]).toLocalTime();
                    LocalTime gioKetThuc = ((java.sql.Time) lichThi[4]).toLocalTime();

                    // Lấy ngày hiện tại
                    LocalDate ngayHienTai = LocalDate.now();
                    // Lấy giờ hiện tại
                    LocalTime gioHienTai = LocalTime.now();
                   
                   if(ngayHienTai.compareTo(ngayThi) < 0) {
                       JOptionPane.showMessageDialog(form, "Chưa đến ngày thi!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                   } else if (ngayHienTai.compareTo(ngayThi) > 0) {
                      JOptionPane.showMessageDialog(form, "Đã quá ngày làm bài thi. Vui lòng thi lại đợt khác!", "Thông báo", JOptionPane.INFORMATION_MESSAGE); 
                   } else {
                       if(gioHienTai.compareTo(gioBatDau) < 0) {
                            JOptionPane.showMessageDialog(form, "Chưa đến giờ thi! Vui lòng quay lại vào " + gioBatDau.toString(), "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                       } else if (gioHienTai.compareTo(gioKetThuc) > 0) {
                            JOptionPane.showMessageDialog(form, "Đã hết giờ làm bài thi trong hôm nay!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                       } else {
                    
                           fThi formThi = new fThi(username, idMonThi , tenMonThi, maDeThi);
                           formThi.setVisible(true);
                       }
                   }
               }

            }
        });
    }
}
