package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Comparator;
import java.util.Objects;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import telran.util.Arrays;

class ArraysTests {
Integer[] numbers = {
		100, -3, 23, 4, 8, 41, 56, -7
};
String[] strings = {
		"abc", "lmn", "123", null, "a"
		};
String[] stringsMin = {
		"abc", "lmn", "123", "y"
		};
	@Test
	@DisplayName("Test of the method indexOf")
	void indexOftest() {
		assertEquals(1,Arrays.indexOf(strings, "lmn"));
		assertEquals(3,Arrays.indexOf(strings, null));
		assertEquals(-1,Arrays.indexOf(numbers, 150));
		assertEquals(4,Arrays.indexOf(numbers, 8));
	}
	@Test
	@DisplayName("Test of the method Comparator")
	void minValuetest() {
		Comparator<String> compLength = (s1,s2) -> s1.length() - s2.length();
		assertEquals("y",Arrays.min(stringsMin, 
				compLength));
		Comparator<String> compNative = (s1, s2) -> s1.compareTo(s2);
		assertEquals("123",Arrays.min(stringsMin, compNative));
	}
	@Test
	@DisplayName("Test of the method bubbleSort")
	void bubbleSortTest() {
			Integer [] expected = {
					4, 8, 56, 100, 41, 23, -3, -7
			};
		Integer [] numbersCopy = java.util.Arrays.copyOf(numbers, numbers.length);
//		Comparator<Integer> evenOddComp = (o1, o2)->
//		evenOddComparator(o1, o2);
//		Arrays.bubbleSort(numbersCopy, evenOddComp);
		Comparator<Integer> evenOddComp = ArraysTests::evenOddComparator;
		Arrays.bubbleSort(numbersCopy, evenOddComp);
		assertArrayEquals(expected, numbersCopy);
	}
	static int evenOddComparator(Integer o1, Integer o2) {
        boolean num1 = o1 % 2 == 0;
        boolean num2 = o2 % 2 == 0;
        if (num1 && num2) {
            return o1.compareTo(o2);
        } else if (!num1 && !num2) {
            return o2.compareTo(o1);
        } else {
            return num1 ? -1 : 1;
        }	
	}
	@Test
	@DisplayName("Test of the method search")
	void searchTest() {
		Integer[] expectedEven = {
				100, 4, 8, 56,
		};
		assertArrayEquals(expectedEven, Arrays.search(numbers,
				a -> a % 2 == 0));
		Integer[] expectedNegative = {
				-3, -7
		};
		assertArrayEquals(expectedNegative, Arrays.search(numbers,
				a -> a < 0));
	}
	@Test
	@DisplayName("Test of the method binarySearch")
	void binarySearchTest() {
		Integer[] sortedNumbers = {10, 20, 30, 40, 50};
		Comparator<Integer> comp = Integer::compare;
		assertEquals(0, Arrays.binarySearch(sortedNumbers, 10, comp));
		assertEquals(4, Arrays.binarySearch(sortedNumbers, 50, comp));
		assertEquals(2, Arrays.binarySearch(sortedNumbers, 30, comp));
		assertEquals(-1, Arrays.binarySearch(sortedNumbers, 5, comp));
		assertEquals(-4, Arrays.binarySearch(sortedNumbers, 35, comp));
		assertEquals(-6, Arrays.binarySearch(sortedNumbers, 55, comp));
	}
	@Test
	@DisplayName("Test of the method removeIf")
	void removeIfTest() {
		 Integer[] expectedEven = {100, 4, 8,  56};
			assertArrayEquals(expectedEven, Arrays.removeIf(numbers,
					a -> a % 2 != 0));
			Integer[] expectedNegative = {-3,-7};
			assertArrayEquals(expectedNegative, Arrays.removeIf(numbers,
					a -> a > 0));
	}
	@Test
	@DisplayName("Test of the method add")
	void addTest (){
		Integer[] expected = {100, -3, 23, 4, 8, 41, 56, -7, 150};
		assertArrayEquals(expected, Arrays.add(numbers, 150));
	}
	@Test
	@DisplayName("Test of the method personsSort")
	void personsSortTest() {
		Person prs1 = new Person(123,1985);
		Person prs2 = new Person(120,2000);
		Person prs3 = new Person(128,1999);
		Person[] persons = {
				prs1,prs2,prs3
		};
		 Person[] expectedAge = {
				 new Person(120, 2000),
				 new Person(128, 1999),
				 new Person(123, 1985)
		 };
		Arrays.bubbleSort(persons);
		Person[] expected = {new Person(120, 2000),
				new Person(123,1985),
				new Person(128,1999)};
		assertArrayEquals(expected, persons);
		Arrays.bubbleSort(persons,(p1, p2) -> Integer.compare(p2.birthYear, p1.birthYear));
		assertArrayEquals(expectedAge, persons);
	}
	
class Person implements Comparable<Person>{
	long id;
	int birthYear;
	public Person(long id, int birthYear) {
		this.id = id;
		this.birthYear = birthYear;
	}
	@Override
	public int compareTo(Person o) {
		
		return Long.compare(id, o.id);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + getEnclosingInstance().hashCode();
		result = prime * result + Objects.hash(birthYear, id);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
			return false;
		return birthYear == other.birthYear && id == other.id;
	}
	private ArraysTests getEnclosingInstance() {
		return ArraysTests.this;
	}
	
	
}

}
	