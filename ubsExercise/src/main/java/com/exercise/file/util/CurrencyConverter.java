package com.exercise.file.util;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Utility class used for currency conversion
 * 
 * @author Sivakumar Kumaravelu
 *
 */

public class CurrencyConverter
{
	
	private static final Map<String, BigDecimal> currencyMap = new HashMap<String, BigDecimal>();

	static
	{
		currencyMap.put("USD_EUR", new BigDecimal(1/1.35));
		currencyMap.put("GBP_USD", new BigDecimal(1.654));
		currencyMap.put("CHF_USD", new BigDecimal(1.1));
		currencyMap.put("GBP_EUR", currencyMap.get("GBP_USD").multiply(currencyMap.get("USD_EUR")));
		currencyMap.put("CHF_EUR", currencyMap.get("CHF_USD").multiply(currencyMap.get("USD_EUR")));
	}

	public static BigDecimal convertToEuro(BigDecimal amount, String currency)
	{
		switch (currency) {
		case "EUR":
			return amount;

		case "USD":
			return currencyMap.get("USD_EUR").multiply(amount);

		case "GBP":
			return currencyMap.get("GBP_EUR").multiply(amount);

		case "CHF":
			return currencyMap.get("CHF_EUR").multiply(amount);
		default:
			break;
		}
		return amount;
	}
}
