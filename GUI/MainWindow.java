package GUI;

import BLL.BLL_Department;
import BLL.BLL_Employee;
import BLL.BLL_Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class MainWindow extends JFrame {

    BLL_Department bll_department;
    BLL_Employee bll_employee;
    BLL_Person bll_person;

    public MainWindow()  {
        bll_department = new BLL_Department();
        bll_employee = new BLL_Employee();
        bll_person = new BLL_Person();
    }
    public void view() {
        setMenuBar();
        setTable();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,700); //400 width and 500 height
        setVisible(true);
    }

    private void setMenuBar() {
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

        add(menuBar, BorderLayout.NORTH);
    }

    private void setTable() {
        try {
            EmployeeTableModel model = new EmployeeTableModel(bll_employee.getAllEmployeesWithDepartmentAndPerson());
            JTable table = new JTable();
            table.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        JTable target = (JTable) e.getSource();
                        int row = target.getSelectedRow(); // select a row
                        int column = target.getSelectedColumn(); // select a column

                        DataView dataView = new DataView(model.getEmployee(row));
                        dataView.setVisible(true);
                    }
                }
            });
            //table.setBounds(30,40,200,300);
            JScrollPane sp = new JScrollPane(table);
            table.setModel(model);
            add(sp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
