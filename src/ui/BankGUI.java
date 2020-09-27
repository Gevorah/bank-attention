package ui;

import java.io.IOException;

import CustomException.InvalidInformationException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import model.Bank;

public class BankGUI {

	private Bank main;

	@FXML
	private BorderPane mainPanel;

	@FXML
	private Label turn;

	@FXML
	private Label identificationUser;

	@FXML
	private Label nameUser;

	@FXML
	private ToggleGroup Trasactison;

	@FXML
	private TableView<?> information;

	@FXML
	private TableColumn<?, ?> ColumnName;

	@FXML
	private TableColumn<?, ?> columnId;

	@FXML
	private TableColumn<?, ?> ColumTime;

	@FXML
	private TableColumn<?, ?> ColumnAmount;

	@FXML
	private TextField TFname;

	@FXML
	private TextField TFid;

	@FXML
	private Label AccountValue;

	@FXML
	private TextField ValueW;

	@FXML
	private Label AccountValueC;

	@FXML
	private TextField ValueC;

	@FXML
	private TextField NameReg;

	@FXML
	private TextField idReg;

	@FXML
	private TextField ValueReg;

	@FXML
	private Label generalQ;

	public BankGUI(Bank bank) {

		main = bank;
	}

	@FXML
	void registry(ActionEvent event) throws IOException {

		String name = NameReg.getText();
		String id = idReg.getText();
		double value = Double.parseDouble(ValueReg.getText());
		
		boolean registred = main.addUser(name, id, value);
		
		if (registred == true) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle(null);
			alert.setContentText("user successfully registered");
			alert.showAndWait();

			showWindowQueue(null);
			generalQ.setText(main.showQueue());
			
			
		}

	}
	
	public void showWindowQueue(ActionEvent event) throws IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("QueueWindow.fxml"));
		fxmlLoader.setController(this);
		Parent setting = fxmlLoader.load();

		mainPanel.getChildren().clear();
		mainPanel.setCenter(setting);

	} 
	
	public void showTrasactionWindow(ActionEvent event) throws IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TransactionWindow.fxml"));
		fxmlLoader.setController(this);
		Parent setting = fxmlLoader.load();

		mainPanel.getChildren().clear();
		mainPanel.setCenter(setting);

	} 
	
	public void showUserWindow(ActionEvent event) throws IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UserWindow.fxml"));
		fxmlLoader.setController(this);
		Parent setting = fxmlLoader.load();

		mainPanel.getChildren().clear();
		mainPanel.setCenter(setting);

	} 

	@FXML
	void Consignment(ActionEvent event) {

		double value = Double.parseDouble(ValueC.getText());
		main.consignation(value);
	}

	@FXML
	void Withdrawal(ActionEvent event) {

		double value = Double.parseDouble(ValueW.getText());
		 main.retirement(value);
	}

	@FXML
	void back(ActionEvent event) throws IOException {

		loadSettinWindow(null);
	}

	@FXML
	void byAmount(ActionEvent event) {

	}

	@FXML
	void byId(ActionEvent event) {

	}

	@FXML
	void byName(ActionEvent event) {
		main.sortByNombreAtoZ();
	}

	@FXML
	void byTime(ActionEvent event) {

	}

	@FXML
	void Enter(ActionEvent event) throws IOException {

		String name = TFname.getText();
		String id = TFid.getText();

		try {

			main.CheckUser(name, id);
			boolean found = main.searchClient(id);

			if (found == true) {

				showWindowQueue(null);

				main.addToQeueu(NameReg.getText(), idReg.getText());
				generalQ.setText(main.showQueue());

			} else if (found == false) {

				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Registry.fxml"));
				fxmlLoader.setController(this);
				Parent setting = fxmlLoader.load();

				mainPanel.getChildren().clear();
				mainPanel.setCenter(setting);

			}

		} catch (InvalidInformationException iv) {

			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle(null);
			alert.setContentText(iv.message());
			alert.showAndWait();

		}

	}

	@FXML
	void operationButton(ActionEvent event) throws IOException {

		String trasactison = ((RadioButton) Trasactison.getSelectedToggle()).getText();

		if (trasactison.equals("Withdrawal ")) {

			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Withdrawal.fxml"));
			fxmlLoader.setController(this);
			Parent setting = fxmlLoader.load();

			mainPanel.getChildren().clear();
			mainPanel.setCenter(setting);

			AccountValue.setText(String.valueOf(main.getClient().getValueAccount()));
			
		} else if (trasactison.equals("Consignment")) {

			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Consignment.fxml"));
			fxmlLoader.setController(this);
			Parent setting = fxmlLoader.load();

			mainPanel.getChildren().clear();
			mainPanel.setCenter(setting);

			AccountValueC.setText(String.valueOf(main.getClient().getValueAccount()));

		} else if (trasactison.equals("Account cancelation")) {

			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle(null);
			alert.setContentText("Account has been deleted");
			alert.showAndWait();

		} else if (trasactison.equals("Card payment")) {

		}
	}

	@FXML
	void undoButtom(ActionEvent event) {

	}

	public void loadSettinWindow(ActionEvent event) throws IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
		fxmlLoader.setController(this);
		Parent setting = fxmlLoader.load();

		mainPanel.getChildren().clear();
		mainPanel.setCenter(setting);

	}

	@FXML
	void Attend(ActionEvent event) throws IOException {
		
		showWindowQueue(null);
		generalQ.setText(main.showQueue());
	}

	@FXML
	void Registry(ActionEvent event) throws IOException {

		showUserWindow(null);
	}

	@FXML
	void BackToMain(ActionEvent event) throws IOException {

		loadSettinWindow(null);
	}

	@FXML
	void AGeneral(ActionEvent event) throws IOException {

		showTrasactionWindow(null);
	}

	@FXML
	void APreferential(ActionEvent event) throws IOException {

		showTrasactionWindow(null);
	}

	@FXML
	void List(ActionEvent event) throws IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("InformationWindow.fxml"));
		fxmlLoader.setController(this);
		Parent setting = fxmlLoader.load();

		mainPanel.getChildren().clear();
		mainPanel.setCenter(setting);
	}
}
