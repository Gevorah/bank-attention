package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import CustomException.InvalidInformationException;
import CustomException.InvalidNegativeValueException;
import datastructure.*;

public class Bank {

	private Client current;
	private Queue<String, String> normalqueue;
	private MinHeap<Client> priorityqueue;
	private Hash<String, Client> clients;
	private ArrayList<Client> clientsToSort;
	private ArrayList<Client> downs;

	public Bank() {
		normalqueue = new Queue<String, String>();
		priorityqueue = new MinHeap<Client>(100);
		clients = new Hash<String, Client>();
		clientsToSort = new ArrayList<Client>();
		downs = new ArrayList<Client>();
	}

	public void attendGeneral() throws NullPointerException {
		current = clients.get(normalqueue.dequeue().getT2());
		if(current==null) throw new NullPointerException("Don't have clients");
	}

	public void attendPriority() {
		current = clients.get(priorityqueue.extractMin().getCc());
	}

	public void refresh() {
		current = clients.get(current.getCc());
	}

	public boolean searchClient(String cc) {
		if (clients.get(cc) != null)
			return true;
		else
			return false;
	}

	public void retirement(double value) throws InvalidNegativeValueException {
		if (value + (int) Math.abs(value) == 0)
			throw new InvalidNegativeValueException();
		else {
			clients.get(current.getCc()).setValueAccount(current.getValueAccount() - value);
		}
	}

	public void consignation(double value) {
		clients.get(current.getCc()).setValueAccount(current.getValueAccount() + value);
	}

	public void accountCancelation() {
		clients.remove(current.getCc());
		refreshArrayList();
		downs.add(current);
	}

	public double payCard() {
		double value = Math.floor(Math.random() * (999999 - 100000 + 1) + 100000);
		clients.get(current.getCc()).setValueAccount(current.getValueAccount() - value);
		return clients.get(current.getCc()).getValueAccount();
	}

	public void refreshArrayList() {
		clientsToSort = clients.toArrayList();
	}

	public void CheckUser(String name, String id) throws InvalidInformationException {

		if (!name.trim().equals("") && !id.trim().equals("")) {

		} else {
			throw new InvalidInformationException();

		}
	}

	public boolean addUser(String name, String id, double value) {

		boolean creditCard = true;
		boolean debitCard = false;
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String formatDateTime = now.format(format);
		String account = String.valueOf(Math.floor(Math.random() * (999999 - 100000 + 1) + 100000));

		int card = (int) Math.floor(Math.random() * (2 - 1 + 1) + 1);

		if (card == 1) {
			creditCard = true;
			debitCard = false;
		} else if (card == 2) {
			creditCard = false;
			debitCard = true;
		}

		Client tmp = new Client(name, id, account, creditCard, debitCard, value, "31-12-2020", formatDateTime);
		clients.put(id, tmp);
		refreshArrayList();
		return true;
	}

	public Client getClient() {
		return current;
	}

	public ArrayList<Client> getList() {
		return clientsToSort;
	}

	public String showNormalQueue() {
		if (normalqueue == null)
			return "empty queue";
		else
			return normalqueue.print();
	}

	public String showPriorityQueue() {
		if (priorityqueue.size() == 0)
			return "empty queue";
		else
			return priorityqueue.print();
	}

	public void addToNormalQeueu(String name, String id) {
		normalqueue.enqueue(name, id);
	}

	public void addToPriorityQueue(String name, String id) {
		priorityqueue.insert(new Client(name, id));
	}

	// SORTS
	public void sortByNombreAtoZ() {
		refreshArrayList();
		Client clientTmp;
		for (int i = 0; i < clientsToSort.size(); i++) {
			for (int j = 1; j < (clientsToSort.size() - i); j++) {
				if (clientsToSort.get(j - 1).getName().compareTo(clientsToSort.get(j).getName()) > 0) {
					clientTmp = clientsToSort.get(j - 1);
					clientsToSort.set(j - 1, clientsToSort.get(j));
					clientsToSort.set(j, clientTmp);
				}
			}
		}
	}

	public void sortByStartDate() {
		refreshArrayList();
		Client[] b = new Client[clientsToSort.size()];
		for (int i = 0; i < b.length; i++)
			b[i] = clientsToSort.get(i);
		mergeSort(b);
		clientsToSort.clear();
		for (Client tmp : b)
			clientsToSort.add(tmp);
	}

	public static Client[] mergeSort(Client[] arr) {
		int lgt = arr.length;
		if (lgt < 2)
			return arr;
		int mid = arr.length / 2;
		Client[] l = new Client[mid];
		Client[] r = new Client[lgt - mid];
		for (int i = 0; i < mid; i++) {
			l[i] = arr[i];
		}
		for (int j = mid; j < lgt; j++) {
			r[j - mid] = arr[j];
		}
		Client[] lr = new Client[lgt];
		l = mergeSort(l);
		r = mergeSort(r);
		lr = merge(arr, l, r, mid, lgt - mid);
		return lr;
	}

	private static Client[] merge(Client[] lr, Client[] l, Client[] r, int lf, int rg) { // 3n+2
		int i = 0, j = 0, k = 0; // 3
		while (i < lf && j < rg) {
			LocalDateTime d1, d2;// 2
			String[] date1 = l[i].getStartDate().split("-");// 3
			String[] date2 = r[j].getStartDate().split("-");// 3
			d1 = LocalDateTime.of(Integer.parseInt(date1[2]), Integer.parseInt(date1[1]), Integer.parseInt(date1[0]), 0,
					0);// 1
			d2 = LocalDateTime.of(Integer.parseInt(date2[2]), Integer.parseInt(date2[1]), Integer.parseInt(date2[0]), 0,
					0);// 1
			if (d1.compareTo(d2) <= 0)
				lr[k++] = l[i++];
			else
				lr[k++] = r[j++];
		}
		while (i < lf)
			lr[k++] = l[i++];
		while (j < rg)
			lr[k++] = r[j++];
		return lr;// 1
	}

	public void sortByValue() {
		refreshArrayList();
		Client[] b = new Client[clientsToSort.size()];
		for (int i = 0; i < b.length; i++)
			b[i] = clientsToSort.get(i);
		quickSort(b, 0, b.length - 1);
		clientsToSort.clear();
		for (Client tmp : b)
			clientsToSort.add(tmp);
	}

	private void quickSort(Client[] arr, int low, int high) {
		if (low < high) {
			int pi = partition(arr, low, high);
			quickSort(arr, low, pi - 1);
			quickSort(arr, pi + 1, high);
		}
	}

	private int partition(Client[] arr, int low, int high) {
		Client pivot = arr[high];
		int i = (low - 1);
		for (int j = low; j < high; j++) {
			if (arr[j].getValueAccount() < pivot.getValueAccount()) {
				Client temp = arr[++i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}
		Client temp = arr[++i];
		arr[i] = arr[high];
		arr[high] = temp;
		return i;
	}

	public void sortByCC() {
		refreshArrayList();
		MinHeap<Client> a = new MinHeap<Client>(100);
		for (Client tmp : clientsToSort)
			a.insert(tmp);
		a.minHeap();
		clientsToSort.clear();
		int size = a.size();
		for (int i = 0; i < size; i++) {
			clientsToSort.add(a.extractMin());
		}
	}

	public void reverseAction(double con, double whit) {

		clients.get(current.getCc()).setValueAccount(current.getValueAccount() - con + whit);

	}

	public String showDowns() {

		String info = "";

		for (int i = 0; i < downs.size(); i++) {
			if (downs.get(i) == null) {

				info = "No one has withdrawn from the bank";
			} else {
				info += downs.get(i).getName() + " " + downs.get(i).getCc() + "\n";

			}
		}

		return info;

	}

	public void init() {
		addUser("sebastian", "1193269834", 1200000);
		addUser("jhon", "115201324", 6580321);
		addUser("erika", "66850123", 1582255);
		addUser("raquel", "2920456", 1254256448);
		addUser("cristian", "112305478", 6900000);
		addUser("julian", "11025846", 12651);
		Client tmp= new Client ("jose marmolejo", "119654521", "1651516", true, false, 15256262,
				"31-12-2020","12-09-2020" );
		downs.add(tmp);
	}

}
