package DAL;
import BE.*;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IRepository {

    ArrayList<BE_Employee> getAllEmployees() throws SQLException;
    BE_Employee getEmployeeById(int id) throws SQLException;
    ArrayList<BE_Employee> getEmployeesByDepartmentId(int departmentId) throws SQLException;
    ArrayList<BE_Employee> getEmployeesByPersonId(int personId) throws SQLException;
    BE_Employee addEmployee(BE_Employee employee) throws SQLException;
    BE_Employee updateEmployee(BE_Employee employee) throws SQLException;
    boolean deleteEmployee(BE_Employee employee) throws SQLException;

    ArrayList<BE_Person> getAllPersons() throws SQLException;
    BE_Person getPersonById(int id) throws SQLException;

    ArrayList<BE_Person> getPersonsByEmployeeId(int employeeId) throws SQLException;
    BE_Person addPerson(BE_Person person) throws SQLException;
    BE_Person updatePerson(BE_Person person) throws SQLException;
    boolean deletePerson(BE_Person person) throws SQLException;

    ArrayList<BE_Department> getAllDepartments() throws SQLException;
    BE_Department getDepartmentById(int id) throws SQLException;
    BE_Department addDepartment(BE_Department department) throws SQLException;
    BE_Department updateDepartment(BE_Department department) throws SQLException;
    boolean deleteDepartment(BE_Department department) throws SQLException;
}