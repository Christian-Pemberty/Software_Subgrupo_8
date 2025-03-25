

/*
 * _____       ______                      ______
  / ___/____  / __/ /__      ______ ______/ ____/
  \__ \/ __ \/ /_/ __/ | /| / / __ `/ ___/ __/   
 ___/ / /_/ / __/ /_ | |/ |/ / /_/ / /  / /___   
/____/\____/_/  \__/ |__/|__/\__,_/_/  /_____/ 

 * Subgrupo_8
 * Integrantes: 
 * CHRISTIAN CAMILO PEMBERTY VILLEGAS
 * JEISSON LEANDRO GUERRERO MOLANO
 * LUIS ANGELO HERNANDEZ BLANCO
 * Desde este Main se desarrollo un menu de opciones
 * para la construcción y generación de la data y los informes
 */
package Main;

import controller.FileReader;
import controller.ReportGenerator;
import controller.GenerateInfoFiles;
import model.Seller;
import model.ProductSold;
import model.Sale;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
	
    // Códigos ANSI para colores en la consola
    private static final String RESET = "\u001B[0m";    // Resetear color
    private static final String GREEN = "\u001B[32m";   // Verde (validaciones OK)
    private static final String RED = "\u001B[31m";     // Rojo (errores)
    private static final String BLUE = "\u001B[94m";    // Azul claro (funcionamiento normal)

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(GREEN + "    _____       ______                      ______  " + RESET);
        System.out.println(GREEN + "   / ___/____  / __/ /__      ______ ______/ ____/  " + RESET);
        System.out.println(GREEN + "   \\__ \\/ __ \\/ /_/ __/ | /| / / __ `/ ___/ __/  " + RESET);
        System.out.println(GREEN + "  _  / / /_/ / __/ /_ | |/ |/ / /_/ / /  / /___     " + RESET);
        System.out.println(GREEN + " /____/\\____/_/  \\__/ |__/|__/\\__,_/_/  /_____/  " + RESET);

             		
        while (true) {
            System.out.println("\nSeleccione una opción:");
            System.out.println("");
            System.out.println("1️. Crear Data del Software");
            System.out.println("2️. Generar Informes");
            System.out.println("3️. Salir");
            System.out.println("");
            System.out.print("Opción Seleccionada: ");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println(BLUE + "Creando Data del Software . . ." + RESET);
                    GenerateInfoFiles.main(new String[]{});  // Llamamos al main de GenerateInfoFiles
                    System.out.println(GREEN + "✅ Data del Software generada correctamente." + RESET);
                    break;

                case 2:
                    System.out.println(BLUE + "Generando Informes . . ." + RESET);
                    generarInformes();
                    break;

                case 3:
                    System.out.println(GREEN + "Saliendo del sistema . . ." + RESET);
                    scanner.close();
                    return;

                default:
                    System.out.println(RED + " X Opción inválida. Intente nuevamente." + RESET);
            }
        }
    }

    private static void generarInformes() {
        try {
            List<Seller> sellers = FileReader.readSellers();
            List<ProductSold> products = FileReader.readProducts();
            List<Sale> sales = FileReader.readSales(products);

            ReportGenerator.generateSellerReport(sellers, sales);
            ReportGenerator.generateProductReport(sales);

            System.out.println(GREEN + "✅ Reportes generados correctamente." + RESET);
        } catch (IOException e) {
            System.out.println(RED + "X Error al procesar los archivos: " + e.getMessage() + RESET);
        }
    }
         
}


        
    



