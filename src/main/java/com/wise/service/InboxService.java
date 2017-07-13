package com.wise.service;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Map;

import org.apache.axis2.AxisFault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.wise.dao.InboxDao;
import com.wise.model.InboxDto;

import functions.rfc.sap.document.sap_com.Z_FE_FM_AGREGAR_BUZONResponse;
import functions.rfc.sap.document.sap_com.Z_FE_FM_CONSULTAR_BUZONResponse;

@Service
public class InboxService {
	
	@Autowired
	private InboxDao inboxDao;
	
	@Transactional(readOnly = false)
	public Z_FE_FM_AGREGAR_BUZONResponse addInbox(String cmbRazonSocial,MultipartFile[] file,String lifnr, String invoices, String referencia, String language, String ebeln, String hdnDivision) throws IOException {
		return inboxDao.addInbox(cmbRazonSocial, file, lifnr, invoices, referencia, language, ebeln, hdnDivision);
	}
	
	@Transactional(readOnly = false)
	public Z_FE_FM_AGREGAR_BUZONResponse addInboxCFDI(String cmbRazonSocial,MultipartFile[] file,String lifnr, String hdnOrdenes, String hdnImportaciones, String cmbReceptionTypeField, String language) throws IOException,AxisFault, RemoteException {
		return inboxDao.addInboxCFDI(cmbRazonSocial, file, lifnr, hdnOrdenes, hdnImportaciones, cmbReceptionTypeField, language);
	}
	
	@Transactional(readOnly = false)
	public Z_FE_FM_AGREGAR_BUZONResponse addInboxSCFDI(String cmbRazonSocial,MultipartFile file,String lifnr, String invoice, String invoiceDate, String iva, String hdnOrdenes, 
													   String hdnImportaciones, String cmbReceptionTypeField, String language) throws IOException {
		return inboxDao.addInboxSCFDI(cmbRazonSocial, file, lifnr, invoice, invoiceDate, iva, hdnOrdenes, hdnImportaciones, cmbReceptionTypeField, language);
	}
	
	@Transactional(readOnly = true)
	public Z_FE_FM_CONSULTAR_BUZONResponse getInbox(String cmbRazonSocial, String fechaIni, String fechaFin,String lifnr, String language, String ekorg) throws AxisFault {
		return inboxDao.findInbox(cmbRazonSocial, fechaIni, fechaFin, lifnr, language, ekorg);
	}
	
	@Transactional(readOnly = false)
	public Map<String, Object> deleteInbox(InboxDto[] invoices, String language) throws AxisFault {
		return inboxDao.deleteInbox(invoices, language);
	}

}
