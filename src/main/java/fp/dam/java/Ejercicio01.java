package fp.dam.java;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Ejercicio01 {

	static void colapsar(Deque<Integer> pila) { 
		Deque<Integer> aux= new ArrayDeque<Integer>(); 
		Deque<Integer> aux2= new ArrayDeque<Integer>();
		
		while(!pila.isEmpty())
			aux.offer(pila.pop()); 
		
		while(!aux.isEmpty()) {
			if(aux.size() > 2) 
				aux2.push(aux.poll() + aux.poll());
			else
				aux2.push(aux.poll());
		}
		
		while (!aux2.isEmpty())
			pila.push(aux2.pop());
	}
	
	public static void main(String[] args) {
		Deque<Integer> pila = new LinkedList<>(List.of(11, -9, 7, -5, 3));
		System.out.println(pila);
		colapsar(pila);
		System.out.println(pila);
	}

}
