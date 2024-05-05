import DAL.Repository;
import GUI.MainView;

public class Main {

    public static void main(String[] args) {
        Repository.INSTANCE.setDatabaseType(Repository.DatabaseType.IN_MEMORY);
        MainView w = new MainView();
        w.view();
    }
}