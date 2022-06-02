package com.curso.poo.config;

import java.util.ArrayList;
import java.util.List;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

public class DozerConverter {

	private static final Mapper mapper = DozerBeanMapperBuilder.buildDefault();
	
	public static <O, D>D parseObjetct(O origin, Class<D> destination){
		return mapper.map(origin, destination);
	}
	
	public static <O, D> List<D> parseListObject(List<O> origins, Class<D> destination){
		List<D> listObjectReturn = new ArrayList<>();
		for(Object o:origins) {
			listObjectReturn.add(mapper.map(o, destination));
		}
		return listObjectReturn;
	}
}
