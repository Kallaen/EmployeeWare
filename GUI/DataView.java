package GUI;

import BE.BE_Department;
import BE.BE_Employee;
import BE.BE_Person;
import BLL.BLL_Department;
import BLL.BLL_Employee;
import BLL.BLL_Person;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class DataView extends JFrame {
    // TODO: Delete employee / person
    BLL_Department bll_department;
    BLL_Employee bll_employee;
    BLL_Person bll_person;
    JTable mainTable;
    MyGridBagConstraints gridBagConstraints;

    private final BE_Employee employee;

    public DataView(JTable table, BE_Employee employee) {
        this.employee = employee;

        bll_department = new BLL_Department();
        bll_employee = new BLL_Employee();
        bll_person = new BLL_Person();
        gridBagConstraints = new MyGridBagConstraints();

        mainTable = table;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800,400);
        setTitle(employee.getPerson().toString());
        setLayout(new GridLayout(0,2));
        setPersonData();
        setEmployeeData();
    }

    private void setEmployeeData() {
        try {
            JPanel jPnlEmployee = new JPanel(new GridBagLayout());
            jPnlEmployee.setBorder(BorderFactory.createTitledBorder("Employee"));

            ArrayList<Object> components = new ArrayList<>();

            JLabel jLblDepartment = new JLabel("Department");
            JComboBox<Object> jCBXDepartment = new JComboBox<>(bll_department.getAllDepartments().toArray());
            jCBXDepartment.setSelectedItem(employee.getDepartment());
            jCBXDepartment.setEnabled(false);
            components.add(jCBXDepartment);

            JLabel jLblJobTitle = new JLabel("Job title");
            JTextField jTxtJobTitle = new JTextField(employee.getJobTitle());
            jTxtJobTitle.setEditable(false);
            components.add(jTxtJobTitle);

            JLabel jLblEmergencyContactName = new JLabel("Emergency contact");
            JTextField jTxtEmergencyContactName = new JTextField(employee.getEmergencyContactName());
            jTxtEmergencyContactName.setEditable(false);
            components.add(jTxtEmergencyContactName);

            JLabel jLblEmergencyContactNo = new JLabel("Emergency contact number");
            JTextField jTxtEmergencyContactNo = new JTextField(employee.getEmergencyContactNo());
            jTxtEmergencyContactNo.setEditable(false);
            components.add(jTxtEmergencyContactNo);

            JLabel jLblStartEmploymentDate = new JLabel("Start date");
            JTextField jTxtStartEmploymentDate = new JTextField(String.valueOf(employee.getStartEmploymentDate() == null ? "" : employee.getStartEmploymentDate()));
            jTxtStartEmploymentDate.setEditable(false);
            components.add(jTxtStartEmploymentDate);

            JLabel jLblEndEmploymentDate = new JLabel("End date");
            JTextField jTxtEndEmploymentDate = new JTextField(String.valueOf(employee.getEndEmploymentDate() == null ? "" : employee.getEndEmploymentDate()));
            jTxtEndEmploymentDate.setEditable(false);
            components.add(jTxtEndEmploymentDate);

            JButton jBtnEdit = new JButton("Edit");
            JButton jBtnSave = new JButton("Save");

            jPnlEmployee.add(jLblDepartment, gridBagConstraints.createGbc(0,0));
            jPnlEmployee.add(jCBXDepartment, gridBagConstraints.createGbc(1,0));

            jPnlEmployee.add(jLblJobTitle, gridBagConstraints.createGbc(0,1));
            jPnlEmployee.add(jTxtJobTitle, gridBagConstraints.createGbc(1,1));

            jPnlEmployee.add(jLblEmergencyContactName, gridBagConstraints.createGbc(0,2));
            jPnlEmployee.add(jTxtEmergencyContactName, gridBagConstraints.createGbc(1,2));

            jPnlEmployee.add(jLblEmergencyContactNo, gridBagConstraints.createGbc(0,3));
            jPnlEmployee.add(jTxtEmergencyContactNo, gridBagConstraints.createGbc(1,3));

            jPnlEmployee.add(jLblStartEmploymentDate, gridBagConstraints.createGbc(0,4));
            jPnlEmployee.add(jTxtStartEmploymentDate, gridBagConstraints.createGbc(1,4));

            jPnlEmployee.add(jLblEndEmploymentDate, gridBagConstraints.createGbc(0,5));
            jPnlEmployee.add(jTxtEndEmploymentDate, gridBagConstraints.createGbc(1,5));

            JPanel jPnlButtons = new JPanel(new FlowLayout());
            jPnlButtons.add(jBtnEdit);
            jPnlButtons.add(jBtnSave);
            jPnlEmployee.add(jPnlButtons, gridBagConstraints.createGbc(0, 6));

            jBtnEdit.addActionListener(actionEvent -> {
                int res = JOptionPane.showConfirmDialog(null, "Do you really want to edit?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (res == 0) {
                    setEditableOfJComponents(components, true);

                    jBtnSave.setVisible(true);
                    jBtnEdit.setVisible(false);
                }
            });

            ArrayList<String> textFieldsCheckArr = getTextOfJComponents(components);

            jBtnSave.setVisible(false);
            jBtnSave.addActionListener(actionEvent -> {
                BE_Department cbxData = (BE_Department) jCBXDepartment.getItemAt(jCBXDepartment.getSelectedIndex());
                var textFieldChangesArr = getTextOfJComponents(components);
                if (!textFieldsCheckArr.equals(textFieldChangesArr) || cbxData.getName() != employee.getDepartment().getName()) {
                    int res = JOptionPane.showConfirmDialog(null, "Do you really want to SAVE?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                    if (res == 0) {
                        employee.setDepartment(cbxData);
                        employee.setDepartmentId(cbxData.getId());
                        employee.setJobTitle(jTxtJobTitle.getText());
                        employee.setEmergencyContactName(jTxtEmergencyContactName.getText());
                        employee.setEmergencyContactNo(jTxtEmergencyContactNo.getText());
                        employee.setStartEmploymentDate(!jTxtStartEmploymentDate.getText().equals("") ? Date.valueOf(jTxtStartEmploymentDate.getText()) : null);
                        employee.setEndEmploymentDate(!jTxtEndEmploymentDate.getText().equals("") ? Date.valueOf(jTxtEndEmploymentDate.getText()) : null);

                        try {
                            bll_employee.updateEmployee(employee);
                            ((AbstractTableModel) mainTable.getModel()).fireTableDataChanged();
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                            throw new RuntimeException(e);
                        }
                    }
                }

                setEditableOfJComponents(components, false);

                jBtnEdit.setVisible(true);
                jBtnSave.setVisible(false);
            });

            add(jPnlEmployee);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }
    }
    private void setPersonData() {
        BE_Person person = employee.getPerson();
        JPanel jPnlPerson = new JPanel(new GridBagLayout());
        jPnlPerson.setBorder(BorderFactory.createTitledBorder("Person"));

        ArrayList<Object> components = new ArrayList<>();

        JLabel jLblCprNo = new JLabel("CPR number");
        JTextField jTxtCprNo = new JTextField(person.getCprNo());
        jTxtCprNo.setEditable(false);
        components.add(jTxtCprNo);

        JLabel jLblFirstName = new JLabel("First name:");
        JTextField jTxtFirstName = new JTextField(person.getFirstName());
        jTxtFirstName.setEditable(false);
        components.add(jTxtFirstName);

        JLabel jLblLastName = new JLabel("Last name:");
        JTextField jTxtLastName = new JTextField(person.getLastName());
        jTxtLastName.setEditable(false);
        components.add(jTxtLastName);

        JLabel jLblPhoneNo = new JLabel("Phone number:");
        JTextField jTxtPhoneNo = new JTextField(person.getPhoneNo());
        jTxtPhoneNo.setEditable(false);
        components.add(jTxtPhoneNo);

        JLabel jLblEmail = new JLabel("Email:");
        JTextField jTxtEmail = new JTextField(person.getEmail());
        jTxtEmail.setEditable(false);
        components.add(jTxtEmail);

        JLabel jLblCountry = new JLabel("Country:");
        JTextField jTxtCountry = new JTextField(person.getCountry());
        jTxtCountry.setEditable(false);
        components.add(jTxtCountry);

        JLabel jLblAddress = new JLabel("Address:");
        JTextField jTxtAddress = new JTextField(person.getAddress());
        jTxtAddress.setEditable(false);
        components.add(jTxtAddress);

        JLabel jLblCity = new JLabel("City:");
        JTextField jTxtCity = new JTextField(person.getCity());
        jTxtCity.setEditable(false);
        components.add(jTxtCity);

        JLabel jLblZipCode = new JLabel("Zip code:");
        JTextField jTxtZipCode = new JTextField(person.getZipCode());
        jTxtZipCode.setEditable(false);
        components.add(jTxtZipCode);

        JButton jBtnEdit = new JButton("Edit");
        JButton jBtnSave = new JButton("Save");
        JButton jBtnCancel = new JButton("Cancel");

        jPnlPerson.add(jLblCprNo, gridBagConstraints.createGbc(0,0));
        jPnlPerson.add(jTxtCprNo, gridBagConstraints.createGbc(1,0));

        jPnlPerson.add(jLblFirstName, gridBagConstraints.createGbc(0,1));
        jPnlPerson.add(jTxtFirstName, gridBagConstraints.createGbc(1,1));
        jPnlPerson.add(jLblLastName, gridBagConstraints.createGbc(0,2));
        jPnlPerson.add(jTxtLastName, gridBagConstraints.createGbc(1,2));

        jPnlPerson.add(jLblPhoneNo, gridBagConstraints.createGbc(0,3));
        jPnlPerson.add(jTxtPhoneNo, gridBagConstraints.createGbc(1,3));

        jPnlPerson.add(jLblEmail, gridBagConstraints.createGbc(0,4));
        jPnlPerson.add(jTxtEmail, gridBagConstraints.createGbc(1,4));

        jPnlPerson.add(jLblCountry, gridBagConstraints.createGbc(0,5));
        jPnlPerson.add(jTxtCountry, gridBagConstraints.createGbc(1,5));
        jPnlPerson.add(jLblAddress, gridBagConstraints.createGbc(0,6));
        jPnlPerson.add(jTxtAddress, gridBagConstraints.createGbc(1,6));

        jPnlPerson.add(jLblCity, gridBagConstraints.createGbc(0,7));
        jPnlPerson.add(jTxtCity, gridBagConstraints.createGbc(1,7));
        jPnlPerson.add(jLblZipCode, gridBagConstraints.createGbc(0,8));
        jPnlPerson.add(jTxtZipCode, gridBagConstraints.createGbc(1,8));

        JPanel jPnlButtons = new JPanel(new FlowLayout());
        jPnlButtons.add(jBtnEdit);
        jPnlButtons.add(jBtnSave);
        jPnlButtons.add(jBtnCancel);
        jPnlPerson.add(jPnlButtons, gridBagConstraints.createGbc(0, 10));

        jBtnEdit.addActionListener(actionEvent -> {
            int res = JOptionPane.showConfirmDialog(null, "Do you really want to edit?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (res == 0) {
                setEditableOfJComponents(components, true);

                jBtnSave.setVisible(true);
                jBtnCancel.setVisible(true);
                jBtnEdit.setVisible(false);
            }
        });

        ArrayList<String> textFieldsCheckArr = getTextOfJComponents(components);

        jBtnSave.setVisible(false);
        jBtnSave.addActionListener(actionEvent -> {
            var textFieldChangesArr = getTextOfJComponents(components);
            if (!textFieldsCheckArr.equals(textFieldChangesArr)) {

                int res = JOptionPane.showConfirmDialog(null, "Do you really want to SAVE?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (res == 0) {
                    person.setCprNo(jTxtCprNo.getText());
                    person.setFirstName(jTxtFirstName.getText());
                    person.setLastName(jTxtLastName.getText());
                    person.setPhoneNo(jTxtPhoneNo.getText());
                    person.setEmail(jTxtEmail.getText());
                    person.setCountry(jTxtCountry.getText());
                    person.setAddress(jTxtAddress.getText());
                    person.setCity(jTxtCity.getText());
                    person.setZipCode(jTxtZipCode.getText());

                    try {
                        bll_person.updatePerson(person);
                        mainTable.updateUI();
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        throw new RuntimeException(e);
                    }
                }


            }
            setEditableOfJComponents(components, false);

            jBtnEdit.setVisible(true);
            jBtnSave.setVisible(false);
            jBtnCancel.setVisible(false);
        });
        jBtnCancel.setVisible(false);

        add(jPnlPerson);
    }

    private static void setEditableOfJComponents(ArrayList<Object> components, boolean editable) {
        for (Object o : components) {
            if (o instanceof JTextField)
                ((JTextField) o).setEditable(editable);
            if (o instanceof JComboBox)
                ((JComboBox<?>) o).setEnabled(editable);
        }
    }

    private static ArrayList<String> getTextOfJComponents(ArrayList<Object> components) {
        ArrayList<String> texts = new ArrayList<>();
        for (Object o : components) {
            if (o instanceof JTextField)
                texts.add(((JTextField) o).getText());
            if (o instanceof JComboBox)
                texts.add(Objects.requireNonNull(((JComboBox<?>) o).getSelectedItem()).toString());

        }
        return texts;
    }
}
