package hw2.out._3_1;
public class Main {
   public static void main(String[] args) {
        Classifier c = new Classifier();
        Object[] o = new Object[] {
                new Integer(0), new String(), new Double(0)
        };
        for (int i = 0; i < o.length; i++) {
            c.classify(o[i]);
        }   
    }
}
