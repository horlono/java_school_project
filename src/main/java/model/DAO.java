package model;

import java.sql.Connection;

public interface  DAO<T>{
    public Connection connect= ConnectionDB.getInstance();
    public T find(int id);
    public void  create(T obj);
    public void add(T obj);
    public void updateName(int id,String string);
    public void  delete(T obj);
    public void updatePosition(T obj,T obj2);
    public void restore(T obj,int pos);
}