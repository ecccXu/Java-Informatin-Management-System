package com.system.service.ProjectManage;

import java.io.IOException;
import java.util.Scanner;

interface IProject {
    void CreatFile(String path, String name);//新建文件

    boolean SearchFile(String filename);//查找文件

    void ModifyFile(Scanner scanner) throws IOException;//修改文件

    void DeleteFile();//删除文件

    void CopyFile(String srcPathStr, String desPathStr);//复制文件

    void MoveFile();//移动文件

    /**
     * 读取文件内容
     */
    void readAllLine();//读取文件全部内容

    String readFirstLine();

    String[] readFileName(String path);//读取path路径下所有的文件名
    void saveFileName(int[][] array);//储存path路径下所有的文件名

}
