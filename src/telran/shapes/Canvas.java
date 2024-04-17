package telran.shapes;

import java.util.Iterator;
import java.util.NoSuchElementException;

import telran.util.Arrays;

public class Canvas implements Iterable<Shape>{
	private Shape[] shapes;
    public Canvas() {
        this.shapes = new Shape[0];
    }
	@Override
	public Iterator<Shape> iterator() {
        return new Iterator<Shape>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < shapes.length;
            }

            @Override
            public Shape next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return shapes[currentIndex++];
            }
        };
	}
	   public void addShape(Shape shape) {
	        Shape[] newShapes = new Shape[shapes.length + 1];
	        System.arraycopy(shapes, 0, newShapes, 0, shapes.length);
	        newShapes[shapes.length] = shape;
	        shapes = newShapes;
	    }
	    public void removeShape(long id) {
	    	shapes = Arrays.removeIf(shapes, s -> s.getId() == id);
	    }
	    public int totalSquare() {
	        int sum = 0;
	        for (Shape shape : shapes) {
	            sum += shape.square();
	        }
	        return sum;
	    }

	    public int totalPerimeter() {
	        int sum = 0;
	        for (Shape shape : shapes) {
	            sum += shape.perimeter();
	        }
	        return sum;
	    }
}
