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
        setTitle("EmployeeWare");

        setMenuBar();
        setTable();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,700);
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
        searchMenuItem.setMnemonic(KeyEvent.VK_E);
        searchMenuItem.setToolTipText("Search...");
        searchMenuItem.addActionListener((event) -> {
            // TODO: Search functionality - new pop-up window OR in button of table
        });

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
        hideDismissedMenuItem.addActionListener((event) -> {
            if (hideDismissedMenuItem.getState()) {
                TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(((EmployeeTableModel) table.getModel()));
                sorter.setRowFilter(getRowFilter());

                table.setRowSorter(sorter);
            } else {
                table.setAutoCreateRowSorter(true);
            }
        });
        optionsMenu.add(hideDismissedMenuItem);
        menuBar.add(optionsMenu);

        add(menuBar, BorderLayout.NORTH);
    }

    private RowFilter<TableModel, Integer> getRowFilter() {
        return new RowFilter<TableModel, Integer>() {
            @Override
            public boolean include(Entry<? extends TableModel, ? extends Integer> entry) {
                int modelRow = entry.getIdentifier();
                var model = (EmployeeTableModel) table.getModel();
                int colIdx = 0;
                for (int i = 0; i < model.getColumnCount(); i++)
                    if (model.getColumns()[i].equalsIgnoreCase("End Employment Date"))
                        colIdx = i;
                return entry.getModel().getValueAt(modelRow, colIdx) == null;
            }
        };
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

                        DataView dataView = new DataView(table, model.getEmployee(selectedViewRow));
                        dataView.setVisible(true);
                    }
                }
            });
            //table.setBounds(30,40,200,300);
            JScrollPane sp = new JScrollPane(table);
            table.setModel(model);
            add(sp);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }
    }
}
