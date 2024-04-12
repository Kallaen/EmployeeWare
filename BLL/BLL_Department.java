package BLL;

import BE.BE_Department;
import DAL.Repository;

import java.sql.SQLException;
import java.util.ArrayList;

public class BLL_Department {
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

    public boolean deleteDepartment(BE_Department department) throws SQLException {
        return Repository.INSTANCE.deleteDepartment(department);
    }
}
