package model;

import java.util.ArrayList;

import CustomException.InvalidInformationException;


public class Bank {

	Client tmp;
	Hash<String, Client> clients;

	Client[] clientsTmp = new Client[1000];

	public Bank() {
		clients = new Hash<String,Client>();
		tmp = null;
	}

	//private void client(String key) {
		//tmp = clients.getTable()[clients.search(key)];
	//}

	public void retirement(double value) {
		double current = tmp.getValueAccount();
		tmp.setValueAccount(current - value);
	}

	public void consignation(double value) {
		tmp.setValueAccount(tmp.getValueAccount() + value);
	}

	public void accountCancelation() {

	}

	public void sortByNombreAtoZ() {

		Client clientTmp;

		for (int i = 0; i < clientsTmp.length; i++) {
			for (int j = 1; j < (clientsTmp.length - i); j++) {

				if (clientsTmp[j - 1].compareTo(clientsTmp[j]) > 0) {
					clientTmp = clientsTmp[j - 1];
					clientsTmp[j - 1] = clientsTmp[j];
					clientsTmp[j] = clientTmp;

				}

			}
		}
	}
	
	public void CheckUser (String name, String id) throws InvalidInformationException {
		
		if (!name.trim().equals("") && !id.trim().equals("")) {
			
		} else {
			throw new InvalidInformationException();

		}
	}

}
