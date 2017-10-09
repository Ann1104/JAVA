package pl.lodz.uni.math.projectTest;

public class Calculator {
   private int firstDigit;
   private int secondDigit;
   private int result;
   
   public Calculator(int firstDigit, int secondDigit)
   {
   this.firstDigit=firstDigit;
   this.secondDigit=secondDigit;
   }

public void add() {
	this.result=this.firstDigit+this.secondDigit;
	
}

public int getResult() {
	return this.result;
	
}
    public void setFirstDigit(int firstDigit) {
		this.firstDigit = firstDigit;
	}
    public void setSecondDigit(int secondDigit) {
		this.secondDigit = secondDigit;
	}

}
