/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package resoluciones;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * @author gabri
 */
public class Examen2 {

    public static class Persona {

        String color, nombre;

        public Persona(String color, String nombre) {
            this.color = color;
            this.nombre = nombre;
        }

        public String getColor() {
            return color;
        }

        public String getNombre() {
            return nombre;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Persona persona = (Persona) o;
            return Objects.equals(nombre, persona.nombre) && Objects.equals(color, persona.color);
        }

        @Override
        public int hashCode() {
            return Objects.hash(nombre, color);
        }

        @Override
        public String toString() {
            return "Persona{" + "color=" + color + ", nombre=" + nombre + '}';
        }

    }

    public static class Pareja {

        Persona roja, azul;

        public Pareja(Persona roja, Persona azul) {
            this.roja = roja;
            this.azul = azul;
        }

        public Pareja(Persona nuevaPersona) {
            if (nuevaPersona.getColor().equals("Azul")) {
                this.azul = nuevaPersona;
                this.roja = null;
            } else if (nuevaPersona.getColor().equals("Roja")) {
                this.roja = nuevaPersona;
                this.azul = null;
            }
        }

        public boolean estaCompleta() {
            return roja != null && azul != null;
        }

        public Persona getRoja() {
            return roja;
        }

        public Persona getAzul() {
            return azul;
        }

        public void setRoja(Persona roja) {
            this.roja = roja;
        }

        public void setAzul(Persona azul) {
            this.azul = azul;
        }

        @Override
        public String toString() {
            return "Pareja{" + "roja=" + roja + ", azul=" + azul + '}';
        }
        
        @Override
        public boolean equals(Object o){
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Pareja pareja = (Pareja) o;
            return Objects.equals(roja, pareja.roja) && Objects.equals(azul, pareja.azul);
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(roja, azul);
        }
    }

    public static class ParejasPresentacion {

        public Queue<Pareja> colaParejas;

        public ParejasPresentacion() {
            this.colaParejas = new LinkedList<>();
        }

        public boolean anadirPersona(Persona nuevaPersona) {
            Queue<Pareja> copia = new LinkedList<>();
            boolean agregado = false;

            if (colaParejas.isEmpty()) {
                colaParejas.offer(new Pareja(nuevaPersona));
                return true;
            }

            while (!colaParejas.isEmpty()) {
                Pareja actual = colaParejas.poll();
                if (!actual.estaCompleta()) {
                    if (compatibilidad(nuevaPersona, actual).equals("Azul")) {
                        actual.azul = nuevaPersona;
                        copia.offer(actual);
                        agregado = true;
                    } else if (compatibilidad(nuevaPersona, actual).equals("Roja")) {
                        actual.roja = nuevaPersona;
                        copia.offer(actual);
                        agregado = true;
                    } else {
                        copia.offer(actual);
                    }
                } else {
                    copia.offer(actual);
                }

            }

            if (!agregado) {
                copia.offer(new Pareja(nuevaPersona));
            }

            colaParejas = copia;
            return true;

        }

        public String compatibilidad(Persona persona, Pareja pareja) {
            if (persona.getColor().equals("Azul") && pareja.azul == null) {
                return "Azul";
            } else if (persona.getColor().equals("Roja") && pareja.roja == null) {
                return "Roja";
            } else {
                return "No";
            }
        }

        public Pareja siguientePareja() {
            if (colaParejas.peek().estaCompleta()) {
                return colaParejas.poll();
            } else {
                return null;
            }
        }
    }

    public static Map<Pareja, Integer> contarParticipaciones(List<Pareja> listaParejas) {
        Map<Pareja, Integer> participaciones = new HashMap<>();
        
        for(Pareja pareja: listaParejas) {
            participaciones.put(pareja, participaciones.getOrDefault(pareja, 0)+1);   
        }
        
        return participaciones;
    }
    
    public static class ParejasRepetidas{
        private Pareja pareja;
        private int repeticiones;

        public ParejasRepetidas(Pareja pareja, int repeticiones) {
            this.pareja = pareja;
            this.repeticiones = repeticiones;
        }

        public Pareja getPareja() {
            return pareja;
        }

        public int getRepeticiones() {
            return repeticiones;
        }

        @Override
        public String toString() {
            return "ParejasRepetidas{" + "pareja=" + pareja + ", repeticiones=" + repeticiones + '}';
        }
    }

    public static PriorityQueue<ParejasRepetidas> parejasOrdenadas(Map<Pareja, Integer> participaciones){
        PriorityQueue<ParejasRepetidas> colaPrioridad = new PriorityQueue<>(Comparator.comparingInt(ParejasRepetidas::getRepeticiones).reversed());
        
        for(Map.Entry<Pareja, Integer> entry : participaciones.entrySet()){
            Pareja pareja = entry.getKey();
            int repeticiones = entry.getValue();
            ParejasRepetidas parejasRepetidas = new ParejasRepetidas(pareja, repeticiones);
            colaPrioridad.offer(parejasRepetidas);
        }
        
        return colaPrioridad;
    }
    
    public static void main(String[] args) {
        ArrayList<Persona> personas = new ArrayList<>();
        personas.addLast(new Persona("Azul", "Artista1"));
        personas.addLast(new Persona("Roja", "Artista2"));
        personas.addLast(new Persona("Roja", "Artista3"));
        
        ParejasPresentacion ejemplo = new ParejasPresentacion();

        for (Persona p : personas) {
            ejemplo.anadirPersona(p);
        }

        while (!ejemplo.colaParejas.isEmpty()) {
            System.out.println(ejemplo.colaParejas.poll());
        }

    }

}
