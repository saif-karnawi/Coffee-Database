package application;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Coffee {
	
	private final SimpleStringProperty coffeeName;
	private final SimpleDoubleProperty coffeePrice;
	private double price;
	
	public Coffee(String coffeeName, double coffeePrice) {
		super();
		this.coffeeName = new SimpleStringProperty(coffeeName);
		price = coffeePrice;
		this.coffeePrice = new SimpleDoubleProperty(coffeePrice);
	}

	public StringProperty coffeeNameProperty()
	{
		return coffeeName;
	}
	
	public DoubleProperty coffeePriceProperty()
	{
		return coffeePrice;
	}
	
	public double getPrice()
	{
		return price;
	}
	
	

}
