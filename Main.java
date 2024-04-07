import DAL.Repository;
import GUI.MainWindow;

public class Main {

    public static void main(String[] args) {
        Repository.INSTANCE.setDatabaseType(Repository.DatabaseType.DATABASE);
        MainWindow w = new MainWindow();
        w.view();
    }
}