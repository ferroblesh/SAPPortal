
/**
 * Z_FE_FM_AGREGAR_FAC_ORDER.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:34:40 IST)
 */

            
                package functions.rfc.sap.document.sap_com;
            

            /**
            *  Z_FE_FM_AGREGAR_FAC_ORDER bean class
            */
            @SuppressWarnings({"unchecked","unused"})
        
        public  class Z_FE_FM_AGREGAR_FAC_ORDER
        implements org.apache.axis2.databinding.ADBBean{
        
                public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName(
                "urn:sap-com:document:sap:rfc:functions",
                "Z_FE_FM_AGREGAR_FAC_ORDER",
                "ns1");

            

                        /**
                        * field for I_BUKRS
                        */

                        
                                    protected functions.rfc.sap.document.sap_com.Char4 localI_BUKRS ;
                                

                           /**
                           * Auto generated getter method
                           * @return functions.rfc.sap.document.sap_com.Char4
                           */
                           public  functions.rfc.sap.document.sap_com.Char4 getI_BUKRS(){
                               return localI_BUKRS;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param I_BUKRS
                               */
                               public void setI_BUKRS(functions.rfc.sap.document.sap_com.Char4 param){
                            
                                            this.localI_BUKRS=param;
                                    

                               }
                            

                        /**
                        * field for I_DATE
                        */

                        
                                    protected functions.rfc.sap.document.sap_com.Date localI_DATE ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localI_DATETracker = false ;

                           public boolean isI_DATESpecified(){
                               return localI_DATETracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return functions.rfc.sap.document.sap_com.Date
                           */
                           public  functions.rfc.sap.document.sap_com.Date getI_DATE(){
                               return localI_DATE;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param I_DATE
                               */
                               public void setI_DATE(functions.rfc.sap.document.sap_com.Date param){
                            localI_DATETracker = param != null;
                                   
                                            this.localI_DATE=param;
                                    

                               }
                            

                        /**
                        * field for I_EKORG
                        */

                        
                                    protected functions.rfc.sap.document.sap_com.Char4 localI_EKORG ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localI_EKORGTracker = false ;

                           public boolean isI_EKORGSpecified(){
                               return localI_EKORGTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return functions.rfc.sap.document.sap_com.Char4
                           */
                           public  functions.rfc.sap.document.sap_com.Char4 getI_EKORG(){
                               return localI_EKORG;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param I_EKORG
                               */
                               public void setI_EKORG(functions.rfc.sap.document.sap_com.Char4 param){
                            localI_EKORGTracker = param != null;
                                   
                                            this.localI_EKORG=param;
                                    

                               }
                            

                        /**
                        * field for I_FLAG
                        */

                        
                                    protected functions.rfc.sap.document.sap_com.Char1 localI_FLAG ;
                                

                           /**
                           * Auto generated getter method
                           * @return functions.rfc.sap.document.sap_com.Char1
                           */
                           public  functions.rfc.sap.document.sap_com.Char1 getI_FLAG(){
                               return localI_FLAG;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param I_FLAG
                               */
                               public void setI_FLAG(functions.rfc.sap.document.sap_com.Char1 param){
                            
                                            this.localI_FLAG=param;
                                    

                               }
                            

                        /**
                        * field for I_IDIOMA
                        */

                        
                                    protected functions.rfc.sap.document.sap_com.Char2 localI_IDIOMA ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localI_IDIOMATracker = false ;

                           public boolean isI_IDIOMASpecified(){
                               return localI_IDIOMATracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return functions.rfc.sap.document.sap_com.Char2
                           */
                           public  functions.rfc.sap.document.sap_com.Char2 getI_IDIOMA(){
                               return localI_IDIOMA;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param I_IDIOMA
                               */
                               public void setI_IDIOMA(functions.rfc.sap.document.sap_com.Char2 param){
                            localI_IDIOMATracker = param != null;
                                   
                                            this.localI_IDIOMA=param;
                                    

                               }
                            

                        /**
                        * field for I_KIDNO
                        */

                        
                                    protected functions.rfc.sap.document.sap_com.Char30 localI_KIDNO ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localI_KIDNOTracker = false ;

                           public boolean isI_KIDNOSpecified(){
                               return localI_KIDNOTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return functions.rfc.sap.document.sap_com.Char30
                           */
                           public  functions.rfc.sap.document.sap_com.Char30 getI_KIDNO(){
                               return localI_KIDNO;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param I_KIDNO
                               */
                               public void setI_KIDNO(functions.rfc.sap.document.sap_com.Char30 param){
                            localI_KIDNOTracker = param != null;
                                   
                                            this.localI_KIDNO=param;
                                    

                               }
                            

                        /**
                        * field for I_LIFNR
                        */

                        
                                    protected functions.rfc.sap.document.sap_com.Char10 localI_LIFNR ;
                                

                           /**
                           * Auto generated getter method
                           * @return functions.rfc.sap.document.sap_com.Char10
                           */
                           public  functions.rfc.sap.document.sap_com.Char10 getI_LIFNR(){
                               return localI_LIFNR;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param I_LIFNR
                               */
                               public void setI_LIFNR(functions.rfc.sap.document.sap_com.Char10 param){
                            
                                            this.localI_LIFNR=param;
                                    

                               }
                            

                        /**
                        * field for I_OPDF
                        */

                        
                                    protected javax.activation.DataHandler localI_OPDF ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localI_OPDFTracker = false ;

                           public boolean isI_OPDFSpecified(){
                               return localI_OPDFTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return javax.activation.DataHandler
                           */
                           public  javax.activation.DataHandler getI_OPDF(){
                               return localI_OPDF;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param I_OPDF
                               */
                               public void setI_OPDF(javax.activation.DataHandler param){
                            localI_OPDFTracker = param != null;
                                   
                                            this.localI_OPDF=param;
                                    

                               }
                            

                        /**
                        * field for I_PDF
                        */

                        
                                    protected javax.activation.DataHandler localI_PDF ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localI_PDFTracker = false ;

                           public boolean isI_PDFSpecified(){
                               return localI_PDFTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return javax.activation.DataHandler
                           */
                           public  javax.activation.DataHandler getI_PDF(){
                               return localI_PDF;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param I_PDF
                               */
                               public void setI_PDF(javax.activation.DataHandler param){
                            localI_PDFTracker = param != null;
                                   
                                            this.localI_PDF=param;
                                    

                               }
                            

                        /**
                        * field for I_REFER
                        */

                        
                                    protected functions.rfc.sap.document.sap_com.ZTT_REFER localI_REFER ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localI_REFERTracker = false ;

                           public boolean isI_REFERSpecified(){
                               return localI_REFERTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return functions.rfc.sap.document.sap_com.ZTT_REFER
                           */
                           public  functions.rfc.sap.document.sap_com.ZTT_REFER getI_REFER(){
                               return localI_REFER;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param I_REFER
                               */
                               public void setI_REFER(functions.rfc.sap.document.sap_com.ZTT_REFER param){
                            localI_REFERTracker = param != null;
                                   
                                            this.localI_REFER=param;
                                    

                               }
                            

                        /**
                        * field for I_RETEN
                        */

                        
                                    protected functions.rfc.sap.document.sap_com.Curr132 localI_RETEN ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localI_RETENTracker = false ;

                           public boolean isI_RETENSpecified(){
                               return localI_RETENTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return functions.rfc.sap.document.sap_com.Curr132
                           */
                           public  functions.rfc.sap.document.sap_com.Curr132 getI_RETEN(){
                               return localI_RETEN;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param I_RETEN
                               */
                               public void setI_RETEN(functions.rfc.sap.document.sap_com.Curr132 param){
                            localI_RETENTracker = param != null;
                                   
                                            this.localI_RETEN=param;
                                    

                               }
                            

                        /**
                        * field for I_SUBTOTAL
                        */

                        
                                    protected functions.rfc.sap.document.sap_com.Curr152 localI_SUBTOTAL ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localI_SUBTOTALTracker = false ;

                           public boolean isI_SUBTOTALSpecified(){
                               return localI_SUBTOTALTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return functions.rfc.sap.document.sap_com.Curr152
                           */
                           public  functions.rfc.sap.document.sap_com.Curr152 getI_SUBTOTAL(){
                               return localI_SUBTOTAL;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param I_SUBTOTAL
                               */
                               public void setI_SUBTOTAL(functions.rfc.sap.document.sap_com.Curr152 param){
                            localI_SUBTOTALTracker = param != null;
                                   
                                            this.localI_SUBTOTAL=param;
                                    

                               }
                            

                        /**
                        * field for I_TAX
                        */

                        
                                    protected functions.rfc.sap.document.sap_com.Curr132 localI_TAX ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localI_TAXTracker = false ;

                           public boolean isI_TAXSpecified(){
                               return localI_TAXTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return functions.rfc.sap.document.sap_com.Curr132
                           */
                           public  functions.rfc.sap.document.sap_com.Curr132 getI_TAX(){
                               return localI_TAX;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param I_TAX
                               */
                               public void setI_TAX(functions.rfc.sap.document.sap_com.Curr132 param){
                            localI_TAXTracker = param != null;
                                   
                                            this.localI_TAX=param;
                                    

                               }
                            

                        /**
                        * field for I_WAERS
                        */

                        
                                    protected functions.rfc.sap.document.sap_com.Cuky5 localI_WAERS ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localI_WAERSTracker = false ;

                           public boolean isI_WAERSSpecified(){
                               return localI_WAERSTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return functions.rfc.sap.document.sap_com.Cuky5
                           */
                           public  functions.rfc.sap.document.sap_com.Cuky5 getI_WAERS(){
                               return localI_WAERS;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param I_WAERS
                               */
                               public void setI_WAERS(functions.rfc.sap.document.sap_com.Cuky5 param){
                            localI_WAERSTracker = param != null;
                                   
                                            this.localI_WAERS=param;
                                    

                               }
                            

                        /**
                        * field for I_XBLNR
                        */

                        
                                    protected functions.rfc.sap.document.sap_com.Char16 localI_XBLNR ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localI_XBLNRTracker = false ;

                           public boolean isI_XBLNRSpecified(){
                               return localI_XBLNRTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return functions.rfc.sap.document.sap_com.Char16
                           */
                           public  functions.rfc.sap.document.sap_com.Char16 getI_XBLNR(){
                               return localI_XBLNR;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param I_XBLNR
                               */
                               public void setI_XBLNR(functions.rfc.sap.document.sap_com.Char16 param){
                            localI_XBLNRTracker = param != null;
                                   
                                            this.localI_XBLNR=param;
                                    

                               }
                            

                        /**
                        * field for I_XML
                        */

                        
                                    protected javax.activation.DataHandler localI_XML ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localI_XMLTracker = false ;

                           public boolean isI_XMLSpecified(){
                               return localI_XMLTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return javax.activation.DataHandler
                           */
                           public  javax.activation.DataHandler getI_XML(){
                               return localI_XML;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param I_XML
                               */
                               public void setI_XML(javax.activation.DataHandler param){
                            localI_XMLTracker = param != null;
                                   
                                            this.localI_XML=param;
                                    

                               }
                            

     
     
        /**
        *
        * @param parentQName
        * @param factory
        * @return org.apache.axiom.om.OMElement
        */
       public org.apache.axiom.om.OMElement getOMElement (
               final javax.xml.namespace.QName parentQName,
               final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException{


        
               org.apache.axiom.om.OMDataSource dataSource =
                       new org.apache.axis2.databinding.ADBDataSource(this,MY_QNAME);
               return factory.createOMElement(dataSource,MY_QNAME);
            
        }

         public void serialize(final javax.xml.namespace.QName parentQName,
                                       javax.xml.stream.XMLStreamWriter xmlWriter)
                                throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
                           serialize(parentQName,xmlWriter,false);
         }

         public void serialize(final javax.xml.namespace.QName parentQName,
                               javax.xml.stream.XMLStreamWriter xmlWriter,
                               boolean serializeType)
            throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
            
                


                java.lang.String prefix = null;
                java.lang.String namespace = null;
                

                    prefix = parentQName.getPrefix();
                    namespace = parentQName.getNamespaceURI();
                    writeStartElement(prefix, namespace, parentQName.getLocalPart(), xmlWriter);
                
                  if (serializeType){
               

                   java.lang.String namespacePrefix = registerPrefix(xmlWriter,"urn:sap-com:document:sap:rfc:functions");
                   if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)){
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           namespacePrefix+":Z_FE_FM_AGREGAR_FAC_ORDER",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "Z_FE_FM_AGREGAR_FAC_ORDER",
                           xmlWriter);
                   }

               
                   }
               
                                            if (localI_BUKRS==null){
                                                 throw new org.apache.axis2.databinding.ADBException("I_BUKRS cannot be null!!");
                                            }
                                           localI_BUKRS.serialize(new javax.xml.namespace.QName("","I_BUKRS"),
                                               xmlWriter);
                                         if (localI_DATETracker){
                                            if (localI_DATE==null){
                                                 throw new org.apache.axis2.databinding.ADBException("I_DATE cannot be null!!");
                                            }
                                           localI_DATE.serialize(new javax.xml.namespace.QName("","I_DATE"),
                                               xmlWriter);
                                        } if (localI_EKORGTracker){
                                            if (localI_EKORG==null){
                                                 throw new org.apache.axis2.databinding.ADBException("I_EKORG cannot be null!!");
                                            }
                                           localI_EKORG.serialize(new javax.xml.namespace.QName("","I_EKORG"),
                                               xmlWriter);
                                        }
                                            if (localI_FLAG==null){
                                                 throw new org.apache.axis2.databinding.ADBException("I_FLAG cannot be null!!");
                                            }
                                           localI_FLAG.serialize(new javax.xml.namespace.QName("","I_FLAG"),
                                               xmlWriter);
                                         if (localI_IDIOMATracker){
                                            if (localI_IDIOMA==null){
                                                 throw new org.apache.axis2.databinding.ADBException("I_IDIOMA cannot be null!!");
                                            }
                                           localI_IDIOMA.serialize(new javax.xml.namespace.QName("","I_IDIOMA"),
                                               xmlWriter);
                                        } if (localI_KIDNOTracker){
                                            if (localI_KIDNO==null){
                                                 throw new org.apache.axis2.databinding.ADBException("I_KIDNO cannot be null!!");
                                            }
                                           localI_KIDNO.serialize(new javax.xml.namespace.QName("","I_KIDNO"),
                                               xmlWriter);
                                        }
                                            if (localI_LIFNR==null){
                                                 throw new org.apache.axis2.databinding.ADBException("I_LIFNR cannot be null!!");
                                            }
                                           localI_LIFNR.serialize(new javax.xml.namespace.QName("","I_LIFNR"),
                                               xmlWriter);
                                         if (localI_OPDFTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "I_OPDF", xmlWriter);
                             
                                        
                                    if (localI_OPDF!=null)  {
                                       try {
                                           org.apache.axiom.util.stax.XMLStreamWriterUtils.writeDataHandler(xmlWriter, localI_OPDF, null, true);
                                       } catch (java.io.IOException ex) {
                                           throw new javax.xml.stream.XMLStreamException("Unable to read data handler for I_OPDF", ex);
                                       }
                                    } else {
                                         
                                    }
                                 
                                   xmlWriter.writeEndElement();
                             } if (localI_PDFTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "I_PDF", xmlWriter);
                             
                                        
                                    if (localI_PDF!=null)  {
                                       try {
                                           org.apache.axiom.util.stax.XMLStreamWriterUtils.writeDataHandler(xmlWriter, localI_PDF, null, true);
                                       } catch (java.io.IOException ex) {
                                           throw new javax.xml.stream.XMLStreamException("Unable to read data handler for I_PDF", ex);
                                       }
                                    } else {
                                         
                                    }
                                 
                                   xmlWriter.writeEndElement();
                             } if (localI_REFERTracker){
                                            if (localI_REFER==null){
                                                 throw new org.apache.axis2.databinding.ADBException("I_REFER cannot be null!!");
                                            }
                                           localI_REFER.serialize(new javax.xml.namespace.QName("","I_REFER"),
                                               xmlWriter);
                                        } if (localI_RETENTracker){
                                            if (localI_RETEN==null){
                                                 throw new org.apache.axis2.databinding.ADBException("I_RETEN cannot be null!!");
                                            }
                                           localI_RETEN.serialize(new javax.xml.namespace.QName("","I_RETEN"),
                                               xmlWriter);
                                        } if (localI_SUBTOTALTracker){
                                            if (localI_SUBTOTAL==null){
                                                 throw new org.apache.axis2.databinding.ADBException("I_SUBTOTAL cannot be null!!");
                                            }
                                           localI_SUBTOTAL.serialize(new javax.xml.namespace.QName("","I_SUBTOTAL"),
                                               xmlWriter);
                                        } if (localI_TAXTracker){
                                            if (localI_TAX==null){
                                                 throw new org.apache.axis2.databinding.ADBException("I_TAX cannot be null!!");
                                            }
                                           localI_TAX.serialize(new javax.xml.namespace.QName("","I_TAX"),
                                               xmlWriter);
                                        } if (localI_WAERSTracker){
                                            if (localI_WAERS==null){
                                                 throw new org.apache.axis2.databinding.ADBException("I_WAERS cannot be null!!");
                                            }
                                           localI_WAERS.serialize(new javax.xml.namespace.QName("","I_WAERS"),
                                               xmlWriter);
                                        } if (localI_XBLNRTracker){
                                            if (localI_XBLNR==null){
                                                 throw new org.apache.axis2.databinding.ADBException("I_XBLNR cannot be null!!");
                                            }
                                           localI_XBLNR.serialize(new javax.xml.namespace.QName("","I_XBLNR"),
                                               xmlWriter);
                                        } if (localI_XMLTracker){
                                    namespace = "";
                                    writeStartElement(null, namespace, "I_XML", xmlWriter);
                             
                                        
                                    if (localI_XML!=null)  {
                                       try {
                                           org.apache.axiom.util.stax.XMLStreamWriterUtils.writeDataHandler(xmlWriter, localI_XML, null, true);
                                       } catch (java.io.IOException ex) {
                                           throw new javax.xml.stream.XMLStreamException("Unable to read data handler for I_XML", ex);
                                       }
                                    } else {
                                         
                                    }
                                 
                                   xmlWriter.writeEndElement();
                             }
                    xmlWriter.writeEndElement();
               

        }

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("urn:sap-com:document:sap:rfc:functions")){
                return "ns1";
            }
            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        /**
         * Utility method to write an element start tag.
         */
        private void writeStartElement(java.lang.String prefix, java.lang.String namespace, java.lang.String localPart,
                                       javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
            if (writerPrefix != null) {
                xmlWriter.writeStartElement(namespace, localPart);
            } else {
                if (namespace.length() == 0) {
                    prefix = "";
                } else if (prefix == null) {
                    prefix = generatePrefix(namespace);
                }

                xmlWriter.writeStartElement(prefix, localPart, namespace);
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
        }
        
        /**
         * Util method to write an attribute with the ns prefix
         */
        private void writeAttribute(java.lang.String prefix,java.lang.String namespace,java.lang.String attName,
                                    java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
            if (xmlWriter.getPrefix(namespace) == null) {
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            xmlWriter.writeAttribute(namespace,attName,attValue);
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeAttribute(java.lang.String namespace,java.lang.String attName,
                                    java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName,attValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace,attName,attValue);
            }
        }


           /**
             * Util method to write an attribute without the ns prefix
             */
            private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
                                             javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

                java.lang.String attributeNamespace = qname.getNamespaceURI();
                java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
                if (attributePrefix == null) {
                    attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
                }
                java.lang.String attributeValue;
                if (attributePrefix.trim().length() > 0) {
                    attributeValue = attributePrefix + ":" + qname.getLocalPart();
                } else {
                    attributeValue = qname.getLocalPart();
                }

                if (namespace.equals("")) {
                    xmlWriter.writeAttribute(attName, attributeValue);
                } else {
                    registerPrefix(xmlWriter, namespace);
                    xmlWriter.writeAttribute(namespace, attName, attributeValue);
                }
            }
        /**
         *  method to handle Qnames
         */

        private void writeQName(javax.xml.namespace.QName qname,
                                javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();
            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix,namespaceURI);
                }

                if (prefix.trim().length() > 0){
                    xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                }

            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
                                 javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }
                    namespaceURI = qnames[i].getNamespaceURI();
                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);
                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix,namespaceURI);
                        }

                        if (prefix.trim().length() > 0){
                            stringToWrite.append(prefix).append(":").append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                    }
                }
                xmlWriter.writeCharacters(stringToWrite.toString());
            }

        }


        /**
         * Register a namespace prefix
         */
        private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace) throws javax.xml.stream.XMLStreamException {
            java.lang.String prefix = xmlWriter.getPrefix(namespace);
            if (prefix == null) {
                prefix = generatePrefix(namespace);
                javax.xml.namespace.NamespaceContext nsContext = xmlWriter.getNamespaceContext();
                while (true) {
                    java.lang.String uri = nsContext.getNamespaceURI(prefix);
                    if (uri == null || uri.length() == 0) {
                        break;
                    }
                    prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                }
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            return prefix;
        }


  
        /**
        * databinding method to get an XML representation of this object
        *
        */
        public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
                    throws org.apache.axis2.databinding.ADBException{


        
                 java.util.ArrayList elementList = new java.util.ArrayList();
                 java.util.ArrayList attribList = new java.util.ArrayList();

                
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "I_BUKRS"));
                            
                            
                                    if (localI_BUKRS==null){
                                         throw new org.apache.axis2.databinding.ADBException("I_BUKRS cannot be null!!");
                                    }
                                    elementList.add(localI_BUKRS);
                                 if (localI_DATETracker){
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "I_DATE"));
                            
                            
                                    if (localI_DATE==null){
                                         throw new org.apache.axis2.databinding.ADBException("I_DATE cannot be null!!");
                                    }
                                    elementList.add(localI_DATE);
                                } if (localI_EKORGTracker){
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "I_EKORG"));
                            
                            
                                    if (localI_EKORG==null){
                                         throw new org.apache.axis2.databinding.ADBException("I_EKORG cannot be null!!");
                                    }
                                    elementList.add(localI_EKORG);
                                }
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "I_FLAG"));
                            
                            
                                    if (localI_FLAG==null){
                                         throw new org.apache.axis2.databinding.ADBException("I_FLAG cannot be null!!");
                                    }
                                    elementList.add(localI_FLAG);
                                 if (localI_IDIOMATracker){
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "I_IDIOMA"));
                            
                            
                                    if (localI_IDIOMA==null){
                                         throw new org.apache.axis2.databinding.ADBException("I_IDIOMA cannot be null!!");
                                    }
                                    elementList.add(localI_IDIOMA);
                                } if (localI_KIDNOTracker){
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "I_KIDNO"));
                            
                            
                                    if (localI_KIDNO==null){
                                         throw new org.apache.axis2.databinding.ADBException("I_KIDNO cannot be null!!");
                                    }
                                    elementList.add(localI_KIDNO);
                                }
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "I_LIFNR"));
                            
                            
                                    if (localI_LIFNR==null){
                                         throw new org.apache.axis2.databinding.ADBException("I_LIFNR cannot be null!!");
                                    }
                                    elementList.add(localI_LIFNR);
                                 if (localI_OPDFTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                        "I_OPDF"));
                                
                            elementList.add(localI_OPDF);
                        } if (localI_PDFTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                        "I_PDF"));
                                
                            elementList.add(localI_PDF);
                        } if (localI_REFERTracker){
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "I_REFER"));
                            
                            
                                    if (localI_REFER==null){
                                         throw new org.apache.axis2.databinding.ADBException("I_REFER cannot be null!!");
                                    }
                                    elementList.add(localI_REFER);
                                } if (localI_RETENTracker){
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "I_RETEN"));
                            
                            
                                    if (localI_RETEN==null){
                                         throw new org.apache.axis2.databinding.ADBException("I_RETEN cannot be null!!");
                                    }
                                    elementList.add(localI_RETEN);
                                } if (localI_SUBTOTALTracker){
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "I_SUBTOTAL"));
                            
                            
                                    if (localI_SUBTOTAL==null){
                                         throw new org.apache.axis2.databinding.ADBException("I_SUBTOTAL cannot be null!!");
                                    }
                                    elementList.add(localI_SUBTOTAL);
                                } if (localI_TAXTracker){
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "I_TAX"));
                            
                            
                                    if (localI_TAX==null){
                                         throw new org.apache.axis2.databinding.ADBException("I_TAX cannot be null!!");
                                    }
                                    elementList.add(localI_TAX);
                                } if (localI_WAERSTracker){
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "I_WAERS"));
                            
                            
                                    if (localI_WAERS==null){
                                         throw new org.apache.axis2.databinding.ADBException("I_WAERS cannot be null!!");
                                    }
                                    elementList.add(localI_WAERS);
                                } if (localI_XBLNRTracker){
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "I_XBLNR"));
                            
                            
                                    if (localI_XBLNR==null){
                                         throw new org.apache.axis2.databinding.ADBException("I_XBLNR cannot be null!!");
                                    }
                                    elementList.add(localI_XBLNR);
                                } if (localI_XMLTracker){
                                      elementList.add(new javax.xml.namespace.QName("",
                                        "I_XML"));
                                
                            elementList.add(localI_XML);
                        }

                return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());
            
            

        }

  

     /**
      *  Factory class that keeps the parse method
      */
    public static class Factory{

        
        

        /**
        * static method to create the object
        * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
        *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
        * Postcondition: If this object is an element, the reader is positioned at its end element
        *                If this object is a complex type, the reader is positioned at the end element of its outer element
        */
        public static Z_FE_FM_AGREGAR_FAC_ORDER parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            Z_FE_FM_AGREGAR_FAC_ORDER object =
                new Z_FE_FM_AGREGAR_FAC_ORDER();

            int event;
            java.lang.String nillableValue = null;
            java.lang.String prefix ="";
            java.lang.String namespaceuri ="";
            try {
                
                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                
                if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","type")!=null){
                  java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                        "type");
                  if (fullTypeName!=null){
                    java.lang.String nsPrefix = null;
                    if (fullTypeName.indexOf(":") > -1){
                        nsPrefix = fullTypeName.substring(0,fullTypeName.indexOf(":"));
                    }
                    nsPrefix = nsPrefix==null?"":nsPrefix;

                    java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":")+1);
                    
                            if (!"Z_FE_FM_AGREGAR_FAC_ORDER".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (Z_FE_FM_AGREGAR_FAC_ORDER)functions.rfc.sap.document.sap_com.ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                
                    
                    reader.next();
                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","I_BUKRS").equals(reader.getName())){
                                
                                                object.setI_BUKRS(functions.rfc.sap.document.sap_com.Char4.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","I_DATE").equals(reader.getName())){
                                
                                                object.setI_DATE(functions.rfc.sap.document.sap_com.Date.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","I_EKORG").equals(reader.getName())){
                                
                                                object.setI_EKORG(functions.rfc.sap.document.sap_com.Char4.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","I_FLAG").equals(reader.getName())){
                                
                                                object.setI_FLAG(functions.rfc.sap.document.sap_com.Char1.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","I_IDIOMA").equals(reader.getName())){
                                
                                                object.setI_IDIOMA(functions.rfc.sap.document.sap_com.Char2.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","I_KIDNO").equals(reader.getName())){
                                
                                                object.setI_KIDNO(functions.rfc.sap.document.sap_com.Char30.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","I_LIFNR").equals(reader.getName())){
                                
                                                object.setI_LIFNR(functions.rfc.sap.document.sap_com.Char10.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","I_OPDF").equals(reader.getName())){
                                
                                            object.setI_OPDF(org.apache.axiom.util.stax.XMLStreamReaderUtils.getDataHandlerFromElement(reader));
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","I_PDF").equals(reader.getName())){
                                
                                            object.setI_PDF(org.apache.axiom.util.stax.XMLStreamReaderUtils.getDataHandlerFromElement(reader));
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","I_REFER").equals(reader.getName())){
                                
                                                object.setI_REFER(functions.rfc.sap.document.sap_com.ZTT_REFER.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","I_RETEN").equals(reader.getName())){
                                
                                                object.setI_RETEN(functions.rfc.sap.document.sap_com.Curr132.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","I_SUBTOTAL").equals(reader.getName())){
                                
                                                object.setI_SUBTOTAL(functions.rfc.sap.document.sap_com.Curr152.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","I_TAX").equals(reader.getName())){
                                
                                                object.setI_TAX(functions.rfc.sap.document.sap_com.Curr132.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","I_WAERS").equals(reader.getName())){
                                
                                                object.setI_WAERS(functions.rfc.sap.document.sap_com.Cuky5.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","I_XBLNR").equals(reader.getName())){
                                
                                                object.setI_XBLNR(functions.rfc.sap.document.sap_com.Char16.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","I_XML").equals(reader.getName())){
                                
                                            object.setI_XML(org.apache.axiom.util.stax.XMLStreamReaderUtils.getDataHandlerFromElement(reader));
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                  
                            while (!reader.isStartElement() && !reader.isEndElement())
                                reader.next();
                            
                                if (reader.isStartElement())
                                // A start element we are not expecting indicates a trailing invalid property
                                throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                            



            } catch (javax.xml.stream.XMLStreamException e) {
                throw new java.lang.Exception(e);
            }

            return object;
        }

        }//end of factory class

        

        }
           
    