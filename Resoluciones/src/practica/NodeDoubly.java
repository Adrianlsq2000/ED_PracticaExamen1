/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica;

import java.io.Serializable;

/**
 *
 * @author gabri
 */
public class NodeDoubly<E> implements Serializable{
    
    private E content;
    private NodeDoubly<E> next;
    private NodeDoubly<E> previous;

    public NodeDoubly(E content) {
        this.content = content;
    }

    public E getContent() {
        return content;
    }

    public void setContent(E content) {
        this.content = content;
    }

    public NodeDoubly<E> getNext() {
        return next;
    }

    public void setNext(NodeDoubly<E> next) {
        this.next = next;
    }

    public NodeDoubly<E> getPrevious() {
        return previous;
    }

    public void setPrevious(NodeDoubly<E> previous) {
        this.previous = previous;
    }
    
}
