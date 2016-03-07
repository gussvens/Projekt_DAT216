package hw2.out;

public class InterfaceAndImplementation {

    //a ----------------------------------------
    public interface IA {
        //public int doIt();
        //public double doIt();
    }

    // b -----------------------------------
    public interface IB {
        public abstract void doIt();
        public abstract int doOther();
        public abstract int doYetOther(int i);
    }

    public interface IC {
        public abstract void doIt();
        public abstract void doOther();
        public abstract int doYetOther(String i);
    }

    public class ImplAB implements IB, IC {

    }

    // c -------------------

    public interface ID {
        public void doIt();
        public void doIt(int i);
    }

    public class ImplD implements ID {

        public void doIt() {
            System.out.println("Doing it");
        }

        public void doIt(int i) {
            System.out.println("Doing it int" + i);
        }

        public void doIt(String s) {
            System.out.println("Doing it string " + s);
        }
    }
}
