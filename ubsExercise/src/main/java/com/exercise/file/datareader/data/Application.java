package com.exercise.file.datareader.data;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.exercise.file.dao.Trade;
import com.exercise.file.datareader.DataExtractor;
import com.exercise.file.datareader.ProcessorException;
import com.exercise.file.datareader.ReaderException;

/**
 * Main class that gets the injected beans by Spring. Invokes the method to read and then 
 * process the data 
 * 
 * @author Sivakumar Kumaravelu
 *
 */

public class Application 
{
	@SuppressWarnings({ "resource", "unchecked" })
	public static void main(String[] args) 
	{
		ApplicationContext context = null;
		try 
		{
			context = new ClassPathXmlApplicationContext("spring.xml");
			DataExtractor dataExtractor = (DataExtractor)context.getBean("dataExtractor");
			
			//Read the file and extract all the data
			List<Trade> input = (List<Trade>) dataExtractor.getReader().read();
			
			//Process the data
			Map<Object, BigDecimal> result = (Map<Object, BigDecimal>) dataExtractor.getProcessor().process(input);
			
			//Print the data
			result.forEach((key,value)->
			System.out.println(key+"  "+value
					));
		}
		catch(ReaderException ex)
		{
			System.out.println("Encountered exception -> " + ex.getMessage());
		}
		catch(ProcessorException ex)
		{
			System.out.println("Encountered exception -> " + ex.getMessage());
		}
		finally {
			((ConfigurableApplicationContext)context).close();
		}
	}
}
