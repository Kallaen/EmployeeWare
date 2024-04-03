package DAL;
import BE.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public interface IRepository {

    public ArrayList<BE_Employee> getAllEmployees() throws SQLException;
    public ArrayList<BE_Employee> getEmployeesByDepartmentId(int departmentId) throws SQLException;
    public ArrayList<BE_Employee> getEmployeesByPersonId(int personId) throws SQLException;
    public BE_Employee addEmployee(String jobTitle, int departmentId, Date startEmploymentDate, int personId) throws SQLException;
    public BE_Employee updateEmployee(BE_Employee employee) throws SQLException;

    public ArrayList<BE_Person> getAllPersons() throws SQLException;
    public BE_Person getPersonByEmployeeId(int employeeId) throws SQLException;
    public BE_Person addPerson(String cprNo, String firstName, String lastName, String country, String address, String city, int zipCode) throws SQLException;
    public BE_Person updatePerson(BE_Person person) throws SQLException;
    
}
