package ui;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Classroom;
import model.UserAccount;

public class ClassroomGUI {
	

    @FXML
    private TableView<UserAccount> tvAccountList;

    @FXML
    private TableColumn<UserAccount,String> tcUsername;

    @FXML
    private TableColumn<UserAccount,String> tcGender;

    @FXML
    private TableColumn<UserAccount,String> tcCareer;

    @FXML
    private TableColumn<UserAccount,String> tcBirthday;

    @FXML
    private TableColumn<UserAccount,String> tcBrowser;
	
	@FXML
	private Pane mainPane;
	
	@FXML
	private TextField txfUser;

	@FXML
	private TextField txfPassword;
	
	@FXML
	private TextField urlPhoto,username,password;
	
	@FXML
	private CheckBox cbSoftEng,cbTeleEng,cbIndEng;
	
	@FXML
    private ImageView imageUser;
	
	@FXML
	private RadioButton rbMale,rbFemale,rbOther;
	
	@FXML
	private ComboBox <String>cbxBrowser;
	
	@FXML
	private Label lbUserRfe;
	
	@FXML
	private DatePicker dpBirth;
	
	private Classroom classroom;
	
	private ObservableList<UserAccount> observableList;
	
	private ObservableList<String> items;
	
	private Stage mainStage;
	
	public ClassroomGUI(){
		classroom =new Classroom();
		imageUser= new ImageView();
		
		dpBirth= new DatePicker();
		dpBirth.setEditable(false);
		
		items = FXCollections.observableArrayList();
		items.addAll("Firefox", "Google Chrome","Microsft Edge","Safari", "Opera");
		cbxBrowser= new ComboBox<>(items);
		
	}
	
	public void initializableTableView() {
		observableList= FXCollections.observableArrayList(classroom.getAccounts());
		
		tvAccountList.setItems(observableList);
		tcUsername.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("username"));
		tcGender.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("gender"));
		tcCareer.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("career"));
		tcBirthday.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("birthday"));
		tcBrowser.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("browser"));
	}


	@FXML
	public void showForm1(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("login.fxml"));
		fxmlLoader.setController(this);
		Parent form= fxmlLoader.load();
		mainPane.getChildren().setAll(form);
		
	}
	
	@FXML
	public void beInside(ActionEvent event)throws IOException {
		
		UserAccount found=classroom.userExist(txfUser.getText());
		if(found!=null) {
			
			FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("account-list.fxml"));
			fxmlLoader.setController(this);
			Parent form2= fxmlLoader.load();
			mainPane.getChildren().setAll(form2);
			initializableTableView();
			
			Image imageLoad= new Image(found.getPhoto());
			imageUser.setImage(imageLoad);
			lbUserRfe.setText(found.getUsername());
		}
		else
			advertisement("This User does not exist");
		
	}

	@FXML
	public void registration(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("register.fxml"));
		fxmlLoader.setController(this);
		Parent form3= fxmlLoader.load();
		mainPane.getChildren().setAll(form3);
	}
	
	 @FXML
	 public void signUpComplete(ActionEvent event) throws IOException {
		 String usernm=username.getText();
		 String passwd=password.getText();
		 String photo=urlPhoto.getText();
		 String gender="";
		 String major="";
		 LocalDate day=dpBirth.getValue();
		 String date=day.toString();
		 
		 if(rbMale.isSelected()) {
			 gender="Male";
		 }
		 else if(rbFemale.isSelected()) {
			 gender="Female";
		 }
		 else if(rbOther.isSelected()) {
			 gender="Other";
		 }
		 
		 if(cbSoftEng.isSelected()) {
			 major="Software Engineering";
		 }
		 else if(cbTeleEng.isSelected()) {
			 major="Telematic Engineering";
		 }
		 else if(cbIndEng.isSelected()) {
			 major="Industrial Engineering";
		 }
		 
		String brow="";
		 
		boolean empty=classroom.chechUser(usernm,passwd,gender,major,date,brow,photo);
		 if(empty)
			 advertisement("No boxes to be filled in"); 
		 else if(classroom.userExist(usernm)!=null) {
			 advertisement("The user already exist");
		 }
		 else {
			 if(classroom.add(new UserAccount(usernm,passwd, gender, major, date, brow,photo))) {
				 
				 advertisement("Has been added a new student"); 
				 FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("login.fxml"));
				 fxmlLoader.setController(this);
				 Parent form4= fxmlLoader.load();
				 mainPane.getChildren().setAll(form4);
			 }
			 else
				 advertisement("Could not be registered");
		 }
	 }
	 
	 @FXML
	 public void searchPhoto(ActionEvent event) throws MalformedURLException{
		FileChooser fileChooser= new FileChooser();
		fileChooser.setTitle("Open Resource File");
		File selectedDirectory= fileChooser.showOpenDialog(mainStage);
		urlPhoto.setText(selectedDirectory.toURI().toURL().toString());
	 }
	 
	 
	 public void advertisement(String info) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText(info);
			alert.showAndWait();
		}
	
	 @FXML
	 public void logOut(ActionEvent event) throws IOException {
		 FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("login.fxml"));
		 fxmlLoader.setController(this);
		 Parent form5= fxmlLoader.load();
		 mainPane.getChildren().setAll(form5);
	 }
	 
	 @FXML
	 private void back(ActionEvent event) throws IOException {
		 logOut(event);
	 }
}

