package com.exercise.file.datareader;

/**
 * Data Extractor which injects reader and processor. 
 * 
 * @author Sivakumar Kumaravelu
 *
 */

public class DataExtractor 
{
	private ReaderFactory readerFactory;
	@SuppressWarnings("rawtypes")
	private Processor processor;
	
	public Reader getReader() throws ReaderException {
		return readerFactory.getReader();
	}

	public void setReaderFactory(ReaderFactory readerFactory) {
		this.readerFactory = readerFactory;
	}
	@SuppressWarnings("rawtypes")
	public Processor getProcessor() {
		return processor;
	}
	@SuppressWarnings("rawtypes")
	public void setProcessor(Processor processor) {
		this.processor = processor;
	}

}
