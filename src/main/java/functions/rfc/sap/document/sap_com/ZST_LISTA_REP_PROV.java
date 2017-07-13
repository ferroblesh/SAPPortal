
/**
 * ZST_LISTA_REP_PROV.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:34:40 IST)
 */

            
                package functions.rfc.sap.document.sap_com;
            

            /**
            *  ZST_LISTA_REP_PROV bean class
            */
            @SuppressWarnings({"unchecked","unused"})
        
        public  class ZST_LISTA_REP_PROV
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = ZST_LISTA_REP_PROV
                Namespace URI = urn:sap-com:document:sap:rfc:functions
                Namespace Prefix = ns1
                */
            

                        /**
                        * field for IDGRUREPO
                        */

                        
                                    protected functions.rfc.sap.document.sap_com.Char10 localIDGRUREPO ;
                                

                           /**
                           * Auto generated getter method
                           * @return functions.rfc.sap.document.sap_com.Char10
                           */
                           public  functions.rfc.sap.document.sap_com.Char10 getIDGRUREPO(){
                               return localIDGRUREPO;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param IDGRUREPO
                               */
                               public void setIDGRUREPO(functions.rfc.sap.document.sap_com.Char10 param){
                            
                                            this.localIDGRUREPO=param;
                                    

                               }
                            

                        /**
                        * field for IDREPO
                        */

                        
                                    protected functions.rfc.sap.document.sap_com.Char10 localIDREPO ;
                                

                           /**
                           * Auto generated getter method
                           * @return functions.rfc.sap.document.sap_com.Char10
                           */
                           public  functions.rfc.sap.document.sap_com.Char10 getIDREPO(){
                               return localIDREPO;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param IDREPO
                               */
                               public void setIDREPO(functions.rfc.sap.document.sap_com.Char10 param){
                            
                                            this.localIDREPO=param;
                                    

                               }
                            

                        /**
                        * field for UBIREPO
                        */

                        
                                    protected functions.rfc.sap.document.sap_com.Char200 localUBIREPO ;
                                

                           /**
                           * Auto generated getter method
                           * @return functions.rfc.sap.document.sap_com.Char200
                           */
                           public  functions.rfc.sap.document.sap_com.Char200 getUBIREPO(){
                               return localUBIREPO;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param UBIREPO
                               */
                               public void setUBIREPO(functions.rfc.sap.document.sap_com.Char200 param){
                            
                                            this.localUBIREPO=param;
                                    

                               }
                            

                        /**
                        * field for NOMBREREPO
                        */

                        
                                    protected functions.rfc.sap.document.sap_com.Char40 localNOMBREREPO ;
                                

                           /**
                           * Auto generated getter method
                           * @return functions.rfc.sap.document.sap_com.Char40
                           */
                           public  functions.rfc.sap.document.sap_com.Char40 getNOMBREREPO(){
                               return localNOMBREREPO;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param NOMBREREPO
                               */
                               public void setNOMBREREPO(functions.rfc.sap.document.sap_com.Char40 param){
                            
                                            this.localNOMBREREPO=param;
                                    

                               }
                            

                        /**
                        * field for ACTIVO
                        */

                        
                                    protected functions.rfc.sap.document.sap_com.Char1 localACTIVO ;
                                

                           /**
                           * Auto generated getter method
                           * @return functions.rfc.sap.document.sap_com.Char1
                           */
                           public  functions.rfc.sap.document.sap_com.Char1 getACTIVO(){
                               return localACTIVO;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ACTIVO
                               */
                               public void setACTIVO(functions.rfc.sap.document.sap_com.Char1 param){
                            
                                            this.localACTIVO=param;
                                    

                               }
                            

                        /**
                        * field for CARPETA
                        */

                        
                                    protected functions.rfc.sap.document.sap_com.Char20 localCARPETA ;
                                

                           /**
                           * Auto generated getter method
                           * @return functions.rfc.sap.document.sap_com.Char20
                           */
                           public  functions.rfc.sap.document.sap_com.Char20 getCARPETA(){
                               return localCARPETA;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param CARPETA
                               */
                               public void setCARPETA(functions.rfc.sap.document.sap_com.Char20 param){
                            
                                            this.localCARPETA=param;
                                    

                               }
                            

                        /**
                        * field for SUBCARPETA
                        */

                        
                                    protected functions.rfc.sap.document.sap_com.Char20 localSUBCARPETA ;
                                

                           /**
                           * Auto generated getter method
                           * @return functions.rfc.sap.document.sap_com.Char20
                           */
                           public  functions.rfc.sap.document.sap_com.Char20 getSUBCARPETA(){
                               return localSUBCARPETA;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param SUBCARPETA
                               */
                               public void setSUBCARPETA(functions.rfc.sap.document.sap_com.Char20 param){
                            
                                            this.localSUBCARPETA=param;
                                    

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
                       new org.apache.axis2.databinding.ADBDataSource(this,parentQName);
               return factory.createOMElement(dataSource,parentQName);
            
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
                           namespacePrefix+":ZST_LISTA_REP_PROV",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "ZST_LISTA_REP_PROV",
                           xmlWriter);
                   }

               
                   }
               
                                            if (localIDGRUREPO==null){
                                                 throw new org.apache.axis2.databinding.ADBException("IDGRUREPO cannot be null!!");
                                            }
                                           localIDGRUREPO.serialize(new javax.xml.namespace.QName("","IDGRUREPO"),
                                               xmlWriter);
                                        
                                            if (localIDREPO==null){
                                                 throw new org.apache.axis2.databinding.ADBException("IDREPO cannot be null!!");
                                            }
                                           localIDREPO.serialize(new javax.xml.namespace.QName("","IDREPO"),
                                               xmlWriter);
                                        
                                            if (localUBIREPO==null){
                                                 throw new org.apache.axis2.databinding.ADBException("UBIREPO cannot be null!!");
                                            }
                                           localUBIREPO.serialize(new javax.xml.namespace.QName("","UBIREPO"),
                                               xmlWriter);
                                        
                                            if (localNOMBREREPO==null){
                                                 throw new org.apache.axis2.databinding.ADBException("NOMBREREPO cannot be null!!");
                                            }
                                           localNOMBREREPO.serialize(new javax.xml.namespace.QName("","NOMBREREPO"),
                                               xmlWriter);
                                        
                                            if (localACTIVO==null){
                                                 throw new org.apache.axis2.databinding.ADBException("ACTIVO cannot be null!!");
                                            }
                                           localACTIVO.serialize(new javax.xml.namespace.QName("","ACTIVO"),
                                               xmlWriter);
                                        
                                            if (localCARPETA==null){
                                                 throw new org.apache.axis2.databinding.ADBException("CARPETA cannot be null!!");
                                            }
                                           localCARPETA.serialize(new javax.xml.namespace.QName("","CARPETA"),
                                               xmlWriter);
                                        
                                            if (localSUBCARPETA==null){
                                                 throw new org.apache.axis2.databinding.ADBException("SUBCARPETA cannot be null!!");
                                            }
                                           localSUBCARPETA.serialize(new javax.xml.namespace.QName("","SUBCARPETA"),
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
                                                                      "IDGRUREPO"));
                            
                            
                                    if (localIDGRUREPO==null){
                                         throw new org.apache.axis2.databinding.ADBException("IDGRUREPO cannot be null!!");
                                    }
                                    elementList.add(localIDGRUREPO);
                                
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "IDREPO"));
                            
                            
                                    if (localIDREPO==null){
                                         throw new org.apache.axis2.databinding.ADBException("IDREPO cannot be null!!");
                                    }
                                    elementList.add(localIDREPO);
                                
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "UBIREPO"));
                            
                            
                                    if (localUBIREPO==null){
                                         throw new org.apache.axis2.databinding.ADBException("UBIREPO cannot be null!!");
                                    }
                                    elementList.add(localUBIREPO);
                                
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "NOMBREREPO"));
                            
                            
                                    if (localNOMBREREPO==null){
                                         throw new org.apache.axis2.databinding.ADBException("NOMBREREPO cannot be null!!");
                                    }
                                    elementList.add(localNOMBREREPO);
                                
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "ACTIVO"));
                            
                            
                                    if (localACTIVO==null){
                                         throw new org.apache.axis2.databinding.ADBException("ACTIVO cannot be null!!");
                                    }
                                    elementList.add(localACTIVO);
                                
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "CARPETA"));
                            
                            
                                    if (localCARPETA==null){
                                         throw new org.apache.axis2.databinding.ADBException("CARPETA cannot be null!!");
                                    }
                                    elementList.add(localCARPETA);
                                
                            elementList.add(new javax.xml.namespace.QName("",
                                                                      "SUBCARPETA"));
                            
                            
                                    if (localSUBCARPETA==null){
                                         throw new org.apache.axis2.databinding.ADBException("SUBCARPETA cannot be null!!");
                                    }
                                    elementList.add(localSUBCARPETA);
                                

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
        public static ZST_LISTA_REP_PROV parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            ZST_LISTA_REP_PROV object =
                new ZST_LISTA_REP_PROV();

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
                    
                            if (!"ZST_LISTA_REP_PROV".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (ZST_LISTA_REP_PROV)functions.rfc.sap.document.sap_com.ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                
                    
                    reader.next();
                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","IDGRUREPO").equals(reader.getName())){
                                
                                                object.setIDGRUREPO(functions.rfc.sap.document.sap_com.Char10.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","IDREPO").equals(reader.getName())){
                                
                                                object.setIDREPO(functions.rfc.sap.document.sap_com.Char10.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","UBIREPO").equals(reader.getName())){
                                
                                                object.setUBIREPO(functions.rfc.sap.document.sap_com.Char200.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","NOMBREREPO").equals(reader.getName())){
                                
                                                object.setNOMBREREPO(functions.rfc.sap.document.sap_com.Char40.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","ACTIVO").equals(reader.getName())){
                                
                                                object.setACTIVO(functions.rfc.sap.document.sap_com.Char1.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","CARPETA").equals(reader.getName())){
                                
                                                object.setCARPETA(functions.rfc.sap.document.sap_com.Char20.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","SUBCARPETA").equals(reader.getName())){
                                
                                                object.setSUBCARPETA(functions.rfc.sap.document.sap_com.Char20.Factory.parse(reader));
                                              
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
           
    