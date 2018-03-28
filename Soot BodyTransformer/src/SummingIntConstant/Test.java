package SummingIntConstant;
public class Test {
   public void foo() {
	 int[] list = new int[10];
     int x = 42;
     x = bar(x * 12);
     System.out.println(x + 5 * bar(x - 2));
     boolean check = true;
     boolean th = false;
    /* if (x == 1) {
    	 x = 0;
     }*/
   }
   public int bar(int q) {
     return q + 23;
   }
}