package lk.ijse.projection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class loadBranchName {
    private String name;

    @Override
    public String toString(){
        return  name;
    }

}