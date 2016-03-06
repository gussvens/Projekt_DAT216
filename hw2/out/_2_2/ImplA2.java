package hw2.out._2_2;
public class ImplA2 implements IA {
   public void doIt(Object o) {
      System.out.println("See you");
   }
   public void doIt(B b) {
      b.sayIt();
   }
}
