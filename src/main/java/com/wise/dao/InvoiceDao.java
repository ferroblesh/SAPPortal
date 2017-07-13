package com.wise.dao;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;

import org.apache.axis2.client.Options;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.HttpTransportProperties;
import org.apache.axis2.util.Base64;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.wise.Constants;

import functions.rfc.sap.document.sap_com.Char1;
import functions.rfc.sap.document.sap_com.Char10;
import functions.rfc.sap.document.sap_com.Char16;
import functions.rfc.sap.document.sap_com.Char2;
import functions.rfc.sap.document.sap_com.Char25;
import functions.rfc.sap.document.sap_com.Char30;
import functions.rfc.sap.document.sap_com.Char4;
import functions.rfc.sap.document.sap_com.Char50;
import functions.rfc.sap.document.sap_com.Cuky5;
import functions.rfc.sap.document.sap_com.Curr132;
import functions.rfc.sap.document.sap_com.Curr152;
import functions.rfc.sap.document.sap_com.Date;
import functions.rfc.sap.document.sap_com.Date10;
import functions.rfc.sap.document.sap_com.Decimal234;
import functions.rfc.sap.document.sap_com.Numeric10;
import functions.rfc.sap.document.sap_com.Numeric4;
import functions.rfc.sap.document.sap_com.Numeric5;
import functions.rfc.sap.document.sap_com.Numeric6;
import functions.rfc.sap.document.sap_com.Quantum133;
import functions.rfc.sap.document.sap_com.Unit3;
import functions.rfc.sap.document.sap_com.ZST_EBELN;
import functions.rfc.sap.document.sap_com.ZST_REFER;
import functions.rfc.sap.document.sap_com.ZTT_EBELN;
import functions.rfc.sap.document.sap_com.ZTT_REFER;
import functions.rfc.sap.document.sap_com.ZWS_AGREGAR_FAC_ORDERStub;
import functions.rfc.sap.document.sap_com.ZWS_AGREGAR_FAC_ORDER_VIStub;
import functions.rfc.sap.document.sap_com.ZWS_AGREGAR_FAC_SIN_ORDERStub;
import functions.rfc.sap.document.sap_com.Z_FE_FM_AGREGAR_FAC_ORDER;
import functions.rfc.sap.document.sap_com.Z_FE_FM_AGREGAR_FAC_ORDERResponse;
import functions.rfc.sap.document.sap_com.Z_FE_FM_AGREGAR_FAC_ORDER_VI;
import functions.rfc.sap.document.sap_com.Z_FE_FM_AGREGAR_FAC_ORDER_VIResponse;
import functions.rfc.sap.document.sap_com.Z_FE_FM_AGREGAR_FAC_SIN_ORDER;
import functions.rfc.sap.document.sap_com.Z_FE_FM_AGREGAR_FAC_SIN_ORDERResponse;

/**
 * @author ferroblesh
 *
 */
@Repository
public class InvoiceDao {
	
	private static final Logger LOGGER = Logger.getLogger(InvoiceDao.class);
	
	public Z_FE_FM_AGREGAR_FAC_ORDERResponse addCFDI(String cmbRazonSocial,MultipartFile[] file,String lifnr, String invoices, String referencia, String language, String hdnDivision) throws IOException, JSONException {
		int timeOutInMilliSeconds = 3 * 60 * 1000; // Three minutes
		String[] invoiceVals = invoices.split(",");		
						
		ZWS_AGREGAR_FAC_ORDERStub stub = new ZWS_AGREGAR_FAC_ORDERStub();
		Options options = stub._getServiceClient().getOptions();
		HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
		auth.setPreemptiveAuthentication(true);
		auth.setUsername(Constants.WISE_WSDL_USER);
		auth.setPassword(Constants.WISE_WSDL_PASS);
		options.setProperty(HTTPConstants.AUTHENTICATE,auth);
		Z_FE_FM_AGREGAR_FAC_ORDER invoice = new Z_FE_FM_AGREGAR_FAC_ORDER();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date10 date = new Date10();
		Char4 bukrs = new Char4();
		Char1 flag = new Char1();
		Char2 i_idioma = new Char2();
		Char10 i_lifnr = new Char10();
		Char4 i_ekorg = new Char4();
		Char30 i_kidno = new Char30();
		MultipartFile file1 = file[1]; //PDF
		MultipartFile file2 = file[0]; //XML
		
		MultipartFile file3 = file[2]; //otherPDF
        // Create the file on server
        File serverFileXML = multipartToFile(file2);
        File serverFilePDF = multipartToFile(file1);
        File serverFileOtherPDF = file3.isEmpty() ? null : multipartToFile(file3);
        
        LOGGER.info("Server PDF File Location=" + serverFilePDF.getAbsolutePath());
        LOGGER.info("Server XML File Location=" + serverFileXML.getAbsolutePath());
//        LOGGER.info("Server Other PDF File Location=" + serverFileOtherPDF != null ? serverFileOtherPDF.getAbsolutePath() : "No file received");
        FileDataSource fdsXML = new FileDataSource(serverFileXML);
        FileDataSource fdsPDF = new FileDataSource(serverFilePDF);
        FileDataSource fdsOtherPDF = new FileDataSource(serverFileOtherPDF);
		DataHandler pdfHandler = new DataHandler(fdsPDF);
		DataHandler xmlHandler = new DataHandler(fdsXML);		
		DataHandler otherPDFHandler = new DataHandler(fdsOtherPDF);
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
		if(hdnDivision != null && hdnDivision.length() > 0)
			i_ekorg.setChar4(hdnDivision);
		flag.setChar1("1");
		date.setDate10(sdf.format(new java.util.Date()));
		bukrs.setChar4(cmbRazonSocial);	
		if(referencia != null && referencia.length() > 0)
			i_kidno.setChar30(referencia);
		
		invoice.setI_BUKRS(bukrs);
		invoice.setI_FLAG(flag);
		invoice.setI_LIFNR(i_lifnr);
		invoice.setI_PDF(pdfHandler);
		invoice.setI_XML(xmlHandler);
		if(serverFileOtherPDF != null)
			invoice.setI_OPDF(otherPDFHandler);
		invoice.setI_REFER(facs);
		if(referencia != null && referencia.length() > 0)
			invoice.setI_KIDNO(i_kidno);
		invoice.setI_IDIOMA(i_idioma);
		if(hdnDivision != null && hdnDivision.length() > 0)
			invoice.setI_EKORG(i_ekorg);	
		
		Z_FE_FM_AGREGAR_FAC_ORDERResponse response = null;
		try {
			stub._getServiceClient().getOptions().setTimeOutInMilliSeconds(timeOutInMilliSeconds);
			response = stub.z_FE_FM_AGREGAR_FAC_ORDER(invoice);			
		} catch (RemoteException e) {
			LOGGER.error("Error with addCFDI:", e);
			return null;
		} catch(Exception e) {
			LOGGER.error("Error with addCFDI:", e);
			return null;
		} finally {
			serverFilePDF.delete();
			serverFileXML.delete();			
			if(serverFileOtherPDF != null)
				serverFileOtherPDF.delete();
		}
		
		return response;
	}
	
	public Z_FE_FM_AGREGAR_FAC_ORDER_VIResponse addCFDIVI(String cmbRazonSocial,MultipartFile[] file,String lifnr, JSONArray invoices, String referencia, String language, String hdnDivision, String subto, String receptionType, String moneda) throws IOException, JSONException {
		int timeOutInMilliSeconds = 3 * 60 * 1000; // Three minutes					
		
		ZWS_AGREGAR_FAC_ORDER_VIStub stub = new ZWS_AGREGAR_FAC_ORDER_VIStub();
		
		Options options = stub._getServiceClient().getOptions();
		HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
		auth.setPreemptiveAuthentication(true);
		auth.setUsername(Constants.WISE_WSDL_USER);
		auth.setPassword(Constants.WISE_WSDL_PASS);
		options.setProperty(HTTPConstants.AUTHENTICATE,auth);
		Z_FE_FM_AGREGAR_FAC_ORDER_VI invoice = new Z_FE_FM_AGREGAR_FAC_ORDER_VI();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date10 date = new Date10();
		Char4 bukrs = new Char4();
		Char1 flag = new Char1();
		Char2 i_idioma = new Char2();
		Char10 i_lifnr = new Char10();
		Char4 i_ekorg = new Char4();
		Curr152 i_subto = new Curr152();
		Char30 i_kidno = new Char30();
		Cuky5 i_waers = new Cuky5();
		Char1 i_tipo = new Char1();
		MultipartFile file1 = file[1]; //PDF
		MultipartFile file2 = file[0]; //XML
				
        // Create the file on server
        File serverFileXML = multipartToFile(file2);
        File serverFilePDF = multipartToFile(file1);
        
        
        LOGGER.info("Server PDF File Location=" + serverFilePDF.getAbsolutePath());
        LOGGER.info("Server XML File Location=" + serverFileXML.getAbsolutePath());
//        LOGGER.info("Server Other PDF File Location=" + serverFileOtherPDF != null ? serverFileOtherPDF.getAbsolutePath() : "No file received");
        FileDataSource fdsXML = new FileDataSource(serverFileXML);
        FileDataSource fdsPDF = new FileDataSource(serverFilePDF);
        
		DataHandler pdfHandler = new DataHandler(fdsPDF);
		DataHandler xmlHandler = new DataHandler(fdsXML);		
		
		ZTT_EBELN ztt_ebeln = new ZTT_EBELN();
		for(int i = 0; i < invoices.length(); i ++){
			JSONObject item = invoices.getJSONObject(i);
			ZST_EBELN zst_ebeln = new ZST_EBELN();
			Char10 ebeln = new Char10();
			Numeric5 ebelp = new Numeric5();
			Char10 mblnr = new Char10();
			Quantum133 menge = new Quantum133();
			Curr152 netwr = new Curr152();
			Cuky5 waers = new Cuky5();
			Char16 xblnr = new Char16();
			Numeric6 invoiceDocItem = new Numeric6();
			Char10 poNumber = new Char10();
			Numeric5 poItem = new Numeric5();
			Char10 refDoc = new Char10();
			Numeric4 refDocYear = new Numeric4();
			Numeric4 refDocIt = new Numeric4();
			Decimal234 itemAmount = new Decimal234();
			Unit3 poUnit = new Unit3();
			Quantum133 poPrQnt = new Quantum133();
			Quantum133 quantity = new Quantum133();
			Unit3 poPrUom = new Unit3();
			Char4 condType = new Char4();	
			Char10 sheetNo = new Char10();
			Numeric10 sheetItem = new Numeric10();
			
			ebeln.setChar10(!item.isNull("ebeln") ? item.getString("ebeln") : "");
			ebelp.setNumeric5(!item.isNull("ebelp") ? item.getString("ebelp") : "");
			mblnr.setChar10(!item.isNull("mblnr") ? item.getString("mblnr") : "");
			menge.setQuantum133(!item.isNull("zmenge") ? new BigDecimal(item.getString("zmenge")) : null);
			netwr.setCurr152(!item.isNull("netwr") ? new BigDecimal(item.getString("netwr")) : null);
			waers.setCuky5(!item.isNull("waers") ? item.getString("waers") : "");
			xblnr.setChar16(!item.isNull("xblnr") ? item.getString("xblnr") : "");
			invoiceDocItem.setNumeric6(!item.isNull("invoiceDocItem") ? item.getString("invoiceDocItem") : "");
			poNumber.setChar10(!item.isNull("poNumber") ? item.getString("poNumber") : "");
			poItem.setNumeric5(!item.isNull("poItem") ? item.getString("poItem") : "");
			refDoc.setChar10(!item.isNull("refDoc") ? item.getString("refDoc") : "");
			refDocYear.setNumeric4(!item.isNull("refDocYear") ? item.getString("refDocYear") : "");
			refDocIt.setNumeric4(!item.isNull("refDocIt") ? item.getString("refDocIt") : "");
			itemAmount.setDecimal234(!item.isNull("itemAmount") ? new BigDecimal(item.getString("itemAmount"))  : null);
			quantity.setQuantum133(!item.isNull("quantity") ? new BigDecimal(item.getString("quantity")) : null);
			poUnit.setUnit3(!item.isNull("poUnit") ?  item.getString("poUnit") : "");
			poPrQnt.setQuantum133(!item.isNull("poPrQnt") ? new BigDecimal(item.getString("poPrQnt")) : null);
			poPrUom.setUnit3(!item.isNull("poPrUom") ? item.getString("poPrUom") : "");
			condType.setChar4(!item.isNull("condType") ? item.getString("condType") : "");
			sheetNo.setChar10(!item.isNull("sheetNo") ? item.getString("sheetNo") : "");
			sheetItem.setNumeric10(!item.isNull("sheetItem") ? item.getString("sheetItem") : "");
			
			zst_ebeln.setEBELN(ebeln);
			zst_ebeln.setEBELP(ebelp);
			zst_ebeln.setMBLNR(mblnr);
			zst_ebeln.setMENGE(menge);
			zst_ebeln.setNETWR(netwr);
			zst_ebeln.setWAERS(waers);
			zst_ebeln.setXBLNR(xblnr);
			zst_ebeln.setINVOICE_DOC_ITEM(invoiceDocItem);
			zst_ebeln.setPO_NUMBER(poNumber);
			zst_ebeln.setPO_ITEM(poItem);
			zst_ebeln.setREF_DOC(refDoc);
			zst_ebeln.setREF_DOC_YEAR(refDocYear);
			zst_ebeln.setREF_DOC_IT(refDocIt);
			zst_ebeln.setITEM_AMOUNT(itemAmount);
			zst_ebeln.setQUANTITY(quantity);
			zst_ebeln.setPO_UNIT(poUnit);
			zst_ebeln.setPO_PR_QNT(poPrQnt);
			zst_ebeln.setPO_PR_UOM(poPrUom);
			zst_ebeln.setCOND_TYPE(condType);
			zst_ebeln.setSHEET_NO(sheetNo);
			zst_ebeln.setSHEET_ITEM(sheetItem);
			
			ztt_ebeln.addItem(zst_ebeln);
		}
		
		
		
		
					
		i_lifnr.setChar10(lifnr);
		if(moneda != null && moneda.length() > 0)
			i_waers.setCuky5(moneda);
		i_idioma.setChar2(language.toUpperCase());
		if(hdnDivision != null && hdnDivision.length() > 0)
			i_ekorg.setChar4(hdnDivision);
		flag.setChar1("1");
		date.setDate10(sdf.format(new java.util.Date()));
		bukrs.setChar4(cmbRazonSocial);
		if(receptionType != null && receptionType.length() > 0)
			i_tipo.setChar1(receptionType);
		i_subto.setCurr152(new BigDecimal(subto));
		if(referencia != null && referencia.length() > 0)
			i_kidno.setChar30(referencia);
		
		invoice.setI_BUKRS(bukrs);
		invoice.setI_FLAG(flag);
		invoice.setI_LIFNR(i_lifnr);
		invoice.setI_PDF(pdfHandler);
		if(moneda != null && moneda.length() > 0)
			invoice.setI_WAERS(i_waers);		
		invoice.setI_XML(xmlHandler);	
		if(hdnDivision != null && hdnDivision.length() > 0)
			invoice.setI_EKORG(i_ekorg);
		invoice.setI_PEDIDO(ztt_ebeln);
		invoice.setI_SUBTO(i_subto);
		if(receptionType != null && receptionType.length() > 0)
			invoice.setI_TIPO(i_tipo);
		invoice.setI_IDIOMA(i_idioma);
		if(referencia != null && referencia.length() > 0)
			invoice.setI_KIDNO(i_kidno);
		
		Z_FE_FM_AGREGAR_FAC_ORDER_VIResponse response = null;
		try {
			stub._getServiceClient().getOptions().setTimeOutInMilliSeconds(timeOutInMilliSeconds);
			LOGGER.info("Base64 output: " + Base64.encode(xmlHandler));
			response = stub.z_FE_FM_AGREGAR_FAC_ORDER_VI(invoice);			
		} catch (RemoteException e) {
			LOGGER.error("Error with addCFDIVI:", e);
			return null;
		} catch(Exception e) {
			LOGGER.error("Error with addCFDIVI:", e);
			return null;
		} finally {
			serverFilePDF.delete();
			serverFileXML.delete();			
		}
		
		return response;
	}
	
	public Z_FE_FM_AGREGAR_FAC_ORDER_VIResponse addCFDIVI(String cmbRazonSocial,MultipartFile[] file,String lifnr, JSONArray invoices, 
														  String referencia, String language, String hdnDivision, String subto, 
														  String receptionType, String moneda, String flag) throws IOException, JSONException {
		int timeOutInMilliSeconds = 3 * 60 * 1000; // Three minutes					
		
		ZWS_AGREGAR_FAC_ORDER_VIStub stub = new ZWS_AGREGAR_FAC_ORDER_VIStub();
		
		Options options = stub._getServiceClient().getOptions();
		HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
		auth.setPreemptiveAuthentication(true);
		auth.setUsername(Constants.WISE_WSDL_USER);
		auth.setPassword(Constants.WISE_WSDL_PASS);
		options.setProperty(HTTPConstants.AUTHENTICATE,auth);
		Z_FE_FM_AGREGAR_FAC_ORDER_VI invoice = new Z_FE_FM_AGREGAR_FAC_ORDER_VI();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date10 date = new Date10();
		Char4 bukrs = new Char4();
		Char1 i_flag = new Char1();
		Char2 i_idioma = new Char2();
		Char10 i_lifnr = new Char10();
		Char4 i_ekorg = new Char4();
		Curr152 i_subto = new Curr152();
		Char30 i_kidno = new Char30();
		Cuky5 i_waers = new Cuky5();
		Char1 i_tipo = new Char1();
		MultipartFile file1 = file[1]; //PDF
		MultipartFile file2 = file[0]; //XML
				
        // Create the file on server
        File serverFileXML = multipartToFile(file2);
        File serverFilePDF = multipartToFile(file1);
        
        
        LOGGER.info("Server PDF File Location=" + serverFilePDF.getAbsolutePath());
        LOGGER.info("Server XML File Location=" + serverFileXML.getAbsolutePath());
//        LOGGER.info("Server Other PDF File Location=" + serverFileOtherPDF != null ? serverFileOtherPDF.getAbsolutePath() : "No file received");
        FileDataSource fdsXML = new FileDataSource(serverFileXML);
        FileDataSource fdsPDF = new FileDataSource(serverFilePDF);
        
		DataHandler pdfHandler = new DataHandler(fdsPDF);
		DataHandler xmlHandler = new DataHandler(fdsXML);		
		
		ZTT_EBELN ztt_ebeln = new ZTT_EBELN();
		for(int i = 0; i < invoices.length(); i ++){
			JSONObject item = invoices.getJSONObject(i);
			ZST_EBELN zst_ebeln = new ZST_EBELN();
			Char10 ebeln = new Char10();
			Numeric5 ebelp = new Numeric5();
			Char10 mblnr = new Char10();
			Quantum133 menge = new Quantum133();
			Curr152 netwr = new Curr152();
			Cuky5 waers = new Cuky5();
			Char16 xblnr = new Char16();
			Numeric6 invoiceDocItem = new Numeric6();
			Char10 poNumber = new Char10();
			Numeric5 poItem = new Numeric5();
			Char10 refDoc = new Char10();
			Numeric4 refDocYear = new Numeric4();
			Numeric4 refDocIt = new Numeric4();
			Decimal234 itemAmount = new Decimal234();
			Unit3 poUnit = new Unit3();
			Quantum133 poPrQnt = new Quantum133();
			Quantum133 quantity = new Quantum133();
			Unit3 poPrUom = new Unit3();
			Char4 condType = new Char4();	
			Char10 sheetNo = new Char10();
			Numeric10 sheetItem = new Numeric10();
			
			ebeln.setChar10(!item.isNull("ebeln") ? item.getString("ebeln") : "");
			ebelp.setNumeric5(!item.isNull("ebelp") ? item.getString("ebelp") : "");
			mblnr.setChar10(!item.isNull("mblnr") ? item.getString("mblnr") : "");
			menge.setQuantum133(!item.isNull("zmenge") ? new BigDecimal(item.getString("zmenge")) : null);
			netwr.setCurr152(!item.isNull("netwr") ? new BigDecimal(item.getString("netwr")) : null);
			waers.setCuky5(!item.isNull("waers") ? item.getString("waers") : "");
			xblnr.setChar16(!item.isNull("xblnr") ? item.getString("xblnr") : "");
			invoiceDocItem.setNumeric6(!item.isNull("invoiceDocItem") ? item.getString("invoiceDocItem") : "");
			poNumber.setChar10(!item.isNull("poNumber") ? item.getString("poNumber") : "");
			poItem.setNumeric5(!item.isNull("poItem") ? item.getString("poItem") : "");
			refDoc.setChar10(!item.isNull("refDoc") ? item.getString("refDoc") : "");
			refDocYear.setNumeric4(!item.isNull("refDocYear") ? item.getString("refDocYear") : "");
			refDocIt.setNumeric4(!item.isNull("refDocIt") ? item.getString("refDocIt") : "");
			itemAmount.setDecimal234(!item.isNull("itemAmount") ? new BigDecimal(item.getString("itemAmount"))  : null);
			quantity.setQuantum133(!item.isNull("quantity") ? new BigDecimal(item.getString("quantity")) : null);
			poUnit.setUnit3(!item.isNull("poUnit") ?  item.getString("poUnit") : "");
			poPrQnt.setQuantum133(!item.isNull("poPrQnt") ? new BigDecimal(item.getString("poPrQnt")) : null);
			poPrUom.setUnit3(!item.isNull("poPrUom") ? item.getString("poPrUom") : "");
			condType.setChar4(!item.isNull("condType") ? item.getString("condType") : "");
			sheetNo.setChar10(!item.isNull("sheetNo") ? item.getString("sheetNo") : "");
			sheetItem.setNumeric10(!item.isNull("sheetItem") ? item.getString("sheetItem") : "");
			
			zst_ebeln.setEBELN(ebeln);
			zst_ebeln.setEBELP(ebelp);
			zst_ebeln.setMBLNR(mblnr);
			zst_ebeln.setMENGE(menge);
			zst_ebeln.setNETWR(netwr);
			zst_ebeln.setWAERS(waers);
			zst_ebeln.setXBLNR(xblnr);
			zst_ebeln.setINVOICE_DOC_ITEM(invoiceDocItem);
			zst_ebeln.setPO_NUMBER(poNumber);
			zst_ebeln.setPO_ITEM(poItem);
			zst_ebeln.setREF_DOC(refDoc);
			zst_ebeln.setREF_DOC_YEAR(refDocYear);
			zst_ebeln.setREF_DOC_IT(refDocIt);
			zst_ebeln.setITEM_AMOUNT(itemAmount);
			zst_ebeln.setQUANTITY(quantity);
			zst_ebeln.setPO_UNIT(poUnit);
			zst_ebeln.setPO_PR_QNT(poPrQnt);
			zst_ebeln.setPO_PR_UOM(poPrUom);
			zst_ebeln.setCOND_TYPE(condType);
			zst_ebeln.setSHEET_NO(sheetNo);
			zst_ebeln.setSHEET_ITEM(sheetItem);
			
			ztt_ebeln.addItem(zst_ebeln);
		}
		
		
		
		
					
		i_lifnr.setChar10(lifnr);
		if(moneda != null && moneda.length() > 0)
			i_waers.setCuky5(moneda);
		i_idioma.setChar2(language.toUpperCase());
		if(hdnDivision != null && hdnDivision.length() > 0)
			i_ekorg.setChar4(hdnDivision);
		i_flag.setChar1(flag);
		date.setDate10(sdf.format(new java.util.Date()));
		bukrs.setChar4(cmbRazonSocial);
		if(receptionType != null && receptionType.length() > 0)
			i_tipo.setChar1(receptionType);
		i_subto.setCurr152(new BigDecimal(subto));
		if(referencia != null && referencia.length() > 0)
			i_kidno.setChar30(referencia);
		
		invoice.setI_BUKRS(bukrs);
		invoice.setI_FLAG(i_flag);
		invoice.setI_LIFNR(i_lifnr);
		invoice.setI_PDF(pdfHandler);
		if(moneda != null && moneda.length() > 0)
			invoice.setI_WAERS(i_waers);		
		invoice.setI_XML(xmlHandler);	
		if(hdnDivision != null && hdnDivision.length() > 0)
			invoice.setI_EKORG(i_ekorg);
		invoice.setI_PEDIDO(ztt_ebeln);
		invoice.setI_SUBTO(i_subto);
		if(receptionType != null && receptionType.length() > 0)
			invoice.setI_TIPO(i_tipo);
		invoice.setI_IDIOMA(i_idioma);
		if(referencia != null && referencia.length() > 0)
			invoice.setI_KIDNO(i_kidno);
		
		Z_FE_FM_AGREGAR_FAC_ORDER_VIResponse response = null;
		try {
			stub._getServiceClient().getOptions().setTimeOutInMilliSeconds(timeOutInMilliSeconds);
			LOGGER.info("Base64 output: " + Base64.encode(xmlHandler));
			response = stub.z_FE_FM_AGREGAR_FAC_ORDER_VI(invoice);			
		} catch (RemoteException e) {
			LOGGER.error("Error with addCFDIVI:", e);
			return null;
		} catch(Exception e) {
			LOGGER.error("Error with addCFDIVI:", e);
			return null;
		} finally {
			serverFilePDF.delete();
			serverFileXML.delete();			
		}
		
		return response;
	}
	
	public Z_FE_FM_AGREGAR_FAC_ORDERResponse addCBB(String cmbRazonSocial,MultipartFile[] files,String lifnr, String invoices, String invoiceDate, String tax, String reten, String moneda, String referencia, String subtotal, String invoiceData, String language, String hdnDivision) throws IOException {
		String[] invoiceVals = invoices.split(",");
		int timeOutInMilliSeconds = 3 * 60 * 1000; // Three minutes
		
		ZWS_AGREGAR_FAC_ORDERStub stub = new ZWS_AGREGAR_FAC_ORDERStub();
		Options options = stub._getServiceClient().getOptions();
		HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
		auth.setPreemptiveAuthentication(true);
		auth.setUsername(Constants.WISE_WSDL_USER);
		auth.setPassword(Constants.WISE_WSDL_PASS);
		options.setProperty(HTTPConstants.AUTHENTICATE,auth);
		Z_FE_FM_AGREGAR_FAC_ORDER invoice = new Z_FE_FM_AGREGAR_FAC_ORDER();
		Char4 bukrs = new Char4();
		Char4 i_ekorg = new Char4();
		ZTT_REFER facs = new ZTT_REFER();
		for(String invoiceVal : invoiceVals) {
			ZST_REFER fac = new ZST_REFER();
			Char16 facInfo = new Char16();
			facInfo.setChar16(invoiceVal);
			fac.setXBLNR(facInfo);
			facs.addItem(fac);
		}
		Date date = new Date();
		Curr132 taxVal = new Curr132();
		Curr132 retenVal = new Curr132();
		Char10 i_lifnr = new Char10();
		Char1 flag = new Char1();
		Char16 ixblnr = new Char16();
		Cuky5 currency = new Cuky5();
		BigDecimal taxDecimal = new BigDecimal(tax);
		Char30 i_kidno = new Char30();
        // Create the file on server
		MultipartFile pdf = files[0]; //PDF
		MultipartFile opdf = files[1]; //XML
        File serverPDFFile = multipartToFile(pdf);
        File serverOPDFFile = opdf.isEmpty() ? null : multipartToFile(opdf);
        LOGGER.info("Server PDF File Location=" + serverPDFFile.getAbsolutePath());
//        LOGGER.info("Server Other PDF File Location=" + serverOPDFFile.getAbsolutePath());
        FileDataSource fds = new FileDataSource(serverPDFFile);
        FileDataSource ofds = new FileDataSource(serverOPDFFile);
		DataHandler dhpdf = new DataHandler(fds);
		DataHandler dhopdf = new DataHandler(ofds);
		Curr152 subVal = new Curr152();
		Char2 i_idioma = new Char2();
		bukrs.setChar4(cmbRazonSocial);
		
		date.setDate(invoiceDate);
		taxVal.setCurr132(taxDecimal);
		flag.setChar1("2");
		if(referencia != null && referencia.length() > 0)
			i_kidno.setChar30(referencia);
				
		ixblnr.setChar16(invoiceData);
		i_lifnr.setChar10(lifnr);
		currency.setCuky5(moneda);
		subVal.setCurr152(new BigDecimal(subtotal));
		i_idioma.setChar2(language.toUpperCase());
		if(hdnDivision != null && hdnDivision.length() > 0)
			i_ekorg.setChar4(hdnDivision);
		
		invoice.setI_BUKRS(bukrs);
		invoice.setI_PDF(dhpdf);
		if(serverOPDFFile != null)
			invoice.setI_OPDF(dhopdf);
		invoice.setI_REFER(facs);
		invoice.setI_DATE(date);
		invoice.setI_TAX(taxVal);
		invoice.setI_LIFNR(i_lifnr);
		invoice.setI_FLAG(flag);
		invoice.setI_XBLNR(ixblnr);
		invoice.setI_WAERS(currency);
		invoice.setI_SUBTOTAL(subVal);
		invoice.setI_IDIOMA(i_idioma);
		if(referencia != null && referencia.length() > 0)
			invoice.setI_KIDNO(i_kidno);
		if(hdnDivision != null && hdnDivision.length() > 0)
			invoice.setI_EKORG(i_ekorg);
		
		
		Z_FE_FM_AGREGAR_FAC_ORDERResponse response = null;
		try {
			stub._getServiceClient().getOptions().setTimeOutInMilliSeconds(timeOutInMilliSeconds);
			response = stub.z_FE_FM_AGREGAR_FAC_ORDER(invoice);
		} catch (Exception e) {
			LOGGER.error("Error with addCBB:", e);
			return null;
		} finally {
			serverPDFFile.delete();
			if(serverOPDFFile != null)
				serverOPDFFile.delete();
		}
		return response;
	}
	
	public Z_FE_FM_AGREGAR_FAC_ORDER_VIResponse addCBBVI(String cmbRazonSocial,MultipartFile[] files,String lifnr, JSONArray invoices, String invoiceDate, String tax, String reten, String moneda, String referencia, String subtotal, String invoiceData, String language, String hdnDivision, String subto, String receptionType, String currencys) throws IOException, JSONException {
		int timeOutInMilliSeconds = 3 * 60 * 1000; // Three minutes
		
		ZWS_AGREGAR_FAC_ORDER_VIStub stub = new ZWS_AGREGAR_FAC_ORDER_VIStub();
		Options options = stub._getServiceClient().getOptions();
		HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
		auth.setPreemptiveAuthentication(true);
		auth.setUsername(Constants.WISE_WSDL_USER);
		auth.setPassword(Constants.WISE_WSDL_PASS);
		options.setProperty(HTTPConstants.AUTHENTICATE,auth);
		Z_FE_FM_AGREGAR_FAC_ORDER_VI invoice = new Z_FE_FM_AGREGAR_FAC_ORDER_VI();
		Char4 bukrs = new Char4();
		Char4 i_ekorg = new Char4();
		ZTT_EBELN ztt_ebeln = new ZTT_EBELN();
		for(int i=0; i < invoices.length(); i++) {
			JSONObject item = invoices.getJSONObject(i);
			ZST_EBELN zst_ebeln = new ZST_EBELN();
			Char10 ebeln = new Char10();
			Numeric5 ebelp = new Numeric5();
			Char10 mblnr = new Char10();
			Quantum133 menge = new Quantum133();
			Curr152 netwr = new Curr152();
			Cuky5 waers = new Cuky5();
			Char16 xblnr = new Char16();
			Numeric6 invoiceDocItem = new Numeric6();
			Char10 poNumber = new Char10();
			Numeric5 poItem = new Numeric5();
			Char10 refDoc = new Char10();
			Numeric4 refDocYear = new Numeric4();
			Numeric4 refDocIt = new Numeric4();
			Decimal234 itemAmount = new Decimal234();
			Unit3 poUnit = new Unit3();
			Quantum133 poPrQnt = new Quantum133();
			Quantum133 quantity = new Quantum133();
			Unit3 poPrUom = new Unit3();
			Char4 condType = new Char4();	
			Char10 sheetNo = new Char10();
			Numeric10 sheetItem = new Numeric10();
			
			ebeln.setChar10(!item.isNull("ebeln") ? item.getString("ebeln") : "");
			ebelp.setNumeric5(!item.isNull("ebelp") ? item.getString("ebelp") : "");
			mblnr.setChar10(!item.isNull("mblnr") ? item.getString("mblnr") : "");
			menge.setQuantum133(!item.isNull("zmenge") ? new BigDecimal(item.getString("zmenge")) : null);
			netwr.setCurr152(!item.isNull("netwr") ? new BigDecimal(item.getString("netwr")) : null);
			waers.setCuky5(!item.isNull("waers") ? item.getString("waers") : "");
			xblnr.setChar16(!item.isNull("xblnr") ? item.getString("xblnr") : "");
			invoiceDocItem.setNumeric6(!item.isNull("invoiceDocItem") ? item.getString("invoiceDocItem") : "");
			poNumber.setChar10(!item.isNull("poNumber") ? item.getString("poNumber") : "");
			poItem.setNumeric5(!item.isNull("poItem") ? item.getString("poItem") : "");
			refDoc.setChar10(!item.isNull("refDoc") ? item.getString("refDoc") : "");
			refDocYear.setNumeric4(!item.isNull("refDocYear") ? item.getString("refDocYear") : "");
			refDocIt.setNumeric4(!item.isNull("refDocIt") ? item.getString("refDocIt") : "");
			itemAmount.setDecimal234(!item.isNull("itemAmount") ? new BigDecimal(item.getString("itemAmount"))  : null);
			quantity.setQuantum133(!item.isNull("quantity") ? new BigDecimal(item.getString("quantity")) : null);
			poUnit.setUnit3(!item.isNull("poUnit") ?  item.getString("poUnit") : "");
			poPrQnt.setQuantum133(!item.isNull("poPrQnt") ? new BigDecimal(item.getString("poPrQnt")) : null);
			poPrUom.setUnit3(!item.isNull("poPrUom") ? item.getString("poPrUom") : "");
			condType.setChar4(!item.isNull("condType") ? item.getString("condType") : "");
			sheetNo.setChar10(!item.isNull("sheetNo") ? item.getString("sheetNo") : "");
			sheetItem.setNumeric10(!item.isNull("sheetItem") ? item.getString("sheetItem") : "");
			
			zst_ebeln.setEBELN(ebeln);
			zst_ebeln.setEBELP(ebelp);
			zst_ebeln.setMBLNR(mblnr);
			zst_ebeln.setMENGE(menge);
			zst_ebeln.setNETWR(netwr);
			zst_ebeln.setWAERS(waers);
			zst_ebeln.setXBLNR(xblnr);
			zst_ebeln.setINVOICE_DOC_ITEM(invoiceDocItem);
			zst_ebeln.setPO_NUMBER(poNumber);
			zst_ebeln.setPO_ITEM(poItem);
			zst_ebeln.setREF_DOC(refDoc);
			zst_ebeln.setREF_DOC_YEAR(refDocYear);
			zst_ebeln.setREF_DOC_IT(refDocIt);
			zst_ebeln.setITEM_AMOUNT(itemAmount);
			zst_ebeln.setQUANTITY(quantity);
			zst_ebeln.setPO_UNIT(poUnit);
			zst_ebeln.setPO_PR_QNT(poPrQnt);
			zst_ebeln.setPO_PR_UOM(poPrUom);
			zst_ebeln.setCOND_TYPE(condType);
			zst_ebeln.setSHEET_NO(sheetNo);
			zst_ebeln.setSHEET_ITEM(sheetItem);
			
			ztt_ebeln.addItem(zst_ebeln);
		}		
		
		Date date = new Date();
		Curr152 taxVal = new Curr152();		
		Char10 i_lifnr = new Char10();
		Char1 flag = new Char1();
		Char16 ixblnr = new Char16();
		Cuky5 currency = new Cuky5();
		Char1 i_tipo = new Char1();
		BigDecimal taxDecimal = new BigDecimal(tax);
		Char30 i_kidno = new Char30();
        // Create the file on server
		MultipartFile pdf = files[0]; //PDF		
        File serverPDFFile = multipartToFile(pdf);
        
        LOGGER.info("Server PDF File Location=" + serverPDFFile.getAbsolutePath());
        
        FileDataSource fds = new FileDataSource(serverPDFFile);        
		DataHandler dhpdf = new DataHandler(fds);
		
		Curr152 i_subto = new Curr152();
		Char2 i_idioma = new Char2();
		Cuky5 i_waers = new Cuky5();
		bukrs.setChar4(cmbRazonSocial);
		
		date.setDate(invoiceDate);
		taxVal.setCurr152(taxDecimal);
		flag.setChar1("2");
		if(referencia != null && referencia.length() > 0)
			i_kidno.setChar30(referencia);
				
		ixblnr.setChar16(invoiceData);
		i_lifnr.setChar10(lifnr);
		currency.setCuky5(moneda);
		i_subto.setCurr152(new BigDecimal(subto));
		i_idioma.setChar2(language.toUpperCase());
		if(receptionType != null && receptionType.length() > 0)
			i_tipo.setChar1(receptionType);
		if(hdnDivision != null && hdnDivision.length() > 0)
			i_ekorg.setChar4(hdnDivision);
		if(currencys != null && currencys.length() > 0)
			i_waers.setCuky5(currencys);
		
		invoice.setI_BUKRS(bukrs);
		invoice.setI_PDF(dhpdf);				
		invoice.setI_DATE(date);		
		invoice.setI_LIFNR(i_lifnr);
		invoice.setI_FLAG(flag);
		invoice.setI_XBLNR(ixblnr);				
		invoice.setI_IDIOMA(i_idioma);
		if(hdnDivision != null && hdnDivision.length() > 0)
			invoice.setI_EKORG(i_ekorg);
		invoice.setI_PEDIDO(ztt_ebeln);
		invoice.setI_TAX(taxVal);
		if(receptionType != null && receptionType.length() > 0)
			invoice.setI_TIPO(i_tipo);
		invoice.setI_SUBTO(i_subto);
		if(currencys != null && currencys.length() > 0)
			invoice.setI_WAERS(i_waers);
		if(referencia != null && referencia.length() > 0)
			invoice.setI_KIDNO(i_kidno);
		
		Z_FE_FM_AGREGAR_FAC_ORDER_VIResponse response = null;
		try {
			stub._getServiceClient().getOptions().setTimeOutInMilliSeconds(timeOutInMilliSeconds);
			response = stub.z_FE_FM_AGREGAR_FAC_ORDER_VI(invoice);
		} catch (Exception e) {
			LOGGER.error("Error with addCBB:", e);
			return null;
		} finally {
			serverPDFFile.delete();			
		}
		return response;
	}
	
	public Z_FE_FM_AGREGAR_FAC_ORDER_VIResponse addCBBVI(String cmbRazonSocial,MultipartFile[] files,String lifnr, JSONArray invoices, String invoiceDate, String tax, String reten, String moneda, String referencia, String subtotal, String invoiceData, String language, String hdnDivision, String subto, String receptionType, String currencys, String flagg) throws IOException, JSONException {
		int timeOutInMilliSeconds = 3 * 60 * 1000; // Three minutes
		
		ZWS_AGREGAR_FAC_ORDER_VIStub stub = new ZWS_AGREGAR_FAC_ORDER_VIStub();
		Options options = stub._getServiceClient().getOptions();
		HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
		auth.setPreemptiveAuthentication(true);
		auth.setUsername(Constants.WISE_WSDL_USER);
		auth.setPassword(Constants.WISE_WSDL_PASS);
		options.setProperty(HTTPConstants.AUTHENTICATE,auth);
		Z_FE_FM_AGREGAR_FAC_ORDER_VI invoice = new Z_FE_FM_AGREGAR_FAC_ORDER_VI();
		Char4 bukrs = new Char4();
		Char4 i_ekorg = new Char4();
		ZTT_EBELN ztt_ebeln = new ZTT_EBELN();
		for(int i=0; i < invoices.length(); i++) {
			JSONObject item = invoices.getJSONObject(i);
			ZST_EBELN zst_ebeln = new ZST_EBELN();
			Char10 ebeln = new Char10();
			Numeric5 ebelp = new Numeric5();
			Char10 mblnr = new Char10();
			Quantum133 menge = new Quantum133();
			Curr152 netwr = new Curr152();
			Cuky5 waers = new Cuky5();
			Char16 xblnr = new Char16();
			Numeric6 invoiceDocItem = new Numeric6();
			Char10 poNumber = new Char10();
			Numeric5 poItem = new Numeric5();
			Char10 refDoc = new Char10();
			Numeric4 refDocYear = new Numeric4();
			Numeric4 refDocIt = new Numeric4();
			Decimal234 itemAmount = new Decimal234();
			Unit3 poUnit = new Unit3();
			Quantum133 poPrQnt = new Quantum133();
			Quantum133 quantity = new Quantum133();
			Unit3 poPrUom = new Unit3();
			Char4 condType = new Char4();	
			Char10 sheetNo = new Char10();
			Numeric10 sheetItem = new Numeric10();
			
			ebeln.setChar10(!item.isNull("ebeln") ? item.getString("ebeln") : "");
			ebelp.setNumeric5(!item.isNull("ebelp") ? item.getString("ebelp") : "");
			mblnr.setChar10(!item.isNull("mblnr") ? item.getString("mblnr") : "");
			menge.setQuantum133(!item.isNull("zmenge") ? new BigDecimal(item.getString("zmenge")) : null);
			netwr.setCurr152(!item.isNull("netwr") ? new BigDecimal(item.getString("netwr")) : null);
			waers.setCuky5(!item.isNull("waers") ? item.getString("waers") : "");
			xblnr.setChar16(!item.isNull("xblnr") ? item.getString("xblnr") : "");
			invoiceDocItem.setNumeric6(!item.isNull("invoiceDocItem") ? item.getString("invoiceDocItem") : "");
			poNumber.setChar10(!item.isNull("poNumber") ? item.getString("poNumber") : "");
			poItem.setNumeric5(!item.isNull("poItem") ? item.getString("poItem") : "");
			refDoc.setChar10(!item.isNull("refDoc") ? item.getString("refDoc") : "");
			refDocYear.setNumeric4(!item.isNull("refDocYear") ? item.getString("refDocYear") : "");
			refDocIt.setNumeric4(!item.isNull("refDocIt") ? item.getString("refDocIt") : "");
			itemAmount.setDecimal234(!item.isNull("itemAmount") ? new BigDecimal(item.getString("itemAmount"))  : null);
			quantity.setQuantum133(!item.isNull("quantity") ? new BigDecimal(item.getString("quantity")) : null);
			poUnit.setUnit3(!item.isNull("poUnit") ?  item.getString("poUnit") : "");
			poPrQnt.setQuantum133(!item.isNull("poPrQnt") ? new BigDecimal(item.getString("poPrQnt")) : null);
			poPrUom.setUnit3(!item.isNull("poPrUom") ? item.getString("poPrUom") : "");
			condType.setChar4(!item.isNull("condType") ? item.getString("condType") : "");
			sheetNo.setChar10(!item.isNull("sheetNo") ? item.getString("sheetNo") : "");
			sheetItem.setNumeric10(!item.isNull("sheetItem") ? item.getString("sheetItem") : "");
			
			zst_ebeln.setEBELN(ebeln);
			zst_ebeln.setEBELP(ebelp);
			zst_ebeln.setMBLNR(mblnr);
			zst_ebeln.setMENGE(menge);
			zst_ebeln.setNETWR(netwr);
			zst_ebeln.setWAERS(waers);
			zst_ebeln.setXBLNR(xblnr);
			zst_ebeln.setINVOICE_DOC_ITEM(invoiceDocItem);
			zst_ebeln.setPO_NUMBER(poNumber);
			zst_ebeln.setPO_ITEM(poItem);
			zst_ebeln.setREF_DOC(refDoc);
			zst_ebeln.setREF_DOC_YEAR(refDocYear);
			zst_ebeln.setREF_DOC_IT(refDocIt);
			zst_ebeln.setITEM_AMOUNT(itemAmount);
			zst_ebeln.setQUANTITY(quantity);
			zst_ebeln.setPO_UNIT(poUnit);
			zst_ebeln.setPO_PR_QNT(poPrQnt);
			zst_ebeln.setPO_PR_UOM(poPrUom);
			zst_ebeln.setCOND_TYPE(condType);
			zst_ebeln.setSHEET_NO(sheetNo);
			zst_ebeln.setSHEET_ITEM(sheetItem);
			
			ztt_ebeln.addItem(zst_ebeln);
		}		
		
		Date date = new Date();
		Curr152 taxVal = new Curr152();		
		Char10 i_lifnr = new Char10();
		Char1 flag = new Char1();
		Char16 ixblnr = new Char16();
		Cuky5 currency = new Cuky5();
		Char1 i_tipo = new Char1();
		BigDecimal taxDecimal = new BigDecimal(tax);
		Char30 i_kidno = new Char30();
        // Create the file on server
		MultipartFile pdf = files[0]; //PDF		
        File serverPDFFile = multipartToFile(pdf);
        
        LOGGER.info("Server PDF File Location=" + serverPDFFile.getAbsolutePath());
        
        FileDataSource fds = new FileDataSource(serverPDFFile);        
		DataHandler dhpdf = new DataHandler(fds);
		
		Curr152 i_subto = new Curr152();
		Char2 i_idioma = new Char2();
		Cuky5 i_waers = new Cuky5();
		bukrs.setChar4(cmbRazonSocial);
		
		date.setDate(invoiceDate);
		taxVal.setCurr152(taxDecimal);
		flag.setChar1(flagg);
		if(referencia != null && referencia.length() > 0)
			i_kidno.setChar30(referencia);
				
		ixblnr.setChar16(invoiceData);
		i_lifnr.setChar10(lifnr);
		currency.setCuky5(moneda);
		i_subto.setCurr152(new BigDecimal(subto));
		i_idioma.setChar2(language.toUpperCase());
		if(receptionType != null && receptionType.length() > 0)
			i_tipo.setChar1(receptionType);
		if(hdnDivision != null && hdnDivision.length() > 0)
			i_ekorg.setChar4(hdnDivision);
		if(currencys != null && currencys.length() > 0)
			i_waers.setCuky5(currencys);
		
		invoice.setI_BUKRS(bukrs);
		invoice.setI_PDF(dhpdf);				
		invoice.setI_DATE(date);		
		invoice.setI_LIFNR(i_lifnr);
		invoice.setI_FLAG(flag);
		invoice.setI_XBLNR(ixblnr);				
		invoice.setI_IDIOMA(i_idioma);
		if(hdnDivision != null && hdnDivision.length() > 0)
			invoice.setI_EKORG(i_ekorg);
		invoice.setI_PEDIDO(ztt_ebeln);
		invoice.setI_TAX(taxVal);
		if(receptionType != null && receptionType.length() > 0)
			invoice.setI_TIPO(i_tipo);
		invoice.setI_SUBTO(i_subto);
		if(currencys != null && currencys.length() > 0)
			invoice.setI_WAERS(i_waers);
		if(referencia != null && referencia.length() > 0)
			invoice.setI_KIDNO(i_kidno);
		
		Z_FE_FM_AGREGAR_FAC_ORDER_VIResponse response = null;
		try {
			stub._getServiceClient().getOptions().setTimeOutInMilliSeconds(timeOutInMilliSeconds);
			response = stub.z_FE_FM_AGREGAR_FAC_ORDER_VI(invoice);
		} catch (Exception e) {
			LOGGER.error("Error with addCBB:", e);
			return null;
		} finally {
			serverPDFFile.delete();			
		}
		return response;
	}
	
	public Z_FE_FM_AGREGAR_FAC_SIN_ORDERResponse addCFDISOC(String cmbRazonSocial, String area, String responsable, String factura, String concepto, MultipartFile[] file, String lifnr, String language, String hdnDivision, String referencia) throws IOException {
		int timeOutInMilliSeconds = 3 * 60 * 1000; // Three minutes
		ZWS_AGREGAR_FAC_SIN_ORDERStub stub = new ZWS_AGREGAR_FAC_SIN_ORDERStub();
		Options options = stub._getServiceClient().getOptions();
		HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
		auth.setPreemptiveAuthentication(true);
		auth.setUsername(Constants.WISE_WSDL_USER);
		auth.setPassword(Constants.WISE_WSDL_PASS);
		options.setProperty(HTTPConstants.AUTHENTICATE,auth);
				
		Z_FE_FM_AGREGAR_FAC_SIN_ORDER invoice = new Z_FE_FM_AGREGAR_FAC_SIN_ORDER();
		
		Char4 bukrs = new Char4();
		Char4 i_ekorg = new Char4();
		bukrs.setChar4(cmbRazonSocial);
		if(hdnDivision != null && hdnDivision.length() > 0)
			i_ekorg.setChar4(hdnDivision);
		Char10 i_area = new Char10();
		i_area.setChar10(area);
		Char10 i_responsable = new Char10();
		i_responsable.setChar10(responsable);
		Char16 fac = new Char16();
		fac.setChar16(factura);
		Char50 concept = new Char50();
		Char30 i_kidno = new Char30();
		concept.setChar50(concepto);
		MultipartFile file1 = file[0]; //xml
		MultipartFile file2 = file[1];
		MultipartFile file3 = file[2];
		File serverFileXML = multipartToFile(file1);
		File serverFilePDF = multipartToFile(file2);
		File serverFileOtherPDF = file3.isEmpty() ? null : multipartToFile(file3);
        LOGGER.info("Server XML File Location=" + serverFileXML.getAbsolutePath());
        LOGGER.info("Server PDF File Location=" + serverFilePDF.getAbsolutePath());
//        LOGGER.info("Server Other PDF File Location=" + serverFilePDF.getAbsolutePath());
        FileDataSource fdsXML = new FileDataSource(serverFileXML);
        FileDataSource fdsPDF = new FileDataSource(serverFilePDF);
        FileDataSource fdsOPDF = new FileDataSource(serverFileOtherPDF);
		DataHandler xml = new DataHandler(fdsXML);
		DataHandler pdf = new DataHandler(fdsPDF);
		DataHandler opdf = new DataHandler(fdsOPDF);
		Char10 i_lifnr = new Char10();
		i_lifnr.setChar10(lifnr);
		Char1 flag = new Char1();
		Char2 i_idioma = new Char2();
		flag.setChar1("1");
		i_idioma.setChar2(language.toUpperCase());
		if(referencia != null && referencia.length() > 0)
			i_kidno.setChar30(referencia);
		
		invoice.setI_BUKRS(bukrs);
		invoice.setI_AREA(i_area);
		invoice.setI_RESPONSABLE(i_responsable);
		invoice.setI_FACTURA(fac);
		invoice.setI_CONCEP(concept);
		invoice.setI_XML(xml);
		invoice.setI_PDF(pdf);
		if(serverFileOtherPDF != null)
			invoice.setI_OPDF(opdf);
		invoice.setI_LIFNR(i_lifnr);
		invoice.setI_FLAG(flag);
		invoice.setI_IDIOMA(i_idioma);
		if(referencia != null && referencia.length() > 0)
			invoice.setI_KIDNO(i_kidno);
		if(hdnDivision != null && hdnDivision.length() > 0)
			invoice.setI_EKORG(i_ekorg);
		
		
		Z_FE_FM_AGREGAR_FAC_SIN_ORDERResponse response = null;
		try {
			stub._getServiceClient().getOptions().setTimeOutInMilliSeconds(timeOutInMilliSeconds);
			response = stub.z_FE_FM_AGREGAR_FAC_SIN_ORDER(invoice);
		} catch (Exception e) {
			return null;
		} finally {
			serverFilePDF.delete();
			serverFileXML.delete();
			if(serverFileOtherPDF != null)
				serverFileOtherPDF.delete();
		}
		
		return response;
	}
	
	public Z_FE_FM_AGREGAR_FAC_SIN_ORDERResponse addCBBSOC(String cmbRazonSocial, String area, String responsable, String factura, String concepto, MultipartFile[] files, String lifnr,String total, String subtotal, String fechaFactura, String tax, String reten, String moneda, String language, String hdnDivision, String referencia) throws IOException {
		int timeOutInMilliSeconds = 3 * 60 * 1000; // Three minutes
		ZWS_AGREGAR_FAC_SIN_ORDERStub stub = new ZWS_AGREGAR_FAC_SIN_ORDERStub();		
		
		Options options = stub._getServiceClient().getOptions();
		HttpTransportProperties.Authenticator auth = new HttpTransportProperties.Authenticator();
		auth.setPreemptiveAuthentication(true);
		auth.setUsername(Constants.WISE_WSDL_USER);
		auth.setPassword(Constants.WISE_WSDL_PASS);
		options.setProperty(HTTPConstants.AUTHENTICATE,auth);
		
		Z_FE_FM_AGREGAR_FAC_SIN_ORDER order = new Z_FE_FM_AGREGAR_FAC_SIN_ORDER();
		Char10 iarea = new Char10();
		Char4 ibukrs = new Char4();
		Char50 iconcep = new Char50();
		Date fecha = new Date();
		Char16 invoice = new Char16();
		Char1 flag = new Char1();
		Char10 ilifnr = new Char10();
		Char10 resp = new Char10();
		Curr152 ireten = new Curr152();
		Curr152 isubtotal = new Curr152();
		Curr152 itax = new Curr152();
		Curr152 itotal = new Curr152();
		Cuky5 mon = new Cuky5();
		Char2 i_idioma = new Char2();
		Char4 i_ekorg = new Char4();
		Char30 i_kidno = new Char30();
		
        // Create the file on server
        File serverPDFFile = multipartToFile(files[0]);
        File serverOPDFFile = files[1].isEmpty() ? null : multipartToFile(files[1]);
        LOGGER.info("Server PDF File Location=" + serverPDFFile.getAbsolutePath());
//        LOGGER.info("Server Other PDF File Location=" + serverOPDFFile.getAbsolutePath());
        FileDataSource fds = new FileDataSource(serverPDFFile);
        FileDataSource ofds = new FileDataSource(serverOPDFFile);
		DataHandler pdfDH = new DataHandler(fds);
		DataHandler opdfDH = new DataHandler(ofds);
		iarea.setChar10(area);
		ibukrs.setChar4(cmbRazonSocial);
		iconcep.setChar50(concepto);
		fecha.setDate(fechaFactura);
		invoice.setChar16(factura);
		flag.setChar1("2");
		ilifnr.setChar10(lifnr);
		resp.setChar10(responsable);
		ireten.setCurr152(new BigDecimal("0.0"));
		isubtotal.setCurr152(new BigDecimal(subtotal));
		itax.setCurr152(new BigDecimal(tax));
		itotal.setCurr152(new BigDecimal("0.0"));
		mon.setCuky5(moneda);
		i_idioma.setChar2(language.toUpperCase());
		if(referencia != null && referencia.length() > 0)
			i_kidno.setChar30(referencia);
		if(hdnDivision != null && hdnDivision.length() > 0)
			i_ekorg.setChar4(hdnDivision);
		
		
		order.setI_AREA(iarea);
		order.setI_BUKRS(ibukrs);
		order.setI_CONCEP(iconcep);
		order.setI_DATE(fecha);
		order.setI_FACTURA(invoice);
		order.setI_FLAG(flag);
		order.setI_LIFNR(ilifnr);
		order.setI_PDF(pdfDH);
		if(serverOPDFFile != null)
			order.setI_OPDF(opdfDH);
		order.setI_RESPONSABLE(resp);
		order.setI_RETEN(ireten);
		order.setI_SUBTOTAL(isubtotal);
		order.setI_TAX(itax);
		order.setI_TOTAL(itotal);
		order.setI_WAERS(mon);
		order.setI_IDIOMA(i_idioma);
		if(referencia != null && referencia.length() > 0)
			order.setI_KIDNO(i_kidno);
		if(hdnDivision != null && hdnDivision.length() > 0)
			order.setI_EKORG(i_ekorg);
		
		
		Z_FE_FM_AGREGAR_FAC_SIN_ORDERResponse response = null;
		
		try {
			stub._getServiceClient().getOptions().setTimeOutInMilliSeconds(timeOutInMilliSeconds);
			response = stub.z_FE_FM_AGREGAR_FAC_SIN_ORDER(order);
		} catch (Exception e) {
			LOGGER.error("ERROR CBBSOC",e);
			serverPDFFile.delete();
			serverOPDFFile.delete();
			return null;			
		}finally {
			serverPDFFile.delete();
			if(serverOPDFFile != null)
				serverOPDFFile.delete();
		}
		
		return response;
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
