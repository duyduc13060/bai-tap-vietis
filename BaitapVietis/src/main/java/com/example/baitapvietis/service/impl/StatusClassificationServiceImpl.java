package com.example.baitapvietis.service.impl;

import com.example.baitapvietis.model.entity.StatusClassficationEntity;
import com.example.baitapvietis.repository.StatusClassficationRepository;
import com.example.baitapvietis.service.StatusClassificationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusClassificationServiceImpl implements StatusClassificationService {

    private final StatusClassficationRepository classficationRepository;

    public StatusClassificationServiceImpl(StatusClassficationRepository classficationRepository){
        this.classficationRepository = classficationRepository;
    }


    @Override
    public Optional<StatusClassficationEntity> findById(Long id){
        return classficationRepository.findById(id);
    }

    @Override
    public List<StatusClassficationEntity> findAll(){
        return classficationRepository.findAll();
    }


}
