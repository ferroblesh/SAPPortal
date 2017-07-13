package com.wise.controller;

import javax.servlet.http.HttpSession;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ScreenController extends BaseController {
	
//	private static final Logger LOGGER = Logger.getLogger(LoginController.class);
	
	@RequestMapping(value="/EstatusRecepcion", method = RequestMethod.GET)
	public ModelAndView getEstatusIntegracion(HttpSession session) {
		ModelAndView page = new ModelAndView("estatusIntegracion");
		return page;
	}
	
	@RequestMapping(value="/oc/cfdici", method = RequestMethod.GET)
	public ModelAndView getCFDICI() {
		return new ModelAndView("oc/cfdici");
	}
	
	@RequestMapping(value="/oc/scfdici", method = RequestMethod.GET)
	public ModelAndView getSCFDICI() {
		return new ModelAndView("oc/scfdici");
	}
	
	@RequestMapping(value="/inactive", method = RequestMethod.GET)
	public String getInactivePage(){
		return "inactive";
	}
	
	@RequestMapping(value="/oc/cfdi", method = RequestMethod.GET)
	public ModelAndView getOCCfdi(HttpSession session) {
		ModelAndView page = new ModelAndView("oc/cfdi");
		
		return page;
	}
	
	@RequestMapping(value="/oc/cbb", method = RequestMethod.GET)
	public ModelAndView getOCCCbb(HttpSession session) {
		ModelAndView page = new ModelAndView("oc/cbb");
		return page;
	}
	
	@RequestMapping(value="/soc/cfdi", method = RequestMethod.GET)
	public ModelAndView getSOCCCfdi(HttpSession session) {
		ModelAndView page = new ModelAndView("soc/cfdi");
		return page;
	}
	
	@RequestMapping(value="/soc/cbb", method = RequestMethod.GET)
	public ModelAndView getSOCCCbb(HttpSession session) {
		ModelAndView page = new ModelAndView("soc/cbb");
		return page;
	}
	
	@RequestMapping(value="/fse/add", method = RequestMethod.GET)
	public ModelAndView getFSEAdd() {
		ModelAndView page = new ModelAndView("fse/add");
		return page;
	}
	
	@RequestMapping(value="/fse/addCFDI", method = RequestMethod.GET)
	public ModelAndView getFSEAddCFDI() {
		ModelAndView page = new ModelAndView("fse/addCFDI");
		return page;
	}
	
	@RequestMapping(value="/fse/addSCFDI", method = RequestMethod.GET)
	public ModelAndView getFSEAddSCFDI() {
		ModelAndView page = new ModelAndView("fse/addSCFDI");
		return page;
	}
	
	@RequestMapping(value="/fse/search", method = RequestMethod.GET)
	public ModelAndView getFSESearch() {
		ModelAndView page = new ModelAndView("fse/search");
		return page;
	}
	
	@RequestMapping(value="/OCSinFacturar", method = RequestMethod.GET)
	public ModelAndView getOCSinFacutrar() {
		ModelAndView page = new ModelAndView("OCSinFacturar");
		return page;
	}
	
	@RequestMapping(value="/EstadoCuenta", method = RequestMethod.GET)
	public ModelAndView getEstadoCuenta() {
		ModelAndView page = new ModelAndView("EstadoCuenta");
		return page;
	}
	
	@RequestMapping(value="/Reportes", method = RequestMethod.GET)
	public String getReportes() {
		return "reports";
	}
	
	@RequestMapping(value="/avisos", method = RequestMethod.GET)
	public ModelAndView getAvisos() {
		ModelAndView page = new ModelAndView("notifications");
		return page;
	}
	
	@RequestMapping(value="/PackingList", method = RequestMethod.GET)
	public ModelAndView getPackingList() {
		ModelAndView page = new ModelAndView("packingList");
		return page;
	}
	
}
