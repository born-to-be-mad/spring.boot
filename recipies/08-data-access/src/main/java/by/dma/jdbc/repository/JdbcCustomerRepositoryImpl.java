package by.dma.jdbc.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import by.dma.entity.Customer;

/**
 * It uses a JdbcTemplate and RowMapper (through a lambda expression)
 * to convert the ResultSet into a Customer.
 *
 * @author : Dzmitry Marudau
 * @created at : 01:24
 * @since : 2020.07
 **/
@Repository
public class JdbcCustomerRepositoryImpl implements CustomerRepository {
  private static final String ALL_QUERY =
          "SELECT id, name, email FROM customer";
  private static final String BY_ID_QUERY =
          "SELECT id, name, email FROM customer WHERE id=?";
  private static final String INSERT_QUERY =
          "INSERT INTO customer (name, email) VALUES (?,?)";

  private final JdbcTemplate jdbc;

  JdbcCustomerRepositoryImpl(JdbcTemplate jdbc) {
    this.jdbc = jdbc;
  }

  @Override
  public List<Customer> findAll() {
    return jdbc.query(ALL_QUERY, (rs, rowNum) -> toCustomer(rs));
  }

  @Override
  public Customer findById(long id) {
    return jdbc.queryForObject(
            BY_ID_QUERY,
            (rs, rowNum) -> toCustomer(rs),
            id
    );
  }

  @Override
  public Customer save(Customer customer) {
    var keyHolder = new GeneratedKeyHolder();
    int rows = jdbc.update(con -> {
      var ps = con.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
      ps.setString(1, customer.getName());
      ps.setString(2, customer.getEmail());
      return ps;
    }, keyHolder);

    if (rows == 1) {
      return new Customer(
              keyHolder.getKey().longValue(),
              customer.getName(),
              customer.getEmail());
    } else {
      throw new IllegalStateException("Customer was not inserted. Number of affected rows:" + rows);
    }
  }

  private Customer toCustomer(ResultSet rs) throws SQLException {
    var id = rs.getLong(1);
    var name = rs.getString(2);
    var email = rs.getString(3);
    return new Customer(id, name, email);
  }
}
