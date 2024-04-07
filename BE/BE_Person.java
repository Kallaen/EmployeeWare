package BE;

public class BE_Person {
    public BE_Person() {}


    private int id;
    private String cprNo;
    private String firstName;
    private String lastName;
    private String country;
    private String address;
    private String city;
    private String zipCode;

    public BE_Person(int _id, String _cprNo, String _firstName, String _lastName, String _country, String _address, String _city, String _zipCode) {
        this.id = _id;
        this.cprNo = _cprNo;
        this.firstName = _firstName;
        this.lastName = _lastName;
        this.country = _country;
        this.address = _address;
        this.city = _city;
        this.zipCode = _zipCode;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return this.zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCprNo() {
        return this.cprNo;
    }

    public void setCprNo(String cprNo) {
        this.cprNo = cprNo;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BE_Person other = (BE_Person) o;
        return getId() == other.getId();
    }

    @Override
    public final int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return getFirstName() + " " + getLastName();
    }
}