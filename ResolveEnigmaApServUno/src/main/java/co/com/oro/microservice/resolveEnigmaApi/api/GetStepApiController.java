package co.com.oro.microservice.resolveEnigmaApi.api;

import co.com.oro.microservice.resolveEnigmaApi.model.ErrorDetail;
import co.com.oro.microservice.resolveEnigmaApi.model.GetEnigmaRequest;
import co.com.oro.microservice.resolveEnigmaApi.model.GetEnigmaStepResponse;
import co.com.oro.microservice.resolveEnigmaApi.model.Header;
import co.com.oro.microservice.resolveEnigmaApi.model.JsonApiBodyRequest;
import co.com.oro.microservice.resolveEnigmaApi.model.JsonApiBodyResponseErrors;
import co.com.oro.microservice.resolveEnigmaApi.model.JsonApiBodyResponseSuccess;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-02-27T20:18:23.958-05:00[America/Bogota]")
@Controller
public class GetStepApiController implements GetStepApi {

    private static final Logger log = LoggerFactory.getLogger(GetStepApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public GetStepApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<JsonApiBodyResponseSuccess>> getStep(@ApiParam(value = "Get one enigma step API" ,required=true )  @Valid @RequestBody JsonApiBodyRequest body) {
        
    	
    	String accept = request.getHeader("Accept");
        //return new ResponseEntity<List<JsonApiBodyResponseSuccess>>(HttpStatus.NOT_IMPLEMENTED);
        
        List<GetEnigmaRequest> enigmas = body.getData();
        
        List<JsonApiBodyResponseSuccess> responseData = new ArrayList<>();
        
        for(int idx=0;idx<enigmas.size();idx++) {
        	String enigma = enigmas.get(idx).getEnigma();
        	Header header = enigmas.get(idx).getHeader(); // You need to set properties of Header
            String answer = getAnswer(enigma);
            header.setId(header.getId());
            header.setType(header.getType());
            GetEnigmaStepResponse response = new GetEnigmaStepResponse().header(header).answer(answer);
            responseData.add(new JsonApiBodyResponseSuccess().addDataItem(response));
        }
        

       
        return new ResponseEntity<>(responseData, HttpStatus.OK);
        
    }
    
    public String getAnswer(String enigma) {
    	if(enigma.equalsIgnoreCase("1")) {
    		return " Abrir el refrigerador";
    		
    	}else {
    		return "Este es el servicio encargado de entregar el primer paso, verifique el endpoint correcto";
    	}
    }

}
