
/**
 * Z_FE_FM_ORDEN_FACTURA.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:34:40 IST)
 */

            
                package functions.rfc.sap.document.sap_com;
            

            /**
            *  Z_FE_FM_ORDEN_FACTURA bean class
            */
            @SuppressWarnings({"unchecked","unused"})
        
        public  class Z_FE_FM_ORDEN_FACTURA
        implements org.apache.axis2.databinding.ADBBean{
        
                public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName(
                "urn:sap-com:document:sap:rfc:functions",
                "Z_FE_FM_ORDEN_FACTURA",
                "ns1");

            

                        /**
                        * field for I_AEDAT
                        */

                        
                                    protected functions.rfc.sap.document.sap_com.BAPISFLDRA localI_AEDAT ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localI_AEDATTracker = false ;

                           public boolean isI_AEDATSpecified(){
                               return localI_AEDATTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return functions.rfc.sap.document.sap_com.BAPISFLDRA
                           */
                           public  functions.rfc.sap.document.sap_com.BAPISFLDRA getI_AEDAT(){
                               return localI_AEDAT;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param I_AEDAT
                               */
                               public void setI_AEDAT(functions.rfc.sap.document.sap_com.BAPISFLDRA param){
                            localI_AEDATTracker = param != null;
                                   
                                            this.localI_AEDAT=param;
                                    

                               }
                            

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
                        * field for I_EBELN
                        */

                        
                                    protected functions.rfc.sap.document.sap_com.Char10 localI_EBELN ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localI_EBELNTracker = false ;

                           public boolean isI_EBELNSpecified(){
                               return localI_EBELNTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return functions.rfc.sap.document.sap_com.Char10
                           */
                           public  functions.rfc.sap.document.sap_com.Char10 getI_EBELN(){
                               return localI_EBELN;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param I_EBELN
                               */
                               public void setI_EBELN(functions.rfc.sap.document.sap_com.Char10 param){
                            localI_EBELNTracker = param != null;
                                   
                                            this.localI_EBELN=param;
                                    

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
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localI_FLAGTracker = false ;

                           public boolean isI_FLAGSpecified(){
                               return localI_FLAGTracker;
                           }

                           

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
                            localI_FLAGTracker = param != null;
                                   
                                            this.localI_FLAG=param;
                                    

                               }
                            

                        /**
                        * field for I_FOLIO
                        */

                        
                                    protected functions.rfc.sap.document.sap_com.Numeric10 localI_FOLIO ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localI_FOLIOTracker = false ;

                           public boolean isI_FOLIOSpecified(){
                               return localI_FOLIOTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return functions.rfc.sap.document.sap_com.Numeric10
                           */
                           public  functions.rfc.sap.document.sap_com.Numeric10 getI_FOLIO(){
                               return localI_FOLIO;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param I_FOLIO
                               */
                               public void setI_FOLIO(functions.rfc.sap.document.sap_com.Numeric10 param){
                            localI_FOLIOTracker = param != null;
                                   
                                            this.localI_FOLIO=param;
                                    

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
                        * field for I_IMPORTACION
                        */

                        
                                    protected functions.rfc.sap.document.sap_com.Char10 localI_IMPORTACION ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localI_IMPORTACIONTracker = false ;

                           public boolean isI_IMPORTACIONSpecified(){
                               return localI_IMPORTACIONTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return functions.rfc.sap.document.sap_com.Char10
                           */
                           public  functions.rfc.sap.document.sap_com.Char10 getI_IMPORTACION(){
                               return localI_IMPORTACION;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param I_IMPORTACION
                               */
                               public void setI_IMPORTACION(functions.rfc.sap.document.sap_com.Char10 param){
                            localI_IMPORTACIONTracker = param != null;
                                   
                                            this.localI_IMPORTACION=param;
                                    

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
                        * field for I_ORDENCOMPRA
                        */

                        
                                    protected functions.rfc.sap.document.sap_com.ZTT_ORDENCOMPRA localI_ORDENCOMPRA ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localI_ORDENCOMPRATracker = false ;

                           public boolean isI_ORDENCOMPRASpecified(){
                               return localI_ORDENCOMPRATracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return functions.rfc.sap.document.sap_com.ZTT_ORDENCOMPRA
                           */
                           public  functions.rfc.sap.document.sap_com.ZTT_ORDENCOMPRA getI_ORDENCOMPRA(){
                               return localI_ORDENCOMPRA;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param I_ORDENCOMPRA
                               */
                               public void setI_ORDENCOMPRA(functions.rfc.sap.document.sap_com.ZTT_ORDENCOMPRA param){
                            localI_ORDENCOMPRATracker = param != null;
                                   
                                            this.localI_ORDENCOMPRA=param;
                                    

                               }
                            

                        /**
                        * field for I_TIPO
                        */

                        
                                    protected functions.rfc.sap.document.sap_com.Char1 localI_TIPO ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localI_TIPOTracker = false ;

                           public boolean isI_TIPOSpecified(){
                               return localI_TIPOTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return functions.rfc.sap.document.sap_com.Char1
                           */
                           public  functions.rfc.sap.document.sap_com.Char1 getI_TIPO(){
                               return localI_TIPO;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param I_TIPO
                               */
                               public void setI_TIPO(functions.rfc.sap.document.sap_com.Char1 param){
                            localI_TIPOTracker = param != null;
                                   
                                            this.localI_TIPO=param;
                                    

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
                           namespacePrefix+":Z_FE_FM_ORDEN_FACTURA",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "Z_FE_FM_ORDEN_FACTURA",
                           xmlWriter);
                   }

               
                   }
                if (localI_AEDATTracker){
                                            if (localI_AEDAT==null){
                                                 throw new org.apache.axis2.databinding.ADBException("I_AEDAT cannot be null!!");
                                            }
                                           localI_AEDAT.serialize(new javax.xml.namespace.QName("","I_AEDAT"),
                                               xmlWriter);
                                        }
                                            if (localI_BUKRS==null){
                                                 throw new org.apache.axis2.databinding.ADBException("I_BUKRS cannot be null!!");
                                            }
                                           localI_BUKRS.serialize(new javax.xml.namespace.QName("","I_BUKRS"),
                                               xmlWriter);
                                         if (localI_EBELNTracker){
                                            if (localI_EBELN==null){
                                                 throw new org.apache.axis2.databinding.ADBException("I_EBELN cannot be null!!");
                                            }
                                           localI_EBELN.serialize(new javax.xml.namespace.QName("","I_EBELN"),
                                               xmlWriter);
                                        } if (localI_EKORGTracker){
                                            if (localI_EKORG==null){
                                                 throw new org.apache.axis2.databinding.ADBException("I_EKORG cannot be null!!");
                                            }
                                           localI_EKORG.serialize(new javax.xml.namespace.QName("","I_EKORG"),
                                               xmlWriter);
                                        } if (localI_FLAGTracker){
                                            if (localI_FLAG==null){
                                                 throw new org.apache.axis2.databinding.ADBException("I_FLAG cannot be null!!");
                                            }
                                           localI_FLAG.serialize(new javax.xml.namespace.QName("","I_FLAG"),
                                               xmlWriter);
                                        } if (localI_FOLIOTracker){
                                            if (localI_FOLIO==null){
                                                 throw new org.apache.axis2.databinding.ADBException("I_FOLIO cannot be null!!");
                                            }
                                           localI_FOLIO.serialize(new javax.xml.namespace.QName("","I_FOLIO"),
                                               xmlWriter);
                                        } if (localI_IDIOMATracker){
                                            if (localI_IDIOMA==null){
                                                 throw new org.apache.axis2.databinding.ADBException("I_IDIOMA cannot be null!!");
                                            }
                                           localI_IDIOMA.serialize(new javax.xml.namespace.QName("","I_IDIOMA"),
                                               xmlWriter);
                                        } if (localI_IMPORTACIONTracker){
                                            if (localI_IMPORTACION==null){
                                                 throw new org.apache.axis2.databinding.ADBException("I_IMPORTACION cannot be null!!");
                                            }
                                           localI_IMPORTACION.serialize(new javax.xml.namespace.QName("","I_IMPORTACION"),
                                               xmlWriter);
                                        }
                                            if (localI_LIFNR==null){
                                                 throw new org.apache.axis2.databinding.ADBException("I_LIFNR cannot be null!!");
                                            }
                                           localI_LIFNR.serialize(new javax.xml.namespace.QName("","I_LIFNR"),
                                               xmlWriter);
                                         if (localI_ORDENCOMPRATracker){
                                            if (localI_ORDENCOMPRA==null){
                                                 throw new org.apache.axis2.databinding.ADBException("I_ORDENCOMPRA cannot be null!!");
                                            }
                                           localI_ORDENCOMPRA.serialize(new javax.xml.namespace.QName("","I_ORDENCOMPRA"),
                                               xmlWriter);
                                        } if (localI_TIPOTracker){
                                            if (localI_TIPO==null){
                                                 throw new org.apache.axis2.databinding.ADBException("I_TIPO cannot be null!!");
                                            }
                                           localI_TIPO.serialize(new javax.xml.namespace.QName("","I_TIPO"),
                                               xmlWriter);
                                        } if (localI_WAERSTracker){
                                            if (localI_WAERS==null){
                                                 throw new org.apache.axis2.databinding.ADBException("I_WAERS cannot be null!!");
                                            }
                                           localI_WAERS.serialize(new javax.xml.namespace.QName("","I_WAERS"),
                                               xmlWriter);
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

                 if (localI_AEDATTracker){
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "I_AEDAT"));
                            
                            
                                    if (localI_AEDAT==null){
                                         throw new org.apache.axis2.databinding.ADBException("I_AEDAT cannot be null!!");
                                    }
                                    elementList.add(localI_AEDAT);
                                }
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "I_BUKRS"));
                            
                            
                                    if (localI_BUKRS==null){
                                         throw new org.apache.axis2.databinding.ADBException("I_BUKRS cannot be null!!");
                                    }
                                    elementList.add(localI_BUKRS);
                                 if (localI_EBELNTracker){
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "I_EBELN"));
                            
                            
                                    if (localI_EBELN==null){
                                         throw new org.apache.axis2.databinding.ADBException("I_EBELN cannot be null!!");
                                    }
                                    elementList.add(localI_EBELN);
                                } if (localI_EKORGTracker){
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "I_EKORG"));
                            
                            
                                    if (localI_EKORG==null){
                                         throw new org.apache.axis2.databinding.ADBException("I_EKORG cannot be null!!");
                                    }
                                    elementList.add(localI_EKORG);
                                } if (localI_FLAGTracker){
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "I_FLAG"));
                            
                            
                                    if (localI_FLAG==null){
                                         throw new org.apache.axis2.databinding.ADBException("I_FLAG cannot be null!!");
                                    }
                                    elementList.add(localI_FLAG);
                                } if (localI_FOLIOTracker){
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "I_FOLIO"));
                            
                            
                                    if (localI_FOLIO==null){
                                         throw new org.apache.axis2.databinding.ADBException("I_FOLIO cannot be null!!");
                                    }
                                    elementList.add(localI_FOLIO);
                                } if (localI_IDIOMATracker){
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "I_IDIOMA"));
                            
                            
                                    if (localI_IDIOMA==null){
                                         throw new org.apache.axis2.databinding.ADBException("I_IDIOMA cannot be null!!");
                                    }
                                    elementList.add(localI_IDIOMA);
                                } if (localI_IMPORTACIONTracker){
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "I_IMPORTACION"));
                            
                            
                                    if (localI_IMPORTACION==null){
                                         throw new org.apache.axis2.databinding.ADBException("I_IMPORTACION cannot be null!!");
                                    }
                                    elementList.add(localI_IMPORTACION);
                                }
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "I_LIFNR"));
                            
                            
                                    if (localI_LIFNR==null){
                                         throw new org.apache.axis2.databinding.ADBException("I_LIFNR cannot be null!!");
                                    }
                                    elementList.add(localI_LIFNR);
                                 if (localI_ORDENCOMPRATracker){
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "I_ORDENCOMPRA"));
                            
                            
                                    if (localI_ORDENCOMPRA==null){
                                         throw new org.apache.axis2.databinding.ADBException("I_ORDENCOMPRA cannot be null!!");
                                    }
                                    elementList.add(localI_ORDENCOMPRA);
                                } if (localI_TIPOTracker){
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "I_TIPO"));
                            
                            
                                    if (localI_TIPO==null){
                                         throw new org.apache.axis2.databinding.ADBException("I_TIPO cannot be null!!");
                                    }
                                    elementList.add(localI_TIPO);
                                } if (localI_WAERSTracker){
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "I_WAERS"));
                            
                            
                                    if (localI_WAERS==null){
                                         throw new org.apache.axis2.databinding.ADBException("I_WAERS cannot be null!!");
                                    }
                                    elementList.add(localI_WAERS);
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
        public static Z_FE_FM_ORDEN_FACTURA parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            Z_FE_FM_ORDEN_FACTURA object =
                new Z_FE_FM_ORDEN_FACTURA();

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
                    
                            if (!"Z_FE_FM_ORDEN_FACTURA".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (Z_FE_FM_ORDEN_FACTURA)functions.rfc.sap.document.sap_com.ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                
                    
                    reader.next();
                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","I_AEDAT").equals(reader.getName())){
                                
                                                object.setI_AEDAT(functions.rfc.sap.document.sap_com.BAPISFLDRA.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
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
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","I_EBELN").equals(reader.getName())){
                                
                                                object.setI_EBELN(functions.rfc.sap.document.sap_com.Char10.Factory.parse(reader));
                                              
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
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","I_FOLIO").equals(reader.getName())){
                                
                                                object.setI_FOLIO(functions.rfc.sap.document.sap_com.Numeric10.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","I_IDIOMA").equals(reader.getName())){
                                
                                                object.setI_IDIOMA(functions.rfc.sap.document.sap_com.Char2.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","I_IMPORTACION").equals(reader.getName())){
                                
                                                object.setI_IMPORTACION(functions.rfc.sap.document.sap_com.Char10.Factory.parse(reader));
                                              
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
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","I_ORDENCOMPRA").equals(reader.getName())){
                                
                                                object.setI_ORDENCOMPRA(functions.rfc.sap.document.sap_com.ZTT_ORDENCOMPRA.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","I_TIPO").equals(reader.getName())){
                                
                                                object.setI_TIPO(functions.rfc.sap.document.sap_com.Char1.Factory.parse(reader));
                                              
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
           
    