import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class StudentInformation
{
    private final Stage primaryStage;

    //region Student Details Pane
    Label nameLabel;
    TextField nameTextField;

    Label addressLabel;
    TextField addressTextField;

    Label provinceLabel;
    TextField provinceTextField;

    Label cityLabel;
    TextField cityTextField;

    Label postalCodeLabel;
    TextField postalCodeTextField;

    Label phoneNumberLabel;
    TextField phoneNumberTextField;

    Label emailLabel;
    TextField emailTextField;

    Button displayButton;
    //endregion

    //region Student CheckBox
    CheckBox studentCouncilCheckBox;
    CheckBox volunteerWorkCheckBox;
    //endregion

    //region Student Radio Button
    RadioButton computerScienceRadioButton;
    RadioButton businessRadioButton;
    //endregion

    //region Student Combo Boxes and List Boxes
    ComboBox<String> courseComboBox;
    ListView<String> courseListView;
    //endregion

    //region Student Text Box
    TextArea displayTextArea;
    //endregion
    public StudentInformation(Stage primaryStage)
    {
        this.primaryStage = primaryStage;
    }

    public void showStudentInformation()
    {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20, 30, 20, 30));

        addLabelsTextFields(gridPane);
        addCheckBox(gridPane);
        addRadioButtons(gridPane);
        addComboBoxesListBoxes(gridPane);
        addTextArea(gridPane);

        Scene scene = new Scene(gridPane, 1600, 900); // Set/Replace the stage title Pane in 1st parameter
        primaryStage.setTitle("Student Information Panel"); // Place the scene in the stage
        primaryStage.setScene(scene); // Display the stage
        primaryStage.show(); // Display the stage
    }

    private void addLabelsTextFields(GridPane gridPane)
    {
        // Student Labels and Text Input
        nameLabel = new Label("Name: ");
        nameTextField = new TextField("Enter Name Here...");

        addressLabel = new Label("Address: ");
        addressTextField = new TextField("Enter Address Here...");

        provinceLabel = new Label("Province: ");
        provinceTextField = new TextField("Enter Province Here...");

        cityLabel = new Label("City: ");
        cityTextField = new TextField("Enter City Here...");

        postalCodeLabel = new Label("Postal Code: ");
        postalCodeTextField = new TextField("Enter Postal Code Here...");

        phoneNumberLabel = new Label("Phone number: ");
        phoneNumberTextField = new TextField("Enter Phone Number Here...");

        emailLabel = new Label("Email: ");
        emailTextField = new TextField("Enter Email Here...");

        displayButton = new Button("Display");


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

        gridPane.add(displayButton, 0, 7);
    }

    private void addCheckBox(GridPane gridPane)
    {
        studentCouncilCheckBox = new CheckBox("Student Council");
        volunteerWorkCheckBox = new CheckBox("Volunteer Work");

        gridPane.add(studentCouncilCheckBox, 2, 1);
        gridPane.add(volunteerWorkCheckBox, 2, 5);
    }

    private void addRadioButtons(GridPane gridPane)
    {
        computerScienceRadioButton = new RadioButton("Computer Science");
        businessRadioButton = new RadioButton("Business");

        ToggleGroup toggleGroup = new ToggleGroup();
        computerScienceRadioButton.setToggleGroup(toggleGroup);
        businessRadioButton.setToggleGroup(toggleGroup);
        toggleGroup.selectToggle(computerScienceRadioButton);

        gridPane.add(computerScienceRadioButton, 3, 0);
        gridPane.add(businessRadioButton, 4, 0);
    }

    private void addComboBoxesListBoxes(GridPane gridPane)
    {
        //TODO: Reference of Radio Button Needed
        String[] computerScienceCourses = new String[]{"Java", "C#", "Python", "Data Structures", "C++"};
        String[] businessCourses = new String[]{"Project Management", "Six Sigma", "Statistics", "Mathematics", "Resource Management"};

        courseComboBox = new ComboBox<String>(FXCollections.observableArrayList(computerScienceCourses));
        // TODO: Add an item when user selects from list view (no duplicates)
        courseListView = new ListView<String>();

        gridPane.add(courseComboBox, 3, 3);
        gridPane.add(courseListView, 3, 4);
    }

    private void addTextArea(GridPane gridPane)
    {
        displayTextArea = new TextArea();

        // TODO: Display details on Click
        gridPane.add(displayTextArea, 0, 8);
    }
}
