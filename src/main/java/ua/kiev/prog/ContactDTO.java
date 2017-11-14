package ua.kiev.prog;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ContactDTO {
    private long id;
    private Group group;

    private String name;
    private String surname;
    private String phone;
    private String email;

    public ContactDTO(Group group, String name, String surname, String phone, String email) {
        this.group = group;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
    }
}
