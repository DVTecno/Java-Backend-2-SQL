package tienda.service;

import com.mysql.cj.util.StringUtils;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import tienda.entidades.Producto;
import tienda.persistencia.ProductoDAO;

public class ProductoService {

    private Scanner leer;
    private ProductoDAO dao;

    public ProductoService() {
        this.dao = new ProductoDAO();
        this.leer = (new Scanner(System.in)).useDelimiter("\n");
    }

    public void crearProducto(String nombre, double precio, int codigo_fabricante) throws Exception {
        try {
            if (nombre != null && !nombre.trim().isEmpty()) {
                if (precio <= 0.0) {
                    throw new Exception("Debe indicar un valor valido para el precio");
                } else {
                    Producto producto = new Producto();
                    producto.setNombre(nombre);
                    producto.setPrecio(precio);
                    producto.setCodigoFabricante(codigo_fabricante);
                    dao.guardarProducto(producto);
                }
            } else {
                throw new Exception("Debe indicar un nombre de producto");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void modificarProducto(String nombre, String nuevoNombre, double precio, double nuevoPrecio) throws Exception {
        try {
            Producto producto = dao.buscarProductoPorNombre(nombre);
            if (producto == null) {
                throw new Exception("No se encontro un producto con el nombre especificado");
            } else if (nombre != null && !nombre.trim().isEmpty()) {
                if (nuevoNombre != null && !nuevoNombre.trim().isEmpty()) {
                    if (nuevoPrecio <= 0.0) {
                        throw new Exception("Debe indicar un nuevo valor valido para el precio");
                    } else {
                        producto.setNombre(nuevoNombre);
                        producto.setPrecio(nuevoPrecio);
                        dao.modificarProducto(producto, precio);
                    }
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

    public void eliminarProductoPorId(Integer codigo) throws Exception {
        try {
            if (codigo <= 0) {
                throw new Exception("Ingrese un valor valido para el id del producto");
            } else {
                dao.eliminarProductoPorId(codigo);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void eliminarProductoPorNombre(String nombre) throws Exception {
        try {
            if (nombre != null && !nombre.trim().isEmpty()) {
                dao.eliminarProducto(nombre);
            } else {
                throw new Exception("Debe ingresar el nombre del producto");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public Producto buscarProductoPorId(Integer codigo) throws Exception {
        try {
            if (codigo == null) {
                throw new Exception("Debe indicar un id");
            } else {
                Producto producto = dao.buscarPorId(codigo);
                return producto;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public Producto buscarProductoPorNombre(String nombre) throws Exception {
        try {
            if (nombre.contains("'")) {
                throw new Exception("El nombre del producto no puede contener comillas");
            } else if (nombre != null && !nombre.trim().isEmpty()) {
                if (StringUtils.isStrictlyNumeric(nombre)) {
                    throw new Exception("El nombre del producto no puede ser numerico");
                } else {
                    Producto producto = dao.buscarProductoPorNombre(nombre);
                    return producto;
                }
            } else {
                throw new Exception("Debe ingresar el nombre del producto");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public Collection<Producto> listarProductos() throws Exception {
        try {
            Collection<Producto> productos = dao.listarProductos();
            return productos;
        } catch (Exception e) {
            throw e;
        }
    }

    public void imprimirProductos() throws Exception {
        try {
            Collection<Producto> productos = dao.listarProductos();
            if (productos.isEmpty()) {
                throw new Exception("No existen Productos para mostrar");
            }
            System.out.println("**************************************************************************");
            System.out.println(" ________________________________________________________________________");
            System.out.println("|Codigo|            Nombre                 |   Precio  |Codigo_Fabricante|");
            System.out.println("|______|___________________________________|___________|_________________|");
            for (Producto producto : productos) {
                System.out.printf("|%-5s |%-35s|$ %-8.2f |%17s|\n", producto.getCodigo(), producto.getNombre(), producto.getPrecio(), producto.getCodigoFabricante());
                System.out.println("|______|___________________________________|___________|_________________|");
            }
            System.out.println("**************************************************************************");
        } catch (Exception e) {
            throw e;
        }
    }

    public void menu() throws Exception {
        FabricanteService fS = new FabricanteService();
        boolean fin = true;

        label94:
        do {
            System.out.println(" *** BIENVENIDOS AL SISTEMA DE CONSULTAS ***\n"
                    + "Menu\n"
                    + "a) Listar el nombre de todos los productos que hay en la tabla producto.\n"
                    + "c) Listar aquellos productos cuyo precio esté entre 120 y 202.\n"
                    + "e) Listar el nombre y el precio del producto más barato.\n"
                    + "f) Ingresar un producto a la base de datos.\n"
                    + "g) Ingresar un fabricante a la base de datos.\n"
                    + "h) Editar un producto con datos a elección.\n"
                    + "i) SALIR.\n");
            System.out.print("Ingrese una Opcion: ");
            switch (this.leer.next().toLowerCase()) {
                case "a":

                    try {
                    System.out.println("\u001b[36mIntentar listar producto por nombre\u001b[0m");
                    this.listarProductosPorNombre();
                    System.out.println("\u001b[37mProducto encontrado con exito!\u001b[0m");
                } catch (Exception e) {
                    System.out.println("Error del sistema por \n" + e.getMessage());
                }

                break;
                case "b":
                    try {
                    System.out.println("\u001b[36mIntentar filtrar por nombre y precio\u001b[0m");
                    this.listarProductoPorNombrePrecio();
                    System.out.println("\u001b[37mLa lista se obtuvo con exito!\u001b[0m");
                } catch (Exception e) {
                    System.out.println("Error del sistema por \n" + e.getMessage());
                }
                break;
                case "c":
                    try {
                    System.out.println("\u001b[36mIntentar listar productos por precio entre 120 y 202\u001b[0m");
                    this.listarProductoPorNombrePrecioEntre();
                    System.out.println("\u001b[37mLa lista se obtuvo con exito!\u001b[0m");
                } catch (Exception e) {
                    System.out.println("Error del sistema por \n" + e.getMessage());
                }
                break;
                case "d":
                    try {
                    System.out.println("\u001b[36mIntentar listar Portátiles de productos\u001b[0m");
                    this.listarPortatilesDeProducto();
                    System.out.println("\u001b[37mLa lista de Portátiles se trajo correctamente\u001b[0m");
                } catch (Exception e) {
                    System.out.println("Error del sistema por \n" + e.getMessage());
                }
                break;
                case "e":
                    try {
                    System.out.println("\u001b[36mIntentar filtrar por nombre y precio minimo de cada producto\u001b[0m");
                    this.listarProductoPorNombrePrecioMin();
                    System.out.println("\u001b[37mLa lista de Productos se obtuvo con exito!\u001b[0m");
                } catch (Exception e) {
                    System.out.println("Error del sistema por \n" + e.getMessage());
                }
                break;
                case "f":
                    try {
                    System.out.println("Intentar crear producto");
                    this.crearProducto("TV 90 LED Full HD", 630.99, 1);
                    System.out.println("\u001b[37mProducto creado con exito!\u001b[0m");
                    this.imprimirProductos();
                } catch (Exception e) {
                    System.out.println("Error del sistema por \n" + e.getMessage());
                }
                break;
                case "g":
                    try {
                    System.out.print("Intentar Ingresar un Fabricante");
                    fS.crearFabricante(tomarNombre());
                    System.out.println("\u001b[32mFabricante ingresado con exito!\u001b[0m");
                } catch (Exception e) {
                    System.out.println("Error del sistema por \n" + e.getMessage());
                }
                break;
                case "h":
                    try {
                    System.out.println("\u001b[36mIntentar actulizar\u001b[0m");
                    int id = this.tomarId();
                    String nombre = tomarNombre();
                    double precio = tomarPrecio();
                    int codigo_fabricante = tomarId();
                    this.modificarProductoPorId(id, nombre, precio, codigo_fabricante);
                    System.out.println("\u001b[37mProducto actualizado con exito!\u001b[0m");
                    this.imprimirProductos();
                    System.out.println("\u001b[37mProductos Actulizado con exito!\u001b[0m");
                } catch (Exception e) {
                    System.out.println("\u001b[31mError del sistema por \n" + e.getMessage());
                }
                break;
                case "i":
                    System.out.println("Gracias por utilizar el sistema de consultas");
                    fin = false;
                    break;
                default:
                    System.out.println("Opcion invalida");
            }
        } while (fin);

    }

    public void modificarProductoPorId(Integer id, String nuevoNombre, double nuevoPrecio, int nuevo_codigo_fabricante) throws Exception {
        try {
            Producto producto = dao.buscarProductoPorId(id);
            if (producto == null) {
                throw new Exception("No se encontro un producto con el nombre especificado");
            } else if (nuevoNombre != null && !nuevoNombre.trim().isEmpty()) {
                if (nuevoPrecio <= 0.0) {
                    throw new Exception("Debe indicar un nuevo valor valido para el precio");
                } else if (nuevo_codigo_fabricante <= 0) {
                    throw new Exception("Debe indicar un nuevo valor valido para el codigo_fabricante");
                } else {
                    producto.setNombre(nuevoNombre);
                    producto.setPrecio(nuevoPrecio);
                    producto.setCodigoFabricante(nuevo_codigo_fabricante);
                    dao.modificarProductoId(producto);
                }
            } else {
                throw new Exception("Debe indicar un nuevo nombre de producto");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void listarProductosPorNombre() throws Exception {
        try {
            List<String> nombres = dao.listarProductosPorNombre();
            if (nombres.isEmpty()) {
                throw new Exception("No existen Productos para mostrar");
            } else {
                System.out.println("*************************************");
                System.out.println(" ___________________________________");
                System.out.print("|  ");
                System.out.print("           \u001B[31mNombre\u001B[0m");
                System.out.println("                |  ");
                System.out.println("|___________________________________|");
                for (String nombre : nombres) {
                    System.out.printf("|%-35s|\n", nombre);
                    System.out.println("|___________________________________|");
                }
                System.out.println("*************************************");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void listarProductoPorNombrePrecio() throws Exception {
        try {
            List<Producto> productos = dao.listarProductosPorNombrePrecio();
            if (productos.isEmpty()) {
                throw new Exception("No existen Productos para mostrar");
            }
            System.out.println("*************************************************");
            System.out.println(" _______________________________________________");
            System.out.print("|  ");
            System.out.print("\u001B[31mNombre\u001B[0m");
            System.out.print("                           |  ");
            System.out.print("\u001B[31mPrecio\u001B[0m");
            System.out.println("   |");

            System.out.println("|___________________________________|___________|");
            for (Producto producto : productos) {
                System.out.printf("|%-35s|$ %-8.2f |\n", producto.getNombre(), producto.getPrecio());
                System.out.println("|___________________________________|___________|");
            }
            System.out.println("*************************************************");

        } catch (Exception e) {
            throw e;
        }
    }

    public void listarProductoPorNombrePrecioEntre() throws Exception {
        try {
            List<Producto> productos = dao.listarProductosPorPrecio();
            if (productos.isEmpty()) {
                throw new Exception("No existen Productos para mostrar");
            }
            System.out.println("*************************************************");
            System.out.println(" _______________________________________________");
            System.out.print("|  ");
            System.out.print("\u001B[31mNombre\u001B[0m");
            System.out.print("                           |  ");
            System.out.print("\u001B[31mPrecio\u001B[0m");
            System.out.println("   |");

            System.out.println("|___________________________________|___________|");
            for (Producto producto : productos) {
                System.out.printf("|%-35s|$ %-8.2f |\n", producto.getNombre(), producto.getPrecio());
                System.out.println("|___________________________________|___________|");
            }
            System.out.println("*************************************************");

        } catch (Exception e) {
            throw e;
        }
    }

    public void listarPortatilesDeProducto() throws Exception {
        try {
            Collection<Producto> productos = dao.listarProductosPortatil();
            if (productos.isEmpty()) {
                throw new Exception("No existen Productos para mostrar");
            }
            System.out.println("**************************************************************************");
            System.out.println(" ________________________________________________________________________");
            System.out.println("|Codigo|            Nombre                 |   Precio  |Codigo_Fabricante|");
            System.out.println("|______|___________________________________|___________|_________________|");
            for (Producto producto : productos) {
                System.out.printf("|%-5s |%-35s|$ %-8.2f |%17s|\n", producto.getCodigo(), producto.getNombre(), producto.getPrecio(), producto.getCodigoFabricante());
                System.out.println("|______|___________________________________|___________|_________________|");
            }
            System.out.println("**************************************************************************");
        } catch (Exception e) {
            throw e;
        }
    }

    public void listarProductoPorNombrePrecioMin() throws Exception {
        try {
            List<Producto> productos = dao.listarProductosPorNombrePrecioMin();
            if (productos.isEmpty()) {
                throw new Exception("No existen Productos para mostrar");
            }
            System.out.println("*************************************************");
            System.out.println(" _______________________________________________");
            System.out.print("|  ");
            System.out.print("\u001B[31mNombre\u001B[0m");
            System.out.print("                           |  ");
            System.out.print("\u001B[31mPrecio\u001B[0m");
            System.out.println("   |");

            System.out.println("|___________________________________|___________|");
            for (Producto producto : productos) {
                System.out.printf("|%-35s|$ %-8.2f |\n", producto.getNombre(), producto.getPrecio());
                System.out.println("|___________________________________|___________|");
            }
            System.out.println("*************************************************");

        } catch (Exception e) {
            throw e;
        }
    }

    private String tomarNombre() throws Exception {
        System.out.print("Ingrese el nombre del producto: ");
        String nombre = leer.next();
        if (nombre.contains("'")) {
            throw new Exception("El nombre del producto no puede contener comillas");
        } else if (nombre != null && !nombre.trim().isEmpty()) {
            if (StringUtils.isStrictlyNumeric(nombre)) {
                throw new Exception("El nombre del producto no puede ser numerico");
            } else {
                return nombre;
            }
        } else {
            throw new Exception("Debe ingresar el nombre del producto");
        }
    }

    private double tomarPrecio() throws Exception {
        System.out.print("Ingrese el precio que desea asignar: ");
        String input = leer.next();

        double precio;
        try {
            precio = Double.parseDouble(input);
        } catch (NumberFormatException e) {
            throw new Exception("Debe indicar un valor numerico valido para el precio");
        }

        if (precio <= 0.0) {
            throw new Exception("Debe indicar un nuevo valor valido para el precio");
        } else {
            return precio;
        }
    }

    private int tomarId() throws Exception {
        System.out.print("Ingrese un codigo para el producto: ");
        String input = leer.next();

        int codigo;
        try {
            codigo = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new Exception("Debe indicar un valor numerico valido para el ID");
        }

        if (codigo <= 0) {
            throw new Exception("Debe indicar un nuevo valor valido para el ID");
        } else {
            return codigo;
        }
    }
}
