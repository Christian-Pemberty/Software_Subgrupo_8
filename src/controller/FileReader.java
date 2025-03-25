package controller;

import model.ProductSold;
import model.Sale;
import model.Seller;

import java.io.*;
import java.util.*;

//Clase para leer archivos de datos del sistema, incluyendo vendedores, productos y ventas.
public class FileReader {
    private static final String SELLERS_FILE = "data/Datos del vendedor/vendedores.txt";
    private static final String PRODUCTS_FILE = "data/Listado de Productos/productos.txt";
    private static final String SALES_FILE = "data/Ventas/ventas.txt";

    // Lee los vendedores desde el archivo y los almacena en una lista.
    public static List<Seller> readSellers() throws IOException {
        List<Seller> sellers = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new java.io.FileReader(SELLERS_FILE));
        String line;

        reader.readLine(); // Saltar encabezado

        while ((line = reader.readLine()) != null) {
            String[] data = line.split(";");
            if (data.length == 4) {
                String type = data[0];
                long id = Long.parseLong(data[1]);
                String firstName = data[2];
                String lastName = data[3];
                sellers.add(new Seller(type, id, firstName, lastName));
            }
        }
        reader.close();
        return sellers;
    }
    
    //Lee los productos desde el archivo y los almacena en una lista.
    public static List<ProductSold> readProducts() throws IOException {
        List<ProductSold> products = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new java.io.FileReader(PRODUCTS_FILE));
        String line;

        reader.readLine(); // Saltar encabezado

        while ((line = reader.readLine()) != null) {
            String[] data = line.split(";");
            if (data.length == 3) {
                String productId = data[0];
                String name = data[1];
                double price = Double.parseDouble(data[2].replace(",", "."));
                products.add(new ProductSold(productId, name, price, 0));
            }
        }
        reader.close();
        return products;
    }
    
    //Lee las ventas desde el archivo y las almacena en una lista.
    public static List<Sale> readSales(List<ProductSold> products) throws IOException {
        List<Sale> sales = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new java.io.FileReader(SALES_FILE));
        String line;

        reader.readLine(); // Saltar encabezado

        while ((line = reader.readLine()) != null) {
            String[] sellerInfo = line.split(";");
            if (sellerInfo.length == 2) {
                long sellerId = Long.parseLong(sellerInfo[1]);
                List<ProductSold> productsSold = new ArrayList<>();

                while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
                    String[] productInfo = line.split(";");
                    if (productInfo.length == 2) {
                        String productId = productInfo[0];
                        int quantity = Integer.parseInt(productInfo[1]);

                        // Buscar el producto en la lista de productos para obtener su precio y nombre
                        ProductSold product = products.stream()
                                .filter(p -> p.getId().equals(productId))
                                .findFirst()
                                .orElse(null);

                        if (product != null) {
                            productsSold.add(new ProductSold(productId, product.getName(), product.getPrice(), quantity));
                        }
                    }
                }
                sales.add(new Sale(sellerId, productsSold));
            }
        }
        reader.close();
        return sales;
    }
}
