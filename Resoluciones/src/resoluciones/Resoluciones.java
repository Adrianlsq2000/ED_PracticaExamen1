/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package resoluciones;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author gabri
 */
public class Resoluciones {

    public static List<String> getStringShorterThan(List<String> inputList, int k){
        List<String> result = new ArrayList<>();
        return filterWords(inputList, k, result);
    }
    
    private static List<String> filterWords(List<String> words, int k, List<String> result){
        if(words.isEmpty()){
            return result;
        }
        
        String actual = words.get(0);     
        
        if(actual.length() < k){
            result.addLast(actual);
        }
        
        return filterWords(words.subList(1, words.size()), k, result);
    }
    
    public static <T extends Comparable<T>> Stack<T> combineOrderedStack(Stack<T> s1, Stack<T> s2){
        Stack<T> resultStack = new Stack<>();
        
        
        while(!s1.isEmpty() && !s2.isEmpty()){
            if(s1.peek().compareTo(s2.peek()) < 0){  
                resultStack.push(s1.pop());              
            } else {                                        
                resultStack.push(s2.pop());             
            }
        }
        
        while(!s1.isEmpty()){
            resultStack.push(s1.pop());
        }
        
        while(!s2.isEmpty()){
            resultStack.push(s2.pop());
        }
        
        return resultStack;
    }
    
    public static void main(String[] args) {
        
//        List<String> inputList = List.of("apple", "banana", " kiwi", "uva");
//        int k = 6;
//        
//        List<String> result = getStringShorterThan(inputList, k);
//        
//        System.out.println("Palabras que son menores a:" + k + ":" + result);
        
        
          Stack<Integer> s1 = new Stack<>();
          s1.push(10);
          s1.push(7);
          s1.push(5);
          
          Stack<Integer> s2 = new Stack<>();
          s2.push(8);
          s2.push(6);
          s2.push(3);
          s2.push(1);
          
          Stack<Integer> resultado = combineOrderedStack(s1, s2);
          
          System.out.println(resultado);
        
    }
    
}
