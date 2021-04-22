package pack.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import pack.ColumnName;

@Data
@ToString
@AllArgsConstructor
public class Task4Dto {

    @ColumnName(name = "month")
    public int month;
    @ColumnName(name = "cancelCount")
    public int cancelCount;
}
