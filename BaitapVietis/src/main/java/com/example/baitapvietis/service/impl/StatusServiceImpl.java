package com.example.baitapvietis.service.impl;

import com.example.baitapvietis.exception.NotFoundException;
import com.example.baitapvietis.model.dto.StatusClassficationDto;
import com.example.baitapvietis.model.entity.StatusClassficationEntity;
import com.example.baitapvietis.model.entity.StatusEntity;
import com.example.baitapvietis.repository.StatusRepository;
import com.example.baitapvietis.service.StatusClassificationService;
import com.example.baitapvietis.service.StatusService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusServiceImpl implements StatusService {

    private final StatusRepository statusRepository;

    private final StatusClassificationService classificationService;


    public StatusServiceImpl(
            StatusRepository statusRepository,
            StatusClassificationService classificationService
    ){
        this.statusRepository = statusRepository;
        this.classificationService = classificationService;
    }


    @Override
    public List<StatusClassficationDto> findAll(){
        return statusRepository.listStatusClassification();
    }

    @Override
    public StatusEntity findById(Long id){
        StatusEntity findByid = statusRepository.findById(id)
                .orElseThrow(()->new NotFoundException("Status id not found" + id));
        return findByid;
    }

    @Override
    public StatusEntity create(StatusEntity statusEntity){
        Optional<StatusClassficationEntity> findByClassificationId = Optional.ofNullable(classificationService.findById(statusEntity.getClassFicationId()).
                orElseThrow(() -> new NotFoundException("Classfication id not found" + statusEntity.getClassFicationId())));

        return statusRepository.save(statusEntity);
    }

    @Override
    public StatusEntity update(Long id, StatusEntity statusEntity){
        Optional<StatusEntity> findById = Optional.ofNullable(statusRepository.findById(id).
                orElseThrow(() -> new NotFoundException("Status id not found" + id)));

        return statusRepository.save(statusEntity);
    }

    @Override
    public void delete(Long id){
        Optional<StatusEntity> findById = Optional.ofNullable(statusRepository.findById(id).
                orElseThrow(() -> new NotFoundException("Status id not found" + id)));
        StatusEntity statusEntity = findById.get();
        statusRepository.deleteById(statusEntity.getId());
    }



}
