
/**
 * Z_FE_FM_PACKING_LISTExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package functions.rfc.sap.document.sap_com;

public class Z_FE_FM_PACKING_LISTExceptionException extends java.lang.Exception{

    private static final long serialVersionUID = 1477096285727L;
    
    private functions.rfc.sap.document.sap_com.Z_FE_FM_PACKING_LISTException faultMessage;

    
        public Z_FE_FM_PACKING_LISTExceptionException() {
            super("Z_FE_FM_PACKING_LISTExceptionException");
        }

        public Z_FE_FM_PACKING_LISTExceptionException(java.lang.String s) {
           super(s);
        }

        public Z_FE_FM_PACKING_LISTExceptionException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public Z_FE_FM_PACKING_LISTExceptionException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(functions.rfc.sap.document.sap_com.Z_FE_FM_PACKING_LISTException msg){
       faultMessage = msg;
    }
    
    public functions.rfc.sap.document.sap_com.Z_FE_FM_PACKING_LISTException getFaultMessage(){
       return faultMessage;
    }
}
    