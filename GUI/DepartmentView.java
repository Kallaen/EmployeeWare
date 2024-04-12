package GUI;

import BLL.BLL_Department;

import java.sql.SQLException;

import javax.swing.*;

import BE.BE_Department;

public class DepartmentView extends JFrame {

    BLL_Department bll_Department = new BLL_Department();

    public DepartmentView() {

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500,400); //400 width and 500 height
        setLayout();
    }

    private void setLayout() {
        try {
            JLabel jLblDepartment = new JLabel();
            DefaultListModel<String> lstModel = new DefaultListModel<>();  

            for (BE_Department department : bll_Department.getAllDepartments()) {
                lstModel.addElement(department.getName());
            }

            JList<String> jLstDepartment = new JList<String>();
            jLstDepartment.setModel(lstModel);

            add(jLstDepartment);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

}
