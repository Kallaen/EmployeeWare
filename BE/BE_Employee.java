package BE;

import java.sql.Date;

public class BE_Employee {
    public BE_Employee() {}
     
    private int id;
    private String jobTitle;
    private int departmentId;
    private String emergencyContactName;
    private String emergencyContactNo;
    private Date startEmploymentDate;
    private Date endEmploymentDate;
    private int personId;

    private BE_Department department;
    private BE_Person person;

    public BE_Employee(int _id, String _jobTitle, int _departmentId, Date _startEmploymentDate, int personId) {
        this.id = _id;
        this.jobTitle = _jobTitle;
        this.departmentId = _departmentId;
        this.startEmploymentDate = _startEmploymentDate;
        this.personId = personId;
    }

    public BE_Employee(int _id, String _jobTitle, int _departmentId, String _emergencyContactName, String _emergencyContactNo, Date _startEmploymentDate, int personId) {
        this.id = _id;
        this.jobTitle = _jobTitle;
        this.departmentId = _departmentId;
        this.emergencyContactName = _emergencyContactName;
        this.emergencyContactNo = _emergencyContactNo;
        this.startEmploymentDate = _startEmploymentDate;
        this.personId = personId;
    }

    public BE_Employee(int _id, String _jobTitle, int _departmentId, String _emergencyContactName, String _emergencyContactNo, Date _startEmploymentDate, Date _endEmploymentDate, int personId) {
        this.id = _id;
        this.jobTitle = _jobTitle;
        this.departmentId = _departmentId;
        this.emergencyContactName = _emergencyContactName;
        this.emergencyContactNo = _emergencyContactNo;
        this.startEmploymentDate = _startEmploymentDate;
        this.endEmploymentDate = _endEmploymentDate;
        this.personId = personId;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJobTitle() {
        return this.jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public int getDepartmentId() {
        return this.departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getEmergencyContactName() {
        return this.emergencyContactName;
    }

    public void setEmergencyContactName(String emergencyContactName) {
        this.emergencyContactName = emergencyContactName;
    }

    public String getEmergencyContactNo() {
        return this.emergencyContactNo;
    }

    public void setEmergencyContactNo(String emergencyContactNo) {
        this.emergencyContactNo = emergencyContactNo;
    }

    public Date getStartEmploymentDate() {
        return this.startEmploymentDate;
    }

    public void setStartEmploymentDate(Date startEmploymentDate) {
        this.startEmploymentDate = startEmploymentDate;
    }

    public Date getEndEmploymentDate() {
        return this.endEmploymentDate;
    }

    public void setEndEmploymentDate(Date endEmploymentDate) {
        this.endEmploymentDate = endEmploymentDate;
    }

    public int getPersonId() {
        return this.personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public BE_Department getDepartment() {
        return department;
    }

    public void setDepartment(BE_Department department) {
        this.department = department;
    }

    public BE_Person getPerson() {
        return person;
    }

    public void setPerson(BE_Person person) {
        this.person = person;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BE_Employee other = (BE_Employee) o;
        return getId() == other.getId();
    }

    @Override
    public final int hashCode() {
        return getClass().hashCode();
    }
}