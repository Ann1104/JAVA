package pl.lodz.uni.math.projectTest;

public class MyFirstCalc {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
Calculator calculator=new Calculator(5,6);
calculator.add();
calculator.getResult();

calculator.setFirstDigit(10);
calculator.setSecondDigit(5);
calculator.add();

System.out.println(calculator.getResult());

	}

}
