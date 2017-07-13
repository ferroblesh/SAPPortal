package com.wise.dao;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;

import org.apache.axis2.AxisFault;
import org.apache.axis2.client.Options;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.HttpTransportProperties;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.wise.Constants;
import com.wise.model.InboxDto;

import functions.rfc.sap.document.sap_com.BAPISFLDRA;
import functions.rfc.sap.document.sap_com.Char1;
import functions.rfc.sap.document.sap_com.Char10;
import functions.rfc.sap.document.sap_com.Char16;
import functions.rfc.sap.document.sap_com.Char2;
import functions.rfc.sap.document.sap_com.Char4;
import functions.rfc.sap.document.sap_com.Curr152;
import functions.rfc.sap.document.sap_com.Date;
import functions.rfc.sap.document.sap_com.Date10;
import functions.rfc.sap.document.sap_com.ZST_IMPORTACION;
import functions.rfc.sap.document.sap_com.ZST_ORDENCOMPRA;
import functions.rfc.sap.document.sap_com.ZST_REFER;
import functions.rfc.sap.document.sap_com.ZTT_EBELN;
import functions.rfc.sap.document.sap_com.ZTT_IMPORTACION;
import functions.rfc.sap.document.sap_com.ZTT_ORDENCOMPRA;
import functions.rfc.sap.document.sap_com.ZTT_REFER;
import functions.rfc.sap.document.sap_com.ZWS_AGREGAR_BUZON;
import functions.rfc.sap.document.sap_com.ZWS_AGREGAR_BUZONStub;
import functions.rfc.sap.document.sap_com.ZWS_BORRAR_BUZON;
import functions.rfc.sap.document.sap_com.ZWS_BORRAR_BUZONStub;
import functions.rfc.sap.document.sap_com.ZWS_CONSULTAR_BUZONStub;
import functions.rfc.sap.document.sap_com.Z_FE_FM_AGREGAR_BUZON;
import functions.rfc.sap.document.sap_com.Z_FE_FM_AGREGAR_BUZONResponse;
import functions.rfc.sap.document.sap_com.Z_FE_FM_BORRAR_BUZON;
import functions.rfc.sap.document.sap_com.Z_FE_FM_BORRAR_BUZONResponse;
import functions.rfc.sap.document.sap_com.Z_FE_FM_CONSULTAR_BUZON;
import functions.rfc.sap.document.sap_com.Z_FE_FM_CONSULTAR_BUZONResponse;
import functions.rfc.sap.document.sap_com.Z_FE_FM_LISTA_FAC;

@Repository
public class InboxDao {
	
	private Logger LOGGER = Logger.getLogger(InboxDao.class);
	
	public Z_FE_FM_AGREGAR_BUZONResponse addInbox(String cmbRazonSocial,MultipartFile[] file,String lifnr, String invoices, String referencia, String language, String ebeln, String hdnDivision) throws IOException {
		int timeOutInMilliSeconds = 3 * 60 * 1000; // Three minutes
		String[] invoiceVals = invoices.split(",");
		ZWS_AGREGAR_BUZONStub stub = new ZWS_AGREGAR_BUZONStub();
		Options options = stub._getServiceClient().getOptions();
		HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
		auth.setPreemptiveAuthentication(true);
		auth.setUsername(Constants.WISE_WSDL_USER);
		auth.setPassword(Constants.WISE_WSDL_PASS);
		options.setProperty(HTTPConstants.AUTHENTICATE,auth);
		Z_FE_FM_AGREGAR_BUZON inbox = new Z_FE_FM_AGREGAR_BUZON();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date10 date = new Date10();
		Char4 bukrs = new Char4();		
		Char2 i_idioma = new Char2();
		Char10 i_lifnr = new Char10();
		Char4 i_ekorg = new Char4();
		Char10 i_ebeln = new Char10(); //purchase order
		MultipartFile file1 = file[1]; //PDF
		MultipartFile file2 = file[0]; //XML
		
		File serverFileXML = multipartToFile(file2);
        File serverFilePDF = multipartToFile(file1);
        LOGGER.info("Server PDF File Location=" + serverFilePDF.getAbsolutePath());
        LOGGER.info("Server XML File Location=" + serverFileXML.getAbsolutePath());
        FileDataSource fdsXML = new FileDataSource(serverFileXML);
        FileDataSource fdsPDF = new FileDataSource(serverFilePDF);
        DataHandler pdfHandler = new DataHandler(fdsPDF);
		DataHandler xmlHandler = new DataHandler(fdsXML);
		ZTT_REFER facs = new ZTT_REFER();
		for(String invoiceVal : invoiceVals) {
			ZST_REFER fac = new ZST_REFER();
			Char16 facInfo = new Char16();
			facInfo.setChar16(invoiceVal);
			fac.setXBLNR(facInfo);
			facs.addItem(fac);
		}			
		i_lifnr.setChar10(lifnr);
		i_idioma.setChar2(language.toUpperCase());
		bukrs.setChar4(cmbRazonSocial);
		i_ebeln.setChar10(ebeln);
		if(hdnDivision != null && hdnDivision.length() > 0)
			i_ekorg.setChar4(hdnDivision);
		inbox.setI_BUKRS(bukrs);
		inbox.setI_IDIOMA(i_idioma);
		inbox.setI_LIFNR(i_lifnr);
		inbox.setI_PDF(pdfHandler);
		inbox.setI_XML(xmlHandler);
		inbox.setI_REFER(facs);
//		inbox.setI_EBELN(i_ebeln);
		if(hdnDivision != null && hdnDivision.length() > 0)
			inbox.setI_EKORG(i_ekorg);
		
		Z_FE_FM_AGREGAR_BUZONResponse response = null;
		try {
			stub._getServiceClient().getOptions().setTimeOutInMilliSeconds(timeOutInMilliSeconds);
			response = stub.z_FE_FM_AGREGAR_BUZON(inbox);
		} catch (RemoteException e) {
			LOGGER.error("Error with addInbox:", e);
			return null;
		} catch(Exception e) {
			LOGGER.error("Error with addInbox:", e);
			return null;
		} finally {
			serverFilePDF.delete();
			serverFileXML.delete();	
		}
		
		return response;
		
	}
	
	public Z_FE_FM_AGREGAR_BUZONResponse addInboxCFDI(String cmbRazonSocial, MultipartFile[] file, String lifnr, String hdnOrders, 
													  String hdnImportaciones, String cmbReceptionTypeField, String language) throws IOException, AxisFault, RemoteException {
		int timeOutInMilliSeconds = 3 * 60 * 1000; // Three minutes		
		String[] orders = hdnOrders.split(",");
		String[] imp = hdnImportaciones.split(",");
		ZWS_AGREGAR_BUZONStub stub = new ZWS_AGREGAR_BUZONStub();
		Options options = stub._getServiceClient().getOptions();
		HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
		auth.setPreemptiveAuthentication(true);
		auth.setUsername(Constants.WISE_WSDL_USER);
		auth.setPassword(Constants.WISE_WSDL_PASS);
		options.setProperty(HTTPConstants.AUTHENTICATE,auth);
		Z_FE_FM_AGREGAR_BUZON inbox = new Z_FE_FM_AGREGAR_BUZON();
		
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		Date10 date = new Date10();
		Char4 bukrs = new Char4();		
		Char2 i_idioma = new Char2();
		Char10 i_lifnr = new Char10();
//		Char4 i_ekorg = new Char4();
		Char1 i_tipo_recep = new Char1();
		Char1 i_flag = new Char1();
		ZTT_ORDENCOMPRA i_ebeln = new ZTT_ORDENCOMPRA();//purchase order
		ZTT_IMPORTACION i_importacion = new ZTT_IMPORTACION();
		ZTT_REFER i_refer = new ZTT_REFER();
		ZST_REFER zst_refer = new ZST_REFER();
		Char16 xblnr = new Char16();
		MultipartFile file1 = file[1]; //PDF
		MultipartFile file2 = file[0]; //XML
		
		File serverFileXML = multipartToFile(file2);
        File serverFilePDF = multipartToFile(file1);
        LOGGER.info("Server PDF File Location=" + serverFilePDF.getAbsolutePath());
        LOGGER.info("Server XML File Location=" + serverFileXML.getAbsolutePath());
        FileDataSource fdsXML = new FileDataSource(serverFileXML);
        FileDataSource fdsPDF = new FileDataSource(serverFilePDF);
        DataHandler pdfHandler = new DataHandler(fdsPDF);
		DataHandler xmlHandler = new DataHandler(fdsXML);					
		for(String order : orders) {
			ZST_ORDENCOMPRA item = new ZST_ORDENCOMPRA();
			Char10 ebelnInfo = new Char10();
			ebelnInfo.setChar10(order);	
			item.setEBELN(ebelnInfo);
			i_ebeln.addItem(item);
		}
		for(String importacion : imp) {
			ZST_IMPORTACION param = new ZST_IMPORTACION();
			Char10 zeimportacion = new Char10();
			zeimportacion.setChar10(importacion);			
			param.setZEIMPORTACION(zeimportacion);
			i_importacion.addItem(param);
		}
		
		i_lifnr.setChar10(lifnr);
		i_idioma.setChar2(language.toUpperCase());
		bukrs.setChar4(cmbRazonSocial);		
		i_tipo_recep.setChar1(cmbReceptionTypeField);
		i_flag.setChar1("1");
		xblnr.setChar16("1");
		zst_refer.setXBLNR(xblnr);
		i_refer.addItem(zst_refer);
		
		inbox.setI_BUKRS(bukrs);
		inbox.setI_IDIOMA(i_idioma);
		inbox.setI_LIFNR(i_lifnr);
		inbox.setI_PDF(pdfHandler);
		inbox.setI_XML(xmlHandler);
		inbox.setI_TIPO_RECEP(i_tipo_recep);
//		inbox.setI_REFER(facs);
		inbox.setI_REFER(i_refer);
		inbox.setI_EBELN(i_ebeln);
		inbox.setI_IMPORTACION(i_importacion);
		inbox.setI_FLAG(i_flag);
		
		stub._getServiceClient().getOptions().setTimeOutInMilliSeconds(timeOutInMilliSeconds);
		return stub.z_FE_FM_AGREGAR_BUZON(inbox);		
	}
	
	public Z_FE_FM_AGREGAR_BUZONResponse addInboxSCFDI(String cmbRazonSocial,MultipartFile file,String lifnr, String invoice, String invoiceDate, String iva, 
													   String hdnOrders, String hdnImportaciones, String cmbReceptionTypeField, String language) throws IOException, AxisFault, RemoteException {
		int timeOutInMilliSeconds = 3 * 60 * 1000; // Three minutes		
		String[] orders = hdnOrders.split(",");
		String[] imp = hdnImportaciones.split(",");
		ZWS_AGREGAR_BUZONStub stub = new ZWS_AGREGAR_BUZONStub();
		Options options = stub._getServiceClient().getOptions();
		HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
		auth.setPreemptiveAuthentication(true);
		auth.setUsername(Constants.WISE_WSDL_USER);
		auth.setPassword(Constants.WISE_WSDL_PASS);
		options.setProperty(HTTPConstants.AUTHENTICATE,auth);
		Z_FE_FM_AGREGAR_BUZON inbox = new Z_FE_FM_AGREGAR_BUZON();
		
		Char4 bukrs = new Char4();		
		Char2 i_idioma = new Char2();
		Char10 i_lifnr = new Char10();
		Char1 i_tipo_recep = new Char1();
		Char1 i_flag = new Char1();
		Curr152 i_tax = new Curr152();
		Date i_date = new Date();
		Char16 xblrn = new Char16();
		Char16 i_xblrn = new Char16();
		
		ZTT_ORDENCOMPRA i_ebeln = new ZTT_ORDENCOMPRA();//purchase order
		ZTT_IMPORTACION i_importacion = new ZTT_IMPORTACION();
		ZTT_REFER i_refer = new ZTT_REFER();
		ZST_REFER zst_refer = new ZST_REFER();
		MultipartFile file1 = file; //PDF		
				
        File serverFilePDF = multipartToFile(file1);
        LOGGER.info("Server PDF File Location=" + serverFilePDF.getAbsolutePath());        
        FileDataSource fdsPDF = new FileDataSource(serverFilePDF);
        DataHandler pdfHandler = new DataHandler(fdsPDF);
							
		for(String order : orders) {
			ZST_ORDENCOMPRA item = new ZST_ORDENCOMPRA();
			Char10 ebelnInfo = new Char10();
			ebelnInfo.setChar10(order);	
			item.setEBELN(ebelnInfo);
			i_ebeln.addItem(item);
		}
		for(String importacion : imp) {
			ZST_IMPORTACION param = new ZST_IMPORTACION();
			Char10 zeimportacion = new Char10();
			zeimportacion.setChar10(importacion);			
			param.setZEIMPORTACION(zeimportacion);
			i_importacion.addItem(param);
		}
		LOGGER.info("lifnr:" + lifnr);
		i_lifnr.setChar10(lifnr);
		language = language == null ? "ES" : language;
		LOGGER.info("Language:" + language.toUpperCase());
		i_idioma.setChar2(language.toUpperCase());
		bukrs.setChar4(cmbRazonSocial);		
		LOGGER.info("i_tipo_recep:" + cmbReceptionTypeField);
		i_tipo_recep.setChar1(cmbReceptionTypeField);
		i_flag.setChar1("2");
		i_tax.setCurr152(new BigDecimal(iva));
		i_date.setDate(invoiceDate);
		xblrn.setChar16("1");
		zst_refer.setXBLNR(xblrn);
		i_refer.addItem(zst_refer);
		i_xblrn.setChar16(invoice);
		
		inbox.setI_BUKRS(bukrs);
		inbox.setI_IDIOMA(i_idioma);
		inbox.setI_LIFNR(i_lifnr);
		inbox.setI_PDF(pdfHandler);		
		inbox.setI_TIPO_RECEP(i_tipo_recep);
		inbox.setI_EBELN(i_ebeln);
		inbox.setI_IMPORTACION(i_importacion);
		inbox.setI_FLAG(i_flag);
		inbox.setI_TAX(i_tax);
		inbox.setI_DATE(i_date);
		inbox.setI_REFER(i_refer);
		inbox.setI_XBLNR(i_xblrn);
		
		stub._getServiceClient().getOptions().setTimeOutInMilliSeconds(timeOutInMilliSeconds);
		return stub.z_FE_FM_AGREGAR_BUZON(inbox);		
	}
	
	public Z_FE_FM_CONSULTAR_BUZONResponse findInbox(String cmbRazonSocial, String fechaIni, String fechaFin,String lifnr, String language, String ekorg) throws AxisFault {
		int timeOutInMilliSeconds = 3 * 60 * 1000; // Three minutes
		ZWS_CONSULTAR_BUZONStub stub = new ZWS_CONSULTAR_BUZONStub();
		Options options = stub._getServiceClient().getOptions();
		HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
		auth.setPreemptiveAuthentication(true);
		auth.setUsername(Constants.WISE_WSDL_USER);
		auth.setPassword(Constants.WISE_WSDL_PASS);
		options.setProperty(HTTPConstants.AUTHENTICATE,auth);
		Z_FE_FM_CONSULTAR_BUZON inbox = new Z_FE_FM_CONSULTAR_BUZON();
		Char1 sign = new Char1();
		Char2 option = new Char2();
		Date dateHigh = new Date();
		Date dateLow = new Date();
		Char4 razonSocial = new Char4();	
		Char4 i_ekorg = new Char4();
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
		if(ekorg != null && ekorg.length() > 0)
			i_ekorg.setChar4(ekorg);
		
		inbox.setI_BUKRS(razonSocial);
		inbox.setI_FECHA(bldat);
		inbox.setI_IDIOMA(i_idioma);
		inbox.setI_LIFNR(i_lifnr);
		inbox.setI_EKORG(i_ekorg);
		
		try {
			stub._getServiceClient().getOptions().setTimeOutInMilliSeconds(timeOutInMilliSeconds);
			Z_FE_FM_CONSULTAR_BUZONResponse response = stub.z_FE_FM_CONSULTAR_BUZON(inbox);
			LOGGER.info("Find Inbox data: " + response);
			return response;
		} catch (Exception e) {
			LOGGER.error("Error Consulta Inbox:",e);
			return null;
		}
	}
	
	public Map<String,Object> deleteInbox(InboxDto[] invoices, String language) throws AxisFault {
		int timeOutInMilliSeconds = 3 * 60 * 1000; // Three minutes
		Map<String, Object> result = new HashMap<String, Object>();
		ZWS_BORRAR_BUZONStub stub = new ZWS_BORRAR_BUZONStub(); 
		Options options = stub._getServiceClient().getOptions();
		HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
		auth.setPreemptiveAuthentication(true);
		auth.setUsername(Constants.WISE_WSDL_USER);
		auth.setPassword(Constants.WISE_WSDL_PASS);
		options.setProperty(HTTPConstants.AUTHENTICATE,auth);
		Z_FE_FM_BORRAR_BUZON inbox = new Z_FE_FM_BORRAR_BUZON();
		
		for(InboxDto item : invoices) {
			Char4 i_bukrs = new Char4();
			Char10 i_ebeln = new Char10();
			Char2 i_idioma = new Char2();
			Char10 i_lifnr = new Char10();
			Char16 i_xblnr = new Char16();
			Char10 i_ctrlid = new Char10();
			
			i_ebeln.setChar10(item.getEbeln());
			i_idioma.setChar2(language.toUpperCase());
			i_lifnr.setChar10(item.getLifnr());
			i_xblnr.setChar16(item.getXblnr());
			i_bukrs.setChar4(item.getBukrs());
			i_ctrlid.setChar10(item.getCtrlid());
					
			inbox.setI_EBELN(i_ebeln);
			inbox.setI_IDIOMA(i_idioma);
			inbox.setI_LIFNR(i_lifnr);
			inbox.setI_XBLNR(i_xblnr);
			inbox.setI_BUKRS(i_bukrs);
			inbox.setI_CTRLID(i_ctrlid);
			
			try {
				stub._getServiceClient().getOptions().setTimeOutInMilliSeconds(timeOutInMilliSeconds);
				Z_FE_FM_BORRAR_BUZONResponse response = stub.z_FE_FM_BORRAR_BUZON(inbox);
				LOGGER.info("Inbox deleted xblnr:" + item.getXblnr());
				result.put(item.getXblnr(), response);
			} catch (Exception e) {
				LOGGER.error("Error deleting inbox", e);
				result.put(item.getXblnr(), null);
			}
			
			
		}
		
		return result;
	}
	
	public File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException {
		// Creating the directory to store file
        String rootPath = System.getProperty("catalina.home");
        File dir = new File(rootPath + File.separator + "tmpFiles");
        if (!dir.exists())
            dir.mkdirs();

        // Create the file on server
        
        File tmpFile = new File(dir.getAbsolutePath() + System.getProperty("file.separator") + 
                                multipart.getOriginalFilename());
        multipart.transferTo(tmpFile);
        return tmpFile;
    }

}
