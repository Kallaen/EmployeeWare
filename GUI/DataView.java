package GUI;

import BE.BE_Employee;
import BE.BE_Person;

import javax.swing.*;
import java.awt.*;

public class DataView extends JFrame {

    private final BE_Employee employee;
    public DataView(BE_Employee employee) {
        this.employee = employee;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400,300); //400 width and 500 height
        setTitle(employee.getPerson().toString());
        setLayout(new GridLayout(0,2));
        setPersonData();
        setEmployeeData();
    }

    private void setEmployeeData() {
        JLabel jLblEmployee = new JLabel("Employee");

        JLabel jLblDepartment = new JLabel("Department");
        JTextField jTxtDepartment = new JTextField(employee.getDepartment().getName());

        JLabel jLblJobTitle = new JLabel("Job title");
        JTextField jTxtJobTitle = new JTextField(employee.getJobTitle());

        JLabel jLblEmergencyContactName = new JLabel("Emergency contact");
        JTextField jTxtEmergencyContactName = new JTextField(employee.getEmergencyContactName());

        JLabel jLblEmergencyContactNo = new JLabel("Emergency contact number");
        JTextField jTxtEmergencyContactNo = new JTextField(employee.getEmergencyContactNo());

        JLabel jLblStartEmploymentDate = new JLabel("Start date");
        JTextField jTxtStartEmploymentDate = new JTextField(String.valueOf(employee.getStartEmploymentDate()));

        JLabel jLblEndEmploymentDate = new JLabel("End date");
        JTextField jTxtEndEmploymentDate = new JTextField(String.valueOf(employee.getEndEmploymentDate()));

        JPanel jPnlEmployee = new JPanel(new GridLayout(7, 1));

        jPnlEmployee.add(jLblEmployee);

        JPanel jPnlDepartment = new JPanel(new FlowLayout());
        jPnlDepartment.add(jLblDepartment);
        jPnlDepartment.add(jTxtDepartment);
        jPnlEmployee.add(jPnlDepartment);

        JPanel jPnlJobTitle = new JPanel(new FlowLayout());
        jPnlJobTitle.add(jLblJobTitle);
        jPnlJobTitle.add(jTxtJobTitle);
        jPnlEmployee.add(jPnlJobTitle);

        JPanel jPnlEmergencyContactName = new JPanel(new FlowLayout());
        jPnlEmergencyContactName.add(jLblEmergencyContactName);
        jPnlEmergencyContactName.add(jTxtEmergencyContactName);
        jPnlEmployee.add(jPnlEmergencyContactName);

        JPanel jPnlEmergencyContactNo = new JPanel(new FlowLayout());
        jPnlEmergencyContactNo.add(jLblEmergencyContactNo);
        jPnlEmergencyContactNo.add(jTxtEmergencyContactNo);
        jPnlEmployee.add(jPnlEmergencyContactNo);

        JPanel jPnlStartEmploymentDate = new JPanel(new FlowLayout());
        jPnlStartEmploymentDate.add(jLblStartEmploymentDate);
        jPnlStartEmploymentDate.add(jTxtStartEmploymentDate);
        jPnlEmployee.add(jPnlStartEmploymentDate);

        JPanel jPnlEndEmploymentDate = new JPanel(new FlowLayout());
        jPnlEndEmploymentDate.add(jLblEndEmploymentDate);
        jPnlEndEmploymentDate.add(jTxtEndEmploymentDate);
        jPnlEmployee.add(jPnlEndEmploymentDate);

        add(jPnlEmployee);
    }

    private void setPersonData() {
        BE_Person person = employee.getPerson();      

        JLabel jLblCprNo = new JLabel("CPR Number");
        JTextField jTxtCprNo = new JTextField(person.getCprNo());
        jTxtCprNo.setEditable(false);

        JLabel jLblFirstName = new JLabel("First name:");
        JTextField jTxtFirstName = new JTextField(person.getFirstName());
        jTxtFirstName.setEditable(false);

        JLabel jLblLastName = new JLabel("Last name:");
        JTextField jTxtLastName = new JTextField(person.getLastName());
        jTxtLastName.setEditable(false);

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
        jBtnSave.setVisible(false);
        jBtnEdit.addActionListener(actionEvent -> {
            int res = JOptionPane.showConfirmDialog(null, "Do you really want to edit?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (res == 0) {
                jTxtFirstName.setEditable(true);
                jTxtLastName.setEditable(true);
                jTxtCountry.setEditable(true);
                jTxtAddress.setEditable(true);
                jTxtCity.setEditable(true);
                jTxtZipCode.setEditable(true);

                jBtnSave.setVisible(true);
                jBtnEdit.setVisible(false);
            }
        });

        JPanel jPnlPerson = new JPanel(new GridLayout(7,1));
        JLabel jLblPerson = new JLabel("Person");
        jPnlPerson.add(jLblPerson);

        JPanel jPnlCprNo = new JPanel(new FlowLayout());
        jPnlCprNo.add(jLblCprNo);
        jPnlCprNo.add(jTxtCprNo);
        jPnlPerson.add(jPnlCprNo);

        JPanel jPnlFirstName = new JPanel(new FlowLayout());
        jPnlFirstName.add(jLblFirstName);
        jPnlFirstName.add(jTxtFirstName);
        jPnlFirstName.add(jLblLastName);
        jPnlFirstName.add(jTxtLastName);
        jPnlPerson.add(jPnlFirstName);

        JPanel jPnlAddress = new JPanel(new FlowLayout());
        jPnlAddress.add(jLblCountry);
        jPnlAddress.add(jTxtCountry);
        jPnlAddress.add(jLblAddress);
        jPnlAddress.add(jTxtAddress);
        jPnlPerson.add(jPnlAddress);
    
        JPanel jPnlCity = new JPanel(new FlowLayout());
        jPnlCity.add(jLblCity);
        jPnlCity.add(jTxtCity);
        jPnlCity.add(jLblZipCode);
        jPnlCity.add(jTxtZipCode);
        jPnlPerson.add(jPnlCity);

        JPanel jPnlBtn = new JPanel(new FlowLayout());
        jPnlBtn.add(jBtnEdit);
        jPnlBtn.add(jBtnSave);
        jPnlPerson.add(jPnlBtn);

        add(jPnlPerson);
    }



}
