package com.simplecash.service;

import com.simplecash.entity.Conseiller;
import com.simplecash.entity.Gerant;

public interface GerantService {

    Gerant getById(Long id);

    Conseiller addConseiller(Long gerantId, Conseiller conseiller);
}
