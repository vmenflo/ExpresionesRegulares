/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ejemplosexpresionesregulares;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author victor
 */
public class EjemplosExpresionesRegulares {
    
//Trabajaremos con cuatro tipos de interfaces funcionales:
//Predicate
//function
//Consumer - no produce nada ni transforma como un sout
//Supplier

    public static void main(String[] args) {
//        Todos los archivos que están en la raíz del proyecto
//        son accesibles directamente con su nombre
        //Probamos el método
        List<String> lineasFichero;
        String fichero = "ssh_config";
        System.out.println("Leyendo el Fichero " + fichero);
        
        //Llamamos al método
        lineasFichero = leerFichero(fichero);
        lineasFichero.forEach(System.out::println);
        
        System.out.println("Mostrar número de palabras por líneas");
        lineasFichero.forEach(linea->{
            //Por cada línea en esa lista cuenta y hace un sout
            int numeroPalabras = contarPalabrasPorLinea(linea);
            System.out.println(linea + " -- > "+ numeroPalabras);
        });
        
        System.out.println("Probando el tercer método");
        lineasFichero.forEach(linea->{
            //Por cada línea en esa lista cuenta y hace un sout
            int numeroPalabras = contarPalabraConcretaPorLinea("is",linea);
            System.out.println(linea + " -- > "+ numeroPalabras);
        });
    }
    
    
    //Método para leer un fichero de texto plano
    //Usamos la clase files
    public static List<String> leerFichero(String nombreFichero){
        //Creamos una lista para almacenar la que nos devolvera ReadAllLines
        List<String> lista = new ArrayList<>();
        //Importante usar un try para controlar una posible excepcion
        try{
            lista = Files.readAllLines(Paths.get(nombreFichero));
        }catch(IOException ex){
            System.out.println("Error accediendo a " + nombreFichero);
        }
        return lista;
    }
    //Método para contar
    //El split corta según lo indiquemos y devuelve un array String
    //Para contar palabras, cortaremos por espacios
    public static int contarPalabrasPorLinea(String linea){
        //En Java para escapar la barra invertida hay que añadir otra
        //Barra adicional
        linea = linea.trim();
        String[] array = linea.split("\\s+");//El + es para que corte por uno o varios espacios
        return array.length;
    }
    //Método para contar cuantas veces se repite una palabra concreta por linea
    public static int contarPalabraConcretaPorLinea(String palabra, String linea){
        //Las barras \\b hacen que coja la palabra exacta
        //Si buscamos Juanito que no cuente Juan
        String regex = "\\b" + palabra + "\\b";
//        La clase Pattern crea un objeto con la empresion compilada o precesada
//        de la expresión regular. Permite crear un objeto Matcher
        Pattern patron = Pattern.compile(regex);
        //Matcher: se crea a partir del objeto Pattern
        //Y permite hacer operaciones usando el regex sobre el String
        //matches() - para ver si esta
        //find() - encontrar partes del string que cumple el patrón
        Matcher buscador = patron.matcher(linea);
        
//      Estamos involucrando 4 cosas: 2 String y 2 Objetos para poder usar 
//      la expresion regular sobre la linea que queramos utilizarla

        int contador=0;
        while(buscador.find()){
            contador++;
        }
        return contador;
    }
}

