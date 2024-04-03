import DAL.Repository;

public class Main {

    public static void main(String[] args) {
        Repository.INSTANCE.setDatabaseType(Repository.DatabaseType.IN_MEMORY);
    }
}