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

Ext.onReady(function(){
	
	Ext.QuickTips.init();
	
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
			width: 200,
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
		
		var uploadXML = new Ext.ux.form.FileUploadField({
			fieldLabel: bundle.getMsg('label.xmlfile'),
			name: 'file',
			id: 'xml_doc',
			buttonText: bundle.getMsg('label.pickfile'),
			allowBlank: false,
	        width: 400
	    });
		
		var uploadPDF = new Ext.ux.form.FileUploadField({
			fieldLabel: bundle.getMsg('label.pdffile'),
			name: 'file',
			id: 'pdf_doc',
			buttonText: bundle.getMsg('label.pickfile'),
			allowBlank: false,
	        width: 400
	    });
				
		var ordenDeCompraField = new Ext.form.TextField({
			fieldLabel: bundle.getMsg('label.purchaseorder'),
			id: 'ebeln',
			name: 'ebeln',
			allowBlank: false,		
			width: 400
		});
				
		
		var fm = Ext.form;
		
		var cm = new Ext.grid.ColumnModel({
			defaults: {
				sortable: false,
				menuDisabled: true
			},
			columns:[{
				id: 'factura',
				header: bundle.getMsg('label.reference'),
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
		if(!listarDivision) {
			division.hide();
		}
		
		var formCFDI = new Ext.form.FormPanel({
			title: bundle.getMsg('label.addinboxtitle'),
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
				       division,
				       uploadXML,
				       uploadPDF,
				       ordenDeCompraField,
				       gridFactura,{
				    	   xtype: 'hidden',
				    	   name: 'hdnFacturas',
				    	   id: 'hdnFacturas'
				       }
//				       facturaField
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
			var gridData = Ext.getCmp('gridFactura');
			var storeData = gridData.getStore();
			var facturas = "";			
			storeData.each(function(record){
				if(!(typeof record.data.Facs === "undefined"))
				facturas = facturas + record.data.Facs + ","; 
			});
			facturas = facturas.substring(0,facturas.length -1);
			Ext.getCmp('hdnFacturas').setValue(facturas);
			if(facturas.length > 0){
				Ext.Ajax.request({
					   url: contextrootpath + '/sessionStatus.action',
					   success: function(result,request) {
							if(result.responseText.indexOf("login")>=0) {
								Ext.MessageBox.alert(bundle.getMsg('label.sessionalerttitle'), bundle.getMsg('label.sessionalert'),relogin);					
							} else {
								frm.submit({
									url: contextrootpath + '/inbox/addInbox.action',
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

