

/**
 * ZWS_LISTA_DIVISION.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package functions.rfc.sap.document.sap_com;

    /*
     *  ZWS_LISTA_DIVISION java interface
     */

    public interface ZWS_LISTA_DIVISION {
          

        /**
          * Auto generated method signature
          * 
                    * @param z_FE_FM_DIVISION0
                
         */

         
                     public functions.rfc.sap.document.sap_com.Z_FE_FM_DIVISIONResponse z_FE_FM_DIVISION(

                        functions.rfc.sap.document.sap_com.Z_FE_FM_DIVISION z_FE_FM_DIVISION0)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param z_FE_FM_DIVISION0
            
          */
        public void startz_FE_FM_DIVISION(

            functions.rfc.sap.document.sap_com.Z_FE_FM_DIVISION z_FE_FM_DIVISION0,

            final functions.rfc.sap.document.sap_com.ZWS_LISTA_DIVISIONCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        
       //
       }
    