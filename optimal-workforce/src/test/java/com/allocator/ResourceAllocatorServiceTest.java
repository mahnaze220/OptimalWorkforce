package com.allocator;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.allocator.data.StructureResource;
import com.allocator.service.ResourceAllocatorService;

/**
 * @author mahnaz
 * @Aug 7, 2019
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ResourceAllocatorServiceTest {

	@Autowired
	private ResourceAllocatorService resourceAllocatorService;

	@Test
	public void getFirstOptimizedResources() {
		Integer[] rooms = {35,21,17,28};
		int seniorCapacity = 10;
		int juniorCapacity = 6;

		List<StructureResource> result = resourceAllocatorService.getOptimizedResources(rooms, seniorCapacity, juniorCapacity);
		Assert.assertEquals(result, mockFirstStructureResources());
	}
	
	@Test
	public void getSecondOptimizedResources() {
		Integer[] rooms = {24,28};
		int seniorCapacity = 11;
		int juniorCapacity = 6;

		List<StructureResource> result = resourceAllocatorService.getOptimizedResources(rooms, seniorCapacity, juniorCapacity);
		Assert.assertEquals(result, mockSecondStructureResources());
	}

	@Test
	public void getOptimizedResourcesForNegativeRoom() {
		Integer[] rooms = {-24,28};
		int seniorCapacity = 11;
		int juniorCapacity = 6;

		List<StructureResource> result = resourceAllocatorService.getOptimizedResources(rooms, seniorCapacity, juniorCapacity);
		Assert.assertEquals(result, mockNegativeStructureResources());
	}

	private List<StructureResource> mockFirstStructureResources(){
		List<StructureResource> strResources = new ArrayList<StructureResource>();
		Integer[] rooms = {35,21,17,28};		
		strResources.add(new StructureResource(rooms[0], 3,1));
		strResources.add(new StructureResource(rooms[1], 1,2));
		strResources.add(new StructureResource(rooms[2], 2,0));
		strResources.add(new StructureResource(rooms[3], 1,3));
		return strResources;
	}
	
	private List<StructureResource> mockSecondStructureResources(){
		List<StructureResource> strResouces = new ArrayList<StructureResource>();
		Integer[] rooms = {24,28};		
		strResouces.add(new StructureResource(rooms[0], 2,1));
		strResouces.add(new StructureResource(rooms[1], 2,1));
		return strResouces;
	}
	
	private List<StructureResource> mockNegativeStructureResources(){
		List<StructureResource> strResources = new ArrayList<StructureResource>();
		Integer[] rooms = {-24,28};		
		strResources.add(new StructureResource(rooms[1], 2,1));
		return strResources;
	}

}
