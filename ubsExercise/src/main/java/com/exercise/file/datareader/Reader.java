package com.exercise.file.datareader;

/**
 * Generic interface for Reader, write custom readers.
 * 
 * @author Sivakumar Kumaravelu
 *
 */
public interface Reader
{
	
	/**
	 * Read and extract data for the given input source 
	 * and bind it to generic type T.
	 * 
	 * @return T
	 * 
	 * @throws ReaderException
	 */
	public <T> T read() throws ReaderException;
}
