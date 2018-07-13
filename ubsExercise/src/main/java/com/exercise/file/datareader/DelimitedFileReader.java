package com.exercise.file.datareader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.exercise.file.dao.Trade;

/**
 * Delimited file reader, a concrete implementation of reading the file and storing the data in memory.
 * 
 * @author Sivakumar Kumaravelu
 *
 */

public class DelimitedFileReader implements Reader
{
	private String fileName;
	private String fileDelimiter;

	private static final Map<String, String> cityCountryMap = new HashMap<String, String>();

	static
	{
		cityCountryMap.put("London", "UK");
	}

	public DelimitedFileReader() {
		
	}
	
	public DelimitedFileReader(String fileName, String fileDelimiter) throws ReaderException {
		if (fileName == null || fileDelimiter == null)
		{
			throw new ReaderException("Unable to initialize DelimitedFileReader. Argument \"filePath\" and/or \"fileDelimiter\" property is missing.");
		}
		this.fileName=fileName;
		this.fileDelimiter=fileDelimiter;
	}

	/**
	 * 
	 * Load the contents of the DAT file as a List of String arrays.
	 * Alternative idea would be to create an object, but that would tie too much to the domain
	 * 
	 * @return List<String[]>
	 * 
	 * @throws ReaderException in case of exception
	 */
	@SuppressWarnings("unchecked")
	public List<Trade> read() throws ReaderException
	{	
		//TODO Reading through nio will be more effective
		Pattern pattern = Pattern.compile(fileDelimiter);
		try (BufferedReader in = new BufferedReader(new FileReader(new File(".").getCanonicalPath()+"/"+fileName))) {
			List<Trade> trades = in
					.lines()
					.skip(1)  //Skip the first line
					.map(trade -> {
						String[] arr = pattern.split(trade);
						return new Trade( arr[2],
								"".equals(arr[3].trim())?cityCountryMap.get(arr[2]):arr[3],
										arr[4],
										arr[5],
										new BigDecimal(arr[6]));
					})
					.collect(Collectors.toList());
			return trades;

		} catch (IOException ex) {
			throw new ReaderException(ex);
		}
	}
}
