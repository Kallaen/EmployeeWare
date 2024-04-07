package BLL;

import BE.BE_Employee;
import DAL.Repository;

import java.sql.SQLException;
import java.util.ArrayList;

public class BLL_Employee {
    public BLL_Employee() {

    }

    public ArrayList<BE_Employee> getAllEmployees() throws SQLException {
        return Repository.INSTANCE.getAllEmployees();
    }

    public ArrayList<BE_Employee> getAllEmployeesWithDepartmentAndPerson() throws SQLException {
        ArrayList<BE_Employee> employees = Repository.INSTANCE.getAllEmployees();
        for (BE_Employee employee : employees) {
            employee.setDepartment(Repository.INSTANCE.getDepartmentById(employee.getDepartmentId()));
            employee.setPerson(Repository.INSTANCE.getPersonById(employee.getPersonId()));
        }
        return employees;
    }

    public ArrayList<BE_Employee> getEmployeesByDepartmentId(int departmentId) throws SQLException {
        return Repository.INSTANCE.getEmployeesByDepartmentId(departmentId);
    }

    public ArrayList<BE_Employee> getEmployeesByPersonId(int personId) throws SQLException {
        return Repository.INSTANCE.getEmployeesByPersonId(personId);
    }

    public BE_Employee addEmployee(BE_Employee employee) throws SQLException {
        return Repository.INSTANCE.addEmployee(employee);
    }

    public BE_Employee updateEmployee(BE_Employee employee) throws SQLException {
        return Repository.INSTANCE.addEmployee(employee);
    }

    public boolean deleteEmployee(BE_Employee employee) throws SQLException {
        return Repository.INSTANCE.deleteEmployee(employee);
    }
}
