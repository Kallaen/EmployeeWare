package BE;

public class BE_Department {
    public BE_Department() {}

    private int _id;
    private String _name;

    public BE_Department(int _id, String _name) {
        this._id = _id;
        this._name = _name;
    }

    public int get_id() {
        return this._id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_name() {
        return this._name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BE_Department other = (BE_Department) o;
        return get_id() == other.get_id();
    }

    @Override
    public final int hashCode() {
        return getClass().hashCode();
    }

}
