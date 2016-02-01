package com.sample.mockito;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.argThat;

import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ArgumentMatcherTest {

	private static ArgumentMatcher<Integer> isValid(){
		return new ArgumentMatcher<Integer>(){

			@Override
			public boolean matches(Object argument) {
				if(argument instanceof Integer)
					return true;
				return false;
			}
	};
	}
	@Mock
	List mockedList;
	@Test
	public void testArgumentMatcher()
	{//stubbing using built-in anyInt() argument matcher
		// when(mockedList.get(anyInt())).thenReturn("element");
		 
		 //stubbing using hamcrest (let's say isValid() returns your own hamcrest matcher):
		 when(mockedList.contains(argThat(isValid()))).thenReturn(true);
		 
		 //following prints "element"
		 System.out.println(mockedList.get(999));
		 System.out.println(mockedList.contains("aaa"));
		 System.out.println(mockedList.contains(777));
		 //you can also verify using an argument matcher
		 verify(mockedList).get(anyInt());
		 verify(mockedList,times(2)).contains(anyString());
}
	

}
