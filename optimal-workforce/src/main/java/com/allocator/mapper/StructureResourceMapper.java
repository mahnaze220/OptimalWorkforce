package com.allocator.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.allocator.data.StructureResource;
import com.allocator.dto.StructureResourceDto;

/**
 * @author mahnaz
 * @Aug 7, 2019
 *
 */

@Component
public class StructureResourceMapper {

	public Function<List<StructureResource>, List<StructureResourceDto>> structureResourceToStructureResourceDto(){
		return new Function<List<StructureResource>, List<StructureResourceDto>>(){
			@Override
			public List<StructureResourceDto> apply(List<StructureResource> structureResources) {
				List<StructureResourceDto> result = new ArrayList<StructureResourceDto>();
				structureResources.stream().forEach(strResource -> result.add(new StructureResourceDto(strResource.getRoom(), strResource.getNumberOfSeniors(),
						strResource.getNumberOfJuniors())));
				return result;
			}
		};
	}
}
