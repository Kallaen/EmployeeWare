package GUI;

import BE.BE_Department;
import BE.BE_Person;
import BLL.BLL_Department;
import BLL.BLL_Person;
import BLL.CustomDocumentFilter;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class AddPersonView extends JFrame {
    BLL_Person bll_Person = new BLL_Person();
    MyGridBagConstraints gridBagConstraints;

    public AddPersonView() {
        gridBagConstraints = new MyGridBagConstraints();
        setTitle("Add new person");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(450,450);
        setLayout();
    }

    private void setLayout() {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createTitledBorder("New person"));

        ArrayList<JTextField> allTxtFields = new ArrayList<>();

        JLabel jLblCprNo = new JLabel("Cpr. Number.:");
        JTextField jTxtCprNo = new JTextField();
        ((PlainDocument) jTxtCprNo.getDocument()).setDocumentFilter(new CustomDocumentFilter.NumbersOnlyFilter());
        allTxtFields.add(jTxtCprNo);

        JLabel jLblFirstName = new JLabel("Firstname:");
        JTextField jTxtFirstName = new JTextField();
        ((PlainDocument) jTxtFirstName.getDocument()).setDocumentFilter(new CustomDocumentFilter.CharactersOnlyFilter());
        allTxtFields.add(jTxtFirstName);

        JLabel jLblLastName = new JLabel("Lastname:");
        JTextField jTxtLastName = new JTextField();
        ((PlainDocument) jTxtLastName.getDocument()).setDocumentFilter(new CustomDocumentFilter.CharactersOnlyFilter());
        allTxtFields.add(jTxtLastName);

        JLabel jLblPhoneNo = new JLabel("Phone number:");
        JTextField jTxtPhoneNo = new JTextField();
        ((PlainDocument) jTxtPhoneNo.getDocument()).setDocumentFilter(new CustomDocumentFilter.NumbersOnlyFilter());
        allTxtFields.add(jTxtPhoneNo);

        JLabel jLblEmail = new JLabel("E-mail:");
        JTextField jTxtEmail = new JTextField();
        allTxtFields.add(jTxtEmail);

        JLabel jLblCountry = new JLabel("Country:");
        JTextField jTxtCountry = new JTextField();
        ((PlainDocument) jTxtCountry.getDocument()).setDocumentFilter(new CustomDocumentFilter.CharactersOnlyFilter());
        allTxtFields.add(jTxtCountry);

        JLabel jLblAddress = new JLabel("Address:");
        JTextField jTxtAddress = new JTextField();
        allTxtFields.add(jTxtAddress);

        JLabel jLblCity = new JLabel("City:");
        JTextField jTxtCity = new JTextField();
        ((PlainDocument) jTxtCity.getDocument()).setDocumentFilter(new CustomDocumentFilter.CharactersOnlyFilter());
        allTxtFields.add(jTxtCity);

        JLabel jLblZipCode = new JLabel("Zip code:");
        JTextField jTxtZipCode = new JTextField();
        ((PlainDocument) jTxtZipCode.getDocument()).setDocumentFilter(new CustomDocumentFilter.NumbersOnlyFilter());
        allTxtFields.add(jTxtZipCode);

        JButton jBtnSave = new JButton("Save");

        ArrayList<JLabel> allErrorLabels = new ArrayList<>();
        for (JTextField txt : allTxtFields) {
            txt.setPreferredSize(new Dimension(150,20));
            allErrorLabels.add(createNewErrorLabel());
        }

        mainPanel.add(jLblCprNo, gridBagConstraints.createGbc(0,0));
        mainPanel.add(jTxtCprNo, gridBagConstraints.createGbc(1,0));
        mainPanel.add(allErrorLabels.get(0), gridBagConstraints.createGbc(2,0));

        mainPanel.add(jLblFirstName, gridBagConstraints.createGbc(0,1));
        mainPanel.add(jTxtFirstName, gridBagConstraints.createGbc(1,1));
        mainPanel.add(allErrorLabels.get(1), gridBagConstraints.createGbc(2,1));

        mainPanel.add(jLblLastName, gridBagConstraints.createGbc(0,2));
        mainPanel.add(jTxtLastName, gridBagConstraints.createGbc(1, 2));
        mainPanel.add(allErrorLabels.get(2), gridBagConstraints.createGbc(2,2));

        mainPanel.add(jLblPhoneNo, gridBagConstraints.createGbc(0,3));
        mainPanel.add(jTxtPhoneNo, gridBagConstraints.createGbc(1,3));
        mainPanel.add(allErrorLabels.get(3), gridBagConstraints.createGbc(2,3));

        mainPanel.add(jLblEmail, gridBagConstraints.createGbc(0,4));
        mainPanel.add(jTxtEmail, gridBagConstraints.createGbc(1,4));
        mainPanel.add(allErrorLabels.get(4), gridBagConstraints.createGbc(2,4));

        mainPanel.add(jLblCountry, gridBagConstraints.createGbc(0,5));
        mainPanel.add(jTxtCountry, gridBagConstraints.createGbc(1,5));
        mainPanel.add(allErrorLabels.get(5), gridBagConstraints.createGbc(2,5));

        mainPanel.add(jLblAddress, gridBagConstraints.createGbc(0,6));
        mainPanel.add(jTxtAddress, gridBagConstraints.createGbc(1,6));
        mainPanel.add(allErrorLabels.get(6), gridBagConstraints.createGbc(2,6));

        mainPanel.add(jLblCity, gridBagConstraints.createGbc(0,7));
        mainPanel.add(jTxtCity, gridBagConstraints.createGbc(1,7));
        mainPanel.add(allErrorLabels.get(7), gridBagConstraints.createGbc(2,7));

        mainPanel.add(jLblZipCode, gridBagConstraints.createGbc(0,8));
        mainPanel.add(jTxtZipCode, gridBagConstraints.createGbc(1,8));
        mainPanel.add(allErrorLabels.get(8), gridBagConstraints.createGbc(2,8));

        mainPanel.add(jBtnSave, gridBagConstraints.createGbc(2,9));

        jBtnSave.addActionListener((actionEvent) -> {
            BE_Person p = new BE_Person(0, jTxtCprNo.getText(), jTxtFirstName.getText(), jTxtLastName.getText(), jTxtPhoneNo.getText(), jTxtEmail.getText(), jTxtCountry.getText(), jTxtAddress.getText(), jTxtCity.getText(), jTxtZipCode.getText());
            try {
                boolean accept = true;
                for (int i = 0; i < allTxtFields.size(); i++)
                    if (allTxtFields.get(i).getText().isBlank()) {
                        allErrorLabels.get(i).setVisible(true);
                        accept = false;
                    }
                /*Pattern pattern = Pattern.compile( "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

                if (!pattern.matcher(jTxtEmail.getText()).matches())
                    accept = false;*/

                if (accept) {
                    p = bll_Person.addPerson(p);
                    if (p.getId() > 0) {
                        JOptionPane.showMessageDialog(this, "Succesfully created person", "New person", JOptionPane.INFORMATION_MESSAGE);
                        this.dispose();
                    }
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        });

        add(mainPanel);
    }

    private JLabel createNewErrorLabel() {
        JLabel jLblError = new JLabel("* Field is required.");
        jLblError.setForeground(Color.RED);
        jLblError.setVisible(false);

        return jLblError;
    }
}
