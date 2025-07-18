/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectog8;

/**
 *
 * @author sarab
 */
public class Estancia {
        private String fechaIngreso;
    private int numeroNoches;
    private int numeroHabitacion;
    private int piso;
    int dias;
    public Estancia(String fechaIngreso, int numeroNoches, int numeroHabitacion, int piso) {
        this.fechaIngreso = fechaIngreso;
        this.numeroNoches = numeroNoches;
        this.numeroHabitacion = numeroHabitacion;
        this.piso = piso;
    }
    
    public Estancia(int piso, int numeroHabitacion){
        this.piso = piso;
        this.numeroHabitacion = this.numeroHabitacion;
    }
    public Estancia(int dias){
        this.dias = dias;
    }

    public int getNumeroHabitacion() { return numeroHabitacion; }
    public int getPiso() { return piso; }
    
}
