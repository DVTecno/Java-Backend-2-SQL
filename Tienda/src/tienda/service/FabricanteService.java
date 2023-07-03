package tienda.service;

import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;
import tienda.entidades.Fabricante;
import tienda.persistencia.FabricanteDAO;

public class FabricanteService {
 FabricanteDAO dao;

    public FabricanteService() {
        this.dao = new FabricanteDAO();
    }

    public void crearFabricante(String nombre) throws Exception {
        try {
            if (nombre != null && !nombre.trim().isEmpty()) {
                Fabricante fabricante = new Fabricante();
                fabricante.setNombre(nombre);
                this.dao.guardarFabricante(fabricante);
                imprimirFabricante();
            } else {
                throw new Exception("Debe indicar un nombre de fabricante");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void modificarFabricante(String nombre, String nuevoNombre) throws Exception {
        try {
            Fabricante fabricante = this.dao.buscarFabricantePorNombre(nombre);
            if (fabricante == null) {
                throw new Exception("No se encontro un producto con el nombre especificado");
            } else if (nombre != null && !nombre.trim().isEmpty()) {
                if (nuevoNombre != null && !nuevoNombre.trim().isEmpty()) {
                    fabricante.setNombre(nuevoNombre);
                    this.dao.modificarFabricante(fabricante);
                } else {
                    throw new Exception("Debe indicar un nuevo nombre de producto");
                }
            } else {
                throw new Exception("Debe indicar un nombre de producto");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void eliminarFabricantePorId(Integer codigo) throws Exception {
        try {
            if (codigo <= 0) {
                throw new Exception("Ingrese un valor valido para el id del fabricante");
            } else {
                this.dao.eliminarFabricantePorId(codigo);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void eliminarFabricantePorNombre(String nombre) throws Exception {
        try {
            if (nombre != null && !nombre.trim().isEmpty()) {
                this.dao.eliminarFabricante(nombre);
            } else {
                throw new Exception("Debe ingresar el nombre del Fabricante");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public Fabricante buscarFabricantePorId(Integer codigo) throws Exception {
        try {
            if (codigo == null) {
                throw new Exception("Debe indicar un id");
            } else {
                Fabricante fabricante = this.dao.buscarPorId(codigo);
                return fabricante;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public Fabricante buscarFabricantePorNombre(String nombre) throws Exception {
        try {
            if (nombre != null && !nombre.trim().isEmpty()) {
                Fabricante fabricante = this.dao.buscarFabricantePorNombre(nombre);
                return fabricante;
            } else {
                throw new Exception("Debe ingresar el nombre del fabricante");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Fabricante> listarFabricantes() throws Exception {
        try {
            List<Fabricante> fabricantes = this.dao.listarFabricante();
            return fabricantes;
        } catch (Exception e) {
            throw e;
        }
    }

    public void imprimirFabricante() throws Exception {
        try {
            List<Fabricante> fabricantes = this.dao.listarFabricante();
            if (fabricantes.isEmpty()) {
                throw new Exception("No existen Productos para mostrar");
            } else {
                System.out.println("*****************************");
            System.out.println(" ___________________________");
            System.out.print("|");
            System.out.print("\u001B[31mCodigo\u001B[0m");
            System.out.print("|       ");
            System.out.print("\u001B[31mNombre\u001B[0m");
            System.out.println("       |");

            System.out.println("|______|____________________|");
            for (Fabricante fabricante : fabricantes) {
                System.out.printf("|%-5s |%-20s|\n", fabricante.getCodigo(), fabricante.getNombre());
                System.out.println("|______|____________________|");
            }
            System.out.println("*****************************");
            }
        } catch (Exception e) {
            throw e;
        }
    }
}

