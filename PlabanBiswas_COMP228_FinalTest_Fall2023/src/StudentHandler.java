import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class StudentHandler
{
    private static final String DB_DATABASE_NAME = "comp228_final_assignment";
    private Connection databaseConnection;
    private final Stage primaryStage;
    private final int width;
    private final int height;

    private ArrayList<Student> studentArrayList;

    public StudentHandler(Stage primaryStage, int width, int height, boolean showParentView)
    {
        this(DB_DATABASE_NAME, primaryStage, width, height, showParentView);
    }

    public StudentHandler(String databaseName, Stage primaryStage, int width, int height, boolean showParentView)
    {
        this.primaryStage = primaryStage;
        this.width = width;
        this.height = height;

        studentArrayList = new ArrayList<>();

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
            databaseConnection = DriverManager.getConnection(Constants.DatabaseConstants.DB_CONNECTION_URL + databaseName,
                    Constants.DatabaseConstants.DB_CONNECTION_USERNAME,
                    Constants.DatabaseConstants.DB_CONNECTION_PASSWORD);

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

        addInputComponents(gridPane);

        Scene scene = new Scene(gridPane, width, height); // Set/Replace the stage title Pane in 1st parameter
        primaryStage.setTitle("Student Information Panel"); // Place the scene in the stage
        primaryStage.setScene(scene); // Display the stage
        primaryStage.show(); // Display the stage
    }

    private void addInputComponents(GridPane gridPane)
    {
        Label inputLabel = new Label("Select City: ");
        TextField inputTextField = new TextField();
        inputTextField.setPromptText("Enter city here...");
        inputTextField.setMinWidth(100);

        Button displayCityButton = new Button("Display");
        displayCityButton.setMinWidth(100);

        HBox hBox = new HBox(inputLabel, inputTextField, displayCityButton);
        hBox.setAlignment(Pos.CENTER);
        hBox.setMinWidth(width);
        hBox.setSpacing(10);

        String cssLayout = """
                -fx-border-color: grey;
                -fx-border-insets: 5;
                -fx-border-width: 1;
                -fx-border-style: solid;
                """;

        hBox.setStyle(cssLayout);

        displayCityButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                if (!inputTextField.getText().isEmpty())
                {
                    executeDatabaseSelectOperation(inputTextField.getText());
                    showTable(gridPane);
                    return;
                }

                showErrorPopup();
            }
        });

        gridPane.add(hBox, 0, 0);
    }

    private void showErrorPopup()
    {
        Stage popUpStage = new Stage(StageStyle.DECORATED);
        popUpStage.setWidth(300);
        popUpStage.setHeight(100);

        Label errorLabel = new Label("ERROR: Please input a value for city.");

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(errorLabel);
        vbox.setAlignment(Pos.CENTER);

        Scene stageScene = new Scene(vbox, 300, 100);
        popUpStage.setScene(stageScene);
        popUpStage.setTitle("Error!!");

        popUpStage.initModality(Modality.APPLICATION_MODAL);
        popUpStage.showAndWait();
    }

    private void executeDatabaseSelectOperation(String city)
    {
        String selectQuery = "SELECT * FROM students WHERE city = \"" + city + "\"";

        try
        {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(selectQuery);

            ResultSet resultSet = preparedStatement.executeQuery();

            studentArrayList.clear();

            while(resultSet.next())
            {
                Student playerGameModel = new Student(resultSet.getString(Constants.StudentTableConstants.COLUMN_STUDENT_ID),
                        resultSet.getString(Constants.StudentTableConstants.COLUMN_FIRST_NAME),
                        resultSet.getString(Constants.StudentTableConstants.COLUMN_LAST_NAME),
                        resultSet.getString(Constants.StudentTableConstants.COLUMN_ADDRESS),
                        resultSet.getString(Constants.StudentTableConstants.COLUMN_CITY),
                        resultSet.getString(Constants.StudentTableConstants.COLUMN_PROVINCE),
                        resultSet.getString(Constants.StudentTableConstants.COLUMN_POSTAL_CODE));

                studentArrayList.add(playerGameModel);
            }

            for (Student student : studentArrayList)
            {
                System.out.println(student.toString());
            }
        }
        catch (Exception exception)
        {
            log("Exception at selectOperation: " + exception.getMessage());

            studentArrayList.clear();
        }
    }

    private void showTable(GridPane gridPane)
    {
        VBox vBox = new VBox(getTableModel());
        vBox.setAlignment(Pos.CENTER);
        vBox.setMinWidth(width);
        vBox.setSpacing(10);

        String cssLayout = """
                -fx-border-color: grey;
                -fx-border-insets: 5;
                -fx-border-width: 1;
                -fx-border-style: solid;
                """;

        vBox.setStyle(cssLayout);

        gridPane.add(vBox, 0, 1);
    }

    private TableView<Student> getTableModel()
    {
        ObservableList<Student> data = FXCollections.observableArrayList(studentArrayList);

        TableView<Student> table = new TableView<>();
        table.setEditable(false);

        TableColumn studentID = new TableColumn("Student ID");
        studentID.setCellValueFactory(new PropertyValueFactory<>("studentID"));

        TableColumn firstName = new TableColumn("First Name");
        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn lastName = new TableColumn("Last Name");
        lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn address = new TableColumn("Address");
        address.setCellValueFactory(new PropertyValueFactory<>("address"));

        TableColumn city = new TableColumn("City");
        city.setCellValueFactory(new PropertyValueFactory<>("city"));

        TableColumn province = new TableColumn("Province");
        province.setCellValueFactory(new PropertyValueFactory<>("province"));

        TableColumn postalCode = new TableColumn("Postal Code");
        postalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));

        table.getColumns().addAll(studentID, firstName, lastName, address, city, province, postalCode);
        table.setItems(data);

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_SUBSEQUENT_COLUMNS);

        return table;
    }

    private static void log(String message)
    {
        System.out.println("<<StudentHandler>> " + message);
    }
}
