/**
 * 系统操作 —— 主UI
 */
package com.system.gui;

import java.util.Scanner;

public class MainUI {
    public static void getMain(){
        System.out.println("正在初始化学生作业提交与批改系统...");
        System.out.println();
        UI();
    }
    private static void UI() {
        while (true){
            menu();
            func();
        }
    }
    private static void menu(){
        System.out.println("=======【欢迎使用学生作业提交与批改系统】=======");
        System.out.println("=======       1.学生登录         =======");
        System.out.println("=======       2.教师登录         =======");
        System.out.println("=======       3.管理员登录         =======");
        System.out.println("=======       0.退出系统         =======");
        System.out.println("===========================================");
        System.out.print("请选择：");
    }
    private static void func(){
        Scanner scanner = new Scanner(System.in);
        try {
            int choose = scanner.nextInt();
            switch (choose) {
                case 1:
                    StudentUI.UI();
                    break;
                case 2:
                    TeacherUI.UI();
                    break;
                case 3:
                    AdminUI.UI();
                    break;
                case 0:
                    System.out.println("正在保存数据...");
                    System.out.println("退出成功！");
                    System.out.println();
                    System.exit(0);
                    break;
                default:
                    System.out.println("输入错误，请重新选择！");
                    break;
            }
        } catch (Exception e){
            System.out.println("输入错误，请重新选择！");
        }
    }

}
