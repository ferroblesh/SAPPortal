

/**
 * ZWS_ORDEN_FACTURA.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package functions.rfc.sap.document.sap_com;

    /*
     *  ZWS_ORDEN_FACTURA java interface
     */

    public interface ZWS_ORDEN_FACTURA {
          

        /**
          * Auto generated method signature
          * 
                    * @param z_FE_FM_ORDEN_FACTURA0
                
         */

         
                     public functions.rfc.sap.document.sap_com.Z_FE_FM_ORDEN_FACTURAResponse z_FE_FM_ORDEN_FACTURA(

                        functions.rfc.sap.document.sap_com.Z_FE_FM_ORDEN_FACTURA z_FE_FM_ORDEN_FACTURA0)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param z_FE_FM_ORDEN_FACTURA0
            
          */
        public void startz_FE_FM_ORDEN_FACTURA(

            functions.rfc.sap.document.sap_com.Z_FE_FM_ORDEN_FACTURA z_FE_FM_ORDEN_FACTURA0,

            final functions.rfc.sap.document.sap_com.ZWS_ORDEN_FACTURACallbackHandler callback)

            throws java.rmi.RemoteException;

     

        
       //
       }
    