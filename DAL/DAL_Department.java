package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import BE.BE_Department;

public class DAL_Department {
    public BE_Department add(BE_Department department) throws SQLException {
        String sqlInsert = "INSERT INTO Department (name) VALUES (?);";
        try (PreparedStatement stmt = Repository.INSTANCE.getConnection().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, department.get_name());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("DAL_Department - add - Creating department failed, no rows affected.");
            }
    
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    department.set_id(generatedKeys.getInt(1));
                    return department;
                }
                else {
                    throw new SQLException("DAL_Department - add - Creating department failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new SQLException("DAL_Department - add - failed to add new entity. " + e.getMessage());
        }
    }
    
    public BE_Department update(BE_Department department) throws SQLException {
        String sqlUpdate = "UPDATE Department SET name = ? WHERE id = ?;";
        try (PreparedStatement stmt = Repository.INSTANCE.getConnection().prepareStatement(sqlUpdate)) {
            stmt.setString(1, department.get_name());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("DAL_Department - update - Updating department failed, no rows affected.");
            }
            return department;
        } catch (SQLException e) {
            throw new SQLException("DAL_Department - update - failed to update entity. " + e.getMessage());
        }
    }

    public boolean delete(BE_Department department) throws SQLException {
        String sqlDelete = "DELETE FROM Department WHERE id = ?;";
        try (PreparedStatement stmt = Repository.INSTANCE.getConnection().prepareStatement(sqlDelete)) {
            stmt.setInt(1, department.get_id());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("DAL_Department - delete - Deleting department failed, no rows affected.");
            }
            return true;
        } catch (SQLException e) {
            throw new SQLException("DAL_Department - delete - failed to delete entity. " + e.getMessage());
        }
    }
}
