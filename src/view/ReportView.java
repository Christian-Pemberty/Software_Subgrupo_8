package view;

import java.util.List;
import java.util.Map;

//Vista de archivos desde la consola no se desarrolla completamente
public class ReportView {
    public static void showReports(Map<String, List<String>> data) {
        System.out.println("\n === Reporte de Datos ===");
        data.forEach((category, files) -> {
            System.out.println("\n " + category);
            if (files.isEmpty()) {
                System.out.println("No hay archivos en esta categoría.");
            } else {
                files.forEach(System.out::println);
            }
        });
    }
}
