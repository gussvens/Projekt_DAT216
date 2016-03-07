package hw2.out._4_1;  
public class Main {
    public static void main(String args[]) {
       B[] b = new B[]{ new B(), new B(), new B() };      
       upDate( b );
       b[1].doB();       
    }   
    public static void upDate( A[] a ){      
        a[0] = null;
        a[1] = new A();
        a[2] = new B();
    }
}
