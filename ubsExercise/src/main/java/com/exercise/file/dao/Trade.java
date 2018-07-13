package com.exercise.file.dao;

import java.math.BigDecimal;
import com.exercise.file.util.CurrencyConverter;

/**
 * POJO class that maps the data with the delimited file.
 * Though it ties the domain object, it is very useful in aggregation operation with streams in Java 8
 *
 * @author Sivakumar Kumaravelu
 *
 */
public class Trade {
	private String companyCode;
	private int account;
	private String city;
	private String country;
	private String creditRating;
	private String currency;
	private BigDecimal amount;
	private BigDecimal amountInEuro;

	public Trade(String city, String country, String creditRating, String currency,	BigDecimal amount) {
		super();
		this.city = city;
		this.country = country;
		this.creditRating = creditRating;
		this.currency = currency;
		this.amount = amount;
		setAmountInEuro();
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public int getAccount() {
		return account;
	}
	public void setAccount(int account) {
		this.account = account;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCreditRating() {
		return creditRating;
	}
	public void setCreditRating(String creditRating) {
		this.creditRating = creditRating;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getAmountInEuro() {
		return amountInEuro;
	}
	public void setAmountInEuro() {
		this.amountInEuro = CurrencyConverter.convertToEuro(this.amount, this.currency);
	}

	//Just for sample, use a better equals and hashcode based on primary attribute
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof Trade))
			return false;
		if (obj == this)
			return true;
		return (this.country == ((Trade) obj).getCountry() && this.creditRating == ((Trade) obj).getCreditRating());
	}

	@Override
	public int hashCode()
	{
		return this.country.hashCode() + this.creditRating.hashCode();
	}
}
