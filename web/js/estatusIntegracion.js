var listaFacturaStore = new Ext.data.JsonStore({
		root: 'data',
		fields: [
		         {name: 'erdat'},
		         {name: 'estatus'},
		         {name: 'message'},		         
		         {name: 'xblnr'}
        ],
		autoLoad: false,
		proxy: new Ext.data.HttpProxy({ url: contextrootpath + '/provider/status.action', method: 'GET' }),
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
					divisionStore.load({
						params: {
							bukrs: rec.data.bukrs
						}
					});
					division.enable();
					division.clearValue();					
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
		var firstDate = new Date();
		firstDate = new Date(firstDate.getFullYear(),firstDate.getMonth(),1);
		fechaIni.setValue(firstDate);
		fechaFin.setValue(new Date());
				
		if(!listarDivision){
			division.hide();
		}
		
		var formListaFactura = new Ext.form.FormPanel({
			title: bundle.getMsg('menu.receptionstatus'),
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
				    	   layout: 'column',
				    	   border: false,			    	   
				    	   items: [{
				    		   columnWidth: .3,
				    		   layout: 'form',
				    		   border: false,
				    		   items:[fechaIni]
				    	   },{
				    		   columnWidth: .3,
				    		   layout: 'form',
				    		   border: false,
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
			          {header: bundle.getMsg('label.receptiondate'), width: 100, dataIndex: 'erdat', sortable : true, align: 'center'},
			          {header: bundle.getMsg('label.status'), width: 100, dataIndex: 'estatus', sortable : true, align: 'center'},
			          {header: bundle.getMsg('label.message'), width: 100, dataIndex: 'message', sortable : true, align: 'center'}		          
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
					var getStr = contextrootpath + "/provider//listInvoiceStatusExcel.action?" +
								 "cmbRazonSocial=" + Ext.getCmp('cmbRazonSocial').getValue() + 
								 "&fechaIni=" + Ext.getCmp('fechaIni').getValue().format('Y-m-d') +
								 "&fechaFin=" + Ext.getCmp('fechaFin').getValue().format('Y-m-d') +
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
						ekorg: Ext.getCmp('cmbDivision').getValue()
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

