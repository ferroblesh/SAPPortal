
/**
 * ZWS_ORDEN_FACTURACallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package functions.rfc.sap.document.sap_com;

    /**
     *  ZWS_ORDEN_FACTURACallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class ZWS_ORDEN_FACTURACallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public ZWS_ORDEN_FACTURACallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public ZWS_ORDEN_FACTURACallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for z_FE_FM_ORDEN_FACTURA method
            * override this method for handling normal response from z_FE_FM_ORDEN_FACTURA operation
            */
           public void receiveResultz_FE_FM_ORDEN_FACTURA(
                    functions.rfc.sap.document.sap_com.Z_FE_FM_ORDEN_FACTURAResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from z_FE_FM_ORDEN_FACTURA operation
           */
            public void receiveErrorz_FE_FM_ORDEN_FACTURA(java.lang.Exception e) {
            }
                


    }
    