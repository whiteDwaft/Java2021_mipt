package pack.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import pack.ColumnName;

@Data
@AllArgsConstructor
@ToString
public class Task5Dto {

    @ColumnName(name = "dayOfWeek")
    public int dayOfWeek;
    @ColumnName(name = "flightCount")
    public int flightCount;
}
