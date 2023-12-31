/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;
//import java.awt.BorderLayout;
//import java.awt.EventQueue;
//import java.io.File;
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.Statement;

import java.util.ArrayList;
import java.util.prefs.Preferences;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import BUS.AccountBUS;
import DAL.Data;
import DTO.AccountDTO;
import DTO.AuthorDTO;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JFrame;

/**
 *
 * @author hoangtuan
 */
public class LoginGUI extends JFrame {

    public Preferences pref = Preferences.userRoot().node("Rememberme");
    public static int quyen;
    public static boolean isdangnhap = true;
    public static boolean dangnhap = false;
    public static String username = "";
    public static ArrayList<AccountDTO> taikhoan = null;

    public void loadtaikhoan() {
        taikhoan = null;
        AccountBUS bus = new AccountBUS();

        taikhoan = bus.getDanhSachTL();
        for (AccountDTO taiKhoan2 : taikhoan) {
            System.out.println("Tài Khoản :" + taiKhoan2.getUsername().trim());
            System.out.println("Mật Khẩu :" + taiKhoan2.getPassword().trim());
        }
    }

    private int x_mouse;
    private int y_mouse;
    private JTextField txtUser;
    private JPasswordField txtPass;

    /**
     * Create the frame.
     */
    public LoginGUI() {

        setUndecorated(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 708, 550);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 765, 590);
        contentPane.add(panel);
        panel.setLayout(null);

//        JLabel lblNewLabel_1 = new JLabel("LOGIN");
//        lblNewLabel_1.setForeground(Color.BLACK);
//        lblNewLabel_1.setFont(
//                new Font("Consolas", lblNewLabel_1.getFont().getStyle(), lblNewLabel_1.getFont().getSize() + 21));
//        lblNewLabel_1.setBounds(100, 59, 107, 58);
//        panel.add(lblNewLabel_1);
        JLabel lblNewLabel_1_1 = new JLabel("UserName");
        lblNewLabel_1_1.setFont(new Font("Consolas", lblNewLabel_1_1.getFont().getStyle() | Font.BOLD,
                lblNewLabel_1_1.getFont().getSize() + 8));
        lblNewLabel_1_1.setForeground(Color.BLACK);
        lblNewLabel_1_1.setBounds(100, 160, 107, 58);
        panel.add(lblNewLabel_1_1);

        JLabel lblNewLabel_1_2 = new JLabel("PassWord");
        lblNewLabel_1_2.setFont(new Font("Consolas", lblNewLabel_1_2.getFont().getStyle() | Font.BOLD,
                lblNewLabel_1_2.getFont().getSize() + 8));
        lblNewLabel_1_2.setForeground(Color.BLACK);
        lblNewLabel_1_2.setBounds(100, 231, 107, 58);
        panel.add(lblNewLabel_1_2);

        txtUser = new JTextField();
        txtUser.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 128)));
        txtUser.setForeground(Color.black);
        txtUser.setFont(new Font("Consolas", txtUser.getFont().getStyle() | Font.BOLD,
                lblNewLabel_1_2.getFont().getSize() + 2));
        txtUser.setBackground(new Color(206, 182, 149));
        txtUser.setBounds(250, 161, 200, 35);
        panel.add(txtUser);
        txtUser.setColumns(10);

        txtPass = new JPasswordField();
        txtPass.setForeground(Color.BLACK);
        txtPass.setFont(new Font("Consolas", txtUser.getFont().getStyle() | Font.BOLD,
                lblNewLabel_1_2.getFont().getSize() + 2));

        txtPass.setBackground(new Color(206, 182, 149));
        txtPass.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 128)));
        txtPass.setColumns(10);
        txtPass.setBounds(250, 232, 200, 35);
        panel.add(txtPass);

        JButton btnNewButton = new JButton("LOGIN");

        btnNewButton.setBounds(250, 370, 119, 45);
        panel.add(btnNewButton);

        JLabel lblexit = new JLabel("");
        lblexit.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent arg0) {
                System.exit(0);
            }
        });
        lblexit.setIcon(new ImageIcon(getClass().getResource("/img/14_delete.png")));
        lblexit.setBounds(613, 44, 35, 35);
        panel.add(lblexit);
        setSize(662, 588);

        JCheckBox checkboxrm = new JCheckBox("Remember Me");
        checkboxrm.setForeground(Color.BLACK);
        checkboxrm.setBackground(new Color(0, 0, 0, 0));
        checkboxrm.setFont(
                new Font("Consolas", checkboxrm.getFont().getStyle() | Font.BOLD, checkboxrm.getFont().getSize() + 8));
        checkboxrm.setBounds(250, 310, 203, 25);
        panel.add(checkboxrm);
        loadtaikhoan();
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                if (checkboxrm.isSelected()) {
                    pref.put("Email", txtUser.getText());

                    pref.put("Password", txtPass.getText());
                }

                for (AccountDTO taiKhoan2 : taikhoan) {
                    if (taiKhoan2.getUsername().trim().equals(txtUser.getText().trim()) && taiKhoan2.getPassword().trim().equals(txtPass.getText().trim())) {
                        switch (taiKhoan2.getPermission_id()) {
                            case 0 -> {
                                MenuleftGUI menuleft = new MenuleftGUI(true, true, true, true, true, true, true, true, true,true);
                                menuleft.setVisible(true);
                            }
                            case 1 -> {

                                MenuleftGUI menuleft = new MenuleftGUI(true, true, false, true, true, false, false, false, false,true);
                                menuleft.setVisible(true);
                            }
                            case 2 -> {
                                System.err.println(taiKhoan2.getPermission_id());
                                MenuleftGUI menuleft = new MenuleftGUI(true, false, false, false, true, false, false, false, false,true);
                                menuleft.setVisible(true);
                            }
                            default -> {
                            }
                        }
                        JOptionPane.showMessageDialog(contentPane, "Đăng Nhập Thành Công !");
                        isdangnhap = true;
                        dangnhap = true;
                        setVisible(false);
                        return;
                    }
                }
                JOptionPane.showMessageDialog(contentPane, "Sai tài khoản hoặc mật khẩu !");
            }
        });
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/img/login.jpg")));
        lblNewLabel.setBounds(-25, -12, 691, 628);
        panel.add(lblNewLabel);
        setBackground(new Color(0, 0, 0, 0));
        panel.setBackground(new Color(0, 0, 0, 0));
        addEvent();
        setLocationRelativeTo(null);
        String usr = null;
        usr = pref.get("Email", usr);
        String pass = null;
        pass = pref.get("Password", pass);
        txtUser.setText(usr);
        txtPass.setText(pass);

    }

    public void savenmailpass(String Email, String Pass) {
        if (Email == null || Pass == null) {
            System.out.println("Loi khong luu duoc email passs");

        } else {
            String user = Email;
            pref.put("Email", Email);
            String pass = Pass;
            pref.put("Password", pass);

        }
    }

    public void checked(boolean remember) {
        if (remember) {
            savenmailpass(txtUser.getText(), txtPass.getText());
        } else {
            System.out.println("null");
        }
    }

    private void moveFrame(int x, int y) {
        // TODO Auto-generated method stub
        this.setLocation(x - x_mouse, y - y_mouse);
    }

    public void addEvent() {

        this.addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseMoved(MouseEvent arg0) {
                // TODO Auto-generated method stub
                x_mouse = arg0.getX();
                y_mouse = arg0.getY();

            }

            @Override
            public void mouseDragged(MouseEvent arg0) {
                // TODO Auto-generated method stub
                moveFrame(arg0.getXOnScreen(), arg0.getYOnScreen());

            }

        });

    }

    public static void main(String[] args) {
        LoginGUI login = new LoginGUI();
        login.setVisible(true);
    }
}
