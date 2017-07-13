

/**
 * ZWS_ESTATUS_PORTAL.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package functions.rfc.sap.document.sap_com;

    /*
     *  ZWS_ESTATUS_PORTAL java interface
     */

    public interface ZWS_ESTATUS_PORTAL {
          

        /**
          * Auto generated method signature
          * 
                    * @param z_FE_FM_ESTATUS_PORTAL0
                
         */

         
                     public functions.rfc.sap.document.sap_com.Z_FE_FM_ESTATUS_PORTALResponse z_FE_FM_ESTATUS_PORTAL(

                        functions.rfc.sap.document.sap_com.Z_FE_FM_ESTATUS_PORTAL z_FE_FM_ESTATUS_PORTAL0)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param z_FE_FM_ESTATUS_PORTAL0
            
          */
        public void startz_FE_FM_ESTATUS_PORTAL(

            functions.rfc.sap.document.sap_com.Z_FE_FM_ESTATUS_PORTAL z_FE_FM_ESTATUS_PORTAL0,

            final functions.rfc.sap.document.sap_com.ZWS_ESTATUS_PORTALCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        
       //
       }
    