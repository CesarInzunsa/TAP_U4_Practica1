/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cesar
 */
public class Productos {
    int idProductos;
    String descripcion;
    float precio;
    int existencia;
    
    public Productos(String descripcion, float precio, int existencia){
        this.descripcion = descripcion;
        this.precio = precio;
        this.existencia = existencia;
    }
    
    public Productos(){}
            
}
