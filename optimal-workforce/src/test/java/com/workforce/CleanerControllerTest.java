package com.workforce;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.workforce.data.StructureCleaner;
import com.workforce.dto.StructureCleanerDto;
import com.workforce.dto.StructureDto;
import com.workforce.mapper.StructureCleanerMapper;
import com.workforce.service.CleanerService;

/**
 * @author mahnaz
 * @Aug 7, 2019
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CleanerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private StructureCleanerMapper structureCleanerMapper;

	@MockBean
	private CleanerService cleanerService;

	@Test
	public void get_cleaners() throws Exception{
		Integer[] rooms = {35,21,17,28};
		int seniorCapacity = 10;
		int juniorCapacity = 6;

		Mockito.when(cleanerService.getOptimizedCleaners(rooms, seniorCapacity, juniorCapacity)).thenReturn(mockFirstStructureCleaners());
		Mockito.when(structureCleanerMapper.structureCleanerToStructureCleanerDto()).thenReturn(mockFirstStructureCleanerDto());
		String resultJson = convertObjectToJson(mockStructureCleanerDtos());
		
		this.mockMvc.perform(get("/cleaners").contentType(MediaType.APPLICATION_JSON)
				.header("accept", MediaType.APPLICATION_JSON)
				.content(convertObjectToJson(new StructureDto(rooms, seniorCapacity, juniorCapacity)))).andDo(print())

		.andExpect(status().isOk())
		.andExpect(content().string(resultJson));
	}

	public List<StructureCleaner> mockFirstStructureCleaners(){
		List<StructureCleaner> cleaners = new ArrayList<StructureCleaner>();
		Integer[] rooms = {35,21,17,28};		
		cleaners.add(new StructureCleaner(rooms[0], 3, 1));
		cleaners.add(new StructureCleaner(rooms[1], 1, 2));
		cleaners.add(new StructureCleaner(rooms[2], 2, 0));
		cleaners.add(new StructureCleaner(rooms[3], 1, 3));
		return cleaners;
	}

	public Function<List<StructureCleaner>, List<StructureCleanerDto>> mockFirstStructureCleanerDto(){
		return new Function<List<StructureCleaner>, List<StructureCleanerDto>>(){
			@Override
			public List<StructureCleanerDto> apply(List<StructureCleaner> structureCleaner) {
				List<StructureCleanerDto> result = new ArrayList<StructureCleanerDto>();
				result.add(new StructureCleanerDto(35, 3, 1));
				result.add(new StructureCleanerDto(21, 1, 2));
				result.add(new StructureCleanerDto(17, 2, 0));
				result.add(new StructureCleanerDto(28, 1, 3));
				return result;
			}
		};
	}

	public String convertObjectToJson(Object object) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(object);
	}
	
	public List<StructureCleanerDto> mockStructureCleanerDtos(){
		List<StructureCleanerDto> cleaners = new ArrayList<StructureCleanerDto>();
		Integer[] rooms = {35,21,17,28};		
		cleaners.add(new StructureCleanerDto(rooms[0], 3, 1));
		cleaners.add(new StructureCleanerDto(rooms[1], 1, 2));
		cleaners.add(new StructureCleanerDto(rooms[2], 2, 0));
		cleaners.add(new StructureCleanerDto(rooms[3], 1, 3));
		return cleaners;
	}
}
