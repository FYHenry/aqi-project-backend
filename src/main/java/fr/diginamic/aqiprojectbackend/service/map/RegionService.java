package fr.diginamic.aqiprojectbackend.service.map;

import fr.diginamic.aqiprojectbackend.dto.HttpStatusDtoOut;
import fr.diginamic.aqiprojectbackend.dto.map.in.RegionDtoIn;
import fr.diginamic.aqiprojectbackend.dto.map.out.RegionDtoOut;
import fr.diginamic.aqiprojectbackend.entity.map.Region;
import fr.diginamic.aqiprojectbackend.exception.EntityNotFoundException;
import fr.diginamic.aqiprojectbackend.repository.map.RegionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static fr.diginamic.aqiprojectbackend.util.Dtos.buildHttpStatusResponse;

/** Region service */
@Service
@Validated
public class RegionService {
    /** Region repository */
    private final RegionRepository regionRepository;

    /**
     * Contructor with parameters.
     * @param regionRepository Region repository
     */
    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }
    
    /**
     * Create region
     * @param body HTTP request body (region)
     * @param path HTTP request path
     * @return HTTP response (status)
     */
    public ResponseEntity<HttpStatusDtoOut>
    createRegion(RegionDtoIn body, String path){
        HttpStatus httpStatus;
        try {
            regionRepository.save(buildRegionFrom(body));
            httpStatus = HttpStatus.OK;
        } catch (EntityNotFoundException ex){
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return buildHttpStatusResponse(httpStatus, path);
    }
    
    /**
     * Read region
     * @param id Region identifier
     * @return HTTP response (region)
     */
    public ResponseEntity<RegionDtoOut> readRegion(int id){
        final Region region =
                regionRepository
                        .findById(id)
                        .orElseThrow(EntityNotFoundException::new);
        final RegionDtoOut regionDtoOut =
                buildRegionDtoOutFrom(region);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(regionDtoOut);
    }

    /**
     * Update region
     * @param id Region identifier
     * @param body HTTP request body (region)
     * @param path HTTP request path
     * @return HTTP response (status)
     */
    public ResponseEntity<HttpStatusDtoOut>
    updateRegion(int id, RegionDtoIn body, String path){
        final Region region = regionRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        region.setName(body.name());
        regionRepository.save(region);
        return buildHttpStatusResponse(HttpStatus.OK, path);
    }

    /**
     * Delete region
     * @param id User account identifier
     * @param path HTTP request path
     * @return HTTP response (status)
     */
    public ResponseEntity<HttpStatusDtoOut> deleteRegion(int id, String path){
        final Region region = regionRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        regionRepository.delete(region);
        return buildHttpStatusResponse(HttpStatus.OK, path);
    }

    /**
     * List regions
     * @return HTTP response (regions)
     */
    public ResponseEntity<List<RegionDtoOut>> listRegions(){
        final List<Region> regions =
                regionRepository.findAll();
        final List<RegionDtoOut> regionDtoOutList = regions
                .stream()
                .map(this::buildRegionDtoOutFrom)
                .toList();
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(regionDtoOutList);
    }

    /**
     * Build region from HTTP request body (region)
     * @param body HTTP request body (region)
     * @return Region
     */
    private Region buildRegionFrom(RegionDtoIn body) {
        return new Region(body.name());
    }

    /**
     * Build region as HTTP response from region
     * @param region Region
     * @return HTTP response (region)
     */
    private RegionDtoOut
    buildRegionDtoOutFrom(Region region){
        return new RegionDtoOut(region.getId(), region.getName());
    }
}
