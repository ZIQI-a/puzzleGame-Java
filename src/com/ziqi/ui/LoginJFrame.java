package com.ziqi.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class LoginJFrame extends JFrame implements ActionListener, MouseListener, KeyListener {
    Random r = new Random();

    //正确的账户
    String name = "ziqi";
    String pasword = "qwe123";

    public LoginJFrame() {
        //初始化界面
        initJFrame();
        //添加控件
        initView();

//显示界面
        this.setVisible(true);
    }

    private void initView() {

//        JLabel usericon = new JLabel(new ImageIcon("..\\puzzleGame\\image\\login\\用户名.png"));
        JLabel usericon = new JLabel(new ImageIcon("image\\login\\用户名.png"));
        usericon.setBounds(116, 135, 47, 17);
        this.getContentPane().add(usericon);

        JTextField username = new JTextField();
        username.setBounds(195, 134, 200, 30);
        this.getContentPane().add(username);

//        JLabel pswicon = new JLabel(new ImageIcon("..\\puzzleGame\\image\\login\\密码.png"));
        JLabel pswicon = new JLabel(new ImageIcon("image\\login\\密码.png"));
        pswicon.setBounds(130, 195, 32, 16);
        this.getContentPane().add(pswicon);

        JPasswordField psw = new JPasswordField();
        psw.setBounds(195, 195, 200, 30);
        this.getContentPane().add(psw);

        //显示密码小眼睛
//        JLabel eye = new JLabel(new ImageIcon("..\\puzzleGame\\image\\login\\显示密码.png"));
        JLabel eye = new JLabel(new ImageIcon("image\\login\\显示密码.png"));
        eye.setBounds(400, 195, 18, 29);
        eye.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                psw.setEchoChar((char)0);
                System.out.println("显示密码");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                psw.setEchoChar('·');
                System.out.println("隐藏密码");
            }
        });
        this.getContentPane().add(eye);

//        JLabel codeText = new JLabel(new ImageIcon("..\\puzzleGame\\image\\login\\验证码.png"));
        JLabel codeText = new JLabel(new ImageIcon("image\\login\\验证码.png"));
        codeText.setBounds(133, 256, 50, 30);
        this.getContentPane().add(codeText);

        JTextField code = new JTextField();
        code.setBounds(195, 250, 100, 30);
        this.getContentPane().add(code);

        //显示随机验证码
        String codeStr = randomCode();
        JLabel rightCode = new JLabel();
        rightCode.setText(codeStr);
        rightCode.setBounds(300, 256, 50, 30);
        this.getContentPane().add(rightCode);

        JButton login = new JButton();
        login.setBounds(123, 310, 128, 47);
//        login.setIcon(new ImageIcon("..\\puzzleGame\\image\\login\\登录按钮.png"));
        login.setIcon(new ImageIcon("image\\login\\登录按钮.png"));
        login.setBorderPainted(false);//去除按钮的默认边框
        login.setContentAreaFilled(false);//去除按钮的默认背景

        //点击登录按钮改变图片（匿名函数
        login.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
//                login.setIcon(new ImageIcon("..\\puzzleGame\\image\\login\\登录按下.png"));
                login.setIcon(new ImageIcon("image\\login\\登录按下.png"));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
//                login.setIcon(new ImageIcon("..\\puzzleGame\\image\\login\\登录按钮.png"));
                login.setIcon(new ImageIcon("image\\login\\登录按钮.png"));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("点下登录按钮");
                String userInput = username.getText();
                String pswInput = psw.getText();
                String codeInput = code.getText();
                System.out.println("用户输入用户名：" + userInput + " 密码：" + pswInput);
                if(userInput.length() == 0 || pswInput.length() == 0) {
                    showDialog("用户名或密码为空");
                    return;
                }
                if(!codeStr.equals(codeInput)) {
                    showDialog("验证码错误");
                    return;
                }
                if(!userInput.equals(name) || !pswInput.equals(pasword)) {
                    showDialog("用户名或密码错误");
                    return;
                }
                System.out.println("登录成功！！！");
                LoginJFrame.this.setVisible(false);
                new GameJFrame();
            }
        });

        this.getContentPane().add(login);

        JButton register = new JButton();
        register.setBounds(256, 310, 128, 47);
//        register.setIcon(new ImageIcon("..\\puzzleGame\\image\\login\\注册按钮.png"));
        register.setIcon(new ImageIcon("image\\login\\注册按钮.png"));
        register.setBorderPainted(false);
        register.setContentAreaFilled(false);

        //按下注册按钮改变颜色
        register.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
//                register.setIcon(new ImageIcon("..\\puzzleGame\\image\\login\\注册按下.png"));
                register.setIcon(new ImageIcon("image\\login\\注册按下.png"));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
//                register.setIcon(new ImageIcon("..\\puzzleGame\\image\\login\\注册按钮.png"));
                register.setIcon(new ImageIcon("image\\login\\注册按钮.png"));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                showDialog("注册功能未开放！敬请期待~");
            }
        });

        this.getContentPane().add(register);

        //1、添加背景图片
//        JLabel background = new JLabel(new ImageIcon("..\\puzzleGame\\image\\login\\background.png"));
        JLabel background = new JLabel(new ImageIcon("image\\login\\background.png"));
        background.setBounds(0, 0, 470, 390);
        this.getContentPane().add(background);
    }

    private void initJFrame() {
        this.setSize(488,430);
        this.setTitle("欢迎登录");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(3);
        this.setAlwaysOnTop(true);
        this.addKeyListener(this);
        this.setLayout(null);
    }

    //随机验证码逻辑
    private String randomCode() {
        String s = "12456890qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZZXCVBNM";

        String res = "";
        for(int i = 0; i < 4; i++) {
            int sr = r.nextInt(0, 62);
            res += s.charAt(sr);
        }
        return res;
    }

    //弹出对话框
    private void showDialog(String content) {
        JDialog jd = new JDialog();
        jd.setTitle("提示信息");
        jd.setSize(350, 150);
        jd.setLocationRelativeTo(null);
        jd.setAlwaysOnTop(true);
        jd.setModal(true);

        JLabel text = new JLabel(content);
        text.setBounds(20, 20, 200, 150);
        text.setFont(new Font("黑体", Font.BOLD, 16));
        text.setForeground(Color.red);
        jd.getContentPane().add(text);

        jd.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
