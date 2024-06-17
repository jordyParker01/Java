/******************************************************************************
Name: Jordy Parker
Date: 16.06.2024
Module: 2
Lab: 2
Purpose: Car class definition
*******************************************************************************/
package labs;

public class Car
{
	private String make;
	private String model;
	private int year;
	private double mileage;

	/*
		CONSTRUCTORS
	*/

	public Car()
	{
		this("", "", 0, 0);
	}

	public Car(String make, String model, int year, double mileage) throws IllegalArgumentException
	{
		this.make = make;
		this.model = model;
		this.year = year;
		this.mileage = mileage;
	}

	/*
		ACCESSOR METHODS
	*/

	public String getMake()
	{
		return make;
	}

	public String getModel()
	{
		return model;
	}

	public int getYear()
	{
		return year;
	}

	public double getMileage()
	{
		return mileage;
	}

	/*
		MUTATOR METHODS
	*/

	public void setMake(String make)
	{
		this.make = make;
	}
	
	public void setModel(String model)
	{
		this.model = model;
	}

	public void setYear(int year) throws IllegalArgumentException
	{
		if(year < 1900) throw new IllegalArgumentException("Year too early.");
		else if(year > 2025) throw new IllegalArgumentException("Year too late");
		else this.year = year;
	}

	public void setMileage(int mileage) throws IllegalArgumentException
	{
		if(mileage < 0) throw new IllegalArgumentException("Mileage must be non-negative.");
		else this.mileage = mileage;
	}

	/*
		INSTANCE METHODS
	*/

	public void drive(double miles)
	{
		mileage += miles;
	}

	@Override
	public String toString()
	{
		return year + " " + make + " " + model;
	}
}