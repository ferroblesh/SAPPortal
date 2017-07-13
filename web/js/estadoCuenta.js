var listaFacturaStore = new Ext.data.JsonStore({
		root: 'abierta',
		fields: [
		         {name: 'belnr'},
		         {name: 'blart'},
		         {name: 'budat'},		         
		         {name: 'zfbdt'},
		         {name: 'xblnr'},
		         {name: 'wrbtr'},
		         {name: 'waers'},
		         {name: 'augbl'},
		         {name: 'augdt'}
        ],
		autoLoad: false,
		proxy: new Ext.data.HttpProxy({ url: contextrootpath + '/provider/accountStatus.action', method: 'GET' }),
		listeners: {
			exception : utilities.handleException
		}
});

var compensadaStore = new Ext.data.JsonStore({
	root: 'compensada',
	fields: [
	         {name: 'belnr'},
	         {name: 'blart'},
	         {name: 'budat'},		         
	         {name: 'zfbdt'},
	         {name: 'xblnr'},
	         {name: 'wrbtr'},
	         {name: 'waers'},
	         {name: 'augbl'},
	         {name: 'augdt'}
    ],
	autoLoad: false,
	proxy: new Ext.data.HttpProxy({ url: contextrootpath + '/provider/accountStatus.action', method: 'GET' }),
	listeners: {
		exception : utilities.handleException
	}
});

var razonSocialStore = new Ext.data.JsonStore({		
	root: 'data',
	fields: ['bukrs','butxt'],
	autoLoad: true,
	proxy: new Ext.data.HttpProxy({ url: contextrootpath + '/provider/razonSocial.action', method: 'GET' })
});

Ext.onReady(function(){
    // Initialize Quicktips 	
    Ext.QuickTips.init();
    getUnreadNotificationsNumber();
    
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
			mode: 'local',
			allowBlank: false,
			width: 250			
		});
				
		
		var formListaFactura = new Ext.form.FormPanel({
			title: bundle.getMsg('menu.accountstatus'),
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
				       razonSocialField,				       
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
			},{
				id: 'conforme',
				text: lang == 'es' ? 'Conforme' : 'Agree',
				type: 'button',
				handler: function(){
					var params = {};
					Ext.MessageBox.show({
						  msg: lang == 'es' ? 'Espere por favor' : 'Wait please',
						  progressText: lang == 'es' ? 'Procesando...' : 'Processing...',
						  width:300,
						  wait:true,
						  waitConfig: {interval:200}
					});

					params.bukrs = Ext.getCmp('cmbRazonSocial').getValue();
					params.estado = 1;
					Ext.Ajax.request({
						url: contextrootpath + '/provider/confirmAccountStatus.action', 
						method: 'POST',
						params: params,
						waitMsg: 'Confirmando Estado',
						success: function(result, request) {
							Ext.MessageBox.hide(); 
							if(Ext.util.JSON.decode(result.responseText).success == false){
								Ext.MessageBox.show({
									title: 'Mensaje',
									msg: Ext.util.JSON.decode(result.responseText).msg,
									buttons: Ext.MessageBox.OK,
									icon: Ext.MessageBox.WARNING
								});
							} else {
								Ext.MessageBox.show({
									title: 'Mensaje',
									msg: Ext.util.JSON.decode(result.responseText).msg,
									buttons: Ext.MessageBox.OK,
									icon: Ext.MessageBox.WARNING
								});
							}
						},
						failure: function(result, request) {
							Ext.MessageBox.hide(); 
							switch (result.failureType) {
							case Ext.form.Action.CLIENT_INVALID:
					          	Ext.MessageBox.show({
					     					title: 'WARNING',
					     					msg: "Error en la información",
					     					buttons: Ext.MessageBox.OK,
					     					icon: Ext.MessageBox.WARNING
			     				});
					          	break;
							case Ext.form.Action.CONNECT_FAILURE:
								Ext.MessageBox.show({
											title: 'CRITICAL FAILURE',
											msg: "Error de comunicación",
												buttons: Ext.MessageBox.OK,
												icon: Ext.MessageBox.ERROR
										});
								break;
							default:
								Ext.MessageBox.show({
											title: 'GENERAL ERROR',
											msg: result.responseText,
											buttons: Ext.MessageBox.OK,
											icon: Ext.MessageBox.ERROR
										});	      			
						}
						}
					});
				},
				style: {
					marginLeft : '15px'
				}
			},{
				id: 'noConforme',
				text: lang == 'es' ? 'No conforme' : "Don't agree",
				type: 'button',
				handler: function(){
					Ext.MessageBox.show({
						  msg: 'Espere por favor',
						  progressText: 'Procesando...',
						  width:300,
						  wait:true,
						  waitConfig: {interval:200}
					});
					var params = {};
					params.bukrs = Ext.getCmp('cmbRazonSocial').getValue();
					params.estado = 0;
					Ext.Ajax.request({
						url: contextrootpath + '/provider/confirmAccountStatus.action', 
						method: 'POST',
						params: params,
						waitMsg: 'Confirmando Estado',
						success: function(result, request) {
							Ext.MessageBox.hide(); 
							if(Ext.util.JSON.decode(result.responseText).success == false){
								Ext.MessageBox.show({
									title: 'WARNING',
									msg: Ext.util.JSON.decode(result.responseText).msg,
									buttons: Ext.MessageBox.OK,
									icon: Ext.MessageBox.WARNING
								});
							} else {
								Ext.MessageBox.show({
									title: 'WARNING',
									msg: Ext.util.JSON.decode(result.responseText).msg,
									buttons: Ext.MessageBox.OK,
									icon: Ext.MessageBox.WARNING
								});
							}
						},
						failure: function(result, request) {
							Ext.MessageBox.hide();
							switch (result.failureType) {
							case Ext.form.Action.CLIENT_INVALID:
					          	Ext.MessageBox.show({
					     					title: 'Mensaje',
					     					msg: "Error en la información",
					     					buttons: Ext.MessageBox.OK,
					     					icon: Ext.MessageBox.WARNING
			     				});
					          	break;
							case Ext.form.Action.CONNECT_FAILURE:
								Ext.MessageBox.show({
											title: 'Falla',
											msg: "Error de comunicación",
												buttons: Ext.MessageBox.OK,
												icon: Ext.MessageBox.ERROR
										});
								break;
							default:
								Ext.MessageBox.show({
											title: 'ERROR',
											msg: result.responseText,
											buttons: Ext.MessageBox.OK,
											icon: Ext.MessageBox.ERROR
										});	      			
						}
						}
					});
				},
				style: {
					marginLeft : '15px'
				}
			}]
		});
		
		var gridListaFac = new Ext.grid.GridPanel({
			store: listaFacturaStore,
			title: lang == 'es' ? 'Partidas abiertas' : 'Open items',
			renderTo: 'gridListaFactura',
			columns: [new Ext.grid.RowNumberer(),
			          {header: bundle.getMsg('label.doc'), width: 100, dataIndex: 'belnr', sortable : true, align: 'center'},
			          {header: 'Clase Doc', width: 100, dataIndex: 'blart', sortable : true, align: 'center'},
			          {header: bundle.getMsg('label.accounteddate'), width: 100, dataIndex: 'budat', sortable : true, align: 'center'},
			          {header: bundle.getMsg('label.neto'), width: 100, dataIndex: 'zfbdt', sortable : true, align: 'center'},
			          {header: bundle.getMsg('label.reference'), width: 100, dataIndex: 'xblnr', sortable : true, align: 'center'},
			          {header: bundle.getMsg('label.amountmd'), width: 100, dataIndex: 'wrbtr', sortable : true, align: 'center', renderer: function(value){return Ext.util.Format.usMoney(value);}},
			          {header: bundle.getMsg('label.currency'), width: 100, dataIndex: 'waers', sortable : true, align: 'center'},
			          {header: bundle.getMsg('label.doccomp'), width: 100, dataIndex: 'augbl', sortable : true, align: 'center'},
			          {header: bundle.getMsg('label.compensationdate'), width: 100, dataIndex: 'augdt', sortable : true, align: 'center'}			          			          
			          ],
			viewConfig: {forceFit: true},		
			border: false,
			stripeRows: true,
			height: 250,
			columnLines: true,
			autoScroll: true,
			loadMask: true,
			selModel: new Ext.grid.RowSelectionModel({singleSelect: true})
			,
			tbar: [bundle.getMsg('label.export'),'-',{
				cls: 'x-btn-icon',
				icon: contextrootpath + '/resources/img/excel.png',
				tooltip: bundle.getMsg('label.exportExcel'),
				handler: function(){
					var getStr = contextrootpath + "/provider/accountStatusExcel.action?" +
								 "bukrs=" + Ext.getCmp('cmbRazonSocial').getValue();
					
					document.location.href = getStr;
				}
			}]
		});
		
		var gridPartidaComp = new Ext.grid.GridPanel({
			store: compensadaStore,
			title: lang == 'es' ? 'Partidas compensadas' : 'Cleared items',
			renderTo: 'gridPartidaComp',
			columns: [new Ext.grid.RowNumberer(),
			          {header: bundle.getMsg('label.doc'), width: 100, dataIndex: 'belnr', sortable : true, align: 'center'},
			          {header: 'Clase Doc', width: 100, dataIndex: 'blart', sortable : true, align: 'center'},
			          {header: bundle.getMsg('label.accounteddate'), width: 100, dataIndex: 'budat', sortable : true, align: 'center'},
			          {header: bundle.getMsg('label.neto'), width: 100, dataIndex: 'zfbdt', sortable : true, align: 'center'},
			          {header: bundle.getMsg('label.reference'), width: 100, dataIndex: 'xblnr', sortable : true, align: 'center'},
			          {header: bundle.getMsg('label.amountmd'), width: 100, dataIndex: 'wrbtr', sortable : true, align: 'center', renderer: function(value){return Ext.util.Format.usMoney(value);}},
			          {header: bundle.getMsg('label.currency'), width: 100, dataIndex: 'waers', sortable : true, align: 'center'},
			          {header: bundle.getMsg('label.doccomp'), width: 100, dataIndex: 'augbl', sortable : true, align: 'center'},
			          {header: bundle.getMsg('label.compensationdate'), width: 100, dataIndex: 'augdt', sortable : true, align: 'center'}			          			          
			          ],
			viewConfig: {forceFit: true},		
			border: false,
			stripeRows: true,
			height: 250,
			columnLines: true,
			autoScroll: true,
			loadMask: true,
			selModel: new Ext.grid.RowSelectionModel({singleSelect: true})
//			,
//			tbar: [bundle.getMsg('label.export'),'-',{
//				cls: 'x-btn-icon',
//				icon: contextrootpath + '/resources/img/excel.png',
//				tooltip: bundle.getMsg('label.exportExcel'),
//				handler: function(){
//					var getStr = contextrootpath + "/provider//listInvoiceStatusExcel.action?" +
//								 "cmbRazonSocial=" + Ext.getCmp('cmbRazonSocial').getValue() + 
//								 "&fechaIni=" + Ext.getCmp('fechaIni').getValue().format('Y-m-d') +
//								 "&fechaFin=" + Ext.getCmp('fechaFin').getValue().format('Y-m-d') +
//								 "&ekorg=" + Ext.getCmp('cmbDivision').getValue();
//					
//					document.location.href = getStr;
//				}
//			}]
		});
		
		function filtrarFacturas() {
			var frm = Ext.getCmp('listaFacturasForm').getForm();	
			if(frm.isValid()){
				listaFacturaStore.removeAll();
				compensadaStore.removeAll();
				listaFacturaStore.load({
					params:{
						bukrs: Ext.getCmp('cmbRazonSocial').getValue()						
					}
				});
				compensadaStore.load({
					params:{
						bukrs: Ext.getCmp('cmbRazonSocial').getValue()						
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
		
	}); // end bundle
	
	
});

