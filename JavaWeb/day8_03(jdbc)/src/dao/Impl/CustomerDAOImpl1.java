package dao.Impl;

import dao.BaseDao1;
import dao.CustomerDAO;
import pojo.Customer;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

public class CustomerDAOImpl1 extends BaseDao1<Customer> implements CustomerDAO {
    @Override
    public void insert(Connection conn, Customer cust)  {
        String sql = "INSERT INTO customers(`name`,email,birth) VALUES (?,?,?)";
        update(conn, sql, cust.getName(), cust.getEmail(), cust.getBirth());

    }

    @Override
    public void deleteById(Connection conn, int id) {
        String sql = "DELETE FROM customers WHERE id = ?";
        update(conn,sql,id);
    }

    @Override
    public void update(Connection conn, Customer cust) {
        String sql = "UPDATE customers SET name = ?,email = ?,birth = ? where id = ?";
        update(conn,sql,cust.getName(),cust.getEmail(),cust.getBirth(),cust.getId());
    }

    @Override
    public List<Customer> getAll(Connection conn) {
        String sql = "select id,name,email,birth from customers";
        List<Customer> list = getForlist(conn, sql);
        return list;
    }

    @Override
    public Long getCount(Connection conn) {
        String sql = "select count(*) from customers";
        return getValue(conn, sql);
    }

    @Override
    public Date getMaxBirth(Connection conn) {
        String sql = "select max(birth) from customers";
        return getValue(conn, sql);
    }
}
