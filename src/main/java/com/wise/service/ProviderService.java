package com.wise.service;

import java.rmi.RemoteException;

import org.apache.axis2.AxisFault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wise.dao.ProviderDao;
import com.wise.model.PackingDto;

import functions.rfc.sap.document.sap_com.Z_FE_FM_AREA_RESPResponse;
import functions.rfc.sap.document.sap_com.Z_FE_FM_CAMBIO_PSWD_PROVResponse;
import functions.rfc.sap.document.sap_com.Z_FE_FM_CONFIRMA_ESTADO_CUENTAResponse;
import functions.rfc.sap.document.sap_com.Z_FE_FM_CONTADOR_AVISOS_NLEResponse;
import functions.rfc.sap.document.sap_com.Z_FE_FM_DATOS_PROVEEDORResponse;
import functions.rfc.sap.document.sap_com.Z_FE_FM_DIVISIONResponse;
import functions.rfc.sap.document.sap_com.Z_FE_FM_ESTADO_CUENTAResponse;
import functions.rfc.sap.document.sap_com.Z_FE_FM_ESTATUS_PORTALResponse;
import functions.rfc.sap.document.sap_com.Z_FE_FM_LISTA_FACResponse;
import functions.rfc.sap.document.sap_com.Z_FE_FM_OBTIENE_AVISOSResponse;
import functions.rfc.sap.document.sap_com.Z_FE_FM_ORDEN_FACTURAResponse;
import functions.rfc.sap.document.sap_com.Z_FE_FM_PACKING_LISTExceptionException;
import functions.rfc.sap.document.sap_com.Z_FE_FM_PACKING_LISTResponse;
import functions.rfc.sap.document.sap_com.Z_FE_FM_RAZON_SOCIALResponse;
import functions.rfc.sap.document.sap_com.Z_FE_FM_REPORTES_PROVResponse;
import functions.rfc.sap.document.sap_com.Z_FE_FM_UPDATE_LECTURA_AVISOResponse;

@Service
public class ProviderService {
	
	@Autowired
	private ProviderDao providerDao;
	
	@Transactional(readOnly = true)
	public Z_FE_FM_DATOS_PROVEEDORResponse getProvider(String provider) throws AxisFault {
		return providerDao.findProvider(provider);
	}
	
	@Transactional(readOnly = true)
	public Z_FE_FM_RAZON_SOCIALResponse getRazonSocial(String lifnr) throws AxisFault {
		return providerDao.findRazonSocial(lifnr);
	}
	
	@Transactional(readOnly = true)
	public Z_FE_FM_LISTA_FACResponse getListaFactura(String cmbRazonSocial, String fechaIni, String fechaFin,String cmbEstado,String lifnr, String language, String ekorg) throws AxisFault {
		return providerDao.findFacturas(cmbRazonSocial, fechaIni, fechaFin, cmbEstado, lifnr, language, ekorg);
	}
	
	@Transactional(readOnly = true)
	public Z_FE_FM_ESTATUS_PORTALResponse getEstatusIntegracion(String cmbRazonSocial, String fechaIni, String fechaFin,String lifnr, String language) throws AxisFault {
		return providerDao.findInvoiceStatus(cmbRazonSocial, fechaIni, fechaFin, lifnr, language);
	}
	
	@Transactional(readOnly = true)
	public Z_FE_FM_AREA_RESPResponse getArea(String bukrs) throws AxisFault {
		return providerDao.findGerencia(bukrs);
	}
	
	@Transactional(readOnly = true)
	public Z_FE_FM_DIVISIONResponse getDivision(String bukrs) throws AxisFault {
		return providerDao.findDivision(bukrs);
	}

	@Transactional(readOnly = true)
	public Z_FE_FM_ORDEN_FACTURAResponse getPendingInvoices(String bukrs, String ekorg, String lifnr,
															String fechaIni, String fechaFin, String ebeln, 
															String language, Integer receptionType, String currency,
															String orders, String numImp) throws AxisFault {		
		return providerDao.findPendingInvoices(bukrs, ekorg, lifnr, fechaIni, fechaFin, ebeln, language, receptionType, currency, orders, numImp);
	}
	
	@Transactional(readOnly = true)
	public Z_FE_FM_ESTADO_CUENTAResponse getAccountStatus(String lifnr, String bukrs, String language) throws Exception, RemoteException {
		return providerDao.findAccountStatus(lifnr, bukrs, language);
	}
	
	@Transactional(readOnly = false)
	public Z_FE_FM_CONFIRMA_ESTADO_CUENTAResponse setAccountStatus(String lifnr, String bukrs, String language, String estado) throws Exception, RemoteException {
		return providerDao.setAccountStatus(lifnr, bukrs, language, estado);
	}
	
	@Transactional(readOnly = false)
	public Z_FE_FM_CAMBIO_PSWD_PROVResponse changePassword(String lifnr,
			String oldPass, String newPass, String newPassReview,
			String language) throws AxisFault, RemoteException {
		
		return providerDao.changePassword(lifnr, oldPass, newPass, newPassReview, language);
	}
	
	@Transactional(readOnly = true)
	public Z_FE_FM_REPORTES_PROVResponse getReports(String lifnr) throws AxisFault, RemoteException {
		return providerDao.findReports(lifnr);
	}
	
	public Z_FE_FM_CONTADOR_AVISOS_NLEResponse getUnreadNotificationsNumber(String lifnr) throws AxisFault, RemoteException {
		return providerDao.findUnreadNotificationsNumber(lifnr);
	}
	
	public Z_FE_FM_UPDATE_LECTURA_AVISOResponse setNotificationStatus(String lifrn, String bukrs, String idaviso, String evento) throws AxisFault, RemoteException {
		return providerDao.setNotificationStatus(lifrn, bukrs, idaviso, evento);
	}
	
	public Z_FE_FM_OBTIENE_AVISOSResponse getNotifications(String lifnr, String bukrs, String language, String tipoAviso) throws AxisFault,RemoteException {
		return providerDao.getNotifications(lifnr, bukrs, language, tipoAviso);
	}
	
	public Z_FE_FM_PACKING_LISTResponse getPackingList(String lifnr, PackingDto requestValues, String language) throws RemoteException, Z_FE_FM_PACKING_LISTExceptionException {
		return providerDao.getPackingList(lifnr, requestValues, language);
	}

}
