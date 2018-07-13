package com.exercise.file.datareader.data;
import java.util.List;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import com.exercise.file.dao.Trade;
import com.exercise.file.datareader.Processor;
import com.exercise.file.datareader.ProcessorException;

/**
 * Actual data processor that does the aggregation and average computation. 
 * Objective of this class is do the seggregation only, could be clubbed as single class 
 * but making sure that Single responsibility is maintained.
 * 
 * @author Sivakumar Kumaravelu
 *
 */
public class DataProcessor implements Processor<Map<Object, BigDecimal>, List<Trade>>
{
	public Map<Object, BigDecimal> process(List<Trade> input) throws ProcessorException 
	{
		Map<Object, BigDecimal> resultMap = input.stream().collect(
				Collectors.groupingBy(trade -> {
					return new ArrayList<String>(Arrays.asList(trade.getCountry(), trade.getCreditRating()));
				}, 
		 		Collectors.mapping(Trade::getAmountInEuro,
		            Collector.<BigDecimal, List<BigDecimal>, BigDecimal>of(
		                ArrayList::new, 
		                List::add,      
		                (list1, list2) -> {   
		                	list1.addAll(list2);
		                    return list1;
		                },
		                list -> list.stream() 
		                    .reduce(BigDecimal.ZERO, BigDecimal::add)
		                    .divide(BigDecimal.valueOf(list.size()))))));
		return resultMap;
	}
}
