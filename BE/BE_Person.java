package BE;

public class BE_Person {
    public BE_Person() {}


    private int _id;
    private String _cprNo;
    private String _firstName;
    private String _lastName;
    private String _country;
    private String _address;
    private String _city;
    private String _zipCode;

    public BE_Person(int _id, String _cprNo, String _firstName, String _lastName, String _country, String _address, String _city, String _zipCode) {
        this._id = _id;
        this._cprNo = _cprNo;
        this._firstName = _firstName;
        this._lastName = _lastName;
        this._country = _country;
        this._address = _address;
        this._city = _city;
        this._zipCode = _zipCode;
    }

    public String get_country() {
        return this._country;
    }

    public void set_country(String _country) {
        this._country = _country;
    }

    public String get_address() {
        return this._address;
    }

    public void set_address(String _address) {
        this._address = _address;
    }

    public String get_city() {
        return this._city;
    }

    public void set_city(String _city) {
        this._city = _city;
    }

    public String get_zipCode() {
        return this._zipCode;
    }

    public void set_zipCode(String _zipCode) {
        this._zipCode = _zipCode;
    }

    public int get_id() {
        return this._id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_firstName() {
        return this._firstName;
    }

    public void set_firstName(String _firstName) {
        this._firstName = _firstName;
    }

    public String get_lastName() {
        return this._lastName;
    }

    public void set_lastName(String _lastName) {
        this._lastName = _lastName;
    }

    public String get_cprNo() {
        return this._cprNo;
    }

    public void set_cprNo(String _cprNo) {
        this._cprNo = _cprNo;
    }
}