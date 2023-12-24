package com.system.gui.Admin;

import com.system.gui.AdminUI;
import com.system.service.StudentManage.StudentBasisInfo;
import com.system.service.StudentManage.StudentInfoUI;
import com.system.service.TeacherManage.TeacherInfoUI;


import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class AdminInfoUI {
    protected static List<AdminInfoUI> LIST;
    public AdminInfoUI(){}
    public static void getAdmin() {
        System.out.println("正在初始化管理员信息系统...");
        load();
        UI();
    }
    private static void UI(){
        while (true){
            menu();
            func();
        }
    }
    private static void menu(){
        System.out.println("=========== 信息管理系统[管理员端] ==============");
        System.out.println("1.教师信息管理");
        System.out.println("2.学生信息管理");
        System.out.println("0.返回上页");
        System.out.println("================================================");
        System.out.print("请选择：");
    }
    private static void func(){
        Scanner scanner = new Scanner(System.in);
        try{
            int choose = scanner.nextInt();
            switch (choose) {
                case 1:
                    TeacherInfoUI.getTea();
                    break;
                case 2:
                    StudentInfoUI.getStu();
                    break;
                case 3:
                    getAdmin();
                    break;
                case 0:
                    System.out.println("正在保存数据...");
                    System.out.println();
                    AdminUI.UI();
                    break;
                default:
                    System.out.println("输入错误，请重新选择！");
                    break;
            }
        } catch (Exception e) {
            System.out.println("输入错误，请重新选择！");
        }

    }
    /** 读取数据 **/
    @SuppressWarnings("unchecked")
    protected static void load(){
//        StudentBasisInfo tea = new StudentBasisInfo();
        File file = new File("AdministratorInfo.txt");
        if (file.exists()){
            try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(file))){//如何保存为txt？
                LIST = (List<AdminInfoUI>) stream.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }else {
            LIST = new LinkedList<>();
        }
    }
    /** 保存数据 **/
    protected static void save(){
        StudentBasisInfo tea = new StudentBasisInfo();
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream("AdministratorInfo.txt"))){//如何保存为txt？
            stream.writeObject(LIST);
            stream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
