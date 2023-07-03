package estancias.service;

import estancia.entidades.Casa;
import estancias.persistencia.CasaDAO;
import java.util.List;

public class CasaService {

    private CasaDAO dao;

    public CasaService() {
        this.dao = new CasaDAO();
    }

    public void listarCasasPorFechaPais() throws Exception {
        try {
            List<Casa> casas = dao.listarCasasPorFechaPais();
            if (casas.isEmpty()) {
                throw new Exception("No existen Productos para mostrar");
            }
            System.out.println("*************************************************************************************************************************************************************");
            System.out.println("\u001B[31m ___________________________________________________________________________________________________________________________________________________________\u001B[0m");
            System.out.println("\u001B[31m| Id |      Calle      | Número |Codigo Postal|   Ciudad   |    Pais   | Fecha Desde | Fecha Hasta | Tiempo Min | Tiempo Max |Precio Habitacion|Tipo Vivienda|\u001B[0m");
            System.out.println("\u001B[31m|____|_________________|________|_____________|____________|___________|_____________|_____________|____________|____________|_________________|_____________|\u001B[0m");

            for (Casa casa : casas) {
                System.out.printf("|%4d|%-17s|%8d|%13s|%-12s|%-10s|%-13s|%-13s|%12d|%12d|%17.2f|%-13s|\n", casa.getIdCasa(), casa.getCalle(), casa.getNumero(), casa.getCodigoPostal(), casa.getCiudad(), casa.getPais(), casa.getFechaDesde(), casa.getFechaHasta(), casa.getTiempoMinimo(), casa.getTiempoMaximo(), casa.getPrecioHabitacion(), casa.getTipoVivienda());
                System.out.println("|____|_________________|________|_____________|____________|___________|_____________|_____________|____________|____________|_________________|_____________|");
            }

            System.out.println("|____|_________________|________|_____________|____________|___________|_____________|_____________|____________|____________|_________________|_____________|");
            System.out.println("\u001b[37mLa lista se obtuvo con exito!\u001b[0m");
            System.out.println("*************************************************************************************************************************************************************");
        } catch (Exception e) {
            throw e;
        }
    }
    

}
