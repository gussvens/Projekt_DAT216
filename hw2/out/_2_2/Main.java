package hw2.out._2_2;
public class Main {
   public static void main(String[] args) {
      IA ia = new ImplA();
      ImplA2 a2 = new ImplA2();
      B b = new B();
      Object o = b;
      ia.doIt(o);
      ia = a2;
      ia.doIt(o);
      ia.doIt(b);
   }
}
