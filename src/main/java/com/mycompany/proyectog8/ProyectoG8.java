/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyectog8;

import javax.swing.JOptionPane;

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
                ---- [Sistema de Reservacion de Habitaciones] ----
                1- Hacer Reservacion.
                2- Cancelar Reservacion.
                3- Facturacion.
                4- Mostrar Datos de Reservacion.
                5- Salir.""");

        if (opcionEntrada == null) {
            JOptionPane.showMessageDialog(null, "Saliendo del sistema...");
            System.exit(0);
        }

        try {
            optmenu = Integer.parseInt(opcionEntrada);

            switch (optmenu) {
                case 1:
                      JOptionPane.showMessageDialog(null, "Has seleccionado la opcion Hacer Reservacion");
                    hacerReservacion();
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null, "Has seleccionado la opcion Cancelar Reservacion");
                    cancelarReservacion()
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "Has seleccionado la opcion Facturacion");
                    facturacion();
                    break;
                case 4:
                    JOptionPane.showMessageDialog(null, "Has seleccionado la opcion Mostrar datos de Reservacion");
                    mostrarDatosReservacion();
                    break;
                case 5:
                    JOptionPane.showMessageDialog(null, "Gracias por usar el Sistema de Reservación de Habitaciones");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opcion del menu no valida.");
            }
        
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entrada invalida. Por favor, ingrese un numero.");
        }

    } while (optmenu != 5);
}
    
   
    static final int pisos = 5;
    static final int habitacionesPorPiso = 10;
    
    static boolean[][] habitacionesOcupadas = new boolean[pisos][habitacionesPorPiso];
    static Usuario[][] usuarios = new Usuario[pisos][habitacionesPorPiso];
    static Estancia[][] estancias = new Estancia[pisos][habitacionesPorPiso];

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
            JOptionPane.showMessageDialog(null, "La habitacion ya esta ocupada.");
        } else {
            int dias = Integer.parseInt(JOptionPane.showInputDialog("¿Cuántos dias se hospedara?"));

            int confirmar = JOptionPane.showConfirmDialog(null,
                    "¿Desea confirmar la reservacion?\n\n" +
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

                JOptionPane.showMessageDialog(null, "Reservacion realizada con exito.");
            } else {
                JOptionPane.showMessageDialog(null, "Reservacion cancelada por el usuario.");
            }
        }

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Entrada no valida. Intente de nuevo.");
    }
}
    
    public static void cancelarReservacion() {
        try {

            String inputPiso = JOptionPane.showInputDialog("Ingrese el número de piso (1 - 5):");
            int piso = Integer.parseInt(inputPiso) - 1; 


            String inputHab = JOptionPane.showInputDialog("Ingrese el número de habitación (1 - 10):");
            int habitacion = Integer.parseInt(inputHab) - 1; 


            if (piso < 0 || piso >= pisos || habitacion < 0 || habitacion >= habitacionesPorPiso) {
                JOptionPane.showMessageDialog(null, "Error: Piso o habitación fuera de rango.");
                return;
            }


            if (!habitacionesOcupadas[piso][habitacion]) {
                JOptionPane.showMessageDialog(null, "La habitación no está reservada.");
                return;
            }


            Usuario usuario = usuarios[piso][habitacion];
            Estancia estancia = estancias[piso][habitacion];

            String mensaje = "Datos de la reservación:\n\n" +
                             "Nombre: " + usuario.nombre + "\n" +
                             "Cédula: " + usuario.cedula + "\n" +
                             "Correo: " + usuario.correo + "\n" +
                             "Teléfono: " + usuario.telefono + "\n" +
                             "Piso: " + (piso + 1) + "\n" +
                             "Habitación: " + (habitacion + 1) + "\n" +
                             "Días de estancia: " + estancia.dias + "\n\n" +
                             "¿Desea cancelar esta reservación?";

            int confirmar = JOptionPane.showConfirmDialog(null, mensaje, "Confirmar cancelación", JOptionPane.YES_NO_OPTION);

            if (confirmar == JOptionPane.YES_OPTION) {
                // Liberar la habitación
                habitacionesOcupadas[piso][habitacion] = false;
                usuarios[piso][habitacion] = null;
                estancias[piso][habitacion] = null;

                JOptionPane.showMessageDialog(null, "Reservación cancelada con éxito.");
            } else {
                JOptionPane.showMessageDialog(null, "Usuario salio de la opcion.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entrada inválida. Debe ingresar números.");
        }
    }
    
    public static void facturacion() {
        try {
            int piso = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de piso a facturar (1 - 5):")) - 1;
            int habitacion = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de habitacion a facturar (1 - 10):")) - 1;

            if (piso < 0 || piso >= pisos || habitacion < 0 || habitacion >= habitacionesPorPiso) {
                JOptionPane.showMessageDialog(null, "Piso o habitacion fuera de rango.");
                return;
            }

            if (!habitacionesOcupadas[piso][habitacion]) {
                JOptionPane.showMessageDialog(null, "La habitacion seleccionada no tiene una reservacion activa.");
                return;
            }

            Usuario u = usuarios[piso][habitacion];
            Estancia e = estancias[piso][habitacion];

            int dias = e.dias; 
            if (dias <= 0) {
                JOptionPane.showMessageDialog(null, "No hay dias de estancia registrados para esta reservacion.");
                return;
            }

            double IVA = 0.13;       
            double SERVICIO = 0.10;  

            double TARIFA_BASE = 45000.0;
            double TARIFA_NOCHE = tarifaPorNoche(dias);

            double subtotal = dias * TARIFA_NOCHE;
            double impuesto = subtotal * IVA;
            double servicio = subtotal * SERVICIO;
            double total = subtotal + impuesto + servicio;

            double subtotalBase = dias * TARIFA_BASE;
            double ahorro = Math.max(0, subtotalBase - subtotal);

            String factura = "----- FACTURA -----\n" +
                    "Cliente: " + u.nombre + "\n" +
                    "Cedula: " + u.cedula + "\n" +
                    "Correo: " + u.correo + "\n" +
                    "Telefono: " + u.telefono + "\n" +
                    "Piso: " + (piso + 1) + " | Habitacion: " + (habitacion + 1) + "\n" +
                    "Noches: " + dias + " x " + moneda(TARIFA_NOCHE) + " (tramo: " + tramoTexto(dias) + ")\n" +
                    (ahorro > 0 ? "Ahorro por tramo vs base (" + moneda(TARIFA_BASE) + "/noche): " + moneda(ahorro) + "\n" : "") +
                    "\n" +
                    "Subtotal: " + moneda(subtotal) + "\n" +
                    "Impuesto (13%): " + moneda(impuesto) + "\n" +
                    "Servicio (10%): " + moneda(servicio) + "\n" +
                    "--------------------------\n" +
                    "TOTAL: " + moneda(total) + "\n\n" +
                    "¿Desea confirmar el cobro?";

            int confirma = JOptionPane.showConfirmDialog(null, factura, "Confirmar Facturacion", JOptionPane.YES_NO_OPTION);

            if (confirma == JOptionPane.YES_OPTION) {
                // Liberamos la habitación (checkout)
                habitacionesOcupadas[piso][habitacion] = false;
                usuarios[piso][habitacion] = null;
                estancias[piso][habitacion] = null;

                JOptionPane.showMessageDialog(null, "Pago registrado con exito.\nTotal cobrado: " + moneda(total) +
                        "\nGracias por preferirnos.");
            } else {
                JOptionPane.showMessageDialog(null, "Facturacion cancelada por el usuario.");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Entrada invalida. Por favor, ingrese numeros.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error en facturacion: " + ex.getMessage());
        }
    }

    private static double tarifaPorNoche(int noches) {
        if (noches <= 0) return 0;
        if (noches <= 2) return 45000.0;
        if (noches <= 6) return 43000.0;
        if (noches <= 13) return 40000.0;
        return 38000.0;
    }

    private static String tramoTexto(int noches) {
        if (noches <= 2) return "1-2 noches";
        if (noches <= 6) return "3-6 noches";
        if (noches <= 13) return "7-13 noches";
        return "14+ noches";
    }

    private static String moneda(double valor) {
        return "₡" + String.format("%,.2f", valor);
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
