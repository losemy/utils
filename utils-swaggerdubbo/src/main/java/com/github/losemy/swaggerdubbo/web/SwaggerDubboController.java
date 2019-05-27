package com.github.losemy.swaggerdubbo.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.losemy.swaggerdubbo.config.DubboPropertyConfig;
import com.github.losemy.swaggerdubbo.config.DubboServiceScanner;
import com.github.losemy.swaggerdubbo.config.SwaggerDocCache;
import com.github.losemy.swaggerdubbo.json.JSONString;
import com.github.losemy.swaggerdubbo.reader.Reader;
import io.swagger.annotations.Api;
import io.swagger.config.SwaggerConfig;
import io.swagger.models.Swagger;
import io.swagger.util.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("${swagger.dubbo.doc:swagger-dubbo}")
@Api(hidden = true)
public class SwaggerDubboController {

    public static final String DEFAULT_URL = "/api-docs";
    private static final String HAL_MEDIA_TYPE = "application/hal+json";
    
	@Autowired
	private DubboServiceScanner dubboServiceScanner;
	@Autowired
	private DubboPropertyConfig dubboPropertyConfig;
	@Autowired
	private SwaggerDocCache swaggerDocCache;

	@Value("${swagger.dubbo.http:dubbo}")
	private String httpContext;
	@Value("${swagger.dubbo.enable:true}")
	private boolean enable = true;

	@RequestMapping(value = DEFAULT_URL, 
	        method = RequestMethod.GET, 
	        produces = {"application/json; charset=utf-8", HAL_MEDIA_TYPE})
	@ResponseBody
	public ResponseEntity<JSONString> getApiList() throws JsonProcessingException {
		
		if (!enable){
			return new ResponseEntity<JSONString>(HttpStatus.NOT_FOUND);
		}
		
		Swagger swagger = swaggerDocCache.getSwagger();
		if (null != swagger){
			return new ResponseEntity<JSONString>(new JSONString(io.swagger.util.Json.mapper().writeValueAsString(swagger)), HttpStatus.OK);
		}else{
			swagger = new Swagger();
		}

		final SwaggerConfig configurator = dubboPropertyConfig;
		if (configurator != null) {
			configurator.configure(swagger);
		}

		//需要对数据做聚合操作

        System.out.println();


		Map<Class<?>, Object> interfaceMapRef = dubboServiceScanner.interfaceMapRef();
		if (null != interfaceMapRef) {
			Reader.read(swagger, interfaceMapRef, httpContext);
		}
		swaggerDocCache.setSwagger(swagger);
		return new ResponseEntity<JSONString>(new JSONString(Json.mapper().writeValueAsString(swagger)), HttpStatus.OK);
	}

}
