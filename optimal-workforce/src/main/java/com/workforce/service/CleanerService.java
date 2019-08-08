package com.workforce.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.stream.IntStream;

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
@Api(value = "Clean Service", description = "Get optimal senior and junior cleaners of a structure")
public class CleanerService {

	@ApiOperation(value = "Get optimal number of seniors and juniors worker for a structures", response = List.class)
	public List<StructureCleaner> getOptimizedCleaners(Integer[] rooms, int seniorCapacity, int juniorCapacity) {

		List<StructureCleaner> cleaners = new ArrayList<StructureCleaner>();
		Map<String, Cleaner> result= new HashMap<String, Cleaner>();

		Arrays.stream(rooms).forEach(room -> {
			if(room != null && room.intValue() > 0) {
				Integer[] newRoom = {room};
				Map<String, Integer> tempMap = new HashMap<String, Integer>();

				/*Each structure needs at least one senior cleaner, so according to number of input rooms, 
				  initially we should allocate one senior to each room and divide the remaining capacity optimally 
				 */ 
				if(room > seniorCapacity) {
					newRoom[0] = newRoom[0].intValue() - seniorCapacity;
				}

				IntStream.range(0, seniorCapacity-1).forEach(s -> {
					OptionalInt jun = IntStream.range(0, juniorCapacity-1).filter(j -> ((s * seniorCapacity) + (j * juniorCapacity) >= newRoom[0])).findFirst();
					if(jun != null && jun.isPresent()) {
						tempMap.put(newRoom[0] + "-" + s + ":" + jun.getAsInt(), (s * seniorCapacity) + (jun.getAsInt() * juniorCapacity));
					}
				});


				/* get optimal seniors and juniors based on the minimum value to meet the room size */
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
			}
		});

		return cleaners;
	}
}
