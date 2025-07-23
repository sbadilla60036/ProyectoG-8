/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyectog8;

/**
 *
 * @author Grupo #8
 */
public class ProyectoG8 {

    public static void main(String[] args) {
        menuPrincipal();
    }
        public static void menuPrincipal(){
    int optmenu = 0;

    do {
        String opcionEntrada = JOptionPane.showInputDialog("""
                ---- [Sistema de Reservación de Habitaciones] ----
                1- Hacer Reservacion.
                2- Cancelar Reservacion.
                3- Facturación.
                4- Mostrar Datos de Reservacion.
                5- Salir.""");

        if (opcionEntrada == null) {
            // Opcion de cuando el usuario cancele la entrada
            JOptionPane.showMessageDialog(null, "Saliendo del sistema...");
            System.exit(0);
        }

        try {
            optmenu = Integer.parseInt(opcionEntrada);

            switch (optmenu) {
                case 1:
                    hacerReservacion();
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null, "Has seleccionado la opcion Cancelar Reservacion");
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "Has seleccionado la opcion Facturacion");
                    break;
                case 4:
                    mostrarDatosReservacion();
                    break;
                case 5:
                    JOptionPane.showMessageDialog(null, "Gracias por usar el Sistema de Reservacion de Habitaciones");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opcion del menu no valida.");
            }
        //Metodo para que si se digiten letras aparezca un mensaje de aviso.
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entrada invalida. Por favor, ingrese un numero.");
        }

    } while (optmenu != 5);
}
    
    //Arreglos fijos
    static final int pisos = 5;
    static final int habitacionesPorPiso = 10;
    
    static boolean[][] habitacionesOcupadas = new boolean[pisos][habitacionesPorPiso];
    static Usuario[][] usuarios = new Usuario[pisos][habitacionesPorPiso];
    static Estancia[][] estancias = new Estancia[pisos][habitacionesPorPiso];

    //Opciones del menu
    public static void hacerReservacion() {
    String nombre = JOptionPane.showInputDialog("Ingrese su nombre:");
    String cedula = JOptionPane.showInputDialog("Ingrese su cedula:");
    String correo = JOptionPane.showInputDialog("Ingrese su correo electronico:");
    String telefono = JOptionPane.showInputDialog("Ingrese su numero de telefono:");

    int piso, habitacion;

    try {
        piso = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de piso (1 - 5):")) - 1;
        habitacion = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de habitacion (1 - 10):")) - 1;

        if (piso < 0 || piso >= pisos || habitacion < 0 || habitacion >= habitacionesPorPiso) {
            JOptionPane.showMessageDialog(null, "Piso o habitacion fuera de rango.");
            return;
        }

        if (habitacionesOcupadas[piso][habitacion]) {
            JOptionPane.showMessageDialog(null, "La habitación ya esta ocupada.");
        } else {
            int dias = Integer.parseInt(JOptionPane.showInputDialog("¿Cuántos dias se hospedará?"));

            int confirmar = JOptionPane.showConfirmDialog(null,
                    "¿Desea confirmar la reservación?\n\n" +
                    "Nombre: " + nombre +
                    "\nCedula: " + cedula +
                    "\nCorreo: " + correo +
                    "\nTelefono: " + telefono +
                    "\nPiso: " + (piso + 1) +
                    "\nHabitacion: " + (habitacion + 1) +
                    "\nDias: " + dias,
                    "Confirmar Reservacion",
                    JOptionPane.YES_NO_OPTION);

            if (confirmar == JOptionPane.YES_OPTION) {
                habitacionesOcupadas[piso][habitacion] = true;
                usuarios[piso][habitacion] = new Usuario(nombre, cedula, correo, telefono);
                estancias[piso][habitacion] = new Estancia(dias);

                JOptionPane.showMessageDialog(null, "Reservación realizada con exito.");
            } else {
                JOptionPane.showMessageDialog(null, "Reservacion cancelada por el usuario.");
            }
        }

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Entrada no válida. Intente de nuevo.");
    }
}

    public static void mostrarDatosReservacion() {
    System.out.println("----- ESTADO DE RESERVACIONES -----");
    System.out.println("         Habitaciones (1-10)");
    System.out.print("Piso |");


    for (int h = 0; h < habitacionesPorPiso; h++) {
        System.out.printf(" %3d ", h + 1);
    }
    System.out.println();
    System.out.println("----------------------------------------------------------");


    for (int piso = pisos - 1; piso >= 0; piso--) {
        System.out.printf(" %2d  |", piso + 1);
        for (int habitacion = 0; habitacion < habitacionesPorPiso; habitacion++) {
            if (habitacionesOcupadas[piso][habitacion]) {
                System.out.print(" OCC ");
            } else {
                System.out.print(" --- ");
            }
        }
        System.out.println();
    }

    System.out.println("OCC = Ocupada | --- = Libre\n");


    boolean hayReservas = false;
    String lectura = "";

    for (int piso = 0; piso < pisos; piso++) {
        for (int habitacion = 0; habitacion < habitacionesPorPiso; habitacion++) {
            if (habitacionesOcupadas[piso][habitacion]) {
                Usuario u = usuarios[piso][habitacion];
                Estancia e = estancias[piso][habitacion];

                lectura = lectura + "Piso: " + (piso + 1) + " | Habitacion: " + (habitacion + 1) + "\n";
                lectura = lectura + "Nombre: " + u.nombre + "\n";
                lectura = lectura + "Cedula: " + u.cedula + "\n";
                lectura = lectura + "Correo: " + u.correo + "\n";
                lectura = lectura + "Telefono: " + u.telefono + "\n";
                lectura = lectura + "Dias de estancia: " + e.dias + "\n\n";

                hayReservas = true;
            }
        }
    }

    if (hayReservas) {
        JOptionPane.showMessageDialog(null, lectura);
    } else {
        JOptionPane.showMessageDialog(null, "No hay reservaciones registradas.");
    }
}
    

}        

