/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ordenamiento_Generico;

/**
 *
 * @author edith
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Integer[] intVector={101,99,12,19,21,111,345,25,77,81};
        Integer[] result= AlgoritmoQuickSort.ordenar(intVector);
        System.out.println("Showing sorted data of type int vector");
        for(int r:result)
        System.out.println(r);
        
        Float[] floatVector = { 19.1f, 456.6f, 23.45f, 12.34f, 11.11f, 354.55f, 78.45f, 28.33f, 45.99f, 108.88f };
        Float [] resultF = AlgoritmoQuickSort.ordenar (floatVector);
        System.out.println("Showing sorted data of type float vector");
        for(float r:resultF)
        System.out.println(r);
       
        /*
        String [] strVector = {"zz", "aa", "cc", "hh", "bb", "ee", "ll"};// Declaracion no aceptada 
        String [] resultS = AlgoritmoQuickSort.ordenar(strVector);
        
        */
    }
    
}
