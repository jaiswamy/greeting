package com.hiideals;

public class RandomNoGenerator {

	
	
	   static int numberGenerator(){
		   
			        long timeSeed = System.nanoTime(); // to get the current date time value

			        double randSeed = Math.random() * 1000; // random number generation

			        long midSeed = (long) (timeSeed * randSeed); // mixing up the time and
			                                                         // rand number.

			                                                        // variable timeSeed
			                                                        // will be unique


			                                                       // variable rand will 
			                                                       // ensure no relation 
			                                                      // between the numbers

			        String s = midSeed + "";
			        String subStr = s.substring(0, 8);

			        int finalSeed = Integer.parseInt(subStr);    // integer value

			        System.out.println(finalSeed);
		
			        return finalSeed;

	      }

	}