package com.workforce;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.workforce.data.StructureCleaner;
import com.workforce.service.CleanerService;

/**
 * @author mahnaz
 * @Aug 7, 2019
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class CleanerServiceTest {

	@Autowired
	private CleanerService cleanerService;

	@Test
	public void getFirstOptimizedCleaners() {
		Integer[] rooms = {35,21,17,28};
		int seniorCapacity = 10;
		int juniorCapacity = 6;

		List<StructureCleaner> result = cleanerService.getOptimizedCleaners(rooms, seniorCapacity, juniorCapacity);
		Assert.assertEquals(result, mockFirstStructureCleaners());
	}
	
	@Test
	public void getSecondOptimizedCleaners() {
		Integer[] rooms = {24,28};
		int seniorCapacity = 11;
		int juniorCapacity = 6;

		List<StructureCleaner> result = cleanerService.getOptimizedCleaners(rooms, seniorCapacity, juniorCapacity);
		Assert.assertEquals(result, mockSecondStructureCleaners());
	}

	private List<StructureCleaner> mockFirstStructureCleaners(){
		List<StructureCleaner> cleaners = new ArrayList<StructureCleaner>();
		Integer[] rooms = {35,21,17,28};		
		cleaners.add(new StructureCleaner(rooms[0], 3,1));
		cleaners.add(new StructureCleaner(rooms[1], 1,2));
		cleaners.add(new StructureCleaner(rooms[2], 2,0));
		cleaners.add(new StructureCleaner(rooms[3], 1,3));
		return cleaners;
	}
	
	private List<StructureCleaner> mockSecondStructureCleaners(){
		List<StructureCleaner> cleaners = new ArrayList<StructureCleaner>();
		Integer[] rooms = {24,28};		
		cleaners.add(new StructureCleaner(rooms[0], 2,1));
		cleaners.add(new StructureCleaner(rooms[1], 2,1));
		return cleaners;
	}

}
