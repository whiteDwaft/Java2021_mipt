package pack.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import pack.ColumnName;

@Data
@AllArgsConstructor
@ToString
public class Task2Dao {

    @ColumnName(name = "city")
    public String city;
    @ColumnName(name = "count")
    public int count;
}
