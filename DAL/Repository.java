package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import BE.BE_Department;
import BE.BE_Employee;
import BE.BE_Person;

public enum Repository implements IRepository {

    INSTANCE;
    private DatabaseType DBType;
    private DAL_Employee dal_Employee;
    private DAL_Person dal_Person;
    private DAL_Department dal_Department;


    public enum DatabaseType {
        IN_MEMORY, DATABASE
    }
    
    private Repository() {
        this.DBType = DatabaseType.DATABASE;
        this.dal_Employee = new DAL_Employee();
        this.dal_Person = new DAL_Person();
        this.dal_Department = new DAL_Department();
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
    public BE_Employee addEmployee(BE_Employee employee) throws SQLException {
        if (DBType == DatabaseType.IN_MEMORY) {
            return MemoryDB.INSTANCE.addEmployee(employee);
        } else if (DBType == DatabaseType.DATABASE) {
            return dal_Employee.add(employee);
        }
        return new BE_Employee();
    }

    @Override
    public BE_Employee updateEmployee(BE_Employee employee) throws SQLException {
        if (DBType == DatabaseType.IN_MEMORY) {
            return MemoryDB.INSTANCE.updateEmployee(employee);
        } else if (DBType == DatabaseType.DATABASE) {
            return dal_Employee.update(employee);
        }
        return new BE_Employee();
    }

    @Override
    public boolean deleteEmployee(BE_Employee employee) throws SQLException {
        if (DBType == DatabaseType.IN_MEMORY) {
            return MemoryDB.INSTANCE.deleteEmployee(employee);
        } else if (DBType == DatabaseType.DATABASE) {
            return dal_Employee.delete(employee);
        }
        return false;
    }

    @Override
    public ArrayList<BE_Person> getAllPersons() throws SQLException {
        if (DBType == DatabaseType.IN_MEMORY) {
            return MemoryDB.INSTANCE.getAllPersons();
        } else if (DBType == DatabaseType.DATABASE) {
            return dal_Person.getAll();
        }
        return new ArrayList<BE_Person>();
    }

    @Override
    public ArrayList<BE_Person> getPersonsByEmployeeId(int employeeId) throws SQLException {
        if (DBType == DatabaseType.IN_MEMORY) {
            return MemoryDB.INSTANCE.getPersonsByEmployeeId(employeeId);
        } else if (DBType == DatabaseType.DATABASE) {
            return dal_Person.getByEmployeeId(employeeId);
        }
        return new ArrayList<BE_Person>();
    }

    @Override
    public BE_Person addPerson(BE_Person person) throws SQLException {
        if (DBType == DatabaseType.IN_MEMORY) {
            return MemoryDB.INSTANCE.addPerson(person);
        } else if (DBType == DatabaseType.DATABASE) {
            return dal_Person.add(person);
        }
        return new BE_Person();
    }

    @Override
    public BE_Person updatePerson(BE_Person person) throws SQLException {
        if (DBType == DatabaseType.IN_MEMORY) {
            return MemoryDB.INSTANCE.updatePerson(person);
        } else if (DBType == DatabaseType.DATABASE) {
            return dal_Person.update(person);
        }
        return new BE_Person();
    }

    @Override
    public boolean deletePerson(BE_Person person) throws SQLException {
        if (DBType == DatabaseType.IN_MEMORY) {
            return MemoryDB.INSTANCE.deletePerson(person);
        } else if (DBType == DatabaseType.DATABASE) {
            return dal_Person.delete(person);
        }
        return false;
    }

    @Override
    public BE_Department addDepartment(BE_Department department) throws SQLException {
        if (DBType == DatabaseType.IN_MEMORY) {
            return MemoryDB.INSTANCE.addDepartment(department);
        } else if (DBType == DatabaseType.DATABASE) {
            return dal_Department.add(department);
        }
        return new BE_Department();
    }

    @Override
    public BE_Department updateDepartment(BE_Department department) throws SQLException {
        if (DBType == DatabaseType.IN_MEMORY) {
            return MemoryDB.INSTANCE.updateDepartment(department);
        } else if (DBType == DatabaseType.DATABASE) {
            return dal_Department.update(department);
        }
        return new BE_Department();
    }

    @Override
    public boolean deleteDepartment(BE_Department department) throws SQLException {
        if (DBType == DatabaseType.IN_MEMORY) {
            return MemoryDB.INSTANCE.deleteDepartment(department);
        } else if (DBType == DatabaseType.DATABASE) {
            return dal_Department.delete(department);
        }
        return false;
    }
}