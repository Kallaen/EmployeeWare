package GUI;

import BLL.BLL_Department;

import java.awt.*;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import BE.BE_Department;

public class DepartmentView extends JFrame {

    BLL_Department bll_Department = new BLL_Department();
    MyGridBagConstraints gridBagConstraints;
    JTable mainTable;

    public DepartmentView(JTable mainTable) {
        gridBagConstraints = new MyGridBagConstraints();
        setTitle("Department");
        this.mainTable = mainTable;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(425,500);
        setLayout();
    }

    private void setLayout() {
        try {
            JPanel mainPanel = new JPanel(new GridBagLayout());
            JPanel jPnlDepartment = new JPanel(new GridBagLayout());
            jPnlDepartment.setBorder(BorderFactory.createTitledBorder("Departments"));

            DefaultListModel<BE_Department> lstModel = new DefaultListModel<>();
            lstModel.addAll(bll_Department.getAllDepartments());
            JList<BE_Department> jLstDepartment = new JList<>(lstModel);

            JScrollPane sp = new JScrollPane(jLstDepartment);
            sp.setPreferredSize(new Dimension(350,150));

            JPanel jPnlEdit = new JPanel(new GridBagLayout());
            jPnlEdit.setBorder(BorderFactory.createTitledBorder("Edit department"));

            JTextField jTxtFieldEdit = new JTextField();
            jTxtFieldEdit.setPreferredSize(new Dimension(200,50));
            jTxtFieldEdit.setFont(new Font("SansSerif", Font.PLAIN, 20));
            JButton jBtnSave = new JButton("Save");
            jBtnSave.setPreferredSize(new Dimension(75,50));
            JButton jBtnDelete = new JButton("Delete");
            jBtnDelete.setPreferredSize(new Dimension(80,50));

            JPanel jPnlAdd = new JPanel(new GridBagLayout());
            jPnlAdd.setBorder(BorderFactory.createTitledBorder("Add new department"));

            JTextField jTxtFieldAdd = new JTextField();
            jTxtFieldAdd.setPreferredSize(new Dimension(280,50));
            jTxtFieldAdd.setFont(new Font("SansSerif", Font.PLAIN, 20));
            JButton jBtnAdd = new JButton("Add");
            jBtnAdd.setPreferredSize(new Dimension(75,50));

            jPnlEdit.add(jTxtFieldEdit, gridBagConstraints.createGbc(0,0));
            jPnlEdit.add(jBtnSave, gridBagConstraints.createGbc(1,0));
            jPnlEdit.add(jBtnDelete, gridBagConstraints.createGbc(2,0));

            jPnlAdd.add(jTxtFieldAdd, gridBagConstraints.createGbc(0,0));
            jPnlAdd.add(jBtnAdd, gridBagConstraints.createGbc(1,0));

            jPnlDepartment.add(sp, gridBagConstraints.createGbc(0,0));

            mainPanel.add(jPnlDepartment, gridBagConstraints.createGbc(0,0));
            mainPanel.add(jPnlEdit, gridBagConstraints.createGbc(0,1));
            mainPanel.add(jPnlAdd, gridBagConstraints.createGbc(0,2));

            jLstDepartment.addListSelectionListener(listSelectionEvent -> {
                if (jLstDepartment.getSelectedValue() != null) {
                    BE_Department selectedDepartment = jLstDepartment.getSelectedValue();
                    setBorderTextAndTextFieldText(jTxtFieldEdit, selectedDepartment.getName(), jPnlEdit, "Selected department: " + selectedDepartment.getName());
                }
            });

            jBtnSave.addActionListener(actionEvent -> {
                try {
                    BE_Department selected = jLstDepartment.getSelectedValue();
                    int selectedIndex = jLstDepartment.getSelectedIndex();
                    if (selected != null) {
                        bll_Department.updateDepartment(selected);

                        selected.setName(jTxtFieldEdit.getText());
                        lstModel.remove(selectedIndex);
                        lstModel.add(selectedIndex, selected);

                        setBorderTextAndTextFieldText(jTxtFieldEdit, "", jPnlEdit, "Edit department");
                        ((AbstractTableModel) mainTable.getModel()).fireTableDataChanged();
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    throw new RuntimeException(e);
                }
            });

            jBtnDelete.addActionListener(actionEvent -> {
                BE_Department selected = jLstDepartment.getSelectedValue();
                try {
                    if (jLstDepartment.getSelectedValue() != null) {
                        int res = JOptionPane.showConfirmDialog(null, "Do you really want to delete, " + selected.getName() + "?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                        if (res == 0) {
                            bll_Department.deleteDepartment(selected);
                            lstModel.remove(jLstDepartment.getSelectedIndex());
                            setBorderTextAndTextFieldText(jTxtFieldEdit, "", jPnlEdit, "Edit department");
                        }
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    throw new RuntimeException(e);
                }
            });

            jBtnAdd.addActionListener(actionEvent -> {
                String departmentName = jTxtFieldAdd.getText();
                if (!departmentName.equalsIgnoreCase("")) {
                    try {
                        BE_Department newDepartment = bll_Department.addDepartment(new BE_Department(0, departmentName));
                        lstModel.add(lstModel.getSize(), newDepartment);
                        jTxtFieldAdd.setText("");
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        throw new RuntimeException(e);
                    }
                }
            });

            add(mainPanel);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void setBorderTextAndTextFieldText(JTextField txtField, String txtFieldText, JPanel pnl, String pnlText) {
        txtField.setText(txtFieldText);
        pnl.setBorder(BorderFactory.createTitledBorder(pnlText));
    }

}
