package de.thi.formrec.model;

import org.javamoney.moneta.Money;

public class Item
{
	private String description;
	private Double quantity;
	private Money totalPrice;

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public Double getQuantity()
	{
		return quantity;
	}

	public void setQuantity(Double quantity)
	{
		this.quantity = quantity;
	}

	public Money getTotalPrice()
	{
		return totalPrice;
	}

	public void setTotalPrice(Money totalPrice)
	{
		this.totalPrice = totalPrice;
	}
}
