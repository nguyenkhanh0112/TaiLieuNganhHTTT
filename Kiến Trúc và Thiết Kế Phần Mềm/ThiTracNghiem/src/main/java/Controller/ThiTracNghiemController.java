/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.CauHoiModel;
import Model.DeThiModel;
import Model.DiemThiModel;
import Model.LopModel;
import Model.ThiSinhModel;
import View.fKQ;
import View.fThi;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import jdk.jfr.Timespan;

/**
 *
 * @author Khoahihi79
 */
public class ThiTracNghiemController {
    private fThi form;
    private String username;
    private String idMonThi;
    private String tenMonThi;
    private String maDeThi;
    private double score;
    private int remainingTime = 0;
    private Timer countdownTimer;
    private int selectedIndex = 0;
    private boolean isLoaded = false;
    private DefaultTableModel tableModelDapAn;

    public ThiTracNghiemController() {}
    
    public ThiTracNghiemController(fThi form, String username, String idMonThi, String tenMonThi, String maDeThi) {
        this.form = form;
        this.username = username;
        this.idMonThi = idMonThi;
        this.tenMonThi = tenMonThi;
        this.maDeThi = maDeThi;
        showThongTinDeThi();
        showThongTinThiSinh();
        convertToSecond();
        showDanhSachCauHoi();
        isLoaded = true;
        jList_cauhoi_ListSelectionListener();
        button_previous_actionPerformed();
        button_next_actionPerformed();
        button_submit_actionPerformer();
    }
    
    private void taoBangDapLuuDapAn() {
        tableModelDapAn = new DefaultTableModel();
        tableModelDapAn.addColumn("QuestionIndex");
        tableModelDapAn.addColumn("DapAnDung");
        tableModelDapAn.addColumn("selectedAnswer");
    }
    
    private void showThongTinDeThi() {
        Object[] deThi = new DeThiModel().getDeThiByMaDe(maDeThi);
        if(deThi != null) {
            form.setTextLabelDeThi(deThi[0].toString());
            form.setTextLabelDiem(deThi[1].toString());
            form.setTextLabelSoLgCauHoi(deThi[2].toString());
            form.setTextLabelTimeLamBai(deThi[3].toString());
            form.setTextLabelMaDe(maDeThi);
            form.setTextLabelMonThi(tenMonThi);
        }
    }
    
    private void showThongTinThiSinh() {
        Object[] thiSinh = new ThiSinhModel().showThongTinSinhVien(username);
        if(thiSinh != null) {
            form.setTextLabelMaSv(thiSinh[0].toString());
            form.setTextLabelHoTen(thiSinh[1].toString());
            form.setTextLabelLop(thiSinh[2].toString());
            byte[] imageData = (byte[])thiSinh[7];
            if(imageData != null) {
                form.setLabelImage(imageData);
            }
        }
    }
    
    private void jList_cauhoi_ListSelectionListener() {
        form.getJListCauHoi().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!e.getValueIsAdjusting()) {
                    if(isLoaded) {
                    showChiTietCauHoi();
                    }
                }
            }
        });
    }
    
    private void showDanhSachCauHoi() {
        List<Object[]> listCauHoi = new CauHoiModel().danhSachCauHoi1(maDeThi);
        DefaultListModel<String> model = new DefaultListModel();
        if(tableModelDapAn == null) {
            taoBangDapLuuDapAn();
        }
        for(Object[] cauHoi : listCauHoi) {
            String questionIndex = (String)cauHoi[0];
            model.addElement(questionIndex);
            tableModelDapAn.addRow(cauHoi);
        }

        form.getJListCauHoi().setModel(model);
        selectedIndex = 0;
        form.getJListCauHoi().setSelectedIndex(selectedIndex);
        showChiTietCauHoi();
        
    }
    
    private void showChiTietCauHoi() {

        if(selectedIndex >= 0 && selectedIndex < (form.getJListCauHoi().getModel().getSize() - 1)) {
            tableModelDapAn.setValueAt(luuDapAnDaChon(), selectedIndex, 2);                 
        }
        
        selectedIndex = form.getJListCauHoi().getSelectedIndex();
                 
        if(selectedIndex < (form.getJListCauHoi().getModel().getSize() - 1)) {
            form.getButtonNext().setEnabled(true);
        } else {
            form.getButtonNext().setEnabled(false);
        }
        
        if(selectedIndex > 0) {
            form.getButtonPrevious().setEnabled(true);
        } else {
            form.getButtonPrevious().setEnabled(false);
        }
        
        int idCauHoi = getIdCauHoi();
        Object tenCau = form.getJListCauHoi().getSelectedValue();
        if(tenCau != null) {
            form.setTextLabelSttCauHoi(tenCau.toString());
        }
        Object[] cauHoi = new CauHoiModel().chiTietCauHoi(idCauHoi);
        if(cauHoi != null) {
            form.setTextAreaCauHoi(cauHoi[0].toString());
            form.getRadioButtonDaA().setText("A. " + cauHoi[1].toString());
            form.getRadioButtonDaB().setText("B. " + cauHoi[2].toString());
            form.getRadioButtonDaC().setText("C. " + cauHoi[3].toString());
            form.getRadioButtonDaD().setText("D. " + cauHoi[4].toString());
        }
        if(form.getButtonGroup().getSelection() != null) {
            form.getButtonGroup().clearSelection();
        }
        hienThiDapAnDaChon();
    }
    
    private Integer getIdCauHoi() {
        int idCauHoi = 0;
        selectedIndex = form.getJListCauHoi().getSelectedIndex();
        List<Object[]> listCauHoi = new CauHoiModel().danhSachCauHoi(maDeThi);
        if(selectedIndex != -1) {
            Object[] selectedCauHoi = listCauHoi.get(selectedIndex);
            idCauHoi = Integer.parseInt(selectedCauHoi[1].toString());
        }
        return idCauHoi;
    }
    
    private void button_previous_actionPerformed() {
        form.getButtonPrevious().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(selectedIndex > 0) {
                    form.getJListCauHoi().setSelectedIndex(selectedIndex - 1);
                }
            }       
        });
    }
    
    private void button_next_actionPerformed() {
        form.getButtonNext().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(selectedIndex < (form.getJListCauHoi().getModel().getSize() - 1)) {
                    form.getJListCauHoi().setSelectedIndex(selectedIndex + 1);
                }
            }       
        });
    }
    
    private void hienThiDapAnDaChon() {
         if (tableModelDapAn != null && selectedIndex >= 0 && selectedIndex < tableModelDapAn.getRowCount()) {
            Object answerSelectedObj = tableModelDapAn.getValueAt(selectedIndex, 2);
            if (answerSelectedObj != null) {
                String answerSelected = answerSelectedObj.toString();
                if(form.getRadioButtonDaA().getText().equals(answerSelected)) {
                form.getRadioButtonDaA().setSelected(true);
                }

             if(form.getRadioButtonDaB().getText().equals(answerSelected)) {
                form.getRadioButtonDaB().setSelected(true);
                }

             if(form.getRadioButtonDaC().getText().equals(answerSelected)) {
                form.getRadioButtonDaC().setSelected(true);
                }

             if(form.getRadioButtonDaD().getText().equals(answerSelected)) {
                form.getRadioButtonDaD().setSelected(true);
                }
            }
        }
    }
    
    private String luuDapAnDaChon() {
        String result = "";
        if(form.getRadioButtonDaA().isSelected() == true) {
            result = form.getRadioButtonDaA().getText();
        } 
        
        if(form.getRadioButtonDaB().isSelected() == true) {
            result = form.getRadioButtonDaB().getText();
        }
        
        if(form.getRadioButtonDaC().isSelected() == true) {
            result = form.getRadioButtonDaC().getText();
        }
        
        if(form.getRadioButtonDaD().isSelected() == true) {
            result = form.getRadioButtonDaD().getText();
        }
        return result;
    }
    
    private String message = "";
    
    private void button_submit_actionPerformer() {
        form.getButtonNopBai().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableModelDapAn.setValueAt(luuDapAnDaChon(), selectedIndex, 2);
                
                int correctAnswer = 0;
                String correctAnswers = "";
                String wrongAnswers = "";
                double scoreOneAnswer = Double.parseDouble(form.getTextLabelDiem()) / Double.parseDouble(form.getTextLabelSLCH());
                if(JOptionPane.showConfirmDialog(form, "Xác nhận nộp bài!", "Thông Báo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    for(int i = 0; i < tableModelDapAn.getRowCount(); i++) {
                        Object objectQuestionIndex = tableModelDapAn.getValueAt(i, 0);
                        Object objectDapAnDung = tableModelDapAn.getValueAt(i, 1);
                        Object objectSelectedAnswer = tableModelDapAn.getValueAt(i, 2);
                        
                        String questionIndex = objectQuestionIndex.toString();
                        String dapAnDung = objectDapAnDung.toString();
                        
                        if(objectSelectedAnswer != null) {
                            String selectedAnswer = objectSelectedAnswer.toString();
                            char firstCharacter = selectedAnswer.charAt(0);
                            
                            selectedAnswer = (selectedAnswer.length() > 0) ? selectedAnswer.substring(3) : selectedAnswer;
                            if(selectedAnswer.equals(dapAnDung)) {
                                correctAnswer++;
                                correctAnswers += questionIndex + "." + firstCharacter + ", ";
                            } else {
                                wrongAnswers += questionIndex + "." + firstCharacter + ", ";
                            }
                        }
                        score = (double)correctAnswer * scoreOneAnswer;
                    }  
                    
                    if (correctAnswers.length() > 0) {
                        correctAnswers = correctAnswers.substring(0, correctAnswers.length() - 2); 
                    }
                    if (wrongAnswers.length() > 0) {
                        wrongAnswers = wrongAnswers.substring(0, wrongAnswers.length() - 2); 
                    }
                    
                    message = "Câu trả lời đúng: " + correctAnswers + "\n" + "Câu trả lời sau: " + wrongAnswers;
                    
                    form.getRadioButtonDaA().setEnabled(false);
                    form.getRadioButtonDaB().setEnabled(false);
                    form.getRadioButtonDaC().setEnabled(false);
                    form.getRadioButtonDaD().setEnabled(false);
                    countdownTimer.stop();
                    form.getButtonNopBai().setEnabled(false);
                    LocalDate ngayThi = LocalDate.now();
                    String idLop = new LopModel().getIdByName(form.getTextLabelLop());
                    new DiemThiModel().luuKetQuaThi(username, idMonThi, Date.valueOf(ngayThi), score, idLop, maDeThi);
                    
                    fKQ formKQ = new fKQ(username, tenMonThi, score, message);
                    form.setVisible(false);
                    formKQ.setVisible(true);
                }
            }          
        });
    }
    
    
    //Đếm ngược time
    private String formatTime(int timeSenconds) {
        int hour = timeSenconds / 3600;
        int minute = (timeSenconds % 3600) / 60;
        int second = timeSenconds % 60;
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }
    
    private void convertToSecond() {
        int totalTime = Integer.parseInt(form.getTextLabelTimeLamBai());
        remainingTime = totalTime * 60;
        form.setTextLabelCountDown("Thời gian làm bài: " + formatTime(remainingTime));
        countdownTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remainingTime--;
                if(remainingTime >= 0) {
                    form.setTextLabelCountDown("Thời gian làm bài: " + formatTime(remainingTime));
                } else {
                    countdownTimer.stop();
                    form.setTextLabelCountDown("Hết thời gian!");
                    form.getRadioButtonDaA().setEnabled(false);
                    form.getRadioButtonDaB().setEnabled(false);
                    form.getRadioButtonDaC().setEnabled(false);
                    form.getRadioButtonDaD().setEnabled(false);
                }
            }
        });
        countdownTimer.start();
    }
    //
    
    
}
