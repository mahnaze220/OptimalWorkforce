package com.allocator.api;

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

import com.allocator.data.StructureResource;
import com.allocator.dto.StructureResourceDto;
import com.allocator.dto.StructureDto;
import com.allocator.mapper.StructureResourceMapper;
import com.allocator.service.ResourceAllocatorService;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author mahnaz
 * @Aug 7, 2019
 *
 */

@RestController
@Api(value = "Resource Allocator Controller", description="Allocating resources to every structure")
public class ResourceAllocatorController implements ResourceAllocatorControllerApi {

	private static final Logger log = LoggerFactory.getLogger(ResourceAllocatorController.class);

	@Autowired
	private ResourceAllocatorService resourceAllocatorService;

	@Autowired
	private StructureResourceMapper structureResourceMapper;

	private final ObjectMapper objectMapper;

	private final HttpServletRequest request;

	@Autowired
	public ResourceAllocatorController(ObjectMapper objectMapper, HttpServletRequest request) {
		this.objectMapper = objectMapper;
		this.request = request;
	}

	@RequestMapping(value = "/structureResources", method = RequestMethod.GET)
	@ApiOperation(value = "Find and allocate optimal numbers of senior and junior resources for each structure")
	public ResponseEntity<List<StructureResourceDto>> getOptimizedResources(@RequestBody StructureDto structureDto) {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {
			List<StructureResource> structureResources = resourceAllocatorService.getOptimizedResources(structureDto.getRooms(), 
					structureDto.getSeniorCapacity(), structureDto.getJuniorCapacity());

			List<StructureResourceDto> resourcesDtos = structureResourceMapper.structureResourceToStructureResourceDto().apply(structureResources);
			try {
				return new ResponseEntity<List<StructureResourceDto>>(resourcesDtos, HttpStatus.OK);
			} catch (Exception e) {
				log.error("Couldn't serialize response for content type application/json", e);
			}
		}
		return new ResponseEntity<List<StructureResourceDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
