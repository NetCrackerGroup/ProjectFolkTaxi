package com.netcracker.DTO.mappers;

import java.util.Objects;

import com.netcracker.DTO.RouteDto;
import com.netcracker.DTO.UserDto;
import com.netcracker.entities.Route;
import com.netcracker.entities.User;

public class RouteMapper extends AbstractMapper<Route, RouteDto> {

	public RouteMapper() {
        super(Route.class, RouteDto.class);
    }
	
	@Override
	public RouteDto toDto(Route entity) {
		
		if(Objects.isNull(entity))
			return null;
		else {
			RouteDto res = mapper.map(entity, RouteDto.class);
			double[] coords = new double[2];
			coords[0] = entity.getRouteBegin().getX();
			coords[1] = entity.getRouteBegin().getY();
			res.setRouteBegin(coords);
			
			return res;
		}
		
	}
	
}
