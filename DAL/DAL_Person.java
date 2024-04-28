package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
                String phoneNo = rs.getString("phoneNo");
                String email = rs.getString("email");
                String country = rs.getString("country");
                String address = rs.getString("address");
                String city = rs.getString("city");
                String zipCode = rs.getString("zipCode");

                BE_Person person = new BE_Person(id, cprNo, firstName, lastName, phoneNo, email, country, address, city, zipCode);
                arr.add(person);
            }
            return arr;
        } catch (SQLException e) {
            throw new SQLException("DAL_Person - getAll - failed to fetch data. " + e.getMessage());
        }
    }

    public BE_Person getById(int id) throws SQLException {
        String sqlSelect = "SELECT * FROM Person WHERE id = (?);";
        try (PreparedStatement stmt = Repository.INSTANCE.getConnection().prepareStatement(sqlSelect)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String cprNo = rs.getString("cprNo");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String phoneNo = rs.getString("phoneNo");
                String email = rs.getString("email");
                String country = rs.getString("country");
                String address = rs.getString("address");
                String city = rs.getString("city");
                String zipCode = rs.getString("zipCode");

                return new BE_Person(id, cprNo, firstName, lastName, phoneNo, email, country, address, city, zipCode);
            }
            return new BE_Person();
        } catch (SQLException e) {
            throw new SQLException("DAL_Person - getAll - failed to fetch data. " + e.getMessage());
        }
    }

    public ArrayList<BE_Person> getByEmployeeId(int employeeId) throws SQLException {
        String sqlSelect = "SELECT Person.id, Person.cprNo, Person.firstName, Person.lastName, Person.country, Person.address, Person.city, Person.zipCode FROM Person INNER JOIN Employee ON Person.id = Employee.personId WHERE Employee.id = ?;";
        try (PreparedStatement stmt = Repository.INSTANCE.getConnection().prepareStatement(sqlSelect)) {
            stmt.setInt(1, employeeId);
            ResultSet rs = stmt.executeQuery();
            ArrayList<BE_Person> arr = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String cprNo = rs.getString("cprNo");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String phoneNo = rs.getString("phoneNo");
                String email = rs.getString("email");
                String country = rs.getString("country");
                String address = rs.getString("address");
                String city = rs.getString("city");
                String zipCode = rs.getString("zipCode");

                BE_Person person = new BE_Person(id, cprNo, firstName, lastName, phoneNo, email, country, address, city, zipCode);
                arr.add(person);
            }
            return arr;
        } catch (SQLException e) {
            throw new SQLException("DAL_Person - getByEmployeeId - failed to fetch data. " + e.getMessage());
        }
    }

    public BE_Person add(BE_Person person) throws SQLException {
        String sqlInsert = "INSERT INTO Person (cprNo, firstName, lastName, phoneNo, email, country, address, city, zipCode) VALUES (?,?,?,?,?,?,?,?,?);";
        try (PreparedStatement stmt = Repository.INSTANCE.getConnection().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, person.getCprNo());
            stmt.setString(2, person.getFirstName());
            stmt.setString(3, person.getLastName());
            stmt.setString(4, person.getPhoneNo());
            stmt.setString(5, person.getEmail());
            stmt.setString(6, person.getCountry());
            stmt.setString(7, person.getAddress());
            stmt.setString(8, person.getCity());
            stmt.setString(9, person.getZipCode());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("DAL_Employee - add - Creating employee failed, no rows affected.");
            }
    
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    person.setId(generatedKeys.getInt(1));
                    return person;
                }
                else {
                    throw new SQLException("DAL_Person - add - Creating person failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new SQLException("DAL_Person - add - failed to add new entity. " + e.getMessage());
        }
    }

    public BE_Person update(BE_Person person) throws SQLException {
        String sqlUpdate = "UPDATE Person SET cprNo = ?, firstName = ?, lastName = ?, phoneNo = ?, email = ?, country = ?, address = ?, city = ?, zipCode = ? WHERE id = ?;";
        try (PreparedStatement stmt = Repository.INSTANCE.getConnection().prepareStatement(sqlUpdate)) {
            stmt.setString(1, person.getCprNo());
            stmt.setString(2, person.getFirstName());
            stmt.setString(3, person.getLastName());
            stmt.setString(4, person.getPhoneNo());
            stmt.setString(5, person.getEmail());
            stmt.setString(6, person.getCountry());
            stmt.setString(7, person.getAddress());
            stmt.setString(8, person.getCity());
            stmt.setString(9, person.getZipCode());
            stmt.setInt(10, person.getId());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("DAL_Person - update - Updating person failed, no rows affected.");
            }
            return person;
        } catch (SQLException e) {
            throw new SQLException("DAL_Person - update - failed to update entity. " + e.getMessage());
        }
    }

    public boolean delete(BE_Person person) throws SQLException {
        String sqlDelete = "DELETE FROM Person WHERE id = ?;";
        try (PreparedStatement stmt = Repository.INSTANCE.getConnection().prepareStatement(sqlDelete)) {
            stmt.setInt(1, person.getId());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("DAL_Person - delete - Deleting person failed, no rows affected.");
            }
            return true;
        } catch (SQLException e) {
            throw new SQLException("DAL_Person - delete - failed to delete entity. " + e.getMessage());
        }
    }
}