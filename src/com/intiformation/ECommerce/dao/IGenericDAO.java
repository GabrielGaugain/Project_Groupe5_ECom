package com.intiformation.ECommerce.dao;

import java.sql.Connection;
import java.util.List;

import com.intiformation.ECommerce.util.ConnectionDB;

public interface IGenericDAO <T> {
	
	public Connection connection = ConnectionDB.getInstance();
	
	
	public boolean add(T t);

	public boolean update(T t);
	
	public boolean delete(long id);
	
	public List<T> getAll();
	
	public T getById(long id);

}
