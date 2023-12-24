package com.system.service.StudentManage;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class StudentBasisInfo implements Serializable {
    private String id;//学号
    private String name;//姓名
    //注意：不同学生的学号和姓名不能相同！！
    private String academy;//学院
    private String major;//专业
    private String classes;//班级

    private static String projectName;//作业名
    public static final String printtitle = "学号-姓名-学院-专业-班级";
    private static StudentBasisInfo temp = null;
    private static int index = 0;

    protected static List<StudentBasisInfo> LIST;
    public StudentBasisInfo(){}

    public StudentBasisInfo(String id, String name, String academy, String major, String classes) {
        this.id = id;
        this.name = name;
        this.academy = academy;
        this.major = major;
        this.classes = classes;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAcademy(String academy) {
        this.academy = academy;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    @Override
    public String toString(){
        return id+"-"+name+"-"+academy+"-"+major+"-"+classes;
    }

    /** 添加学生信息 **/
    protected static void insert(Scanner scanner){
        scanner.nextLine();
        System.out.print("学号：");
        String id = scanner.nextLine();
        while (true){
            if (isSearch(id)){
                System.out.print("\n学号为 ["+LIST.get(index).id+"] 的学生已存在，\n请重新输入(或输入“#”退出)：");
                id = scanner.nextLine();
                if (id.equals("#")){
                    System.out.println("退出成功！");
                    break;
                }
            } else {
                System.out.print("姓名：");
                String name = scanner.nextLine();
                System.out.print("学院：");
                String academy = scanner.nextLine();
                System.out.print("专业：");
                String major = scanner.nextLine();
                System.out.print("班级：");
                String clasess = scanner.nextLine();

                save();//保存信息
                StudentBasisInfo student = new StudentBasisInfo(id, name, academy, major, clasess);
                LIST.add(student);
                System.out.println("添加成功!");
                break;
            }
        }
    }

    /** 浏览学生信息 **/
    protected static void browse(){
        System.out.println(printtitle);
        for (int i = 0; i < LIST.size(); i++){
            int num = i + 1;
            System.out.println(num+". "+LIST.get(i));
        }
    }

    /** 查询学生信息 **/
    protected static void search(Scanner scanner){
        scanner.nextLine();
        String keywords = scanner.nextLine();
        while (true){
            if (isSearch(keywords)){//找到了
                System.out.println("-- 学号为 ["+ LIST.get(index).id +"] 的学生信息如下：");
                System.out.println(printtitle);
                System.out.println(temp);
                break;
            }
            if (!isSearch(keywords)){
                System.out.print("\n查无此学生，请重新输入(或输入“#”退出操作)：");
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

    /** 修改学生信息 **/
    protected static void modify(Scanner scanner) {
        scanner.nextLine();
        String keywords = scanner.nextLine();
        while (true){
            if (isSearch(keywords)){
                System.out.println("-- 学号为 ["+ LIST.get(index).id +"] 的学生信息如下：");
                System.out.println(printtitle);
                System.out.println(temp);

                StudentBasisInfo student = LIST.get(index);

                System.out.println("请输入修改信息：");
                System.out.print("学号：");
                student.setId(scanner.nextLine());
                System.out.print("姓名：");
                student.setName(scanner.nextLine());
                System.out.print("学院：");
                student.setAcademy(scanner.nextLine());
                System.out.print("专业：");
                student.setMajor(scanner.nextLine());
                System.out.print("班级：");
                student.setClasses(scanner.nextLine());

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

    /** 删除学生信息 **/
    protected static void delete(Scanner scanner){
        scanner.nextLine();
        String keywords = scanner.nextLine();
        while (true){
            if (isSearch(keywords)){
                System.out.println("-- 学号为 ["+ LIST.get(index).id +"] 的学生信息如下：");
                System.out.println(printtitle);
                System.out.println(temp);

                System.out.print("请输入【'y'/'n'】确认或取消删除：");
                String keyin = scanner.nextLine();
                if (keyin.equals("y")||keyin.equals("Y")){
                    LIST.remove(index);
                    System.out.println("删除成功！");
                }else {
                    System.out.println("已取消删除操作...");
                }break;
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
    public static void getProjectName(String pjtname){
        String id,name,major,classes;
        for (int i=0; i<LIST.size(); i++){
            id = LIST.get(i).id;
            name = LIST.get(i).name;
            major = LIST.get(i).major;
            classes = LIST.get(i).classes;
            projectName = pjtname+"-"+id+"-"+name+"-"+major+classes+".txt";
            System.out.println((i+1)+"."+projectName);
        }
    }

    /** 读取数据 **/
    @SuppressWarnings("unchecked")
    protected static void load() throws RuntimeException {
        LIST = new LinkedList<>();
        String path = ".\\SystemTest\\Student\\Student_Info\\StudentBasisInfo.txt";
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)))) {
            String line = null;
            while ((line = br.readLine()) != null) {
                // line为空或者记录不够就略过
                if(line.isEmpty()) {
                    continue;
                }
                String[] fields = line.split("-");
                if (fields.length<5){
                    continue;
                }
                StudentBasisInfo studentBasisInfo = new StudentBasisInfo();
                studentBasisInfo.setId(fields[0]);
                studentBasisInfo.setName(fields[1]);
                studentBasisInfo.setAcademy(fields[2]);
                studentBasisInfo.setMajor(fields[3]);
                studentBasisInfo.setClasses(fields[4]);
                LIST.add(studentBasisInfo);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /** 保存数据 **/
    public static void save()  {
        String path = ".\\SystemTest\\Student\\Student_Info\\StudentBasisInfo.txt";
        try{
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path),"UTF-8"));
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
