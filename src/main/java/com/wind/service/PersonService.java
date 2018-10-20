package com.wind.service;

import com.wind.dao.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("personService")
public class PersonService {

    @Autowired
    private PersonDao personDao;

    @Transactional(readOnly = false)
    public int updatePersonById(Integer id, String updateName) {
        return personDao.updatePersonById(id, updateName);
    }
}
