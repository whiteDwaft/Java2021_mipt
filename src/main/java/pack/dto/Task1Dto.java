package pack.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import pack.ColumnName;

import java.util.List;

@Data
@AllArgsConstructor
@ToString
public class Task1Dto {

    @ColumnName(name = "city")
    public String city;
    @ColumnName(name = "airCodes")
    public List<String> airCodes;
}
