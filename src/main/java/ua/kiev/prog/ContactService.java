package ua.kiev.prog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private GroupRepository groupRepository;

    @Transactional
    public void addContact(Contact contact) {
        contactRepository.save(contact); //Save Entity

    }

    @Transactional
    public void addGroup(Group group) {
        groupRepository.save(group); //Save Entity

    }

    @Transactional
    public void deleteContacts(long[] idList) {
        for (long id : idList)
            contactRepository.delete(id); //Delete Entity
    }

    @Transactional(readOnly=true)
    public List<GroupDTO> findGroups() {
        List<GroupDTO> all = new ArrayList<>();
        for(Group group:groupRepository.findAll()){
            all.add(group.toDTO());
        }
        return all;
    }

    @Transactional(readOnly=true)
    public List<ContactDTO> findAll(Pageable pageable) {
        List<ContactDTO> res = new ArrayList<>();
        for(Contact contact: contactRepository.findAll(pageable).getContent()){
            res.add(contact.toDTO());
        }
        return res;
    }

    @Transactional(readOnly=true)//реализован новый метод - выдать всег по группе по странично
    public List<ContactDTO> findByGroup(GroupDTO group, Pageable pageable) {
        List<ContactDTO> res = new ArrayList<>();
        for(Contact contact: contactRepository.findByGroup(Group.fromDTO(group), pageable)){
            res.add(contact.toDTO());
        }
        return res;
    }

    @Transactional(readOnly = true)//посчитать контакты в группе
    public long countByGroup(GroupDTO group) {

        return contactRepository.countByGroup(Group.fromDTO(group)); //Count by Entity
    }

    @Transactional(readOnly=true)//найти Лайк запросом контакт по странично
    public List<ContactDTO> findByPattern(String pattern, Pageable pageable) {
        List<ContactDTO> res = new ArrayList<>();
        for(Contact contact: contactRepository.findByPattern(pattern, pageable)){
            res.add(contact.toDTO());
        }
        return res;
    }

    @Transactional(readOnly = true) //колличество контактов
    public long count() {
        return contactRepository.count();
    }

    @Transactional(readOnly=true)
    public GroupDTO findGroup(long id) {
        return groupRepository.findOne(id).toDTO(); //find Entity by id
    }
}
