package HW6;

//In the Java programming language, an interface is a reference type, similar to a class, that can contain only constants,
// method signatures, default methods, static methods, and nested types. Method bodies exist only for default methods and static methods.
// Interfaces cannot be instantiated, they can only be implemented by classes or extended by other interfaces.

// Interface Transport
interface Transport {
    void start();
    void stop();
}
//can absolutely use interfaces even when there is no direct inheritance relationship involved.
// Interfaces are not limited to inheritance, and they have broader uses beyond inheritance hierarchies.
// here we are trying to achieve polymorphism without actually introducing inheritance.
// Concrete class: Car
class Car implements Transport {
    private String brand;

    public Car(String brand) {
        this.brand = brand;
    }
    // the class that implements an interface must define the method which is declared in the interface.
    @Override
    public void start() {
        System.out.println("Starting the " + brand + " car.");
    }
    @Override
    public void stop() {
        System.out.println("Stopping the " + brand + " car.");
    }
}

// Concrete class: Bike
class Bike implements Transport {
    private String brand;
    //constructor
    public Bike(String brand) {
        this.brand = brand;
    }
    //defining the methods here
    @Override
    public void start() {
        System.out.println("Starting the " + brand + " bike.");
    }
    @Override
    public void stop() {
        System.out.println("Stopping the " + brand + " bike.");
    }
}

public class MainInterface {
    public static void main(String[] args) {
        Transport car = new Car("Dodge");
        Transport Bike = new Bike("Harley");

        // Polymorphism
        car.start();
        car.stop();

        Bike.start();
        Bike.stop();
    }
}
// In the Main class, we create instances of both Car and Bike and demonstrate polymorphism by calling the start() and stop()
// methods on objects of the Transport interface.
// This shows how different classes can adhere to a common contract defined by an interface.


// So it makes more sense using Interfaces when multiple classes need to use code which behaves same but are not actually communicating with
//the different classes which are using the same code behaviour.

// In this code, the interface Transport provides start and stop methods as contract for the classes which implements the interface.
// the classes Car and Bike aren't actually related to each other but can access the same code as that of the class Car.
