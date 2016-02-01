package com.sample.mockito;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SpyTest {

	private static interface TestIntf{
		void get();
	}
	
	@Spy
	LinkedList spy= new LinkedList();
	
	
	
	@Test
	public void testVerifySpy()
	{
		/* List list = new LinkedList();
		   List spy = spy(list);*/
		 
		   //optionally, you can stub out some methods:
		   when(spy.size()).thenReturn(100);
		 
		   //using the spy calls real methods
		   spy.add("one");
		   spy.add("two");
		 
		   //prints "one" - the first element of a list
		   System.out.println(spy.get(0));
		 
		   //size() method was stubbed - 100 is printed
		   System.out.println(spy.size());
		 
		   //optionally, you can verify
		   verify(spy).add("one");
		   verify(spy).add("two");




	}
	

}
