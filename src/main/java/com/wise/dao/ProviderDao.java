package com.wise.dao;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.apache.axis2.AxisFault;
import org.apache.axis2.client.Options;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.HttpTransportProperties;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.wise.Constants;
import com.wise.model.PackingDto;
import com.wise.model.PackingListDto;

import functions.rfc.sap.document.sap_com.BAPISFLDRA;
import functions.rfc.sap.document.sap_com.Char1;
import functions.rfc.sap.document.sap_com.Char10;
import functions.rfc.sap.document.sap_com.Char13;
import functions.rfc.sap.document.sap_com.Char16;
import functions.rfc.sap.document.sap_com.Char18;
import functions.rfc.sap.document.sap_com.Char2;
import functions.rfc.sap.document.sap_com.Char220;
import functions.rfc.sap.document.sap_com.Char4;
import functions.rfc.sap.document.sap_com.Cuky5;
import functions.rfc.sap.document.sap_com.Date;
import functions.rfc.sap.document.sap_com.Date10;
import functions.rfc.sap.document.sap_com.Lang;
import functions.rfc.sap.document.sap_com.Numeric10;
import functions.rfc.sap.document.sap_com.Numeric5;
import functions.rfc.sap.document.sap_com.ZSPACKINGLIST;
import functions.rfc.sap.document.sap_com.ZST_ORDENCOMPRA;
import functions.rfc.sap.document.sap_com.ZTTPACKINGLIST;
import functions.rfc.sap.document.sap_com.ZTT_ORDENCOMPRA;
import functions.rfc.sap.document.sap_com.ZWS_AREA_RESPStub;
import functions.rfc.sap.document.sap_com.ZWS_CAMBIO_PSWD_PROVStub;
import functions.rfc.sap.document.sap_com.ZWS_CONFIRMA_ESTADO_CUENTAStub;
import functions.rfc.sap.document.sap_com.ZWS_CONTADOR_AVISOS_NLEStub;
import functions.rfc.sap.document.sap_com.ZWS_DATOS_PROVEEDORStub;
import functions.rfc.sap.document.sap_com.ZWS_ESTADO_CUENTAStub;
import functions.rfc.sap.document.sap_com.ZWS_ESTATUS_PORTALStub;
import functions.rfc.sap.document.sap_com.ZWS_LISTA_DIVISIONStub;
import functions.rfc.sap.document.sap_com.ZWS_LISTA_FACStub;
import functions.rfc.sap.document.sap_com.ZWS_OBTIENE_AVISOSStub;
import functions.rfc.sap.document.sap_com.ZWS_ORDEN_FACTURAStub;
import functions.rfc.sap.document.sap_com.ZWS_PACKING_LISTStub;
import functions.rfc.sap.document.sap_com.ZWS_RAZON_SOCIALStub;
import functions.rfc.sap.document.sap_com.ZWS_REPORTES_PROVStub;
import functions.rfc.sap.document.sap_com.ZWS_UPDATE_LECTURA_AVISOStub;
import functions.rfc.sap.document.sap_com.Z_FE_FM_AREA_RESP;
import functions.rfc.sap.document.sap_com.Z_FE_FM_AREA_RESPResponse;
import functions.rfc.sap.document.sap_com.Z_FE_FM_CAMBIO_PSWD_PROV;
import functions.rfc.sap.document.sap_com.Z_FE_FM_CAMBIO_PSWD_PROVResponse;
import functions.rfc.sap.document.sap_com.Z_FE_FM_CONFIRMA_ESTADO_CUENTA;
import functions.rfc.sap.document.sap_com.Z_FE_FM_CONFIRMA_ESTADO_CUENTAResponse;
import functions.rfc.sap.document.sap_com.Z_FE_FM_CONTADOR_AVISOS_NLE;
import functions.rfc.sap.document.sap_com.Z_FE_FM_CONTADOR_AVISOS_NLEResponse;
import functions.rfc.sap.document.sap_com.Z_FE_FM_DATOS_PROVEEDOR;
import functions.rfc.sap.document.sap_com.Z_FE_FM_DATOS_PROVEEDORResponse;
import functions.rfc.sap.document.sap_com.Z_FE_FM_DIVISION;
import functions.rfc.sap.document.sap_com.Z_FE_FM_DIVISIONResponse;
import functions.rfc.sap.document.sap_com.Z_FE_FM_ESTADO_CUENTA;
import functions.rfc.sap.document.sap_com.Z_FE_FM_ESTADO_CUENTAResponse;
import functions.rfc.sap.document.sap_com.Z_FE_FM_ESTATUS_PORTAL;
import functions.rfc.sap.document.sap_com.Z_FE_FM_ESTATUS_PORTALResponse;
import functions.rfc.sap.document.sap_com.Z_FE_FM_LISTA_FAC;
import functions.rfc.sap.document.sap_com.Z_FE_FM_LISTA_FACResponse;
import functions.rfc.sap.document.sap_com.Z_FE_FM_OBTIENE_AVISOS;
import functions.rfc.sap.document.sap_com.Z_FE_FM_OBTIENE_AVISOSResponse;
import functions.rfc.sap.document.sap_com.Z_FE_FM_ORDEN_FACTURA;
import functions.rfc.sap.document.sap_com.Z_FE_FM_ORDEN_FACTURAResponse;
import functions.rfc.sap.document.sap_com.Z_FE_FM_PACKING_LIST;
import functions.rfc.sap.document.sap_com.Z_FE_FM_PACKING_LISTExceptionException;
import functions.rfc.sap.document.sap_com.Z_FE_FM_PACKING_LISTResponse;
import functions.rfc.sap.document.sap_com.Z_FE_FM_RAZON_SOCIAL;
import functions.rfc.sap.document.sap_com.Z_FE_FM_RAZON_SOCIALResponse;
import functions.rfc.sap.document.sap_com.Z_FE_FM_REPORTES_PROV;
import functions.rfc.sap.document.sap_com.Z_FE_FM_REPORTES_PROVResponse;
import functions.rfc.sap.document.sap_com.Z_FE_FM_UPDATE_LECTURA_AVISO;
import functions.rfc.sap.document.sap_com.Z_FE_FM_UPDATE_LECTURA_AVISOResponse;

@Repository
public class ProviderDao {
	
	public final static Logger LOGGER = Logger.getLogger(ProviderDao.class);
	
	public Z_FE_FM_DATOS_PROVEEDORResponse findProvider(String provider) throws AxisFault{
		ZWS_DATOS_PROVEEDORStub stub = new ZWS_DATOS_PROVEEDORStub();
		Options options = stub._getServiceClient().getOptions();
		HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
		auth.setPreemptiveAuthentication(true);
		auth.setUsername(Constants.WISE_WSDL_USER);
		auth.setPassword(Constants.WISE_WSDL_PASS);
		options.setProperty(HTTPConstants.AUTHENTICATE,auth);
		Char10 providerChar = new Char10();
		providerChar.setChar10(provider);
		Z_FE_FM_DATOS_PROVEEDOR provedor = new Z_FE_FM_DATOS_PROVEEDOR();
		provedor.setI_LIFNR(providerChar);
		try {
			Z_FE_FM_DATOS_PROVEEDORResponse response = stub.z_FE_FM_DATOS_PROVEEDOR(provedor);
			LOGGER.info("Datos Provedor: " + response.getE_NAME1() + " " + response.getE_NAME2() + " " + response.getE_NAME3());
			return response;
		} catch (RemoteException e) {
			LOGGER.error("ERROR",e);
			return null;
		}
	}
	
	public Z_FE_FM_RAZON_SOCIALResponse findRazonSocial(String lifnr) throws AxisFault {
		ZWS_RAZON_SOCIALStub stub = new ZWS_RAZON_SOCIALStub();
		Options options = stub._getServiceClient().getOptions();
		HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
		auth.setPreemptiveAuthentication(true);
		auth.setUsername(Constants.WISE_WSDL_USER);
		auth.setPassword(Constants.WISE_WSDL_PASS);
		options.setProperty(HTTPConstants.AUTHENTICATE,auth);
		Char10 i_lifnr = new Char10();
		i_lifnr.setChar10(lifnr);
		Z_FE_FM_RAZON_SOCIAL razonSocial = new Z_FE_FM_RAZON_SOCIAL();
		razonSocial.setI_LIFNR(i_lifnr);
		try {
			Z_FE_FM_RAZON_SOCIALResponse response = stub.z_FE_FM_RAZON_SOCIAL(razonSocial);
			LOGGER.info("RazonSocial data: " + response);
			return response;
		} catch (Exception e) {
			LOGGER.error("Error Razon Social:",e);
			return null;
		}
	}
	
	public Z_FE_FM_LISTA_FACResponse findFacturas(String cmbRazonSocial, String fechaIni, String fechaFin,String cmbEstado,String lifnr, String language, String ekorg) throws AxisFault {
		int timeOutInMilliSeconds = 3 * 60 * 1000; // Three minutes
		ZWS_LISTA_FACStub stub = new ZWS_LISTA_FACStub();
		Options options = stub._getServiceClient().getOptions();
		HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
		auth.setPreemptiveAuthentication(true);
		auth.setUsername(Constants.WISE_WSDL_USER);
		auth.setPassword(Constants.WISE_WSDL_PASS);
		options.setProperty(HTTPConstants.AUTHENTICATE,auth);
		Char1 sign = new Char1();
		Char2 option = new Char2();
		Date dateHigh = new Date();
		Date dateLow = new Date();
		Char4 razonSocial = new Char4();
		Char1 status = new Char1();
		Char10 i_lifnr = new Char10();
		Char2 i_idioma = new Char2();
		Char4 i_ekorg = new Char4();
		i_lifnr.setChar10(lifnr);
		dateHigh.setDate(fechaFin);
		dateLow.setDate(fechaIni);
		sign.setChar1("I");
		option.setChar2("BT");		
		razonSocial.setChar4(cmbRazonSocial);
		status.setChar1(cmbEstado);
		Z_FE_FM_LISTA_FAC listFac = new Z_FE_FM_LISTA_FAC();
		BAPISFLDRA bldat = new BAPISFLDRA();
		bldat.setSIGN(sign);
		bldat.setOPTION(option);
		bldat.setHIGH(dateHigh);
		bldat.setLOW(dateLow);
		i_idioma.setChar2(language.toUpperCase());
		i_ekorg.setChar4(ekorg);
		
		listFac.setI_BLDAT(bldat);
		listFac.setI_BUKRS(razonSocial);
		listFac.setI_ESTATUS(status);
		listFac.setI_LIFNR(i_lifnr);
		listFac.setI_IDIOMA(i_idioma);
		listFac.setI_EKORG(i_ekorg);
		
		try {
			stub._getServiceClient().getOptions().setTimeOutInMilliSeconds(timeOutInMilliSeconds);
			Z_FE_FM_LISTA_FACResponse response = stub.z_FE_FM_LISTA_FAC(listFac);
			LOGGER.info("z_FE_FM_LISTA_FAC data: " + response);
			return response;
		} catch (RemoteException e) {
			LOGGER.error("Error Lista Fac:",e);
			return null;
		}
	}
	
	public Z_FE_FM_ESTATUS_PORTALResponse findInvoiceStatus(String cmbRazonSocial, String fechaIni, String fechaFin,String lifnr, String language) throws AxisFault {
		int timeOutInMilliSeconds = 3 * 60 * 1000; // Three minutes 
		ZWS_ESTATUS_PORTALStub stub = new ZWS_ESTATUS_PORTALStub();
		Options options = stub._getServiceClient().getOptions();
		HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
		auth.setPreemptiveAuthentication(true);
		auth.setUsername(Constants.WISE_WSDL_USER);
		auth.setPassword(Constants.WISE_WSDL_PASS);
		options.setProperty(HTTPConstants.AUTHENTICATE,auth);
		Char1 sign = new Char1();
		Char2 option = new Char2();
		Date dateHigh = new Date();
		Date dateLow = new Date();
		Char4 razonSocial = new Char4();		
		Char10 i_lifnr = new Char10();
		Char2 i_idioma = new Char2();
		i_lifnr.setChar10(lifnr);
		dateHigh.setDate(fechaFin);
		dateLow.setDate(fechaIni);
		sign.setChar1("I");
		option.setChar2("BT");		
		razonSocial.setChar4(cmbRazonSocial);
		BAPISFLDRA bldat = new BAPISFLDRA();
		bldat.setSIGN(sign);
		bldat.setOPTION(option);
		bldat.setHIGH(dateHigh);
		bldat.setLOW(dateLow);
		i_idioma.setChar2(language.toUpperCase());
		
		Z_FE_FM_ESTATUS_PORTAL status = new Z_FE_FM_ESTATUS_PORTAL();
		status.setI_BLDAT(bldat);
		status.setI_BUKRS(razonSocial);
		status.setI_LIFNR(i_lifnr);
		status.setI_IDIOMA(i_idioma);
		try {
			stub._getServiceClient().getOptions().setTimeOutInMilliSeconds(timeOutInMilliSeconds);
			Z_FE_FM_ESTATUS_PORTALResponse response = stub.z_FE_FM_ESTATUS_PORTAL(status);
			LOGGER.info("Z_FE_FM_ESTATUS_PORTALResponse:"+ response);
			return response;
		} catch (Exception e) {
			LOGGER.error("Error Estatus Integración:",e);
			return null;
		}
	}
	
	public Z_FE_FM_AREA_RESPResponse findGerencia(String bukrs) throws AxisFault {
		ZWS_AREA_RESPStub stub = new ZWS_AREA_RESPStub();
		Options options = stub._getServiceClient().getOptions();
		HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
		auth.setPreemptiveAuthentication(true);
		auth.setUsername(Constants.WISE_WSDL_USER);
		auth.setPassword(Constants.WISE_WSDL_PASS);
		options.setProperty(HTTPConstants.AUTHENTICATE,auth);
		Z_FE_FM_AREA_RESP area = new Z_FE_FM_AREA_RESP();		
		Char4 i_bukrs = new Char4();
		i_bukrs.setChar4(bukrs);
		area.setI_BUKRS(i_bukrs);
		
		Z_FE_FM_AREA_RESPResponse response;
		try {
			response = stub.z_FE_FM_AREA_RESP(area);
		} catch (Exception e) {
			LOGGER.error("Error al obtener area", e);
			return null;
		}
		
		return response;
		
	}
	
	public Z_FE_FM_DIVISIONResponse findDivision(String bukrs) throws AxisFault {
		ZWS_LISTA_DIVISIONStub stub = new ZWS_LISTA_DIVISIONStub();
		Options options = stub._getServiceClient().getOptions();
		HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
		auth.setPreemptiveAuthentication(true);
		auth.setUsername(Constants.WISE_WSDL_USER);
		auth.setPassword(Constants.WISE_WSDL_PASS);
		options.setProperty(HTTPConstants.AUTHENTICATE,auth);
		Z_FE_FM_DIVISION division = new Z_FE_FM_DIVISION();
		Char4 i_bukrs = new Char4();
		i_bukrs.setChar4(bukrs);
		division.setI_BUKRS(i_bukrs);
		
		Z_FE_FM_DIVISIONResponse response;
		try {
			response = stub.z_FE_FM_DIVISION(division);
		} catch (Exception e) {
			LOGGER.error("Error al obtener division",e);
			return null;
		}
		return response;
	}

	public Z_FE_FM_ORDEN_FACTURAResponse findPendingInvoices(String bukrs, String ekorg, String lifnr, String fechaIni, 
			String fechaFin, String ebeln, String language, Integer receptionType, String currency, String orders, String numImp) throws AxisFault{
		int timeOutInMilliSeconds = 3 * 60 * 1000; // Three minutes
		ZWS_ORDEN_FACTURAStub stub = new ZWS_ORDEN_FACTURAStub();
		Options options = stub._getServiceClient().getOptions();
		HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
		auth.setPreemptiveAuthentication(true);
		auth.setUsername(Constants.WISE_WSDL_USER);
		auth.setPassword(Constants.WISE_WSDL_PASS);
		options.setProperty(HTTPConstants.AUTHENTICATE,auth);
		Z_FE_FM_ORDEN_FACTURA invoice = new Z_FE_FM_ORDEN_FACTURA();
		Char1 sign = new Char1();
		Char2 option = new Char2();
		Date dateHigh = new Date();
		Date dateLow = new Date();
		Char4 i_bukrs = new Char4();
		Char10 i_ebeln = new Char10();
		Char4 i_ekorg = new Char4();
		Char10 i_lifnr = new Char10();
		Char1 i_flag = new Char1();
		Numeric10 i_folio = new Numeric10();
		BAPISFLDRA bapisfldra = new BAPISFLDRA();
		Char1 i_tipo = new Char1();
		Cuky5 i_waers = new Cuky5();
		Char2 i_idioma = new Char2();
		Char10 i_importacion = new Char10();
		ZTT_ORDENCOMPRA i_ordencompra = new ZTT_ORDENCOMPRA();		
		List<ZST_ORDENCOMPRA> listOrder = new ArrayList<ZST_ORDENCOMPRA>();
		
		i_bukrs.setChar4(bukrs);
		i_ebeln.setChar10(ebeln == null ? "" : ebeln);
		i_ekorg.setChar4(ekorg);
		i_lifnr.setChar10(lifnr);
		i_flag.setChar1("");
		i_folio.setNumeric10("");
		i_idioma.setChar2(language.toUpperCase());
		sign.setChar1("I");
		option.setChar2("BT");
		dateLow.setDate(fechaIni);
		dateHigh.setDate(fechaFin);
		bapisfldra.setSIGN(sign);
		bapisfldra.setOPTION(option);
		bapisfldra.setHIGH(dateHigh);
		bapisfldra.setLOW(dateLow);
		i_tipo.setChar1(receptionType.toString());
		i_importacion.setChar10(numImp != null && numImp.length() > 0 ? numImp : "");
		if(currency != null && currency.length() > 0)
			i_waers.setCuky5(currency);
		if(orders != null && orders.length() > 0) {
			
			String[] ordersArray = orders.split(",");
		
			for(int i = 0; i < ordersArray.length; i++) {
				ZST_ORDENCOMPRA order = new ZST_ORDENCOMPRA();
				Char10 ebelnVal = new Char10();
				ebelnVal.setChar10(ordersArray[i]);
				order.setEBELN(ebelnVal);
				listOrder.add(order);
			}
			if(listOrder.size() > 0){
				ZST_ORDENCOMPRA[] orderValues = new ZST_ORDENCOMPRA[listOrder.size()];
				listOrder.toArray(orderValues);
				i_ordencompra.setItem(orderValues);
			}
		}
		
		invoice.setI_BUKRS(i_bukrs);
		invoice.setI_EBELN(i_ebeln);
		invoice.setI_EKORG(i_ekorg);
		invoice.setI_LIFNR(i_lifnr);
		invoice.setI_FLAG(i_flag);
		invoice.setI_FOLIO(i_folio);
		invoice.setI_AEDAT(bapisfldra);
		invoice.setI_IDIOMA(i_idioma);
		invoice.setI_TIPO(i_tipo);
		if(orders != null && orders.length() > 0)
			invoice.setI_ORDENCOMPRA(i_ordencompra);
		invoice.setI_IMPORTACION(i_importacion);
		if(currency != null && currency.length() > 0)
			invoice.setI_WAERS(i_waers);
		
		Z_FE_FM_ORDEN_FACTURAResponse response;
		try {
			stub._getServiceClient().getOptions().setTimeOutInMilliSeconds(timeOutInMilliSeconds);
			response = stub.z_FE_FM_ORDEN_FACTURA(invoice);
		} catch (Exception e) {
			LOGGER.error("Error al obtener Lista OC sin facturar",e);
			return null;
		}
		return response;
	}
	
	public Z_FE_FM_ESTADO_CUENTAResponse findAccountStatus(String lifnr, String bukrs, String language) throws RemoteException,AxisFault {
		LOGGER.info("Finding account status with bukrs: " + bukrs + ",lifnr: " + lifnr + ",language: " + language);
		ZWS_ESTADO_CUENTAStub stub = new ZWS_ESTADO_CUENTAStub();
		Options options = stub._getServiceClient().getOptions();
		HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
		auth.setPreemptiveAuthentication(true);
		auth.setUsername(Constants.WISE_WSDL_USER);
		auth.setPassword(Constants.WISE_WSDL_PASS);
		options.setProperty(HTTPConstants.AUTHENTICATE,auth);
		Z_FE_FM_ESTADO_CUENTA account = new Z_FE_FM_ESTADO_CUENTA();
		Char4 i_burks = new Char4();
		Char10 i_lifnr = new Char10();
		Char2 i_idioma = new Char2();
		
		i_burks.setChar4(bukrs);
		i_lifnr.setChar10(lifnr);
		i_idioma.setChar2(language.toUpperCase());
		
		account.setI_BUKRS(i_burks);
		account.setI_LIFNR(i_lifnr);
		account.setI_IDIOMA(i_idioma);
		
		Z_FE_FM_ESTADO_CUENTAResponse response;
		try {
			response = stub.z_FE_FM_ESTADO_CUENTA(account);
		} catch (Exception e) {
			LOGGER.error("Error al obtener Estado de cuenta",e);
			return null;
		}
		
		
		return response;
	}
	
	public Z_FE_FM_CONFIRMA_ESTADO_CUENTAResponse setAccountStatus(String lifnr, String bukrs, String language, String estado) throws RemoteException, AxisFault, Exception {
		LOGGER.info("Set account status parameters lifnr:" + lifnr + ", bukrs: " + bukrs + ",language: " + language);
		ZWS_CONFIRMA_ESTADO_CUENTAStub stub = new ZWS_CONFIRMA_ESTADO_CUENTAStub();
		Options options = stub._getServiceClient().getOptions();
		HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
		auth.setPreemptiveAuthentication(true);
		auth.setUsername(Constants.WISE_WSDL_USER);
		auth.setPassword(Constants.WISE_WSDL_PASS);
		options.setProperty(HTTPConstants.AUTHENTICATE,auth);
		Z_FE_FM_CONFIRMA_ESTADO_CUENTA account = new Z_FE_FM_CONFIRMA_ESTADO_CUENTA();
		
		Char4 i_burks = new Char4();
		Char10 i_lifnr = new Char10();
		Char2 i_idioma = new Char2();
		Char1 i_estado = new Char1();
		
		i_burks.setChar4(bukrs);
		i_lifnr.setChar10(lifnr);
		i_idioma.setChar2(language.toUpperCase());
		i_estado.setChar1(estado);		
		
		account.setI_BUKRS(i_burks);
		account.setI_LIFNR(i_lifnr);
		account.setI_IDIOMA(i_idioma);
		account.setI_ESTADO(i_estado);
		
		return stub.z_FE_FM_CONFIRMA_ESTADO_CUENTA(account);
	}
	
	public Z_FE_FM_CAMBIO_PSWD_PROVResponse changePassword(String lifnr,
			String oldPass, String newPass, String newPassReview,
			String language) throws AxisFault, RemoteException {
		ZWS_CAMBIO_PSWD_PROVStub stub = new ZWS_CAMBIO_PSWD_PROVStub();
		Options options = stub._getServiceClient().getOptions();
		HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
		auth.setPreemptiveAuthentication(true);
		auth.setUsername(Constants.WISE_WSDL_USER);
		auth.setPassword(Constants.WISE_WSDL_PASS);
		options.setProperty(HTTPConstants.AUTHENTICATE,auth);
		Z_FE_FM_CAMBIO_PSWD_PROV provider = new Z_FE_FM_CAMBIO_PSWD_PROV();
		
		Char10 i_lifnr = new Char10();
		Char16 i_psw_antiguo = new Char16();
		Char16 i_psw_nuevo = new Char16();
		Char16 i_psw_conf = new Char16();
		Char2 i_idioma = new Char2();
		
		i_lifnr.setChar10(lifnr);
		i_psw_antiguo.setChar16(oldPass);
		i_psw_nuevo.setChar16(newPass);
		i_psw_conf.setChar16(newPassReview);
		i_idioma.setChar2(language);
		
		provider.setI_LIFNR(i_lifnr);
		provider.setI_PSW_ANTIGUO(i_psw_antiguo);
		provider.setI_PSW_NUEVO(i_psw_nuevo);
		provider.setI_PSW_CONF(i_psw_conf);
		provider.setI_IDIOMA(i_idioma);
				
		return stub.z_FE_FM_CAMBIO_PSWD_PROV(provider);
	}
	
	public Z_FE_FM_REPORTES_PROVResponse findReports(String lifrn) throws AxisFault, RemoteException{
		ZWS_REPORTES_PROVStub stub = new ZWS_REPORTES_PROVStub();
		int timeOutInMilliSeconds = 3 * 60 * 1000; // Three minutes
		Options options = stub._getServiceClient().getOptions();
		HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
		auth.setPreemptiveAuthentication(true);
		auth.setUsername(Constants.WISE_WSDL_USER);
		auth.setPassword(Constants.WISE_WSDL_PASS);
		options.setProperty(HTTPConstants.AUTHENTICATE,auth);	
		options.setTimeOutInMilliSeconds(timeOutInMilliSeconds);
		Z_FE_FM_REPORTES_PROV reportes = new Z_FE_FM_REPORTES_PROV();
		Char10 i_lifnr = new Char10();
		
		i_lifnr.setChar10(lifrn);
		
		reportes.setI_LIFNR(i_lifnr);
				
		return stub.z_FE_FM_REPORTES_PROV(reportes);
	}
	
	public Z_FE_FM_CONTADOR_AVISOS_NLEResponse findUnreadNotificationsNumber(String lifrn) throws AxisFault, RemoteException {
		int timeOutInMilliSeconds = 3 * 60 * 1000; // Three minutes
		ZWS_CONTADOR_AVISOS_NLEStub stub = new ZWS_CONTADOR_AVISOS_NLEStub();
		Options options = stub._getServiceClient().getOptions();
		HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
		auth.setPreemptiveAuthentication(true);
		auth.setUsername(Constants.WISE_WSDL_USER);
		auth.setPassword(Constants.WISE_WSDL_PASS);
		options.setProperty(HTTPConstants.AUTHENTICATE,auth);
		Z_FE_FM_CONTADOR_AVISOS_NLE contador = new Z_FE_FM_CONTADOR_AVISOS_NLE();
		
		Char10 i_lifnr = new Char10();
		i_lifnr.setChar10(lifrn);
		contador.setI_LIFNR(i_lifnr);
		stub._getServiceClient().getOptions().setTimeOutInMilliSeconds(timeOutInMilliSeconds);
		
		return stub.z_FE_FM_CONTADOR_AVISOS_NLE(contador);
	}
	
	public Z_FE_FM_UPDATE_LECTURA_AVISOResponse setNotificationStatus(String lifrn, String bukrs, String idaviso, String evento) throws AxisFault, RemoteException {
		int timeOutInMilliSeconds = 3 * 60 * 1000; // Three minutes
		ZWS_UPDATE_LECTURA_AVISOStub stub = new ZWS_UPDATE_LECTURA_AVISOStub();
		Options options = stub._getServiceClient().getOptions();
		HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
		auth.setPreemptiveAuthentication(true);
		auth.setUsername(Constants.WISE_WSDL_USER);
		auth.setPassword(Constants.WISE_WSDL_PASS);
		options.setProperty(HTTPConstants.AUTHENTICATE,auth);
		Z_FE_FM_UPDATE_LECTURA_AVISO aviso = new Z_FE_FM_UPDATE_LECTURA_AVISO();
		
		Char10 i_avisoid = new Char10();
		Char4 i_bukrs = new Char4();
		Char1 i_evento = new Char1();
		Char10 i_lifnr = new Char10();
		
		i_avisoid.setChar10(idaviso);
		i_bukrs.setChar4(bukrs);
		i_evento.setChar1(evento);
		i_lifnr.setChar10(lifrn);
		
		aviso.setI_AVISOID(i_avisoid);
		aviso.setI_BUKRS(i_bukrs);
		aviso.setI_EVENTO(i_evento);
		aviso.setI_LIFNR(i_lifnr);
		
		stub._getServiceClient().getOptions().setTimeOutInMilliSeconds(timeOutInMilliSeconds);
		
		return stub.z_FE_FM_UPDATE_LECTURA_AVISO(aviso);
	}
	
	public Z_FE_FM_OBTIENE_AVISOSResponse getNotifications(String lifnr, String bukrs, String language, String tipoAviso) throws AxisFault,RemoteException {
		int timeOutInMilliSeconds = 3 * 60 * 1000; // Three minutes
		ZWS_OBTIENE_AVISOSStub stub = new ZWS_OBTIENE_AVISOSStub();
		Options options = stub._getServiceClient().getOptions();
		HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
		auth.setPreemptiveAuthentication(true);
		auth.setUsername(Constants.WISE_WSDL_USER);
		auth.setPassword(Constants.WISE_WSDL_PASS);
		options.setProperty(HTTPConstants.AUTHENTICATE,auth);
		Z_FE_FM_OBTIENE_AVISOS notifications = new Z_FE_FM_OBTIENE_AVISOS();
		
		Char4 i_bukrs = new Char4();
		Char2 i_idioma = new Char2();
		Char10 i_lifnr = new Char10();
		Char1 i_tipo_aviso = new Char1();
		
		i_bukrs.setChar4(bukrs == null ? "" : bukrs);
		i_lifnr.setChar10(lifnr);
		i_idioma.setChar2(language);
		i_tipo_aviso.setChar1(tipoAviso);
		
		if(bukrs != null && !bukrs.equals("")) notifications.setI_BUKRS(i_bukrs);
		notifications.setI_IDIOMA(i_idioma);
		notifications.setI_LIFNR(i_lifnr);
		notifications.setI_TIPO_AVISO(i_tipo_aviso);
		
		stub._getServiceClient().getOptions().setTimeOutInMilliSeconds(timeOutInMilliSeconds);
		return stub.z_FE_FM_OBTIENE_AVISOS(notifications);
	}
	
	public Z_FE_FM_PACKING_LISTResponse getPackingList(String lifnr, PackingDto requestValues, String language) throws RemoteException, Z_FE_FM_PACKING_LISTExceptionException {
		int timeOutInMilliSeconds = 3 * 60 * 1000; // Three minutes
		ZWS_PACKING_LISTStub stub = new ZWS_PACKING_LISTStub();
		Options options = stub._getServiceClient().getOptions();
		HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
		auth.setPreemptiveAuthentication(true);
		auth.setUsername(Constants.WISE_WSDL_USER);
		auth.setPassword(Constants.WISE_WSDL_PASS);
		options.setProperty(HTTPConstants.AUTHENTICATE,auth);
		options.setTimeOutInMilliSeconds(timeOutInMilliSeconds);
		
		Z_FE_FM_PACKING_LIST req = new Z_FE_FM_PACKING_LIST();
		LOGGER.info("Language Packing List: " + language);
		if("C".equals(requestValues.getIndicator())) {
			Char10 i_lifnr = new Char10();
			Char10 i_eveln = new Char10();
			Char1 i_indicador = new Char1();
			Lang lang = new Lang();
			ZTTPACKINGLIST packing = new ZTTPACKINGLIST();
			ZSPACKINGLIST param = new ZSPACKINGLIST();
			
			i_lifnr.setChar10(lifnr);
			i_eveln.setChar10(requestValues.getEbeln());
			i_indicador.setChar1(requestValues.getIndicator());
			lang.setLang("ES".equals(language) ? "S" : "E") ;
			
			packing.addItem(param);
			Char10 ebeln = new Char10();
			Numeric5 ebelp = new Numeric5();
			
			param.setEBELN(ebeln);
			param.setEBELP(ebelp);
			Char18 matnr = new Char18();
			param.setMATNR(matnr);
			Char18 ematn = new Char18();
			param.setEMATN(ematn);
			Char1 borra = new Char1();
			param.setBORRA(borra);
			Char13 menge = new Char13();
			param.setMENGE(menge);
			Char13 mengep = new Char13();
			param.setMENGEP(mengep);
			Char13 factor = new Char13();
			param.setFACTOR(factor);
			Char13 merma = new Char13();
			param.setMERMA(merma);
			Char13 ntgew = new Char13();
			param.setNTGEW(ntgew);
			Char13 ntgewp = new Char13();
			param.setNTGEWP(ntgewp);
			Char10 toler = new Char10();
			param.setTOLER(toler);
			Char13 mobras = new Char13();
			param.setMOBRAS(mobras);
			Char13 mobrap = new Char13();
			param.setMOBRAP(mobrap);
			Char13 pesot = new Char13();
			param.setPESOT(pesot);
			Char13 pesotp = new Char13();
			param.setPESOTP(pesotp);
			Char220 mens = new Char220();
			param.setMENS(mens);
			Char220 menssage = new Char220();
			param.setMENSSAGE(menssage);
			Char1 estat = new Char1();
			param.setESTAT(estat);
			Lang idioma = new Lang();
			param.setIDIOMA(idioma);
			Date bedat = new Date();
			param.setBEDAT(bedat);
			
			req.setPI_LIFNR(i_lifnr);
			req.setPI_EVELN(i_eveln);
			req.setPI_INDICADOR(i_indicador);
			req.setPI_IDIOMA(lang);
			
			
			req.setPT_PACKING(packing);
		} else {
			LOGGER.info("INDICATOR: " + requestValues.getIndicator());
			Char10 i_lifnr = new Char10();
			Char10 i_eveln = new Char10();
			Char1 i_indicador = new Char1();
			Lang lang = new Lang();
			ZTTPACKINGLIST packing = new ZTTPACKINGLIST();
//			ZSPACKINGLIST param = new ZSPACKINGLIST();
			
			i_lifnr.setChar10(lifnr);
			i_eveln.setChar10(requestValues.getEbeln());
			i_indicador.setChar1(requestValues.getIndicator());
			
			lang.setLang("ES".equals(language) ? "S" : "E") ;
			
			for(PackingListDto packingListDto : requestValues.getPackingList()) {
				ZSPACKINGLIST param = new ZSPACKINGLIST();
				Char10 ebeln = new Char10();
				Numeric5 ebelp = new Numeric5();
				Char18 matnr = new Char18();
				Char18 ematn = new Char18();
				Char1 borra = new Char1();
				Char13 menge = new Char13();
				Char13 mengep = new Char13();
				Char13 factor = new Char13();
				Char13 merma = new Char13();
				Char13 ntgew = new Char13();
				Char13 ntgewp = new Char13();
				Char10 toler = new Char10();
				Char13 mobras = new Char13();
				Char13 mobrap = new Char13();
				Char13 pesot = new Char13();
				Char13 pesotp = new Char13();
				Char220 mens = new Char220();
				Char220 menssage = new Char220();
				Char1 estat = new Char1();
				Lang idioma = new Lang();
				Date bedat = new Date();
				
				ebeln.setChar10(packingListDto.getEbeln());
				ebelp.setNumeric5(packingListDto.getEbelp());
				ematn.setChar18(packingListDto.getEmatn());
				factor.setChar13(packingListDto.getFactor());
				matnr.setChar18(packingListDto.getMatnr());
				mengep.setChar13(packingListDto.getMengep());		
				menssage.setChar220(packingListDto.getMenssage());
				merma.setChar13(packingListDto.getMerma());
				mobrap.setChar13(packingListDto.getMobrap());
				mobras.setChar13(packingListDto.getMobras());
				ntgewp.setChar13(packingListDto.getNtgewp());
				pesotp.setChar13(packingListDto.getPesotp());
				pesot.setChar13(packingListDto.getPesot());
				ntgew.setChar13(packingListDto.getNtgew());
								
				borra.setChar1(packingListDto.getBorra());
				toler.setChar10(packingListDto.getToler());
				mens.setChar220(packingListDto.getMens());
				estat.setChar1(packingListDto.getEstat());
				idioma.setLang(packingListDto.getIdioma());		
				bedat.setDate(packingListDto.getBedat());
								
				menge.setChar13(packingListDto.getMenge());
								
				param.setEBELN(ebeln);
				param.setEBELP(ebelp);
				param.setEMATN(ematn);
				param.setFACTOR(factor);
				param.setMATNR(matnr);
				param.setMENGEP(mengep);
				param.setMENSSAGE(menssage);
				param.setMERMA(merma);
				param.setMOBRAP(mobrap);
				param.setMOBRAS(mobras);
				param.setNTGEWP(ntgewp);				
				param.setPESOTP(pesotp);
				param.setPESOT(pesot);
				param.setNTGEW(ntgew);
				
				param.setMENGE(menge);
				
				param.setBORRA(borra);
				param.setTOLER(toler);
				param.setMENS(mens);
				param.setESTAT(estat);
				param.setIDIOMA(idioma);
				param.setBEDAT(bedat);
				
				packing.addItem(param);
			}
			
			req.setPI_LIFNR(i_lifnr);
			req.setPI_EVELN(i_eveln);
			req.setPI_INDICADOR(i_indicador);
			req.setPI_IDIOMA(lang);
			req.setPT_PACKING(packing);	
		}
		return stub.z_FE_FM_PACKING_LIST(req);
	}

}
