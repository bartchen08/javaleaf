package com.example.helloworld.customer;

public class Customer {
	private String Name;
	private String City;
	private String Country;
	
	public Customer(String name, String city, String country){
		Name = name;
		City = city;
		Country = country;
	}
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getCountry() {
		return Country;
	}
	public void setCountry(String country) {
		Country = country;
	}
}
