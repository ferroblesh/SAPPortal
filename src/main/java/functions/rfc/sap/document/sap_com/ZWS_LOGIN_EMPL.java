

/**
 * ZWS_LOGIN_EMPL.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package functions.rfc.sap.document.sap_com;

    /*
     *  ZWS_LOGIN_EMPL java interface
     */

    public interface ZWS_LOGIN_EMPL {
          

        /**
          * Auto generated method signature
          * 
                    * @param z_FE_FM_LOGIN_EMPL0
                
         */

         
                     public functions.rfc.sap.document.sap_com.Z_FE_FM_LOGIN_EMPLResponse z_FE_FM_LOGIN_EMPL(

                        functions.rfc.sap.document.sap_com.Z_FE_FM_LOGIN_EMPL z_FE_FM_LOGIN_EMPL0)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param z_FE_FM_LOGIN_EMPL0
            
          */
        public void startz_FE_FM_LOGIN_EMPL(

            functions.rfc.sap.document.sap_com.Z_FE_FM_LOGIN_EMPL z_FE_FM_LOGIN_EMPL0,

            final functions.rfc.sap.document.sap_com.ZWS_LOGIN_EMPLCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        
       //
       }
    