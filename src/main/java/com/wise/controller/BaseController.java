package com.wise.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseController {    

    @SuppressWarnings("rawtypes")
	protected static Map<String, Object> getResponseMap(List data) {
    	return getResponseMap(data, data != null ? data.size() : 0);
    }
    
    @SuppressWarnings("rawtypes")
	protected static Map<String, Object> getTwoList(List abierta, List compensada) {
    	Map<String, Object> model = new HashMap<String, Object>(6);
    	model.put("totalListOne", abierta != null ? abierta.size() : 0);
    	model.put("totalListTwo", compensada != null ? compensada.size() : 0);
    	model.put("abierta", abierta);
    	model.put("success", true);
    	model.put("compensada", compensada);
    	model.put("msg","Successful");
    	
    	return model;
    }

/**
* Generates modelMap to return in the modelAndView
*
* @param total
* @return
*/
    @SuppressWarnings("rawtypes")
	protected static Map<String, Object> getResponseMap(List data, int total) {

		Map<String, Object> modelMap = new HashMap<String, Object>(4);
		modelMap.put("total", total);
		modelMap.put("data", data);
		modelMap.put("success", true);
		modelMap.put("msg", "Successful");

		return modelMap;
    }


/**
* Generates modelMap to return in the modelAndView
*
* @param total
* @return
*/
    protected static Map<String, Object> getResponseMapFromObject(Object data) {

		Map<String, Object> modelMap = new HashMap<String, Object>(4);
		modelMap.put("data", data);
		modelMap.put("success", true);
		modelMap.put("msg", "Successful");
		
		return modelMap;
    }

/**
* Generates modelMap to return in the modelAndView in case
* of exception
*
* @param msg message
* @return
*/
    protected static Map<String, Object> getModelMapError(String msg) {

		Map<String, Object> modelMap = new HashMap<String, Object>(2);
		modelMap.put("success", false);
		modelMap.put("msg", msg);
		
		return modelMap;
    }

    protected static Map<String, Object> getModelMapSuccess(String msg) {

		Map<String, Object> modelMap = new HashMap<String, Object>(2);
		modelMap.put("success", true);
		modelMap.put("msg", msg);
		
		return modelMap;
    }	
}
