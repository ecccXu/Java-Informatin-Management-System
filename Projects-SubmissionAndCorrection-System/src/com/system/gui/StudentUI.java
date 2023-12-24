/**
 * 系统操作 —— 学生端UI
 */
package com.system.gui;

import com.system.service.StudentManage.StudentInfoUI;
import com.system.service.AdminManage.AdminManage;

import java.util.Scanner;

public class StudentUI {
    public static void UI() {
    while (true){
        menu();
        func();
    }
}
    private static void menu() {
        System.out.println("=========== 学生作业提交与批改系统[学生端] ===========");
        System.out.println("1.查看作业");//查看未完成的作业
        System.out.println("2.编辑作业");
        System.out.println("3.提交作业");
        System.out.println("4.作业成绩");
        System.out.println("5.个人信息管理");//先进管理员端口测试一下！！
        System.out.println("0.返回主页");
        System.out.println("=================================================");
        System.out.print("请选择：");
    }
    private static void func(){
        Scanner scanner = new Scanner(System.in);
        AdminManage adminManage = new AdminManage();
        try {
            switch (scanner.nextInt()) {
                case 1:
                    adminManage.acceptPJT();
                    break;
                case 2:
                    adminManage.editPJT();
                    break;
                case 3:
                    adminManage.submitPJT();
                    break;
                case 4:
                    adminManage.SingleGrade();
                    break;
                case 5:
                    StudentInfoUI.getStu();
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
