package com.workforce.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;

import com.workforce.dto.StructureCleanerDto;
import com.workforce.dto.StructureDto;

/**
 * @author mahnaz
 * @Aug 7, 2019
 *
 */
public interface CleanerControllerApi {

	public @ResponseBody ResponseEntity<List<StructureCleanerDto>> getOptimizedCleaners(StructureDto structureDto);
}
