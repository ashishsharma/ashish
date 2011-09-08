package main.java.concepts;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

// Externalizable - interface is used to achive the serialization, but with our custom writing and reading logic.

// Example - read and write PDF files with a Java application. 
// If you know how to write and read PDF (the sequence of bytes required), 
// you could provide the PDF-specific protocol in the writeExternal and readExternal methods.

public class ExternalizableSample implements Externalizable{

	@Override
	public void writeExternal(ObjectOutput paramObjectOutput) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void readExternal(ObjectInput paramObjectInput) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
	}

	
}
