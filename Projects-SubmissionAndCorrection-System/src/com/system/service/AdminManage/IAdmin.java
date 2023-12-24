package com.system.service.AdminManage;

/**
 * 作业操作 —— 学生端界面
 */
public interface IAdmin{
    void postPJT();//1.发布作业
    void acceptPJT();//2.查看作业
    void editPJT();//3.编辑作业
    void submitPJT();//4.提交作业
    void markPJT();//5.批改作业

    void SingleGrade();//读取单个作业分数
    void AllGrades();//读取所有作业分数
}
