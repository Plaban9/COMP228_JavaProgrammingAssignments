import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class StudentInformation
{
    private static final int ROW_COUNT = 11;
    private static final int COLUMN_COUNT = 4;

    private final int width;
    private final int height;
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
    ToggleGroup toggleGroup;
    RadioButton computerScienceRadioButton;
    RadioButton businessRadioButton;
    //endregion

    //region Student Combo Boxes and List Boxes
    String[] computerScienceCourses = new String[]{"Java", "C#", "Python", "Data Structures", "C++"};
    String[] businessCourses = new String[]{"Project Management", "Six Sigma", "Statistics", "Mathematics", "Resource Management"};
    ComboBox<String> courseComboBox;
    ListView<String> courseListView;
    //endregion

    //region Student Text Box
    TextArea displayTextArea;

    //endregion
    public StudentInformation(Stage primaryStage, int width, int height)
    {
        this.primaryStage = primaryStage;
        this.width = width;
        this.height = height;
    }

    public void showStudentInformation()
    {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER_LEFT);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 150, 10, 10));

        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setPercentHeight((float) height / ROW_COUNT);

        for (int i = 0; i < ROW_COUNT; i++)
        {
            gridPane.getRowConstraints().add(rowConstraints);
        }


        addLabelsTextFields(gridPane);
        addCheckBox(gridPane);
        addRadioButtons(gridPane);
        addComboBoxesListBoxes(gridPane);
        addTextArea(gridPane);

        Scene scene = new Scene(gridPane, width, height); // Set/Replace the stage title Pane in 1st parameter
        primaryStage.setTitle("Student Information Panel"); // Place the scene in the stage
        primaryStage.setScene(scene); // Display the stage
        primaryStage.show(); // Display the stage
    }

    private void addLabelsTextFields(GridPane gridPane)
    {
        // Student Labels and Text Input
        nameLabel = new Label("Name: ");
        nameTextField = new TextField();
        nameTextField.setPromptText("Enter Name Here...");

        addressLabel = new Label("Address: ");
        addressTextField = new TextField();
        addressTextField.setPromptText("Enter Address Here...");

        provinceLabel = new Label("Province: ");
        provinceTextField = new TextField();
        provinceTextField.setPromptText("Enter Province Here...");

        cityLabel = new Label("City: ");
        cityTextField = new TextField();
        cityTextField.setPromptText("Enter City Here...");

        postalCodeLabel = new Label("Postal Code: ");
        postalCodeTextField = new TextField();
        postalCodeTextField.setPromptText("Enter Postal Code Here...");

        phoneNumberLabel = new Label("Phone number: ");
        phoneNumberTextField = new TextField();
        phoneNumberTextField.setPromptText("Enter Phone Number Here...");

        emailLabel = new Label("Email: ");
        emailTextField = new TextField();
        emailTextField.setPromptText("Enter Email Here...");

        displayButton = new Button("Display");

        HBox hDisplayBox = new HBox(displayButton);
        hDisplayBox.setMinWidth(width);
        hDisplayBox.setAlignment(Pos.CENTER);
        hDisplayBox.setPadding(new Insets(10, 10, 10, 10));

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

        gridPane.add(hDisplayBox, 0, 7);

        gridPane.getColumnConstraints().add(new ColumnConstraints(width * 0.25f));
        gridPane.getColumnConstraints().add(new ColumnConstraints(width * 0.25f));

        displayButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                displayTextArea.setText(getDisplayText());
            }
        });
    }

    private void addCheckBox(GridPane gridPane)
    {
        studentCouncilCheckBox = new CheckBox("Student Council");
        volunteerWorkCheckBox = new CheckBox("Volunteer Work");

        gridPane.add(studentCouncilCheckBox, 2, 1);
        gridPane.add(volunteerWorkCheckBox, 2, 5);

        gridPane.getColumnConstraints().add(new ColumnConstraints(width * 0.2f));
    }

    private void addRadioButtons(GridPane gridPane)
    {
        toggleGroup = new ToggleGroup();

        computerScienceRadioButton = new RadioButton("Computer Science");
        businessRadioButton = new RadioButton("Business");

        computerScienceRadioButton.setToggleGroup(toggleGroup);
        businessRadioButton.setToggleGroup(toggleGroup);

        HBox radioButtonHBox = new HBox(10f, computerScienceRadioButton, businessRadioButton);
        radioButtonHBox.setPadding(new Insets(10f, 10f, 10f, 10f));
        radioButtonHBox.setMinWidth(0.3f * width);
        radioButtonHBox.setAlignment(Pos.CENTER_LEFT);

        toggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>()
        {
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle old_toggle, Toggle new_toggle)
            {
                if (toggleGroup.getSelectedToggle() != null)
                {
                    RadioButton radioButton = (RadioButton) toggleGroup.getSelectedToggle();

                    if (courseComboBox != null && courseListView != null)
                    {
                        switch (radioButton.getText())
                        {
                            case "Computer Science":
                                courseComboBox.setItems(FXCollections.observableArrayList(computerScienceCourses));
                                courseListView.getItems().clear();
                                break;

                            case "Business":
                                courseComboBox.setItems(FXCollections.observableArrayList(businessCourses));
                                courseListView.getItems().clear();
                                break;
                        }
                    }
                }
            }
        });

        toggleGroup.selectToggle(computerScienceRadioButton);

        gridPane.add(radioButtonHBox, 3, 0);
//        gridPane.add(businessRadioButton, 4, 0);

    }

    private void addComboBoxesListBoxes(GridPane gridPane)
    {
        courseComboBox = new ComboBox<String>(FXCollections.observableArrayList(computerScienceCourses));
        courseListView = new ListView<String>();

        courseComboBox.setMaxWidth(0.24f * width);
        courseListView.setMaxWidth(0.24f * width);
        courseListView.setMinHeight(0.375f * height);

        courseComboBox.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent ae)
            {
                String selectedValue = courseComboBox.getValue();

                if (!courseListView.getItems().contains(selectedValue))
                {
                    courseListView.getItems().add(selectedValue);
                }
            }
        });

        VBox verticalBox = new VBox(10f, courseComboBox, courseListView);
        verticalBox.setAlignment(Pos.TOP_LEFT);
        verticalBox.setPadding(new Insets(0f, 10f, 25f, 10f));

        gridPane.add(verticalBox, 3, 4);
//        gridPane.add(courseListView, 3, 4);
    }

    private void addTextArea(GridPane gridPane)
    {
        displayTextArea = new TextArea();
        displayTextArea.setMinWidth(width * 0.98f);
        displayTextArea.setMaxWidth(width * 0.98f);

        displayTextArea.setMinHeight(height * 0.25f);
        displayTextArea.setMaxWidth(height * 0.25f);

        gridPane.add(displayTextArea, 0, 9);
    }

    private String getDisplayText()
    {
        String displayText;

        displayText = nameTextField.getText() + ", " + addressTextField.getText() + ", " + provinceTextField.getText() + ", " + cityTextField.getText() + ", " + postalCodeTextField.getText() + ", " + phoneNumberTextField.getText() + ", " + emailTextField.getText();

        displayText += "\n";
        displayText += "Program: " + getProgram() + "\n";
        displayText += "---- Courses ----\n";
        displayText += getListViewItems();

        if (getAdditionalWork() != null && !getAdditionalWork().isEmpty())
        {
            displayText += "---- Additional Work ----\n";
            displayText += getAdditionalWork();
        }

        return displayText;
    }

    private String getListViewItems()
    {
        StringBuilder items = new StringBuilder();

        for (var item : courseListView.getItems())
        {
            items.append(item).append("\n");
        }

        return items.toString();
    }

    private String getProgram()
    {
        return ((RadioButton) toggleGroup.getSelectedToggle()).getText();
    }

    private String getAdditionalWork()
    {
        String additionalWork = "";

        if (studentCouncilCheckBox.isSelected())
        {
            additionalWork += studentCouncilCheckBox.getText() + "\n";
        }

        if (volunteerWorkCheckBox.isSelected())
        {
            additionalWork += volunteerWorkCheckBox.getText();
        }

        return additionalWork;
    }
}
