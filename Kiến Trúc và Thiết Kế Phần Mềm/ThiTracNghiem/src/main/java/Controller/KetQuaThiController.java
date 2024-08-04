/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.DiemThiModel;
import Model.LopModel;
import Model.MonThiModel;
import View.fKetQuaThi;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Khoahihi79
 */
public class KetQuaThiController {
    private fKetQuaThi form;
    private DiemThiModel diemThiModel;
    private DefaultTableModel tableModelDiem;

    public KetQuaThiController(fKetQuaThi form, DiemThiModel diemThiModel) {
        this.form = form;
        this.diemThiModel = diemThiModel;
        createTableModelDiem();
        showComboBoxLop();
        showComboBoxMonThi();
        showDsDiemByLopVaMonThi();
        showDsDiemByMonThi();
        searchDiemThi();
        xuatExcel();
    }
    
    private void createTableModelDiem() {
        tableModelDiem = new DefaultTableModel();
        tableModelDiem.addColumn("Mã sinh viên");
        tableModelDiem.addColumn("Họ tên");
        tableModelDiem.addColumn("Lớp");
        tableModelDiem.addColumn("Điểm");
        tableModelDiem.addColumn("Môn thi");
    }
    
    private void showComboBoxLop() {
        List<String> dsLop = new LopModel().getAllLop();
        if(dsLop != null)
            for(String lop : dsLop) {
              form.getComboBoxLop().addItem(lop);
            }
    }
    
    private void showComboBoxMonThi() {
        List<Object[]> listMonThi = new MonThiModel().getListMonThi();
        if(listMonThi != null) {
            for(Object[] monThi : listMonThi) {
                form.getComboBoxMonThi().addItem(monThi[1].toString());
            }
        }
    }
    
    private void showDsDiemByLopVaMonThi() {
        form.getComboBoxLop().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED) {
                    String idMonThi = new MonThiModel().getIdByName((String)form.getComboBoxMonThi().getSelectedItem().toString());
                    String idLop = new LopModel().getIdByName((String)form.getComboBoxLop().getSelectedItem().toString());
                    List<Object[]> dsDiem = diemThiModel.dsDiemTheoLopVaMon(idLop, idMonThi);
                    if(dsDiem != null) {
                        tableModelDiem.setRowCount(0);
                        JTable tableDiem = form.getTableDiem();
                        for(Object[] diem : dsDiem) {
                            double diemValue = ((Double)diem[3]).doubleValue(); 
                            String diemFormatted = String.format("%.1f", diemValue);
                            diem[3] = diemFormatted;
                            tableModelDiem.addRow(diem);
                        }
                        tableDiem.setModel(tableModelDiem);
                    }
                }
            }
        });
    }
    
    private void showDsDiemByMonThi() {
        form.getComboBoxMonThi().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED) {
                    String idMonThi = new MonThiModel().getIdByName((String)form.getComboBoxMonThi().getSelectedItem().toString());
                    List<Object[]> dsDiem = diemThiModel.DsDiemTheoMonThi(idMonThi);
                    if(dsDiem != null) {
                        tableModelDiem.setRowCount(0);
                        JTable tableDiem = form.getTableDiem();
                        for(Object[] diem : dsDiem) {
                            double diemValue = ((Double)diem[3]).doubleValue(); 
                            String diemFormatted = String.format("%.1f", diemValue);
                            diem[3] = diemFormatted;
                            tableModelDiem.addRow(diem);
                        }
                        tableDiem.setModel(tableModelDiem);
                    }
                }
            }
        });
    }
    
    private void searchDiemThi() {
        form.getButtonSearch().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idMonThi = new MonThiModel().getIdByName((String)form.getComboBoxMonThi().getSelectedItem().toString());
                String idLop = new LopModel().getIdByName((String)form.getComboBoxLop().getSelectedItem().toString());
                double diem1 = Double.parseDouble(form.getTextFieldDiem1());
                double diem2 = Double.parseDouble(form.getTextFieldDiem2());
                if(!(form.getTextFieldDiem1().equals("") || form.getTextFieldDiem2().equals(""))) {
                    if(diem1 >= 0 && diem2 <= 10) {
                        if(isValidTextFieldDiem(form.getTextFieldDiem1()) && isValidTextFieldDiem(form.getTextFieldDiem2())) {
                            List<Object[]> dsDiem = diemThiModel.timKiemDiem(idMonThi, idLop, diem1, diem2);
                                if(dsDiem != null) {
                                    tableModelDiem.setRowCount(0);
                                    JTable tableDiem = form.getTableDiem();
                                    for(Object[] diem : dsDiem) {
                                        double diemValue = ((Double)diem[3]).doubleValue(); 
                                        String diemFormatted = String.format("%.1f", diemValue);
                                        diem[3] = diemFormatted;
                                        tableModelDiem.addRow(diem);
                                    }
                                    tableDiem.setModel(tableModelDiem);
                                }
                        } else {
                            JOptionPane.showMessageDialog(form, "Vui lòng nhập số thực hợp lệ");
                        }
                    } else {
                        JOptionPane.showMessageDialog(form, "Vui lòng nhập điểm trong khoảng 0 đến 10");
                    }
                } else {
                    JOptionPane.showMessageDialog(form, "Vui lòng nhập điểm cần tìm");
                }
            }
        });
    }
    
    private boolean isValidTextFieldDiem(String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    private void xuatExcel() {
        form.getButtonExport().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                //Tạo luồng ghi dữ liệu vào file
                FileOutputStream excelFOU = null;
                BufferedOutputStream excelBOU = null;
                XSSFWorkbook excelJTableExporter = null;
                
                //Hộp thoại sẽ được mở tại thư mục ThiTracNghiem
                JFileChooser excelFile = new JFileChooser("C:\\Javaa\\NetBeansProjects\\ThiTracNghiem");
                excelFile.setDialogTitle("Save As");
                //chỉ cho phép hộp thoại hiển thị các tệp excel có phần mở rộng .xls, .xlsx, .xlxs
                FileNameExtensionFilter fnef = new FileNameExtensionFilter("Excel files", "xls", "xlsx", "xlxs");
                excelFile.setFileFilter(fnef);
                
                int excelChooser = excelFile.showSaveDialog(null);
                
                if(excelChooser == JFileChooser.APPROVE_OPTION) {
                    try {
                        //tạo ra đối tượng XSSFWorkbook đại diện cho 1 file excel
                        excelJTableExporter = new XSSFWorkbook();
                        // tạo 1 sheet trong workbook
                        XSSFSheet excelSheet = excelJTableExporter.createSheet(form.getComboBoxLop().getSelectedItem().toString());
                        //Tạo dòng tiêu đề
                        //format cho dòng tiêu đề
                        CellStyle cellStyle = excelSheet.getWorkbook().createCellStyle();
                        XSSFFont font = excelSheet.getWorkbook().createFont();
                        font.setBold(true);
                        font.setFontHeightInPoints((short) 14);
                        font.setColor(IndexedColors.RED.getIndex());
                        cellStyle.setFont(font);
                        
                        XSSFRow headerRow = excelSheet.createRow(0);
                        for(int j = 0;  j < tableModelDiem.getColumnCount(); j++) {
                            XSSFCell headerCell = headerRow.createCell(j);
                            headerCell.setCellValue(tableModelDiem.getColumnName(j));
                            headerCell.setCellStyle(cellStyle);
                        }
                        //lặp qua các hàng và cột của tableModelDiem để tạo ra các hàng và ô trong excel
                        for(int i = 0; i < form.getTableDiem().getRowCount(); i++) {
                            XSSFRow excelRow = excelSheet.createRow(i + 1);
                            for(int j = 0; j < form.getTableDiem().getColumnCount(); j++) {
                                XSSFCell excelCell = excelRow.createCell(j);
                                //gán dữ liệu từ các cột trong JTable vào các cell
                                excelCell.setCellValue(form.getTableDiem().getValueAt(i, j).toString());
                            }
                        }
                        excelFOU = new FileOutputStream(excelFile.getSelectedFile() + ".xlsx");
                        excelBOU = new BufferedOutputStream(excelFOU);
                        excelJTableExporter.write(excelBOU);
                        JOptionPane.showMessageDialog(form, "Export data thành công!");
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } finally {
                        try {
                            if (excelBOU != null) {
                                excelBOU.close();
                            }
                            if (excelFOU != null) {
                                excelFOU.close();
                            }
                            if (excelJTableExporter != null) {
                                excelJTableExporter.close();
                            }
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        });
    }
}


