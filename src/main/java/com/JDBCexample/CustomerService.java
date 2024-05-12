package com.JDBCexample;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerService {
    private final JdbcTemplate  jdbcTemplate;
    private final NamedParameterJdbcTemplate jdbcTemplateNamed;

    public CustomerService(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate jdbcTemplateNamed) {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcTemplateNamed = jdbcTemplateNamed;
    }


    //operations
    //add customer
    public void addCustomer(Customer customer) {
       // String sql = "INSERT INTO customer values (? , ?)"; // placeholder
        String sql = "INSERT INTO customer(id,name) VALUES (:id , :name)"; // parametrized query
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", customer.getId())
                        .addValue("name", customer.getName());

        //jdbcTemplate.update(sql, customer.getId(), customer.getName());
        jdbcTemplateNamed.update(sql, params);
    }
    //get all customers
    public List<Customer> getAllCustomers() {
        String sql = "SELECT * FROM customer";
        return jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(Customer.class));
    }
    //get customer by id
    public Customer getCustomerById(int id){
        String sql = "SELECT * FROM customer where id = ?";
        return jdbcTemplate.queryForObject(sql,
                new Object[]{id},
                new BeanPropertyRowMapper<>(Customer.class)
                );
    }
    //delete customer
    public void deleteCustomer(int id){
//        String sql = "DELETE FROM customer WHERE id = ?"; //placeholder
//        jdbcTemplate.update(sql,id);

        String sql = "DELETE FROM customer WHERE id = :id"; //parameter
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id);
        jdbcTemplateNamed.update(sql,params);
    }
    //update customer
    public void updateCustomer(Customer customer){
        String sql = "UPDATE customer SET name =? where id = ?";
        jdbcTemplate.update(sql, customer.getName(), customer.getId());
    }

}
