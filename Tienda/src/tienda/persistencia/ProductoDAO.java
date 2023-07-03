package tienda.persistencia;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import tienda.entidades.Producto;

public class ProductoDAO extends DAO {

    public ProductoDAO() {
    }

    public void guardarProducto(Producto producto) throws Exception {
        try {
            if (producto == null) {
                throw new Exception("Debe ingresar un producto");
            } else {
                String sql = "INSERT INTO Producto (nombre, precio, codigo_fabricante)VALUES ('" + producto.getNombre() + "', " + producto.getPrecio() + ", " + producto.getCodigoFabricante() + ")";
                insertarModificarEliminar(sql);
                desconectarBase();
            }
        } catch (Exception e) {
            desconectarBase();
            throw e;
        }
    }

    public void modificarProducto(Producto producto, double precio) throws Exception {
        try {
            if (producto == null) {
                throw new Exception("Debe indicar el producto a modificar");
            }

            String var10000 = producto.getNombre();
            String sql = "UPDATE Producto SET nombre = '" + var10000 + "', precio = " + producto.getPrecio() + " WHERE precio = " + precio;
            insertarModificarEliminar(sql);
            desconectarBase();
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }

    }

    public void eliminarProductoPorId(Integer codigo) throws Exception {
        try {
            String sql = "DELETE FROM producto WHERE codigo = '" + codigo + "'";
            insertarModificarEliminar(sql);
            desconectarBase();
        } catch (Exception e) {
            desconectarBase();
            throw e;
        }
    }

    public void eliminarProducto(String nombre) throws Exception {
        try {
            String sql = "DELETE FROM producto WHERE nombre = '" + nombre + "'";
            insertarModificarEliminar(sql);
            desconectarBase();
        } catch (Exception e) {
            desconectarBase();
            throw e;
        }
    }

    public Producto buscarProductoPorNombre(String nombre) throws Exception {
        try {
            String sql = "SELECT * FROM Producto WHERE nombre = '" + nombre + "'";
            consultarBase(sql);
            Producto producto = null;

            while (resultado.next()) {
                producto = new Producto();
                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getDouble(3));
                producto.setCodigoFabricante(resultado.getInt(4));
            }

            desconectarBase();
            return producto;
        } catch (Exception e) {
            desconectarBase();
            throw e;
        }
    }

    public Producto buscarPorId(Integer id) throws Exception {
        try {
            String sql = "SELECT * FROM Producto WHERE codigo =" + id;
            consultarBase(sql);
            Producto producto = null;

            while (resultado.next()) {
                producto = new Producto();
                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getDouble(3));
                producto.setCodigoFabricante(resultado.getInt(4));
            }

            desconectarBase();
            return producto;
        } catch (Exception e) {
            desconectarBase();
            throw e;
        }
    }

    public Collection<Producto> listarProductos() throws Exception {
        try {
            String sql = "SELECT * FROM Producto";
            consultarBase(sql);
            Producto producto = null;
            List<Producto> productos = new ArrayList();

            while (this.resultado.next()) {
                producto = new Producto();
                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getDouble(3));
                producto.setCodigoFabricante(resultado.getInt(4));
                productos.add(producto);
            }

            desconectarBase();
            return productos;
        } catch (Exception e) {
            desconectarBase();
            throw e;
        }
    }

    public Producto buscarProductoPorId(Integer codigo) throws Exception {
        try {
            String sql = "SELECT * FROM Producto WHERE codigo =" + codigo;
            consultarBase(sql);
            Producto producto = null;

            while (resultado.next()) {
                producto = new Producto();
                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getDouble(3));
                producto.setCodigoFabricante(resultado.getInt(4));
            }

            desconectarBase();
            return producto;
        } catch (Exception e) {
            desconectarBase();
            throw e;
        }
    }

    public void modificarProductoId(Producto producto) throws Exception {
        try {
            if (producto == null) {
                throw new Exception("Debe indicar el producto a modificar");
            }
            String sql = "UPDATE Producto SET nombre = '" + producto.getNombre() + "', precio = " + producto.getPrecio() + ", codigo_fabricante =" + producto.getCodigoFabricante() + " WHERE codigo =" + producto.getCodigo();
            insertarModificarEliminar(sql);
            desconectarBase();
        } catch (Exception e) {
            desconectarBase();
            throw e;
        } finally {
            desconectarBase();
        }

    }

    public List<String> listarProductosPorNombre() throws Exception {
        try {
            String sql = "SELECT nombre FROM Producto ";
            consultarBase(sql);
            List<String> nombres = new ArrayList();

            while (this.resultado.next()) {
                nombres.add(resultado.getString(1));
            }

            desconectarBase();
            return nombres;
        } catch (Exception e) {
            desconectarBase();
            throw e;
        }
    }

    public List<Producto> listarProductosPorNombrePrecio() throws Exception {
        try {
            String sql = "SELECT nombre, precio FROM Producto ";
            consultarBase(sql);
            List<Producto> productos = new ArrayList();

            while (resultado.next()) {
                productos.add(new Producto(resultado.getString(1), resultado.getDouble(2)));
            }

            desconectarBase();
            return productos;
        } catch (Exception e) {
            desconectarBase();
            throw e;
        }
    }

    public List<Producto> listarProductosPorPrecio() throws Exception {
        try {
            String sql = "SELECT nombre, precio FROM Producto WHERE precio > 120 AND precio <202";
            consultarBase(sql);
            List<Producto> productos = new ArrayList();

            while (resultado.next()) {
                productos.add(new Producto(resultado.getString(1), resultado.getDouble(2)));
            }

            desconectarBase();
            return productos;
        } catch (Exception e) {
            desconectarBase();
            throw e;
        }
    }

    public List<Producto> listarProductosPortatil() throws Exception {
        try {
            String sql = "SELECT * FROM Producto WHERE nombre LIKE '%portatil%' LIMIT 100;";
            consultarBase(sql);
            Producto producto = null;
            List<Producto> productos = new ArrayList();

            while (this.resultado.next()) {
                producto = new Producto();
                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getDouble(3));
                producto.setCodigoFabricante(resultado.getInt(4));
                productos.add(producto);
            }

            desconectarBase();
            return productos;
        } catch (Exception e) {
            desconectarBase();
            throw e;
        }
    }

    public List<Producto> listarProductosPorNombrePrecioMin() throws Exception {
        try {
            String sql = "SELECT p1.nombre, p1.precio FROM Producto p1\nWHERE p1.precio = (SELECT MIN(p2.precio) FROM Producto p2\nWHERE p2.codigo_fabricante = p1.codigo_fabricante)\nORDER BY precio ASC LIMIT 1;\n";
            consultarBase(sql);
            List<Producto> productos = new ArrayList();

            while (resultado.next()) {
                productos.add(new Producto(resultado.getString(1), resultado.getDouble(2)));
            }

            desconectarBase();
            return productos;
        } catch (Exception e) {
            desconectarBase();
            throw e;
        }
    }
}
