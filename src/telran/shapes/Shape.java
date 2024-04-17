package telran.shapes;

public abstract class Shape {
	long id;
    public Shape(long id) {
        this.id = id;
    }
    public long getId() {
        return id;
    }
    public abstract int square();
    public abstract int perimeter();
}
