package de.thi.formrec.model;

import org.javamoney.moneta.Money;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Receipt {
	private List<Item> items = new ArrayList<>();
	private String merchant;
	private Money subTotal;
	private Money totalTax;
	private Money total;
	private LocalDate date;

	public Money getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(Money subTotal) {
		this.subTotal = subTotal;
	}

	public Money getTotalTax() {
		return totalTax;
	}

	public void setTotalTax(Money totalTax) {
		this.totalTax = totalTax;
	}

	public Money getTotal() {
		return total;
	}

	public void setTotal(Money total) {
		this.total = total;
	}

	public void addItem(Item item) {
		items.add(item);
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public List<Item> getItems() {
		return items;
	}

	public String getMerchant() {
		return merchant;
	}

	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalDate getDate() {
		return date;
	}
}
