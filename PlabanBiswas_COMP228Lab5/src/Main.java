import exercise1.Exercise1Manager;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Exercise1Manager exercise1Manager = new Exercise1Manager(primaryStage, 1600, 900);
        exercise1Manager.showParentView();

    }
}