package GUI;

import BE.BE_Employee;
import BE.BE_Person;

import javax.swing.*;
import java.awt.*;

public class DataView extends JFrame {

    private final BE_Employee employee;
    public DataView(BE_Employee employee) {
        this.employee = employee;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,700); //400 width and 500 height
        setTitle(employee.getPerson().toString());
        setLayout(new BorderLayout());
        setPersonData();
    }

    private void setPersonData() {
        BE_Person person = employee.getPerson();
        JLabel jLblPerson = new JLabel("Person");
        add(jLblPerson);

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

        add(jLblCprNo);
        add(jTxtCprNo);
        add(jLblFirstName);
        add(jTxtFirstName);
        add(jLblLastName);
        add(jTxtLastName);
        add(jLblCountry);
        add(jTxtCountry);
        add(jLblAddress);
        add(jTxtAddress);
        add(jLblCity);
        add(jTxtCity);
        add(jLblZipCode);
        add(jTxtZipCode);
        add(jBtnEdit);
        add(jBtnSave);
    }



}
