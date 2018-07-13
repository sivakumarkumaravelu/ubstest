package com.exercise.datareader.data;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
import com.exercise.file.datareader.data.DataProcessor;
import com.exercise.file.util.CurrencyConverter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class TestUBSDataProcessor
{
	
	@Autowired
	Reader reader;
	@Autowired
	DataProcessor processor;

	@Configuration
	static class ContextConfiguration {
		@Bean
		public Reader getReader() throws ReaderException {
			Reader reader = new DelimitedFileReader("/src/main/resources/FILE.DAT","\t");
			return reader;
		}
		
		@Bean
		public DataProcessor getProcessor() throws ReaderException {
			DataProcessor processor = new DataProcessor();
			return processor;
		}
	}
	
		
	@SuppressWarnings("unchecked")
	@Test
	public void testProcess() throws Exception
	{
		List<Trade> input = (List<Trade>) reader.read();
		Map<Object, BigDecimal> result = (Map<Object, BigDecimal>) processor.process(input);
		
		List<String> key = Arrays.asList("IRL", "Aa");
		BigDecimal actualValue = result.get(key);
		
		BigDecimal expectedValue = (CurrencyConverter.convertToEuro(new BigDecimal("572106536.9"), "CHF").add(CurrencyConverter.convertToEuro(new BigDecimal("607295781.3"), "GBP"))).divide(new BigDecimal(2));
		
		assertTrue(actualValue.equals(expectedValue));
	}
}
