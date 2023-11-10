import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

        addLabelsTextFields(gridPane);
        addCheckBox(gridPane);
        addRadioButtons(gridPane);
        addComboBoxesListBoxes(gridPane);
        addTextField(gridPane);


        Scene scene = new Scene(gridPane, 1600, 900); // Set/Replace the stage title Pane in 1st parameter
        primaryStage.setTitle("ShowFlowPanel"); // Place the scene in the stage
        primaryStage.setScene(scene); // Display the stage
        primaryStage.show(); // Display the stage
    }

    private void addLabelsTextFields(GridPane gridPane)
    {
        // Student Labels and Text Input
        Label nameLabel = new Label("Name: ");
        TextField nameTextField = new TextField("Enter Name Here...");

        Label addressLabel = new Label("Address: ");
        TextField addressTextField = new TextField("Enter Address Here...");

        Label provinceLabel = new Label("Province: ");
        TextField provinceTextField = new TextField("Enter Province Here...");

        Label cityLabel = new Label("City: ");
        TextField cityTextField = new TextField("Enter City Here...");

        Label postalCodeLabel = new Label("Postal Code: ");
        TextField postalCodeTextField = new TextField("Enter Postal Code Here...");

        Label phoneNumberLabel = new Label("Phone number: ");
        TextField phoneNumberTextField = new TextField("Enter Phone Number Here...");

        Label emailLabel = new Label("Email: ");
        TextField emailTextField = new TextField("Enter Email Here...");


        //TODO: ADD Display Button and click Logic
        Button displayButton = new Button("Display");


        gridPane.add(nameLabel, 0, 0);
        gridPane.add(nameTextField, 1, 0);

        gridPane.add(addressLabel, 0, 1);
        gridPane.add(addressTextField, 1, 1);

        gridPane.add(provinceLabel, 0, 2);
        gridPane.add(provinceTextField, 1, 2);

        gridPane.add(cityLabel, 0, 3);
        gridPane.add(cityTextField, 1, 3);

        gridPane.add(postalCodeLabel, 0, 4);
        gridPane.add(postalCodeTextField, 1, 4);

        gridPane.add(phoneNumberLabel, 0, 5);
        gridPane.add(phoneNumberTextField, 1, 5);

        gridPane.add(emailLabel, 0, 6);
        gridPane.add(emailTextField, 1, 6);

        gridPane.add(displayButton, 0,7);
    }

    private void addCheckBox(GridPane gridPane)
    {
        CheckBox studentCouncilCheckBox = new CheckBox("Student Council");
        CheckBox volunteerWorkCheckBox = new CheckBox("Volunteer Work");

        gridPane.add(studentCouncilCheckBox,2, 1);
        gridPane.add(volunteerWorkCheckBox,2, 5);
    }

    private void addRadioButtons(GridPane gridPane)
    {
        RadioButton computerScienceRadioButton = new RadioButton("Computer Science");
        RadioButton businessRadioButton = new RadioButton("Business");

        ToggleGroup toggleGroup = new ToggleGroup();
        computerScienceRadioButton.setToggleGroup(toggleGroup);
        businessRadioButton.setToggleGroup(toggleGroup);
        toggleGroup.selectToggle(computerScienceRadioButton);

        gridPane.add(computerScienceRadioButton, 3, 0);
        gridPane.add(businessRadioButton, 4,0);
    }

    private void addComboBoxesListBoxes(GridPane gridPane)
    {
        //TODO: Reference of Radio Button Needed
        String[] computerScienceCourses = new String[]{"Java", "C#", "Python", "Data Structures", "C++"};
        String[] businessCourses = new String[]{"Project Management", "Six Sigma", "Statistics", "Mathematics", "Resource Management"};

        ComboBox<String> courseComboBox = new ComboBox<String>(FXCollections.observableArrayList(computerScienceCourses));

        // TODO: Add an item when user selects from list view (no duplicates)
        ListView<String> courseListView = new ListView<String>();

        gridPane.add(courseComboBox, 3,3);
        gridPane.add(courseListView, 3,4);
    }

    private void addTextField(GridPane gridPane)
    {
        TextArea displayTextArea = new TextArea();

        gridPane.add(displayTextArea, 0,8);
    }
}