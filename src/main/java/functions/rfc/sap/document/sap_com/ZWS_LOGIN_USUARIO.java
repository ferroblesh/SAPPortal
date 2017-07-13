

/**
 * ZWS_LOGIN_USUARIO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package functions.rfc.sap.document.sap_com;

    /*
     *  ZWS_LOGIN_USUARIO java interface
     */

    public interface ZWS_LOGIN_USUARIO {
          

        /**
          * Auto generated method signature
          * 
                    * @param z_FE_FM_LOGIN_USUARIO0
                
         */

         
                     public functions.rfc.sap.document.sap_com.Z_FE_FM_LOGIN_USUARIOResponse z_FE_FM_LOGIN_USUARIO(

                        functions.rfc.sap.document.sap_com.Z_FE_FM_LOGIN_USUARIO z_FE_FM_LOGIN_USUARIO0)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param z_FE_FM_LOGIN_USUARIO0
            
          */
        public void startz_FE_FM_LOGIN_USUARIO(

            functions.rfc.sap.document.sap_com.Z_FE_FM_LOGIN_USUARIO z_FE_FM_LOGIN_USUARIO0,

            final functions.rfc.sap.document.sap_com.ZWS_LOGIN_USUARIOCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        
       //
       }
    