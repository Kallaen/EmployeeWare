package GUI;

import BE.BE_Department;
import BE.BE_Person;
import BLL.BLL_Department;
import BLL.BLL_Employee;
import BLL.BLL_Person;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.sql.SQLException;

public class AddEmployeeView extends JFrame {
    // TODO: Finish add employee
    BLL_Employee bll_Employee = new BLL_Employee();
    BLL_Person bll_Person = new BLL_Person();

    MyGridBagConstraints gridBagConstraints;
    JTable mainTable;

    public AddEmployeeView(JTable mainTable) {
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
            jPnlDepartment.setBorder(BorderFactory.createTitledBorder("Persons"));

            DefaultListModel<BE_Person> lstModel = new DefaultListModel<>();
            lstModel.addAll(bll_Person.getAllPersons());
            JList<BE_Person> jLstPerson = new JList<>(lstModel);

            JScrollPane sp = new JScrollPane(jLstPerson);
            sp.setPreferredSize(new Dimension(350,150));


            jPnlDepartment.add(sp, gridBagConstraints.createGbc(0,0));

            mainPanel.add(jPnlDepartment, gridBagConstraints.createGbc(0,0));

            jLstPerson.addListSelectionListener(listSelectionEvent -> {
                if (jLstPerson.getSelectedValue() != null) {
                    BE_Person selectedPerson = jLstPerson.getSelectedValue();
                    //setBorderTextAndTextFieldText(jTxtFieldEdit, selectedPerson.getName(), jPnlEdit, "Selected department: " + selectedPerson.getName());
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
