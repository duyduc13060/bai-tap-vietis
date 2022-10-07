package com.example.baitapvietis.service.impl;

import com.example.baitapvietis.model.entity.WasehouseEntity;
import com.example.baitapvietis.repository.WasehouseRepository;
import com.example.baitapvietis.service.WasehouseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WasehoseServiceImpl implements WasehouseService {

    private final WasehouseRepository wasehouseRepository;

    public WasehoseServiceImpl(WasehouseRepository wasehouseRepository){
        this.wasehouseRepository = wasehouseRepository;
    }

    @Override
    public List<WasehouseEntity> getAll(){
        return wasehouseRepository.findAll();
    }

    @Override
    public Optional<WasehouseEntity> findById(Long id){
        return wasehouseRepository.findById(id);
    }

    @Override
    public WasehouseEntity createWasehouse(WasehouseEntity wasehouseEntity){
        return wasehouseRepository.save(wasehouseEntity);
    }


    @Override
    public WasehouseEntity updateWasehouse(Long id, WasehouseEntity wasehouseEntity){
        Optional<WasehouseEntity> findById = wasehouseRepository.findById(id);
        if(!findById.isPresent()) return null;

        return wasehouseRepository.save(wasehouseEntity);
    }

    @Override
    public boolean deleteWasehouse(Long id){
        Optional<WasehouseEntity> findById = wasehouseRepository.findById(id);
        if(!findById.isPresent()) return false;

        WasehouseEntity wasehouse = findById.get();
        wasehouseRepository.deleteById(wasehouse.getId());

        return true;
    }





}
