package app;

import data.Persistencia;
import views.VentanaPrincipal;

public class Program {
    public static void main(String[] args) {
        // 1. Inicializamos datos básicos (ahora arranca sin vehículos, vacío)
        Persistencia.inicializar();
        
        // 2. Lanzamos el Menú Principal
        VentanaPrincipal menu = new VentanaPrincipal();
        menu.setVisible(true);
    }
}