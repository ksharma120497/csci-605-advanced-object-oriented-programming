package HW6;

// Abstract class
abstract class Shape {
    // Fields
    protected String color;

    // Constructor of the abstract class shape
    public Shape(String color) {
        this.color = color;
    }

    // Abstract method for calculating area (to be implemented by subclasses)
    // calculate area() method is just declared in abstract class and not implemented. implemented in subclass
    // and is implemented by all the child classes below
    public abstract double calculateArea();

    // Concrete method
    // Abstract classes also better when we have a need to implement concrete methods which can be accessed by the
    // objects of subclasses
    public void printColor() {
        System.out.println("Color: " + color);
    }
}

// Concrete subclass: Circle
class Circle extends Shape {
    private double radius;
    // Since class Circle is a subclass to the abstract class, it has to implement the abstract methods in
    // the abstract class otherwise if not, we would need to declare the sublcass as ABSTRACT as well.
    public Circle(String color, double radius) {
        super(color);
        this.radius = radius;
    }
    //implementing the abstract method here
    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}

// class rectangle is another subclass to the abstract class shape,
class Rectangle extends Shape {
    private double width;
    private double height;

    public Rectangle(String color, double width, double height) {
        //calling the abstract class constructor with color as its parameter
        super(color);
        this.width = width;
        this.height = height;
    }
    // implementing the abstract method here
    @Override
    public double calculateArea() {
        return width * height;
    }
}

public class Main {
    public static void main(String[] args) {
        Circle circle = new Circle("Red", 5.0);
        circle.printColor();
        System.out.println("Circle Area: " + circle.calculateArea());

        Rectangle rectangle = new Rectangle("Blue", 4.0, 6.0);
        rectangle.printColor();
        System.out.println("Rectangle Area: " + rectangle.calculateArea());
        //Since we have common behavior that can be shared among subclasses like calculating the area,
        // abstract classes are a good choice.
        // here we have called the function printcolor multiple times, without having the need to repeat the code for the same action.
        // Interfaces, on the other hand, can only declare method signatures without providing any default
        // implementations.
    }
}

// Since class Circle is a subclass to the abstract class, it has to implement the abstract methods in
// the abstract class otherwise if not, we would need to declare the sublcass as ABSTRACT as well.
