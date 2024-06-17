/******************************************************************************
Name: Jordy Parker
Date: 16.06.2024
Module: 2
Lab: 3
Purpose: House class definition
*******************************************************************************/
package labs;

public class House
{
	private String address;
	private int bedrooms;
	private int bathrooms;
	private double price;
	private int squareFeet;

	/*
		CONSTRUCTORS
	*/

	public House(String address, int bedrooms, int bathrooms, double price, int squareFeet)
	{
		this.address = address;
		this.bedrooms = bedrooms;
		this.bathrooms = bathrooms;
		this.price = price;
		this.squareFeet = squareFeet;
	}
	
	public House()
	{
		this("", 0, 0, 0, 0);
	}

	/*
		ACCESSOR METHODS
	*/

	public String getAddress()
	{
		return address;
	}
	
	public int getBedrooms()
	{
		return bedrooms;
	}

	public int getBathrooms()
	{
		return bathrooms;
	}

	public double getPrice()
	{
		return price;
	}

	public int getSquareFeet()
	{
		return squareFeet;
	}

	/*
		MUTATOR METHODS
	*/

	public void setAddress(String address)
	{
		this.address = address;
	}

	public void setBedrooms(int bedrooms)
	{
		this.bedrooms = bedrooms;
	}

	public void setBathrooms(int bathrooms)
	{
		this.bathrooms = bathrooms;
	}

	public void setPrice(double price)
	{
		this.price = price;
	}

	public void setSquareFeet(int squareFeet)
	{
		this.squareFeet = squareFeet;
	}

	/*
		PUBLIC METHODS
	*/

	public double calculatePricePerSqFt()
	{
		return price / squareFeet;
	}

	@Override
	public String toString()
	{
		return address;
	}
}