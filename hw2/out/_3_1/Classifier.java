package hw2.out._3_1;
public class Classifier {
   public void classify(Integer i) {
        System.out.println("It's an integer");
    }
    public void classify(String s) {
        System.out.println("It's a String");
    }
    public void classify(Object o) {
        System.out.println("Don't know, it's anything...");
    }
}
