package pack.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import pack.ColumnName;

@Data
@ToString
@AllArgsConstructor
public class Task4Dao {

    @ColumnName(name = "month")
    public int month;
    @ColumnName(name = "cancelCount")
    public int cancelCount;
}
