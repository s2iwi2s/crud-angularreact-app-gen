package com.myapp.crud.appgen;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {
	public Response(ResponseStatus status) {
		this.setResponseStatus(status);
	}
	private ResponseStatus responseStatus;
}
