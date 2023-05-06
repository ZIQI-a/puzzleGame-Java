package com.ziqi.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {
    Random r = new Random();
    //记录空白位置
    int x, y;
    //记录图片路径，易于切换
//    String path = "..\\puzzleGame\\image\\animal\\animal3\\";
    String path = "image\\animal\\animal3\\";

    int step = 0;
    //正确图片的顺序
    int [][] win = new int[][] {
        {1, 2, 3, 4},
        {5, 6, 7, 8},
        {9, 10, 11, 12},
        {13, 14, 15, 0}
    };

    int [][] data = new int[4][4];

    //初始化窗口
    public GameJFrame(){

        //初始化界面
        initFrame();

        //菜单栏
        initMenuBar();

        //初始化打乱15数据
        initData();

        //加入图片
        initImage();

        //显示整个菜单
        this.setVisible(true);
    }

    private void initData() {
        int [] tempArr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        for(int i : tempArr) {
            int index = r.nextInt(tempArr.length);
            int temp = tempArr[i];
            tempArr[i] = tempArr[index];
            tempArr[index] = temp;
        }

        for(int i = 0; i < tempArr.length; i++){
            data[i / 4][i % 4] = tempArr[i];
            if (data[i / 4][i % 4] == 0) {
                x = i / 4;
                y = i % 4;
            }
        }
    }

    //初始化图片
    private void initImage() {
        //先移除所有的图片
        this.getContentPane().removeAll();

        if(isWin()) {
//            JLabel winimg = new JLabel(new ImageIcon("..\\puzzleGame\\image\\win.png"));
            JLabel winimg = new JLabel(new ImageIcon("image\\win.png"));
            winimg.setBounds(180, 250, 197, 73);
            this.getContentPane().add(winimg);
        }

        JLabel stepCount = new JLabel("步数：" + step);
        stepCount.setBounds(50,2,100,50);
        stepCount.setFont(new Font("宋体", Font.BOLD, 20));
        this.getContentPane().add(stepCount);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {

                int imgnum = data[i][j];

                this.setLayout(null);

                ImageIcon icon = new ImageIcon(path + imgnum +".jpg");

                JLabel jl = new JLabel(icon);

                jl.setBounds(105 * j + 52, 105 * i + 96, 105, 105);
                jl.setBorder(new BevelBorder(1));

                this.add(jl);
            }
        }
//        JLabel background = new JLabel(new ImageIcon("..\\puzzleGame\\image\\background.png"));
        JLabel background = new JLabel(new ImageIcon("image\\background.png"));
        background.setBounds(8, 2, 508, 560);
        this.add(background);
        this.getContentPane().repaint();
    }

    private void initMenuBar() {
        JMenuBar jb = new JMenuBar();
        jb.setSize(514,20);

        //菜单中的栏目
        JMenu functionJM = new JMenu("功能");
        JMenu money = new JMenu("充值入口");
        JMenu aboutJM = new JMenu("关于作者");


        //菜单小栏目
            //更换图片的逻辑
        JMenu changeImg = new JMenu("更换图片");
        JMenuItem gril = new JMenuItem("美女");
        JMenuItem animal = new JMenuItem("动物");
        JMenuItem sport = new JMenuItem("运动");

        JMenuItem replayItem = new JMenuItem("重新游戏");
        JMenuItem reloginItem = new JMenuItem("重新登录");
        JMenuItem closeItem = new JMenuItem("关闭游戏");

        JMenuItem plus6 = new JMenuItem("6元首冲");
        JMenuItem plus648 = new JMenuItem("648元变强");

        //加入
        functionJM.add(changeImg);
        functionJM.add(replayItem);
        functionJM.add(reloginItem);
        functionJM.add(closeItem);

        //更换图片子菜单
        changeImg.add(gril);
        changeImg.add(animal);
        changeImg.add(sport);

        money.add(plus648);
        money.add(plus6);

        jb.add(functionJM);
        jb.add(aboutJM);
        jb.add(money);
//关于作者
        aboutJM.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                System.out.println("关于作者");
                JDialog zd = new JDialog();
                zd.setTitle("关于作者");
                //上面的说明文字
                JLabel complain = new JLabel("点击链接即可进入我的B站主页^v^");
                complain.setBounds(75, 30, 400, 50);
                complain.setFont(new Font("仿宋", Font.CENTER_BASELINE, 20));
                //链接，点击可以跳转
                final JLabel author = new JLabel("B站主页:https://space.bilibili.com/432460619/");
                author.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        URI uri;
                        try {
                            uri = new URI("https://space.bilibili.com/432460619/");
                            Desktop dep = Desktop.getDesktop();
                            if(Desktop.isDesktopSupported() && dep.isSupported(Desktop.Action.BROWSE)) {
                                try {
                                    dep.browse(uri);
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                            }
                        } catch (URISyntaxException ex) {
                            throw new RuntimeException(ex);
                        }
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        author.setForeground(Color.blue);
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        author.setForeground(Color.CYAN);
                    }
                }); //使用URI来进行匹配
                author.setFont(new Font("仿宋", Font.CENTER_BASELINE, 20));
                author.setBounds(50,50, 400,30);
                //设置对话框的格式
                zd.setSize(500, 300);
                zd.setAlwaysOnTop(true);
                zd.setLocationRelativeTo(null);
                zd.setModal(true);
                //将标签添加到对话框，并显示
                zd.getContentPane().add(complain);
                zd.getContentPane().add(author);
                zd.setVisible(true);
            }

            @Override
            public void menuDeselected(MenuEvent e) {

            }

            @Override
            public void menuCanceled(MenuEvent e) {

            }
        });

        //添加到动作监听事件中
        //重新游戏
        replayItem.addActionListener(new MyAction(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("重新游戏");
                step = 0;
                GameJFrame.this.setVisible(false);
                new GameJFrame();
            }
        });
        //重新登录
        reloginItem.addActionListener(new MyAction(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("重新登录");
                GameJFrame.this.setVisible(false);
                new LoginJFrame();
            }
        });
        //关闭游戏
        closeItem.addActionListener(new MyAction(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("关闭游戏");
                System.exit(0);
            }
        });

        plus6.addActionListener(new MyAction(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("充值1");
                JDialog m1 = new JDialog();
                JLabel text1 = new JLabel("请点开关于作者，给第一个视频三连即可自动充值成功！^v^");
                text1.setBounds(20, 20, 250, 250);
                text1.setFont(new Font("仿宋", Font.CENTER_BASELINE, 16));

                m1.setTitle("变强第一步");
                m1.setSize(500, 300);
                m1.setAlwaysOnTop(true);
                m1.setLocationRelativeTo(null);
                m1.setModal(true); //弹框不关闭则无法操作下面的界面

                m1.getContentPane().add(text1);
                m1.setVisible(true);
            }
        });
        plus648.addActionListener(new MyAction(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("充值2");
                JDialog m1 = new JDialog();
                JLabel text1 = new JLabel("请点开关于作者，给所有视频三连即可自动充值成功！^v^");
                text1.setBounds(80, 20, 250, 250);
                text1.setFont(new Font("仿宋", Font.CENTER_BASELINE, 16));

                m1.setTitle("骚年，想变强吗？");
                m1.setSize(500, 300);
                m1.setAlwaysOnTop(true);
                m1.setLocationRelativeTo(null);
                m1.setModal(true); //弹框不关闭则无法操作下面的界面

                m1.getContentPane().add(text1);
                m1.setVisible(true);
            }
        });

        //更换图片子菜单添加触发
        gril.addActionListener(new MyAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("更换美女图片");
                int n = r.nextInt(1, 14);
//                path = "..\\puzzleGame\\image\\girl\\girl" + n + "\\";
                path = "image\\girl\\girl" + n + "\\";
                initImage();
                step = 0;
            }
        });
        animal.addActionListener(new MyAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("更换动物图片");
                int n = r.nextInt(1, 9);
//                path = "..\\puzzleGame\\image\\animal\\animal" + n + "\\";
                path = "image\\animal\\animal" + n + "\\";
                initImage();
                step = 0;
            }
        });
        sport.addActionListener(new MyAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("更换运动图片");
                int n = r.nextInt(1, 11);
//                path = "..\\puzzleGame\\image\\sport\\sport" + n + "\\";
                path = "image\\sport\\sport" + n + "\\";
                initImage();
                step = 0;
            }
        });

        this.setJMenuBar(jb);
    }

    private void initFrame() {
        this.setSize(540,630);
        this.setTitle("天天拼图");
        this.setAlwaysOnTop(true);
        //设置关闭模式
        this.setLocationRelativeTo(null);
        //设置关闭窗口后关闭
        this.setDefaultCloseOperation(3);
        this.addKeyListener(this);
    }

    //判断当前的数组是否和正确答案一致
    public boolean isWin() {
        for(int i = 0; i < data.length; i++) {
            for(int j = 0; j < data[i].length; j++) {
                if(data[i][j] != win[i][j]) return false;
            }
        }
        return true;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int n = e.getKeyCode();
        if(n == 65) {
            this.getContentPane().removeAll();
            JLabel all = new JLabel(new ImageIcon(path + "all.jpg"));
            all.setBounds(52, 96, 420, 420);
            this.getContentPane().add(all);

//            JLabel background = new JLabel(new ImageIcon("..\\puzzleGame\\image\\background.png"));
            JLabel background = new JLabel(new ImageIcon("image\\background.png"));
            background.setBounds(8, 2, 508, 560);
            this.add(background);
            this.getContentPane().repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
//        如果赢了直接不能操作
        if(isWin()) return;

        int n = e.getKeyCode();
        //图片上下左右移动的逻辑
        if(n == 37) {  //向左
            if(y == 3) return;
            data[x][y] = data[x][y + 1];
            data[x][y + 1] = 0;
            y++;step++;
            initImage();
            System.out.println("向左移动");
        }
        if (n  == 38) { //向上
            if(x == 3) return;
            data[x][y] = data[x + 1][y];
            data[x + 1][y] = 0;
            x++;step++;
            initImage();
            System.out.println("向上移动");
        }
        if (n == 39) {  //向右
            if(y == 0) return;
            data[x][y] = data[x][y - 1];
            data[x][y - 1] = 0;
            y--;step++;
            initImage();
            System.out.println("向右移动");

        }
        if (n == 40) {   //向下
            if(x == 0) return;
            data[x][y] = data[x - 1][y];
            data[x - 1][y] = 0;
            x--;step++;
            initImage();
            System.out.println("向下移动");
        }

        //按键a松开时变回原样。
        if(n == 65) {
            initImage();
        }

        //风灵月影---启动！！！！
        if(n == 71) {
            data = win;
            initImage();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
