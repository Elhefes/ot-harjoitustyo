/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kurssikuulustelija.dao;

import java.sql.*;
import java.util.List;

/**
 *
 * @author henripal
 */
public interface Dao<T, K> {

    void create(T object) throws SQLException;

    List<T> list() throws SQLException;
}
