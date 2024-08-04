/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.sql.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SinhVien {

    private String idSinhVien;

    private String tenSinhVien;

    private Lop lop;

    private Khoa khoa;

    private Date ngaySinh;

    private Boolean gioiTinh;

    private String diaChi;

    private String sdt;

    private String taiKhoan;

    private byte[] anhSinhVien;
    
    public SinhVien() {}

    public SinhVien(String idSinhVien, String tenSinhVien, Lop lop, Khoa khoa, Date ngaySinh, Boolean gioiTinh, String diaChi, String sdt, String taiKhoan, byte[] anhSinhVien) {
        this.idSinhVien = idSinhVien;
        this.tenSinhVien = tenSinhVien;
        this.lop = lop;
        this.khoa = khoa;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.taiKhoan = taiKhoan;
        this.anhSinhVien = anhSinhVien;
    }

    public String getIdSinhVien() {
        return idSinhVien;
    }

    public void setIdSinhVien(String idSinhVien) {
        this.idSinhVien = idSinhVien;
    }

    public String getTenSinhVien() {
        return tenSinhVien;
    }

    public void setTenSinhVien(String tenSinhVien) {
        this.tenSinhVien = tenSinhVien;
    }

    public Lop getLop() {
        return lop;
    }

    public void setLop(Lop lop) {
        this.lop = lop;
    }

    public Khoa getKhoa() {
        return khoa;
    }

    public void setKhoa(Khoa khoa) {
        this.khoa = khoa;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public Boolean getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(Boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public byte[] getAnhSinhVien() {
        return anhSinhVien;
    }

    public void setAnhSinhVien(byte[] anhSinhVien) {
        this.anhSinhVien = anhSinhVien;
    }
    
    
    
}
