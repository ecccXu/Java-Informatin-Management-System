package com.system.service.TeacherManage;

import com.system.gui.TeacherUI;

import java.util.Scanner;

public class TeacherInfoUI {
    public static void getTea() {
        System.out.println("正在初始化教师信息系统...");
        TeacherBasisInfo.load();
        UI();
    }
    protected static void UI() {
        while (true){
            menu();
            func();
        }
    }
    static void menu() {
        System.out.println("=========== 教师基础信息[管理员端] ===========");
        System.out.println("1.添加教师信息");
        System.out.println("2.浏览教师信息");
        System.out.println("3.查询教师信息");
        System.out.println("4.修改教师信息");
        System.out.println("5.删除教师信息");
        System.out.println("0.返回上页");
        System.out.println("==========================================");
        System.out.print("请选择：");
    }
    static void func(){
        Scanner scanner = new Scanner(System.in);
        try{
            switch (scanner.nextInt()) {
                case 1:
                    System.out.println("-- 教师信息添加 --");
                    TeacherBasisInfo.insert(scanner);
                    break;
                case 2:
                    System.out.println("-- 教师信息浏览 --");
                    TeacherBasisInfo.browse();
                    break;
                case 3:
                    System.out.println("-- 教师信息查询 --");
                    System.out.print("请输入需要查询的教师工号/姓名：");
                    TeacherBasisInfo.search(scanner);
                    break;
                case 4:
                    System.out.println("-- 教师信息修改 --");
                    System.out.print("请输入需要修改的教师工号/姓名：");
                    TeacherBasisInfo.modify(scanner);
                    break;
                case 5:
                    System.out.println("-- 教师信息删除 --");
                    System.out.print("请输入需要删除的教师工号/姓名：");
                    TeacherBasisInfo.delete(scanner);
                    break;
                case 0:
                    System.out.println("正在保存教师信息数据...");
                    TeacherBasisInfo.save();
                    System.out.println();
                    TeacherUI.UI();
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
