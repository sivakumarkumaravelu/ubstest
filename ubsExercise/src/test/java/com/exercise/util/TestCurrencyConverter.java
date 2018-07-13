package com.exercise.util;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Test;

import com.exercise.file.util.CurrencyConverter;

public class TestCurrencyConverter 
{
	@Test
	public void testConvertToEuro()
	{
		BigDecimal actualResult   = CurrencyConverter.convertToEuro(new BigDecimal(1.0), "USD").setScale(6, BigDecimal.ROUND_DOWN);
		BigDecimal expectedResult = (new BigDecimal("1.0").divide(new BigDecimal("1.35"),5, RoundingMode.HALF_UP)).multiply(new BigDecimal("1.0"));
				
		assertTrue(actualResult.equals(expectedResult));
		
		actualResult   = CurrencyConverter.convertToEuro(new BigDecimal(1.0), "GBP").setScale(5, BigDecimal.ROUND_DOWN);
		expectedResult = (new BigDecimal("1.654").multiply(new BigDecimal("1").divide(new BigDecimal("1.35"),5, RoundingMode.HALF_UP)).multiply(new BigDecimal("1.0"))).setScale(5, BigDecimal.ROUND_DOWN);
				
		assertTrue(actualResult.equals(expectedResult));
		
		actualResult   = CurrencyConverter.convertToEuro(new BigDecimal("1.0"), "CHF").setScale(5, BigDecimal.ROUND_DOWN);
		expectedResult = (new BigDecimal("1.1").multiply(new BigDecimal("1").divide(new BigDecimal("1.35"),5, RoundingMode.HALF_UP)).multiply(new BigDecimal("1.0"))).setScale(5, BigDecimal.ROUND_DOWN);
		
		assertTrue(actualResult.equals(expectedResult));
	}
}
