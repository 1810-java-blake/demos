package com.revature.model;

public class Account {
	private int id;
	private double balance;
	private int owner;
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Account(int id, double balance, int owner) {
		super();
		this.id = id;
		this.balance = balance;
		this.owner = owner;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public int getOwner() {
		return owner;
	}
	public void setOwner(int owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", balance=" + balance + ", owner=" + owner + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + id;
		result = prime * result + owner;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
			return false;
		if (id != other.id)
			return false;
		if (owner != other.owner)
			return false;
		return true;
	}
	
	
}
