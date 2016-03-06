package hw2.out;
public class Compatibility {

    public interface IA {
        public void doA();
    }

    public interface IB {
        public void doB();
    }

    // static has no impact (just because this class is inside other class)
    public static class C implements IA {
        public void doA() {
            System.out.println("C doA");
        }

        public void doC() {
            System.out.println("C doC");
        }
    }

    public static class D extends C implements IB {
        public void doC() {
            System.out.println("D doC");
        }

        public void doB() {
            System.out.println("D doB");
        }
    }

    public static class E extends D {
        public void doB() {
            System.out.println("E doB");
        }
    }

    public static class F extends C implements IB {
        public void doB() {
            System.out.println("F doB");
        }
    }

    public static void main(String[] args) {
        // a
        C c = new E();
        c.doC();

        // b
        Object o = new E();
        System.out.println(o instanceof D);

        // c
        C c1 = new D();
        c1.doC();

        // d
        IB b = new E();
        D d = (D) b;
        d.doB();

        // e
        C c2 = new F();
        c2.doB();

        // f 
        IB b1 = new F();
        D d1 = (D) b1;

        // g
        D d2 = new C();
        d2.doA();

        // h
        IA a = new F();
        C c3 = a;
        c3.doA();
    }
}
