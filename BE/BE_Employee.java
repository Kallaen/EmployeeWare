package BE;

import java.util.Date;

public class BE_Employee {
    public BE_Employee() {}
     
    private int _id;
    private String _jobTitle;
    private int _departmentId;
    private String _emergencyContactName;
    private String _emergencyContactNo; 
    private Date _startEmploymentDate;
    private Date _endEmploymentDate;
    private int _personId;

    public BE_Employee(int _id, String _jobTitle, int _departmentId, Date _startEmploymentDate, int personId) {
        this._id = _id;
        this._jobTitle = _jobTitle;
        this._departmentId = _departmentId;
        this._startEmploymentDate = _startEmploymentDate;
    }

    public BE_Employee(int _id, String _jobTitle, int _departmentId, String _emergencyContactName, String _emergencyContactNo, Date _startEmploymentDate, int personId) {
        this._id = _id;
        this._jobTitle = _jobTitle;
        this._departmentId = _departmentId;
        this._emergencyContactName = _emergencyContactName;
        this._emergencyContactNo = _emergencyContactNo;
        this._startEmploymentDate = _startEmploymentDate;
    }

    public BE_Employee(int _id, String _jobTitle, int _departmentId, String _emergencyContactName, String _emergencyContactNo, Date _startEmploymentDate, Date _endEmploymentDate, int personId) {
        this._id = _id;
        this._jobTitle = _jobTitle;
        this._departmentId = _departmentId;
        this._emergencyContactName = _emergencyContactName;
        this._emergencyContactNo = _emergencyContactNo;
        this._startEmploymentDate = _startEmploymentDate;
        this._endEmploymentDate = _endEmploymentDate;
    }

    public int get_id() {
        return this._id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_jobTitle() {
        return this._jobTitle;
    }

    public void set_jobTitle(String _jobTitle) {
        this._jobTitle = _jobTitle;
    }

    public int get_departmentId() {
        return this._departmentId;
    }

    public void set_departmentId(int _departmentId) {
        this._departmentId = _departmentId;
    }

    public String get_emergencyContactName() {
        return this._emergencyContactName;
    }

    public void set_emergencyContactName(String _emergencyContactName) {
        this._emergencyContactName = _emergencyContactName;
    }

    public String get_emergencyContactNo() {
        return this._emergencyContactNo;
    }

    public void set_emergencyContactNo(String _emergencyContactNo) {
        this._emergencyContactNo = _emergencyContactNo;
    }

    public Date get_startEmploymentDate() {
        return this._startEmploymentDate;
    }

    public void set_startEmploymentDate(Date _startEmploymentDate) {
        this._startEmploymentDate = _startEmploymentDate;
    }

    public Date get_endEmploymentDate() {
        return this._endEmploymentDate;
    }

    public void set_endEmploymentDate(Date _endEmploymentDate) {
        this._endEmploymentDate = _endEmploymentDate;
    }

    public int get_personId() {
        return this._personId;
    }

    public void set_personId(int _personId) {
        this._personId = _personId;
    }
}