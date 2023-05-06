package com.ziqi.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("这是默认点击方法");
    }
}
