package com.system.gui.Admin;

import java.util.Scanner;

import com.system.gui.AdminUI;
import com.system.service.ProjectManage.ProjectManage;
import com.system.service.AdminManage.AdminManage;
import com.system.service.AdminManage.IAdmin;

public class AdminProjectUI {
    public static void UI() {
        while (true){
            menu();
            func();
        }
    }
    private static void menu(){
        System.out.println("=========== 作业管理系统[管理员端] ===========");
        System.out.println("1.发布作业");//tea
        System.out.println("2.查看作业");//tea、stu
        System.out.println("3.编辑作业");//     stu
        System.out.println("4.提交作业");//     stu
        System.out.println("5.批改作业");//tea
        System.out.println("6.新建作业");//tea
        System.out.println("7.删除作业");//tea
        System.out.println("8.单科成绩");//tea、stu---->读取M文件里面的第一行
//        System.out.println("9.所有成绩");//tea、stu---->读取M文件里面的第一行到grades
        System.out.println("0.返回上页");
        System.out.println("===========================================");
        System.out.print("请选择：");
    }
    private static void func(){
        Scanner scanner =new Scanner(System.in);
        IAdmin admin = new AdminManage();
        ProjectManage pjtm = new ProjectManage();
        try{
            int choose = scanner.nextInt();
            switch (choose){
                case 1:
                    admin.postPJT();
                    break;
                case 2:
                    admin.acceptPJT();
                    break;
                case 3:
                    admin.editPJT();
                    break;
                case 4:
                    admin.submitPJT();
                    break;
                case 5:
                    admin.markPJT();
                    break;
                case 6:
                    System.out.print("请输入新增的文件名：");
                    scanner.nextLine();
                    pjtm.setTxtname(scanner.nextLine());
                    System.out.print("请输入文件路径：");
                    pjtm.setPath(scanner.nextLine());
                    System.out.println(pjtm.getPath());
                    pjtm.CreatFile(pjtm.getPath(),pjtm.getTxtname());
                    break;
                case 7:
                    System.out.print("请输入删除的文件名：");
                    scanner.nextLine();
                    pjtm.setFileName(scanner.nextLine());
                    System.out.print("请输入文件路径：");
                    pjtm.setFilePath(scanner.nextLine());
                    pjtm.DeleteFile();
                    System.out.println("删除成功！");
                    break;
                case 8:
                    admin.SingleGrade();
                    break;
//                case 9:
//                    admin.AllGrades();
//                    break;
                case 0:
                    System.out.println("正在保存数据...");
                    System.out.println();
                    AdminUI.UI();
                    break;
                default:
                    System.out.println("输入错误，请重新选择！");
                    break;
            }
//            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("输入错误，请重新选择！");
        }
    }

}
