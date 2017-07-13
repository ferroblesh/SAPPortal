package com.wise.service;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.wise.dao.InvoiceDao;

import functions.rfc.sap.document.sap_com.Z_FE_FM_AGREGAR_FAC_ORDERResponse;
import functions.rfc.sap.document.sap_com.Z_FE_FM_AGREGAR_FAC_ORDER_VI;
import functions.rfc.sap.document.sap_com.Z_FE_FM_AGREGAR_FAC_ORDER_VIResponse;
import functions.rfc.sap.document.sap_com.Z_FE_FM_AGREGAR_FAC_SIN_ORDERResponse;

@Service
public class InvoiceService {

//	private static final Logger LOGGER = Logger.getLogger(InvoiceService.class);
	
	@Autowired
	private InvoiceDao invoiceDao;
	
	@Transactional(readOnly = false)
	public Z_FE_FM_AGREGAR_FAC_ORDERResponse addCFDI(String cmbRazonSocial,MultipartFile[] file,String lifnr, String invoices, String referencia, String language, String hdnDivision) throws IOException, JSONException{
		return invoiceDao.addCFDI(cmbRazonSocial, file, lifnr, invoices, referencia, language, hdnDivision);
	}
	
	@Transactional(readOnly = false)
	public Z_FE_FM_AGREGAR_FAC_ORDER_VIResponse addCFDI(String cmbRazonSocial,MultipartFile[] file,String lifnr, JSONArray invoices, String referencia, String language, String hdnDivision, String subto, String receptionType, String moneda) throws IOException, JSONException {
		return invoiceDao.addCFDIVI(cmbRazonSocial, file, lifnr, invoices, referencia, language, hdnDivision, subto, receptionType, moneda);
	}
	
	@Transactional(readOnly = false)
	public Z_FE_FM_AGREGAR_FAC_ORDER_VIResponse addCFDI(String cmbRazonSocial,MultipartFile[] file,String lifnr, JSONArray invoices, String referencia, String language, String hdnDivision, String subto, String receptionType, String moneda, String flag) throws IOException, JSONException {
		return invoiceDao.addCFDIVI(cmbRazonSocial, file, lifnr, invoices, referencia, language, hdnDivision, subto, receptionType, moneda, flag);
	}
	
	@Transactional(readOnly = false)
	public Z_FE_FM_AGREGAR_FAC_SIN_ORDERResponse addCFDISOC(String cmbRazonSocial,MultipartFile[] file,String lifnr, String invoices, String concept, String area, String responsable, String language, String hdnDivision, String referencia) throws IOException {
		return invoiceDao.addCFDISOC(cmbRazonSocial, area, responsable, invoices, concept, file, lifnr, language, hdnDivision, referencia);
	}
	
	@Transactional(readOnly = false)
	public Z_FE_FM_AGREGAR_FAC_ORDERResponse addCBB(MultipartFile[] files,String invoice, String hdnRazonSocial, String fechaInvoice, String hdnIva, String hdnMoneda, String retenciones, String subtotal, String lifnr, String moneda, String referencia, String invoiceData, String language, String hdnDivision) throws IOException {
		return invoiceDao.addCBB(hdnRazonSocial, files, lifnr, invoice, fechaInvoice, hdnIva, retenciones, moneda, referencia, subtotal, invoiceData,language, hdnDivision);
	}
	
	@Transactional(readOnly = false)
	public Z_FE_FM_AGREGAR_FAC_ORDER_VIResponse addCBB(MultipartFile[] files,JSONArray invoice, String hdnRazonSocial, String fechaInvoice, String hdnIva, String hdnMoneda, String retenciones, String subtotal, String lifnr, String moneda, String referencia, String invoiceData, String language, String hdnDivision, String subto, String receptionType, String currency) throws IOException, JSONException{
		return invoiceDao.addCBBVI(hdnRazonSocial, files, lifnr, invoice, fechaInvoice, hdnIva, retenciones, moneda, referencia, subtotal, invoiceData, language, hdnDivision, subto, receptionType, currency);
	}
	
	@Transactional(readOnly = false)
	public Z_FE_FM_AGREGAR_FAC_ORDER_VIResponse addCBB(MultipartFile[] files,JSONArray invoice, String hdnRazonSocial, String fechaInvoice, String hdnIva, String hdnMoneda, String retenciones, String subtotal, String lifnr, String moneda, String referencia, String invoiceData, String language, String hdnDivision, String subto, String receptionType, String currency, String flag) throws IOException, JSONException{
		return invoiceDao.addCBBVI(hdnRazonSocial, files, lifnr, invoice, fechaInvoice, hdnIva, retenciones, moneda, referencia, subtotal, invoiceData, language, hdnDivision, subto, receptionType, currency,flag);
	}
	
	@Transactional(readOnly = false)
	public Z_FE_FM_AGREGAR_FAC_SIN_ORDERResponse addCBBSOC(String cmbRazonSocial,MultipartFile[] files,String lifnr, String invoices, String concept, String area, String responsable, String total, String subtotal, String fechaFactura, String tax, String reten, String moneda, String language, String hdnDivision, String referencia) throws IOException {
		return invoiceDao.addCBBSOC(cmbRazonSocial, area, responsable, invoices, concept, files, lifnr, total, subtotal, fechaFactura, tax, reten, moneda, language, hdnDivision, referencia);
	}
	
}
