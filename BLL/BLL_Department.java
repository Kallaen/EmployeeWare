package BLL;

import BE.BE_Department;
import DAL.Repository;

import java.sql.SQLException;

public class BLL_Department {
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
