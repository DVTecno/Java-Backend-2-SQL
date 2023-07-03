package estancias.persistencia;

import estancia.entidades.Casa;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CasaDAO extends DAO {

    
    
      public List<Casa> listarCasasPorFechaPais() throws SQLException, Exception {
        try {
            String sql = "SELECT * FROM casas WHERE pais = 'Reino Unido' AND fecha_desde >= '2020-08-01'  AND fecha_hasta <= '2020-08-31' ";

            this.consultarBase(sql);
            List<Casa> casas = new ArrayList<>();

            while (resultado.next()) {
                casas.add(new Casa(resultado.getInt(1), resultado.getString(2), resultado.getInt(3), resultado.getString(4), resultado.getString(5), resultado.getString(6), resultado.getDate(7).toLocalDate(), resultado.getDate(8).toLocalDate(), resultado.getInt(9), resultado.getInt(10), resultado.getDouble(11), resultado.getString(12)));

            }
            desconectarBase();
            return casas;

        } catch (SQLException e) {
            desconectarBase();
            throw e;
        } finally {
            desconectarBase();
        }

    }

}
