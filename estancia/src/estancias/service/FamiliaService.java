package estancias.service;

import estancia.entidades.Familia;
import estancias.persistencia.FamiliaDAO;
import java.io.EOFException;
import java.util.ArrayList;
import java.util.List;

public class FamiliaService {

    private FamiliaDAO dao;

    public FamiliaService() {
        this.dao = new FamiliaDAO();
    }

    public void listarFamiliaPorHijoEdad() throws Exception {
        try {
            List<Familia> familias = dao.listarFamiliaPorHijoEdad();
            if (familias.isEmpty()) {
                throw new Exception("No existen Productos para mostrar");
            }
            System.out.println("****************************************************************************************************");
            System.out.println("\u001B[31m __________________________________________________________________________________________________\u001B[0m");
            System.out.println("\u001B[31m| Id |   Nombre    |Edad Minima|Edad Maxima|Cant. Hijos|           Email           |Id Casa Familia|\u001B[0m");
            System.out.println("\u001B[31m|____|_____________|___________|___________|___________|___________________________|_______________|\u001B[0m");
            for (Familia familia : familias) {
                System.out.printf("|%4d|%-11s  |%11d|%11d|%11d| %-25s |%15d|\n", familia.getIdFamilia(), familia.getNombre(), familia.getEdadMinima(), familia.getEdadMaxima(), familia.getNumHijos(), familia.getEmail(), familia.getIdCasaFamilia());
                System.out.println("|____|_____________|___________|___________|___________|___________________________|_______________|");
            }
            System.out.println("|____|_____________|___________|___________|___________|___________________________|_______________|");
            System.out.println("\u001b[37mLa lista se obtuvo con exito!\u001b[0m");
            System.out.println("****************************************************************************************************");
        } catch (Exception e) {
            throw e;
        }
    }

}
