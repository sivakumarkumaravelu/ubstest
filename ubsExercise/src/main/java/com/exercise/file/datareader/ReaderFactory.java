package com.exercise.file.datareader;
import com.exercise.file.util.SupportedInputTypes;

/**
 * Reader factory initialized from Spring Beans, need to pass the reader and based on reader type this class decides which reader to invoke.
 * This class uses constructor injection (Reason is inducing mandatory dependency)
 * 
 * @author Sivakumar Kumaravelu
 *
 */

public class ReaderFactory
{
	private Reader reader;
	private String readerType;

	public Reader getReader() throws ReaderException
	{
		//Create many readers and use in future
		if (SupportedInputTypes.valueOf(getReaderType())==SupportedInputTypes.DELIMITED)
		{
			return this.reader;
		}
		
		StringBuilder errorMsg = new StringBuilder("DataReader for ").append(readerType).append(" is not supported yet.");
		throw new ReaderException(errorMsg.toString());
	}
	

	public void setReader(Reader reader) {
		this.reader = reader;
	}
	
	public String getReaderType() {
		return readerType;
	}

	public void setReaderType(String readerType) {
		this.readerType = readerType;
	}
}

