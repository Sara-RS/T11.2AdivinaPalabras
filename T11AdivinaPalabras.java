
package com.mycompany.t11adivinapalabras;

import java.io.*;
import java.util.*;

public class T11AdivinaPalabras {
    public static void main(String[] args) {

        HashMap<Integer, String> palabras=LeerDeArchivo("palabras.game");
           if (palabras.isEmpty()) {
               System.out.println("El archivo está vacio");
               return;
           } 
           int pr = new Random().nextInt(palabras.size());
           String palabraAdivinar = palabras.get(pr).toUpperCase();
           char[] progreso = new char[palabraAdivinar.length()];
           Arrays.fill(progreso,'_'); 

           Scanner leer = new Scanner(System.in);
           int intentos = 0;
           boolean palabraCompleta = false;
           System.out.println("¿Listo para adivinar la palabra? (sí/no)");
           String respuesta = leer.nextLine().toLowerCase().trim();

           if (respuesta.equals("no")) {
               System.out.println("El juego ha terminado");
               return;
           }
           
           while (!palabraCompleta) {
               System.out.println("Palabra a adivinar: " + new String(progreso));
               System.out.print("Ingresa una letra: ");
               char letra = leer.nextLine().toUpperCase().charAt(0);
               intentos++;
               boolean letraEncontrada = false;
               for (int i= 0; i<palabraAdivinar.length(); i++) {
                   if (palabraAdivinar.charAt(i)== letra) {
                       progreso[i]= letra;
                       letraEncontrada = true;
                   }
               }
               if (!letraEncontrada) {
                   System.out.println("La letra '" +letra+ "' no está en la palabra");
               }
               palabraCompleta = new String(progreso).equals(palabraAdivinar);
           }
           System.out.println("¡¡Felicidades!! Adivinaste la palabra: "+palabraAdivinar);
           System.out.println("Número de intentos: "+intentos);
       }

       private static HashMap<Integer, String> LeerDeArchivo(String nombreArchivo) {
           HashMap<Integer, String> palabras =new HashMap<>();
           try {
               BufferedReader br =new BufferedReader(new FileReader(nombreArchivo));
               String linea;
               int x= 0;
               while ((linea= br.readLine()) !=null) {
                   String[] palabrasArray= linea.split(",");
                   for (String palabra: palabrasArray) {
                       palabras.put(x, palabra);
                       x++;
                   }
               }
           } catch (IOException e) {
               System.out.println("El archivo 'palabras.game' no ha sido encontrado.");
           }
           return palabras;
       }
}
