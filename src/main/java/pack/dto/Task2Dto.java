package pack.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import pack.ColumnName;

@Data
@AllArgsConstructor
@ToString
public class Task2Dto {

    @ColumnName(name = "city")
    public String city;
    @ColumnName(name = "count")
    public int count;
}
