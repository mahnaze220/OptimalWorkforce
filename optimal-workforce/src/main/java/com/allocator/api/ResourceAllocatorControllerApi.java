package com.allocator.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;

import com.allocator.dto.StructureResourceDto;
import com.allocator.dto.StructureDto;

/**
 * @author mahnaz
 * @Aug 7, 2019
 *
 */
public interface ResourceAllocatorControllerApi {

	public @ResponseBody ResponseEntity<List<StructureResourceDto>> getOptimizedResources(StructureDto structureDto);
}
