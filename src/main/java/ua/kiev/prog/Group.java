package ua.kiev.prog;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Groups")
@NoArgsConstructor
@Getter
@Setter
public class Group {
    @Id
    @GeneratedValue
    private long id;
    private String name;

    @OneToMany(mappedBy="group", cascade=CascadeType.ALL)
    private List<Contact> contacts = new ArrayList<Contact>();

    public Group(String name) {
        this.name = name;
    }

    public GroupDTO toDTO(){
        List <ContactDTO> res = new ArrayList<>();
        for(Contact contact: contacts){
            res.add(contact.toDTO());
        }
        GroupDTO groupDTO = new GroupDTO(name);
        groupDTO.setContacts(res);
        groupDTO.setId(id);
        return groupDTO;
    }

    public static Group fromDTO(GroupDTO groupDTO){
        Group group = new Group();
        if(groupDTO==null){
            group = null;
        }
        else {
            group = new Group(groupDTO.getName());
            List<Contact> list = new ArrayList<>();
            for (ContactDTO contactDTO : groupDTO.getContacts()) {
                list.add(Contact.fromDTO(contactDTO));
            }
            group.setContacts(list);
            group.setId(groupDTO.getId());
        }
        return group;
    }
}
