package by.dma1979.reactivedailyqa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author dzmitry.marudau
 * @since 2019.6
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Branch {
    @Id
    private String id;

    private String name;

}
