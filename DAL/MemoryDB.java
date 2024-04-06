package DAL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;

import BE.BE_Department;
import BE.BE_Employee;
import BE.BE_Person;

public enum MemoryDB implements IRepository {

    INSTANCE;

    private ArrayList<BE_Employee> employees;
    private ArrayList<BE_Person> persons;
    private ArrayList<BE_Department> departments;

    MemoryDB() {
        persons = new ArrayList<>();
        persons.add(new BE_Person(1, "2310902233", "Kasper", "Svensson", "Danmark", "Stenhuggervej 12", "Esbjerg", "6710"));

        departments = new ArrayList<>();
        BE_Department warehouseDepartment = new BE_Department(1, "Warehouse");
        departments.add(warehouseDepartment);

        employees = new ArrayList<>();
        employees.add(new BE_Employee(1, "Warehouse Assistant", warehouseDepartment.getId(), new Date(System.currentTimeMillis()), 1));
    }

    @Override
    public ArrayList<BE_Employee> getAllEmployees() {
        return employees;
    }

    @Override
    public ArrayList<BE_Employee> getEmployeesByDepartmentId(int departmentId) {
        ArrayList<BE_Employee> found = new ArrayList<>();
        for (BE_Employee e : employees) {
            if (e.getDepartmentId() == departmentId)
                found.add(e);
        }
        return found;
    }

    @Override
    public ArrayList<BE_Employee> getEmployeesByPersonId(int personId) {
        ArrayList<BE_Employee> found = new ArrayList<>();
        for (BE_Employee e : employees)
            if (e.getPersonId() == personId)
                found.add(e);
        return found;
    }

    @Override
    public BE_Employee addEmployee(BE_Employee employee) {
        employees.sort(Comparator.comparing(BE_Employee::getId));
        BE_Employee e = new BE_Employee(employees.get(employees.size()-1).getId()+1, employee.getJobTitle(), employee.getDepartmentId(), employee.getEmergencyContactName(), employee.getEmergencyContactNo(), employee.getStartEmploymentDate(), employee.getPersonId());
        return e;
    }

    @Override
    public BE_Employee updateEmployee(BE_Employee employee) {
        for (BE_Employee e : employees)
            if (e.getId() == employee.getId()) {
                employees.remove(e);
                employees.add(employee);
                return employee;
            }
        return new BE_Employee();
    }

    @Override
    public boolean deleteEmployee(BE_Employee employee) throws SQLException {
        for (BE_Employee e : employees)
            if (e.getId() == employee.getId()) {
                employees.remove(e);
                return true;
            }
        return false;
    }

    @Override
    public ArrayList<BE_Person> getAllPersons() {
        return persons;    
    }

    @Override
    public ArrayList<BE_Person> getPersonsByEmployeeId(int employeeId) {
        ArrayList<BE_Person> arr = new ArrayList<>();
        for (BE_Employee e : employees)
            if (e.getId() == employeeId) {
                for (BE_Person p : persons)
                    if (e.getPersonId() == p.getId())
                        arr.add(p);
            }
        return arr;
    }

    @Override
    public BE_Person addPerson(BE_Person person) {
        persons.sort(Comparator.comparing(BE_Person::getId));
        BE_Person p = new BE_Person(persons.get(persons.size()-1).getId()+1, person.getCprNo(), person.getFirstName(), person.getLastName(), person.getCountry(), person.getAddress(), person.getCity(), person.getZipCode());
        return p;
    }

    @Override
    public BE_Person updatePerson(BE_Person person) {
        for (BE_Person p : persons)
            if (p.getId() == person.getId()) {
                persons.remove(p);
                persons.add(person);
                return person;
            }
        return new BE_Person();
    }

    @Override
    public boolean deletePerson(BE_Person person) throws SQLException {
        for (BE_Person p : persons)
            if (p.getId() == person.getId()) {
                persons.remove(p);
                return true;
            }
        return false;
    }

    @Override
    public BE_Department addDepartment(BE_Department department) throws SQLException {
        departments.sort(Comparator.comparing(BE_Department::getId));
        BE_Department d = new BE_Department(employees.get(departments.size()-1).getId()+1, department.getName());
        return d;
    }

    @Override
    public BE_Department updateDepartment(BE_Department department) throws SQLException {
        for (BE_Department d : departments)
            if (d.getId() == department.getId()) {
                departments.remove(d);
                departments.add(department);
                return department;
            }
        return new BE_Department();
    }

    @Override
    public boolean deleteDepartment(BE_Department department) throws SQLException {
        for (BE_Department d : departments)
            if (d.getId() == department.getId()) {
                departments.remove(d);
                return true;
            }
        return false;
    }
}
