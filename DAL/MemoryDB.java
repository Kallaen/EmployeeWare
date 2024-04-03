package DAL;
import java.sql.Date;
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
        persons.add(new BE_Person(1, "2310902233", "Kasper", "Svensson", "Danmark", "Stenhuggervej 12", "Esbjerg", 6710));

        departments = new ArrayList<>();
        BE_Department warehouseDepartment = new BE_Department(1, "Warehouse");
        departments.add(warehouseDepartment);

        employees = new ArrayList<>();
        employees.add(new BE_Employee(1, "Warehouse Assistant", warehouseDepartment.get_id(), new Date(System.currentTimeMillis()), 1));
    }

    @Override
    public ArrayList<BE_Employee> getAllEmployees() {
        return employees;
    }

    @Override
    public ArrayList<BE_Employee> getEmployeesByDepartmentId(int departmentId) {
        ArrayList<BE_Employee> found = new ArrayList<>();
        for (BE_Employee e : employees) {
            if (e.get_departmentId() == departmentId)
                found.add(e);
        }
        return found;
    }

    @Override
    public ArrayList<BE_Employee> getEmployeesByPersonId(int personId) {
        ArrayList<BE_Employee> found = new ArrayList<>();
        for (BE_Employee e : employees)
            if (e.get_personId() == personId)
                found.add(e);
        return found;
    }

    @Override
    public BE_Employee addEmployee(BE_Employee employee) {
        employees.sort(Comparator.comparing(BE_Employee::get_id));
        BE_Employee e = new BE_Employee(employees.get(employees.size()-1).get_id()+1, employee.get_jobTitle(), employee.get_departmentId(), employee.get_emergencyContactName(), employee.get_emergencyContactNo(), employee.get_startEmploymentDate(), employee.get_personId());
        return e;
    }

    @Override
    public BE_Employee updateEmployee(BE_Employee employee) {
        for (BE_Employee e : employees)
            if (e.get_id() == employee.get_id()) {
                employees.remove(e);
                employees.add(employee);
                return employee;
            }
        return new BE_Employee();
    }

    @Override
    public ArrayList<BE_Person> getAllPersons() {
        return persons;    
    }

    @Override
    public BE_Person getPersonByEmployeeId(int employeeId) {
        for (BE_Employee e : employees)
            if (e.get_id() == employeeId) {
                for (BE_Person p : persons)
                    if (e.get_personId() == p.get_id())
                        return p;
            }
        return new BE_Person();
    }

    @Override
    public BE_Person addPerson(BE_Person person) {
        persons.sort(Comparator.comparing(BE_Person::get_id));
        BE_Person p = new BE_Person(persons.get(persons.size()-1).get_id()+1, person.get_cprNo(), person.get_firstName(), person.get_lastName(), person.get_country(), person.get_address(), person.get_city(), person.get_zipCode());
        return p;
    }

    @Override
    public BE_Person updatePerson(BE_Person person) {
        for (BE_Person p : persons)
            if (p.get_id() == person.get_id()) {
                persons.remove(p);
                persons.add(person);
                return person;
            }
        return new BE_Person();
    }
}
