package estancias.persistencia;

import estancia.entidades.Familia;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.List;

public class FamiliaDAO extends DAO {

    public FamiliaDAO() {
    }

    public List<Familia> listarFamiliaPorHijoEdad() throws SQLException, Exception {
        try {
            String sql = """
                         SELECT * FROM familias
                         WHERE num_hijos >=3 AND edad_maxima <10
                         
                         """;
            consultarBase(sql);
            List<Familia> familias = new ArrayList<>();

            while (resultado.next()) {
                familias.add(new Familia(resultado.getInt(1), resultado.getString(2), resultado.getInt(3), resultado.getInt(4), resultado.getInt(5), resultado.getString(6), resultado.getInt(7)));
            }
            return familias;

        } catch (SQLException e) {
            throw e;
        } finally {
            desconectarBase();
        }

    }

}
