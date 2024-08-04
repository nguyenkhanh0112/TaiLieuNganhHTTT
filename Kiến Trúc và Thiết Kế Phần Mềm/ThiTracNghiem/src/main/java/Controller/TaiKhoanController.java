/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.TaiKhoanModel;
import View.LoginForm;
import javax.swing.JOptionPane;


public class TaiKhoanController {
    private LoginForm loginForm;
    private TaiKhoanModel userModel;
    
    
    public TaiKhoanController(LoginForm loginForm, TaiKhoanModel userModel) {
        this.loginForm = loginForm;
        this.userModel = userModel;
        
        addLoginListener();
    }
    
    public void login(String username, String password) {
        if(userModel.login(username, password)) {
            int i = userModel.phanQuyen(username);
            JOptionPane.showMessageDialog(loginForm, "Đăng nhập thành công");
            loginForm.hideLoginForm();
            loginForm.showNewForm(i, username);
        } else {
            JOptionPane.showMessageDialog(loginForm, "Sai tài khoản hoặc mật khẩu!");
        }
    }
    
    private void addLoginListener() {
        loginForm.addLoginListener(e -> {
            String username = loginForm.getUsername();
            String password = loginForm.getPassword();
            login(username, password);
        });
    }
    
   
}
