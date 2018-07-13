package com.exercise.datareader;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.exercise.file.dao.Trade;
import com.exercise.file.datareader.DelimitedFileReader;
import com.exercise.file.datareader.Reader;
import com.exercise.file.datareader.ReaderException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class TestDelimitedFileReader
{
	@Autowired
	Reader reader;
	List<Trade> input;

	//Injecting the beans through configuration, look for a better option.
	@Configuration
	static class ContextConfiguration {
		@Bean
		public Reader getReader() throws ReaderException {
			Reader reader = new DelimitedFileReader("/src/main/resources/FILE.DAT","\t");
			return reader;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Before
	public void setUp() throws Exception 
	{
		this.input = (List<Trade>) reader.read();
	}

	@Test
	public void testDelimiterFileReaderInit() {
		assertEquals(
				"class com.exercise.file.datareader.DelimitedFileReader",
				this.reader.getClass().toString());
	}

	@Test
	public void testRead_ByCountry() throws Exception
	{
		Trade trade = input.get(17);

		//Test with country
		assertTrue("Moscow".equals(trade.getCity()));
		assertTrue("RUS".equals(trade.getCountry()));
		assertTrue("CHF".equals(trade.getCurrency()));

		trade = input.get(11);
		
		assertTrue("Copenhagen".equals(trade.getCity()));
		assertTrue("DK".equals(trade.getCountry()));
		assertTrue("CHF".equals(trade.getCurrency()));
	}
	
	@Test
	public void testRead_ByCity() throws Exception
	{
		Trade trade = input.get(2);

		//One test with no country only city
		assertTrue("London".equals(trade.getCity()));
		assertTrue("A".equals(trade.getCreditRating()));
		assertTrue("GBP".equals(trade.getCurrency()));
		assertTrue(new BigDecimal("456.85014").compareTo(trade.getAmount())==0);
	}
	
	@Test
	public void testRead_ByNoOfItems() throws Exception
	{
		assertTrue(input.size() == 19);
	}

}
