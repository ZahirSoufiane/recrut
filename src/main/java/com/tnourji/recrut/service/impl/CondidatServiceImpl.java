package com.tnourji.recrut.service.impl;

import com.tnourji.recrut.model.Condidat;
import com.tnourji.recrut.repository.CondidatRepository;
import com.tnourji.recrut.service.CondidatService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service Implementation for managing Condidat.
 */
@Service
@Transactional
public class CondidatServiceImpl implements CondidatService{

    private final Logger log = LoggerFactory.getLogger(CondidatServiceImpl.class);

    private final CondidatRepository condidatRepository;

    public CondidatServiceImpl(CondidatRepository condidatRepository) {
        this.condidatRepository = condidatRepository;
    }

    /**
     * Save a condidat.
     *
     * @param condidat the entity to save
     * @return the persisted entity
     */
    @Override
    public Condidat save(Condidat condidat) {
        log.debug("Request to save Condidat : {}", condidat);
        return condidatRepository.save(condidat);
    }

    /**
     * Get all the condidats.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Condidat> findAll() {
        log.debug("Request to get all Condidats");
        return condidatRepository.findAll();
    }

    /**
     * Get one condidat by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Condidat findOne(Long id) {
        log.debug("Request to get Condidat : {}", id);
        return condidatRepository.findOne(id);
    }

    /**
     * Delete the condidat by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Condidat : {}", id);
        condidatRepository.delete(id);
    }
}
