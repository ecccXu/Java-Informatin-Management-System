/**
 * 学生信息操作
 */
package com.system.service.StudentManage;

import com.system.gui.StudentUI;

import java.util.Scanner;
public class StudentInfoUI {
    public static void getStu(){
        System.out.println("正在初始化学生信息系统...");
        StudentBasisInfo.load();
        UI();
    }
    private static void UI(){
        while (true){
            menu();
            func();
        }
    }
    private static void menu() {
        System.out.println("=========== 学生基础信息[管理员端] ===========");
        System.out.println("1.添加学生信息");
        System.out.println("2.浏览学生信息");
        System.out.println("3.查询学生信息");
        System.out.println("4.修改学生信息");
        System.out.println("5.删除学生信息");
        System.out.println("0.返回上页");
        System.out.println("==========================================");
        System.out.print("请选择：");
    }
    private static void func(){
        Scanner scanner = new Scanner(System.in);
        try{
            switch (scanner.nextInt()) {
                case 1:
                    System.out.println("-- 学生信息添加 --");
                    StudentBasisInfo.insert(scanner);
                    break;
                case 2:
                    System.out.println("-- 学生信息浏览 --");
                    StudentBasisInfo.browse();
                    break;
                case 3:
                    System.out.println("-- 学生信息查询 --");
                    System.out.print("请输入需要查询的学生学号/姓名：");
                    StudentBasisInfo.search(scanner);
                    break;
                case 4:
                    System.out.println("-- 学生信息修改 --");
                    System.out.print("请输入需要修改的学生学号/姓名：");
                    StudentBasisInfo.modify(scanner);
                    break;
                case 5:
                    System.out.println("-- 学生信息删除 --");
                    System.out.print("请输入需要删除的学生学号/姓名：");
                    StudentBasisInfo.delete(scanner);
                    break;
                case 0:
                    System.out.println("正在保存学生信息数据...");
                    StudentBasisInfo.save();
                    System.out.println();
                    StudentUI.UI();
                    break;
                default:
                    System.out.println("输入错误，请重新选择！");
                    break;
            }
        } catch (Exception e) {
            System.out.println("输入错误，请重新输入");
        }

    }
}
