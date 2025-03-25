package controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GenerateInfoFiles {
	//Ruta donde se construiran los .txt
    private static final String SELLERS_FILE = "data/Datos del vendedor/vendedores.txt";
    private static final String PRODUCTS_FILE = "data/Listado de Productos/productos.txt";
    private static final String SALES_FILE = "data/Ventas/ventas.txt";
    // Nombres, Apellidos y Productos del Software 
    private static final String[] NAMES = {"Christian", "Camilo", "Jeisson", "Leandro", "Luis", "Angelo", "Diego", "Laura", "Andrés", "Elena"};
    private static final String[] LASTNAMES = {"Pemberty", "Pemberty", "Guerrero", "Molano", "Hernandez", "Blanco", "Martínez", "García", "Hernández", "Torres"};
    private static final String[] PRODUCT_NAMES = {"Laptop", "Xbox", "Teclado", "Monitor", "Mouse", "Impresora", "Tablet", "Auriculares", "Altavoz", "TV"};

    // Método para generar el archivo de vendedores
    public static void createSalesManInfoFile(int salesmanCount) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(SELLERS_FILE));

        // Escribir encabezado
        writer.write("TipoDocumento;NúmeroDocumento;NombresVendedor;ApellidosVendedor");
        writer.newLine();

        Random random = new Random();

        for (int i = 0; i < salesmanCount; i++) {
            String documentType = "CC"; // Tipo de Documento
            long documentNumber = 10000000L + random.nextInt(90000000); // Número de documento aleatorio
            String firstName = NAMES[random.nextInt(NAMES.length)];
            String lastName = LASTNAMES[random.nextInt(LASTNAMES.length)];

            writer.write(documentType + ";" + documentNumber + ";" + firstName + ";" + lastName);
            writer.newLine();
        }
        writer.close();
        System.out.println("✅ Archivo de vendedores generado correctamente.");
    }

    // Método para generar el archivo de productos
    public static void createProductsFile(int productsCount) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(PRODUCTS_FILE));

        // Escribir encabezado
        writer.write("IDProducto;NombreProducto;PrecioPorUnidadProducto");
        writer.newLine();

        Random random = new Random();

        for (int i = 0; i < productsCount; i++) {
            String productId = "P" + (100 + i);
            String productName = PRODUCT_NAMES[random.nextInt(PRODUCT_NAMES.length)];
            double price = 100000 + (random.nextDouble() * (400000 - 100000)); // Precio entre $100.000 y $400.000 Rango para la prueba

            writer.write(productId + ";" + productName + ";" + String.format("%.2f", price).replace(",", "."));
            writer.newLine();
        }
        writer.close();
        System.out.println("✅ Archivo de productos generado correctamente.");
    }

    // Método para generar el archivo de ventas
    public static void createSalesMenFile(int randomSalesCount, String name, long id) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(SALES_FILE));

        // Escribir encabezado
        writer.write("TipoDocumentoVendedor;NúmeroDocumentoVendedor");
        writer.newLine();

        Random random = new Random();

        for (int i = 0; i < randomSalesCount; i++) {
            writer.write("CC;" + id);
            writer.newLine();

            int numProducts = 1 + random.nextInt(4); // Entre 1 y 4 productos por venta
            for (int j = 0; j < numProducts; j++) {
                String productId = "P" + (100 + random.nextInt(10)); // Producto aleatorio
                int quantity = 1 + random.nextInt(5); // Cantidad entre 1 y 5

                writer.write(productId + ";" + quantity);
                writer.newLine();
            }
        }
        writer.close();
        System.out.println("✅ Archivo de ventas generado correctamente.");
    }

    // Método principal para generar todos los archivos
    public static void main(String[] args) {
        try {
            createSalesManInfoFile(5);  // Generar 5 vendedores
            createProductsFile(10);     // Generar 10 productos
            createSalesMenFile(5, "Christian Pemberty", 12345678L); // Generar 5 ventas para un vendedor
            System.out.println("✅ Todos los archivos han sido generados exitosamente.");
        } catch (IOException e) {
            System.out.println("❌ Error al generar los archivos: " + e.getMessage());
        }
    }
}
