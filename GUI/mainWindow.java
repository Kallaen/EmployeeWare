package GUI;

import BLL.BLL_Department;
import BLL.BLL_Employee;
import BLL.BLL_Person;
import BLL.EmployeeTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class mainWindow {

    JFrame mainFrame;
    BLL_Department bll_department;
    BLL_Employee bll_employee;
    BLL_Person bll_person;

    public mainWindow()  {
        mainFrame = new JFrame();
        bll_department = new BLL_Department();
        bll_employee = new BLL_Employee();
        bll_person = new BLL_Person();
    }
    public void run() {

        setMenuBar();
        setTable();

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(600,700); //400 width and 500 height
        mainFrame.setVisible(true);
    }

    public void setLayout() {

    }

    public void setMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setMnemonic(KeyEvent.VK_E);
        exitMenuItem.setToolTipText("Exit application");
        exitMenuItem.addActionListener((event) -> System.exit(0));

        JMenuItem searchMenuItem = new JMenuItem("Search");
        exitMenuItem.setMnemonic(KeyEvent.VK_E);
        exitMenuItem.setToolTipText("Search...");

        fileMenu.add(searchMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);

        mainFrame.add(menuBar, BorderLayout.NORTH);
    }

    public void setTable() {
        //bll_employee.getAllPersons()
        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonString = mapper.writeValueAsString(bll_employee.getAllEmployees());
            System.out.println(jsonString);



        } catch (JsonProcessingException | SQLException e) {
            System.out.println(e.toString());
            throw new RuntimeException(e);
        }


        String data[][] = { {"101","Amit","670000"},
                {"102","Jai","780000"},
                {"101","Sachin","700000"}};

        String column[]={"ID","NAME","SALARY"};



        JTable table = new JTable(data,column);
        table.setBounds(30,40,200,300);
        JScrollPane sp = new JScrollPane(table);
        mainFrame.add(sp);
    }
}
