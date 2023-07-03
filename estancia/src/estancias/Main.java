package estancias;
import estancias.service.CasaService;
import estancias.service.FamiliaService;

public class Main {
    public static void main(String[] args) {
        FamiliaService fS = new FamiliaService();
        CasaService cS = new CasaService();
        try {
            System.out.println("\u001b[32mIntentar Traer 3 o mas hijos con edad maxima inferior a 10 \n");
        fS.listarFamiliaPorHijoEdad();
            
        } catch (Exception e) {
            System.out.println("\u001b[31mError del sistema por \n" + e.getMessage());
        }
    
        try {
            System.out.println("\u001b[32mIntentar Traer Casas disponibles en Reino Unido\n");
        cS.listarCasasPorFechaPais();
            
        } catch (Exception e) {
            System.out.println("\u001b[31mError del sistema por \n" + e.getMessage());
        }
    }
}
