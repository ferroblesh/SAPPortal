

/**
 * ZWS_OBTIENE_AVISOS.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package functions.rfc.sap.document.sap_com;

    /*
     *  ZWS_OBTIENE_AVISOS java interface
     */

    public interface ZWS_OBTIENE_AVISOS {
          

        /**
          * Auto generated method signature
          * 
                    * @param z_FE_FM_OBTIENE_AVISOS0
                
         */

         
                     public functions.rfc.sap.document.sap_com.Z_FE_FM_OBTIENE_AVISOSResponse z_FE_FM_OBTIENE_AVISOS(

                        functions.rfc.sap.document.sap_com.Z_FE_FM_OBTIENE_AVISOS z_FE_FM_OBTIENE_AVISOS0)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param z_FE_FM_OBTIENE_AVISOS0
            
          */
        public void startz_FE_FM_OBTIENE_AVISOS(

            functions.rfc.sap.document.sap_com.Z_FE_FM_OBTIENE_AVISOS z_FE_FM_OBTIENE_AVISOS0,

            final functions.rfc.sap.document.sap_com.ZWS_OBTIENE_AVISOSCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        
       //
       }
    