package br.com.task.bank.repository;

/**
 * 
 * 
 * @author Artur Maia Pereira
 * @version 1.0 - 05/10/2020
 */

import java.util.List;
import java.util.Optional;

public interface AccountsDAO<T> {
	Optional<T> get(int id);
    
    List<T> getAll();
    
    void save(T t);
    
    void update(T t);
    
    void delete(T t);
}
