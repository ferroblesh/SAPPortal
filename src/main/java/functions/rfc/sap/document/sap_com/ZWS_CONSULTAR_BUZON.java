

/**
 * ZWS_CONSULTAR_BUZON.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package functions.rfc.sap.document.sap_com;

    /*
     *  ZWS_CONSULTAR_BUZON java interface
     */

    public interface ZWS_CONSULTAR_BUZON {
          

        /**
          * Auto generated method signature
          * 
                    * @param z_FE_FM_CONSULTAR_BUZON0
                
         */

         
                     public functions.rfc.sap.document.sap_com.Z_FE_FM_CONSULTAR_BUZONResponse z_FE_FM_CONSULTAR_BUZON(

                        functions.rfc.sap.document.sap_com.Z_FE_FM_CONSULTAR_BUZON z_FE_FM_CONSULTAR_BUZON0)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param z_FE_FM_CONSULTAR_BUZON0
            
          */
        public void startz_FE_FM_CONSULTAR_BUZON(

            functions.rfc.sap.document.sap_com.Z_FE_FM_CONSULTAR_BUZON z_FE_FM_CONSULTAR_BUZON0,

            final functions.rfc.sap.document.sap_com.ZWS_CONSULTAR_BUZONCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        
       //
       }
    