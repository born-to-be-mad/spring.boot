package learn.dma.jdbc.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Class representing a chairperson of a department.
 */
@Table("CHAIR")
public class Chair {
    @Id
    private int department;

    private String name;

    public Chair(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Chair{" +
               "department=" + department +
               ", name='" + name + '\'' +
               '}';
    }
}
