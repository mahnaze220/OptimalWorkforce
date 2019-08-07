package com.workforce.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.workforce.dto.StructureCleanerDto;
import com.workforce.dto.StructureDto;

/**
 * @author mahnaz
 * @Aug 7, 2019
 *
 */
public interface CleanerControllerApi {

	@RequestMapping(value = "/cleaners", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<StructureCleanerDto>> getOptimizedCleaners(StructureDto structureDto);
}
