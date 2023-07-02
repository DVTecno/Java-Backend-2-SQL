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
                String var10000 = producto.getNombre();
                String sql = "INSERT INTO Producto (nombre, precio, codigo_fabricante)VALUES ('" + var10000 + "', " + producto.getPrecio() + ", " + producto.getCodigoFabricante() + ")";
                this.insertarModificarEliminar(sql);
                this.desconectarBase();
            }
        } catch (Exception e) {
            this.desconectarBase();
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
            this.insertarModificarEliminar(sql);
            this.desconectarBase();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectarBase();
        }

    }

    public void eliminarProductoPorId(Integer codigo) throws Exception {
        try {
            String sql = "DELETE FROM producto WHERE codigo = '" + codigo + "'";
            this.insertarModificarEliminar(sql);
            this.desconectarBase();
        } catch (Exception e) {
            this.desconectarBase();
            throw e;
        }
    }

    public void eliminarProducto(String nombre) throws Exception {
        try {
            String sql = "DELETE FROM producto WHERE nombre = '" + nombre + "'";
            this.insertarModificarEliminar(sql);
            this.desconectarBase();
        } catch (Exception e) {
            this.desconectarBase();
            throw e;
        }
    }

    public Producto buscarProductoPorNombre(String nombre) throws Exception {
        try {
            String sql = "SELECT * FROM Producto WHERE nombre = '" + nombre + "'";
            this.consultarBase(sql);
            Producto producto = null;

            while (this.resultado.next()) {
                producto = new Producto();
                producto.setCodigo(this.resultado.getInt(1));
                producto.setNombre(this.resultado.getString(2));
                producto.setPrecio(this.resultado.getDouble(3));
                producto.setCodigoFabricante(this.resultado.getInt(4));
            }

            this.desconectarBase();
            return producto;
        } catch (Exception e) {
            this.desconectarBase();
            throw e;
        }
    }

    public Producto buscarPorId(Integer id) throws Exception {
        try {
            String sql = "SELECT * FROM Producto WHERE codigo =" + id;
            this.consultarBase(sql);
            Producto producto = null;

            while (this.resultado.next()) {
                producto = new Producto();
                producto.setCodigo(this.resultado.getInt(1));
                producto.setNombre(this.resultado.getString(2));
                producto.setPrecio(this.resultado.getDouble(3));
                producto.setCodigoFabricante(this.resultado.getInt(4));
            }

            this.desconectarBase();
            return producto;
        } catch (Exception e) {
            this.desconectarBase();
            throw e;
        }
    }

    public Collection<Producto> listarProductos() throws Exception {
        try {
            String sql = "SELECT * FROM Producto";
            this.consultarBase(sql);
            Producto producto = null;
            Collection<Producto> productos = new ArrayList();

            while (this.resultado.next()) {
                producto = new Producto();
                producto.setCodigo(this.resultado.getInt(1));
                producto.setNombre(this.resultado.getString(2));
                producto.setPrecio(this.resultado.getDouble(3));
                producto.setCodigoFabricante(this.resultado.getInt(4));
                productos.add(producto);
            }

            this.desconectarBase();
            return productos;
        } catch (Exception e) {
            this.desconectarBase();
            throw e;
        }
    }

    public Producto buscarProductoPorId(Integer codigo) throws Exception {
        try {
            String sql = "SELECT * FROM Producto WHERE codigo =" + codigo;
            this.consultarBase(sql);
            Producto producto = null;

            while (this.resultado.next()) {
                producto = new Producto();
                producto.setCodigo(this.resultado.getInt(1));
                producto.setNombre(this.resultado.getString(2));
                producto.setPrecio(this.resultado.getDouble(3));
                producto.setCodigoFabricante(this.resultado.getInt(4));
            }

            this.desconectarBase();
            return producto;
        } catch (Exception e) {
            this.desconectarBase();
            throw e;
        }
    }

    public void modificarProductoId(Producto producto) throws Exception {
        try {
            if (producto == null) {
                throw new Exception("Debe indicar el producto a modificar");
            }

            String var10000 = producto.getNombre();
            String sql = "UPDATE Producto SET nombre = '" + var10000 + "', precio = " + producto.getPrecio() + ", codigo_fabricante =" + producto.getCodigoFabricante() + " WHERE codigo =" + producto.getCodigo();
            this.insertarModificarEliminar(sql);
            this.desconectarBase();
        } catch (Exception e) {
            this.desconectarBase();
            throw e;
        } finally {
            this.desconectarBase();
        }

    }

    public List<String> listarProductosPorNombre() throws Exception {
        try {
            String sql = "SELECT nombre FROM Producto ";
            this.consultarBase(sql);
            List<String> nombres = new ArrayList();

            while (this.resultado.next()) {
                nombres.add(this.resultado.getString(1));
            }

            this.desconectarBase();
            return nombres;
        } catch (Exception e) {
            this.desconectarBase();
            throw e;
        }
    }

    public List<Producto> listarProductosPorNombrePrecio() throws Exception {
        try {
            String sql = "SELECT nombre, precio FROM Producto ";
            this.consultarBase(sql);
            List<Producto> productos = new ArrayList();

            while (this.resultado.next()) {
                productos.add(new Producto(this.resultado.getString(1), this.resultado.getDouble(2)));
            }

            this.desconectarBase();
            return productos;
        } catch (Exception e) {
            this.desconectarBase();
            throw e;
        }
    }

    public List<Producto> listarProductosPorPrecio() throws Exception {
        try {
            String sql = "SELECT nombre, precio FROM Producto WHERE precio > 120 AND precio <202";
            this.consultarBase(sql);
            List<Producto> productos = new ArrayList();

            while (this.resultado.next()) {
                productos.add(new Producto(this.resultado.getString(1), this.resultado.getDouble(2)));
            }

            this.desconectarBase();
            return productos;
        } catch (Exception e) {
            this.desconectarBase();
            throw e;
        }
    }

    public Collection<Producto> listarProductosPortatil() throws Exception {
        try {
            String sql = "SELECT * FROM Producto WHERE nombre LIKE '%portatil%' LIMIT 100;";
            this.consultarBase(sql);
            Producto producto = null;
            Collection<Producto> productos = new ArrayList();

            while (this.resultado.next()) {
                producto = new Producto();
                producto.setCodigo(this.resultado.getInt(1));
                producto.setNombre(this.resultado.getString(2));
                producto.setPrecio(this.resultado.getDouble(3));
                producto.setCodigoFabricante(this.resultado.getInt(4));
                productos.add(producto);
            }

            this.desconectarBase();
            return productos;
        } catch (Exception e) {
            this.desconectarBase();
            throw e;
        }
    }

    public List<Producto> listarProductosPorNombrePrecioMin() throws Exception {
        try {
            String sql = "SELECT p1.nombre, p1.precio FROM Producto p1\nWHERE p1.precio = (SELECT MIN(p2.precio) FROM Producto p2\nWHERE p2.codigo_fabricante = p1.codigo_fabricante)\nORDER BY precio ASC LIMIT 1;\n";
            this.consultarBase(sql);
            List<Producto> productos = new ArrayList();

            while (this.resultado.next()) {
                productos.add(new Producto(this.resultado.getString(1), this.resultado.getDouble(2)));
            }

            this.desconectarBase();
            return productos;
        } catch (Exception e) {
            this.desconectarBase();
            throw e;
        }
    }
}
