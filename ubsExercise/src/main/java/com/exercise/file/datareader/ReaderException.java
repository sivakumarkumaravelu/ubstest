package com.exercise.file.datareader;

/**
 * Exception to handle the reader failures.
 * 
 * @author Sivakumar Kumaravelu
 *
 */
public class ReaderException extends Exception 
{
	private static final long serialVersionUID = 1L;
	private String errorMsg = null;
	
	public ReaderException()
	{
		super();
	}
	
	public ReaderException(String errorMsg)
	{
		super(errorMsg);
		this.errorMsg = errorMsg;
	}
	
	public ReaderException(Throwable cause) 
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

