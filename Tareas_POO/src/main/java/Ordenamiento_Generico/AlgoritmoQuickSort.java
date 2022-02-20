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
public class AlgoritmoQuickSort {
    
    
    public static<E extends Number> E[] ordenar(E vector[])
    {
        quicksort(vector,0, vector.length-1);
        return vector;
        
    }
    public static <E extends Number> void quicksort(E  A[], int izq, int der) {

        E pivote=A[izq]; // tomamos primer elemento como pivote
        
        int i=izq;         // i realiza la búsqueda de izquierda a derecha
        int j=der;         // j realiza la búsqueda de derecha a izquierda
        E aux;
        

        while(i < j){                          // mientras no se crucen las búsquedas
            while(A[i].doubleValue() <= pivote.doubleValue() && i < j) i++; // busca elemento mayor que pivote
            while(A[j].doubleValue() > pivote.doubleValue()) j--;           // busca elemento menor que pivote
            if (i < j) {                        // si no se han cruzado
                aux= A[i];                      // los intercambia
                A[i]=A[j];
                A[j]=aux;
            }
        }

        A[izq]=A[j];      // se coloca el pivote en su lugar de forma que tendremos
        A[j]=pivote;      // los menores a su izquierda y los mayores a su derecha

        if(izq < j-1)
            quicksort(A,izq,j-1);          // ordenamos subarray izquierdo
        if(j+1 < der)
            quicksort(A,j+1,der);          // ordenamos subarray derecho

    }
    
}
