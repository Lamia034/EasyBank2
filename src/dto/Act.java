package dto;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Data
@NotNull
public class Act {
    private Employee employee;
    private Agency agency;
}
