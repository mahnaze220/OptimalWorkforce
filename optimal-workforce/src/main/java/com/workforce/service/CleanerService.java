package com.workforce.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.workforce.data.StructureCleaner;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author mahnaz
 * @Aug 7, 2019
 *
 */

@Service
@Api(value = "Clean Service", description = "Operations pertaining to employee in Employee Management System")
public class CleanerService {

	@ApiOperation(value = "View a list of available employees", response = List.class)
	public List<StructureCleaner> getOptimizedCleaners(Integer[] rooms, int seniorCapacity, int juniorCapacity) {

		List<StructureCleaner> cleaners = new ArrayList<StructureCleaner>();
		Map<String, Cleaner> result= new HashMap<String, Cleaner>();

		Arrays.stream(rooms).forEach(room -> {
			Integer newRoom = room;
			Map<String, Integer> tempMap = new HashMap<String, Integer>();
			if(room > seniorCapacity) {
				newRoom = newRoom - seniorCapacity;
			}
			for(int s = 0; s < seniorCapacity; s++) {
				for(int j = 0; j < juniorCapacity; j++) {
					if((s * seniorCapacity) + (j * juniorCapacity) >= newRoom) {
						tempMap.put(newRoom + "-" + s + ":" + j, (s * seniorCapacity) + (j * juniorCapacity));
						break;
					}
				}
			}

			//get optimal senior and juniors
			int[] minValue = {0};
			tempMap.keySet().forEach(temp -> {
				if(minValue[0] == 0) {
					minValue[0] = tempMap.get(temp);
				}
				if(tempMap.get(temp) < minValue[0]) {
					minValue[0] = tempMap.get(temp);
					String senior = temp.substring(temp.indexOf("-") + 1, temp.indexOf(":"));
					String junior = temp.substring(temp.indexOf(":") + 1, temp.length());
					Integer sValue = Integer.valueOf(senior);
					result.put(String.valueOf(room), new Cleaner(room > seniorCapacity ? sValue + 1 : sValue , Integer.valueOf(junior)));
				}
			});	

			StructureCleaner structureCleaner = new StructureCleaner(room, 
					result.get(String.valueOf(room)).getSeniorCleaner(), 
					result.get(String.valueOf(room)).getJuniorCleaner());
			cleaners.add(structureCleaner);
		});
		return cleaners;
	}
}
