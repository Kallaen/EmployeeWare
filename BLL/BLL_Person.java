package BLL;

import BE.BE_Person;
import DAL.Repository;

import java.sql.SQLException;
import java.util.ArrayList;

public class BLL_Person {
    public BLL_Person() {

    }

    public ArrayList<BE_Person> getAllPersons() throws SQLException {
        return Repository.INSTANCE.getAllPersons();
    }

    public ArrayList<BE_Person> getPersonsByEmployeeId(int employeeId) throws SQLException {
        return Repository.INSTANCE.getPersonsByEmployeeId(employeeId);
    }

    public BE_Person addPerson(BE_Person person) throws SQLException {
        return Repository.INSTANCE.addPerson(person);
    }

    public BE_Person updatePerson(BE_Person person) throws SQLException {
        return Repository.INSTANCE.updatePerson(person);
    }

    public boolean deletePerson(BE_Person person) throws SQLException {
        return Repository.INSTANCE.deletePerson(person);
    }

}
