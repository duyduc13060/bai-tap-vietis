package com.example.baitapvietis.service.impl;

import com.example.baitapvietis.dao.AntenWasehouseDao;
import com.example.baitapvietis.exception.NotFoundException;
import com.example.baitapvietis.model.dto.AntenWasehouseDto;
import com.example.baitapvietis.model.entity.AntenEntity;
import com.example.baitapvietis.model.entity.ReaderWriterEntity;
import com.example.baitapvietis.model.entity.ShelfEntity;
import com.example.baitapvietis.model.entity.WasehouseEntity;
import com.example.baitapvietis.repository.AntenRepository;
import com.example.baitapvietis.repository.ReaderWriterRepository;
import com.example.baitapvietis.repository.ShelfRepository;
import com.example.baitapvietis.repository.WasehouseRepository;
import com.example.baitapvietis.service.AntenService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AntenServiceImpl implements AntenService {

    private final AntenRepository antenRepository;
    private final ShelfRepository shelfRepository;
    private final ReaderWriterRepository readerWriterRepository;
    private final AntenWasehouseDao antenWasehouseDao;
    private final WasehouseRepository wasehouseRepository;

    public AntenServiceImpl(
            AntenRepository antenRepository,
            ShelfRepository shelfRepository,
            ReaderWriterRepository readerWriterRepository,
            AntenWasehouseDao antenWasehouseDao,
            WasehouseRepository wasehouseRepository
    ){
        this.antenRepository = antenRepository;
        this.shelfRepository = shelfRepository;
        this.readerWriterRepository = readerWriterRepository;
        this.antenWasehouseDao = antenWasehouseDao;
        this.wasehouseRepository = wasehouseRepository;
    }

    @Override
    public List<AntenEntity> getAll(){
        return antenRepository.findAll();
    }

    @Override
    public List<AntenWasehouseDto> get(){
        return antenWasehouseDao.getAntenWasehouse();
    }

    @Override
    public Optional<AntenEntity> findById(Long id){
        return antenRepository.findById(id);
    }

    @Override
    public AntenEntity create(AntenEntity antenEntity){
        Optional<ShelfEntity> findByShelfId = Optional.ofNullable(shelfRepository.findById(antenEntity.getSheftId()).
                orElseThrow(() -> new NotFoundException("Shelf id not found" + antenEntity.getSheftId())));

        Optional<ReaderWriterEntity> findByReaderWriterId = Optional.ofNullable(readerWriterRepository.findById(antenEntity.getReaderWriterId()).
                orElseThrow(() -> new NotFoundException("Reader Writer Id not found" + antenEntity.getReaderWriterId())));

        return antenRepository.save(antenEntity);
    }

    @Override
    public AntenEntity update(Long id, AntenEntity antenEntity){
        Optional<AntenEntity> findById = Optional.ofNullable(antenRepository.findById(id).
                orElseThrow(() -> new NotFoundException("Anten id not found" + id)));
        AntenEntity anten = findById.get();

        return antenRepository.save(antenEntity);
    }

    @Override
    public void delete(Long id){
        Optional<AntenEntity> findById = Optional.ofNullable(antenRepository.findById(id).
                orElseThrow(() -> new NotFoundException("Anten not found id" + id)));
        AntenEntity anten = findById.get();
        antenRepository.deleteById(anten.getId());
    }









}
