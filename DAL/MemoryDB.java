package DAL;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;

import BE.BE_Department;
import BE.BE_Employee;
import BE.BE_Person;

public enum MemoryDB implements IRepository {

    INSTANCE;

    private final ArrayList<BE_Employee> employees;
    private final ArrayList<BE_Person> persons;
    private final ArrayList<BE_Department> departments;

    MemoryDB() {
        persons = new ArrayList<>();
        persons.add(new BE_Person(1, "1234567891", "Doroteya", "Jefferys", "+4550257839", "test@test.com", "Danmark", "Envej 1", "Nærum", "2850"));
        persons.add(new BE_Person(2, "1234567892", "Lanny", "Twomey", "+4591442223", "test1@test.com", "Danmark", "Enanden vej 1", "Vejby", "3210"));
        persons.add(new BE_Person(3, "1234567893", "Asher", "Digges","+4579430742", "test2@test.com", "Danmark", "Envej 1", "Kirke Hyllinge", "4070"));
        persons.add(new BE_Person(4, "1234567894", "Dunstan", "Tinan", "+4591442223", "test3@test.com", "Danmark", "Envej 1", "Sorø", "4180"));
        persons.add(new BE_Person(5, "1234567895", "Arney", "Balwin","+4535665968", "test4@test.com", "Danmark", "Envej 1", "Korsør", "4220"));
        persons.add(new BE_Person(6, "1234567896", "Branden", "Butlin","+4550061427", "test5@test.com", "Danmark", "Envej 1", "Ugerløse", "4350"));
        persons.add(new BE_Person(7, "1234567897", "Adolphus", "Stoute","+4582704062", "test6@test.com", "Danmark", "Envej 1", "Haslev", "4690"));
        persons.add(new BE_Person(8, "1234567898", "Jermain", "Ticic","+4534245368", "test7@test.com", "Danmark", "Envej 1", "Odense SV", "5250"));
        persons.add(new BE_Person(9, "1234567899", "Napoleon", "Noddles","+4555492128", "test8@test.com", "Danmark", "Envej 1", "Langeskov", "5550"));
        persons.add(new BE_Person(10, "1234567810", "Chrissie", "Teare","+4562759748", "test9@test.com", "Danmark", "Envej 1", "Gelsted", "5591"));
        persons.add(new BE_Person(11, "1234567811", "Rafa", "Crellim","+4559285827", "test10@test.com", "Danmark", "Envej 1", "Ebberup", "5631"));
        persons.add(new BE_Person(12, "1234567812", "Teador", "Rollinshaw","+4579430742", "test11@test.com", "Danmark", "Envej 1", "Gislev", "5854"));
        persons.add(new BE_Person(13, "1234567813", "Hort", "Held", "Danmark","+4591442223", "test12@test.com", "Envej 1", "Viuf", "6052"));


        departments = new ArrayList<>();
        departments.add(new BE_Department(1, "Warehouse"));
        departments.add(new BE_Department(2, "Sales"));
        departments.add(new BE_Department(3, "Human Resources"));
        departments.add(new BE_Department(4, "Marketing"));
        departments.add(new BE_Department(5, "Accounting"));
        departments.add(new BE_Department(6, "IT"));

        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        employees = new ArrayList<>();
        // public BE_Employee(int _id, String _jobTitle, int _departmentId, String _emergencyContactName, String _emergencyContactNo, Date _startEmploymentDate, Date _endEmploymentDate, int personId) {
        try {
            employees.add(new BE_Employee(1, "Warehouse Assistant", 1, "Husband, Anders Ager", "12345678", new Date(df.parse("02-03-2020").getTime()), 1));
            employees.add(new BE_Employee(2, "Account Executive", 2, new Date(System.currentTimeMillis()), 2));
            employees.add(new BE_Employee(3, "HR Assistant", 3, new Date(df.parse("30-05-2020").getTime()), 3));
            employees.add(new BE_Employee(4, "Sales Consultant", 2, new Date(df.parse("04-03-2017").getTime()), 4));
            employees.add(new BE_Employee(5, "Recruiter", 3, new Date(df.parse("15-08-2021").getTime()), 5));
            employees.add(new BE_Employee(6, "Warehouse Assistant", 1,"Wife, Line Thulm", "12345679", new Date(df.parse("24-09-2022").getTime()), 6));
            employees.add(new BE_Employee(7, "Warehouse Assistant", 1, new Date(System.currentTimeMillis()), 7));
            employees.add(new BE_Employee(8, "Director of marketing", 4, new Date(df.parse("12-12-2023").getTime()), 8));
            employees.add(new BE_Employee(9, "Warehouse Assistant", 1, "Roommate, Nike Slaz", "12345699", new Date(df.parse("01-02-2024").getTime()), new Date(df.parse("01-06-2024").getTime()), 9));
            employees.add(new BE_Employee(10, "Marketing coordinator", 4, new Date(df.parse("01-11-2019").getTime()), 10));
            employees.add(new BE_Employee(11, "Accountant", 5, new Date(System.currentTimeMillis()), 11));
            employees.add(new BE_Employee(12, "CFO", 5, new Date(System.currentTimeMillis()), 12));
            employees.add(new BE_Employee(13, "IT Director", 6, new Date(df.parse("30-10-2019").getTime()), 13));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public ArrayList<BE_Employee> getAllEmployees() {
        return employees;
    }

    @Override
    public BE_Employee getEmployeeById(int id) {
        for (BE_Employee e : employees) {
            if (e.getId() == id)
                return e;
        }
        return new BE_Employee();
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
        return new BE_Employee(employees.get(employees.size()-1).getId()+1, employee.getJobTitle(), employee.getDepartmentId(), employee.getEmergencyContactName(), employee.getEmergencyContactNo(), employee.getStartEmploymentDate(), employee.getPersonId());
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
    public boolean deleteEmployee(BE_Employee employee) {
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
    public BE_Person getPersonById(int id) {
        for (BE_Person p : persons)
            if (p.getId() == id)
                return p;
        return new BE_Person();
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
        return new BE_Person(persons.get(persons.size()-1).getId()+1, person.getCprNo(), person.getFirstName(), person.getLastName(), person.getPhoneNo(), person.getEmail(), person.getCountry(), person.getAddress(), person.getCity(), person.getZipCode());
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
    public boolean deletePerson(BE_Person person) {
        for (BE_Person p : persons)
            if (p.getId() == person.getId()) {
                persons.remove(p);
                return true;
            }
        return false;
    }

    @Override
    public ArrayList<BE_Department> getAllDepartments() {
        return departments;
    }

    @Override
    public BE_Department getDepartmentById(int id) {
        for (BE_Department d : departments) {
            if (d.getId() == id)
                return d;
        }
        return new BE_Department();
    }

    @Override
    public BE_Department addDepartment(BE_Department department) {
        departments.sort(Comparator.comparing(BE_Department::getId));
        return new BE_Department(employees.get(departments.size()-1).getId()+1, department.getName());
    }

    @Override
    public BE_Department updateDepartment(BE_Department department) {
        for (BE_Department d : departments)
            if (d.getId() == department.getId()) {
                departments.remove(d);
                departments.add(department);
                return department;
            }
        return new BE_Department();
    }

    @Override
    public boolean deleteDepartment(BE_Department department) {
        for (BE_Department d : departments)
            if (d.getId() == department.getId()) {
                departments.remove(d);
                return true;
            }
        return false;
    }
}
