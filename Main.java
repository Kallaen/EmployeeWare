import DAL.Repository;
import GUI.mainWindow;

public class Main {

    public static void main(String[] args) {
        Repository.INSTANCE.setDatabaseType(Repository.DatabaseType.DATABASE);
        mainWindow w = new mainWindow();
        w.run();
    }
}