package GUI;

import BLL.BLL_Department;

import java.awt.*;
import java.sql.SQLException;

import javax.swing.*;

import BE.BE_Department;

public class DepartmentView extends JFrame {

    BLL_Department bll_Department = new BLL_Department();

    public DepartmentView() {
        setTitle("Department");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500,400); //400 width and 500 height
        setLayout();
    }

    private void setLayout() {
        try {
            JPanel jPnlDepartment = new JPanel(new GridBagLayout());

            DefaultListModel<String> lstModel = new DefaultListModel<>();

            for (BE_Department department : bll_Department.getAllDepartments()) {
                lstModel.addElement(department.getName());
            }

            JList<String> jLstDepartment = new JList<String>();
            jLstDepartment.addListSelectionListener(listSelectionEvent -> {
                System.out.println(jLstDepartment.getSelectedIndex());
            });
            jLstDepartment.setModel(lstModel);
            jPnlDepartment.add(jLstDepartment);

            // TODO: SAVE/EDIT functionality
            add(jPnlDepartment);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        
    }

}
