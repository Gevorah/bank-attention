package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import CustomException.InvalidInformationException;
import datastructure.*;

public class Bank {

	private Client current;
	private Object a;
	private Queue<String, String> normalqueue;
	private Hash<String, Client> clients;
	private ArrayList<Client> clientsToSort;
	private ArrayList<Client> downs;

	public Bank() {
		clients = new Hash<String, Client>();
	}

	public void attend(String cc) {
		current = clients.get(normalqueue.dequeue().getT2());
	}

	private void refresh() {
		current = clients.get(current.getCc());
	}

	public boolean searchClient(String cc) {
		if (clients.get(cc) != null)
			return true;
		else
			return false;
	}

	public void retirement(double value) {
		clients.get(current.getCc()).setValueAccount(current.getValueAccount() - value);
	}

	public void consignation(double value) {
		clients.get(current.getCc()).setValueAccount(current.getValueAccount() + value);
	}

	public void accountCancelation() {
		clients.remove(current.getCc());
		downs.add(current);
	}

	@SuppressWarnings("unchecked")
	public void refreshArrayList() {
		clientsToSort = (ArrayList<Client>) clients.toArrayList();
	}
	
	public void sortByNombreAtoZ() {
		refreshArrayList();
		Client clientTmp;

		for (int i = 0; i < clientsToSort.size(); i++) {
			for (int j = 1; j < (clientsToSort.size() - i); j++) {
				if (clientsToSort.get(j - 1).compareTo(clientsToSort.get(j)) > 0) {
					clientTmp = clientsToSort.get(j - 1);
					clientsToSort.set(j - 1, clientsToSort.get(j));
					clientsToSort.set(j, clientTmp);
				}
			}
		}
	}

	public void CheckUser(String name, String id) throws InvalidInformationException {

		if (!name.trim().equals("") && !id.trim().equals("")) {

		} else {
			throw new InvalidInformationException();

		}
	}

	public boolean addUser(String name, String id, double value) {
		
		boolean creditCard= true;
		boolean debitCard=false;
		LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");  
        String formatDateTime = now.format(format);  
		String account = String.valueOf(Math.floor(Math.random()*(999999-100000+1)+100000));
		
		int card = (int)Math.floor(Math.random()*(2-1+1)+1);
		
		if (card==1) {
			creditCard=true;
			debitCard=false;
		}else if(card==2) {
			creditCard=false;
			debitCard=true;
		}
		
		Client tmp= new Client ( name,  id,  account,  creditCard,  debitCard,  value, "31/12/2020", formatDateTime );
		clients.put(id, tmp);
		
		return true;
	}

	public Client getClient() {
		return current;
	};
	
	
	public String showQueue () {
		
		if (normalqueue==null) {
		return "empty queue";
		}
		else {
		return normalqueue.print();
		}
		
	}
	
	public void addToQeueu (String name, String id) {
		
		normalqueue.enqueue(name, id);
	}
}
