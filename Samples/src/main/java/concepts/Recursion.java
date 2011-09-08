package main.java.concepts;

public class Recursion {

	// Every recursion should have the following characteristics.
	//
	// 1) A simple base case which we know a solution for and a return value. This is to break the recursion loop. This is generally a hardcoded known value for fxn(0) or fxn(1) or fxn(null)
	// 2) A way of getting our problem closer to the base case. I.e. a way to chop out part of the problem to get a somewhat simpler problem.
	// 3) A recursive call which passes the simpler problem back into the method.
	
	// approach
	// solve for f(0) or f(1) or f(null)
	// solve for f(2)
	// solve for f(3) using f(2) - this will give a logic for sub-problem
	// generalize for f(n)
	// test
	
	// recusrion vs iteration
	
	// recursive - calls itself (possibly more than once), with different parameters, and defines an exit clause that is guaranteed to be reached
	// iterative - includes a loop, which iterates a pre-determined number of times, or checks for an exit clause every time through. 
	
	// 1) recursion uses more memory = Each recursive call adds a new layer to the stack, which means that if your algorithm has O(n) recursive calls then it uses O(n) memory. 
	// 	- Recursion may be slower, and use greater resources, because of the extra function calls. 
	// 2. Recursion may lead to simpler, shorter, easier-to-understand functions. 
	
	// use iteration or recursion 
	// with which it is easy to implement, maintain the code in a few months or years (and should not blow up performance) 
	// If you run into performance issues, then profile your code, and then and only then look into optimizing by moving over to an iterative implementation. 
	// You may want to look into memoization and dynamic programming.
	
	// when easy to use recursion - 
	// ÒDesign an algorithm to compute the nth	Ó; ÒWrite code to list the first n	Ó; ÒImplement a method to compute all	Ó; 
	
	public static void main(String[] args) {
		Recursion r = new Recursion();
		r.myCounter(4);
	}

	// recursive method is comprised of an if-else statement where the base case
	// returns one value and the non-base case(s) recursively call(s) the same
	// method with a smaller parameter or set of data.

	public void myCounter(int c) {
		if (c == 0)
			return;
		System.out.println(c);
		myCounter(--c);
		System.out.println(c);

	}

	// Note: Forgetting the base case leads to infinite recursion. It won't run
	// an infinite loop, instead, it will eventually run out of stack memory and
	// get a run-time exception called a stack overflow. The size of a
	// stack may be quite large, but limited. Therefore too deep recursion can
	// result in Stack Overflow. recursion has a serious disadvantage of using
	// large amount of memory

	public int myFactorial(int n) {
		if (n == 1)
			return 1;
		else {
			return (n * (myFactorial(n - 1)));
		}
	}
}
