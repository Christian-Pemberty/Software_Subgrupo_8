package model;

import java.util.List;

/**
 * Clase que representa una venta realizada por un vendedor.
 * Contiene el ID del vendedor y la lista de productos vendidos en la venta.
 */


public class Sale {
    private long sellerId; // Identificador del vendedor que realizó la venta
    private List<ProductSold> productsSold; // Lista de productos vendidos en esta venta

    //Constructor de la clase Sale.
     
    public Sale(long sellerId, List<ProductSold> productsSold) {
        this.sellerId = sellerId;
        this.productsSold = productsSold;
    }

    public long getSellerId() {
        return sellerId;
    }

    public List<ProductSold> getProductsSold() {
        return productsSold;
    }
}
