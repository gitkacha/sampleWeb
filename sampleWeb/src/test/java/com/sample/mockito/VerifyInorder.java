package com.sample.mockito;

import static org.mockito.Mockito.inOrder;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class VerifyInorder {

	private static interface TestIntf{
		void get();
	}
	
	@Mock
	List firstMock;
	
	@Mock
	TestIntf secondMock;
	
	@Test
	public void testVerifyOrder()
	{
		//List mockedList = mock(List.class);

		 //using mock object
		secondMock.get();
		firstMock.add("was called first");
		
		 
		//create inOrder object passing any mocks that need to be verified in order
		 InOrder inOrder = inOrder(firstMock, secondMock);
		 
		 //following will make sure that firstMock was called before secondMock
		 inOrder.verify(firstMock).add("was called first");
		 inOrder.verify(secondMock).get();




	}
	

}
