package com.exercise.file.datareader;

/**
 * Exception class to handle the processor failures.
 * 
 * @author Sivakumar Kumaravelu
 *
 */
public class ProcessorException extends Exception 
{
	private static final long serialVersionUID = 1L;
	
	private String errorMsg = null;
	
	public ProcessorException()
	{
		super();
	}
	
	public ProcessorException(String errorMsg)
	{
		super(errorMsg);
		this.errorMsg = errorMsg;
	}
	
	public ProcessorException(Throwable cause) 
	{
        super(cause);
        errorMsg = cause.getMessage();
    }
 
    @Override
    public String toString() 
    {
        return errorMsg;
    }
 
    @Override
    public String getMessage() 
    {
        return errorMsg;
    }
}

