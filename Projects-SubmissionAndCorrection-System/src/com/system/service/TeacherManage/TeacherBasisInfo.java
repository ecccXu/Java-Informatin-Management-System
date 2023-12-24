package com.system.service.TeacherManage;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TeacherBasisInfo implements Serializable {
    private String id;//工号
    private String name;//姓名
    private String phone;//电话
    public static final String printtitle = "工号-姓名-联系电话";
    private static TeacherBasisInfo temp = null;
    private static int index = 0;
    protected static List<TeacherBasisInfo> LIST = new LinkedList<>();
    public TeacherBasisInfo(){}
    public TeacherBasisInfo(String id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString(){
        return id+"-"+name+"-"+phone;
    }

    /** 添加教师信息 **/
    protected static void insert(Scanner scanner){
        scanner.nextLine();
        System.out.print("工号：");
        String id = scanner.next();
        while (true){
            if (isSearch(id)){
                System.out.print("\n工号为 ["+LIST.get(index).id+"] 的教师已存在，\n请重新输入(或输入“#”退出)：");
                id = scanner.nextLine();
                if (id.equals("#")){
                    System.out.println("退出成功！");
                    break;
                }
            } else {
                System.out.print("姓名：");
                String name = scanner.next();
                System.out.print("电话：");
                String phone = scanner.next();

                save();//保存信息
                TeacherBasisInfo teacher = new TeacherBasisInfo(id, name, phone);
                LIST.add(teacher);
                System.out.println("添加成功！");
                break;
            }
        }
    }

    /** 浏览教师信息 **/
    protected static void browse(){
        System.out.println(printtitle);
        for (int i = 0; i < LIST.size(); i++){
            int num = i + 1;
            System.out.println(num+". "+LIST.get(i));
        }
    }

    /** 查询教师信息 **/
    protected static void search(Scanner scanner){
        scanner.nextLine();
        String keywords = scanner.nextLine();
        while (true){
            if (isSearch(keywords)){//找到了
                System.out.println("-- 工号为 "+ LIST.get(index).id +" 的教师信息如下：");
                System.out.println(printtitle);
                System.out.println(temp);
                break;
            }
            if (!isSearch(keywords)){
                System.out.print("\n查无此教师，请重新输入(或输入“#”退出查询)：");
                keywords = scanner.nextLine();
                if (keywords.equals("#")){
                    System.out.println("退出成功！");
                    break;
                }
            }
        }
    }
    private static boolean isSearch(String keyin){//键入id或name查找
        boolean result = false;
        for (int i=0; i<LIST.size();i++){
            if ((keyin.equals(LIST.get(i).id))||(keyin.equals(LIST.get(i).name)))
            {//找到了
                index = i;
                temp = LIST.get(i);
                result = true;
                break;
            }
        } return result;
    }

    /** 修改教师信息 **/
    protected static void modify(Scanner scanner) {
        scanner.nextLine();
        String keywords = scanner.nextLine();
        while (true){
            if (isSearch(keywords)){
                System.out.println("-- 工号为 ["+ LIST.get(index).id +"] 的教师信息如下：");
                System.out.println(printtitle);
                System.out.println(temp);

                TeacherBasisInfo teacher = LIST.get(index);

                System.out.println("请输入修改信息：");
                System.out.print("工号：");
                teacher.setId(scanner.nextLine());
                System.out.print("姓名：");
                teacher.setName(scanner.nextLine());
                System.out.print("电话：");
                teacher.setPhone(scanner.nextLine());

                save();
                System.out.println("修改成功!");
                break;
            }else {
                System.out.print("\n输入有误！请重新输入(或输入“#”退出操作)：");
                keywords = scanner.nextLine();
                if (keywords.equals("#")){
                    System.out.println("退出成功！");
                    break;
                }
            }
        }
    }

    /** 删除教师信息 **/
    protected static void delete(Scanner scanner){
        scanner.nextLine();
        String keywords = scanner.nextLine();
        while (true){
            if (isSearch(keywords)){
                System.out.println("-- 工号为 ["+ LIST.get(index).id +"] 的教师信息如下：");
                System.out.println(printtitle);
                System.out.println(temp);

                System.out.print("请输入【'y'/'n'】确认或取消删除：");
                String keyin = scanner.nextLine();
                if (keyin.equals("y")||keyin.equals("Y")){
                    LIST.remove(index);
                    System.out.println("删除成功！");
                }else {
                    System.out.println("已取消删除操作...");
                }
                break;
            }else {
                System.out.print("\n输入有误！请重新输入(或输入“#”退出操作)：");
                keywords = scanner.nextLine();
                if (keywords.equals("#")){
                    System.out.println("退出成功！");
                    break;
                }
            }
        }
    }
    /** 读取数据 **/
    @SuppressWarnings("unchecked")
    protected static void load() throws RuntimeException {
        LIST = new LinkedList<>();
        String path = ".\\SystemTest\\Teacher\\Teacher_Info\\TeacherBasisInfo.txt";
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)))) {
            String line = null;
            while ((line = br.readLine()) != null) {
                // line为空或者记录不够就略过
                if(line.isEmpty()) {
                    continue;
                }
                String[] fields = line.split("-");
                if (fields.length<3){
                    continue;
                }
                TeacherBasisInfo teacherBasisInfo = new TeacherBasisInfo();
                teacherBasisInfo.setId(fields[0]);
                teacherBasisInfo.setName(fields[1]);
                teacherBasisInfo.setPhone(fields[2]);
                LIST.add(teacherBasisInfo);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /** 保存数据 **/
    public static void save()  {
        String path = ".\\SystemTest\\Teacher\\Teacher_Info\\TeacherBasisInfo.txt";
        try{
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), StandardCharsets.UTF_8));
            for (int i=0; i<LIST.size(); i++){
                bw.write(LIST.get(i).toString());
                bw.newLine();
                bw.flush();
            }
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
