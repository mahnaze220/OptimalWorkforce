package com.workforce.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.workforce.data.StructureCleaner;
import com.workforce.dto.StructureCleanerDto;

/**
 * @author mahnaz
 * @Aug 7, 2019
 *
 */

@Component
public class StructureCleanerMapper {

	public Function<List<StructureCleaner>, List<StructureCleanerDto>> structureCleanerToStructureCleanerDto(){
		return new Function<List<StructureCleaner>, List<StructureCleanerDto>>(){
			@Override
			public List<StructureCleanerDto> apply(List<StructureCleaner> structureCleaners) {
				List<StructureCleanerDto> result = new ArrayList<StructureCleanerDto>();
				structureCleaners.stream().forEach(cleaner -> result.add(new StructureCleanerDto(cleaner.getRoom(), cleaner.getNumberOfSeniors(),
						cleaner.getNumberOfJuniors())));
				return result;
			}
		};
	}
}
