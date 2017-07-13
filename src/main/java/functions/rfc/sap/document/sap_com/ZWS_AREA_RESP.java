

/**
 * ZWS_AREA_RESP.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package functions.rfc.sap.document.sap_com;

    /*
     *  ZWS_AREA_RESP java interface
     */

    public interface ZWS_AREA_RESP {
          

        /**
          * Auto generated method signature
          * 
                    * @param z_FE_FM_AREA_RESP0
                
         */

         
                     public functions.rfc.sap.document.sap_com.Z_FE_FM_AREA_RESPResponse z_FE_FM_AREA_RESP(

                        functions.rfc.sap.document.sap_com.Z_FE_FM_AREA_RESP z_FE_FM_AREA_RESP0)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param z_FE_FM_AREA_RESP0
            
          */
        public void startz_FE_FM_AREA_RESP(

            functions.rfc.sap.document.sap_com.Z_FE_FM_AREA_RESP z_FE_FM_AREA_RESP0,

            final functions.rfc.sap.document.sap_com.ZWS_AREA_RESPCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        
       //
       }
    