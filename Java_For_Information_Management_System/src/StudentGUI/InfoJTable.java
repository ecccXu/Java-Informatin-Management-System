package StudentGUI;
import java.awt.*;
import javax.swing.*;
public class InfoJTable {
    JTable table1 = new JTable(12 , 4);     //创建12行4列的空表格
    final Object[] columnNames = {"姓名", "性别", "专业", "学号"};  //定义列名
    //定义行数据
    Object[][] rowData = {{"家小瓦", "男", "物联网工程", "22034560123"},
            {"派小森", "女", "计算机科学与技术", "22022560333"}};
    //创建表格
    JTable table2 = new JTable(rowData, columnNames);
    //创建表格属性
    table2.setRowHeight(30);
    table2.setRowHeight(0, 20);

}
