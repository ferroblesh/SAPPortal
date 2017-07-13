var listaFacturaStore = new Ext.data.JsonStore({
		root: 'data',
		fields: [
		         {name: 'facturado'},		         
		         {name: 'fechaIngreso'},
		         {name: 'importe'},
		         {name: 'waers'},
		         {name: 'xblnr'},
		         {name: 'ebeln'},
		         {name: 'lifnr'},
		         {name: 'xmlName'},
		         {name: 'pdfName'},
		         {name: 'borrado'},
		         {name: 'factura'},
		         {name: 'ctrlid'},
		         {name: 'deleted', type: 'bool'}  
        ],
		autoLoad: false,
		proxy: new Ext.data.HttpProxy({ url: contextrootpath + '/inbox/getInbox.action', method: 'GET' }),
		listeners: {			
			exception : utilities.handleException
//			'exception': function (t,type,action,options,response,arg) {
//				Ext.MessageBox.show({
//					title: 'Atención',
//					msg: 'No se encontró información',
//					icon: Ext.MessageBox.WARNING,
//					buttons: Ext.MessageBox.OK
//				});
//			}
		}
	});

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
	var lang;
	var params = Ext.urlDecode(window.location.search.substring(1));
	lang = params.language ? params.language : 'es'; 
	Ext.QuickTips.init();
	getUnreadNotificationsNumber();
	Ext.form.Field.prototype.msgTarget = 'side';
	
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
			width: 250,
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
		
		var fechaIni = new Ext.form.DateField({
			fieldLabel: bundle.getMsg('label.invoicedate'),
			id: 'fechaIni',
			name: 'fechaIni',		
			allowBlank: false,
			width: 130
		});
		
		var fechaFin = new Ext.form.DateField({
			fieldLabel: bundle.getMsg('label.invoicedateto'),		
			id: 'fechaFin',		
			name: 'fechaFin',		
			allowBlank: false,
			width: 130
		});
		
		
		var onemonthBack = new Date();
		onemonthBack.setMonth(onemonthBack.getMonth() - 1);
		fechaIni.setValue(onemonthBack);
		fechaFin.setValue(new Date());
		if(!listarDivision) {
			division.hide();
		}
		
		var formListaFactura = new Ext.form.FormPanel({
			title: bundle.getMsg('menu.findInbox'),
			id: 'listaFacturasForm',
			method: 'POST',
			bodyStyle: 'padding: 10px 10px 0 10px;',
			labelWidth: 100,
			frame: true,
			defaults: {
				msgTarget: 'side',
				anchor: '95%'
			},
			border: false,
			renderTo: 'formListaFactura',
			items:[{
				layout: 'form',
				border: false,
				items:[
				       razonSocialField,
				       division,
				       {
				    	   layout: 'hbox',
				    	   border: false,
				    	   defaults:{margins:'0 10 0 0'},
				    	   items: [{			    		   
				    		   layout: 'form',
				    		   border: false,
				    		   items:[fechaIni]
				    	   },{			    		   
				    		   layout: 'form',
				    		   border: true,
				    		   labelWidth: 25,			    		   
//				   			   defaults: {
////				   				   anchor: '-50',
//				   				   border: false,
////				   	               labelStyle: 'margin-left: 5px;'
//				   	       	   },
				    		   items:[fechaFin]
				    	   }]
				       }
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
		
		var gridListaFac = new Ext.grid.GridPanel({
			store: listaFacturaStore,
			renderTo: 'gridListaFactura',
			columns: [new Ext.grid.RowNumberer(),
			          {header: bundle.getMsg('label.invoice'), width: 100, dataIndex: 'xblnr', sortable : true, align: 'center'},
			          {header: bundle.getMsg('label.doc'), width: 100, dataIndex: 'ebeln', sortable : true, align: 'center'},
			          {header: bundle.getMsg('label.amountwotax'), width: 100, dataIndex: 'importe', align: 'center', sortable : true, renderer: function(value){return Ext.util.Format.usMoney(value);}},
			          {header: bundle.getMsg('label.currency'), width: 50, dataIndex: 'waers', sortable : true, align: 'center'},
			          {header: 'XML', width: 250, dataIndex: 'xmlName', sortable : true, align: 'center'},
			          {header: 'PDF', width: 250, dataIndex: 'pdfName', sortable : true, align: 'center'},
			          {header: bundle.getMsg('label.invoiced'), width: 150, dataIndex: 'facturado', sortable : true, align: 'center'},
			          {header: bundle.getMsg('label.deleted'), width: 150, dataIndex: 'borrado', sortable : true, align: 'center'},
			          {header: bundle.getMsg('label.delete'), width: 80, sortable : false, align: 'center', xtype: 'checkcolumn', dataIndex: 'deleted'},			          
			          ],
			viewConfig: {forceFit: true},		
			border: false,
			stripeRows: true,
			height: 300,
			columnLines: true,
			autoScroll: true,
			loadMask: true,
			selModel: new Ext.grid.RowSelectionModel({singleSelect: true}),
			tbar: [bundle.getMsg('label.export'),'-',{
				cls: 'x-btn-icon',
				icon: contextrootpath + '/resources/img/excel.png',
				tooltip: bundle.getMsg('label.exportExcel'),
				handler: function(){
					var getStr = contextrootpath + "/inbox/getInboxExcel.action?" +
								 "cmbRazonSocial=" + Ext.getCmp('cmbRazonSocial').getValue() +
						 		 "&fechaIni=" + Ext.getCmp('fechaIni').getValue().format('Y-m-d') +
								 "&fechaFin=" + Ext.getCmp('fechaFin').getValue().format('Y-m-d') +
								 "&ekorg=" + Ext.getCmp('cmbDivision').getValue();
					
					document.location.href = getStr;
				}
			},'->',{				
				tooltip: bundle.getMsg('label.delete.tooltip'),
				text: bundle.getMsg('label.delete'),
				width: 80,
				handler: function(){
					var data = [];
					var invoicesStores = gridListaFac.getStore();
					var params = {
							burks : Ext.getCmp('cmbRazonSocial').getValue()
					};
					invoicesStores.each(function(record){
						if(record.data.deleted) {
							var row = {};
							for(var i = 0; i < record.fields.length; i++){
								if(record.fields.items[i].name != 'deleted')
									row[record.fields.items[i].name] = record.data[record.fields.items[i].name];								
							}
							row['bukrs'] = Ext.getCmp('cmbRazonSocial').getValue();
							data.push(row);
						}
					});
					if(data.length > 0){
						var msgBox = Ext.MessageBox.wait('Eliminando', 'Eliminando...');
						Ext.Ajax.request({
							url: contextrootpath + '/inbox/deleteInbox.action',
							method: 'POST',
							jsonData: data,
							params: params,
							waitMsg: 'Eliminando...',
							success: function(result,request){
								msgBox.hide();
								console.log(result);
								var msgs = Ext.decode(result.responseText);
								var msg = "";
								for(var i = 0; i < msgs.data.length; i++){
									msg = msg + msgs.data[i].invoice + ":" + msgs.data[i].e_msg + "\n";
								}								
								Ext.Msg.alert(bundle.getMsg('label.message'),msg);
								listaFacturaStore.reload();
							},
							failure: function(result,request){
								console.log(result);
								msgBox.hide();
								listaFacturaStore.reload();
							}							
						});
					} else{
						Ext.Msg.alert(bundle.getMsg('label.message'), bundle.getMsg('label.noinboxselected'));
					}
					
				}
			}]
		});
		
		function filtrarFacturas() {
			var frm = Ext.getCmp('listaFacturasForm').getForm();	
			if(frm.isValid()){
				listaFacturaStore.removeAll();
				listaFacturaStore.load({
					params:{
						cmbRazonSocial: Ext.getCmp('cmbRazonSocial').getValue(),
						ekorg: Ext.getCmp('cmbDivision').getValue(),
						fechaIni: Ext.getCmp('fechaIni').getValue().format('Y-m-d'),
						fechaFin: Ext.getCmp('fechaFin').getValue().format('Y-m-d')						 
					}
				});
			} else {
				Ext.MessageBox.show({
					title: bundle.getMsg('label.alerttitle'),
					msg: bundle.getMsg('label.alert'),
					icon: Ext.MessageBox.INFO,
					buttons: Ext.MessageBox.OK
				});
			}
		}
	}); // end bundle
				
});

