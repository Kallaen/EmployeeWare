package GUI;

import BE.BE_Employee;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class EmployeeTableModel extends AbstractTableModel {

    private final List<BE.BE_Employee> employees;

    private static final String[] columns = new String[]{"Person", "Job title", "Department", "Emergency Contact Name", "Emergency Contact Number", "Start Employment Date", "End Employment Date"};

    public EmployeeTableModel(List<BE_Employee> employees) {
        this.employees = employees;
    }

    @Override
    public int getRowCount() {
        return employees.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columns[columnIndex];
    }

    /*    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return clazz[columnIndex];
    }*/

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        BE_Employee employee = getEmployee(rowIndex);

        if(employee != null) {
            switch (columnIndex) {
                case 0 -> {
                    return employee.getPerson().toString();
                }
                case 1 -> {
                    return employee.getJobTitle();
                }
                case 2 -> {
                    return employee.getDepartment().getName();
                }
                case 3 -> {
                    return employee.getEmergencyContactName();
                }
                case 4 -> {
                    return employee.getEmergencyContactNo();
                }
                case 5 -> {
                    return employee.getStartEmploymentDate();
                }
                case 6 -> {
                    return employee.getEndEmploymentDate();
                }

            }
        }
        return "";
    }

    public BE_Employee getEmployee(int rowIndex) {
        if (getRowCount() > rowIndex && rowIndex >= 0) {
            return employees.get(rowIndex);
        }
        return null;
    }

    public void addEmployee(BE_Employee employee) {
        employees.add(employee);
        fireTableDataChanged();
    }
}
