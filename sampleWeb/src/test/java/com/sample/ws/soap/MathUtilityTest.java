package com.sample.ws.soap;

import javax.jws.WebService;


public class MathUtilityTest implements MathUtilityService {

    /* (non-Javadoc)
	 * @see com.sample.ws.soap.MathUtilityService#addIntegers(int, int)
	 */
    @Override
	public int addIntegers(int firstNum, int secondNum) {
        return firstNum + secondNum;
    }    

    /* (non-Javadoc)
	 * @see com.sample.ws.soap.MathUtilityService#factorial(int)
	 */
    @Override
	public int factorial(int n) {
        int result = 1;        
        for (int i = 1; i <= n; i++) {
            result = result * i;
        }        
        return result;
    }
}