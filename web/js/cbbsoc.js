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

var areaStore = new Ext.data.JsonStore({
	root: 'data',
	fields: ['idDireccion','idGerencia','descDireccion','descGerencia'],
	autoLoad: false,
	proxy: new Ext.data.HttpProxy({ url: contextrootpath + '/provider/areas.action', method: 'GET' })
});

var responsableStore = new Ext.data.JsonStore({
	fields: ['idGerencia', 'descGerencia']
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
			width: 400,
			listeners: {
				select : function(c,rec,index){
					areaStore.load({
						params: {
							bukrs: rec.data.bukrs
						}
					});
					areaCmb.enable();
					areaCmb.clearValue();
					responsableCmb.disable();
					responsableCmb.clearValue();
					
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
		
		var areaCmb = new Ext.form.ComboBox({
			triggerAction: 'all',
			id: 'cmbAreas',
			name: 'cmbAreas',
			store : areaStore,
			fieldLabel: bundle.getMsg('label.area'),
			valueField: 'idDireccion',
			displayField: 'descDireccion',
			hiddenName: 'hdnArea',
			mode: 'local',
			disabled: true,
			allowBlank: false,
			width: 400
		});
		
		var responsableCmb = new Ext.form.ComboBox({
			triggerAction: 'all',
			id: 'cmbResponsable',
			name: 'cmbResponsable',
			store : responsableStore,
			disabled: true,
			fieldLabel: bundle.getMsg('label.responsible'),
			valueField: 'idGerencia',
			displayField: 'descGerencia',
			hiddenName: 'hdnResponsable',
			mode: 'local',
			allowBlank: false,
			width: 400
		});
		var d = new Date(new Date().getFullYear(), 0, 1);
		var fechaFactura = new Ext.form.DateField({
			fieldLabel: bundle.getMsg('label.invoicedate'),
			id: 'fechaInvoice',
			name: 'fechaInvoice',		
			allowBlank: false,
			format: 'Y-m-d',
			minValue: d,
			width: 130
		});
		
		var facturaField = new Ext.form.TextField({
			fieldLabel: bundle.getMsg('label.invoice'),
			id: 'invoice',
			name: 'invoice',
			maxLength: 15,
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
		
		var conceptoField = new Ext.form.TextField({
			fieldLabel: bundle.getMsg('label.concept'),
			id: 'concepto',
			name: 'concepto',
			allowBlank: false,
			maxLength: 50,
			width: 200
		});
		
		var subtotalField = new Ext.form.TextField({
			fieldLabel: 'Subtotal',
			id: 'subtotal',
			name: 'subtotal',
			allowBlank: false,		
			width: 200
		});
		
		var ivaCmb = new Ext.form.ComboBox({
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
		
		var monedaCmb = new Ext.form.ComboBox({
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
		
		var totalField = new Ext.form.TextField({
			fieldLabel: 'Total',
			id: 'total',
			name: 'total',
			allowBlank: false,		
			width: 200
		});	
		
		var uploadPDF = new Ext.ux.form.FileUploadField({
			fieldLabel: bundle.getMsg('label.pdffile'),
			name: 'file',			
			allowBlank: false,
			regex: /^.*\.(pdf)$/i,
			regexText:'Solo archivos PDF',
	        width: 400
	    });
		
		var otherPDF = new Ext.ux.form.FileUploadField({
			fieldLabel: bundle.getMsg('label.otherpdffile'),
			name: 'file',
			id: 'other_pdf_doc',
			buttonText: bundle.getMsg('label.pickfile'),
			allowBlank: true,
			regex: /^.*\.(pdf)$/i,
			regexText:'Solo archivos PDF',
	        width: 400
	    });
		if(!listarDivision){
			division.hide();
		}
		
		var formCFDI = new Ext.form.FormPanel({
			title: bundle.getMsg('label.wopcbb'),
			id: 'cfdiForm',
			fileUpload: true,
			method: 'POST',
			bodyStyle: 'padding: 10px 10px 0 10px;',		
			frame: true,
			defaults: {
				msgTarget: 'side',
				anchor: '95%',
				labelWidth: 150
			},
			border: false,
			renderTo: 'formListaFactura',
			items:[{
				layout: 'form',
				border: false,
				items:[
				       razonSocialField,
				       division,
				       areaCmb,
				       responsableCmb,
				       fechaFactura,
				       facturaField,
				       conceptoField,
				       subtotalField,
				       ivaCmb,
//				       retencionesField,
//				       totalField,
				       monedaCmb,
				       referenciaField,
				       uploadPDF,
				       otherPDF,
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
		
		areaCmb.on('select',function(cmb,record,index){
			responsableCmb.enable();
			responsableCmb.reset();
			responsableStore.removeAll();			
			responsableStore.loadData(record.json.responsables);
		});
		
		function enviarCFDI(){
			Ext.getCmp('enviar').disable();
			var frm = Ext.getCmp('cfdiForm').getForm();
			if(frm.isValid()) {
				Ext.Ajax.request({
					   url: contextrootpath + '/sessionStatus.action',
					   success: function(result,request) {
							if(result.responseText.indexOf("login")>=0) {
								Ext.MessageBox.alert(bundle.getMsg('label.sessionalerttitle'), bundle.getMsg('label.sessionalert'),relogin);					
							} else {
								frm.submit({
									url: contextrootpath + '/soc/addCBB.action',
									waitMsg: bundle.getMsg('label.savingcbb'),
									success: function(form,action){
										Ext.MessageBox.show({
											title: bundle.getMsg('label.success'),
											msg: action.result.msg,
											buttons: Ext.MessageBox.OK,
											icon: Ext.MessageBox.INFO
										});
										frm.reset();
										Ext.getCmp('enviar').enable();
									},
									failure: function(form,action){
										switch (action.failureType) {
										  case Ext.form.Action.CLIENT_INVALID:
											 Ext.Msg.alert('Error', 'Valores inválidos');
											 frm.reset();
											 break;
										  case Ext.form.Action.CONNECT_FAILURE:
											 Ext.Msg.alert('Error', 'Falla de comunicación con el servidor');
											 frm.reset();
											 break;
										  case Ext.form.Action.SERVER_INVALID:
											 Ext.Msg.alert('Error', action.result.msg);
											 frm.reset();
											 break;
										  default:
											 Ext.Msg.alert('Error',action.result.msg);
										  	 frm.reset();
										  	 break;
										}
										Ext.getCmp('enviar').enable();
									}
								});
							}
					   },
					   failure: utilities.expiredSession
				});	
				
			}else {		
				Ext.MessageBox.show({
					title: bundle.getMsg('label.alerttitle'),
					msg: bundle.getMsg('label.alert'),
					icon: Ext.MessageBox.INFO,
					buttons: Ext.MessageBox.OK
				});
				Ext.getCmp('enviar').enable();
			}
		}
	}); // end bundle
	
	
});
