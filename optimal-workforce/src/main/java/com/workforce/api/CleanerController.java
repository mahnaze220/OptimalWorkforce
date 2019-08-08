package com.workforce.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.workforce.data.StructureCleaner;
import com.workforce.dto.StructureCleanerDto;
import com.workforce.dto.StructureDto;
import com.workforce.mapper.StructureCleanerMapper;
import com.workforce.service.CleanerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author mahnaz
 * @Aug 7, 2019
 *
 */

@RestController
@Api(value = "Cleaner Controller", description="Allocating cleaners to every structure")
public class CleanerController implements CleanerControllerApi {

	private static final Logger log = LoggerFactory.getLogger(CleanerController.class);

	@Autowired
	private CleanerService cleanerService;

	@Autowired
	private StructureCleanerMapper structureCleanerMapper;

	private final ObjectMapper objectMapper;

	private final HttpServletRequest request;

	@Autowired
	public CleanerController(ObjectMapper objectMapper, HttpServletRequest request) {
		this.objectMapper = objectMapper;
		this.request = request;
	}

	@RequestMapping(value = "/cleaners", method = RequestMethod.GET)
	@ApiOperation(value = "Find and allocate optimal numbers of senior and junior cleaners for each structure")
	public ResponseEntity<List<StructureCleanerDto>> getOptimizedCleaners(@RequestBody StructureDto structureDto) {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			List<StructureCleaner> cleaners = cleanerService.getOptimizedCleaners(structureDto.getRooms(), 
					structureDto.getSeniorCapacity(), structureDto.getJuniorCapacity());

			List<StructureCleanerDto> cleanerDtos = structureCleanerMapper.structureCleanerToStructureCleanerDto().apply(cleaners);
			try {
				return new ResponseEntity<List<StructureCleanerDto>>(cleanerDtos, HttpStatus.OK);
			} catch (Exception e) {
				log.error("Couldn't serialize response for content type application/json", e);
			}
		}
		return new ResponseEntity<List<StructureCleanerDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
