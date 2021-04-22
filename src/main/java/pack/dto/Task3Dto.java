package pack.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import pack.ColumnName;

@ToString
@Data
@AllArgsConstructor
public class Task3Dto {

    @ColumnName(name = "city")
    public String city;
    @ColumnName(name = "minTime")
    public int minTime;
}
