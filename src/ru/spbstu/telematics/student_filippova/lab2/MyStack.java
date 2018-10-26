package ru.spbstu.telematics.student_filippova.lab2;

import java.util.Iterator;

public class MyStack<T> implements Iterable<T> {
	
	private final int RES = 50;
	
	private T[] Items;
    private int CountOfItems;
    private int Capacity;

	public MyStack() {
		CountOfItems = Capacity = 0;
	}
	
	public void Push(T item) {
		if (CountOfItems>=Capacity){
	        Capacity += RES;
	        T[] tmpArray = (T[])new Object[Capacity];
	        if(CountOfItems != 0) {
	        	for(int i = 0; i < CountOfItems; i++) {
	        		tmpArray[i] = Items[i];
	        	}
	        }
	        Items = tmpArray;
	    }
		Items[CountOfItems++] = item;
	}
	
	public boolean Empty() {
		if(CountOfItems == 0)
			return true;
		else return false;
	}
	
	public T Peek() {
		if(!Empty()) {
			return Items[CountOfItems-1];
		}
		return null;
	}
	
	public T Pop() {
		if(!Empty()) {
			T tmpItem = Items[CountOfItems-1];
			Items[CountOfItems-1] = null;
			CountOfItems--;
			return tmpItem;
		}
		return null;
	}
	
	public int Search(T item) {
		if(!Empty()) {
			for(int i = 0; i < CountOfItems; i++) {
				if(Items[i] == item)
					return (CountOfItems-(i+1));
			}
		}
		return -1;
	}
	
	@Override
	public Iterator<T> iterator() {
		Iterator<T> it = new Iterator<T>() {
			 private int currentIndex = 0;

	         @Override
	         public boolean hasNext() {
	        	 return currentIndex < CountOfItems && Items[currentIndex] != null;
	         }
	         
	         @Override
	         public T next() {
	        	 return Items[currentIndex++];
	         }

	         @Override
	         public void remove() {
	        	 throw new UnsupportedOperationException();
	         }

		};
		return it;
	}
	
	@Override
	public String toString()
    {
        String result = "[ ";
        for(int i = 0; i < CountOfItems; i++) {
        	result += Items[i];
        	if(i != CountOfItems-1) {
        		result += ", ";
        	}
        }
        result += "]";
        return result;
    }

}

/* Data& BD::operator[](const char* key){
    for(int i = 0; i < size; i++ ){
        if(ar[i]->name==key) return ar[i]->information;
    }
    if (size>=capacity){
        capacity += Res;
        Pair** tmp = new Pair*[capacity];
        for (int i = 0; i<size; i++){
            tmp[i] = ar[i];
        }
        delete[] ar;
        ar = tmp;
    }
    ar[size] = new Pair;
    ar[size]->name = key;
    return ar[size++]->information;
}*/