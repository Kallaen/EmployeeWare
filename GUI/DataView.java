package GUI;

import BE.BE_Employee;
import BE.BE_Person;
import BLL.BLL_Department;
import BLL.BLL_Employee;
import BLL.BLL_Person;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataView extends JFrame {
    BLL_Department bll_department;
    BLL_Employee bll_employee;
    BLL_Person bll_person;
    private final BE_Employee employee;
    public DataView(BE_Employee employee) {
        this.employee = employee;

        bll_department = new BLL_Department();
        bll_employee = new BLL_Employee();
        bll_person = new BLL_Person();

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

            JLabel jLblDepartment = new JLabel("Department");
            JComboBox<Object> jCBXDepartment = new JComboBox<>(bll_department.getAllDepartments().toArray());
            jCBXDepartment.setSelectedItem(employee.getDepartment());
            jCBXDepartment.addActionListener(actionEvent -> {
                /*String data = "Programming language Selected: "
                        + cb.getItemAt(cb.getSelectedIndex());
                label.setText(data);*/
            });
            jCBXDepartment.setEnabled(false);

            JLabel jLblJobTitle = new JLabel("Job title");
            JTextField jTxtJobTitle = new JTextField(employee.getJobTitle());
            jTxtJobTitle.setEditable(false);


            JLabel jLblEmergencyContactName = new JLabel("Emergency contact");
            JTextField jTxtEmergencyContactName = new JTextField(employee.getEmergencyContactName());
            jTxtEmergencyContactName.setEditable(false);

            JLabel jLblEmergencyContactNo = new JLabel("Emergency contact number");
            JTextField jTxtEmergencyContactNo = new JTextField(employee.getEmergencyContactNo());
            jTxtEmergencyContactNo.setEditable(false);

            JLabel jLblStartEmploymentDate = new JLabel("Start date");
            JTextField jTxtStartEmploymentDate = new JTextField(String.valueOf(employee.getStartEmploymentDate() == null ? "" : employee.getStartEmploymentDate()));
            jTxtStartEmploymentDate.setEditable(false);

            JLabel jLblEndEmploymentDate = new JLabel("End date");
            JTextField jTxtEndEmploymentDate = new JTextField(String.valueOf(employee.getEndEmploymentDate() == null ? "" : employee.getEndEmploymentDate()));
            jTxtEndEmploymentDate.setEditable(false);

            JButton jBtnEdit = new JButton("Edit");
            JButton jBtnSave = new JButton("Save");

            jPnlEmployee.add(jLblDepartment, createGbc(0,0));
            jPnlEmployee.add(jCBXDepartment, createGbc(1,0));

            jPnlEmployee.add(jLblJobTitle, createGbc(0,1));
            jPnlEmployee.add(jTxtJobTitle, createGbc(1,1));

            jPnlEmployee.add(jLblEmergencyContactName, createGbc(0,2));
            jPnlEmployee.add(jTxtEmergencyContactName, createGbc(1,2));

            jPnlEmployee.add(jLblEmergencyContactNo, createGbc(0,3));
            jPnlEmployee.add(jTxtEmergencyContactNo, createGbc(1,3));

            jPnlEmployee.add(jLblStartEmploymentDate, createGbc(0,4));
            jPnlEmployee.add(jTxtStartEmploymentDate, createGbc(1,4));

            jPnlEmployee.add(jLblEndEmploymentDate, createGbc(0,5));
            jPnlEmployee.add(jTxtEndEmploymentDate, createGbc(1,5));

            JPanel jPnlButtons = new JPanel(new FlowLayout());
            jPnlButtons.add(jBtnEdit);
            jPnlButtons.add(jBtnSave);
            jPnlEmployee.add(jPnlButtons, createGbc(0, 6));

            jBtnSave.setVisible(false);
            jBtnSave.addActionListener(actionEvent -> {
                int res = JOptionPane.showConfirmDialog(null, "Do you really want to SAVE?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (res == 0) {
                    setEditableOfPanelJTextFields(jPnlEmployee, false);
                    jCBXDepartment.setEnabled(false);

                    jBtnEdit.setVisible(true);
                    jBtnSave.setVisible(false);
                }
            });
            jBtnEdit.addActionListener(actionEvent -> {
                int res = JOptionPane.showConfirmDialog(null, "Do you really want to edit?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (res == 0) {
                    setEditableOfPanelJTextFields(jPnlEmployee, true);
                    jCBXDepartment.setEnabled(true);

                    jBtnSave.setVisible(true);
                    jBtnEdit.setVisible(false);
                }
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

        JLabel jLblCprNo = new JLabel("CPR number");
        JTextField jTxtCprNo = new JTextField(person.getCprNo());
        jTxtCprNo.setEditable(false);

        JLabel jLblFirstName = new JLabel("First name:");
        JTextField jTxtFirstName = new JTextField(person.getFirstName());
        jTxtFirstName.setEditable(false);

        JLabel jLblLastName = new JLabel("Last name:");
        JTextField jTxtLastName = new JTextField(person.getLastName());
        jTxtLastName.setEditable(false);

        JLabel jLblPhoneNo = new JLabel("Phone number:");
        JTextField jTxtPhoneNo = new JTextField(person.getPhoneNo());
        jTxtPhoneNo.setEditable(false);

        JLabel jLblEmail = new JLabel("Email:");
        JTextField jTxtEmail = new JTextField(person.getEmail());
        jTxtEmail.setEditable(false);

        JLabel jLblCountry = new JLabel("Country:");
        JTextField jTxtCountry = new JTextField(person.getCountry());
        jTxtCountry.setEditable(false);

        JLabel jLblAddress = new JLabel("Address:");
        JTextField jTxtAddress = new JTextField(person.getAddress());
        jTxtAddress.setEditable(false);

        JLabel jLblCity = new JLabel("City:");
        JTextField jTxtCity = new JTextField(person.getCity());
        jTxtCity.setEditable(false);

        JLabel jLblZipCode = new JLabel("Zip code:");
        JTextField jTxtZipCode = new JTextField(person.getZipCode());
        jTxtZipCode.setEditable(false);

        JButton jBtnEdit = new JButton("Edit");
        JButton jBtnSave = new JButton("Save");
        JButton jBtnCancel = new JButton("Cancel");

        jPnlPerson.add(jLblCprNo, createGbc(0,0));
        jPnlPerson.add(jTxtCprNo, createGbc(1,0));

        jPnlPerson.add(jLblFirstName, createGbc(0,1));
        jPnlPerson.add(jTxtFirstName, createGbc(1,1));
        jPnlPerson.add(jLblLastName, createGbc(0,2));
        jPnlPerson.add(jTxtLastName, createGbc(1,2));

        jPnlPerson.add(jLblPhoneNo, createGbc(0,3));
        jPnlPerson.add(jTxtPhoneNo, createGbc(1,3));

        jPnlPerson.add(jLblEmail, createGbc(0,4));
        jPnlPerson.add(jTxtEmail, createGbc(1,4));

        jPnlPerson.add(jLblCountry, createGbc(0,5));
        jPnlPerson.add(jTxtCountry, createGbc(1,5));
        jPnlPerson.add(jLblAddress, createGbc(0,6));
        jPnlPerson.add(jTxtAddress, createGbc(1,6));

        jPnlPerson.add(jLblCity, createGbc(0,7));
        jPnlPerson.add(jTxtCity, createGbc(1,7));
        jPnlPerson.add(jLblZipCode, createGbc(0,8));
        jPnlPerson.add(jTxtZipCode, createGbc(1,8));

        JPanel jPnlButtons = new JPanel(new FlowLayout());
        jPnlButtons.add(jBtnEdit);
        jPnlButtons.add(jBtnSave);
        jPnlButtons.add(jBtnCancel);
        jPnlPerson.add(jPnlButtons, createGbc(0, 10));

        jBtnEdit.addActionListener(actionEvent -> {
            int res = JOptionPane.showConfirmDialog(null, "Do you really want to edit?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (res == 0) {
                setEditableOfPanelJTextFields(jPnlPerson, true);

                jBtnSave.setVisible(true);
                jBtnCancel.setVisible(true);
                jBtnEdit.setVisible(false);
            }
        });

        ArrayList<String> textFieldsCheckArr = getTextOfPanelJTextFields(jPnlPerson);

        jBtnSave.setVisible(false);
        jBtnSave.addActionListener(actionEvent -> {
            var textFieldChangesArr = getTextOfPanelJTextFields(jPnlPerson);
            if (!textFieldsCheckArr.equals(textFieldChangesArr)) {
                textFieldChangesArr.removeAll(textFieldsCheckArr);
                String followingTextChanged = "";

                int res = JOptionPane.showConfirmDialog(null, "Do you really want to SAVE?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                //if (res == 0)


            }
            setEditableOfPanelJTextFields(jPnlPerson, false);

            jBtnEdit.setVisible(true);
            jBtnSave.setVisible(false);
            jBtnCancel.setVisible(false);
        });
        jBtnCancel.setVisible(false);

        add(jPnlPerson);
    }

    private static GridBagConstraints createGbc(int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        int gap = 3;
        gbc.insets = new Insets(gap, gap + 2 * gap * x, gap, gap);
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.weighty = 0.5;
        return gbc;
    }

    private static void setEditableOfPanelJTextFields(Container container, boolean editable) {
        for (Component component : container.getComponents()) {
            if (component instanceof JTextField) {
                ((JTextField) component).setEditable(editable);
            }
        }
    }

    private static ArrayList<String> getTextOfPanelJTextFields(Container container) {
        ArrayList<String> texts = new ArrayList<>();
        for (Component component : container.getComponents()) {
            if (component instanceof JTextField) {
                texts.add(((JTextField) component).getText());
            }
        }
        return texts;
    }
}
