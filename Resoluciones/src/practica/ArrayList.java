/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author gabri
 */

public class ArrayList<E> implements List<E>, Serializable{
    
    private int capacity = 100;
    private E[] elements = null;
    private int effectiveSize = 0;

    public ArrayList() {
        this.elements = (E[]) (new Object[capacity]);
        this.effectiveSize = 0;
    }
  
    
    private boolean isFull() {
        return elements.length == effectiveSize;
    }
    
    private void addCapacity () {
        E[] tmp = (E[]) new Object[capacity * 2];
        for (int i = 0; i < capacity; i++) {
            tmp[i] = elements[i];
        }
        elements = tmp;
        capacity = capacity * 2;
    }
    
    @Override
    public int size() {
        return effectiveSize;
    }

    @Override
    public boolean isEmpty() {
        return effectiveSize == 0;
    }

    @Override
    public void clear() {
        effectiveSize = 0;
    }
    
    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < effectiveSize; i++) {
            result += elements[i].toString() + ", ";
        }
        return result;
    }
    
    @Override
    public boolean addFirst(E element) {
        if(element == null){
            return false;
        }else if(isEmpty()){
            elements[effectiveSize ++] = element;
            return true;
        }else if (capacity == effectiveSize){
            addCapacity();
        }        
        for(int i = effectiveSize -1 ; i >= 0; i-- ){
            elements[i+1] = elements[i];
        } 
        elements [0] = element;
        effectiveSize++;
        return true;   
    }
    
    /* EN ESTE TALLER, USTED DEBE IMPLEMENTAR LOS SIGUIENTES MÉTODOS */

    @Override
    public boolean addLast(E e) {
        if(e == null) return false;
        else if(isEmpty()) {
            elements[0] = e;
            effectiveSize ++;
            return true;
        }
        else if (capacity == effectiveSize){
            addCapacity();
        }
        elements[effectiveSize++] = e;
        return true;
    }

    @Override
    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }
        E first = elements[0];
        for (int i = 0; i < effectiveSize-1; i++) {
            elements[i] = elements[i+1];
        }
        effectiveSize--;
        return first;
    }

    @Override
    public E removeLast() throws Exception{
        if (isEmpty()) throw new Exception("ArrayList vacio.");
        E last = elements[effectiveSize-1];
        effectiveSize--;
        return last;
    }
    
    @Override
    public boolean add(int index, E element) throws Exception {
        if(element == null) return false;
        if(index < 0) return false;
        if(isEmpty()){
            if(index==0) {
                elements[0] = element;
                effectiveSize++;
            }
            else throw new Exception("indice no admitido.");
        }else if(isFull()){
            addCapacity();
        }else{
            for (int i = effectiveSize-1; i >= index; i--) {
            elements[i+1] = elements[i];
            }
            elements[index] = element;
            effectiveSize++;
        }
        return true;
    }

    @Override
    public E remove(int index) throws Exception{
        if(index < 0) throw new Exception("No se admiten indices negativos.");
        if(index > effectiveSize-1) throw new Exception("Indice no existente");
        if (isEmpty()) return null;
        E removido = elements[index];
        for (int i = index; i < effectiveSize; i++) {
            elements[i] = elements[i+1];
        }
        
        effectiveSize--;
        return removido;
    }

    @Override
    public E get(int index) throws Exception {
        if(index < 0) throw new Exception("No se admiten indices negativos.");
        if(index > effectiveSize-1) throw new Exception("Indice no existente");
        return elements[index];
    }

    @Override
    public E set(int index, E element) throws Exception{
        if(index < 0) throw new Exception("No se admiten indices negativos.");
        if(index > effectiveSize-1) throw new Exception("Indice no existente");
        elements[index] = element;
        return element;
    }
    
    public int getIndex(E element) throws Exception{
        int accu= 0;
        for(int i=0;i<effectiveSize;i++){
            if(!(get(i).equals(element))){
                accu+=1;
            }            
        }
        return accu;
    }
    
    public Iterator<E> iterator(){
        Iterator<E> it = new Iterator(){
            int i = 0;
            @Override
            public boolean hasNext() {
                return i<effectiveSize;
            }

            @Override
            public E next() {
                E e = elements[i];
                i++;
                return e;
            }
            
        };
        return it;
    }
    
    public boolean isIn(E element) {
        if(element == null) 
            return false;
        
        if(this.isEmpty()){
            throw new EmptyListException();
        }
        
        for(int n = 0; n < elements.length; n++){
            return elements[n] == element;
        }
        return false;
    }
    
   
}