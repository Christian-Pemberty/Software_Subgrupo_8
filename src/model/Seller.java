package model;

/**
 * Clase que representa a un vendedor en el sistema.
 * Contiene información personal como el tipo de documento, ID, nombres y apellidos.
 */

public class Seller {
    private String documentType;
    private long id;
    private String firstName;
    private String lastName;

    
    public Seller(String documentType, long id, String firstName, String lastName) {
        this.documentType = documentType;
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public long getId() {
        return id;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getDocumentType() {
        return documentType;
    }
}
