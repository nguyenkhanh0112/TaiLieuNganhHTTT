/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.ThiSinhModel;
import View.fKQ;
import View.fSinhVien;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Khoahihi79
 */
public class KetQuaController {
    private fKQ form;
    private String username;
    private String tenMonThi;
    private double score;
    private String message;

    public KetQuaController(fKQ form, String username, String tenMonThi, double score, String message) {
        this.form = form;
        this.username = username;
        this.tenMonThi = tenMonThi;
        this.score = score;
        this.message = message;
        loadKetQuaSV();
        button_back_actionPerformed();
    }
    
    private void loadKetQuaSV() {
        Object[] ttSV = new ThiSinhModel().showThongTinSinhVien(username);
        if(ttSV != null) {
            form.setTextFieldMaSV(ttSV[0].toString());
            form.setTextFieldHoTen(ttSV[1].toString());
            form.setTextFieldLop(ttSV[2].toString());
        }
        form.setTextFieldMonThi(tenMonThi);
        form.setTextFieldDiem(String.format("%.1f", score));
        form.setTextAreaDapAn(message);
    }
    
    private void button_back_actionPerformed() {
        form.getButtonBack().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                form.setVisible(false);
                fSinhVien fSV = new fSinhVien(username);
                fSV.setVisible(true);
            }
        });
    }
    
    
}
