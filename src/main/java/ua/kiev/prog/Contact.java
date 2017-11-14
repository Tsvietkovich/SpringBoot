package ua.kiev.prog;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="Contacts")
@NoArgsConstructor
@Getter
@Setter
public class Contact {
    @Id
    @GeneratedValue
    private long id;
    
    @ManyToOne
    @JoinColumn(name="group_id")
    private Group group;

    private String name;
    private String surname;
    private String phone;
    private String email;

    public Contact(Group group, String name, String surname, String phone, String email) {
        this.group = group;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
    }
    public ContactDTO toDTO(){
        ContactDTO contactDTO = new ContactDTO(group,name,surname,phone,email);
        contactDTO.setId(id);
        return contactDTO;
    }
    public static Contact fromDTO(ContactDTO contactDTO){
        Group group = contactDTO.getGroup();
        if(group==null){
            group=null;
        }
        Contact contact = new Contact(group,contactDTO.getName(),
                contactDTO.getSurname(),contactDTO.getPhone(),contactDTO.getEmail());
        contact.setId(contactDTO.getId());
        return contact;
    }
}
