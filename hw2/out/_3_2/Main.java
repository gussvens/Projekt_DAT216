package hw2.out._3_2;
public class Main {
    public static void main(String[] args) {
        Car c1 = new Car(123,1); 
        Car c2 = new Car(123,2);  
        Vehicle v1 = c1;
        Vehicle v2 = new Vehicle(123); 
        if( v1.equals(c2)){ 
            System.out.println("v1 eq c2");
        }
        if( !v1.equals(v2)){
            System.out.println("v1 NOT eq v2");
        }
    }
}
