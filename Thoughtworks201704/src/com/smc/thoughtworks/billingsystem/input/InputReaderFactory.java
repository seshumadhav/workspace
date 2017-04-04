package com.smc.thoughtworks.billingsystem.input;

/**
 * 
 * @author seshumadhav@gmail.com
 */
public class InputReaderFactory {
	
	private GetsInput inputReader;

	public InputReaderFactory(boolean needRealInputReader) {
		inputReader = needRealInputReader ? new CommandLineReader() : new DummyInputReader();
	}
	
	public GetsInput getInputReader() {
		return inputReader;
	}

}
