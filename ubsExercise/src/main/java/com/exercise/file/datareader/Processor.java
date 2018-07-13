package com.exercise.file.datareader;

/**
 * Generic interface for Processor creation. Implement this interface.
 * 
 * @author Sivakumar Kumaravelu
 *
 * @param <A>
 * @param <B>
 */

public interface Processor<A, B>
{
	/**
	 * 
	 * Process input of generic type B and return processed output
	 * of generic type A.
	 * 
	 * @param input of type B
	 * 
	 * @return - process and return processed output of type A.
	 * 
	 * @throws ProcessorException
	 */
	public A process(B input) throws ProcessorException;
}
