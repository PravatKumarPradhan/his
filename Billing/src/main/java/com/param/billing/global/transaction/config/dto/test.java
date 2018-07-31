package com.param.billing.global.transaction.config.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class test {
	
	public static void main(String[] args) {
		/*SortedMap<String, Integer>  map = new TreeMap<String, Integer> ();
		map.put( "PT", 1);
		map.put( "BT", 6);
		map.put( "PET", 4);
		map.put( "ST", 2);*/

		List<MySequenceDto> listMyDto = new ArrayList();
		listMyDto.add(new MySequenceDto(4, "PT"));
		listMyDto.add(new MySequenceDto(2, "BT"));
		listMyDto.add(new MySequenceDto(3, "PET"));
		listMyDto.add(new MySequenceDto(4, "ST"));
		Collections.sort(listMyDto, new MySequenceDto());
		
		/*Collections.sort(listMyDto, new Comparator<MySequenceDto>() {
		    @Override
		    public int compare(MySequenceDto o1, MySequenceDto o2) {
		        return o1.getSeqNo().compareTo(o2.getSeqNo());
		    }
		});*/
		for(MySequenceDto m : listMyDto){
			System.out.println(m.getSeqNo() + "-"+ m.getSeqDesc());
		}
		System.out.println("map : "+ listMyDto.toString());
	}

}
