package DAL;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import BE.BE_Employee;
import BE.BE_Person;

public class DAL_Person {
    
    public ArrayList<BE_Person> getAll() throws SQLException {
        String sqlSelect = "SELECT * FROM Person;";
        try (PreparedStatement stmt = Repository.INSTANCE.getConnection().prepareStatement(sqlSelect)) {
            ResultSet rs = stmt.executeQuery();
            ArrayList<BE_Person> arr = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String cprNo = rs.getString("cprNo");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String country = rs.getString("country");
                String address = rs.getString("address");
                String city = rs.getString("city");
                int zipCode = rs.getInt("zipCode");

                BE_Person person = new BE_Person(id, cprNo, firstName, lastName, country, address, city, zipCode);
                arr.add(person);
            }
            return arr;
        } catch (SQLException e) {
            throw new SQLException("DAL_Employee - getAll - failed to fetch data. " + e.getMessage());
        }
    }

    public ArrayList<BE_Person> getByEmployeeId(int employeeId) throws SQLException {
        String sqlSelect = "SELECT Person.id, Person.cprNo, Person.firstName, Person.lastName, Person.country, Person.address, Person.city, Person.zipCode FROM Person INNER JOIN Employee ON Person.id = Employee.personId;";
        try (PreparedStatement stmt = Repository.INSTANCE.getConnection().prepareStatement(sqlSelect)) {
            stmt.setInt(1, departmentId);
            ResultSet rs = stmt.executeQuery();
            ArrayList<BE_Employee> arr = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String jobTitle = rs.getString("jobTitle");
                String emergencyContactName = rs.getString("emergencyContactName");
                String emergencyContactNo = rs.getString("emergencyContactNo");
                Date startEmploymentDate = rs.getDate("startEmploymentDate");
                Date endEmploymentDate = rs.getDate("endEmploymentDate");
                int personId = rs.getInt("personId");

                BE_Employee e = new BE_Employee(id, jobTitle, departmentId, emergencyContactName, emergencyContactNo,
                        startEmploymentDate, endEmploymentDate, personId);
                arr.add(e);
            }
            return arr;
        } catch (SQLException e) {
            throw new SQLException("DAL_Employee - getByDepartmentId - failed to fetch data. " + e.getMessage());
        }
    }

    

}