import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.sql.Connection;

public class StudentHandler
{
    private static final String DB_DATABASE_NAME = "java_lab_assignment";
    private Connection databaseConnection;
    private final Stage primaryStage;

    private final int width;
    private final int height;

    public StudentHandler(Stage primaryStage, int width, int height, boolean showParentView)
    {
        this(DB_DATABASE_NAME, primaryStage, width, height, showParentView);
    }

    public StudentHandler(String databaseName, Stage primaryStage, int width, int height, boolean showParentView)
    {
        this.primaryStage = primaryStage;
        this.width = width;
        this.height = height;

        initializeConnection(databaseName);

        if (showParentView)
        {
            showParentView();
        }
    }

    public void initializeConnection(String databaseName)
    {
        try
        {
            log("Attempting Connection");
//            databaseConnection = DriverManager.getConnection(Constants.DatabaseConstants.DB_CONNECTION_URL + databaseName,
//                    Constants.DatabaseConstants.DB_CONNECTION_USERNAME,
//                    Constants.DatabaseConstants.DB_CONNECTION_PASSWORD);

            log("Attempted Connection");
        }
        catch (Exception exception)
        {
            databaseConnection = null;
            log("Exception at initializing connection: " + exception.getMessage());
        }
    }

    public void showParentView()
    {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER_LEFT);


        gridPane.getRowConstraints().add(new RowConstraints(0.15 * height));
        gridPane.getRowConstraints().add(new RowConstraints(0.8 * height));

        gridPane.getColumnConstraints().add(new ColumnConstraints(width));

        Scene scene = new Scene(gridPane, width, height); // Set/Replace the stage title Pane in 1st parameter
        primaryStage.setTitle("Player Game Information"); // Place the scene in the stage
        primaryStage.setScene(scene); // Display the stage
        primaryStage.show(); // Display the stage
    }

    private static void log(String message)
    {
        System.out.println("<<StudentHandler>> " + message);
    }
}
