package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Bank;


public class Main extends Application {

	private BankGUI bankGUI;
	private static  Bank bank;

	public Main() {

		bank= new Bank();
		bankGUI = new BankGUI(bank);

	}

	public static void main(String[] args) {

		launch(args);
		
		}

	

	@Override
	public void start(Stage stage) throws Exception {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("MainPane.fxml"));

		loader.setController(bankGUI);
		Parent root = loader.load();

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Main Window");
		bankGUI.loadSettinWindow(null);
		stage.show();
		
	}


}
