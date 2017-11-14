package ua.kiev.prog;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class GroupDTO {
    private long id;
    private String name;
    private List<ContactDTO> contacts = new ArrayList<ContactDTO>();
    public GroupDTO(String name) {
        this.name = name;
    }
}
