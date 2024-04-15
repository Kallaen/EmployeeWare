package GUI;

import BLL.BLL_Department;
import BLL.BLL_Employee;
import BLL.BLL_Person;

import javax.swing.*;
import javax.swing.event.RowSorterEvent;
import javax.swing.event.RowSorterListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainView extends JFrame {

    BLL_Department bll_department;
    BLL_Employee bll_employee;
    BLL_Person bll_person;
    JTable table;

    public MainView()  {
        bll_department = new BLL_Department();
        bll_employee = new BLL_Employee();
        bll_person = new BLL_Person();
        table = new JTable();
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

        JMenuItem departmentMenu = new JMenuItem("Department");
        departmentMenu.addActionListener((event) -> new DepartmentView().setVisible(true));

        fileMenu.add(searchMenuItem);
        fileMenu.add(departmentMenu);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);

        JMenu optionsMenu = new JMenu("Options");
        fileMenu.setMnemonic(KeyEvent.VK_O);

        JCheckBoxMenuItem hideDismissedMenuItem = new JCheckBoxMenuItem("Hide dismissed");
  /*      hideDismissedMenuItem.addActionListener((event) -> {
                    TableModel model = table.getModel();
                    for (int c = 0; c < model.getRowCount(); c++) {
                        if (model.getValueAt(c, model.getColumnCount()-1) != null)
                            model.removeRow(c);
                    }
                }
        );*/
        optionsMenu.add(hideDismissedMenuItem);
        menuBar.add(optionsMenu);

        add(menuBar, BorderLayout.NORTH);
    }

    private void setTable() {
        try {
            EmployeeTableModel model = new EmployeeTableModel(bll_employee.getAllEmployeesWithDepartmentAndPerson());
            table.setAutoCreateRowSorter(true);

            table.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        JTable target = (JTable) e.getSource();
                        int row = target.getSelectedRow(); // select a row
                        //int column = target.getSelectedColumn(); // select a column

                        int selectedViewRow = target.getRowSorter().convertRowIndexToModel(row);

                        DataView dataView = new DataView(model.getEmployee(selectedViewRow));
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
