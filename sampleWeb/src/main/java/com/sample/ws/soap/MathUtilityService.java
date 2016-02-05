package com.sample.ws.soap;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

@WebService(name="MathService")
@SOAPBinding(style = Style.DOCUMENT, use = Use.LITERAL)
public interface MathUtilityService {

	int addIntegers(int firstNum, int secondNum);

	int factorial(int n);

}