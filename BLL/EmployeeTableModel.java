package BLL;

import BE.BE_Employee;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class EmployeeTableModel extends AbstractTableModel {

        private List<BE_Employee> employeeData = new ArrayList<BE_Employee>();
        private String[] columnNames =  {"ID", "Job title", "Department", "Emergency Contact Name", "Emergency Contact Number", "Start Employment Date", "End Employment Date"};

        public EmployeeTableModel() {}

        public EmployeeTableModel(List<BE_Employee> employeeData) {
            this.employeeData = employeeData;
        }

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public int getRowCount() {
            return employeeData.size();
        }

        @Override
        public Object getValueAt(int row, int column) {
            Object employeeAttribute = null;
            BE_Employee userObject = employeeData.get(row);
            switch(column) {
                case 0: employeeAttribute = userObject.getId(); break;
                case 1: employeeAttribute = userObject.getJobTitle(); break;
                case 2: employeeAttribute = userObject.getDepartmentId(); break;
                case 3: employeeAttribute = userObject.getEmergencyContactName(); break;
                case 4: employeeAttribute = userObject.getEmergencyContactNo(); break;
                case 5: employeeAttribute = userObject.getStartEmploymentDate(); break;
                case 6: employeeAttribute = userObject.getEndEmploymentDate(); break;
                default: break;
            }
            return employeeAttribute;
        }

        public void addEmployee(BE_Employee employee) {
            employeeData.add(employee);
            fireTableDataChanged();
        }
}
