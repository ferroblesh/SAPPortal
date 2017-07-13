
/**
 * Z_FE_FM_PACKING_LIST.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:34:40 IST)
 */

            
                package functions.rfc.sap.document.sap_com;
            

            /**
            *  Z_FE_FM_PACKING_LIST bean class
            */
            @SuppressWarnings({"unchecked","unused"})
        
        public  class Z_FE_FM_PACKING_LIST
        implements org.apache.axis2.databinding.ADBBean{
        
                public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName(
                "urn:sap-com:document:sap:rfc:functions",
                "Z_FE_FM_PACKING_LIST",
                "ns1");

            

                        /**
                        * field for PI_EVELN
                        */

                        
                                    protected functions.rfc.sap.document.sap_com.Char10 localPI_EVELN ;
                                

                           /**
                           * Auto generated getter method
                           * @return functions.rfc.sap.document.sap_com.Char10
                           */
                           public  functions.rfc.sap.document.sap_com.Char10 getPI_EVELN(){
                               return localPI_EVELN;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param PI_EVELN
                               */
                               public void setPI_EVELN(functions.rfc.sap.document.sap_com.Char10 param){
                            
                                            this.localPI_EVELN=param;
                                    

                               }
                            

                        /**
                        * field for PI_IDIOMA
                        */

                        
                                    protected functions.rfc.sap.document.sap_com.Lang localPI_IDIOMA ;
                                

                           /**
                           * Auto generated getter method
                           * @return functions.rfc.sap.document.sap_com.Lang
                           */
                           public  functions.rfc.sap.document.sap_com.Lang getPI_IDIOMA(){
                               return localPI_IDIOMA;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param PI_IDIOMA
                               */
                               public void setPI_IDIOMA(functions.rfc.sap.document.sap_com.Lang param){
                            
                                            this.localPI_IDIOMA=param;
                                    

                               }
                            

                        /**
                        * field for PI_INDICADOR
                        */

                        
                                    protected functions.rfc.sap.document.sap_com.Char1 localPI_INDICADOR ;
                                

                           /**
                           * Auto generated getter method
                           * @return functions.rfc.sap.document.sap_com.Char1
                           */
                           public  functions.rfc.sap.document.sap_com.Char1 getPI_INDICADOR(){
                               return localPI_INDICADOR;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param PI_INDICADOR
                               */
                               public void setPI_INDICADOR(functions.rfc.sap.document.sap_com.Char1 param){
                            
                                            this.localPI_INDICADOR=param;
                                    

                               }
                            

                        /**
                        * field for PI_LIFNR
                        */

                        
                                    protected functions.rfc.sap.document.sap_com.Char10 localPI_LIFNR ;
                                

                           /**
                           * Auto generated getter method
                           * @return functions.rfc.sap.document.sap_com.Char10
                           */
                           public  functions.rfc.sap.document.sap_com.Char10 getPI_LIFNR(){
                               return localPI_LIFNR;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param PI_LIFNR
                               */
                               public void setPI_LIFNR(functions.rfc.sap.document.sap_com.Char10 param){
                            
                                            this.localPI_LIFNR=param;
                                    

                               }
                            

                        /**
                        * field for PT_PACKING
                        */

                        
                                    protected functions.rfc.sap.document.sap_com.ZTTPACKINGLIST localPT_PACKING ;
                                

                           /**
                           * Auto generated getter method
                           * @return functions.rfc.sap.document.sap_com.ZTTPACKINGLIST
                           */
                           public  functions.rfc.sap.document.sap_com.ZTTPACKINGLIST getPT_PACKING(){
                               return localPT_PACKING;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param PT_PACKING
                               */
                               public void setPT_PACKING(functions.rfc.sap.document.sap_com.ZTTPACKINGLIST param){
                            
                                            this.localPT_PACKING=param;
                                    

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
                           namespacePrefix+":Z_FE_FM_PACKING_LIST",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "Z_FE_FM_PACKING_LIST",
                           xmlWriter);
                   }

               
                   }
               
                                            if (localPI_EVELN==null){
                                                 throw new org.apache.axis2.databinding.ADBException("PI_EVELN cannot be null!!");
                                            }
                                           localPI_EVELN.serialize(new javax.xml.namespace.QName("","PI_EVELN"),
                                               xmlWriter);
                                        
                                            if (localPI_IDIOMA==null){
                                                 throw new org.apache.axis2.databinding.ADBException("PI_IDIOMA cannot be null!!");
                                            }
                                           localPI_IDIOMA.serialize(new javax.xml.namespace.QName("","PI_IDIOMA"),
                                               xmlWriter);
                                        
                                            if (localPI_INDICADOR==null){
                                                 throw new org.apache.axis2.databinding.ADBException("PI_INDICADOR cannot be null!!");
                                            }
                                           localPI_INDICADOR.serialize(new javax.xml.namespace.QName("","PI_INDICADOR"),
                                               xmlWriter);
                                        
                                            if (localPI_LIFNR==null){
                                                 throw new org.apache.axis2.databinding.ADBException("PI_LIFNR cannot be null!!");
                                            }
                                           localPI_LIFNR.serialize(new javax.xml.namespace.QName("","PI_LIFNR"),
                                               xmlWriter);
                                        
                                            if (localPT_PACKING==null){
                                                 throw new org.apache.axis2.databinding.ADBException("PT_PACKING cannot be null!!");
                                            }
                                           localPT_PACKING.serialize(new javax.xml.namespace.QName("","PT_PACKING"),
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
                                                                      "PI_EVELN"));
                            
                            
                                    if (localPI_EVELN==null){
                                         throw new org.apache.axis2.databinding.ADBException("PI_EVELN cannot be null!!");
                                    }
                                    elementList.add(localPI_EVELN);
                                
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "PI_IDIOMA"));
                            
                            
                                    if (localPI_IDIOMA==null){
                                         throw new org.apache.axis2.databinding.ADBException("PI_IDIOMA cannot be null!!");
                                    }
                                    elementList.add(localPI_IDIOMA);
                                
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "PI_INDICADOR"));
                            
                            
                                    if (localPI_INDICADOR==null){
                                         throw new org.apache.axis2.databinding.ADBException("PI_INDICADOR cannot be null!!");
                                    }
                                    elementList.add(localPI_INDICADOR);
                                
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "PI_LIFNR"));
                            
                            
                                    if (localPI_LIFNR==null){
                                         throw new org.apache.axis2.databinding.ADBException("PI_LIFNR cannot be null!!");
                                    }
                                    elementList.add(localPI_LIFNR);
                                
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "PT_PACKING"));
                            
                            
                                    if (localPT_PACKING==null){
                                         throw new org.apache.axis2.databinding.ADBException("PT_PACKING cannot be null!!");
                                    }
                                    elementList.add(localPT_PACKING);
                                

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
        public static Z_FE_FM_PACKING_LIST parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            Z_FE_FM_PACKING_LIST object =
                new Z_FE_FM_PACKING_LIST();

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
                    
                            if (!"Z_FE_FM_PACKING_LIST".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (Z_FE_FM_PACKING_LIST)functions.rfc.sap.document.sap_com.ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                
                    
                    reader.next();
                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","PI_EVELN").equals(reader.getName())){
                                
                                                object.setPI_EVELN(functions.rfc.sap.document.sap_com.Char10.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","PI_IDIOMA").equals(reader.getName())){
                                
                                                object.setPI_IDIOMA(functions.rfc.sap.document.sap_com.Lang.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","PI_INDICADOR").equals(reader.getName())){
                                
                                                object.setPI_INDICADOR(functions.rfc.sap.document.sap_com.Char1.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","PI_LIFNR").equals(reader.getName())){
                                
                                                object.setPI_LIFNR(functions.rfc.sap.document.sap_com.Char10.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","PT_PACKING").equals(reader.getName())){
                                
                                                object.setPT_PACKING(functions.rfc.sap.document.sap_com.ZTTPACKINGLIST.Factory.parse(reader));
                                              
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
           
    