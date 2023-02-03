package com.vitg.mappers;

public class ModelMapper<D, E> {

	private org.modelmapper.ModelMapper modelMapper;

	private final Class<D> dtoClass;

	private final Class<E> entityClass;

	public ModelMapper(org.modelmapper.ModelMapper modelMapper, Class<D> dtoClass, Class<E> eClass) {
		super();
		this.modelMapper = modelMapper;
		this.dtoClass = dtoClass;
		this.entityClass = eClass;
	}

	/**
	 * @param entity that to be converted to DTO
	 * @return converted DTO
	 */
	public D entityToDto(E entity) {
		return modelMapper.map(entity, dtoClass);
	}

	/**
	 * 
	 * @param dto that has to be converted to entity
	 * @return converted entity
	 */
	public E dtoToEntity(D dto) {
		return modelMapper.map(dto, entityClass);
	}

	
}
