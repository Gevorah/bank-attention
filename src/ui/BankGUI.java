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

	public BankGUI(Bank bank) {

		main = bank;
	}

	
	@FXML
	void back(ActionEvent event) throws IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TransactionWindow.fxml"));
		fxmlLoader.setController(this);
		Parent setting = fxmlLoader.load();

		mainPanel.getChildren().clear();
		mainPanel.setCenter(setting);
	}

	@FXML
	void byAmount(ActionEvent event) {

	}

	@FXML
	void byId(ActionEvent event) {

	}

	@FXML
	void byName(ActionEvent event) {

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

			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TransactionWindow.fxml"));
			fxmlLoader.setController(this);
			Parent setting = fxmlLoader.load();

			mainPanel.getChildren().clear();
			mainPanel.setCenter(setting);
			
			nameUser.setText(name);
			identificationUser.setText(id);
			

		} catch (InvalidInformationException iv) {

			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle(null);
			alert.setContentText(iv.message());
			alert.showAndWait();

		}

	}

	@FXML
	void operationButton(ActionEvent event) {

		String trasactison = ((RadioButton) Trasactison.getSelectedToggle()).getText();

		if (trasactison.equals("Withdrawal ")) {

			System.out.println("hello");
		} else if (trasactison.equals("Consignment")) {

			System.out.println("2");
		} else if (trasactison.equals("Account cancelation")) {

			System.out.println("3");
		} else if (trasactison.equals("Card payment")) {

			System.out.println("4");
		}
	}

	@FXML
	void undoButtom(ActionEvent event) {

	}

	public void loadSettinWindow(ActionEvent event) throws IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UserWindow.fxml"));
		fxmlLoader.setController(this);
		Parent setting = fxmlLoader.load();

		mainPanel.getChildren().clear();
		mainPanel.setCenter(setting);

	}
}
