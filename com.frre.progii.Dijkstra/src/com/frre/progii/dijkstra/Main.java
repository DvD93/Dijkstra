package com.frre.progii.dijkstra;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 30/11/15.
 */
public class   Main {
    Nodo nodoA = new Nodo("A");
    Nodo nodoB = new Nodo("B");
    Nodo nodoC = new Nodo("C");
    Nodo nodoD = new Nodo("D");
    Nodo nodoE = new Nodo("E");
    Nodo nodoF = new Nodo("F");
    Nodo nodoG = new Nodo("G");

    Conexion conexion1 = new Conexion(1,nodoA,nodoB);
    Conexion conexion2 = new Conexion(4,nodoA,nodoC);
    Conexion conexion3 = new Conexion(6,nodoB,nodoE);
    Conexion conexion4 = new Conexion(3,nodoB,nodoD);
    Conexion conexion5 = new Conexion(2,nodoC,nodoD);
    Conexion conexion6 = new Conexion(5,nodoC,nodoF);
    Conexion conexion7 = new Conexion(4,nodoD,nodoF);
    Conexion conexion8 = new Conexion(2,nodoD,nodoE);
    Conexion conexion9 = new Conexion(2,nodoE,nodoF);
    Conexion conexion10 = new Conexion(7,nodoE,nodoG);
    Conexion conexion11 = new Conexion(6,nodoF,nodoG);

    Camino caminoOptimo = null;




    public static void main(String[] args){
        Main main = new Main();
        main.encontrarCamino(main.nodoA, main.nodoG, null);
        System.out.println("Camino optimo" + main.caminoOptimo.conexiones);
    }

    public void encontrarCamino(Nodo de, Nodo a, Camino camino){
        for (Conexion conexion : de.conexiones){
            Camino caminoNuevo = new Camino();
            if (camino != null){
                if (camino.conexiones.contains(conexion)){
                    continue;
                }
             caminoNuevo.conexiones.addAll(camino.conexiones);
                caminoNuevo.pesoCamino += camino.pesoCamino;
            }
            caminoNuevo.conexiones.add(conexion);
            caminoNuevo.pesoCamino += conexion.peso;
            if (conexion.primerNodo.nombre.equals(a.nombre) || conexion.segundoNodo.nombre.equals(a.nombre)){
                //llegamos al final
                if (caminoOptimo == null || caminoOptimo.pesoCamino > caminoNuevo.pesoCamino){
                    caminoOptimo = caminoNuevo;
                }
                return;
            }
            Nodo nuevoDe = conexion.primerNodo.nombre.equals(de.nombre) ? conexion.segundoNodo : conexion.primerNodo;
            encontrarCamino(nuevoDe, a, caminoNuevo);
        }
    }
}

class Nodo{
    String nombre;
    List<Conexion> conexiones = new ArrayList<Conexion>() {};
    Nodo (String nombre){
        this.nombre = nombre;
    }
    @Override
    public String toString(){
        return nombre;
    }
}
class Conexion{
    int peso;
    Nodo primerNodo;
    Nodo segundoNodo;

    Conexion(int peso, Nodo primerNodo, Nodo segundoNodo){
        this.peso = peso;
        this.primerNodo = primerNodo;
        this.segundoNodo = segundoNodo;
        primerNodo.conexiones.add(this);
        segundoNodo.conexiones.add(this);
    }
    @Override
    public String toString(){
        return primerNodo + "a" + segundoNodo;
    }
}
class Camino{
    List<Conexion> conexiones = new ArrayList<Conexion>() {};
    int pesoCamino;
}