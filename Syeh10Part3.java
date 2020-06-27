
package syeh10part3;

import java.util.HashSet;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * An application that stores a collection of Students
 * This application allow the user to enter data about a student
 * When the user is finished the data will be written to a Student object 
 * and then the Student object will be added to an ArrayList
 * The user can then enter data for a new Student and repeat the process.
 * @author Shu Wei, Yeh. 22862541
 */
public class Syeh10Part3 extends Application {
    @Override
    public void start(Stage primaryStage) {
        textfield(primaryStage);
    }

    public void textfield(Stage primaryStage) {
        
        Label nameLabel = new Label("Student name:");
        TextField firstName = new TextField();
        firstName.setPromptText("Enter first name");
        firstName.setFont(new Font("Courier", 20));
        firstName.setAlignment(Pos.CENTER);
        
        TextField lastName = new TextField();
        lastName.setPromptText("Enter last name");
        lastName.setFont(new Font("Courier", 20));
        lastName.setAlignment(Pos.CENTER);
        
        VBox nameRoot = new VBox();
        nameRoot.setPadding(new Insets(10, 10, 10, 10));
        nameRoot.setSpacing(20);
        nameRoot.getChildren().addAll(nameLabel, firstName, lastName);
        
        
        
        //Slider for age
        Label ageLabel = new Label("Slid to a age:");
        //create a slider
        Slider age = new Slider(0, 100, 1);
        age.setValue(18);
        age.setShowTickMarks(true);
        age.setShowTickLabels(true);
        age.setMajorTickUnit(1);
        age.setMinorTickCount(5);
        age.valueProperty().addListener(evt
                -> ageLabel.setText(String.format("Age:%.0f", age.getValue())));
        VBox ageRoot = new VBox();
        ageRoot.setPadding(new Insets(10, 10, 10, 10));
        ageRoot.setSpacing(20);
        ageRoot.getChildren().addAll(ageLabel, age);

        
        
        //Radio for gender
        Label gender = new Label("Select gender:");
        ToggleGroup toggleGroup = new ToggleGroup();
        RadioButton male = new RadioButton("Male");
        male.setSelected(true);
        RadioButton female = new RadioButton("Female");
        male.setToggleGroup(toggleGroup);
        female.setToggleGroup(toggleGroup);
        male.setOnAction(evt -> {
            if (male.isSelected()) {
                gender.setText("Male");
            }
        });
        female.setOnAction(evt -> {
            if (female.isSelected()) {
                gender.setText("Female");
            }
        });
        VBox genderRoot = new VBox();
        genderRoot.setPadding(new Insets(10, 10, 10, 10));
        genderRoot.setSpacing(20);
        genderRoot.getChildren().addAll(gender, male, female);

        
        
        //Check boxes for the subjects the student has completed
        Label subjectsLabel = new Label("Subjects the student has completed:");
        //Check boxes
        CheckBox cb1 = new CheckBox("CSC00240 Data Communications & Networks (core)");
        CheckBox cb2 = new CheckBox("ISY00243 Systems Analysis and Design (core)");
        CheckBox cb3 = new CheckBox("ISY10209 Web Development I (core) 1,3");
        CheckBox cb4 = new CheckBox("ISY00243 Systems Analysis and Design (core) (Enrolled Awaiting Results)");
        CheckBox cb5 = new CheckBox("CSC00228 Database Systems I (pre-req: ISY00243)");
        CheckBox cb6 = new CheckBox("CSC72003 Programming II (core) (pre-req: CSC71001) 1,3");
        //Store subjects to HashSet
        HashSet<String> subjectsHashSet = new HashSet();
        subjectsHashSet.add(cb1.getText());
        subjectsHashSet.add(cb2.getText());
        subjectsHashSet.add(cb3.getText());
        subjectsHashSet.add(cb4.getText());
        subjectsHashSet.add(cb5.getText());
        subjectsHashSet.add(cb6.getText());
        //set a default subject
        cb1.setSelected(true);
        VBox subjectsRoot = new VBox();
        subjectsRoot.getChildren().addAll(subjectsLabel, cb1, cb2, cb3, cb4, cb5, cb6);
        
        
        
        //ListView
        Label languagesLabel = new Label("A programming language the student likes:");
        ListView<String> programmingLanguages = new ListView<>();
        programmingLanguages.getItems().addAll("SQL", "Java", "JavaScript", "C#", "C++", "PHP");
        HashSet<String> languagesHashSet = new HashSet();
        languagesHashSet.add("SQL");
        languagesHashSet.add("Java");
        languagesHashSet.add("JavaScript");
        languagesHashSet.add("C++");
        languagesHashSet.add("PHP");
        
        programmingLanguages.setPrefWidth(700);
        programmingLanguages.setPrefHeight(100);
        programmingLanguages.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        VBox languagesRoot = new VBox();
        languagesRoot.getChildren().addAll(languagesLabel, programmingLanguages);
        
        
        
        //Buttons for "New Student" and "Add Student"
        Button clear = new Button("New Student");
        clear.setOnAction(evt -> {
            firstName.setText("");
            lastName.setText("");
            age.setValue(18);
            male.setSelected(true);
            cb1.setSelected(true);
            cb2.setSelected(false);
            cb3.setSelected(false);
            cb4.setSelected(false);
            cb5.setSelected(false);
            cb6.setSelected(false);
            programmingLanguages.getSelectionModel().clearSelection();
        });

        Button save = new Button("Add Student");
        ObservableList<Student> data = FXCollections.observableArrayList(
            new Student("Jacob", "Smith", 23, "Male"),
            new Student("Smith", "Jacob", 100, "Female", subjectsHashSet, languagesHashSet));
        save.setOnAction(evt -> {
            data.add(new Student(
                        firstName.getText(),
                        lastName.getText(),
                        (int) age.getValue(), 
                        gender.getText(), 
                        subjectsHashSet, 
                        languagesHashSet));
            
            System.out.println(
                    "Student has been add to system:\n"+"firstName:"+firstName.getText()+"\nLast name:"+lastName.getText()+"\nAge:"+(int) age.getValue()+"\nGender:"+ gender.getText());
        });
        //Horizontal box for buttons
        HBox buttonsRoot = new HBox();
        buttonsRoot.setPadding(new Insets(20, 20, 20, 20));
        buttonsRoot.setSpacing(20);
        buttonsRoot.getChildren().addAll(clear, save);
        buttonsRoot.setAlignment(Pos.CENTER);
        
        
        
        //Show data from the ArrayList
        Label recordLabel = new Label("Students Record");
        recordLabel.setFont(new Font("Arial", 20));
        TableView<Student> table = new TableView<Student>();
        table.setEditable(true);
        TableColumn firstNameCol = new TableColumn("First Name");
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("firstName"));
        //
        TableColumn lastNameCol = new TableColumn("Last Name");
        lastNameCol.setMinWidth(100);
        lastNameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("lastName"));
        //
        TableColumn ageCol = new TableColumn("Age");
        ageCol.setMinWidth(200);
        ageCol.setCellValueFactory(new PropertyValueFactory<Student, Integer>("age"));
        //
        TableColumn genderCol = new TableColumn("Gender");
        genderCol.setMinWidth(200);
        genderCol.setCellValueFactory(new PropertyValueFactory<Student, String>("gender"));
        //
        TableColumn subjectsCol = new TableColumn("Completed Subjects");
        subjectsCol.setMinWidth(200);
        subjectsCol.setCellValueFactory(new PropertyValueFactory<Student, String>("subjects"));
        //
        TableColumn programmingLanguagesCol = new TableColumn("Likes Programming Language");
        programmingLanguagesCol.setMinWidth(200);
        programmingLanguagesCol.setCellValueFactory(new PropertyValueFactory<Student, String>("programmingLanguages"));
        //
        table.setItems(data);
        table.getColumns().addAll(firstNameCol, lastNameCol, ageCol, genderCol, subjectsCol, programmingLanguagesCol);
        //Vertical box for showing the record
        VBox recordRoot = new VBox();
        recordRoot.setSpacing(5);
        recordRoot.setPadding(new Insets(10, 0, 0, 10));
        recordRoot.getChildren().addAll(recordLabel, table);
        
        
        
        //Vertical box for all roots which are name, age, gender, subjects, languages and buttons
        VBox root = new VBox();
        root.setPadding(new Insets(20, 20, 20, 20));
        root.setSpacing(20);
        root.getChildren().addAll(nameRoot, ageRoot, genderRoot, subjectsRoot, languagesRoot, buttonsRoot, recordRoot);
        
        
        
        // Create a ScrollPane
        ScrollPane scrollPane = new ScrollPane();
        // Set content for ScrollPane
        scrollPane.setContent(root);
         // Always show vertical scroll bar
        scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        // Horizontal scroll bar is only displayed when needed
        scrollPane.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
        
        
        Scene scene = new Scene(scrollPane, 1200, 600);
        primaryStage.setTitle("syeh10Part3 - Enter data about a student.");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    
    /**
     * Student class for collection.
     */
    public class Student {
        // text field
        private String firstName; 
        // text field
        private String lastName; 
        // slider
        private Integer age;
        // radio buttons
        private String gender;
        // check boxes
        private HashSet<String> subjects = new HashSet();
        // list view
        private HashSet<String> programmingLanguages = new HashSet();

        /**
         * Constructor.
         */
        public Student() {
        }

        /**
         * Constructor.
         * @param firstName
         * @param lastName
         * @param age
         * @param gender 
         */
        public Student(String firstName, String lastName, Integer age, String gender) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
            this.gender = gender;
        }
        
        /**
         * Constructor.
         * @param firstName
         * @param lastName
         * @param age
         * @param gender
         * @param subjects
         * @param programmingLanguages 
         */
        public Student(String firstName, String lastName, Integer age, String gender, HashSet<String> subjects, HashSet<String> programmingLanguages ) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
            this.gender = gender;
            this.subjects = subjects;
            this.programmingLanguages = programmingLanguages;
        }
        /**
         * A mutator method (setter) 
         * For setting firstName
         * @param firstName 
         */
        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        /**
         * A mutator method (setter) 
         * For setting lastName
         * @param lastName 
         */
        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        /**
         * A mutator method (setter) 
         * For setting age
         * @param age 
         */
        public void setAge(Integer age) {
            this.age = age;
        }

        /**
         * A mutator method (setter) 
         * For setting gender
         * @param gender 
         */
        public void setGender(String gender) {
            this.gender = gender;
        }

        /**
         * A mutator method (setter) 
         * For setting subjects
         * @param subjects 
         */
        public void setSubjects(HashSet<String> subjects) {
            this.subjects = subjects;
        }

        /**
         * A mutator method (setter) 
         * For setting programming Languages
         * @param programmingLanguages 
         */
        public void setProgrammingLanguages(HashSet<String> programmingLanguages) {
            this.programmingLanguages = programmingLanguages;
        }
        
        /**
         * An accessor method (getter) 
         * For get firstName
         * @return firstName
         */
        public String getFirstName() {
            return firstName;
        }

        /**
         * An accessor method (getter) 
         * For get lastName
         * @return 
         */
        public String getLastName() {
            return lastName;
        }
        
        /**
         * An accessor method (getter) 
         * For get age
         * @return 
         */
        public Integer getAge() {
            return age;
        }

        /**
         * An accessor method (getter) 
         * For get gender
         * @return 
         */
        public String getGender() {
            return gender;
        }

        /**
         * An accessor method (getter) 
         * For get subjects
         * @return 
         */
        public HashSet<String> getSubjects() {
            return subjects;
        }

        /**
         * An accessor method (getter) 
         * For get programmingLanguages
         * @return 
         */
        public HashSet<String> getProgrammingLanguages() {
            return programmingLanguages;
        }
   
    }

}
