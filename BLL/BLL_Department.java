package BLL;

import BE.BE_Department;
import BE.BE_Employee;
import DAL.Repository;

import java.sql.SQLException;
import java.util.ArrayList;

public class BLL_Department {
    BLL_Employee bll_employee;
    public BLL_Department() {
        bll_employee = new BLL_Employee();
    }

    public ArrayList<BE_Department> getAllDepartments() throws SQLException {
        return Repository.INSTANCE.getAllDepartments();
    }

    public BE_Department getDepartmentById(int id) throws SQLException {
        return Repository.INSTANCE.getDepartmentById(id);
    }

    public BE_Department addDepartment(BE_Department department) throws SQLException {
        return Repository.INSTANCE.addDepartment(department);
    }

    public BE_Department updateDepartment(BE_Department department) throws SQLException {
        return Repository.INSTANCE.updateDepartment(department);
    }

    public boolean deleteDepartment(BE_Department department) throws SQLException, Exception {

        for (BE_Employee emp : bll_employee.getAllEmployees()) {
            if (emp.getDepartmentId() == department.getId())
                throw new Exception("Cannot remove a department with associated employees!");
        }

        return Repository.INSTANCE.deleteDepartment(department);
    }
}
