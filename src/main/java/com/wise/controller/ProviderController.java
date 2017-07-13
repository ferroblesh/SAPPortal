package com.wise.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.axis2.AxisFault;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.wise.model.AreaDto;
import com.wise.model.DivisionDto;
import com.wise.model.EstadoCuentaDto;
import com.wise.model.EstatusIntegracionDto;
import com.wise.model.ListFacturaDto;
import com.wise.model.NotificationDto;
import com.wise.model.PackingDto;
import com.wise.model.PackingListDto;
import com.wise.model.PendingOrdersDto;
import com.wise.model.RazonSocialDto;
import com.wise.model.ReporteDto;
import com.wise.model.ResponsableDto;
import com.wise.model.TreeItemDto;
import com.wise.model.UserDto;
import com.wise.service.ProviderService;
import com.wise.viewResolver.AccountStatusView;
import com.wise.viewResolver.ListInvoiceStatusView;
import com.wise.viewResolver.ListInvoiceView;
import com.wise.viewResolver.PurchaseWOInvoiceView;

import functions.rfc.sap.document.sap_com.ZSPACKINGLIST;
import functions.rfc.sap.document.sap_com.ZST_AVISO;
import functions.rfc.sap.document.sap_com.ZST_DIV_LIST;
import functions.rfc.sap.document.sap_com.ZST_ESTADO_CUENTA;
import functions.rfc.sap.document.sap_com.ZST_LISTA_REP_PROV;
import functions.rfc.sap.document.sap_com.ZTL_BUKRS_LIST;
import functions.rfc.sap.document.sap_com.ZTL_ESTATUS_PORTAL;
import functions.rfc.sap.document.sap_com.ZTL_GERENCIA;
import functions.rfc.sap.document.sap_com.ZTL_LISTA_FAC;
import functions.rfc.sap.document.sap_com.ZTL_ORDER_FAC;
import functions.rfc.sap.document.sap_com.ZTTPACKINGLIST;
import functions.rfc.sap.document.sap_com.ZTT_AVISO;
import functions.rfc.sap.document.sap_com.ZTT_BUKRS_LIST;
import functions.rfc.sap.document.sap_com.ZTT_DIV_LIST;
import functions.rfc.sap.document.sap_com.ZTT_ESTADO_CUENTA;
import functions.rfc.sap.document.sap_com.ZTT_ESTATUS_PORTAL;
import functions.rfc.sap.document.sap_com.ZTT_GERENCIA;
import functions.rfc.sap.document.sap_com.ZTT_LISTA_FAC;
import functions.rfc.sap.document.sap_com.ZTT_LISTA_REP_PROV;
import functions.rfc.sap.document.sap_com.ZTT_ORDER_FAC;
import functions.rfc.sap.document.sap_com.Z_FE_FM_AREA_RESPResponse;
import functions.rfc.sap.document.sap_com.Z_FE_FM_CAMBIO_PSWD_PROV;
import functions.rfc.sap.document.sap_com.Z_FE_FM_CAMBIO_PSWD_PROVResponse;
import functions.rfc.sap.document.sap_com.Z_FE_FM_CONFIRMA_ESTADO_CUENTAResponse;
import functions.rfc.sap.document.sap_com.Z_FE_FM_CONTADOR_AVISOS_NLEResponse;
import functions.rfc.sap.document.sap_com.Z_FE_FM_DIVISIONResponse;
import functions.rfc.sap.document.sap_com.Z_FE_FM_ESTADO_CUENTAResponse;
import functions.rfc.sap.document.sap_com.Z_FE_FM_ESTATUS_PORTALResponse;
import functions.rfc.sap.document.sap_com.Z_FE_FM_LISTA_FACResponse;
import functions.rfc.sap.document.sap_com.Z_FE_FM_OBTIENE_AVISOSResponse;
import functions.rfc.sap.document.sap_com.Z_FE_FM_ORDEN_FACTURAResponse;
import functions.rfc.sap.document.sap_com.Z_FE_FM_PACKING_LISTExceptionException;
import functions.rfc.sap.document.sap_com.Z_FE_FM_PACKING_LISTResponse;
import functions.rfc.sap.document.sap_com.Z_FE_FM_PACKING_LISTRfcExceptions;
import functions.rfc.sap.document.sap_com.Z_FE_FM_RAZON_SOCIALResponse;
import functions.rfc.sap.document.sap_com.Z_FE_FM_REPORTES_PROVResponse;
import functions.rfc.sap.document.sap_com.Z_FE_FM_UPDATE_LECTURA_AVISOResponse;

@Controller
@RequestMapping("/provider")
public class ProviderController extends BaseController{
	
	private static final Logger LOGGER = Logger.getLogger(ProviderController.class);
	
	@Autowired
	private ProviderService providerService;
	
	@Autowired
	private ListInvoiceView listInvoiceView;
	
	@Autowired
	private PurchaseWOInvoiceView purchaseWOInvoiceView;
	
	@Autowired
	private AccountStatusView accountStatusView;
	
	@Autowired
	private ListInvoiceStatusView listInvoiceStatusView;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value= "/razonSocial.action", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getRazonSocial(HttpSession session) throws Exception {
		LOGGER.info("Getting razon social");
		UserDto user = (UserDto) session.getAttribute("UserDetails");
		if(user != null){
			Z_FE_FM_RAZON_SOCIALResponse response = providerService.getRazonSocial(user.getI_LIFNR());
			ZTT_BUKRS_LIST list = response.getE_BUKRSLIST();
			ZTL_BUKRS_LIST[] data = list.getItem();
			List json = new ArrayList();
			
			for(ZTL_BUKRS_LIST item : data){
				RazonSocialDto razonSocialDto = new RazonSocialDto();
				razonSocialDto.setBukrs(item.getBUKRS().toString());
				razonSocialDto.setButxt(item.getBUTXT().toString());
				json.add(razonSocialDto);				
			}
			return getResponseMap(json);
		}else {
			return getModelMapError("ERROR");
		}		
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value= "/list.action", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getListInvoice(HttpServletRequest request, HttpSession session,String cmbRazonSocial, String fechaIni, String fechaFin,String cmbEstado, String ekorg) throws Exception {
		LOGGER.info("Getting Invoice list");
		UserDto user = (UserDto) session.getAttribute("UserDetails");
		Locale locale = RequestContextUtils.getLocale(request);
		
		if(user != null) {
			Z_FE_FM_LISTA_FACResponse response = providerService.getListaFactura(cmbRazonSocial, fechaIni, fechaFin, cmbEstado, user.getI_LIFNR(), locale.getLanguage(), ekorg);
			if(response.getE_TYPE().toString().equals("E")){
				return getModelMapError(response.getE_MSG().toString());
			}else {
				ZTT_LISTA_FAC listaFac = response.getE_INVOICE_LIST();
				ZTL_LISTA_FAC[] data = listaFac.getItem();
				List jsonResponse = new ArrayList();
				
				for(ZTL_LISTA_FAC item : data) {
					ListFacturaDto factura = new ListFacturaDto();
					factura.setBELN(item.getBELNR().toString());
					factura.setESTATUS(item.getESTATUS().toString());
					factura.setFPAGO(item.getFPAGO().toString());
					factura.setFVENC(item.getFVENC().toString());
					factura.setNETO(item.getNETO().toString());
					factura.setWAERS(item.getWAERS().toString());
					factura.setXBLNR(item.getXBLNR().toString());
					jsonResponse.add(factura);
				}
				return getResponseMap(jsonResponse);
			}
		} else{
			return getModelMapError("ERROR");
		}				
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value= "/status.action", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getStatus(HttpServletRequest request ,HttpSession session,String cmbRazonSocial , String fechaIni, String fechaFin) throws Exception {
		LOGGER.info("Getting Integration status");
		Locale locale = RequestContextUtils.getLocale(request);
		UserDto user = (UserDto) session.getAttribute("UserDetails");
		if(user != null) {
			Z_FE_FM_ESTATUS_PORTALResponse response = providerService.getEstatusIntegracion(cmbRazonSocial, fechaIni, fechaFin, user.getI_LIFNR(), locale.getLanguage());
			if(response.getE_TYPE().toString().equals("E")) {
				return getModelMapError(response.getE_MSG().toString());
			}else {
				ZTT_ESTATUS_PORTAL statusInt = response.getE_INVOICE_LIST();
				ZTL_ESTATUS_PORTAL[] data = statusInt.getItem();
				List jsonResponse = new ArrayList();
				
				for(ZTL_ESTATUS_PORTAL item : data) {
					EstatusIntegracionDto factura = new EstatusIntegracionDto();
					factura.setERDAT(item.getERDAT().toString());
					factura.setESTATUS(item.getESTATUS().toString());
					factura.setMESSAGE(item.getMESSAGE().toString());
					factura.setXBLNR(item.getXBLNR().toString());
					jsonResponse.add(factura);
				}
				return getResponseMap(jsonResponse);
			}
		} else {
			return getModelMapError("ERROR");
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/areas.action", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getArea(HttpSession session, String bukrs) throws AxisFault {
		LOGGER.info("Getting Areas using bukrs");
		UserDto user = (UserDto) session.getAttribute("UserDetails");
		if(user != null) {
			Z_FE_FM_AREA_RESPResponse response = providerService.getArea(bukrs);
			if(response.getE_TYPE().toString().equals("E")) {
				return getModelMapError(response.getE_MSG().toString());
			}else {
				ZTT_GERENCIA ger = response.getE_GERENCIA();
				ZTL_GERENCIA[] items = ger.getItem();
				List<AreaDto> jsonResponse = new ArrayList<AreaDto>();
				List<ResponsableDto> responsables = new ArrayList<ResponsableDto>();
//				AreaDto dto = jsonResponse.isEmpty() ?  new AreaDto() : jsonResponse.get(jsonResponse.size() -1);
										
				for(ZTL_GERENCIA item : items) {
					AreaDto area = new AreaDto();
					area.setIdDireccion(item.getID_DIRECCION().toString());
					area.setDescDireccion(item.getDESC_DIRECCION().toString());
					if(!jsonResponse.contains(area)) {
						jsonResponse.add(area);
					}										
				} // filling jsonResponse with all areas;				
				ListIterator<AreaDto> listIterator = jsonResponse.listIterator();				
				while(listIterator.hasNext()) { //adding all the responsables to the areas
					AreaDto listItem = listIterator.next();
					List<ResponsableDto> resp =  new ArrayList<ResponsableDto>();// listItem.getResponsables() == null ? new ArrayList<ResponsableDto>() : listItem.getResponsables();
					for(ZTL_GERENCIA item : items) {
						if(listItem.getIdDireccion().equals(item.getID_DIRECCION().toString())){							
							ResponsableDto tmpResponsable = new ResponsableDto();
							tmpResponsable.setIdGerencia(item.getID_GERENCIA().toString());
							tmpResponsable.setDescGerencia(item.getDESC_GERENCIA().toString());
							resp.add(tmpResponsable);						
						}
					}
					listItem.setResponsables(resp);
					listIterator.set(listItem);					
				}
				return getResponseMap(jsonResponse);
			}
		} else {
			return getModelMapError("Error de login");
		}
	}
	
	@RequestMapping(value = "/listInvoiceExcel.action", method = RequestMethod.GET)
	public ModelAndView getLIstInvoiceExcel(HttpServletRequest request,HttpSession session,String cmbRazonSocial, String fechaIni, String fechaFin,String cmbEstado, String ekorg) throws Exception {
		UserDto user = (UserDto) session.getAttribute("UserDetails");
		Locale locale = RequestContextUtils.getLocale(request);
		if(user != null) {
			Z_FE_FM_LISTA_FACResponse response = providerService.getListaFactura(cmbRazonSocial, fechaIni, fechaFin, cmbEstado, user.getI_LIFNR(), locale.getLanguage(), ekorg);
			if(response.getE_TYPE().toString().equals("E")){
				return new ModelAndView(listInvoiceView,null);
			}else {
				ZTT_LISTA_FAC listaFac = response.getE_INVOICE_LIST();
				ZTL_LISTA_FAC[] data = listaFac.getItem();
				List<ListFacturaDto> jsonResponse = new ArrayList<ListFacturaDto>();
				
				for(ZTL_LISTA_FAC item : data) {
					ListFacturaDto factura = new ListFacturaDto();
					factura.setBELN(item.getBELNR().toString());
					factura.setESTATUS(item.getESTATUS().toString());
					factura.setFPAGO(item.getFPAGO().toString());
					factura.setFVENC(item.getFVENC().toString());
					factura.setNETO(item.getNETO().toString());
					factura.setWAERS(item.getWAERS().toString());
					factura.setXBLNR(item.getXBLNR().toString());
					jsonResponse.add(factura);
				}
				Map<String, Object> reportModel = new HashMap<String, Object>();
	            reportModel.put("data", jsonResponse);
				return new ModelAndView(listInvoiceView,reportModel);
			}
		}
		return new ModelAndView(listInvoiceView,null);
	}
	
	@RequestMapping(value = "/listInvoiceStatusExcel.action", method = RequestMethod.GET)
	public ModelAndView getLIstInvoiceStatusExcel(HttpServletRequest request,HttpSession session,String cmbRazonSocial , String fechaIni, String fechaFin) throws Exception {
		UserDto user = (UserDto) session.getAttribute("UserDetails");
		Locale locale = RequestContextUtils.getLocale(request);
		if(user != null) {
			Z_FE_FM_ESTATUS_PORTALResponse response = providerService.getEstatusIntegracion(cmbRazonSocial, fechaIni, fechaFin, user.getI_LIFNR(),locale.getLanguage());
			if(response.getE_TYPE().toString().equals("E")){
				return new ModelAndView(listInvoiceView,null);
			}else {
				ZTT_ESTATUS_PORTAL listaFac = response.getE_INVOICE_LIST();
				ZTL_ESTATUS_PORTAL[] data = listaFac.getItem();
				List<EstatusIntegracionDto> jsonResponse = new ArrayList<EstatusIntegracionDto>();
				
				for(ZTL_ESTATUS_PORTAL item : data) {
					EstatusIntegracionDto factura = new EstatusIntegracionDto();
					factura.setERDAT(item.getERDAT().toString());
					factura.setESTATUS(item.getESTATUS().toString());
					factura.setMESSAGE(item.getMESSAGE().toString());
					factura.setXBLNR(item.getXBLNR().toString());
					jsonResponse.add(factura);					
				}
				Map<String, Object> reportModel = new HashMap<String, Object>();
	            reportModel.put("data", jsonResponse);
				return new ModelAndView(listInvoiceStatusView,reportModel);
			}
		}
		return new ModelAndView(listInvoiceView,null);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/division.action", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getDivision(HttpSession session, String bukrs) throws AxisFault {
		LOGGER.info("Getting Division using bukrs");
		UserDto user = (UserDto) session.getAttribute("UserDetails");
		if(user != null) {
			Z_FE_FM_DIVISIONResponse response = providerService.getDivision(bukrs);
			if(response.getE_TYPE().toString().equals("E")) {
				return getModelMapError(response.getE_MSG().toString());	
			} else {
				ZTT_DIV_LIST division = response.getE_DIVLIST();
				ZST_DIV_LIST[] items  = division.getItem();
				List jsonResponse = new ArrayList();
				
				for(ZST_DIV_LIST item : items) {
					DivisionDto div = new DivisionDto();
					div.setName(item.getNAME().toString());
					div.setSegment(item.getSEGMENT().toString());
					jsonResponse.add(div);
				}
				return getResponseMap(jsonResponse);
			}
		} else {
			return getModelMapError("ERROR");
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/listPendingInvoice.action", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getPendingInvoices(HttpSession session, HttpServletRequest request, 
												  String bukrs, String ekorg, String lifnr, 
												  String fechaIni, String fechaFin, 
												  String ebeln, String currency, 
												  Integer receptionType, String orders, String numImp) throws AxisFault{
		LOGGER.info("Getting Pending Invoices");
		UserDto user = (UserDto) session.getAttribute("UserDetails");
		Locale locale = RequestContextUtils.getLocale(request);
		LOGGER.info("Value currency: " + currency);
		if(user != null) {			
			Z_FE_FM_ORDEN_FACTURAResponse response = providerService.getPendingInvoices(bukrs,ekorg,user.getI_LIFNR().toString(), fechaIni,
																						fechaFin, ebeln, locale.getLanguage(), receptionType,
																						currency, orders, numImp);
			if(response.getE_TYPE().toString().equals("E")) {
				return getModelMapError(response.getE_MSG().toString());
			} else {
				ZTT_ORDER_FAC invoices = response.getE_PO();
				ZTL_ORDER_FAC[] data = invoices.getItem();
				List jsonResponse = new ArrayList();
				
				for(ZTL_ORDER_FAC item : data) {
					PendingOrdersDto dto = new PendingOrdersDto();
					dto.setAedat(item.getAEDAT().toString());
					dto.setBudat(item.getBUDAT().toString());
					dto.setBuzei(item.getBUZEI().toString());
					dto.setEbeln(item.getEBELN().toString());
					dto.setEbelp(item.getEBELP().toString());
					dto.setMatnr(item.getMATNR().toString());
					dto.setMblnr(item.getMBLNR().toString());
					dto.setMeins(item.getMEINS().toString());
					dto.setMenge(item.getMENGE().toString());
					dto.setNetwr(item.getNETWR().toString());
					dto.setPunit(item.getPUNIT().toString());
					dto.setTxz01(item.getTXZ01().toString());
					dto.setWaers(item.getWAERS().toString());
					dto.setXblnr(item.getXBLNR().toString());
					dto.setZmenge(item.getZMENGE().toString());
					dto.setInvoiceDocItem(item.getINVOICE_DOC_ITEM().toString());
					dto.setPoNumber(item.getPO_NUMBER().toString());
					dto.setPoItem(item.getPO_ITEM().toString());
					dto.setRefDoc(item.getREF_DOC().toString());
					dto.setRefDocYear(item.getREF_DOC_YEAR().toString());
					dto.setRefDocIt(item.getREF_DOC_IT().toString());
					dto.setItemAmount(item.getITEM_AMOUNT().toString());
					dto.setQuantity(item.getQUANTITY().toString());
					dto.setPoUnit(item.getPO_UNIT().toString());
					dto.setPoPrQnt(item.getPO_PR_QNT().toString());
					dto.setPoPrUom(item.getPO_PR_UOM().toString());
					dto.setCondType(item.getCOND_TYPE().toString());
					dto.setSheetNo(item.getSHEET_NO().toString());
					dto.setSheetItem(item.getSHEET_ITEM().toString());
					jsonResponse.add(dto);
				}
				return getResponseMap(jsonResponse);
			}							
			
		} else {
			return getModelMapError("ERROR");
		}
	}
	
	@RequestMapping(value= "/pendingInvoicesExcel.action", method = RequestMethod.GET)
	public ModelAndView getInvoicesExcel(HttpSession session, HttpServletRequest request, String bukrs, String ekorg, String fechaIni, String fechaFin, 
										 String ebeln, String currency, Integer receptionType, String orders, String numImp){
		UserDto user = (UserDto) session.getAttribute("UserDetails");
		Locale locale = RequestContextUtils.getLocale(request);
		if(user != null) {
			try {
				Z_FE_FM_ORDEN_FACTURAResponse response = providerService.getPendingInvoices(bukrs,ekorg,user.getI_LIFNR().toString(), 
																							fechaIni, fechaFin, ebeln, locale.getLanguage(),
																							receptionType, currency,orders,numImp);
				if(response.getE_TYPE().toString().equals("E")) {
					return new ModelAndView(purchaseWOInvoiceView,null);
				} else {
					ZTT_ORDER_FAC invoices = response.getE_PO();
					ZTL_ORDER_FAC[] data = invoices.getItem();
					List<PendingOrdersDto> jsonResponse = new ArrayList<PendingOrdersDto>();
					
					for(ZTL_ORDER_FAC item : data) {
						PendingOrdersDto dto = new PendingOrdersDto();
						dto.setAedat(item.getAEDAT().toString());
						dto.setBudat(item.getBUDAT().toString());
						dto.setBuzei(item.getBUZEI().toString());
						dto.setEbeln(item.getEBELN().toString());
						dto.setEbelp(item.getEBELP().toString());
						dto.setMatnr(item.getMATNR().toString());
						dto.setMblnr(item.getMBLNR().toString());
						dto.setMeins(item.getMEINS().toString());
						dto.setMenge(item.getMENGE().toString());
						dto.setNetwr(item.getNETWR().toString());
						dto.setPunit(item.getPUNIT().toString());
						dto.setTxz01(item.getTXZ01().toString());
						dto.setWaers(item.getWAERS().toString());
						dto.setXblnr(item.getXBLNR().toString());
						dto.setZmenge(item.getZMENGE().toString());
						jsonResponse.add(dto);
					}
					Map<String, Object> reportModel = new HashMap<String, Object>();
		            reportModel.put("data", jsonResponse);
					return new ModelAndView(purchaseWOInvoiceView,reportModel);
				}
			} catch (Exception e) {
				LOGGER.error("Error generating output",e);
				return new ModelAndView(purchaseWOInvoiceView,null);
			}
		} else {
			return new ModelAndView(purchaseWOInvoiceView,null);
		}
	}
	
	@RequestMapping(value = "/accountStatus.action", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAccountStatus(HttpSession session, HttpServletRequest request, String bukrs) {
		LOGGER.info("Getting account status");
		UserDto user = (UserDto) session.getAttribute("UserDetails");
		Locale locale = RequestContextUtils.getLocale(request);
		if(user != null) {
			try {
				Z_FE_FM_ESTADO_CUENTAResponse response = providerService.getAccountStatus(user.getI_LIFNR().toString(), bukrs, locale.getLanguage());
				LOGGER.info("Response type: " + response.getE_TYPE().toString() + ", response msg: " + response.getE_MSG().toString());
				if(response.getE_TYPE().toString().equals("E")) {
					return getModelMapError(response.getE_MSG().toString());
				} else {
					ZTT_ESTADO_CUENTA listaAbierta = response.getE_LISTA_P_ABIERTA();
					ZTT_ESTADO_CUENTA listaCompensada = response.getE_LISTA_P_COMPENSADA();
					ZST_ESTADO_CUENTA[] data = listaAbierta.getItem();
					ZST_ESTADO_CUENTA[] dataCompensada = listaCompensada.getItem();
					
					List<EstadoCuentaDto> abierta = new ArrayList<EstadoCuentaDto>();
					List<EstadoCuentaDto> compensada = new ArrayList<EstadoCuentaDto>();
					if(data != null && data.length > 0)
					for(ZST_ESTADO_CUENTA item : data) {
						EstadoCuentaDto dto = new EstadoCuentaDto();
						dto.setAugbl(item.getAUGBL().toString());
						dto.setAugdt(item.getAUGDT().toString());
						dto.setBelnr(item.getBELNR().toString());
						dto.setBlart(item.getBLART().toString());
						dto.setBudat(item.getBUDAT().toString());
						dto.setWaers(item.getWAERS().toString());
						dto.setWrbtr(item.getWRBTR().toString());
						dto.setXblnr(item.getXBLNR().toString());
						dto.setZfbdt(item.getZFBDT().toString());
						abierta.add(dto);
					}
					if(dataCompensada != null && dataCompensada.length > 0)
					for(ZST_ESTADO_CUENTA item : dataCompensada) {
						EstadoCuentaDto dto = new EstadoCuentaDto();
						dto.setAugbl(item.getAUGBL().toString());
						dto.setAugdt(item.getAUGDT().toString());
						dto.setBelnr(item.getBELNR().toString());
						dto.setBlart(item.getBLART().toString());
						dto.setBudat(item.getBUDAT().toString());
						dto.setWaers(item.getWAERS().toString());
						dto.setWrbtr(item.getWRBTR().toString());
						dto.setXblnr(item.getXBLNR().toString());
						dto.setZfbdt(item.getZFBDT().toString());
						compensada.add(dto);
					}
					return getTwoList(abierta, compensada);
				}
				
			} catch (Exception e) {
				LOGGER.error("Error estado de cuenta:" + e.getMessage(), e);
				return getModelMapError(e.getMessage());
			}
			
		}
		return getModelMapError("No session");
	}
	
	@RequestMapping(value = "/accountStatusExcel.action", method = RequestMethod.GET)	
	public ModelAndView getAccountStatusExcel(HttpSession session, HttpServletRequest request, String bukrs) {
		LOGGER.info("Getting account status");
		UserDto user = (UserDto) session.getAttribute("UserDetails");
		Locale locale = RequestContextUtils.getLocale(request);
		if(user != null) {
			try {
				Z_FE_FM_ESTADO_CUENTAResponse response = providerService.getAccountStatus(user.getI_LIFNR().toString(), bukrs, locale.getLanguage());
				LOGGER.info("Response type: " + response.getE_TYPE().toString() + ", response msg: " + response.getE_MSG().toString());
				if(response.getE_TYPE().toString().equals("E")) {
					return new ModelAndView(accountStatusView,null);
				} else {
					ZTT_ESTADO_CUENTA listaAbierta = response.getE_LISTA_P_ABIERTA();
					ZTT_ESTADO_CUENTA listaCompensada = response.getE_LISTA_P_COMPENSADA();
					ZST_ESTADO_CUENTA[] data = listaAbierta.getItem();
					ZST_ESTADO_CUENTA[] dataCompensada = listaCompensada.getItem();
					
					List<EstadoCuentaDto> abierta = new ArrayList<EstadoCuentaDto>();
					List<EstadoCuentaDto> compensada = new ArrayList<EstadoCuentaDto>();
					if(data != null && data.length > 0)
					for(ZST_ESTADO_CUENTA item : data) {
						EstadoCuentaDto dto = new EstadoCuentaDto();
						dto.setAugbl(item.getAUGBL().toString());
						dto.setAugdt(item.getAUGDT().toString());
						dto.setBelnr(item.getBELNR().toString());
						dto.setBlart(item.getBLART().toString());
						dto.setBudat(item.getBUDAT().toString());
						dto.setWaers(item.getWAERS().toString());
						dto.setWrbtr(item.getWRBTR().toString());
						dto.setXblnr(item.getXBLNR().toString());
						dto.setZfbdt(item.getZFBDT().toString());
						abierta.add(dto);
					}
					if(dataCompensada != null && dataCompensada.length > 0)
					for(ZST_ESTADO_CUENTA item : dataCompensada) {
						EstadoCuentaDto dto = new EstadoCuentaDto();
						dto.setAugbl(item.getAUGBL().toString());
						dto.setAugdt(item.getAUGDT().toString());
						dto.setBelnr(item.getBELNR().toString());
						dto.setBlart(item.getBLART().toString());
						dto.setBudat(item.getBUDAT().toString());
						dto.setWaers(item.getWAERS().toString());
						dto.setWrbtr(item.getWRBTR().toString());
						dto.setXblnr(item.getXBLNR().toString());
						dto.setZfbdt(item.getZFBDT().toString());
						compensada.add(dto);
					}
					Map<String, Object> reportModel = new HashMap<String, Object>();
		            reportModel.put("dataOpen", abierta);
		            reportModel.put("dataComp", compensada);
					return new ModelAndView(accountStatusView,reportModel);
				}
				
			} catch (Exception e) {
				LOGGER.error("Error estado de cuenta:" + e.getMessage(), e);
				return new ModelAndView(accountStatusView,null);
			}
			
		}
		return new ModelAndView(accountStatusView,null);
	}
	
	@RequestMapping(value = "/confirmAccountStatus.action", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> confirmAccountStatus(String bukrs, String estado, HttpSession session, HttpServletRequest request) {
		LOGGER.info("Setting account status");
		UserDto user = (UserDto) session.getAttribute("UserDetails");
		Locale locale = RequestContextUtils.getLocale(request);
		if(user != null) {
			try {
				Z_FE_FM_CONFIRMA_ESTADO_CUENTAResponse response = providerService.setAccountStatus(user.getI_LIFNR().toString(), bukrs, locale.getLanguage().toUpperCase(), estado);
				if(response.getE_TYPE().toString().equals("E")) {
					return getModelMapError(response.getE_MSG().toString());
				} else {
					return getModelMapSuccess(response.getE_MSG().toString());
				}
			} catch (Exception e) {
				LOGGER.error("Error setting accountStatus: " + e.getMessage(), e);
				return getModelMapError("Error: " + e.getMessage());
			}
		} else {
			return getModelMapError("Error");
		}
	}
	
	@RequestMapping(value = "/changePassword.action", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> changePassword(String oldPass, String newPass, String newPassReview, HttpSession session, HttpServletRequest request) {
		
		UserDto user = (UserDto) session.getAttribute("UserDetails");
		LOGGER.info("Changing password for provider: " +  user.getI_LIFNR());
		Locale locale = RequestContextUtils.getLocale(request);
		if(user != null) {
			try {
				Z_FE_FM_CAMBIO_PSWD_PROVResponse response = providerService.changePassword(user.getI_LIFNR(), oldPass, newPass, newPassReview, locale.getLanguage().toUpperCase());
				if(response.getE_TYPE().toString().equals("E")) {
					return getModelMapError(response.getE_MSG().toString());
				} else {
					return getModelMapSuccess(response.getE_MSG().toString());
				}				
			} catch (Exception e) {
				LOGGER.error("Error changing password: " + e.getMessage(),e);
				return getModelMapError("Error: " + e.getMessage());
			}
		}		
		return getModelMapError("Error");
	}
	
	@RequestMapping(value = "/getReports.action", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getReports(HttpSession session) {
		UserDto user = (UserDto) session.getAttribute("UserDetails");
		if(user != null) {
			try {
				Z_FE_FM_REPORTES_PROVResponse response = providerService.getReports(user.getI_LIFNR());
				if(response.getE_TYPE().toString().equals("E")) {
					return null;
				} else {
					ZST_LISTA_REP_PROV[] lista = response.getE_LISTA().getItem();
					List<ReporteDto> jsonResponse = new ArrayList<ReporteDto>();
					List<TreeItemDto> result = new ArrayList<TreeItemDto>();
					if(lista.length > 0) {
						for(ZST_LISTA_REP_PROV report : lista) {
							ReporteDto dto = new ReporteDto();
							dto.setActivo(report.getACTIVO().toString());
							dto.setIdgrurepo(report.getIDGRUREPO().toString());
							dto.setIdrepo(report.getIDREPO().toString());
							dto.setNombrerepo(report.getNOMBREREPO().toString());
							dto.setUbirepo(report.getUBIREPO().toString());
							dto.setCarpeta(report.getCARPETA().toString());
							dto.setSubcarpeta(report.getSUBCARPETA().toString());
							jsonResponse.add(dto);
						}
						Map<String, TreeItemDto> carpetas = new HashMap<String, TreeItemDto>();				        
						for(ReporteDto dto : jsonResponse) {
							TreeItemDto carpetaNode = carpetas.get(dto.getCarpeta());
							if(carpetaNode == null ){								
								carpetaNode = new TreeItemDto();
								carpetaNode.setText(dto.getCarpeta());
								carpetaNode.setLeaf(false);
								carpetaNode.setIconCls("folder");
								carpetaNode.setExpanded(true);
								carpetas.put(dto.getCarpeta(), carpetaNode);
								TreeItemDto subcarpetaNode = new TreeItemDto();
								subcarpetaNode.setText(dto.getSubcarpeta());
								subcarpetaNode.setLeaf(false);
								subcarpetaNode.setIconCls("folder");
								carpetaNode.setExpanded(true);
								TreeItemDto node = new TreeItemDto();
								node.setText(dto.getNombrerepo());
								node.setLeaf(true);
								node.setNombrerepo(dto.getNombrerepo());
								node.setIconCls("file");
								node.setIdgrurepo(dto.getIdgrurepo());
								node.setIdrepo(dto.getIdrepo());
								node.setUbirepo(dto.getUbirepo());
								node.setActivo(dto.getActivo());
								subcarpetaNode.addChild(node);
								carpetaNode.addChild(subcarpetaNode);			
								result.add(carpetaNode);
							} else {
								List<TreeItemDto> subcarpetas = carpetaNode.getChildren();
								boolean isModified = false;
								for(TreeItemDto subcarpeta : subcarpetas) {
									if(subcarpeta.getText().equals(dto.getSubcarpeta())) {
										TreeItemDto node = new TreeItemDto();
										node.setText(dto.getNombrerepo());
										node.setLeaf(true);
										node.setNombrerepo(dto.getNombrerepo());
										node.setIconCls("file");
										node.setIdgrurepo(dto.getIdgrurepo());
										node.setIdrepo(dto.getIdrepo());
										node.setUbirepo(dto.getUbirepo());
										node.setActivo(dto.getActivo());										
										subcarpeta.addChild(node);
										isModified = true;
										break;
									}
								}
								if(!isModified) {
									TreeItemDto subcarpetaNode = new TreeItemDto();
									subcarpetaNode.setText(dto.getSubcarpeta());
									subcarpetaNode.setLeaf(false);
									subcarpetaNode.setIconCls("folder");
									carpetaNode.setExpanded(true);
									TreeItemDto node = new TreeItemDto();
									node.setText(dto.getNombrerepo());
									node.setLeaf(true);
									node.setNombrerepo(dto.getNombrerepo());
									node.setIconCls("file");
									node.setIdgrurepo(dto.getIdgrurepo());
									node.setIdrepo(dto.getIdrepo());
									node.setUbirepo(dto.getUbirepo());
									node.setActivo(dto.getActivo());
									subcarpetaNode.addChild(node);
									carpetaNode.addChild(subcarpetaNode);
								}
							}
							
						}
						return getResponseMap(result);
					} else {
						return getModelMapSuccess("Proveedor sin reportes");
					}
					
				}
			} catch (Exception e) {
				LOGGER.error("Error gettting reports", e);
				return getModelMapError("Error:" + e.getMessage());
			}
		}
		return getModelMapError("Error general");
	}
	
	@RequestMapping(value = "/existReport.action", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> existReport(HttpSession session, String nombrerepo, String idrepo, String idgrurepo) {
		UserDto user = (UserDto) session.getAttribute("UserDetails");
		if(user != null) {
			try {
				Z_FE_FM_REPORTES_PROVResponse response = providerService.getReports(user.getI_LIFNR());
				if(response.getE_TYPE().toString().equals("E")) {
					return getModelMapError(response.getE_MSG().toString());
				} else {
					ZST_LISTA_REP_PROV[] lista = response.getE_LISTA().getItem();
					List<ReporteDto> jsonResponse = new ArrayList<ReporteDto>();
					ReporteDto reporte = new ReporteDto();
					if(lista.length > 0) {
						for(ZST_LISTA_REP_PROV report : lista) {
							ReporteDto dto = new ReporteDto();
							dto.setActivo(report.getACTIVO().toString());
							dto.setIdgrurepo(report.getIDGRUREPO().toString());
							dto.setIdrepo(report.getIDREPO().toString());
							dto.setNombrerepo(report.getNOMBREREPO().toString());
							dto.setUbirepo(report.getUBIREPO().toString());
							jsonResponse.add(dto);
						}
						for(ReporteDto report : jsonResponse) {
							if(report.getIdrepo().equals(idrepo) && report.getIdgrurepo().equals(idgrurepo) && report.getNombrerepo().equals(nombrerepo)) {
								reporte = report;
								break;
							}
						}
					}
					if(reporte.getUbirepo() != null) {
						File file = new File(reporte.getUbirepo());
						if(file.exists() && !file.isDirectory()) {
							return getModelMapSuccess("File Exists");
						} else {
							return getModelMapError("File not exists");
						}
					} else {
						return getModelMapError("No data");
					}
					
				}
			} catch (Exception e) {
				LOGGER.error("Error gettting reports", e);
				return getModelMapError("Error:" + e.getMessage());
			}
		}
		return getModelMapError("Error general");
	}
	
	@RequestMapping(value = "/getAllReports.action", method = RequestMethod.GET)
	public void getAllReports(HttpSession session, HttpServletResponse responseServlet) {
		UserDto user = (UserDto) session.getAttribute("UserDetails");
		byte[] buffer = new byte[1024];
		
		if(user != null) {
			try {
				Z_FE_FM_REPORTES_PROVResponse response = providerService.getReports(user.getI_LIFNR());
				if(response.getE_TYPE().toString().equals("E")) {					
				} else {
					ZST_LISTA_REP_PROV[] lista = response.getE_LISTA().getItem();
					LOGGER.info("Lista length: " + lista.length);
					String headerKey = "Content-Disposition";						
					SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
					responseServlet.setContentType("application/zip");
					String headerValue = String.format("attachment; filename=\"%s\"",user.getI_LIFNR() + ".zip");
					responseServlet.setHeader(headerKey, headerValue);
					if(lista.length > 0) {
						ZipOutputStream zos = new ZipOutputStream(responseServlet.getOutputStream());
						for(ZST_LISTA_REP_PROV report : lista) {
							File file = new File(report.getUBIREPO().toString());
							if(file.exists() && !file.isDirectory()) {
								LOGGER.info("Adding file to zip: " + report.getUBIREPO());
								FileInputStream fis = new FileInputStream(file);
								zos.putNextEntry(new ZipEntry(file.getName()));
								int length;
								while((length = fis.read(buffer)) > 0) {
									zos.write(buffer,0,length);
								}
								fis.close();								
							}
						}
						
						
						zos.closeEntry();
						zos.close();
					}
					
				}
			} catch (Exception e) {
				LOGGER.error("Error generating zip file", e);
			}
		}
	}
	
	@RequestMapping(value = "/getReport.pdf", method = RequestMethod.GET)
	public void getReport(HttpSession session, String nombrerepo, String idrepo, String idgrurepo, HttpServletResponse responseServlet, HttpServletRequest request) {
		UserDto user = (UserDto) session.getAttribute("UserDetails");
		ServletContext context = request.getSession().getServletContext();
		FileInputStream inputStream = null;
		OutputStream outputStream = null;
		if(user != null) {
			try {
				Z_FE_FM_REPORTES_PROVResponse response = providerService.getReports(user.getI_LIFNR());
				if(response.getE_TYPE().toString().equals("E")) {					
				} else {
					ZST_LISTA_REP_PROV[] lista = response.getE_LISTA().getItem();
					List<ReporteDto> jsonResponse = new ArrayList<ReporteDto>();
					ReporteDto reporte = new ReporteDto();
					if(lista.length > 0) {
						for(ZST_LISTA_REP_PROV report : lista) {
							ReporteDto dto = new ReporteDto();
							dto.setActivo(report.getACTIVO().toString());
							dto.setIdgrurepo(report.getIDGRUREPO().toString());
							dto.setIdrepo(report.getIDREPO().toString());
							dto.setNombrerepo(report.getNOMBREREPO().toString());
							dto.setUbirepo(report.getUBIREPO().toString());
							jsonResponse.add(dto);
						}
						for(ReporteDto report : jsonResponse) {
							if(report.getIdrepo().equals(idrepo) && report.getIdgrurepo().equals(idgrurepo) && report.getNombrerepo().equals(nombrerepo)) {
								reporte = report;
								break;
							}
						}
					}
					if(reporte.getUbirepo() != null) {
						File file = new File(reporte.getUbirepo());
						LOGGER.info("File exist: " + file.getName());
						if(file.exists() && !file.isDirectory()) {
							inputStream = new FileInputStream(file);
							
							responseServlet.setContentLength((int) file.length() );
							if (file.getName().toUpperCase().contains("PDF")) {
								responseServlet.setContentType("application/pdf");
							}else {
								responseServlet.setContentType(context.getMimeType(reporte.getUbirepo()));
							}
							
							String headerKey = "Content-Disposition";
							String action = file.getName().toUpperCase().contains("PDF") ? "inline" : "attachment";
							String headerValue = String.format("; filename=\"%s\"",file.getName());
							responseServlet.setHeader(headerKey, action + headerValue);
							
							outputStream = responseServlet.getOutputStream();
							
					        IOUtils.copy(inputStream, outputStream);
					        
						}
					}
					
				}
			} catch (Exception e) {
				LOGGER.error("Error downloading report", e);				
			} finally {
				try {
					if(null != inputStream)
						inputStream.close();
					if(null != outputStream) 
						outputStream.close();
				} catch (Exception e) {
					LOGGER.error("Error closing streams",e);
				}
			}
		}		
	}
	
	@RequestMapping(value = "/getUnreadNotificationsNumber.action", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getUnreadNotificationsNumber(HttpSession session) {
		UserDto user = (UserDto) session.getAttribute("UserDetails");
		try {
			Z_FE_FM_CONTADOR_AVISOS_NLEResponse response = providerService.getUnreadNotificationsNumber(user.getI_LIFNR());
			if(response.getE_TYPE().toString().toUpperCase().equals("E")) {
				return getModelMapError("Error: " + response.getE_MSG().toString());
			} else {
				return getResponseMapFromObject(response.getE_VALOR());
			}
		} catch (Exception e) {
			LOGGER.error("Error getting UnreadNotificationsNumber:", e);
			return getModelMapError("Error:" + e.getMessage());
		}
	}
	
	@RequestMapping(value = "/setNotificationStatus.action", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> setNotificationStatus(HttpSession session, String bukrs, String idaviso, String evento) {
		UserDto user = (UserDto) session.getAttribute("UserDetails");
		try {
			Z_FE_FM_UPDATE_LECTURA_AVISOResponse response = providerService.setNotificationStatus(user.getI_LIFNR(), bukrs, idaviso, evento);
			if(response.getE_TYPE().toString().toUpperCase().equals("E")) {
				return getModelMapError("Error:" + response.getE_MSG().toString());
			} else {
				return getModelMapSuccess(response.getE_MSG().toString());
			}
		} catch (Exception e) {
			LOGGER.error("Error setting notification status: ", e);
			return getModelMapError("Error: " + e.getMessage());
		}
	}
	
	@SuppressWarnings("unused")
	@RequestMapping(value = "/getNotifications.action", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getNotifications(String bukrs, String tipoAviso, HttpSession session, HttpServletRequest request) {
		UserDto user = (UserDto) session.getAttribute("UserDetails");
		LOGGER.info("Getting notifications for provider: " + user.getI_LIFNR() + ", bukrs:" + bukrs);
		Locale locale = RequestContextUtils.getLocale(request);
		if(user != null) {
			try {
				Z_FE_FM_OBTIENE_AVISOSResponse response = providerService.getNotifications(user.getI_LIFNR(), bukrs, locale.getLanguage().toUpperCase(), tipoAviso);
				if(response.getE_TYPE().toString().toUpperCase().equals("E")) {
					return getModelMapError(response.getE_MSG().toString());
				} else {
				    ZTT_AVISO tmp = response.getE_LISTAPROV();
				    ZST_AVISO[] notifications = tmp.getItem();
				    List<NotificationDto> jsonResponse = new ArrayList<NotificationDto>();
				    if(notifications != null && notifications.length > 0) {				    	
				    	for(ZST_AVISO notification : notifications) {
				    		NotificationDto dto = new NotificationDto();
				    		dto.setBukrs(notification.getBUKRS().toString());
				    		dto.setCuerpo(notification.getCUERPO().toString());
				    		dto.setEstatus(notification.getESTATUS().toString());
				    		dto.setFecha(notification.getFECHA().toString());
				    		dto.setIdioma(notification.getIDIOMA().toString());
				    		dto.setNaviso(Short.toString(notification.getNAVISO()));
				    		dto.setTitulo(notification.getTITULO().toString());
				    		dto.setIdAviso(notification.getIDAVISO().toString());
				    		dto.setEstatusLectura(notification.getESTATUSLECTURA().toString());
				    		jsonResponse.add(dto);
				    	}
				    }
				    tmp = response.getE_LISTGENERAL();
				    notifications = tmp.getItem();
				    if(notifications != null && notifications.length > 0) {
				    	for(ZST_AVISO notification : notifications) {
				    		NotificationDto dto = new NotificationDto();
				    		dto.setBukrs(notification.getBUKRS().toString());
				    		dto.setCuerpo(notification.getCUERPO().toString());
				    		dto.setEstatus(notification.getESTATUS().toString());
				    		dto.setFecha(notification.getFECHA().toString());
				    		dto.setIdioma(notification.getIDIOMA().toString());
				    		dto.setNaviso(Short.toString(notification.getNAVISO()));
				    		dto.setTitulo(notification.getTITULO().toString());
				    		dto.setIdAviso(notification.getIDAVISO().toString());
				    		dto.setEstatusLectura(notification.getESTATUSLECTURA().toString());
				    		jsonResponse.add(dto);
				    	}
				    }
				    return getResponseMap(jsonResponse);
				}
			} catch (Exception e) {
				LOGGER.error("Error getting notifications:" + e.getMessage(),e);
				return getModelMapError("Error: " + e.getMessage());
			}
		} else{
			return getModelMapError("ERROR GENERAL");
		}
		
	}
	
	@SuppressWarnings("unused")
	@RequestMapping(value= "/getPackingList.action", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getPackingList(@RequestBody PackingDto requestValues, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		UserDto user = (UserDto) session.getAttribute("UserDetails");
		LOGGER.info("Getting notifications for provider: " + user.getI_LIFNR() + ", ebeln:" + requestValues.getEbeln());
		Locale locale = RequestContextUtils.getLocale(request);
		if(user != null) {
			try {
				Z_FE_FM_PACKING_LISTResponse resp = providerService.getPackingList(user.getI_LIFNR(), requestValues, locale.getLanguage().toUpperCase());
				if(resp.getPT_PACKING() != null) {
					ZTTPACKINGLIST packingList = resp.getPT_PACKING();
					ZSPACKINGLIST[] zs = packingList.getItem();
					List<PackingListDto> jsonResponse = new ArrayList<PackingListDto>();
					for(ZSPACKINGLIST item : zs) {
						if(item.getEBELN() == null || item.getEBELN().toString().length() <= 0) 
							continue;
						PackingListDto dto = new PackingListDto();
						dto.setEbeln(item.getEBELN().toString());
						dto.setEbelp(item.getEBELP().toString());
						dto.setEmatn(item.getEMATN().toString());
						dto.setFactor(item.getFACTOR().toString());
						dto.setMatnr(item.getMATNR().toString());
						dto.setMenge(item.getMENGE().toString());
						dto.setMengep(item.getMENGEP().toString());
						dto.setMens(item.getMENS().toString());
						dto.setMenssage(item.getMENSSAGE().toString());
						dto.setMerma(item.getMERMA().toString());
						dto.setMobrap(item.getMOBRAP().toString());
						dto.setMobras(item.getMOBRAS().toString());
						dto.setNtgew(item.getNTGEW().toString());
						dto.setNtgewp(item.getNTGEWP().toString());
						dto.setPesot(item.getPESOT().toString());
						dto.setPesotp(item.getPESOTP().toString());
						dto.setToler(item.getTOLER().toString());	
						dto.setEstat(item.getESTAT().toString());	
						dto.setBorra(item.getBORRA().toString());
						dto.setIdioma(item.getIDIOMA().toString());
						dto.setBedat(item.getBEDAT().toString());
						
						jsonResponse.add(dto);
					}
					Map<String, Object> model = new HashMap<String, Object>(6);
					model.put("moneda", resp.getPE_MONEDA().toString());
					model.put("oro", resp.getPE_ORO().toString());
					model.put("data", jsonResponse);
					model.put("pe_ok", resp.getPE_OK().toString());
				    	model.put("success", true);			    	
				    	model.put("msg","Successful");
					return model;
				} else {
					return getModelMapSuccess(resp.getPE_OK().toString());
				}
			} catch (RemoteException e) {
				LOGGER.error("Error packingList ", e);
				return getModelMapError("Error: " + e.getMessage());
			} catch (Z_FE_FM_PACKING_LISTExceptionException e) {				
				LOGGER.info("E_ERROR: " + Z_FE_FM_PACKING_LISTRfcExceptions.E_ERROR.getValue());
				LOGGER.info("E_ERROR: " + Z_FE_FM_PACKING_LISTRfcExceptions.E_ERROR2.getValue());
				LOGGER.info("E_ERROR: " + Z_FE_FM_PACKING_LISTRfcExceptions.E_ERROR3.getValue());
				LOGGER.error("Error packingList ", e);
//				e.getFaultMessage().getZ_FE_FM_PACKING_LISTException().getName().E_ERROR.getValue();				
				return getModelMapError(Z_FE_FM_PACKING_LISTRfcExceptions.E_ERROR.getValue() + "," + Z_FE_FM_PACKING_LISTRfcExceptions.E_ERROR2.getValue() + "," + Z_FE_FM_PACKING_LISTRfcExceptions.E_ERROR3.getValue());
			}
		} else {
			return getModelMapError("Credentials Error");
		}
	}
	
}
