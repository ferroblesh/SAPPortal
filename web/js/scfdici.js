var total;
var razonSocialStore = new Ext.data.JsonStore({		
	root: 'data',
	fields: ['bukrs','butxt'],
	autoLoad: true,
	proxy: new Ext.data.HttpProxy({ url: contextrootpath + '/provider/razonSocial.action', method: 'GET' })
});	

var divisionStore = new Ext.data.JsonStore({
	root: 'data',
	fields: ['segment','name'],
	autoLoad: false,
	proxy: new Ext.data.HttpProxy({ url: contextrootpath + '/provider/division.action', method: 'GET' })
});

var listaFacturaStore = new Ext.data.JsonStore({
	root: 'data',
	fields: [
	         {name: 'ebeln'},
	         {name: 'ebelp'},
	         {name: 'mblnr'},
	         {name: 'aedat'},
	         {name: 'matnr'},
	         {name: 'meins'},
	         {name: 'txz01'},
	         {name: 'menge'},
	         {name: 'zmenge'},
	         {name: 'netwr'},
	         {name: 'waers'},
	         {name: 'punit'},
	         {name: 'invoiceDocItem'},
	     	 {name: 'poNumber'},
	     	 {name: 'poItem'},
	     	 {name: 'refDoc'},
	     	 {name: 'refDocYear'},
	     	 {name: 'refDocIt'},
	     	 {name: 'itemAmount'},
	     	 {name: 'quantity'},
	     	 {name: 'poUnit'},
	     	 {name: 'poPrQnt'},
	     	 {name: 'poPrUom'},
	     	 {name: 'condType'},
	     	 {name: 'sheetNo'},
	     	 {name: 'sheetItem'},
	         {name: 'select', type:'bool'}
    ],
	autoLoad: false,
	proxy: new Ext.data.HttpProxy({ url: contextrootpath + '/provider/listPendingInvoice.action', method: 'GET' }),
	listeners: {
		exception : utilities.handleException
	}
});

var listaFacturaStoreD = new Ext.data.JsonStore({
	root: 'data',
	fields: [
	         {name: 'ebeln'},
	         {name: 'ebelp'},
	         {name: 'mblnr'},
	         {name: 'aedat'},
	         {name: 'matnr'},
	         {name: 'meins'},
	         {name: 'txz01'},
	         {name: 'menge'},
	         {name: 'zmenge'},
	         {name: 'netwr'},
	         {name: 'waers'},
	         {name: 'punit'},
	         {name: 'invoiceDocItem'},
	     	 {name: 'poNumber'},
	     	 {name: 'poItem'},
	     	 {name: 'refDoc'},
	     	 {name: 'refDocYear'},
	     	 {name: 'refDocIt'},
	     	 {name: 'itemAmount'},
	     	 {name: 'quantity'},
	     	 {name: 'poUnit'},
	     	 {name: 'poPrQnt'},
	     	 {name: 'poPrUom'},
	     	 {name: 'condType'},
	     	 {name: 'sheetNo'},
	     	 {name: 'sheetItem'},
	         {name: 'select', type:'bool'}
    ],
	autoLoad: false,
	proxy: new Ext.data.HttpProxy({ url: contextrootpath + '/provider/listPendingInvoice.action', method: 'GET' }),
	listeners: {
		exception : utilities.handleException
	}
});

Ext.onReady(function(){
	
	Ext.QuickTips.init();
	getUnreadNotificationsNumber();
	
	Ext.form.Field.prototype.msgTarget = 'side';
	var lang;
	var params = Ext.urlDecode(window.location.search.substring(1));
	lang = params.language ? params.language : 'es';
	bundle = new Ext.i18n.Bundle({bundle:'messages', path: contextrootpath + '/resources/language', lang: lang});
	bundle.onReady(function(){
		
		var razonSocialField = new Ext.form.ComboBox({
			triggerAction: 'all',
			id: 'cmbRazonSocial',
			name: 'cmbRazonSocial',
			store : razonSocialStore,
			fieldLabel: bundle.getMsg('label.company'),
			valueField: 'bukrs',
			displayField: 'butxt',
			hiddenName: 'hdnRazonSocial',
			mode: 'local',
			allowBlank: false,
			width: 400,
			listeners: {
				select : function(c,rec,index){
					if(listarDivision) {
						divisionStore.load({
							params: {
								bukrs: rec.data.bukrs
							}
						});
						division.enable();
						division.clearValue();
					}										
				}
			}
		});
		
		var division = new Ext.form.ComboBox({
			triggerAction: 'all',
			id: 'cmbDivision',
			name: 'cmbDivision',
			store: divisionStore,
			disabled: true,
			fieldLabel: bundle.getMsg('label.division'),
			valueField: 'name',
			hiddenName: 'hdnDivision',
			displayField: 'segment',
			mode: 'local',
			allowBlank: true,
			width: 250
		});
		
		var uploadPDF = new Ext.ux.form.FileUploadField({
			fieldLabel: bundle.getMsg('label.pdffile'),
			name: 'file',
			buttonText: bundle.getMsg('label.pickfile'),
			allowBlank: false,
			regex: /^.*\.(pdf)$/i,
			regexText:'Solo archivos PDF',
	        width: 400
	    });
		
		var facturaField = new Ext.form.TextField({
			fieldLabel: bundle.getMsg('label.invoice'),
			id: 'invoice',
			name: 'invoice',
			allowBlank: false,		
			width: 200
		});
		var d = new Date(new Date().getFullYear(), 0, 1);
		var fechaFactura = new Ext.form.DateField({
			fieldLabel: bundle.getMsg('label.invoicedate'),
			id: 'fechaInvoice',
			name: 'fechaInvoice',		
			allowBlank: false,
			minValue: d,
			format: 'Y-m-d',
			width: 130
		});
		
		var ivaCMb = new Ext.form.ComboBox({
			id:'ivaCmb',
			name: 'ivaCmb',
			fieldLabel: bundle.getMsg('label.tax'),			
			store: new Ext.data.SimpleStore({
				idProperty: 'status',
				fields: ['IVA'],
				data:[
				      ['16.00'],
				      ['11.00'],
				      ['0.00']
				      ]
			}),
			typeAhead: true,
			triggerAction: 'all',
			mode: 'local',
			forceSelection: true,
			allowBlank: false,
			hiddenName: 'hdnIva',
			selectOnFocus: true,
			valueField: 'IVA',
			displayField: 'IVA',
			width: 200
		});
		
		var monedaCMb = new Ext.form.ComboBox({
			id:'monedaCmb',
			name: 'monedaCmb',
			fieldLabel: bundle.getMsg('label.currency'),
			store: new Ext.data.SimpleStore({
				idProperty: 'status',
				fields: ['Currency'],
				data:[
				      ['MXN'],
				      ['USD'],
				      ['EUR'],
				      ['CAD'],
				      ['JPY']
				      ]
			}),
			typeAhead: true,
			triggerAction: 'all',
			mode: 'local',
			forceSelection: true,
			allowBlank: false,
			selectOnFocus: true,
			hiddenName: 'hdnMoneda',
			valueField: 'Currency',
			displayField: 'Currency',
			width: 200
		});
		
		var retencionesField = new Ext.form.TextField({
			fieldLabel: bundle.getMsg('label.retentions'),
			id: 'retenciones',
			name: 'retenciones',
			allowBlank: false,		
			width: 200
		});
		
		var referenciaField = new Ext.form.TextField({
			fieldLabel: bundle.getMsg('label.reference'),
			id: 'referencia',
			name: 'referencia',
			allowBlank: true,		
			width: 200
		});
		
		var subTotalField = new Ext.form.TextField({
			fieldLabel: 'Subtotal',
			id: 'subtotal',
			name: 'subtotal',
			allowBlank: false,		
			width: 200
		});
		var otherPDF = new Ext.ux.form.FileUploadField({
			fieldLabel: bundle.getMsg('label.otherpdffile'),
			name: 'file',
			id: 'other_pdf_doc',
			buttonText: bundle.getMsg('label.pickfile'),
			allowBlank: true,
			regex: /^.*\.(pdf|PDF)$/,
			regexText:'Solo archivos PDF',
	        width: 400
	    });
		
		var cm = new Ext.grid.ColumnModel({
			defaults: {
				sortable: false,
				menuDisabled: true
			},
			columns:[{
				id: 'factura',
				header: bundle.getMsg('label.reference'),
				dataIndex: 'Facs',
				editor: new Ext.form.TextField({
					allowBlank: false,
					autoCreate: {
						tag: 'input',
						type: 'text',
						maxlength : 16
					}
				})
			},{
				xtype: 'actioncolumn',
				width: 30,
				menuDisabled: true,
				items: [{
					icon: contextrootpath + "/resources/img/delete.png",
					handler: function(grid, rowIndex, colIndex){
						storeFacTmp.removeAt(rowIndex);
					}
				}]
			}]
		});
		
		var storeFacTmp =  new Ext.data.SimpleStore({
			idProperty: 'status',
			fields: ['Facs','eliminar'],
			data:[]
		});
		
		var gridFactura = new Ext.grid.EditorGridPanel({
			store: storeFacTmp,
			id: 'gridFactura',
			cm: cm,
			width: 400,
			height: 300,
			autoExpandColumn: 'factura',
			clicksToEdit: 1,
			tbar: [{
				text: bundle.getMsg('label.addreference'),
				handler: function(){
					var fac = gridFactura.getStore().recordType;
					var f = new fac({
						factura: 'Nueva referencia'
					});
					gridFactura.stopEditing();
					storeFacTmp.insert(0,f);
					gridFactura.startEditing(0,0);
				}
			}]
		});
		
		var btnSelectDoc = new Ext.Button({
			text: 'Seleccionar Documentos',
			handler: windowSelection
		});
		
		var gridListaFacD = new Ext.grid.GridPanel({
			store: listaFacturaStoreD,			
			columns: [new Ext.grid.RowNumberer(),
			          {header: bundle.getMsg('label.purchaseorder'), width: 80, dataIndex: 'ebeln', sortable : true, align: 'center'}, //orden de compra
			          {header: bundle.getMsg('label.position'), width: 60, dataIndex: 'ebelp', sortable : true, align: 'center'}, //posicion
			          {header: bundle.getMsg('label.docnum'), width: 80, dataIndex: 'mblnr', sortable : true, align: 'center'}, //no. docto
			          {header: bundle.getMsg('label.date'), width: 80, dataIndex: 'aedat', sortable : true, align: 'center'}, //fecha
			          {header: 'Material', width: 100, dataIndex: 'matnr', sortable : true, align: 'center'}, //Material
			          {header: 'UN', width: 50, dataIndex: 'meins', sortable : true, align: 'center'}, //UN
			          {header: bundle.getMsg('label.description'), width: 200, dataIndex: 'txz01', sortable : true, align: 'center'}, //Descripción
			          {header: bundle.getMsg('label.authamount'), width: 60, dataIndex: 'menge', sortable : true, align: 'center'}, //Ctd. Aut.
			          {header: bundle.getMsg('label.recamount'), width: 80, dataIndex: 'zmenge', editor: new Ext.form.TextField({ allowBlank:false}), sortable : true, align: 'center'}, //Ctd. Rec.
			          {header: bundle.getMsg('label.amountwotax'), width: 100, dataIndex: 'netwr', sortable : true, align: 'center', renderer: function(value, metaData, record){
			        	  record.data.netwr = record.data.zmenge * record.data.punit;
			        	  record.data.netwr = record.data.netwr.toFixed(2);
			        	  return Ext.util.Format.usMoney(record.data.zmenge * record.data.punit);
		        	  }}, //Importe Sin IVA
			          {header: bundle.getMsg('label.currency'), width: 100, dataIndex: 'waers', sortable : true, align: 'center'} //Moneda
			          ],
			viewConfig: {forceFit: true},		
			border: false,
			bbar: [{xtype: 'tbtext', text: 'Total:'},'->',{tag: 'div', id: 'divTotalD', style: 'height:20px;width:100px;border:1px solid;margin-right:120px'}],
			stripeRows: true,
			height: 300,
			columnLines: true,
			autoScroll: true,
			loadMask: true,
			selModel: new Ext.grid.RowSelectionModel({singleSelect: true})
		});
		if(!listarDivision){
			division.hide();
		}
		var formCFDI;
		if(isVisible) {
			formCFDI = new Ext.form.FormPanel({
				title: bundle.getMsg('label.cbbtitle'),
				id: 'cbbForm',
				fileUpload: true,
				method: 'POST',
				bodyStyle: 'padding: 10px 10px 0 10px;',		
				frame: true,
				defaults: {
					msgTarget: 'side',
					anchor: '95%',
					labelWidth: 120,
				},
				border: false,
				renderTo: 'formListaFactura',
				items:[{
					layout: 'form',
					border: false,
					items:[
					       razonSocialField,
					       division,
					       referenciaField,
					       uploadPDF,					       
					       facturaField,
					       fechaFactura,
					       ivaCMb,
					       btnSelectDoc,
					       gridListaFacD,{
					    	   xtype: 'hidden',
					    	   name: 'hdnFacturas',
					    	   id: 'hdnFacturas'
					       },{
					    	   xtype: 'hidden',
					    	   name: 'subto',
					    	   id: 'subto'
					       },{
					    	   xtype: 'hidden',
					    	   name: 'receptionType',
					    	   id: 'hdnReceptionType'
					       },{
					    	   xtype: 'hidden',
					    	   name: 'moneda',
					    	   id: 'hdnMoneda'
					       }
					       ]
				}],
				buttonAlign: 'left',
				buttons:[{
					id: 'enviar',
					text: bundle.getMsg('label.send'),
					type: 'button',
					handler: enviarCFDI,
					style: {
						marginLeft : '15px'
					}
				}]
			});	
			
		} else {
			formCFDI = new Ext.form.FormPanel({
				title: bundle.getMsg('label.cbbtitle'),
				id: 'cbbForm',
				fileUpload: true,
				method: 'POST',
				bodyStyle: 'padding: 10px 10px 0 10px;',		
				frame: true,
				defaults: {
					msgTarget: 'side',
					anchor: '95%',
					labelWidth: 120,
				},
				border: false,
				renderTo: 'formListaFactura',
				items:[{
					layout: 'form',
					border: false,
					items:[
					       razonSocialField,
					       division,
					       referenciaField,
					       uploadPDF,
					       otherPDF,
					       facturaField,
					       fechaFactura,
					       ivaCMb,
//					       retencionesField,
					       monedaCMb,
//					       referenciaField,
					       subTotalField
					       ,
					       gridFactura,{
					    	   xtype: 'hidden',
					    	   name: 'hdnFacturas',
					    	   id: 'hdnFacturas'
					       }
					       ]
				}],
				buttonAlign: 'left',
				buttons:[{
					id: 'enviar',
					text: bundle.getMsg('label.send'),
					type: 'button',
					handler: enviarCFDI,
					style: {
						marginLeft : '15px'
					}
				}]
			});	
		}
		
		function windowSelection(){
			var razonSocialFieldS = new Ext.form.ComboBox({
				triggerAction: 'all',
				id: 'cmbRazonSocialS',
				name: 'cmbRazonSocialS',
				store : razonSocialStore,
				fieldLabel: bundle.getMsg('label.company'),
				valueField: 'bukrs',
				displayField: 'butxt',
				hiddenName: 'hdnbukrs',
				mode: 'local',
				allowBlank: false,
				width: 250,
				listeners: {
					select : function(c,rec,index){
						divisionStore.load({
							params: {
								bukrs: rec.data.bukrs
							}
						});
						divisionS.enable();
						divisionS.clearValue();					
					}
				}
			});
			
			var divisionS = new Ext.form.ComboBox({
				triggerAction: 'all',
				id: 'cmbDivisionS',
				name: 'cmbDivisionS',
				store: divisionStore,
				disabled: true,
				fieldLabel: bundle.getMsg('label.division'),
				valueField: 'name',
				displayField: 'segment',
				mode: 'local',
				allowBlank: true,
				width: 250
			});
			
			var fechaIniS = new Ext.form.DateField({
				fieldLabel: bundle.getMsg('label.invoicepurchasedate'),
				id: 'fechaIniS',
				name: 'fechaIniS',		
				allowBlank: false,
				width: 130
			});
			
			var fechaFinS = new Ext.form.DateField({
				fieldLabel: bundle.getMsg('label.invoicedateto'),
				id: 'fechaFinS',		
				name: 'fechaFinS',		
				allowBlank: false,
				width: 130
			});
			var firstDate = new Date();
			firstDate = new Date(firstDate.getFullYear(),firstDate.getMonth(),1);
			fechaIniS.setValue(firstDate);
			fechaFinS.setValue(new Date());
			
			var orderField = new Ext.form.TextField({
				id: 'ebeln',
				name: 'ebeln',
				fieldLabel: bundle.getMsg('label.purchaseorder'),
				allowBlank: true,
				width: 250
			});
			
			var receptionTypeField = new Ext.form.ComboBox({
				triggerAction: 'all',
				id: 'cmbReceptionTypeField',
				name: 'receptionType',
				store : new Ext.data.SimpleStore({				
					fields: ['val','desc'],
					data:[
					      [1,'MERCANCÍAS/POSICIONES DE SERVICIO'],
					      [2,'COSTES INDIRECTOS PLANIFICADOS']
//					      ,[3,'MCÍA/SERVICIOS + CST. INDIRECTOS PLANIF']
					      ]
				}),
				fieldLabel: 'Tipo Recepción',
				valueField: 'val',
				listWidth: 350,
				displayField: 'desc',
				typeAhead: true,			
				mode: 'local',
				forceSelection: true,
				allowBlank: false,
				selectOnFocus: true,
				width: 250,
			});
			
			var currencyField = new Ext.form.TextField({
				id: 'currency',
				name: 'currency',
				fieldLabel: bundle.getMsg('label.currency'),
				allowBlank: true,
				maxLength: 3,
				width: 250
			});
			
			var numImpField = new Ext.form.TextField({
				id: 'numImp',
				name: 'numImp',
				fieldLabel: 'Número Importación',
				allowBlank: true,
				width: 250
			});
			
			var cm = new Ext.grid.ColumnModel({
				defaults: {
					sortable: false,
					menuDisabled: true
				},
				columns:[{
					id: 'factura',
					header: 'Orden de compra',
					dataIndex: 'Facs',
					editor: new Ext.form.TextField({
						allowBlank: false,
						autoCreate: {
							tag: 'input',
							type: 'text',
							maxlength : 16
						}
					})
				},{
					xtype: 'actioncolumn',
					width: 30,
					menuDisabled: true,
					items: [{
						icon: contextrootpath + "/resources/img/delete.png",
						handler: function(grid, rowIndex, colIndex){
							storeFacTmp.removeAt(rowIndex);
						}
					}]
				}]
			});
			
			var storeFacTmp =  new Ext.data.SimpleStore({
				idProperty: 'status',
				fields: ['Facs','eliminar'],
				data:[]
			});
			
			var gridFactura = new Ext.grid.EditorGridPanel({
				store: storeFacTmp,
				id: 'gridFactura',
				cm: cm,
				width: 400,
				height: 170,
				autoExpandColumn: 'factura',
				clicksToEdit: 1,
				tbar: [{
					text: 'Agregar Orden',
					handler: function(){
						var fac = gridFactura.getStore().recordType;
						var f = new fac({
							factura: 'Nueva orden'
						});
						gridFactura.stopEditing();
						storeFacTmp.insert(0,f);
						gridFactura.startEditing(0,0);
					}
				}]
			});
			
			var btnAgregarDoc = new Ext.Button({
				text: 'Agregar',
				handler: function(){
					var records = new Array();
					listaFacturaStoreD.removeAll();
					listaFacturaStore.each(function(record){
						if(record.data.select){
							records.push(record);
						}
					});
					listaFacturaStoreD.add(records);
					var lastOptions = listaFacturaStore.lastOptions;
					var soc = Ext.getCmp('cmbRazonSocial');
					var buk = Ext.getCmp('cmbDivision');
					if(lastOptions.params) {
						soc.setValue(lastOptions.params.bukrs);
						var index = soc.getStore().find('bukrs',lastOptions.params.bukrs);
						soc.fireEvent('select',soc,soc.getStore().getAt(index),index);					
						buk.setValue(lastOptions.params.ekorg);									
					}
					soc.disable();
					buk.disable();
										
					var totalField = Ext.get('divTotalD');
					totalField.dom.innerHTML = Ext.util.Format.usMoney(total);
					winSelect.hide();
					listaFacturaStore.removeAll();
					console.log(Ext.getCmp('cmbReceptionTypeField').getValue());
					Ext.getCmp('hdnReceptionType').setValue(Ext.getCmp('cmbReceptionTypeField').getValue());
					Ext.getCmp('hdnMoneda').setValue(Ext.getCmp('currency').getValue());
					formListaFactura.getForm().reset();
					var totalFieldS = Ext.get('divTotal');
					totalFieldS.dom.innerHTML = Ext.util.Format.usMoney('0');
				}
			});
			
			var gridListaFac = new Ext.grid.EditorGridPanel({
				store: listaFacturaStore,
				tbar: ['->',{
					xtype: 'tbbutton',
					text:'Seleccionar Todos',
					handler: function(){
						listaFacturaStore.each(function(record){
							record.set('select',true); 
						});
					}
				}],
				columns: [new Ext.grid.RowNumberer(),
				          {header: bundle.getMsg('label.purchaseorder'), width: 80, dataIndex: 'ebeln', sortable : true, align: 'center'}, //orden de compra
				          {header: bundle.getMsg('label.position'), width: 60, dataIndex: 'ebelp', sortable : true, align: 'center'}, //posicion
				          {header: bundle.getMsg('label.docnum'), width: 80, dataIndex: 'mblnr', sortable : true, align: 'center'}, //no. docto
				          {header: bundle.getMsg('label.date'), width: 80, dataIndex: 'aedat', sortable : true, align: 'center'}, //fecha
				          {header: 'Material', width: 100, dataIndex: 'matnr', sortable : true, align: 'center'}, //Material
				          {header: 'UN', width: 30, dataIndex: 'meins', sortable : true, align: 'center'}, //UN
				          {header: bundle.getMsg('label.description'), width: 200, dataIndex: 'txz01', sortable : true, align: 'center'}, //Descripción
				          {header: bundle.getMsg('label.authamount'), width: 80, dataIndex: 'menge', sortable : true, align: 'center'}, //Ctd. Aut.
				          {header: bundle.getMsg('label.recamount'), width: 80, dataIndex: 'zmenge', sortable : true, align: 'center'}, //Ctd. Rec.
				          {header: bundle.getMsg('label.unitprice'), width: 140, dataIndex: 'punit', sortable : true, align: 'center'}, //Ctd. Aut.
				          {header: bundle.getMsg('label.amountwotax'), width: 140, dataIndex: 'netwr', sortable : true, align: 'center', renderer: function(value, metaData, record){
				        	  record.data.netwr = record.data.zmenge * record.data.punit;
				        	  return Ext.util.Format.usMoney(record.data.zmenge * record.data.punit);
			        	  }}, //Importe Sin IVA
				          {header: bundle.getMsg('label.currency'), width: 50, dataIndex: 'waers', sortable : true, align: 'center'}, //Moneda			          
				          {header: bundle.getMsg('label.select'), width: 80, sortable : false, align: 'center', dataIndex: 'select', xtype: 'checkcolumn'}
				          ],
				viewConfig: {forceFit: true},		
				border: false,
				bbar: [{xtype: 'tbtext', text: 'Total:'},'->',{tag: 'div', id: 'divTotal', style: 'height:20px;width:100px;border:1px solid;margin-right:120px'}],
				stripeRows: true,
				height: 300,
				columnLines: true,
				autoScroll: true,
				loadMask: true,
				selModel: new Ext.grid.RowSelectionModel({singleSelect: true})			
			});
			
			var sociedad = Ext.getCmp('cmbRazonSocial');
			var bukrsField = Ext.getCmp('cmbDivision');
			if(sociedad.getValue()) {
				razonSocialFieldS.setValue(sociedad.getValue());
				var index = sociedad.getStore().find('bukrs',sociedad.getValue());
				razonSocialFieldS.fireEvent('select',razonSocialFieldS,razonSocialFieldS.getStore().getAt(index),index);
				if(bukrsField.getValue()){
					divisionS.setValue(bukrsField.getValue());				
				}
			}						
			
			gridListaFac.getStore().on('update', function(t,r,o) {
				total = 0;
				
				t.each(function(record){
					if(record.data.select){
						total = total + (record.data.zmenge * record.data.punit);
					}
				});
				var totalField = Ext.get('divTotal');
				totalField.dom.innerHTML = Ext.util.Format.usMoney(total);
				
			});
			
			if(!listarDivision){
				divisionS.hide();
			}
			
			var formListaFactura = Ext.getCmp('listaFacturasForm') || new Ext.form.FormPanel({
				title: bundle.getMsg('label.orderbuywithoutinvoice'),
				id: 'listaFacturasForm',
				method: 'POST',
				bodyStyle: 'padding-left: 20px;padding-right: 20px;padding-top: 20px;',		 
				layout: 'form',
				border: false,
				frame: true,
				renderTo: 'formListaFactura',
				items:[{
					layout: 'form',
					border: false,
					items:[
					       razonSocialFieldS,
					       divisionS,
					       {
					    	   layout: 'hbox',
					    	   border: false,
					    	   defaults:{margins:'0 10 0 0'},
					    	   items: [{			    		   
					    		   layout: 'form',
					    		   border: false,
					    		   items:[fechaIniS]
					    	   },{			    		   
					    		   layout: 'form',
					    		   border: true,
					    		   labelWidth: 25,
					    		   items:[fechaFinS]
					    	   }]
					       },
//					       orderField,
					       {
					    	   layout: 'hbox',
					    	   border: false,
					    	   defaults:{margins:'0 10 0 0'},
					    	   items: [{			    		   
					    		   layout: 'form',
					    		   border: false,
					    		   items:[receptionTypeField]
					    	   },{			    		   
					    		   layout: 'form',
					    		   border: true,
					    		   labelWidth: 80,
					    		   items:[currencyField]
					    	   }]
					       },
						   gridFactura,
						   numImpField
					       ]
				}],
				buttonAlign: 'left',
				buttons:[{
					id: 'filtrar',
					text: bundle.getMsg('label.filter'),
					type: 'button',
					handler: filtrarFacturas,
					style: {
						marginLeft : '15px'
					}
				}]
			});
			Ext.getCmp('cmbReceptionTypeField').setValue(1);
			
			var winSelect = Ext.getCmp('winSelection') || new Ext.Window({
				id: 'winSelection',
				title: 'Selección de Documentos',
				frame: true,
				layout: 'form',
				closeAction: 'hide',
				width: 1050,
				modal: true,
				items: [formListaFactura,gridListaFac,btnAgregarDoc]
			});
			winSelect.show();
		} // end windowSelection function
		
		function filtrarFacturas() {
			var frm = Ext.getCmp('listaFacturasForm').getForm();	
			var gridOrders = Ext.getCmp('gridFactura');
			var orders = '';
			gridOrders.getStore().each(function(record) {
				if(!(typeof record.data.Facs === "undefined"))
					orders += record.data.Facs + ',';
			});
			if(orders.length > 0)
				orders = orders.substring(0,orders.length -1);
			if(frm.isValid()){
				var values = frm.getFieldValues();
				console.log(values);
				listaFacturaStore.removeAll();
				listaFacturaStore.load({
					params:{
						bukrs: values.hdnbukrs,
						ekorg: values.cmbDivisionS,
						ebeln: values.ebeln,
						fechaIni: values.fechaIniS.format('Y-m-d'),
						fechaFin: values.fechaFinS.format('Y-m-d'),
						receptionType: 4,
						currency: values.currency,
						orders: orders,
						numImp: values.numImp
					}
				});
			} else {
				Ext.MessageBox.show({
					title: bundle.getMsg('label.message'),
					msg: bundle.getMsg('label.alert'),
					icon: Ext.MessageBox.INFO,
					buttons: Ext.MessageBox.OK
				});
			}
		}
		
		function enviarCFDI(){
			Ext.getCmp('enviar').disable();
			Ext.getCmp('cmbRazonSocial').enable();			
			var frm = Ext.getCmp('cbbForm').getForm();
			if(frm.isValid()) {				
				
				var data = new Array();
				
				listaFacturaStoreD.each(function(r){
					data.push(r.data);
				});				
				
				Ext.getCmp('hdnFacturas').setValue(Ext.encode(data));
				if(Ext.getCmp('subto'))
					Ext.getCmp('subto').setValue(total.toFixed(2));
				
				if(true) {
					Ext.Ajax.request({
						   url: contextrootpath + '/sessionStatus.action',
						   success: function(result,request) {
								if(result.responseText.indexOf("login")>=0) {
									Ext.MessageBox.alert(bundle.getMsg('label.sessionalerttitle'), bundle.getMsg('label.sessionalert'),relogin);					
								} else {
									frm.submit({
										url: contextrootpath + '/oc/addCBBCI.action',
										waitMsg: bundle.getMsg('label.savingcbb'),
										success: function(form,action){
											Ext.MessageBox.show({
												title: bundle.getMsg('label.success'),
												msg: action.result.msg,
												buttons: Ext.MessageBox.OK,
												icon: Ext.MessageBox.INFO
											});
											frm.reset();
											storeData.removeAll();
											listaFacturaStoreD.removeAll();
											var totalField = Ext.get('divTotalD');
											totalField.dom.innerHTML = Ext.util.Format.usMoney('0');
											Ext.getCmp('enviar').enable();
										},
										failure: function(form,action){
											switch (action.failureType) {
											  case Ext.form.Action.CLIENT_INVALID:
												 Ext.MessageBox.show({
														title: 'Atención',
														msg: 'Valores inválidos',
														buttons: Ext.MessageBox.OK,
														icon: Ext.MessageBox.ERROR
												 });
												 frm.reset();
												 storeData.removeAll();
												 listaFacturaStoreD.removeAll();
												 var totalField = Ext.get('divTotalD');
												 totalField.dom.innerHTML = Ext.util.Format.usMoney('0');
												 break;
											  case Ext.form.Action.CONNECT_FAILURE:
												 Ext.MessageBox.show({
														title: 'Atención',
														msg: 'Falla de comunicación con el servidor',
														buttons: Ext.MessageBox.OK,
														icon: Ext.MessageBox.ERROR
												 });
												 frm.reset();
												 storeData.removeAll();
												 listaFacturaStoreD.removeAll();
												 var totalField = Ext.get('divTotalD');
												 totalField.dom.innerHTML = Ext.util.Format.usMoney('0');
												 break;
											  case Ext.form.Action.SERVER_INVALID:
												 Ext.MessageBox.show({
														title: 'Atención',
														msg: action.result.msg,
														buttons: Ext.MessageBox.OK,
														icon: Ext.MessageBox.ERROR
												 });
												 frm.reset();
												 storeData.removeAll();
												 listaFacturaStoreD.removeAll();
												 var totalField = Ext.get('divTotalD');
												 totalField.dom.innerHTML = Ext.util.Format.usMoney('0');
												 break;
											  default:
												 Ext.MessageBox.show({
														title: 'Atención',
														msg: action.result.msg,
														buttons: Ext.MessageBox.OK,
														icon: Ext.MessageBox.ERROR
												 });
												 frm.reset();
												 storeData.removeAll();
												 listaFacturaStoreD.removeAll();
												 var totalField = Ext.get('divTotalD');
												 totalField.dom.innerHTML = Ext.util.Format.usMoney('0');
											  	 break;
											}
											Ext.getCmp('enviar').enable();
										}
									});
								}
						   		},
							   failure: utilities.expiredSession
					});												
				} else {
					Ext.MessageBox.show({
						title: bundle.getMsg('label.alerttitle'),
						msg: bundle.getMsg('label.referenceerror'),
						buttons: Ext.MessageBox.OK,
						icon: Ext.MessageBox.ERROR
					});
					Ext.getCmp('enviar').enable();
				}
			}
		}
		
	});//end bundle
	
		
});
