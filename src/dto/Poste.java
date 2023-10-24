package dto;

import java.time.LocalDate;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Data
@NotNull

public class Poste {
    private Employee employee;
    private Agency agency;
    private LocalDate startdate;
    private LocalDate enddate;
}
