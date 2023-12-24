package com.system.gui;

import com.system.gui.Admin.AdminInfoUI;
import com.system.gui.Admin.AdminProjectUI;

import java.util.Scanner;

public class AdminUI {
    public static void UI() {
        while (true) {
            menu();
            func();
        }
    }
    private static void menu(){
        System.out.println("=========== 学生作业提交与批改系统[管理员端] ===========");
        System.out.println("1.信息管理系统");//查看未完成的作业
        System.out.println("2.作业管理系统");
        System.out.println("0.返回主页");
        System.out.println("=================================================");
        System.out.print("请选择：");
    }
    private static void func(){
        Scanner scanner = new Scanner(System.in);
        try {
            switch (scanner.nextInt()) {
                case 1:
                    AdminInfoUI.getAdmin();
                    break;
                case 2:
                    AdminProjectUI.UI();
                    break;
                case 0:
                    System.out.println("正在保存数据...");
                    System.out.println();
                    MainUI.getMain();
                    break;
                default:
                    System.out.println("输入错误，请重新选择！");
                    break;
            }
        }catch (Exception e){
            System.out.println("输入错误，请重新选择！");
        }
    }
}
