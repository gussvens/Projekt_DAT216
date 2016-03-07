package hw2.out._2_2;
public class ImplA implements IA {
   public void doIt(Object o) {
      System.out.println("Hello");
   }
   public void doIt(B b) {
      b.sayIt();
   }
}
