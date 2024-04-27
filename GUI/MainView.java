package GUI;

import BLL.BLL_Department;
import BLL.BLL_Employee;
import BLL.BLL_Person;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.RowSorterEvent;
import javax.swing.event.RowSorterListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.*;
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

        JMenuItem departmentMenu = new JMenuItem("Department");
        departmentMenu.addActionListener((event) -> new DepartmentView(table).setVisible(true));

        JTextField jTxtFieldSearch = new JTextField("Search ...");
        jTxtFieldSearch.setMaximumSize(new Dimension((int) jTxtFieldSearch.getPreferredSize().getWidth() + 250, (int) jTxtFieldSearch.getPreferredSize().getHeight()));
        jTxtFieldSearch.setPreferredSize(new Dimension((int) jTxtFieldSearch.getPreferredSize().getWidth() + 250, (int) jTxtFieldSearch.getPreferredSize().getHeight()));
        jTxtFieldSearch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if (jTxtFieldSearch.getText().toLowerCase().contains("search"))
                    jTxtFieldSearch.setText("");
            }
        });
        ((AbstractDocument) jTxtFieldSearch.getDocument()).setDocumentFilter(myDocumentFilter());


        JMenu optionsMenu = new JMenu("Options");
        optionsMenu.setMnemonic(KeyEvent.VK_O);

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

        fileMenu.add(departmentMenu);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        optionsMenu.add(hideDismissedMenuItem);
        menuBar.add(optionsMenu);

        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(jTxtFieldSearch);


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

    private DocumentFilter myDocumentFilter() {
        return new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
                super.insertString(fb, offset, text, attr);
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if (fb.getDocument().getText(0, fb.getDocument().getLength()).toLowerCase().contains("search")) {
                    fb.getDocument().remove(0, fb.getDocument().getLength());
                }
                System.out.println(text);
                if (fb.getDocument().getLength() >= 2) {
                    TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(((EmployeeTableModel) table.getModel()));
                    String textToFilter = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;
                    System.out.println(fb.getDocument().getText(0, fb.getDocument().getLength()) + text);

                    sorter.setRowFilter(RowFilter.regexFilter(textToFilter, 0));

                    table.setRowSorter(sorter);
                } else {
                    table.setAutoCreateRowSorter(true);
                }

                super.replace(fb, offset, length, text, attrs);
            }

            @Override
            public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
                System.out.println("remove");
                System.out.println("offset: " + offset);
                System.out.println("length: " + length);
                if (length < 2) {
                    table.setAutoCreateRowSorter(true);
                }
                super.remove(fb, offset, length);
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
