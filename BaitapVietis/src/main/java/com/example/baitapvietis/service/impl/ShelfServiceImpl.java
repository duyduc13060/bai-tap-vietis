package com.example.baitapvietis.service.impl;

import com.example.baitapvietis.exception.NotFoundException;
import com.example.baitapvietis.model.entity.ShelfEntity;
import com.example.baitapvietis.model.entity.WasehouseEntity;
import com.example.baitapvietis.repository.ShelfRepository;
import com.example.baitapvietis.repository.WasehouseRepository;
import com.example.baitapvietis.service.ShelfService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShelfServiceImpl implements ShelfService {

    private final ShelfRepository shelfRepository;
    private final WasehouseRepository wasehouseRepository;

    public ShelfServiceImpl(
            ShelfRepository shelfRepository,
            WasehouseRepository wasehouseRepository

    ){
        this.shelfRepository = shelfRepository;
        this.wasehouseRepository = wasehouseRepository;
    }

    @Override
    public List<ShelfEntity> getALl(){
        return shelfRepository.findAll();
    }

    @Override
    public Optional<ShelfEntity> findById(Long id){
        return shelfRepository.findById(id);
    }

    @Override
    public ShelfEntity create(ShelfEntity shelfEntity){
        Optional<WasehouseEntity> findByWasehouseId = Optional.ofNullable(wasehouseRepository.findById(shelfEntity.getWasehoueId())
                .orElseThrow(() -> new NotFoundException("Id not found")));

        return shelfRepository.save(shelfEntity);
    }


    @Override
    public ShelfEntity update(Long id, ShelfEntity shelfEntity){
        Optional<ShelfEntity> findById = Optional.ofNullable(shelfRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Shelf Id not found" + id)));

        Optional<WasehouseEntity> findByWasehouseId = Optional.ofNullable(wasehouseRepository.findById(shelfEntity.getWasehoueId())
                .orElseThrow(() -> new NotFoundException("Wasehouse Id not found")));

        return shelfRepository.save(shelfEntity);
    }



}
