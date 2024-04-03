package DAL;

import java.util.ArrayList;

import BE.BE_Person;

public class DAL_Person {
    
    public DAL_Person() {

    }

    public ArrayList<BE_Person> getAllPersons() {
        
    }

    public BE_Person getPersonByEmployeeId(int employeeId);
    public BE_Person addPerson(String cprNo, String firstName, String lastName, String country, String address, String city, int zipCode);
    public BE_Person updatePerson(BE_Person person);

}