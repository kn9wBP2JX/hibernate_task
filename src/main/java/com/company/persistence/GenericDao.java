package com.company.persistence;

import javassist.NotFoundException;

public interface GenericDao<En> {
    En save(En dto);

    boolean delete(En dto);

    En update(En dto);

    En find(Long id) throws NotFoundException;
}

