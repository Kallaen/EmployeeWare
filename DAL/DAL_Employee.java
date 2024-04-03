package DAL;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import BE.BE_Employee;

public class DAL_Employee {
    public ArrayList<BE_Employee> getAll() throws SQLException {
        try (Statement stmt = Repository.INSTANCE.getConnection().createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM Employee;");
            ArrayList<BE_Employee> arr = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String jobTitle = rs.getString("jobTitle");
                int departmentId = rs.getInt("departmentId");
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
            throw e;
        }
    }

    public ArrayList<BE_Employee> getByDepartmentId(int departmentId) throws SQLException {
        try (PreparedStatement stmt = Repository.INSTANCE.getConnection().prepareStatement("SELECT * FROM Employee WHERE departmentId = ?;")) {
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
            throw e;
        }
    }

    public ArrayList<BE_Employee> getByPersonId(int personId) throws SQLException {
        try (PreparedStatement stmt = Repository.INSTANCE.getConnection().prepareStatement("SELECT * FROM Employee WHERE personId = ?;")) {
            stmt.setInt(1, personId);
            ResultSet rs = stmt.executeQuery();
            ArrayList<BE_Employee> arr = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String jobTitle = rs.getString("jobTitle");
                int departmentId = rs.getInt("departmentId");
                String emergencyContactName = rs.getString("emergencyContactName");
                String emergencyContactNo = rs.getString("emergencyContactNo");
                Date startEmploymentDate = rs.getDate("startEmploymentDate");
                Date endEmploymentDate = rs.getDate("endEmploymentDate");

                BE_Employee e = new BE_Employee(id, jobTitle, departmentId, emergencyContactName, emergencyContactNo,
                        startEmploymentDate, endEmploymentDate, personId);
                arr.add(e);
            }
            return arr;
        } catch (SQLException e) {
            throw e;
        }
    }

    public void add(String jobTitle, int departmentId, Date startEmploymentDate, int personId) {
        // TODO: INSERT statement
        try (PreparedStatement stmt = Repository.INSTANCE.getConnection().prepareStatement("SELECT * FROM Employee WHERE personId = ?;")) {
            stmt.setInt(1, personId);
            ResultSet rs = stmt.executeQuery();
            ArrayList<BE_Employee> arr = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String jobTitle = rs.getString("jobTitle");
                int departmentId = rs.getInt("departmentId");
                String emergencyContactName = rs.getString("emergencyContactName");
                String emergencyContactNo = rs.getString("emergencyContactNo");
                Date startEmploymentDate = rs.getDate("startEmploymentDate");
                Date endEmploymentDate = rs.getDate("endEmploymentDate");

                BE_Employee e = new BE_Employee(id, jobTitle, departmentId, emergencyContactName, emergencyContactNo,
                        startEmploymentDate, endEmploymentDate, personId);
                arr.add(e);
            }
            return arr;
        } catch (SQLException e) {
            throw e;
        }
    }

    public void remove() {

    }

    public void geById() {

    }

    public void getByName() {

    }

    public void getByCPR() {

    }

}
