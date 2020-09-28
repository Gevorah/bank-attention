package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import CustomException.InvalidInformationException;
import datastructure.*;

public class Bank {

	private Client current;
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
		if (normalqueue==null) return "empty queue";
		else return normalqueue.print();
	}
	
	public void addToQeueu (String name, String id) {
		normalqueue.enqueue(name, id);
	}
	
	
	//SORTS
	public void sortByNombreAtoZ() {
		refreshArrayList();
		Client clientTmp;
		for(int i = 0; i < clientsToSort.size(); i++) {
			for(int j = 1; j < (clientsToSort.size() - i); j++) {
				if(clientsToSort.get(j - 1).compareTo(clientsToSort.get(j)) > 0) {
					clientTmp = clientsToSort.get(j - 1);
					clientsToSort.set(j - 1, clientsToSort.get(j));
					clientsToSort.set(j, clientTmp);
				}
			}
		}
	}
	
	public void sortByCC() {
		refreshArrayList();
		Client[] b = new Client[clientsToSort.size()];
		for(int i=0;i<b.length;i++) b[i] = clientsToSort.get(i);
		mergeSort(b);
		clientsToSort.clear();
		for(Client tmp:b) clientsToSort.add(tmp);
	}
	
	public static Client[] mergeSort(Client[] arr) {
		int lgt = arr.length;
		if(lgt<2)return arr;
		int mid = arr.length/2;
		Client[] l = new Client[mid];
		Client[] r = new Client[lgt-mid];
		for(int i=0;i<mid;i++){
			l[i] = arr[i];
		}
		for(int j=mid;j<lgt;j++) {
			r[j-mid] = arr[j];
		}
		Client[] lr = new Client[lgt];
		l = mergeSort(l);
		r = mergeSort(r);
		lr = merge(arr,l,r,mid,lgt-mid);
		return lr;
	}
	
	private static Client[] merge(Client[] lr, Client[] l, Client[] r, int lf, int rg) {
		int i=0, j=0, k=0;
		while(i<lf&&j<rg) {
			if(l[i].getCc().compareTo(r[j].getCc())<=0) lr[k++] = l[i++];
			else lr[k++] = r[j++];
		}
		while(i < lf) lr[k++] = l[i++];
	    while(j < rg) lr[k++] = r[j++];
	    return lr;
	}
	
	
	public static void main(String[] args) {
		ArrayList<Client> a = new ArrayList<Client>();
		a.add(new Client("1", "B"));
		a.add(new Client("2", "F"));
		a.add(new Client("3", "D"));
		a.add(new Client("4", "A"));
		a.add(new Client("5", "G"));
		a.add(new Client("6", "C"));
		a.add(new Client("7", "B"));
		a.add(new Client("8", "E"));
		a.add(new Client("9", "H"));
		Client[] b = new Client[a.size()];
		for(int i=0;i<b.length;i++) b[i] = a.get(i);
		mergeSort(b);
		a.clear();
		for(Client tmp:b) a.add(tmp);
		System.out.println();
	}
}
