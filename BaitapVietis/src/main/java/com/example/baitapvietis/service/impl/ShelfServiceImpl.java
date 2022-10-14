package com.example.baitapvietis.service.impl;

import com.example.baitapvietis.exception.NotFoundException;
import com.example.baitapvietis.model.entity.ShelfEntity;
import com.example.baitapvietis.model.entity.WarehouseEntity;
import com.example.baitapvietis.repository.ShelfRepository;
import com.example.baitapvietis.repository.WarehouseRepository;
import com.example.baitapvietis.service.ShelfService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShelfServiceImpl implements ShelfService {

    private final ShelfRepository shelfRepository;
    private final WarehouseRepository warehouseRepository;

    public ShelfServiceImpl(
            ShelfRepository shelfRepository,
            WarehouseRepository warehouseRepository

    ){
        this.shelfRepository = shelfRepository;
        this.warehouseRepository = warehouseRepository;
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
        Optional<WarehouseEntity> findByWasehouseId = Optional.ofNullable(warehouseRepository.findById(shelfEntity.getWasehoueId())
                .orElseThrow(() -> new NotFoundException("Id not found")));

        return shelfRepository.save(shelfEntity);
    }


    @Override
    public ShelfEntity update(Long id, ShelfEntity shelfEntity){
        Optional<ShelfEntity> findById = Optional.ofNullable(shelfRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Shelf Id not found" + id)));

        Optional<WarehouseEntity> findByWasehouseId = Optional.ofNullable(warehouseRepository.findById(shelfEntity.getWasehoueId())
                .orElseThrow(() -> new NotFoundException("Wasehouse Id not found")));

        return shelfRepository.save(shelfEntity);
    }

    @Override
    public void delete(Long id){
        Optional<ShelfEntity> findById = Optional.ofNullable(shelfRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Shelfs id not found" + id)));

        ShelfEntity shelf = findById.get();
        shelfRepository.deleteById(shelf.getId());
    }

    @Override
    public List<ShelfEntity> search(Long id){
//        Optional<WasehouseEntity> findByWasehousId = Optional.ofNullable(wasehouseRepository.findById(id)
//                .orElseThrow(() -> new NotFoundException("Wasehouse Id not found" + id)));

        Optional<WarehouseEntity> findByWasehousId = warehouseRepository.findById(id);

        List<ShelfEntity> shelfEntityList = null;
        if(!findByWasehousId.isPresent()){
            shelfEntityList = shelfRepository.findAll();
            return shelfEntityList;
        }else{
            shelfEntityList = shelfRepository.findByWasehoue(id);
            return shelfEntityList;
        }
    }



}
