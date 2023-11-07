package StudentData;

/*
    定义学生类
 */
public class Student {
    private String name;
    //姓名
    private String sex;
    //性别
    private String major;
    //专业
    private String stuid;
    //学号
    public Student(){}
    public Student(String name, String sex, String major, String stuid){
        //初始化
        this.name = name;
        this.sex = sex;
        this.major = major;
        this.stuid = stuid;
    }
    public String getName(){
        //获取姓名
        return name;
    }
    public String getSex(){
        //获取性别
        return sex;
    }
    public String getMajor(){
        //获取专业
        return major;
    }
    public String getStuid(){
        //获取学号
        return stuid;
    }

}
