var notificationStore = new Ext.data.JsonStore({		
	root: 'data',
	fields: ['cuerpo',
	         'estatus',
	         'fecha',
	         'idioma',
	         'titulo',
	         'naviso',
	         'bukrs',
	         'idAviso',
	         'estatusLectura',
	         {name: 'mark', type: 'boolean'}],
	autoLoad: false,
	proxy: new Ext.data.HttpProxy({ url: contextrootpath + '/provider/getNotifications.action', method: 'POST' }),
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
		
		var formListaFactura = new Ext.form.FormPanel({
			title: bundle.getMsg('menu.notifications'),
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
				       razonSocialField				       
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
			store: notificationStore,
			renderTo: 'gridListaFactura',
			columns: [new Ext.grid.RowNumberer(),
			          {header: bundle.getMsg('label.date'), width: 40, dataIndex: 'fecha', sortable : true, align: 'center'
			        	  , renderer: function(value, metaData,record) {
			        		  if(record.data.estatusLectura != 1)
			        			  metaData.attr = 'style = "font-weight: bold;"';
			        		  return value;
			        	  }},
			          {header: bundle.getMsg('label.title'), width: 70, dataIndex: 'titulo', sortable : true, align: 'center'
			        	  , renderer: function(value, metaData,record) {
			        		  if(record.data.estatusLectura != 1)
			        			  metaData.attr = 'style = "font-weight: bold;"';
			        		  return value;
			        	  }},			          
			          {header: bundle.getMsg('label.body'), width: 500, dataIndex: 'cuerpo', align: 'center', sortable : true
			        	,renderer: function(value, metaData, record){
			        		if(record.data.estatusLectura != 1)
			        			metaData.attr = 'style = "font-weight: bold;"';
			        		return record.data.cuerpo.replace(/#/g,'<br/>');
			          	}},
			          {header: 'Mensaje no leído', width: 80, sortable : false, align: 'center', xtype: 'checkcolumn', dataIndex: 'mark'},
//			          {header: bundle.getMsg('label.status'), width: 80, dataIndex: 'estatus', sortable : true, align: 'center'},
//			          {header: bundle.getMsg('label.notice'), width: 80, dataIndex: 'naviso', sortable : true, align: 'center'}
//			          {header: bundle.getMsg('label.paymentdate'), width: 100, dataIndex: 'fpago', sortable : true, align: 'center'},
//			          {header: bundle.getMsg('label.status'), width: 100, dataIndex: 'estatus', sortable : true, align: 'center'}
			          ],
			viewConfig: {forceFit: true},		
			border: false,
			stripeRows: true,
			height: 300,
			columnLines: true,
			autoScroll: true,
			loadMask: true,
			selModel: new Ext.grid.RowSelectionModel({singleSelect: true}),
			listeners : {
				rowclick : function(t, rowIndex, e) {
					var row = notificationStore.getAt(rowIndex);
					console.log(row);
					Ext.Ajax.request({
						url: contextrootpath + '/provider/setNotificationStatus.action',
						params: {
							bukrs: row.data.bukrs,
							idaviso: row.data.idAviso,
							evento: '1'
						},
						success: function(response,opts) {							
							var responseDecoded = Ext.decode(response.responseText);
							console.log(responseDecoded);
							getUnreadNotificationsNumber();
							notificationStore.reload();
						},
						failure: function(response,opts) {
							console.log(response);
						}
					});
				}
			}
		});
		
		notificationStore.on('update', function(t,row,operation) {
			console.log(row);
			Ext.Ajax.request({
				url: contextrootpath + '/provider/setNotificationStatus.action',
				params: {
					bukrs: row.data.bukrs,
					idaviso: row.data.idAviso,
					evento: '2'
				},
				success: function(response,opts) {							
					var responseDecoded = Ext.decode(response.responseText);
					console.log(responseDecoded);
					getUnreadNotificationsNumber();
					notificationStore.reload();
				},
				failure: function(response,opts) {
					console.log(response);
				}
			});
		});
		
		function filtrarFacturas() {
			var frm = Ext.getCmp('listaFacturasForm').getForm();	
			if(frm.isValid()){
				notificationStore.removeAll();
				notificationStore.load({
					params:{
						bukrs: Ext.getCmp('cmbRazonSocial').getValue(),
						tipoAviso: '1'
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

