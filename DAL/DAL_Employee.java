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
        String sqlSelect = "SELECT * FROM Employee;";
        try (PreparedStatement stmt = Repository.INSTANCE.getConnection().prepareStatement(sqlSelect)) {
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
                int personId = rs.getInt("personId");

                BE_Employee e = new BE_Employee(id, jobTitle, departmentId, emergencyContactName, emergencyContactNo,
                        startEmploymentDate, endEmploymentDate, personId);
                arr.add(e);
            }
            return arr;
        } catch (SQLException e) {
            throw new SQLException("DAL_Employee - getAll - failed to fetch data. " + e.getMessage());
        }
    }

    public BE_Employee getById(int id) throws SQLException {
        String sqlSelect = "SELECT * FROM Employee WHERE id = (?);";
        try (PreparedStatement stmt = Repository.INSTANCE.getConnection().prepareStatement(sqlSelect)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String jobTitle = rs.getString("jobTitle");
                int departmentId = rs.getInt("departmentId");
                String emergencyContactName = rs.getString("emergencyContactName");
                String emergencyContactNo = rs.getString("emergencyContactNo");
                Date startEmploymentDate = rs.getDate("startEmploymentDate");
                Date endEmploymentDate = rs.getDate("endEmploymentDate");
                int personId = rs.getInt("personId");

                return new BE_Employee(id, jobTitle, departmentId, emergencyContactName, emergencyContactNo,
                        startEmploymentDate, endEmploymentDate, personId);
            }
            return new BE_Employee();
        } catch (SQLException e) {
            throw new SQLException("DAL_Employee - getAll - failed to fetch data. " + e.getMessage());
        }
    }

    public ArrayList<BE_Employee> getByDepartmentId(int departmentId) throws SQLException {
        String sqlSelect = "SELECT * FROM Employee WHERE departmentId = ?;";
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

    public ArrayList<BE_Employee> getByPersonId(int personId) throws SQLException {
        String sqlSelect = "SELECT * FROM Employee WHERE personId = ?;";
        try (PreparedStatement stmt = Repository.INSTANCE.getConnection().prepareStatement(sqlSelect)) {
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
            throw new SQLException("DAL_Employee - getPersonById - failed to fetch data. " + e.getMessage());
        }
    }

    public BE_Employee add(BE_Employee employee) throws SQLException {
        String sqlInsert = "INSERT INTO Employee (jobTitle, departmentId, emergencyContactName, emergencyContactNo, startEmploymentDate, personId) VALUES (?,?,?,?,?,?);";
        try (PreparedStatement stmt = Repository.INSTANCE.getConnection().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, employee.getJobTitle());
            stmt.setInt(2, employee.getDepartmentId());
            stmt.setString(3, employee.getEmergencyContactName());
            stmt.setString(4, employee.getEmergencyContactNo());
            stmt.setDate(5, employee.getStartEmploymentDate());
            stmt.setInt(6, employee.getPersonId());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("DAL_Employee - add - Creating employee failed, no rows affected.");
            }
    
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    employee.setId(generatedKeys.getInt(1));
                    return employee;
                }
                else {
                    throw new SQLException("DAL_Employee - add - Creating employee failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new SQLException("DAL_Employee - add - failed to add new entity. " + e.getMessage());
        }
    }

    public BE_Employee update(BE_Employee employee) throws SQLException {
        String sqlUpdate = "UPDATE Employee SET jobTitle = ?, departmentId = ?, emergencyContactName = ?, emergencyContactNo = ?, startEmploymentDate = ?, endEmploymentDate = ?, personId = ? WHERE id = ?;";
        try (PreparedStatement stmt = Repository.INSTANCE.getConnection().prepareStatement(sqlUpdate)) {
            stmt.setString(1, employee.getJobTitle());
            stmt.setInt(2, employee.getDepartmentId());
            stmt.setString(3, employee.getEmergencyContactName());
            stmt.setString(4, employee.getEmergencyContactNo());
            stmt.setDate(5, employee.getStartEmploymentDate());
            stmt.setDate(6, employee.getEndEmploymentDate());
            stmt.setInt(7, employee.getPersonId());
            stmt.setInt(8, employee.getId());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("DAL_Employee - update - Updating employee failed, no rows affected.");
            }
            return employee;
        } catch (SQLException e) {
            throw new SQLException("DAL_Employee - update - failed to update entity. " + e.getMessage());
        }
    }

    public boolean delete(BE_Employee employee) throws SQLException {
        String sqlDelete = "DELETE FROM Employee WHERE id = ?;";
        try (PreparedStatement stmt = Repository.INSTANCE.getConnection().prepareStatement(sqlDelete)) {
            stmt.setInt(1, employee.getId());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("DAL_Employee - delete - Deleting employee failed, no rows affected.");
            }
            return true;
        } catch (SQLException e) {
            throw new SQLException("DAL_Employee - delete - failed to delete entity. " + e.getMessage());
        }
    }
}
