var listaFacturaStore = new Ext.data.JsonStore({
		root: 'data',
		fields: [
		         {name: 'estatus'},
		         {name: 'fpago'},
		         {name: 'fvenc'},
		         {name: 'neto'},
		         {name: 'waers'},
		         {name: 'xblnr'},
		         {name: 'beln'}		         
        ],
		autoLoad: false,
		proxy: new Ext.data.HttpProxy({ url: contextrootpath + '/provider/list.action', method: 'GET' }),
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
			valueField: 'segment',
			displayField: 'name',
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
		
		var estadoCmb = new Ext.form.ComboBox({
			id:'cmbEstado',
			name: 'cmbEstado',
			fieldLabel: bundle.getMsg('label.status'),
			emptyText: bundle.getMsg('label.emptystatus'),
			store: new Ext.data.SimpleStore({
				idProperty: 'status',
				fields: ['I_ESTATUS','ESTATUS'],
				data:[
				      [0,bundle.getMsg('cmb.paid')],
				      [1,bundle.getMsg('cmb.payable')],
				      [2,bundle.getMsg('cmb.all')]
				      ]
			}),
			typeAhead: true,
			triggerAction: 'all',
			mode: 'local',
			forceSelection: true,
			allowBlank: false,
			selectOnFocus: true,
			valueField: 'I_ESTATUS',
			displayField: 'ESTATUS',
			width: 250
		});
		var onemonthBack = new Date();
		onemonthBack.setMonth(onemonthBack.getMonth() - 1);
		fechaIni.setValue(onemonthBack);
		fechaFin.setValue(new Date());
		if(!listarDivision){
			division.hide();
		}
		
		var formListaFactura = new Ext.form.FormPanel({
			title: bundle.getMsg('menu.invoicelist'),
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
				    		   items:[fechaFin]
				    	   }]
				       },
				       estadoCmb
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
			          {header: bundle.getMsg('label.doc'), width: 100, dataIndex: 'beln', sortable : true, align: 'center'},
			          {header: bundle.getMsg('label.amountwotax'), width: 100, dataIndex: 'neto', align: 'center', sortable : true, renderer: function(value){return Ext.util.Format.usMoney(value);}},
			          {header: bundle.getMsg('label.currency'), width: 100, dataIndex: 'waers', sortable : true, align: 'center'},
			          {header: bundle.getMsg('label.neto'), width: 100, dataIndex: 'fvenc', sortable : true, align: 'center'},
			          {header: bundle.getMsg('label.paymentdate'), width: 100, dataIndex: 'fpago', sortable : true, align: 'center'},
			          {header: bundle.getMsg('label.status'), width: 100, dataIndex: 'estatus', sortable : true, align: 'center'}
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
					var getStr = contextrootpath + "/provider//listInvoiceExcel.action?" +
								 "cmbRazonSocial=" + Ext.getCmp('cmbRazonSocial').getValue() +
						 		 "&fechaIni=" + Ext.getCmp('fechaIni').getValue().format('Y-m-d') +
								 "&fechaFin=" + Ext.getCmp('fechaFin').getValue().format('Y-m-d') +
								 "&cmbEstado=" + Ext.getCmp('cmbEstado').getValue() +
								 "&ekorg=" + Ext.getCmp('cmbDivision').getValue();
					
					document.location.href = getStr;
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
						fechaIni: Ext.getCmp('fechaIni').getValue().format('Y-m-d'),
						fechaFin: Ext.getCmp('fechaFin').getValue().format('Y-m-d'),
						cmbEstado: Ext.getCmp('cmbEstado').getValue(),
						ekorg: Ext.getCmp('cmbDivision').getValue()
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

