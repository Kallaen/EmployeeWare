package DAL;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import BE.BE_Department;
import BE.BE_Employee;
import BE.BE_Person;

public enum Repository implements IRepository {

    INSTANCE;
    private DatabaseType DBType;
    private DAL_Employee dal_Employee;

    public enum DatabaseType {
        IN_MEMORY, DATABASE
    }
    
    private Repository() {
        this.DBType = DatabaseType.DATABASE;
        this.dal_Employee = new DAL_Employee();
    }

    public Connection getConnection() throws SQLException {
        String url = ConfigParser.INSTANCE.getProperty("DATABASE_URL") + 
        ConfigParser.INSTANCE.getProperty("DATABASE_IP_ADDRESS") + ":" + 
        ConfigParser.INSTANCE.getProperty("DATABASE_PORT") + "/" +  
        ConfigParser.INSTANCE.getProperty("DATABASE_NAME");
        return DriverManager.getConnection(url, ConfigParser.INSTANCE.getProperty("DATABASE_USERNAME"), ConfigParser.INSTANCE.getProperty("DATABASE_PASSWORD"));
    }

    public void setDatabaseType(DatabaseType DBType) {
        this.DBType = DBType;
    }

    @Override
    public ArrayList<BE_Employee> getAllEmployees() throws SQLException {
        if (DBType == DatabaseType.IN_MEMORY) {
            return MemoryDB.INSTANCE.getAllEmployees();
        } else if (DBType == DatabaseType.DATABASE) {
            return dal_Employee.getAll();
        }
        return new ArrayList<BE_Employee>();
    }

    @Override
    public ArrayList<BE_Employee> getEmployeesByDepartmentId(int departmentId) throws SQLException {
        if (DBType == DatabaseType.IN_MEMORY) {
            return MemoryDB.INSTANCE.getEmployeesByDepartmentId(departmentId);
        } else if (DBType == DatabaseType.DATABASE) {
            return dal_Employee.getByDepartmentId(departmentId);
        }
        return new ArrayList<BE_Employee>();
    }

    @Override
    public ArrayList<BE_Employee> getEmployeesByPersonId(int personId) throws SQLException {
        if (DBType == DatabaseType.IN_MEMORY) {
            return MemoryDB.INSTANCE.getEmployeesByPersonId(personId);
        } else if (DBType == DatabaseType.DATABASE) {
            return dal_Employee.getByPersonId(personId);
        }
        return new ArrayList<BE_Employee>();
    }

    @Override
    public BE_Employee addEmployee(String jobTitle, int departmentId,
            Date startEmploymentDate, int personId) {
        if (DBType == DatabaseType.IN_MEMORY) {
            return MemoryDB.INSTANCE.addEmployee(jobTitle, departmentId, startEmploymentDate, personId);
        } else if (DBType == DatabaseType.DATABASE) {

        }
    }

    @Override
    public BE_Employee updateEmployee(BE_Employee employee) {
        if (DBType == DatabaseType.IN_MEMORY) {
            return MemoryDB.INSTANCE.updateEmployee(employee);
        } else if (DBType == DatabaseType.DATABASE) {

        }
    }

    @Override
    public ArrayList<BE_Person> getAllPersons() {
        if (DBType == DatabaseType.IN_MEMORY) {
            return MemoryDB.INSTANCE.getAllPersons();
        } else if (DBType == DatabaseType.DATABASE) {

        }
    }

    @Override
    public BE_Person getPersonByEmployeeId(int employeeId) {
        if (DBType == DatabaseType.IN_MEMORY) {
            return MemoryDB.INSTANCE.getPersonByEmployeeId(employeeId);
        } else if (DBType == DatabaseType.DATABASE) {

        }
    }

    @Override
    public BE_Person addPerson(String cprNo, String firstName, String lastName, String country, String address,
            String city, int zipCode) {
        if (DBType == DatabaseType.IN_MEMORY) {
            return MemoryDB.INSTANCE.addPerson(cprNo, firstName, lastName, country, address, city, zipCode);
        } else if (DBType == DatabaseType.DATABASE) {

        }
    }

    @Override
    public BE_Person updatePerson(BE_Person person) {
        if (DBType == DatabaseType.IN_MEMORY) {
            return MemoryDB.INSTANCE.updatePerson(person);
        } else if (DBType == DatabaseType.DATABASE) {

        }
    }
}