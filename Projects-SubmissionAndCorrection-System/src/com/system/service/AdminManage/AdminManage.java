package com.system.service.AdminManage;

import com.system.service.ProjectManage.ProjectManage;

import java.io.*;
import java.util.Scanner;

class AdminDemo {
    ProjectManage pjtm = new ProjectManage();
    Scanner scanner = new Scanner(System.in);
    String filepath = null;
    String tempstr = null;

}
public class AdminManage extends AdminDemo implements IAdmin{
    /**
     * 1.postPJT —— 发布作业
     * <p>
     * 操作：从 T.Post -copy-> S.Accept
     */
    @Override
    public void postPJT() {
        try {
            /*可发布的作业：展示post内有的文件**/
            System.out.println("可发布的作业如下：");
            pjtm.setPath("p");
            pjtm.setFilePath(pjtm.getPath());
            getTXT(pjtm.getPath());
            while (true) {
                System.out.print("请输入发布的作业名：");//输入文件名
                try {
                    pjtm.setTxtname(scanner.nextLine());
                    pjtm.setFileName(pjtm.getTxtname());
                    if (pjtm.getFileName().equals("#")) {
                        break;
                    }else {
                        filepath = pjtm.getPath() + "\\" + pjtm.getFileName();
                        pjtm.setPath1(filepath);//真正的源地址 --> path1
                        pjtm.setPath("a");
                        pjtm.setPath2(pjtm.getPath());
                        pjtm.CopyFile(pjtm.getPath1(), pjtm.getPath2());

                        System.out.println(pjtm.getFileName());
                        System.out.println(pjtm.getFilePath());
                        pjtm.DeleteFile();
                        System.out.println("发布成功！");
                        break;
                    }
                } catch (Exception e) {
                    System.out.print("\n输入有误！请重新输入(或输入“#”退出操作)：");
                }
            }
        }catch (Exception e){
            System.out.println("error!");
        }
    }

    /**
     * 2.acceptPJT —— 查看作业
     * <p>
     * 操作：查询S.Accept内的.txt文件
     */
    @Override
    public void acceptPJT() {
        System.out.println("已发布的作业如下：");
        pjtm.setPath("a");
        getTXT(pjtm.getPath());
        scanner.nextLine();
    }

    /**
     * 列举 .txt 后缀的文件
     *
     */
    private void getTXT(String path){
        File dir = new File(path);

        File[] subFiles = dir.listFiles();

        if (null!=subFiles){
            for (File subFile : subFiles) {
                if (subFile.isDirectory()){
                    getTXT(subFile.getAbsolutePath());
                } else if(subFile.isFile() && subFile.getName().endsWith(".txt")){
                    System.out.println(subFile.getName());
                }
            }
        }else {
            System.out.println("无文件！");
        }
    }

    /**
     * 3.editPJT —— 编辑作业
     * 操作：1.在打开目标文件 —— aimTXT
     * 2.编辑文件
     * 3.保存
     */
    @Override
    public void editPJT() {
        System.out.println("需完成的作业如下：");
        pjtm.setPath("a");
        getTXT(pjtm.getPath());
        System.out.print("请输入需要编辑的作业名：");
        try {
            pjtm.ModifyFile(scanner);
        } catch (IOException e) {
            System.out.println();
        }
        scanner.nextLine();
    }

    /**
     * 4.submitPJT —— 提交作业
     * 操作：1.查找到 S.temp内的目标文件
     * 2.提交 - 从 S.temp - move -> T.Mark
     */
    @Override
    public void submitPJT() {
        System.out.println("未提交的作业如下：");
        pjtm.setPath("a");
        pjtm.setFilePath(pjtm.getPath());
        getTXT(pjtm.getPath());

        System.out.print("需要提交的作业名：");//输入文件名
        pjtm.setTxtname(scanner.nextLine());
        pjtm.setFileName(pjtm.getTxtname());

        filepath = pjtm.getFilePath()+pjtm.getFileName();
        pjtm.setPath1(filepath);//真正的源地址 --> path1
        pjtm.DeleteFile();

        pjtm.setPath("s");
        pjtm.setPath2(pjtm.getPath());//--->path2

        pjtm.CopyFile(pjtm.getPath1(), pjtm.getPath2());

        pjtm.setPath("m");
        pjtm.setPath2(pjtm.getPath());//--->T.Mark
        pjtm.CopyFile(pjtm.getPath1(), pjtm.getPath2());

        System.out.println("提交成功！");
    }

    /**
     * 5.markPJT —— 批改作业
     * 操作：在 grades.txt 中写入
     */
    @Override
    public void markPJT() {
        pjtm.setPath("m");
        getTXT(pjtm.getPath());
        System.out.print("请输入需要批改的作业名：");
        try {
            pjtm.setPath("m");
            pjtm.ModifyFile(scanner);
            scanner.nextLine();
            System.out.println("批改成功！");
        } catch (IOException e) {
            System.out.println();
        }
        scanner.nextLine();
    }
    @Override
    public void SingleGrade(){
        //        System.out.print("输入需要读取的文件路径：");
        pjtm.setPath("m");
        pjtm.setFilePath(pjtm.getPath());
        getTXT(pjtm.getFilePath());
        System.out.print("输入需要读取的文件名：");
        pjtm.setTxtname(scanner.nextLine());
        pjtm.setFileName(pjtm.getTxtname());
        tempstr = pjtm.readFirstLine();
        System.out.println("作业:[ "+pjtm.getFileName()+" ]\t"+tempstr+" 分");
    }
    @Override
    public void AllGrades(){
//        pjtm.setPath("m");
//        pjtm.setFilePath(pjtm.getPath());
//
//        String[] temp = pjtm.readFileName(pjtm.getPath());
//        System.out.println(Arrays.toString(temp));
//        for (int i = 1; i < temp.length+1; i++) {
//            for (int j = 0; j < i; j++) {
//                pjtm.grade = String.valueOf(j+1);
//                pjtm.setFileName("分数:"+pjtm.grade+"-"+temp[i-1]);
//            }
//            temp[i-1] = pjtm.getFileName();
//            System.out.println(pjtm.getFileName());
//        }
//        System.out.println(Arrays.toString(temp));
//
//
//
////        while (true){
////            //System.out.print("输入要进入的文件路径：");
////            pjtm.setPath("sourceT");
////            pjtm.setFilePath(pjtm.getPath());
////            pjtm.setTxtname("grades");
////            pjtm.setFileName(pjtm.getTxtname());
////            System.out.println("读取[ " + pjtm.getFileName() + " ]内容如下：");
////            pjtm.readAllLine();
////
////            scanner.nextLine();
////            break;
////        }
    }

}
