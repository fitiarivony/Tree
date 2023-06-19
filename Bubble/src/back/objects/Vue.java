/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package back.objects;

import exception.DAOException;
import exception.MappingException;
import java.sql.SQLException;
import utils.Connect;

/**
 *
 * @author FITIA ARIVONY
 * @param <T>
 */
public class Vue<T extends Vue> extends BddObject<T>{

    @Override
    public void insertArray(T[] intervalles, Connect conn) throws SQLException, Exception {
       throw new DAOException("This is a view");
    }

    @Override
    public void delete(Connect conn) throws SQLException, MappingException, Exception {
      throw new DAOException("This is a view");
    }

    @Override
    public void update(Connect conn, T vaovao) throws SQLException, Exception {
        throw new DAOException("This is a view");
    }

    @Override
    public void update(Connect conn) throws SQLException, Exception {
       throw new DAOException("This is a view");
    }

    @Override
    public void save(Connect conn) throws ClassNotFoundException, SQLException, Exception {
        throw new DAOException("This is a view");
    }

    @Override
    public void setPrimary_key(Object value) throws IllegalArgumentException, Exception {
       throw new DAOException("This is a view");
    }
    
    
}
