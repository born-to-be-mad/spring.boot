package by.dma1979.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * The JdbcCustomerRepository uses a JdbcTemplate and RowMapper (through a lambda expression)
 * to convert the ResultSet into a Customer.
 *
 * @author : Dzmitry Marudau
 * @created at : 15:54
 * @since : 2019.12
 **/
@Repository
public class JdbcCustomerRepository implements CustomerRepository {
    private static final String ALL_QUERY =
            "SELECT id, name, email FROM customer";

    private static final String BY_ID_QUERY =
            "SELECT id, name, email FROM customer WHERE id=?";

    private static final String INSERT_QUERY =
            "INSERT INTO customer (name, email) VALUES (?,?)";

    private final JdbcTemplate jdbc;

    JdbcCustomerRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public List<Customer> findAll() {
        return jdbc.query(ALL_QUERY, (rs, rowNum) -> toCustomer(rs));
    }

    @Override
    public Customer findById(long id) {
        return jdbc.queryForObject(BY_ID_QUERY, (rs, rowNum) -> toCustomer(rs), id);
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
            return new Customer(keyHolder.getKey().longValue(), customer.getName(), customer.getEmail());
        } else {
            throw new IllegalStateException("Customer was not inserted. Number of affected rows:" + rows);
        }
    }

    private Customer toCustomer(ResultSet rs) throws SQLException {
        return new Customer(
                rs.getLong(1),
                rs.getString(2),
                rs.getString(3)
        );
    }
}