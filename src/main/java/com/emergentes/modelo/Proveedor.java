
package com.emergentes.modelo;

public class Proveedor {
    private int id;
    private String nom_proveedor;
    private String nom_producto;
    private int prod_cantidad;
    private String fecha;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_proveedor() {
        return nom_proveedor;
    }

    public void setNom_proveedor(String nom_proveedor) {
        this.nom_proveedor = nom_proveedor;
    }

    public String getNom_producto() {
        return nom_producto;
    }

    public void setNom_producto(String nom_producto) {
        this.nom_producto = nom_producto;
    }

    public int getProd_cantidad() {
        return prod_cantidad;
    }

    public void setProd_cantidad(int prod_cantidad) {
        this.prod_cantidad = prod_cantidad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Proveedor{" + "id=" + id + ", nom_proveedor=" + nom_proveedor + ", nom_producto=" + nom_producto + ", prod_cantidad=" + prod_cantidad + ", fecha=" + fecha + '}';
    }

   
}
