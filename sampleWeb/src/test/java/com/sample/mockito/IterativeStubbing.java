package com.sample.mockito;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;



import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class IterativeStubbing {
	
	private static interface TestIntf{
		String someMethod(String arg);
	}
	
	@Mock
	TestIntf mock;
	
	@Test
	public void testInterfaceStubbing()
	{
		when(mock.someMethod("some arg"))
		   .thenThrow(new RuntimeException())
		   .thenReturn("foo");
		//First call: throws runtime exception:
		 try {
			mock.someMethod("some arg");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 //Second call: prints "foo"
		 System.out.println(mock.someMethod("some arg"));
		 
		 //Any consecutive call: prints "foo" as well (last stubbing wins). 
		 System.out.println(mock.someMethod("some arg"));

		 when(mock.someMethod("some arg"))
		   .thenReturn("one", "two", "three");

		 System.out.println("Changed existing mock");
		 System.out.println(mock.someMethod("some arg"));
		 System.out.println(mock.someMethod("some arg"));
	}
}
