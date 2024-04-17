package telran.util;

import java.util.Comparator;
import java.util.function.Predicate;

public class Arrays {
	public static <T> int indexOf(T[]array, T element) {
		int index = 0;
		while (index < array.length && !equals(array[index], element)) {
			index ++;
		}
		return index == array.length ? -1: index;
	}

	private static <T> boolean equals(T elem1, T elem2) {
		
		return elem1 == null ? elem1 == elem2 : elem1.equals(elem2);
	}
	public static <T> T min(T[] array, Comparator<T> comp ) {
		T res = null;
		if (array != null && array.length > 0) {
			res = array[0];
			for(int i = 0; i< array.length; i++) {
				if (comp.compare(res, array[i])> 0) {
					res = array[i];
				}
			}
		}
		return res;
		
	}
	public static <T> void bubbleSort(T[] arrays, Comparator<T> comp) {
		int length = arrays.length;
		for (int a = 0; a < length - 1; a++) {
			for (int b=0; b < length - a - 1; b++) {
				if (comp.compare(arrays[b], arrays[b + 1]) > 0) {
	                T temporary = arrays[b];
	                arrays[b] = arrays[b + 1];
	                arrays[b + 1] = temporary;
				}
			}
		}
	}
	@SuppressWarnings("unchecked")
	public static <T> void bubbleSort(T[] arrays) {
		bubbleSort(arrays, (Comparator<T>)Comparator.naturalOrder());
	}
	public static <T> int binarySearch(T[] array, T key, Comparator<T> comp) {
		int left = 0;
		int right = array.length - 1;
		int middle = (left + right) / 2;
		int res = -1;
		while(left <= right && (res = comp.compare(key, array[middle])) != 0) {
			if (res > 0) {
				left = middle + 1;
			} else {
				right = middle - 1;
			}
			middle = (left + right) / 2;
		}
		return left > right ? -(left + 1) : middle;
	}
	public static <T> T[] search (T[] array, Predicate<T> predicate) {
		T[] arResult = java.util.Arrays.copyOf(array, array.length);
		int index = 0;
		for(int i = 0; i < array.length; i++) {
			if(predicate.test(array[i])) {
				arResult[index++] = array[i];
			}
		}
		return java.util.Arrays.copyOf(arResult, index);
	}
	public static <T> T[] removeIf(T[] array, Predicate<T> predicate) {
		return search(array, e -> !predicate.test(e));
	}
	public static <T> T[] add (T[] array, T element) {
		T[] result = java.util.Arrays.copyOf(array, array.length + 1);
		result[array.length] = element;
		return result;
	}
	public static <T> T[] copy(T[] array) {
		return java.util.Arrays.copyOf(array, array.length);
	}

}
