var razonSocialStore = new Ext.data.JsonStore({		
		root: 'data',
		fields: ['bukrs','butxt'],
		autoLoad: true,
		proxy: new Ext.data.HttpProxy({ url: contextrootpath + '/provider/razonSocial.action', method: 'GET' })
	});


Ext.onReady(function(){
	
	Ext.QuickTips.init();
	getUnreadNotificationsNumber();
	
	Ext.form.Field.prototype.msgTarget = 'side';		
	var lang;
	var params = Ext.urlDecode(window.location.search.substring(1));
	lang = params.language ? params.language : 'es';
	var bundle = new Ext.i18n.Bundle({bundle:'messages', path: contextrootpath + '/resources/language', lang: lang});
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
			width: 200
			
		});
				
		
		var uploadPDF = new Ext.ux.form.FileUploadField({
			fieldLabel: bundle.getMsg('label.pdffile'),
			name: 'file',
			id: 'pdf_doc',
			buttonText: bundle.getMsg('label.pickfile'),
			allowBlank: false,
	        width: 400
	    });
		
		var invoiceField = new Ext.form.TextField({
			fieldLabel: bundle.getMsg('label.invoice'),
			id: 'invoice',
			name: 'invoice',
			allowBlank: false,		
			width: 400
		});
		
		var invoiceDate = new Ext.form.DateField({
			fieldLabel: bundle.getMsg('label.invoicedate'),
			id: 'invoiceDate',
			name: 'invoiceDate',		
			allowBlank: false,
			format: 'Y-m-d',
			width: 130
		});
		
		var ivaCmb = new Ext.form.ComboBox({
			id:'iva',
			name: 'iva',
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
			// hiddenName: 'hdnIva',
			selectOnFocus: true,
			valueField: 'IVA',
			displayField: 'IVA',
			width: 200
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
//				      ,[3,'MCÍA/SERVICIOS + CST. INDIRECTOS PLANIF']
				      ]
			}),
			fieldLabel: 'Tipo Recepción',
			valueField: 'val',
			hiddenName: 'cmbReceptionTypeField',
			listWidth: 350,
			displayField: 'desc',
			typeAhead: true,			
			mode: 'local',
			forceSelection: true,
			allowBlank: false,
			selectOnFocus: true,
			width: 250,
		});
				
		
		var fm = Ext.form;
		
		var cm = new Ext.grid.ColumnModel({
			defaults: {
				sortable: false,
				menuDisabled: true
			},
			columns:[{
				id: 'order',
				header: bundle.getMsg('label.purchaseorder'),
				dataIndex: 'Facs',
				editor: new fm.TextField({
					allowBlank: false
				})
			},{
				xtype: 'actioncolumn',
				width: 30,
				menuDisabled: true,
				items: [{
					icon: contextrootpath + "/resources/img/delete.png",
					handler: function(grid, rowIndex, colIndex){
						storeOrderTmp.removeAt(rowIndex);
					}
				}]
			}]
		});
		
		var cmImp = new Ext.grid.ColumnModel({
			defaults: {
				sortable: false,
				menuDisabled: true
			},
			columns:[{
				id: 'importation',
				header: bundle.getMsg('label.importation'),
				dataIndex: 'Imp',
				editor: new fm.TextField({
					allowBlank: false
				})
			},{
				xtype: 'actioncolumn',
				width: 30,
				menuDisabled: true,
				items: [{
					icon: contextrootpath + "/resources/img/delete.png",
					handler: function(grid, rowIndex, colIndex){
						storeImpTmp.removeAt(rowIndex);
					}
				}]
			}]
		});
		
		var storeOrderTmp =  new Ext.data.SimpleStore({
			idProperty: 'status',
			fields: ['Order','eliminar'],
			data:[]
		});
		
		var gridOrders = new Ext.grid.EditorGridPanel({
			store: storeOrderTmp,
			id: 'gridOrders',
			cm: cm,
			width: 400,
			height: 300,
			autoExpandColumn: 'order',
			clicksToEdit: 1,
			tbar: [{
				text: bundle.getMsg('label.addpurchaseorder'),
				handler: function(){
					var fac = gridOrders.getStore().recordType;
					var f = new fac({
						factura: 'Nueva orden'
					});
					gridOrders.stopEditing();
					storeOrderTmp.insert(0,f);
					gridOrders.startEditing(0,0);
				}
			}]
		});
		
		var storeImpTmp =  new Ext.data.SimpleStore({
			idProperty: 'status',
			fields: ['Imp','eliminar'],
			data:[]
		});
		
		var gridImp = new Ext.grid.EditorGridPanel({
			store: storeImpTmp,
			id: 'gridImp',
			cm: cmImp,
			width: 400,
			height: 300,
			autoExpandColumn: 'importation',
			clicksToEdit: 1,
			tbar: [{
				text: bundle.getMsg('label.addimportation'),
				handler: function(){
					var fac = gridImp.getStore().recordType;
					var f = new fac({
						factura: 'Nueva importacion'
					});
					gridImp.stopEditing();
					storeImpTmp.insert(0,f);
					gridImp.startEditing(0,0);
				}
			}]
		});
		
		var formCFDI = new Ext.form.FormPanel({
			title: bundle.getMsg('label.addinboxscfdititle'),
			id: 'cfdiForm',
			fileUpload: true,
			method: 'POST',
			bodyStyle: 'padding: 10px 10px 0 10px;',		
			frame: true,
			defaults: {
				msgTarget: 'side',
				anchor: '95%',
				labelWidth: 200,
			},
			border: false,
			renderTo: 'formListaFactura',
			items:[{
				layout: 'form',
				border: false,
				items:[
				       razonSocialField,
				       uploadPDF,
				       invoiceField,
				       invoiceDate,
				       ivaCmb,
				       receptionTypeField,
				       gridOrders,
				       gridImp,{
				    	   xtype: 'hidden',
				    	   name: 'hdnOrdenes',
				    	   id: 'hdnOrders'
				       },{
				    	   xtype: 'hidden',
				    	   name: 'hdnImportaciones',
				    	   id: 'hdnImportaciones'
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
		
	}); //end bundle
	
	function enviarCFDI(){
		var frm = Ext.getCmp('cfdiForm').getForm();
		if(frm.isValid()) {
			var gridData = Ext.getCmp('gridOrders');
			var storeData = gridData.getStore();
			var orders = "";			
			storeData.each(function(record){
				if(!(typeof record.data.Facs === "undefined"))
					orders = orders + record.data.Facs + ","; 
			});
			orders = orders.substring(0,orders.length -1);
			Ext.getCmp('hdnOrders').setValue(orders);
			
			var gridDataImp = Ext.getCmp('gridImp');
			var storeDataImp = gridDataImp.getStore();
			var importaciones = "";			
			storeDataImp.each(function(record){
				if(!(typeof record.data.Imp === "undefined"))
					importaciones = importaciones + record.data.Imp + ","; 
			});
			importaciones = importaciones.substring(0,importaciones.length -1);
			Ext.getCmp('hdnImportaciones').setValue(importaciones);
			
			if(orders.length > 0 && importaciones.length > 0){
				Ext.Ajax.request({
					   url: contextrootpath + '/sessionStatus.action',
					   success: function(result,request) {
							if(result.responseText.indexOf("login")>=0) {
								Ext.MessageBox.alert(bundle.getMsg('label.sessionalerttitle'), bundle.getMsg('label.sessionalert'),relogin);					
							} else {
								frm.submit({
									url: contextrootpath + '/inbox/addInboxSCFDI.action',
									waitMsg: bundle.getMsg('label.savinginbox'),
									success: function(form,action){
										Ext.MessageBox.show({
											title: 'Éxito',
											msg: bundle.getMsg('label.cfdisaved') + action.result.msg,
											buttons: Ext.MessageBox.OK,
											icon: Ext.MessageBox.INFO
										});
										frm.reset();
										storeData.removeAll();
									},
									failure: function(form,action){
										switch (action.failureType) {
										  case Ext.form.Action.CLIENT_INVALID:
											 Ext.Msg.alert('Error', 'Valores inválidos');
											 frm.reset();
											 storeData.removeAll();
											 break;
										  case Ext.form.Action.CONNECT_FAILURE:
											 Ext.Msg.alert('Error', 'Falla de comunicación con el servidor');
											 frm.reset();
											 storeData.removeAll();
											 break;
										  case Ext.form.Action.SERVER_INVALID:
											Ext.Msg.alert('Error', action.result.msg);
											frm.reset();
											storeData.removeAll();
											break;
										  default:
											Ext.Msg.alert('Error',action.result.msg);
										  	frm.reset();
										  	storeData.removeAll();
										  	break;
										}
									}
								});
							}
					   }
					});						
			} else{
				Ext.MessageBox.show({
					title: 'Atención',
					msg: bundle.getMsg('label.referenceerror'),
					buttons: Ext.MessageBox.OK,
					icon: Ext.MessageBox.ERROR
				});
			}
			
		}
	}	
	
});

