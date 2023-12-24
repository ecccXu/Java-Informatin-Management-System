/**
 * 系统操作 —— 教师端UI
 */
package com.system.gui;

import com.system.service.ProjectManage.ProjectManage;
import com.system.service.TeacherManage.TeacherInfoUI;
import com.system.service.AdminManage.AdminManage;

import java.util.Scanner;

public class TeacherUI {
    public static void UI() {
        while (true){
            menu();
            func();
        }
    }
    private static void menu() {
        System.out.println("=========== 学生作业提交与批改系统[教师端] ===========");
        System.out.println("1.查看作业");//查看已发布的作业
        System.out.println("2.发布作业");
        System.out.println("3.批改作业");
        System.out.println("4.新建作业");
        System.out.println("5.删除作业");
        System.out.println("6.单科成绩");
//        System.out.println("7.所有成绩");
        System.out.println("7.个人信息管理");//先进管理员端口测试一下！！
        System.out.println("0.返回主页");
        System.out.println("=================================================");
        System.out.print("请选择：");
    }
    private static void func(){
        Scanner scanner = new Scanner(System.in);
        AdminManage adminManage = new AdminManage();
        ProjectManage pjtm = new ProjectManage();
        try{
            switch (scanner.nextInt()) {
                case 1:
                    adminManage.acceptPJT();
                    break;
                case 2:
                    adminManage.postPJT();
                    break;
                case 3:
                    adminManage.markPJT();
                    break;
                case 4:
                    System.out.print("请输入新增的文件名：");
                    scanner.nextLine();
                    pjtm.setTxtname(scanner.nextLine());
                    System.out.print("请输入文件路径：");
                    pjtm.setPath(scanner.nextLine());
                    System.out.println(pjtm.getPath());
                    pjtm.CreatFile(pjtm.getPath(),pjtm.getTxtname());
                    break;
                case 5:
                    System.out.print("请输入删除的文件名：");
                    scanner.nextLine();
                    pjtm.setTxtname(scanner.nextLine());
                    pjtm.setFileName(pjtm.getTxtname());
                    System.out.print("请输入文件路径：");
                    pjtm.setPath(scanner.nextLine());
                    pjtm.setFilePath(pjtm.getPath());
                    pjtm.DeleteFile();
                    break;
                case 6:
                    adminManage.SingleGrade();
                    break;
//                case 7:
//                    adminManage.AllGrades();
//                    break;
                case 7:
                    TeacherInfoUI.getTea();
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
        } catch (Exception e) {
            System.out.println("输入错误，请重新选择！");
        }

    }
}
