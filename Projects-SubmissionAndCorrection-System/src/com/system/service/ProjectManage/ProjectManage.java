package com.system.service.ProjectManage;

import java.awt.*;
import java.io.*;
import java.util.Scanner;

class ProjectDemo {
    public ProjectDemo(){}
    private final String SOURCEPATH = "X:\\Code Projects\\Java\\Projects-SubmissionAndCorrection-System\\SystemTest\\";
    public String getSOURCEPATH() {
        return SOURCEPATH;
    }
//    private String projectPath;//项目路径
//    private String projectName;//项目名 = sid + "-" + sname
    private String path1 = null;
    private String path2 = null;
    private String path = null;

    public void setPath(String path) {
        switch (path) {
            case "a":
                this.path = SOURCEPATH + "Student\\Accept\\";break;
            case "s":
                this.path = SOURCEPATH + "Student\\Submit\\";break;
            case "st":
                this.path = SOURCEPATH + "Student\\temp\\";break;
            case "m":
                this.path = SOURCEPATH + "Teacher\\Mark\\";break;
            case "p":
                this.path = SOURCEPATH + "Teacher\\Post\\";break;
            case "tt":
                this.path = SOURCEPATH + "Teacher\\temp\\";break;
            case "sourceS":
                this.path = SOURCEPATH + "Student\\";break;
            case "sourceT":
                this.path = SOURCEPATH + "Teacher\\";break;
            default:
                this.path = path;break;
        }

    }

    public String getPath() {
        return path;
    }
    protected String txtname = null;
    public void setTxtname(String txtname) {
            this.txtname = txtname + ".txt";
    }

    public String getTxtname() {
        return txtname;
    }

    public void setPath1(String path1) {
        this.path1 = path1;
    }

    public void setPath2(String path2) {
        this.path2 = path2;
    }

    public String getPath1() {
        return path1;
    }

    public String getPath2() {
        return path2;
    }

    protected String fileName = null;//文件名（编辑端/提交作业；接收作业/批改作业；发布作业）
    protected String filePath = null;//文件路径

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }
    public String grade = null;//测试用
}

public class ProjectManage extends ProjectDemo implements IProject {
    /**
     * 文件操作 —— 新建文件
     */
    @Override
    public void CreatFile(String path, String name) {
        setFilePath(path);
        setFileName(name);
        //创建文件对象
        File file = new File(getFilePath(),getFileName());
        if (file.exists()){
            System.out.println("ATTENTION:目标文件已存在");
        }else {
            try{
                file.createNewFile();
                System.out.println("SUCCESS:创建文件成功！");
            } catch (IOException e) {
                System.out.println("ERROR:创建文件失败！");
            }
        }
    }

    /**
     * 文件操作 —— 查找文件
     */
    @Override
    public boolean SearchFile(String filename){
//        System.out.print("请输入需要查询的文件名：");
//        filename = scanner.nextLine();
        File dir = new File(getSOURCEPATH());
        if (search(dir,filename)){
            System.out.println("查询成功！");
            return true;
        }else {
            System.out.println("查询失败！");
            return false;
        }
    }
    private static boolean search(File dir, String filename) {
        boolean flag = false;
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        // 递归进入子目录
                        search(file, filename);
                    } else if (file.getName().equals(filename)) {
                        // 找到目标文件
//                        System.out.println("Found: " + file.getAbsolutePath());
                        flag = true;
                        return flag;
                    }
                }
            }
        }
        return flag;
    }

    /**
     * 文件操作 —— 修改文件
     */
    @Override
    public void ModifyFile(Scanner scanner) throws IOException {
        setTxtname(scanner.nextLine());
        setFileName(getTxtname());
        setFilePath(getPath() + getFileName());
        File f1=new File(getFilePath());
        Desktop.getDesktop().open(f1);
    }

    /**
     * 文件操作 —— 删除文件
     * <p>
     * 操作：需要提前设置 源文件 的地址，源文件 的文件名
     * setPath
     * setFileName
     */
    @Override
    public void DeleteFile() {
        File file = new File(getFilePath() + getFileName());
        file.deleteOnExit();
//        System.out.println("删除成功！");
    }
    @Override
    public void CopyFile(String srcPathStr, String desPathStr) {
        //获取源文件的名称
        String newFileName = srcPathStr.substring(srcPathStr.lastIndexOf("\\")+1); //目标文件地址
//        System.out.println("移动的文件:"+newFileName);
        desPathStr = desPathStr + File.separator + newFileName; //源文件地址
//        System.out.println("目标文件地址:"+desPathStr);
        try
        {
            FileInputStream fis = new FileInputStream(srcPathStr);//创建输入流对象
            FileOutputStream fos = new FileOutputStream(desPathStr); //创建输出流对象
            byte[] datas = new byte[1024*8];//创建搬运工具
            int len;//创建长度
            while((len = fis.read(datas))!=-1)//循环读取数据
            {
                fos.write(datas,0,len);
            }
            fis.close();//释放资源
            fis.close();//释放资源
        }
        catch (Exception e)
        {
            System.out.println();
        }
    }

    /**
     * 文件操作 —— 移动文件
     * 本操作用于 学生Stu 提交submit 作业pjt 给教师Tea 批改mark
     */
    @Override
    public void MoveFile(){
//        System.out.print("请输入要移动的文件名：");
//        System.out.println(getFileName());

//        setFileName(scanner.nextLine());


        filePath = getFilePath() + getFileName();

        try {
            File file = new File(filePath); //源文件

            if (file.renameTo(new File("X:\\Code Projects\\Java\\Projects-SubmissionAndCorrection-System\\SystemTest\\Teacher\\Post\\已发布"))) //源文件移动至目标文件目录
            {
                System.out.println("成功！");//输出移动成功
            } else {
                System.out.println("失败！");//输出移动失败
            }
        } catch (Exception e) {
            System.out.println();
        }
    }

    @Override
    public void readAllLine() {
        filePath = getFilePath() + getFileName();//读取文件的路径
        try { // 防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw
            /* 读入TXT文件 */
            String pathname = filePath; // 绝对路径或相对路径都可以，这里是绝对路径，写入文件时演示相对路径
            File filename = new File(pathname); // 要读取以上路径的input.txt文件
            InputStreamReader reader = new InputStreamReader(new FileInputStream(filename)); // 建立一个输入流对象reader
            BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
            String line;
            line = br.readLine();
            while (line != null) {
                line = br.readLine(); // 一次读入一行数据
                System.out.println(line);
            }
        } catch (Exception e) {
            System.out.println();
        }
    }

    @Override
    public String readFirstLine(){
        //open the file
        FileReader fileReader;
        try {
            fileReader = new FileReader(getFilePath()+getFileName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        //read the 1st line
        String line;
        try {
            line = bufferedReader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //display the 1st line
        return line;
    }

    @Override
    public String[] readFileName(String path) {
        int count = 0;
        //获取文件路径文件夹下的全部文件列表
        System.out.println("文件有如下：");
        //表示一个文件路径
        File file = new File(path);
        //用数组把文件夹下的文件存起来
        File[] files = file.listFiles();
        //foreach遍历数组
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                count++;
                File file2 = files[i];
//                System.out.println("文件名：" + file2.getName());
                RenameFile(path,file2,count);
            }
//            System.out.println("files.length:"+files.length);
        }
        return file.list();
    }
    /**
     * 重命名文件
     */
    public void RenameFile(String path, File files, int count){
        File oldName = new File(String.valueOf(files));
        setTxtname("作业"+ count +"-"+"学号-姓名-专业班级");
        setFileName(getTxtname());
        File newName = new File(path+"\\"+getFileName());

        boolean rename = oldName.renameTo(newName);

//        if (rename){
//            System.out.println("文件重命名成功！");
//        }else {
//            System.out.println("文件重命名失败！");
//        }
    }

    @Override
    public void saveFileName(int[][] array) {
        //1.创建字符输出流
        FileWriter writeFile = null;
        try {
            //2.数据想写入的路径及文件
            File file = new File(getFilePath()+getFileName());
            //3.如果该文件不存在，就创建
            if(!file.exists()) {
                file.createNewFile();
            }
            //4.给字节输出流赋予实例
            writeFile = new FileWriter(file);
            //5.通过循环将数组写入txt文件中
            for(int i = 0; i < array.length; i++) {
                //6.数据前n - 1列尾部加入","
                for(int j = 0; j < array[0].length - 1; j++) {
                    writeFile.write(array[i][j] + ",");
                }
                //7.数组最后一列后面不加","
                writeFile.write(array[i][array[0].length - 1] + "");
                //8.加上换行符
                writeFile.write("\n");
            }
            //9.把writeFile里的数据全部刷新一次，全部写入文件中
            writeFile.flush();
        } catch (Exception e) {//10.异常捕获
            System.out.println();
        } finally {
            try {
                //11.如果writeFile不为空，就将其关闭
                if(writeFile != null)
                    writeFile.close();
            } catch (IOException e) {
                System.out.println();
            }
        }
    }
}


