package application;

// Loops through the given counter value and returns a string of values 
// printed as many times as provided in the parameter / argument 

public class Looper {
	
	private int loopLimit;
	
	public Looper(int loop){
		loopLimit = loop;	
	}
	
	public String loop(){
		String output = "";
		for(int i = 1 ; i <= loopLimit; i++ ){
			// prints a dot and a space as many times as the value of loopLimit
			// 0 % 10 will be 0 and thus the if condition will be true for 0
			// so the loop here starts with 1
			
			if(i % 10 == 0)
				output += ". \n";
			else
				output += ". ";			
		}
		return output;
	}	
}
