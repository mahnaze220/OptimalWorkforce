package com.allocator;

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

import com.allocator.data.StructureResource;
import com.allocator.dto.StructureResourceDto;
import com.allocator.dto.StructureDto;
import com.allocator.mapper.StructureResourceMapper;
import com.allocator.service.ResourceAllocatorService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author mahnaz
 * @Aug 7, 2019
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ResourceAllocatorControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private StructureResourceMapper structureResourceMapper;

	@MockBean
	private ResourceAllocatorService resourceAllocatorService;

	@Test
	public void getOptimizedResources_happySenario() throws Exception{
		Integer[] rooms = {35,21,17,28};
		int seniorCapacity = 10;
		int juniorCapacity = 6;

		Mockito.when(resourceAllocatorService.getOptimizedResources(rooms, seniorCapacity, juniorCapacity)).thenReturn(mockFirstStructureResources());
		Mockito.when(structureResourceMapper.structureResourceToStructureResourceDto()).thenReturn(mockFirstStructureResourceDto());
		String resultJson = convertObjectToJson(mockStructureResourceDtos());
		
		this.mockMvc.perform(get("/structureResources").contentType(MediaType.APPLICATION_JSON)
				.header("accept", MediaType.APPLICATION_JSON)
				.content(convertObjectToJson(new StructureDto(rooms, seniorCapacity, juniorCapacity)))).andDo(print())

		.andExpect(status().isOk())
		.andExpect(content().string(resultJson));
	}

	public List<StructureResource> mockFirstStructureResources(){
		List<StructureResource> strResources = new ArrayList<StructureResource>();
		Integer[] rooms = {35,21,17,28};		
		strResources.add(new StructureResource(rooms[0], 3, 1));
		strResources.add(new StructureResource(rooms[1], 1, 2));
		strResources.add(new StructureResource(rooms[2], 2, 0));
		strResources.add(new StructureResource(rooms[3], 1, 3));
		return strResources;
	}

	public Function<List<StructureResource>, List<StructureResourceDto>> mockFirstStructureResourceDto(){
		return new Function<List<StructureResource>, List<StructureResourceDto>>(){
			@Override
			public List<StructureResourceDto> apply(List<StructureResource> structureResource) {
				List<StructureResourceDto> result = new ArrayList<StructureResourceDto>();
				result.add(new StructureResourceDto(35, 3, 1));
				result.add(new StructureResourceDto(21, 1, 2));
				result.add(new StructureResourceDto(17, 2, 0));
				result.add(new StructureResourceDto(28, 1, 3));
				return result;
			}
		};
	}

	public String convertObjectToJson(Object object) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(object);
	}
	
	public List<StructureResourceDto> mockStructureResourceDtos(){
		List<StructureResourceDto> strResources = new ArrayList<StructureResourceDto>();
		Integer[] rooms = {35,21,17,28};		
		strResources.add(new StructureResourceDto(rooms[0], 3, 1));
		strResources.add(new StructureResourceDto(rooms[1], 1, 2));
		strResources.add(new StructureResourceDto(rooms[2], 2, 0));
		strResources.add(new StructureResourceDto(rooms[3], 1, 3));
		return strResources;
	}
}
