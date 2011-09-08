package main.java.concepts;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

// http://java.sun.com/developer/technicalArticles/Programming/serialization/

// Object serialization is the process of saving an object's state to a sequence of bytes
// as well as the process of rebuilding those bytes into a live object at some future time. 

// Common use - to send objects over network

// Core components -
// FileOutputStream
// ObjectOutputStream
// FileInputStream
// ObjectInputStream

public class Serialization {

	public static final String dir = "/Users/ashishsharma/development/code/workspace/Samples/files/";

	public static void main(String[] args) throws IOException,
			ClassNotFoundException {
		Serialization s = new Serialization();
		s.serialize();
		s.deserialize();
	}

	public void serialize() {
		// creating an object that is to be serialized
		Employee emp = new Employee();
		
		emp.name = "Ashish";
		emp.id = 123;
		emp.role = "Manager";
		Department d = new Department();
		d.deptName = "Engineering";
		emp.dept = d;

		// file path to which the serialized object should be written
		String file = dir + "emp.ser";
		
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		
		try {
		
			// these are the 2 core components that serialize
			// any operations with these, throw IOException
			fos = new FileOutputStream(file);
			oos = new ObjectOutputStream(fos);
			
			// this actually writes the object to bytes into the file
			oos.writeObject(emp);
		
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			try {
				// FileOutputStream and ObjectOutputStream MUST be closed
				if (oos != null)
					oos.close();
				if (fos != null)
					fos.close();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
	}

	public void deserialize() throws IOException, ClassNotFoundException {
		
		String file = dir + "emp.ser";
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		
		try {
			// these are the 2 core components that read the serialized object
			// any operations with these, throw IOException
			fis = new FileInputStream(file);
			ois = new ObjectInputStream(fis);
			
			// ObjectInputStream.readObject() - returns Object(). It must be type-casted
			// It throws ClassNotFoundException
			// For a JVM to be able to deserialize an object, it must be able to find the bytecode for the class, it is deserializing and typecasting to.
			Employee e = (Employee) ois.readObject();
			
			System.out.println(e.name);
			System.out.println(e.id);
			
			
			System.out.println("Role: " + e.role);
			
			// Department class was not serializable
			// so we made dept member of Employee as transient
			// thus dept was never written to serialized object
			// when we read back the serialzed object, e.dept will not be there and will be null
			if(e.dept != null)
				System.out.println(e.dept.deptName);
			
			e.doJob();
			
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			try {
				// FileInputStream and ObjectInputStream MUST be closed
				if (ois != null)
					ois.close();
				if (fis != null)
					fis.close();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
	}
}

// The object to be persisted must implement the Serializable interface OR inherit that implementation from its object hierarchy.
// For complete Object to be seralized, all members of the object should be serializable. 
// The object to be persisted must mark all nonserializable fields transient

// serialVersionUID
// If after serializing an object, we make a small change (add one field) to class and try to desirialize, it gives InvalidClassException
// all Serializable classes automatically get a unique identifier serialVersionUID which become different after we change the class.
// and while deserialization, if serialVersionUID's are different, it throws exception

// To avoid this - if we know that the changes we make to class, will be compatible after deserialization - then we can specify serialVersionUID to class
// If we hardcode serialVersionUID, and the change we make is compatible, such that and  (Employee) ois.readObject() casting is fine, we won't get any exceptions
// JDK provides "serialver MyClass" utility, which gives the serialVersionUID from command line.

class Employee implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public String name = null;
	public int id = 0;
	
	public static String role = "HardCodedStaticRole";
	
	// all members of an Object should be serailizable.
	// here Department is not serializable, it will throw java.io.NotSerializableException at runtime.
	// so we can avoid that by telling JVM that this member should not serialized by using TRANSIENT modifier.
	// in that case, transient member if not written to object bytes, while rest of the object is serialized.
	public transient Department dept;

	public void doJob() {
		System.out.println("Name is: " + name + ". ID is: " + id);
	}
}

class Department {
	
	public String deptName = null;
}

// NOTES
// Serializable - is a MARKER INTERFACE
// An empty interface having no methods or fields/constants is called a marker interface or a tag interface.
// Why Marker Interface? - the class might only need to flag that it belongs to that particular type and everything else is handled/done by some other api.

// Parent class fields are only serialized if the base class itself is serializable.

