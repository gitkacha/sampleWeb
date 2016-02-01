package com.sample.mockito;

import static org.mockito.Mockito.*;

import java.io.FileNotFoundException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class StubWithExceptions {

	private static interface TestIntf{
		void throwChecked() throws FileNotFoundException;
		int throwChecked(int a) throws FileNotFoundException;
	}
	@Mock
	List mockedList;
	
	@Mock
	TestIntf mockedTestIntf;
	
	@Test
	public void testStubWithExceptions() 
	{
		//for non-void methods use thenThrow
	//	when(mockedList.get(0)).thenThrow(new RuntimeException());
		
		//for void methods  thenThrow does not compile
		   //when(mockedList.clear()).thenThrow(new RuntimeException());
		
		
		//for void methods use doThrow 
		
		doThrow(new RuntimeException()).when(mockedList).clear();	
		
		
		mockedList.get(0);
		mockedList.clear();
		/* //verification
		 verify(mockedList).add("one");
		 verify(mockedList).clear();*/


	
	try {
		doThrow(new FileNotFoundException("for void methods use doThrow")).when(mockedTestIntf).throwChecked();
		mockedTestIntf.throwChecked();
		verify(mockedTestIntf).throwChecked();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	}
	

}
