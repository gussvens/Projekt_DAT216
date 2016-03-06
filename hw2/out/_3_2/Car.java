package hw2.out._3_2;
public class Car extends Vehicle {
    private int numbSeats;
    public Car(int licNumb, int numbSeats) {
        super(licNumb);
        this.numbSeats = numbSeats;
    }
    public boolean equals(Car c) {
        if (this == c) {
            return true;
        } else if (!super.equals(c)) {
            return false;
        } else if (getClass() != c.getClass()) {
            return false;
        }
        final Car other = (Car) c;
        if (numbSeats != other.numbSeats){
            return false;
        }
        return true;
    }
}
