package SummingIntConstant;
class Test {
   public void foo() {
     int x = 42;
     x = bar(x * 12);
     System.out.println(x + 5 * bar(x - 2));
   }
   public int bar(int q) {
     return q + 23;
   }
}