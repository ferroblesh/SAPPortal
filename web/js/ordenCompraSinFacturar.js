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
		         {name: 'xblnr'},
		         {name: 'menge'},
		         {name: 'netwr'},
		         {name: 'waers'},
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
		     	 {name: 'sheetItem'}
        ],
		autoLoad: false,
		proxy: new Ext.data.HttpProxy({ url: contextrootpath + '/provider/listPendingInvoice.action', method: 'GET' }),
		listeners: {
			exception : utilities.handleException
//			'exception': function (t,type,action,options,response,arg) {
//				var res = Ext.decode(response.responseText);
//				Ext.MessageBox.show({
//					title: 'Atención',
//					msg: res.msg,
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
			width: 250,
			listeners: {
				select : function(c,rec,index){
					if(listarDivision){
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
			mode: 'local',
			allowBlank: true,
			width: 250
		});
		
		var fechaIni = new Ext.form.DateField({
			fieldLabel: bundle.getMsg('label.invoicepurchasedate'),
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
		var firstDate = new Date();
		firstDate = new Date(firstDate.getFullYear(),firstDate.getMonth(),1);
		fechaIni.setValue(firstDate);
		fechaFin.setValue(new Date());
		
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
//				      ,[3,'MCÍA/SERVICIOS + CST. INDIRECTOS PLANIF']
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
			value: 1,
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
		
		if(!listarDivision){
			division.hide();
		}
		
		var formListaFactura = new Ext.form.FormPanel({
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
				       orderField,
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
		Ext.getCmp('cmbReceptionTypeField').setValue(1);
		
		var gridListaFac = new Ext.grid.GridPanel({
			store: listaFacturaStore,
			renderTo: 'gridListaFactura',
			columns: [new Ext.grid.RowNumberer(),
			          {header: bundle.getMsg('label.purchaseorder'), width: 80, dataIndex: 'ebeln', sortable : true, align: 'center'}, //orden de compra
			          {header: bundle.getMsg('label.position'), width: 60, dataIndex: 'ebelp', sortable : true, align: 'center'}, //posicion
			          {header: bundle.getMsg('label.docnum'), width: 80, dataIndex: 'mblnr', sortable : true, align: 'center'}, //no. docto
			          {header: bundle.getMsg('label.date'), width: 80, dataIndex: 'aedat', sortable : true, align: 'center'}, //fecha
			          {header: 'Material', width: 100, dataIndex: 'matnr', sortable : true, align: 'center'}, //Material
			          {header: 'UN', width: 50, dataIndex: 'meins', sortable : true, align: 'center'}, //UN
			          {header: bundle.getMsg('label.description'), width: 200, dataIndex: 'txz01', sortable : true, align: 'center'}, //Descripción
			          {header: bundle.getMsg('label.authamount'), width: 60, dataIndex: 'menge', sortable : true, align: 'center'}, //Ctd. Aut.			          
			          {header: bundle.getMsg('label.amountwotax'), width: 100, dataIndex: 'netwr', sortable : true, align: 'center', renderer: function(value){return Ext.util.Format.usMoney(value);}}, //Importe Sin IVA
			          {header: bundle.getMsg('label.currency'), width: 100, dataIndex: 'waers', sortable : true, align: 'center'} //Moneda
			          ],
			viewConfig: {forceFit: true},		
			border: false,
			stripeRows: true,
			height: 300,
			columnLines: true,
			autoScroll: true,
			loadMask: true,
			selModel: new Ext.grid.RowSelectionModel({singleSelect: true})
			,tbar: [bundle.getMsg('label.export'),'-',{
				cls: 'x-btn-icon',
				icon: contextrootpath + '/resources/img/excel.png',
				tooltip: bundle.getMsg('label.exportExcel'),
				handler: function(){
					var getStr = contextrootpath + "/provider/pendingInvoicesExcel.action?" +
								 "bukrs=" + Ext.getCmp('cmbRazonSocial').getValue() + 
								 "&ekorg=" + Ext.getCmp('cmbDivision').getValue() +
								 "&fechaIni=" + Ext.getCmp('fechaIni').getValue().format('Y-m-d') +
								 "&fechaFin=" + Ext.getCmp('fechaFin').getValue().format('Y-m-d') +								 
								 "&ebeln=" + Ext.getCmp('ebeln').getValue() +
								 "&receptionType=" + Ext.getCmp('cmbReceptionTypeField').getValue() +
								 "&currency=" + Ext.getCmp('currency').getValue();
					
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
						bukrs: Ext.getCmp('cmbRazonSocial').getValue(),
						ekorg: Ext.getCmp('cmbDivision').getValue(),
						ebeln: Ext.getCmp('ebeln').getValue(),
						fechaIni: Ext.getCmp('fechaIni').getValue().format('Y-m-d'),
						fechaFin: Ext.getCmp('fechaFin').getValue().format('Y-m-d'),
						receptionType: Ext.getCmp('cmbReceptionTypeField').getValue(),
						currency: Ext.getCmp('currency').getValue()
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

