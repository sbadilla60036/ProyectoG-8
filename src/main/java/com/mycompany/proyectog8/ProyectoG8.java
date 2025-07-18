/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyectog8;

/**
 *
 * @author sarab
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
                1- Hacer Reservación.
                2- Cancelar Reservación.
                3- Facturación.
                4- Mostrar Datos de Reservación.
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
                    // cancelarReservacion(); falta de agregar
                    break;
                case 3:
                    // facturacion(); falta de agregar
                    break;
                case 4:
                    mostrarDatosReservacion();
                    break;
                case 5:
                    JOptionPane.showMessageDialog(null, "Gracias por usar el Sistema de Reservación de Habitaciones");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción del menú no válida.");
            }
        //Metodo para que si se digiten letras aparezca un mensaje de aviso.
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entrada inválida. Por favor, ingrese un número.");
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
    String cedula = JOptionPane.showInputDialog("Ingrese su cédula:");
    String correo = JOptionPane.showInputDialog("Ingrese su correo electrónico:");
    String telefono = JOptionPane.showInputDialog("Ingrese su número de teléfono:");

    int piso, habitacion;

    try {
        piso = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de piso (1 - 5):")) - 1;
        habitacion = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de habitación (1 - 10):")) - 1;

        if (piso < 0 || piso >= pisos || habitacion < 0 || habitacion >= habitacionesPorPiso) {
            JOptionPane.showMessageDialog(null, "Piso o habitación fuera de rango.");
            return;
        }

        if (habitacionesOcupadas[piso][habitacion]) {
            JOptionPane.showMessageDialog(null, "La habitación ya está ocupada.");
        } else {
            int dias = Integer.parseInt(JOptionPane.showInputDialog("¿Cuántos días se hospedará?"));

            int confirmar = JOptionPane.showConfirmDialog(null,
                    "¿Desea confirmar la reservación?\n\n" +
                    "Nombre: " + nombre +
                    "\nCédula: " + cedula +
                    "\nCorreo: " + correo +
                    "\nTeléfono: " + telefono +
                    "\nPiso: " + (piso + 1) +
                    "\nHabitación: " + (habitacion + 1) +
                    "\nDías: " + dias,
                    "Confirmar Reservación",
                    JOptionPane.YES_NO_OPTION);

            if (confirmar == JOptionPane.YES_OPTION) {
                habitacionesOcupadas[piso][habitacion] = true;
                usuarios[piso][habitacion] = new Usuario(nombre, cedula, correo, telefono);
                estancias[piso][habitacion] = new Estancia(dias);

                JOptionPane.showMessageDialog(null, "Reservación realizada con éxito.");
            } else {
                JOptionPane.showMessageDialog(null, "Reservación cancelada por el usuario.");
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

                lectura = lectura + "Piso: " + (piso + 1) + " | Habitación: " + (habitacion + 1) + "\n";
                lectura = lectura + "Nombre: " + u.nombre + "\n";
                lectura = lectura + "Cédula: " + u.cedula + "\n";
                lectura = lectura + "Correo: " + u.correo + "\n";
                lectura = lectura + "Teléfono: " + u.telefono + "\n";
                lectura = lectura + "Días de estancia: " + e.dias + "\n\n";

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

