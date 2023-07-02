package tienda;

import tienda.service.ProductoService;

public class Main {

    public static void main(String[] args) {
        ProductoService pS = new ProductoService();
        try {
            pS.menu();
        } catch (Exception e) {
            System.out.println("\u001b[31mError del sistema por \n" + e.getMessage());
        }
    }
    
}
