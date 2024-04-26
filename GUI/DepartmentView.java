package GUI;

import BLL.BLL_Department;

import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import BE.BE_Department;

public class DepartmentView extends JFrame {

    BLL_Department bll_Department = new BLL_Department();
    MyGridBagConstraints gridBagConstraints;

    public DepartmentView() {
        gridBagConstraints = new MyGridBagConstraints();
        setTitle("Department");

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

           /* jLstDepartment.setCellRenderer(new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                    Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    if (renderer instanceof JLabel && value instanceof BE_Department) {
                        // Here value will be of the Type 'CD'
                        ((JLabel) renderer).setText(((BE_Department) value).getName());
                    }
                    return renderer;
                }
            });*/
            jLstDepartment.setPreferredSize(new Dimension(350,50));

            JScrollPane sp = new JScrollPane(jLstDepartment);
            sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

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
                System.out.println(jLstDepartment.getSelectedValue());
                System.out.println(jLstDepartment.getSelectedIndex());
                BE_Department selectedDepartment = jLstDepartment.getSelectedValue();
                jPnlEdit.setBorder(BorderFactory.createTitledBorder("Selected department: " + selectedDepartment.getName()));
                jTxtFieldEdit.setText(selectedDepartment.getName());
            });

            jBtnSave.addActionListener((event) -> {
                try {
                    bll_Department.updateDepartment(jLstDepartment.getSelectedValue());
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    throw new RuntimeException(e);
                }
            });

            add(mainPanel);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        
    }

}
