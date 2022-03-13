package com.restApi.security.jwt.Api.exeptions;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String resourceName;
	private String fieldName;
	private long fielValue;
        private String field;

	public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
		super(String.format("%s not found with %s -> '%s'", resourceName, fieldName, fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fielValue = fieldValue;
	}
        public ResourceNotFoundException(String resourceName, String fieldName, String field) {
		super(String.format("%s not found with %s -> '%s'", resourceName, fieldName, field));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.field = field;
	}


}
