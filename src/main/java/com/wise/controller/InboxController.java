package com.wise.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;

import org.apache.axis2.AxisFault;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.wise.model.InboxDeleteDto;
import com.wise.model.InboxDto;
import com.wise.model.UserDto;
import com.wise.service.InboxService;
import com.wise.viewResolver.ListInbox;

import functions.rfc.sap.document.sap_com.ZTL_BUZON;
import functions.rfc.sap.document.sap_com.ZTT_BUZON;
import functions.rfc.sap.document.sap_com.Z_FE_FM_AGREGAR_BUZONResponse;
import functions.rfc.sap.document.sap_com.Z_FE_FM_BORRAR_BUZONResponse;
import functions.rfc.sap.document.sap_com.Z_FE_FM_CONSULTAR_BUZONResponse;

@Controller
@RequestMapping("/inbox")
public class InboxController extends BaseController {
	
	private Logger LOGGER = Logger.getLogger(InboxController.class);
	
	@Autowired
	private InboxService inboxService;
	
	@Autowired
	private ListInbox listInbox;
	
	@RequestMapping(value = "/addInbox.action", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addInbox(@RequestParam("file") MultipartFile[] files, String cmbRazonSocial, String ebeln, HttpSession session, String hdnRazonSocial, HttpServletResponse responseServlet, String hdnFacturas, String referencia, HttpServletRequest request, String hdnDivision) throws IOException {
			HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper(responseServlet);
			wrapper.setContentType("text/html;charset=UTF-8");
			UserDto user = (UserDto) session.getAttribute("UserDetails");
			Locale locale = RequestContextUtils.getLocale(request);
			if(user != null) {
				try {
					Z_FE_FM_AGREGAR_BUZONResponse response = inboxService.addInbox(hdnRazonSocial, files, user.getI_LIFNR(), hdnFacturas, referencia, locale.getLanguage(), ebeln, hdnDivision);
					if(response != null){
						if(response.getE_TYPE().toString().equals("E")) {
							wrapper.getWriter().write("{success:false,msg:'"+response.getE_MSG().toString() +"'}");
							return null;
						}else {
							wrapper.getWriter().write("{success:true,msg:'"+response.getE_MSG().toString() +"'}");
							return null;
						}
					} else {
						wrapper.getWriter().write("{success:false,msg:'Error fatal'}");
						return null;
					}
				} catch (IOException e) {
					LOGGER.error("Error addInbox:", e);
					wrapper.getWriter().write("{success:false,msg:'"+e.getMessage() +"'}");
					return null;
				}
				
			} else {
				wrapper.getWriter().write("{success:false,msg:'Error de sesión'}");
				return null;
			}
					
	}
	
	@RequestMapping(value = "/addInboxCFDI.action", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addInboxCFDI(@RequestParam("file") MultipartFile[] files, String cmbRazonSocial, HttpSession session, String hdnRazonSocial, HttpServletResponse responseServlet, 
											String hdnFacturas, HttpServletRequest request, String hdnOrdenes, String hdnImportaciones, String cmbReceptionTypeField) throws IOException {
			HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper(responseServlet);
			wrapper.setContentType("text/html;charset=UTF-8");
			UserDto user = (UserDto) session.getAttribute("UserDetails");
			Locale locale = RequestContextUtils.getLocale(request);
			if(user != null) {
				try {
					Z_FE_FM_AGREGAR_BUZONResponse response = inboxService.addInboxCFDI(hdnRazonSocial, files, user.getI_LIFNR(), hdnOrdenes, hdnImportaciones, cmbReceptionTypeField, locale.getLanguage());
					if(response != null){
						if(response.getE_TYPE().toString().equals("E")) {
							wrapper.getWriter().write("{success:false,msg:'"+response.getE_MSG().toString() +"'}");
							return null;
						}else {
							wrapper.getWriter().write("{success:true,msg:'"+response.getE_MSG().toString() +"'}");
							return null;
						}
					} else {
						wrapper.getWriter().write("{success:false,msg:'Error fatal'}");
						return null;
					}
				} catch (IOException e) {
					LOGGER.error("Error addInboxCFDI:", e);
					wrapper.getWriter().write("{success:false,msg:'"+e.getMessage() +"'}");
					return null;
				}
				
			} else {
				wrapper.getWriter().write("{success:false,msg:'Error de sesión'}");
				return null;
			}
					
	}
	
	@RequestMapping(value = "/addInboxSCFDI.action", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addInboxSCFDI(@RequestParam("file") MultipartFile file, String cmbRazonSocial, HttpSession session, HttpServletResponse responseServlet, HttpServletRequest request,  
											 String hdnRazonSocial, String invoice, String invoiceDate, String iva, String hdnOrdenes, String hdnImportaciones, String cmbReceptionTypeField) throws IOException {
			HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper(responseServlet);
			wrapper.setContentType("text/html;charset=UTF-8");
			UserDto user = (UserDto) session.getAttribute("UserDetails");
			Locale locale = RequestContextUtils.getLocale(request);
			if(user != null) {
				try {
					Z_FE_FM_AGREGAR_BUZONResponse response = inboxService.addInboxSCFDI(hdnRazonSocial, file, user.getI_LIFNR(), invoice, invoiceDate, iva, hdnOrdenes, hdnImportaciones, cmbReceptionTypeField, locale.getLanguage());
					if(response != null){
						if(response.getE_TYPE().toString().equals("E")) {
							wrapper.getWriter().write("{success:false,msg:'"+response.getE_MSG().toString() +"'}");
							return null;
						}else {
							wrapper.getWriter().write("{success:true,msg:'"+response.getE_MSG().toString() +"'}");
							return null;
						}
					} else {
						wrapper.getWriter().write("{success:false,msg:'Error fatal'}");
						return null;
					}
				} catch (IOException e) {
					LOGGER.error("Error addInbox:", e);
					wrapper.getWriter().write("{success:false,msg:'"+e.getMessage() +"'}");
					return null;
				}
				
			} else {
				wrapper.getWriter().write("{success:false,msg:'Error de sesión'}");
				return null;
			}
					
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/getInbox.action", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getInbox(String cmbRazonSocial, String fechaIni, String fechaFin, String language, HttpServletRequest request, HttpSession session, String ekorg) throws AxisFault{
		UserDto user = (UserDto) session.getAttribute("UserDetails");
		Locale locale = RequestContextUtils.getLocale(request);
		
		if( user != null ){
			Z_FE_FM_CONSULTAR_BUZONResponse response = inboxService.getInbox(cmbRazonSocial, fechaIni, fechaFin, user.getI_LIFNR(), locale.getLanguage(), ekorg);
			if(response.getE_TYPE().toString().equals("E")){
				return getModelMapError(response.getE_MSG().toString());
			}else {
				ZTT_BUZON inboxList = response.getE_BUZON();
				ZTL_BUZON[] items = inboxList.getItem();
				List jsonResponse = new ArrayList();
				
				for(ZTL_BUZON item : items) {
					InboxDto inboxDto = new InboxDto();
					inboxDto.setEbeln(item.getEBELN().toString());
					inboxDto.setFactura(item.getFACTURA().toString());
					inboxDto.setBorrado(item.getBORRADO().toString());
					inboxDto.setFacturado(item.getFACTURADO().toString());
					inboxDto.setFechaIngreso(item.getFECHA_INGRESO().toString());
					inboxDto.setImporte(item.getIMPORTE().toString());
					inboxDto.setLifnr(item.getLIFNR().toString());
					inboxDto.setPdfName(item.getPDF_NAME().toString());
					inboxDto.setWaers(item.getWAERS().toString());
					inboxDto.setXblnr(item.getXBLNR().toString());
					inboxDto.setXmlName(item.getXML_NAME().toString());
					inboxDto.setCtrlid(item.getCTRLID().toString());
					jsonResponse.add(inboxDto);
				}
				return getResponseMap(jsonResponse);
			}
			
		}else {
			return getModelMapError("ERROR");
		}				
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/deleteInbox.action", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteInbox(@RequestBody InboxDto[] invoices, HttpSession session, HttpServletRequest request) throws AxisFault {
		UserDto user = (UserDto) session.getAttribute("UserDetails");
		Locale locale = RequestContextUtils.getLocale(request);
		
		
		if(user != null) {
			Map<String, Object> result = inboxService.deleteInbox(invoices, locale.getLanguage());
			Iterator iterator = result.entrySet().iterator();
			List jsonResponse = new ArrayList();
			
			while(iterator.hasNext()) {
				Map.Entry pairs = (Map.Entry)iterator.next();
				if(pairs.getValue() != null){
					Z_FE_FM_BORRAR_BUZONResponse response = (Z_FE_FM_BORRAR_BUZONResponse) pairs.getValue();
					InboxDeleteDto item = new InboxDeleteDto();
					item.setInvoice(pairs.getKey().toString());
					item.setE_type(response.getE_TYPE().toString());
					item.setE_msg(response.getE_MSG().toString());
					jsonResponse.add(item);
				} else {
					InboxDeleteDto item = new InboxDeleteDto();
					item.setInvoice(pairs.getKey().toString());
					jsonResponse.add(item);
				}
			}
			return getResponseMap(jsonResponse) ;
		} else {
			return getModelMapError("ERROR GENERAL");
		}
		
		
	}
	
	@RequestMapping(value = "/getInboxExcel.action", method = RequestMethod.GET)
	public ModelAndView getInboxExcel(String cmbRazonSocial, String fechaIni, String fechaFin, String language, HttpServletRequest request, HttpSession session, String ekorg) throws AxisFault {
		UserDto user = (UserDto) session.getAttribute("UserDetails");
		Locale locale = RequestContextUtils.getLocale(request);
		
		if( user != null ){
			Z_FE_FM_CONSULTAR_BUZONResponse response = inboxService.getInbox(cmbRazonSocial, fechaIni, fechaFin, user.getI_LIFNR(), locale.getLanguage(), ekorg);
			if(response.getE_TYPE().toString().equals("E")){
				return new ModelAndView(listInbox,null);
			}else {
				ZTT_BUZON inboxList = response.getE_BUZON();
				ZTL_BUZON[] items = inboxList.getItem();
				List jsonResponse = new ArrayList();
				
				for(ZTL_BUZON item : items) {
					InboxDto inboxDto = new InboxDto();
					inboxDto.setEbeln(item.getEBELN().toString());
					inboxDto.setFactura(item.getFACTURA().toString());
					inboxDto.setBorrado(item.getBORRADO().toString());
					inboxDto.setFacturado(item.getFACTURADO().toString());
					inboxDto.setFechaIngreso(item.getFECHA_INGRESO().toString());
					inboxDto.setImporte(item.getIMPORTE().toString());
					inboxDto.setLifnr(item.getLIFNR().toString());
					inboxDto.setPdfName(item.getPDF_NAME().toString());
					inboxDto.setWaers(item.getWAERS().toString());
					inboxDto.setXblnr(item.getXBLNR().toString());
					inboxDto.setXmlName(item.getXML_NAME().toString());
					jsonResponse.add(inboxDto);
				}
				Map<String, Object> reportModel = new HashMap<String, Object>();
	            reportModel.put("data", jsonResponse);
				return new ModelAndView(listInbox,reportModel);
			}
			
		}else {
			return new ModelAndView(listInbox,null);
		}
	}
	

}
