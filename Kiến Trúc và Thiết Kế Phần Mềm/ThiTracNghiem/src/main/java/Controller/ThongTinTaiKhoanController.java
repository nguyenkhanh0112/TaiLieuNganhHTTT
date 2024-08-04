/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.DiemThiModel;
import Model.ThiSinhModel;
import View.fThongTinTaiKhoan;
import com.mysql.cj.jdbc.Blob;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Khoahihi79
 */
public class ThongTinTaiKhoanController {
    private fThongTinTaiKhoan form;
    private ThiSinhModel thiSinhModel;
    private String username;
    private DefaultTableModel tableModelKqThi;

    public ThongTinTaiKhoanController(fThongTinTaiKhoan form, ThiSinhModel thiSinhModel, String username) {
        this.username = username;
        this.form = form;
        this.thiSinhModel = thiSinhModel;
        createTableModelKqThi();
        showThongTinSv(username);
        showKetQuaThi();
        upLoadFile();
        updateImage();
    }
    
    private void createTableModelKqThi() {
        tableModelKqThi = new DefaultTableModel();
        tableModelKqThi.addColumn("Ngày thi");
        tableModelKqThi.addColumn("Môn thi");
        tableModelKqThi.addColumn("Điểm");
    }
    
    private void showThongTinSv(String username) {
        Object[] infos = thiSinhModel.showThongTinSinhVien(username);
        if(infos != null) {
            form.setTextFieldMaSV(infos[0].toString());
            form.setTextFieldHoTen(infos[1].toString());
            form.setTextFieldGioiTinh(infos[4].toString());
            form.setTextFieldNgaySinh(infos[3].toString());
            form.setTextFieldLop(infos[2].toString());
            form.setTextFieldDiaChi(infos[5].toString());
            form.setTextFieldSDT(infos[6].toString());
             // Ép kiểu và hiển thị ảnh
            byte[] imageBytes = (byte[])infos[7];
            if(imageBytes != null) {
                form.setImage(imageBytes);
            }
            
        }
    }
    
    private void showKetQuaThi() {
        List<Object[]> dsKetQua = new DiemThiModel().showKetQuaThi(username);
        if(dsKetQua != null) {
            JTable tableKetQua = form.getTableKQThi();
            for(Object[] diem : dsKetQua) {
                double diemValue = ((Double)diem[2]).doubleValue();
                String diemFormat = String.format("%.1f", diemValue);
                diem[2] = diemFormat;
                tableModelKqThi.addRow(diem);
            }
            tableKetQua.setModel(tableModelKqThi);
        }
    }
    
    private void upLoadFile() {
        form.getButtonChoose().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser file_upload = new JFileChooser();
                int result = file_upload.showOpenDialog(null);
                if(result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = file_upload.getSelectedFile();
                    showImage(selectedFile);
                }
            }
        });
    }
    
    private void showImage(File file) {
        try {
            ImageIcon imageIcon = new ImageIcon(file.getAbsolutePath());
            Image scaledImage = imageIcon.getImage().getScaledInstance(form.getLabelImage().getWidth(), form.getLabelImage().getHeight(), Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            form.getLabelImage().setIcon(scaledIcon);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void updateImage() {
        form.getButtonUpdate().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // String idSinhVien = form.getTextFieldMaSv();
                Icon icon = form.getLabelImage().getIcon();
                if (icon != null) {
                    try {
                        BufferedImage bufferedImage = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
                        form.getLabelImage().paint(bufferedImage.getGraphics());
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
                        byte[] imageBytes = byteArrayOutputStream.toByteArray();
                        if(thiSinhModel.updateImage(imageBytes, username)) {
                            JOptionPane.showMessageDialog(form, "Cập nhật ảnh thành công !");
                        } else {
                            JOptionPane.showMessageDialog(form, "Cập nhật ảnh không thành công !");
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(form, "Lỗi khi chuyển đổi ảnh !");
                    }
                } else {
                    JOptionPane.showMessageDialog(form, "Vui lòng chọn ảnh trước khi cập nhật !");
                }
            }
        });
    }
}
