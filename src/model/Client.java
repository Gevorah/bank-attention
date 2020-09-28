package model;

public class Client implements Comparable<Client> {

	private String name;
	private String cc;
	private String account;
	private boolean creditCard;
	private boolean debitCard;
	private double valueAccount;
	private String creditCardPayDate;
	private String startDate;
	
	public Client(String name, String cc) {
		this.name=name;
		this.cc=cc;
	}
	public Client(String name, String cc, String account, boolean creditCard, boolean debitCard, double valueAccount,
			String creditCardPayDate, String startDate) {
		super();
		this.name = name;
		this.cc = cc;
		this.account = account;
		this.creditCard = creditCard;
		this.debitCard = debitCard;
		this.valueAccount = valueAccount;
		this.creditCardPayDate = creditCardPayDate;
		this.startDate = startDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public boolean isCreditCard() {
		return creditCard;
	}

	public void setCreditCard(boolean creditCard) {
		this.creditCard = creditCard;
	}

	public boolean isDebitCard() {
		return debitCard;
	}

	public void setDebitCard(boolean debitCard) {
		this.debitCard = debitCard;
	}

	public double getValueAccount() {
		return valueAccount;
	}

	public void setValueAccount(double valueAccount) {
		this.valueAccount = valueAccount;
	}

	public String getCreditCardPayDate() {
		return creditCardPayDate;
	}

	public void setCreditCardPayDate(String creditCardPayDate) {
		this.creditCardPayDate = creditCardPayDate;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	@Override
	public int compareTo(Client o) {
		if (this.name.equals(o.getName()))		
			return 0;
		else if (this.name.compareTo(o.getName())>0) 
			return 1;
		else 
			return -1;
	}

}
