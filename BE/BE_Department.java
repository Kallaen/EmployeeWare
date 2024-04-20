package BE;

public class BE_Department {
    public BE_Department() {}

    private int id;
    private String name;

    public BE_Department(int _id, String _name) {
        this.id = _id;
        this.name = _name;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BE_Department other = (BE_Department) o;
        return getId() == other.getId();
    }

    @Override
    public final int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return getName();
    }

}
