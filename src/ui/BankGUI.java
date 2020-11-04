package ui;

import java.io.IOException;
import CustomException.InvalidInformationException;
import CustomException.InvalidNegativeValueException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import model.Bank;
import model.Client;

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
	private TableView<Client> information;

	@FXML
	private TableColumn<Client, String> ColumnName;

	@FXML
	private TableColumn<Client, String> columnId;

	@FXML
	private TableColumn<Client, String> ColumTime;

	@FXML
	private TableColumn<Client, Double> ColumnAmount;

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
	private Label idReg;

	@FXML
	private Label nameReg;

	@FXML
	private TextField ValueReg;

	@FXML
	private Label generalQ;

	@FXML
	private Label preferenceQ;

	@FXML
	private RadioButton Qpreferencial;

	@FXML
	private ToggleGroup selectQueue;

	@FXML
	private RadioButton Qgeneral;

	public BankGUI(Bank bank) {

		main = bank;
		main.init();
	}

	@FXML
	void registry(ActionEvent event) throws IOException {

		String name = nameReg.getText();
		String id = idReg.getText();
		double value = Double.parseDouble(ValueReg.getText());

		boolean registred = main.addUser(name, id, value);

		if (registred == true) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle(null);
			alert.setContentText("user successfully registered");
			alert.showAndWait();

			String queue = ((RadioButton) selectQueue.getSelectedToggle()).getText();

			if (queue.equals("Preferencial")) {
				System.out.println(name);
				main.addToPriorityQueue(name, id);
				showWindowQueue(null);

			} else if (queue.equals("General")) {
				System.out.println(name);
				System.out.println(id);
				main.addToNormalQeueu(name, id);
				showWindowQueue(null);

			}

		}

	}

	public void showWindowQueue(ActionEvent event) throws IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("QueueWindow.fxml"));
		fxmlLoader.setController(this);
		Parent setting = fxmlLoader.load();

		mainPanel.getChildren().clear();
		mainPanel.setCenter(setting);

		preferenceQ.setText(main.showPriorityQueue());
		generalQ.setText(main.showNormalQueue());

	}

	public void showTrasactionWindow(ActionEvent event) throws IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TransactionWindow.fxml"));
		fxmlLoader.setController(this);
		Parent setting = fxmlLoader.load();

		mainPanel.getChildren().clear();
		mainPanel.setCenter(setting);

		nameUser.setText(main.getClient().getName());
		identificationUser.setText(main.getClient().getCc());
	}

	public void showUserWindow(ActionEvent event) throws IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UserWindow.fxml"));
		fxmlLoader.setController(this);
		Parent setting = fxmlLoader.load();

		mainPanel.getChildren().clear();
		mainPanel.setCenter(setting);

	}

	public void showRegistry(ActionEvent event) throws IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Registry.fxml"));
		fxmlLoader.setController(this);
		Parent setting = fxmlLoader.load();

		mainPanel.getChildren().clear();
		mainPanel.setCenter(setting);

	}

	@FXML
	void Consignment(ActionEvent event) throws IOException {

		double value = Double.parseDouble(ValueC.getText());
		main.consignation(value);
		main.refresh();
		showTrasactionWindow(null);
	}

	@FXML
	void Withdrawal(ActionEvent event) throws IOException, InvalidNegativeValueException {
		try {
			double value = Double.parseDouble(ValueW.getText());
			main.retirement(value);
			main.refresh();
			showTrasactionWindow(null);
		} catch (InvalidNegativeValueException e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle(null);
			alert.setContentText(e.message());
			alert.showAndWait();
		}

	}

	@FXML
	void back(ActionEvent event) throws IOException {

		loadSettinWindow(null);
	}

	@FXML
	void byAmount(ActionEvent event) {
		main.sortByValue();
		initializeScores();
	}

	@FXML
	void byId(ActionEvent event) {
		main.sortByCC();
		initializeScores();
	}

	@FXML
	void byName(ActionEvent event) {
		main.sortByNombreAtoZ();
		initializeScores();
	}

	@FXML
	void byTime(ActionEvent event) {
		main.sortByStartDate();
		initializeScores();
	}

	@FXML
	void Enter(ActionEvent event) throws IOException {

		try {
			String name = TFname.getText();
			String id = TFid.getText();
			String queue = ((RadioButton) selectQueue.getSelectedToggle()).getText();
			main.CheckUser(name, id);
			boolean found = main.searchClient(id);

			if (found == true) {

				if (queue.equals("Preferencial")) {

					main.addToPriorityQueue(name, id);
					showWindowQueue(null);

				} else if (queue.equals("General")) {
					main.addToNormalQeueu(name, id);
					showWindowQueue(null);
				}

			} else if (found == false) {

				showRegistry(null);

				idReg.setText(id);
				nameReg.setText(name);

			}

		} catch (InvalidInformationException e) {

			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle(null);
			alert.setContentText(e.message());
			alert.showAndWait();

		} catch (NullPointerException e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle(null);
			alert.setContentText("You have not selected a valid option");
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

			main.accountCancelation();
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle(null);
			alert.setContentText("Account has been deleted");
			alert.showAndWait();
			showWindowQueue(null);

		} else if (trasactison.equals("Card payment")) {

			double value = main.payCard();
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle(null);
			alert.setContentText(String.valueOf(value));
			alert.showAndWait();
			showTrasactionWindow(null);
		}
	}

	@FXML
	void undoButtom(ActionEvent event) {

		double con = 0;
		double whit = 0;
		if (ValueC == null) {
			con = 0;
		} else {
			con = Double.parseDouble(ValueC.getText());
		}

		if (ValueW == null) {
			whit = 0;
		} else {
			whit = Double.parseDouble(ValueW.getText());
		}

		main.reverseAction(con, whit);
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle(null);
		alert.setContentText(String.valueOf("reversed action"));
		alert.showAndWait();

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
		System.out.println("metodoattent");
		showWindowQueue(null);
		// generalQ.setText(main.showNormalQueue());
	}

	@FXML
	void Registry(ActionEvent event) throws IOException {

		showUserWindow(null);
	}

	@FXML
	void BackToMain(ActionEvent event) throws IOException {

		showWindowQueue(null);
	}

	@FXML
	void AGeneral(ActionEvent event) throws IOException {

		main.attendGeneral();
		showTrasactionWindow(null);

	}

	@FXML
	void APreferential(ActionEvent event) throws IOException {

		main.attendPriority();
		showTrasactionWindow(null);
	}

	@FXML
	void List(ActionEvent event) throws IOException {

		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle(null);
		alert.setContentText("retired people \n" + main.showDowns());
		alert.showAndWait();
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("InformationWindow.fxml"));
		fxmlLoader.setController(this);
		Parent setting = fxmlLoader.load();
		mainPanel.getChildren().clear();
		mainPanel.setCenter(setting);

	}

	public void initialize() throws Exception {
		if (information != null) {
			initializeScores();
		}
		
	

	}

	public void initializeScores() {
		ObservableList<Client> observableList;
		observableList = FXCollections.observableArrayList(main.getList());

		information.setItems(observableList);
		ColumnName.setCellValueFactory(new PropertyValueFactory<Client, String>("name"));
		columnId.setCellValueFactory(new PropertyValueFactory<Client, String>("cc"));
		ColumTime.setCellValueFactory(new PropertyValueFactory<Client, String>("startDate"));
		ColumnAmount.setCellValueFactory(new PropertyValueFactory<Client, Double>("valueAccount"));

	}

}
