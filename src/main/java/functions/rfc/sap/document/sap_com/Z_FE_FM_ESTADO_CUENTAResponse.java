
/**
 * Z_FE_FM_ESTADO_CUENTAResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:34:40 IST)
 */

            
                package functions.rfc.sap.document.sap_com;
            

            /**
            *  Z_FE_FM_ESTADO_CUENTAResponse bean class
            */
            @SuppressWarnings({"unchecked","unused"})
        
        public  class Z_FE_FM_ESTADO_CUENTAResponse
        implements org.apache.axis2.databinding.ADBBean{
        
                public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName(
                "urn:sap-com:document:sap:rfc:functions",
                "Z_FE_FM_ESTADO_CUENTAResponse",
                "ns1");

            

                        /**
                        * field for E_ID
                        */

                        
                                    protected functions.rfc.sap.document.sap_com.Char4 localE_ID ;
                                

                           /**
                           * Auto generated getter method
                           * @return functions.rfc.sap.document.sap_com.Char4
                           */
                           public  functions.rfc.sap.document.sap_com.Char4 getE_ID(){
                               return localE_ID;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param E_ID
                               */
                               public void setE_ID(functions.rfc.sap.document.sap_com.Char4 param){
                            
                                            this.localE_ID=param;
                                    

                               }
                            

                        /**
                        * field for E_LISTA_P_ABIERTA
                        */

                        
                                    protected functions.rfc.sap.document.sap_com.ZTT_ESTADO_CUENTA localE_LISTA_P_ABIERTA ;
                                

                           /**
                           * Auto generated getter method
                           * @return functions.rfc.sap.document.sap_com.ZTT_ESTADO_CUENTA
                           */
                           public  functions.rfc.sap.document.sap_com.ZTT_ESTADO_CUENTA getE_LISTA_P_ABIERTA(){
                               return localE_LISTA_P_ABIERTA;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param E_LISTA_P_ABIERTA
                               */
                               public void setE_LISTA_P_ABIERTA(functions.rfc.sap.document.sap_com.ZTT_ESTADO_CUENTA param){
                            
                                            this.localE_LISTA_P_ABIERTA=param;
                                    

                               }
                            

                        /**
                        * field for E_LISTA_P_COMPENSADA
                        */

                        
                                    protected functions.rfc.sap.document.sap_com.ZTT_ESTADO_CUENTA localE_LISTA_P_COMPENSADA ;
                                

                           /**
                           * Auto generated getter method
                           * @return functions.rfc.sap.document.sap_com.ZTT_ESTADO_CUENTA
                           */
                           public  functions.rfc.sap.document.sap_com.ZTT_ESTADO_CUENTA getE_LISTA_P_COMPENSADA(){
                               return localE_LISTA_P_COMPENSADA;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param E_LISTA_P_COMPENSADA
                               */
                               public void setE_LISTA_P_COMPENSADA(functions.rfc.sap.document.sap_com.ZTT_ESTADO_CUENTA param){
                            
                                            this.localE_LISTA_P_COMPENSADA=param;
                                    

                               }
                            

                        /**
                        * field for E_MSG
                        */

                        
                                    protected java.lang.String localE_MSG ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getE_MSG(){
                               return localE_MSG;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param E_MSG
                               */
                               public void setE_MSG(java.lang.String param){
                            
                                            this.localE_MSG=param;
                                    

                               }
                            

                        /**
                        * field for E_TYPE
                        */

                        
                                    protected functions.rfc.sap.document.sap_com.Char1 localE_TYPE ;
                                

                           /**
                           * Auto generated getter method
                           * @return functions.rfc.sap.document.sap_com.Char1
                           */
                           public  functions.rfc.sap.document.sap_com.Char1 getE_TYPE(){
                               return localE_TYPE;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param E_TYPE
                               */
                               public void setE_TYPE(functions.rfc.sap.document.sap_com.Char1 param){
                            
                                            this.localE_TYPE=param;
                                    

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
                           namespacePrefix+":Z_FE_FM_ESTADO_CUENTAResponse",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "Z_FE_FM_ESTADO_CUENTAResponse",
                           xmlWriter);
                   }

               
                   }
               
                                            if (localE_ID==null){
                                                 throw new org.apache.axis2.databinding.ADBException("E_ID cannot be null!!");
                                            }
                                           localE_ID.serialize(new javax.xml.namespace.QName("","E_ID"),
                                               xmlWriter);
                                        
                                            if (localE_LISTA_P_ABIERTA==null){
                                                 throw new org.apache.axis2.databinding.ADBException("E_LISTA_P_ABIERTA cannot be null!!");
                                            }
                                           localE_LISTA_P_ABIERTA.serialize(new javax.xml.namespace.QName("","E_LISTA_P_ABIERTA"),
                                               xmlWriter);
                                        
                                            if (localE_LISTA_P_COMPENSADA==null){
                                                 throw new org.apache.axis2.databinding.ADBException("E_LISTA_P_COMPENSADA cannot be null!!");
                                            }
                                           localE_LISTA_P_COMPENSADA.serialize(new javax.xml.namespace.QName("","E_LISTA_P_COMPENSADA"),
                                               xmlWriter);
                                        
                                    namespace = "";
                                    writeStartElement(null, namespace, "E_MSG", xmlWriter);
                             

                                          if (localE_MSG==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("E_MSG cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localE_MSG);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             
                                            if (localE_TYPE==null){
                                                 throw new org.apache.axis2.databinding.ADBException("E_TYPE cannot be null!!");
                                            }
                                           localE_TYPE.serialize(new javax.xml.namespace.QName("","E_TYPE"),
                                               xmlWriter);
                                        
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
                                                                      "E_ID"));
                            
                            
                                    if (localE_ID==null){
                                         throw new org.apache.axis2.databinding.ADBException("E_ID cannot be null!!");
                                    }
                                    elementList.add(localE_ID);
                                
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "E_LISTA_P_ABIERTA"));
                            
                            
                                    if (localE_LISTA_P_ABIERTA==null){
                                         throw new org.apache.axis2.databinding.ADBException("E_LISTA_P_ABIERTA cannot be null!!");
                                    }
                                    elementList.add(localE_LISTA_P_ABIERTA);
                                
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "E_LISTA_P_COMPENSADA"));
                            
                            
                                    if (localE_LISTA_P_COMPENSADA==null){
                                         throw new org.apache.axis2.databinding.ADBException("E_LISTA_P_COMPENSADA cannot be null!!");
                                    }
                                    elementList.add(localE_LISTA_P_COMPENSADA);
                                
                                      elementList.add(new javax.xml.namespace.QName("",
                                                                      "E_MSG"));
                                 
                                        if (localE_MSG != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localE_MSG));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("E_MSG cannot be null!!");
                                        }
                                    
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "E_TYPE"));
                            
                            
                                    if (localE_TYPE==null){
                                         throw new org.apache.axis2.databinding.ADBException("E_TYPE cannot be null!!");
                                    }
                                    elementList.add(localE_TYPE);
                                

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
        public static Z_FE_FM_ESTADO_CUENTAResponse parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            Z_FE_FM_ESTADO_CUENTAResponse object =
                new Z_FE_FM_ESTADO_CUENTAResponse();

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
                    
                            if (!"Z_FE_FM_ESTADO_CUENTAResponse".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (Z_FE_FM_ESTADO_CUENTAResponse)functions.rfc.sap.document.sap_com.ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                
                    
                    reader.next();
                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","E_ID").equals(reader.getName())){
                                
                                                object.setE_ID(functions.rfc.sap.document.sap_com.Char4.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","E_LISTA_P_ABIERTA").equals(reader.getName())){
                                
                                                object.setE_LISTA_P_ABIERTA(functions.rfc.sap.document.sap_com.ZTT_ESTADO_CUENTA.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","E_LISTA_P_COMPENSADA").equals(reader.getName())){
                                
                                                object.setE_LISTA_P_COMPENSADA(functions.rfc.sap.document.sap_com.ZTT_ESTADO_CUENTA.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","E_MSG").equals(reader.getName())){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"E_MSG" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setE_MSG(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","E_TYPE").equals(reader.getName())){
                                
                                                object.setE_TYPE(functions.rfc.sap.document.sap_com.Char1.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
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
           
    