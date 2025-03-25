package controller;

import model.Seller;
import model.Sale;
import model.ProductSold;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

// Clase que genera los reportes del software
public class ReportGenerator {
    private static final String REPORTS_FOLDER = "data/Reportes/";

    // Genera el reporte de ventas por vendedor
    public static void generateSellerReport(List<Seller> sellers, List<Sale> sales) throws IOException {
        new File(REPORTS_FOLDER).mkdirs();
        FileWriter writer = new FileWriter(REPORTS_FOLDER + "reporte_vendedores.csv");
        writer.write("NombreVendedor;TotalRecaudado\n");

        // Mapa para almacenar el total recaudado por cada vendedor (usando su ID como clave)
        Map<Long, Double> salesTotal = new HashMap<>();

        for (Sale sale : sales) {
            double total = 0;
            for (ProductSold product : sale.getProductsSold()) {
                total += product.getQuantity() * product.getPrice();
            }
            // Acumular ventas por vendedor
            salesTotal.put(sale.getSellerId(), salesTotal.getOrDefault(sale.getSellerId(), 0.0) + total);
        }

        // Ordenar por total recaudado (de mayor a menor)
        List<Map.Entry<Long, Double>> sortedSales = new ArrayList<>(salesTotal.entrySet());
        sortedSales.sort((a, b) -> Double.compare(b.getValue(), a.getValue()));

        // Escribir en el CSV
        for (Map.Entry<Long, Double> entry : sortedSales) {
            long sellerId = entry.getKey();
            double total = entry.getValue();

            // Buscar el vendedor por ID para obtener su nombre
            Optional<Seller> seller = sellers.stream()
                    .filter(s -> s.getId() == sellerId)
                    .findFirst();

            if (seller.isPresent()) {
                writer.write(seller.get().getFullName() + ";$" + String.format("%.2f", total) + "\n");
            } else {
                writer.write("Desconocido;$" + String.format("%.2f", total) + "\n");
            }
        }
        writer.close();
        System.out.println("✅ Reporte de vendedores generado correctamente.");
    }

    // Genera el reporte de productos vendidos
    public static void generateProductReport(List<Sale> sales) throws IOException {
        new File(REPORTS_FOLDER).mkdirs();
        FileWriter writer = new FileWriter(REPORTS_FOLDER + "reporte_productos.csv");
        writer.write("NombreProducto;PrecioPorUnidad;CantidadVendida\n");

        // Mapa para contar cantidad de productos vendidos
        Map<String, ProductSold> productMap = new HashMap<>();

        for (Sale sale : sales) {
            for (ProductSold product : sale.getProductsSold()) {
                productMap.putIfAbsent(product.getId(), new ProductSold(product.getId(), product.getName(), product.getPrice(), 0));
                productMap.get(product.getId()).addQuantity(product.getQuantity());
            }
        }

        // Ordenar productos vendidos de mayor a menor cantidad
        List<ProductSold> sortedProducts = new ArrayList<>(productMap.values());
        sortedProducts.sort((a, b) -> Integer.compare(b.getQuantity(), a.getQuantity()));

        // Escribir en el CSV
        for (ProductSold product : sortedProducts) {
            writer.write(product.getName() + ";$" + String.format("%.2f", product.getPrice()) + ";" + product.getQuantity() + "\n");
        }
        writer.close();
        System.out.println("✅ Reporte de productos generado correctamente.");
    }
}
