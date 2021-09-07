package ui;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
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
	
	private Classroom classroom;
	
	private ObservableList<UserAccount> observableList;
	
	public ClassroomGUI(){
		classroom =new Classroom();
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
		FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("account-list.fxml"));
		fxmlLoader.setController(this);
		Parent form2= fxmlLoader.load();
		mainPane.getChildren().setAll(form2);
	}

	@FXML
	public void registration(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("register.fxml"));
		fxmlLoader.setController(this);
		Parent form3= fxmlLoader.load();
		mainPane.getChildren().setAll(form3);
	}

}

