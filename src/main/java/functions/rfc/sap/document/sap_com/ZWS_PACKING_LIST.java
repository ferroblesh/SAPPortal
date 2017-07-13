

/**
 * ZWS_PACKING_LIST.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package functions.rfc.sap.document.sap_com;

    /*
     *  ZWS_PACKING_LIST java interface
     */

    public interface ZWS_PACKING_LIST {
          

        /**
          * Auto generated method signature
          * 
                    * @param z_FE_FM_PACKING_LIST0
                
             * @throws functions.rfc.sap.document.sap_com.Z_FE_FM_PACKING_LISTExceptionException : 
         */

         
                     public functions.rfc.sap.document.sap_com.Z_FE_FM_PACKING_LISTResponse z_FE_FM_PACKING_LIST(

                        functions.rfc.sap.document.sap_com.Z_FE_FM_PACKING_LIST z_FE_FM_PACKING_LIST0)
                        throws java.rmi.RemoteException
             
          ,functions.rfc.sap.document.sap_com.Z_FE_FM_PACKING_LISTExceptionException;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param z_FE_FM_PACKING_LIST0
            
          */
        public void startz_FE_FM_PACKING_LIST(

            functions.rfc.sap.document.sap_com.Z_FE_FM_PACKING_LIST z_FE_FM_PACKING_LIST0,

            final functions.rfc.sap.document.sap_com.ZWS_PACKING_LISTCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        
       //
       }
    