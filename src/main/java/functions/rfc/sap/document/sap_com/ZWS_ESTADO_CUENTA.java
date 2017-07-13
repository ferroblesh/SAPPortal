

/**
 * ZWS_ESTADO_CUENTA.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package functions.rfc.sap.document.sap_com;

    /*
     *  ZWS_ESTADO_CUENTA java interface
     */

    public interface ZWS_ESTADO_CUENTA {
          

        /**
          * Auto generated method signature
          * 
                    * @param z_FE_FM_ESTADO_CUENTA0
                
         */

         
                     public functions.rfc.sap.document.sap_com.Z_FE_FM_ESTADO_CUENTAResponse z_FE_FM_ESTADO_CUENTA(

                        functions.rfc.sap.document.sap_com.Z_FE_FM_ESTADO_CUENTA z_FE_FM_ESTADO_CUENTA0)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param z_FE_FM_ESTADO_CUENTA0
            
          */
        public void startz_FE_FM_ESTADO_CUENTA(

            functions.rfc.sap.document.sap_com.Z_FE_FM_ESTADO_CUENTA z_FE_FM_ESTADO_CUENTA0,

            final functions.rfc.sap.document.sap_com.ZWS_ESTADO_CUENTACallbackHandler callback)

            throws java.rmi.RemoteException;

     

        
       //
       }
    