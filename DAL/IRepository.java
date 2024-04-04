package DAL;
import BE.*;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IRepository {

    public ArrayList<BE_Employee> getAllEmployees() throws SQLException;
    public ArrayList<BE_Employee> getEmployeesByDepartmentId(int departmentId) throws SQLException;
    public ArrayList<BE_Employee> getEmployeesByPersonId(int personId) throws SQLException;
    public BE_Employee addEmployee(BE_Employee employee) throws SQLException;
    public BE_Employee updateEmployee(BE_Employee employee) throws SQLException;
    public boolean deleteEmployee(BE_Employee employee) throws SQLException;

    public ArrayList<BE_Person> getAllPersons() throws SQLException;
    public ArrayList<BE_Person> getPersonsByEmployeeId(int employeeId) throws SQLException;
    public BE_Person addPerson(BE_Person person) throws SQLException;
    public BE_Person updatePerson(BE_Person person) throws SQLException;
    public boolean deletePerson(BE_Person person) throws SQLException;

    public BE_Department addDepartment(BE_Department department) throws SQLException;
    public BE_Department updateDepartment(BE_Department department) throws SQLException;
    public boolean deleteDepartment(BE_Department department) throws SQLException;
    
}