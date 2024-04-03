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

    public ArrayList<BE_Person> getAllPersons() throws SQLException;
    public BE_Person getPersonByEmployeeId(int employeeId) throws SQLException;
    public BE_Person addPerson(BE_Person person) throws SQLException;
    public BE_Person updatePerson(BE_Person person) throws SQLException;
    
}