import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
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
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20, 30, 20, 30));

        Scene scene = new Scene(gridPane, 1600, 900); // Set/Replace the stage title Pane in 1st parameter
        primaryStage.setTitle("ShowFlowPanel"); // Place the scene in the stage
        primaryStage.setScene(scene); // Display the stage
        primaryStage.show(); // Display the stage
    }
}