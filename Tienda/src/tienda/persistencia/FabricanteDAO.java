package tienda.persistencia;

import java.util.ArrayList;
import java.util.List;
import tienda.entidades.Fabricante;

public class FabricanteDAO extends DAO {

    public FabricanteDAO() {
    }

    public void guardarFabricante(Fabricante fabricante) throws Exception {
        try {
            if (fabricante == null) {
                throw new Exception("Debe ingresar un fabricante");
            } else {
                String sql = "INSERT INTO Fabricante (nombre) VALUES('" + fabricante.getNombre() + "')";
                insertarModificarEliminar(sql);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

    public void modificarFabricante(Fabricante fabricante) throws Exception {
        try {
            if (fabricante == null) {
                throw new Exception("Debe indicar el fabricante a modificar");
            }
            String sql = "UPDATE Fabricante SET nombre = '" + fabricante.getNombre() + "' WHERE codigo = " + fabricante.getCodigo();
            insertarModificarEliminar(sql);
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
            System.out.println("Base de datos desconectada");
        }

    }

    public void eliminarFabricantePorId(Integer codigo) throws Exception {
        try {
            String sql = "DELETE FROM fabricante WHERE codigo = " + codigo;
            insertarModificarEliminar(sql);
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

    public void eliminarFabricante(String nombre) throws Exception {
        try {
            String var2 = "DELETE FROM fabricante WHERE nombre = '" + nombre + "'";
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

    public Fabricante buscarFabricantePorNombre(String nombre) throws Exception {
        try {
            String sql = "SELECT * FROM fabricante WHERE nombre = '" + nombre + "'";
            consultarBase(sql);
            Fabricante fabricante = null;

            while (resultado.next()) {
                fabricante = new Fabricante();
                fabricante.setCodigo(resultado.getInt(1));
                fabricante.setNombre(resultado.getString(2));
            }
            return fabricante;
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

    public Fabricante buscarPorId(Integer id) throws Exception {
        try {
            String sql = "SELECT * FROM fabricante WHERE codigo = " + id;
            consultarBase(sql);
            Fabricante fabricante = null;

            while (resultado.next()) {
                fabricante = new Fabricante();
                fabricante.setCodigo(resultado.getInt(1));
                fabricante.setNombre(resultado.getString(2));
            }
            return fabricante;
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

    public List<Fabricante> listarFabricante() throws Exception {
        try {
            String sql = "SELECT * FROM fabricante";
            consultarBase(sql);
            Fabricante fabricante = null;
            List<Fabricante> fabricantes = new ArrayList();

            while (resultado.next()) {
                fabricante = new Fabricante();
                fabricante.setCodigo(resultado.getInt(1));
                fabricante.setNombre(resultado.getString(2));
                fabricantes.add(fabricante);
            }
            return fabricantes;
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }
}
