package ibf2022.paf.tvshows.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Show {

    private String name;

    private Integer rating;

    private String comments;

}
