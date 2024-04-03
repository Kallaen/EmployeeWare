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
    public boolean equals(Object arg0) {
        try {
            BE_Department compareDep = (BE_Department) arg0;
            if (compareDep.get_id() == this.get_id() && compareDep.get_name() == this.get_name())
                return true;
        } catch (Exception e) {
            return false;
        }
        return false;
    }

}
