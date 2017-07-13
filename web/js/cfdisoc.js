
Ext.onReady(function(){
	
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
	
	Ext.QuickTips.init();
	getUnreadNotificationsNumber();
	
	var lang;
	var params = Ext.urlDecode(window.location.search.substring(1));
	lang = params.language ? params.language : 'es';
	var bundle = new Ext.i18n.Bundle({bundle:'messages', path: contextrootpath + '/resources/language', lang: lang});
	bundle.onReady(function(){
		
		var areaStore = new Ext.data.JsonStore({
			root: 'data',
			fields: ['idDireccion','idGerencia','descDireccion','descGerencia'],
			autoLoad: false,
			proxy: new Ext.data.HttpProxy({ url: contextrootpath + '/provider/areas.action', method: 'GET' }),
			listeners: {
				exception : utilities.handleException
			}
		});
		
		var responsableStore = new Ext.data.JsonStore({
			fields: ['idGerencia', 'descGerencia']
		});
		
		Ext.form.Field.prototype.msgTarget = 'side';
		
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
			displayField: 'segment',
			hiddenName: 'hdnDivision',
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
			disabled: true,
			hiddenName: 'hdnArea',
			mode: 'local',
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
			id: 'concept',
			maxLength: 50,
			name: 'concept',
			allowBlank: false,		
			width: 200
		});
		
		var uploadXML = new Ext.ux.form.FileUploadField({
			fieldLabel: bundle.getMsg('label.xmlfile'),
			name: 'file',
			buttonText: bundle.getMsg('label.pickfile'),
			allowBlank: false,
			regex: /^.*\.(xml)$/i,
			regexText:'Solo archivos XML',
	        width: 400
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
			title: bundle.getMsg('label.wopcfdi'),
			id: 'cfdiForm',
			fileUpload: true,
			method: 'POST',
			bodyStyle: 'padding: 10px 10px 0 10px;',		
			frame: true,
			defaults: {
				msgTarget: 'side',
				anchor: '95%',
				labelWidth: 150,
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
				       facturaField,
				       conceptoField,
				       referenciaField,
				       uploadXML,
				       uploadPDF,
				       otherPDF
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
		
	}); // end bundle
	
	function enviarCFDI(){
		Ext.getCmp('enviar').disable();
		var frm = Ext.getCmp('cfdiForm').getForm();
		if(frm.isValid()) {
			Ext.Ajax.request({
				   url: contextrootpath + '/sessionStatus.action',
				   success: function(result,request) {
						if(result.responseText.indexOf("login")>=0) {
							Ext.MessageBox.alert(bundle.getMsg('label.alerttitle'), bundle.getMsg('label.sessionalert'),relogin);					
						} else {
							frm.submit({
								url: contextrootpath + '/soc/addCFDI.action',
								waitMsg: bundle.getMsg('label.savingcfdi'),
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
		}
	}
		
});

