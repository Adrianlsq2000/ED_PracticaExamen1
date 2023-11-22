/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica;

/**
 *
 * @author gabri
 */
public class EmptyListException extends RuntimeException {
    
    public EmptyListException(){
        super("La lista está vacía.");
    }
    
}