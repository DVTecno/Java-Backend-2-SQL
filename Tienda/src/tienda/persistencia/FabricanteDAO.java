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
                this.insertarModificarEliminar(sql);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void modificarFabricante(Fabricante fabricante) throws Exception {
        try {
            if (fabricante == null) {
                throw new Exception("Debe indicar el fabricante a modificar");
            }

            String var10000 = fabricante.getNombre();
            String sql = "UPDATE Fabricante SET nombre = '" + var10000 + "' WHERE codigo = " + fabricante.getCodigo();
            this.insertarModificarEliminar(sql);
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectarBase();
            System.out.println("Base de datos desconectada");
        }

    }

    public void eliminarFabricantePorId(Integer codigo) throws Exception {
        try {
            String sql = "DELETE FROM fabricante WHERE codigo = " + codigo;
            this.insertarModificarEliminar(sql);
        } catch (Exception e) {
            throw e;
        }
    }

    public void eliminarFabricante(String nombre) throws Exception {
        try {
            String var2 = "DELETE FROM fabricante WHERE nombre = '" + nombre + "'";
        } catch (Exception e) {
            throw e;
        }
    }

    public Fabricante buscarFabricantePorNombre(String nombre) throws Exception {
        try {
            String sql = "SELECT * FROM fabricante WHERE nombre = '" + nombre + "'";
            this.consultarBase(sql);
            Fabricante fabricante = null;

            while (this.resultado.next()) {
                fabricante = new Fabricante();
                fabricante.setCodigo(this.resultado.getInt(1));
                fabricante.setNombre(this.resultado.getString(2));
            }

            this.desconectarBase();
            return fabricante;
        } catch (Exception e) {
            this.desconectarBase();
            throw e;
        }
    }

    public Fabricante buscarPorId(Integer id) throws Exception {
        try {
            String sql = "SELECT * FROM fabricante WHERE codigo = " + id;
            this.consultarBase(sql);
            Fabricante fabricante = null;

            while (this.resultado.next()) {
                fabricante = new Fabricante();
                fabricante.setCodigo(this.resultado.getInt(1));
                fabricante.setNombre(this.resultado.getString(2));
            }

            this.desconectarBase();
            return fabricante;
        } catch (Exception e) {
            this.desconectarBase();
            throw e;
        }
    }    

    public List<Fabricante> listarFabricante() throws Exception {
        try {
            String sql = "SELECT * FROM fabricante";
            this.consultarBase(sql);
            Fabricante fabricante = null;
            List<Fabricante> fabricantes = new ArrayList();

            while (this.resultado.next()) {
                fabricante = new Fabricante();
                fabricante.setCodigo(this.resultado.getInt(1));
                fabricante.setNombre(this.resultado.getString(2));
                fabricantes.add(fabricante);
            }

            this.desconectarBase();
            return fabricantes;
        } catch (Exception e) {
            this.desconectarBase();
            throw e;
        }
    }
}
