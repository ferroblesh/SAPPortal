var listaFacturaStore = new Ext.data.JsonStore({
		root: 'data',
		fields: [
		         	{name: 'ebeln'},
					{name: 'bedat'},
					{name: 'ebelp'},
					{name: 'matnr'},
					{name: 'ematn'},
					{name: 'menge'},
					{name: 'mengep'},
					{name: 'factor'},
					{name: 'merma'},
					{name: 'ntgew'},
					{name: 'ntgewp'},
					{name: 'toler'},
					{name: 'mobrap'},
					{name: 'mobras'},
					{name: 'pesot'},
					{name: 'pesotp'},
					{name: 'mens'},
					{name: 'menssage'},
					{name: 'estat'},
					{name: 'borra'},
					{name: 'idioma'}
        ],
		autoLoad: false,
		proxy: new Ext.data.HttpProxy({ url: contextrootpath + '/provider/getPackingList.action', method: 'POST' }),
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


Ext.onReady(function(){
    // Initialize Quicktips 	
    Ext.QuickTips.init();
    getUnreadNotificationsNumber();
    
    var lang;
	var params = Ext.urlDecode(window.location.search.substring(1));
	lang = params.language ? params.language : 'es';
	bundle = new Ext.i18n.Bundle({bundle:'messages', path: contextrootpath + '/resources/language', lang: lang});
	bundle.onReady(function(){
				
		var orderField = new Ext.form.TextField({
			id: 'ebeln',
			name: 'ebeln',
			fieldLabel: bundle.getMsg('label.purchaseorder'),
			anchor:'30%',
			allowBlank: true,
			width: 100
		});
		
		var htmlTableEs = '<table style="border-collapse:collapse;border-spacing:0;table-layout: fixed; width: 350px"><colgroup><col style="width: 85px"><col style="width: 225px"></colgroup><tr><th style="font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:3px 7px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;vertical-align:top" colspan="2">Política de tolerancia</th></tr><tr><td style="font-family:Arial, sans-serif;font-size:14px;padding:3px 7px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;vertical-align:top">(+/-) 15%</td><td style="font-family:Arial, sans-serif;font-size:14px;padding:3px 7px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;vertical-align:top">Para modelos &lt;= 2grs.</td></tr><tr><td style="font-family:Arial, sans-serif;font-size:14px;padding:3px 7px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;vertical-align:top">(+/-) 10%</td><td style="font-family:Arial, sans-serif;font-size:14px;padding:3px 7px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;vertical-align:top">Para modelos entre 2.1 and 5 grs.</td></tr><tr><td style="font-family:Arial, sans-serif;font-size:14px;padding:3px 7px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;vertical-align:top">(+/-) 5 %</td><td style="font-family:Arial, sans-serif;font-size:14px;padding:3px 7px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;vertical-align:top">Para modelos &gt;= 5g.</td></tr></table>';
		var htmlTableEn = '<table style="border-collapse:collapse;border-spacing:0;border-color:#ccc;table-layout: fixed; width: 310px"><colgroup><col style="width: 85px"><col style="width: 225px"></colgroup><tr><th style="font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:3px 7px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;vertical-align:top" colspan="2">Tolerance Policy</th></tr><tr><td style="font-family:Arial, sans-serif;font-size:14px;padding:3px 7px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;vertical-align:top">(+/-) 15%</td><td style="font-family:Arial, sans-serif;font-size:14px;padding:3px 7px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;vertical-align:top">For pieces &lt;= 2g.</td></tr><tr><td style="font-family:Arial, sans-serif;font-size:14px;padding:3px 7px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;vertical-align:top">(+/-) 10%</td><td style="font-family:Arial, sans-serif;font-size:14px;padding:3px 7px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;vertical-align:top">For pieces between 2.1 and 5 g.</td></tr><tr><td style="font-family:Arial, sans-serif;font-size:14px;padding:3px 7px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;vertical-align:top">(+/-) 5 %</td><td style="font-family:Arial, sans-serif;font-size:14px;padding:3px 7px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;vertical-align:top">For pieces &gt;= 5g.</td></tr></table>'
		var formListaFactura = new Ext.form.FormPanel({
			title: 'Packing List',
			id: 'listaFacturasForm',
			method: 'POST',
			bodyStyle: 'padding-left: 20px;padding-right: 20px;padding-top: 20px;',		 
			layout: 'column',
			border: false,
			frame: true,
			renderTo: 'formListaFactura',
			items:[{
				columnWidth: .5,
				layout: 'form',
				border: false,
				defaults:{
					anchor: '40%',
					labelStyle: 'margin-left:5px;'
				},
				items:[				      
				       orderField,				       
				       ]
			},{
				columnWidth: .5,
				layout: 'form',
				border: false,
				defaults: {
					anchor: '-20',
					labelStyle: 'margin-left-5px'
				},
				items:[{
					html: lang == 'es' ? htmlTableEs : htmlTableEn,
					xtype:'panel'
				}]
			}],
			buttonAlign: 'left',
			buttons:[{
				id: 'filtrar',
				text: bundle.getMsg('label.fill'),
				type: 'button',
				handler: filtrarFacturas,
				style: {
					marginLeft : '15px'
				}
			}]
		});
		
		var footerMsgEn = '<p><b>Packing List checked.</b><br/><br/>If the order is not authorized in 3 days, please contact the buyer</p>';
		var footerMsgEs = '<p><b>Packing List Confirmado:</b><br/><br/>Si en 3 días no queda autorizada la orden favor de contactar a la compradora</p>';
		
		var gridListaFac = new Ext.grid.EditorGridPanel({
			id: 'gridListaFac',
			store: listaFacturaStore,
			renderTo: 'gridListaFactura',
			colModel: new Ext.ux.grid.LockingColumnModel([ // new Ext.grid.RowNumberer(),
			          {header: bundle.getMsg('label.purchaseorder'), width: 100, dataIndex: 'ebeln',resizable: false, sortable : false, menuDisabled: true, align: 'center',locked: true}, //orden de compra
					  {header: bundle.getMsg('label.status'), width: 55, dataIndex: 'estat',resizable: false, sortable : true, sortable : false, menuDisabled: true,locked: true , align: 'center', renderer: function(value,meta,record,rowIndex,colIndex) {
						  if(value == '1'){
							  // meta.style = "background-color: #FF0000;width: 53px;"
							  meta.attr = 'style="background-color:#FF0000;color:black;"';
							  return '&nbsp;';
							  // return '<font color="#FF0000">No procede</font>';
						  } else if( value == '2') {
							  //meta.attr = "background-color: #FFFF00;;width: 53px;"
							  meta.attr = 'style="background-color:#FFFF00;color:black;"';
							  return '&nbsp;';
							  // return '<font color="#868A08">Se ajustar&aacute; en el pedido</font>';
						  } else if( value == '3') {
							  //meta.attr = "background-color: #04B431;;width: 53px;"
							  meta.attr = 'style="background-color:#04B431;color:black;"';
							  return '&nbsp;';
							  // return '<font color="#04B431">correcta la informaci&oacute;n</font>';
						  }
					  }},
					  {header: bundle.getMsg('label.position'), width: 60, dataIndex: 'ebelp',locked: true ,resizable: false, sortable : false, menuDisabled: true, align: 'center'}, //posicion
			          {header: bundle.getMsg('label.deliveryDate'), width: 110, dataIndex: 'bedat',locked: true ,resizable: false, sortable : false, menuDisabled: true, align: 'center'}, //Fecha de Entrega
			          {header: bundle.getMsg('label.material'), width: 110, dataIndex: 'matnr',locked: true ,resizable: false, sortable : false, menuDisabled: true, align: 'center'}, //Material
			          {header: bundle.getMsg('label.materialfab'), width: 130, dataIndex: 'ematn',locked: true ,resizable: false, sortable : false, menuDisabled: true, align: 'center'}, // Num material p.num.pieza fabricante
					  {header: bundle.getMsg('label.cantsap'), width: 80, dataIndex: 'menge',resizable: false, sortable : false, menuDisabled: true, align: 'center', editable: false},
			          {header: bundle.getMsg('label.cantprov'), width: 80, dataIndex: 'mengep',resizable: false, sortable : false, menuDisabled: true, align: 'center', editable: true,editor: {xtype: 'textfield'},renderer: function(value,meta,record,rowIndex,colIndex) {
						meta.attr = "style='border: 1px solid black;display: inline-table;text-align: center;width: 72px;'";
						return value;
					  }
					  }, //Campo Cantidad
			          {header: bundle.getMsg('label.merma'), width: 60, dataIndex: 'merma',resizable: false, sortable : false, menuDisabled: true, align: 'center'}, //UN
					  {header: 'Factor', width: 60, dataIndex: 'factor',resizable: false, sortable : false, menuDisabled: true, align: 'center'}, //fecha
			          {header: bundle.getMsg('label.weight'), width: 80,resizable: false, dataIndex: 'ntgew', sortable : false, menuDisabled: true, align: 'center', editable: false},
					  {header: bundle.getMsg('label.weightp'), width: 90,resizable: false, dataIndex: 'ntgewp', sortable : false, menuDisabled: true, align: 'center', editable: false},
					  {header: bundle.getMsg('label.toler'), width: 80, dataIndex: 'toler',resizable: false, sortable : false, menuDisabled: true, align: 'center'},
					  {header: bundle.getMsg('label.mobras'), width: 110, dataIndex: 'mobras',resizable: false, sortable : false, menuDisabled: true, align: 'center'},
			          {header: bundle.getMsg('label.mobrap'), width: 110, dataIndex: 'mobrap',resizable: false, sortable : false, menuDisabled: true, align: 'center',editable: true,editor: {xtype: 'textfield'},renderer: function(value,meta,record,rowIndex,colIndex) {
						meta.attr = "style='border: 1px solid black;display: inline-table;text-align: center;width: 95px;'";
						return value;
					  }},
			          {header: bundle.getMsg('label.weighttp'), width: 110, dataIndex: 'pesotp',resizable: false, sortable : false, menuDisabled: true, align: 'center',editable: true, renderer: function(value,meta,record,rowIndex,colIndex) {
						// meta.attr = "style='border: 1px solid black;text-align: center;width: 75px;'";
						// var mobrap = record.get('mobrap');
						// var mobras = record.get('mobras');
						// value = Number(mobrap) * Number(mobras);
						// value = value.toFixed(2);
						return value;
					  }},
					  {header: bundle.getMsg('label.weightt'), width: 80, dataIndex: 'pesot',resizable: false, sortable : false, menuDisabled: true, align: 'center',editable: true, editor: {xtype: 'textfield'},renderer: function(value,meta,record,rowIndex,colIndex) {
						meta.attr = "style='border: 1px solid black;display: inline-table;text-align: center;width: 75px;'";
						return value;
					  }},
					  {header: bundle.getMsg('label.text'), width: 700, dataIndex: 'mens',resizable: false, sortable : false, menuDisabled: true, align: 'center'}
			          ]),
			// viewConfig: {forceFit: true},		
			border: false,
			view: new Ext.ux.grid.LockingGridView(),
			stripeRows: true,
			height: 300,
			// width: 900,
			// columnLines: true,
			autoScroll: true,
			loadMask: true,
			clicksToEdit: 1,
			selModel: new Ext.grid.RowSelectionModel({singleSelect: true})
			,tbar: [{
				text: bundle.getMsg('label.read'),	
				icon: contextrootpath + '/resources/img/search.png',
				handler: function(){
					gridListaFac.getEl().mask('Procesando...');
					var data = [];
					listaFacturaStore.each(function(record) {
						var row = {};
						record.fields.each(function(item,index,length) {
							row[item.name] = record.get(item.name);
						});
						data.push(row);
					});
					var params = {
						ebeln: Ext.getCmp('ebeln').getValue(),
						indicator: 'V',
						packingList: data
					};
					Ext.Ajax.request({
					method: 'POST',
					url: contextrootpath + '/provider/getPackingList.action',
					jsonData: params,
					waitMsg: 'Loading',
					success: function(result) {
						gridListaFac.getEl().unmask();
						// console.log(result);
						var jsonData = Ext.util.JSON.decode(result.responseText);
						// console.log(jsonData);
						if(jsonData.success) {
							
							listaFacturaStore.loadData(jsonData);
							var allowConfirm = true;
							var msgEn = 'You can not confirm your packing list with positions in RED state. Verify that your information is within the parameters allowed by Grupo Cristal';
							var msgEs = 'No se puede confirmar tu packing list con posiciones en estado ROJO. Verifica que tu información este dentro de los parámetros permitidos por Grupo Cristal.';
							listaFacturaStore.each(function(record) {
								if(parseInt(record.data.estat) < 2) {
									allowConfirm = false;
									Ext.MessageBox.show({
                                      title:  bundle.getMsg('label.message'),
                                      msg: lang == 'es' ? msgEs : msgEn,
                                      buttons: Ext.MessageBox.OK,
                                      icon: Ext.MessageBox.WARNING
									});
									return;
								}
							});
							Ext.getCmp('btnConfirmar').setDisabled(!allowConfirm);
						} else {
							Ext.MessageBox.show({
                                      title: 'Mensaje',
                                      msg: 'Pedido no asignado a proveedor, consulte a CxP',
                                      buttons: Ext.MessageBox.OK,
                                      icon: Ext.MessageBox.WARNING
                                  });
						}
					},
					failure: function() {
						gridListaFac.getEl().unmask();
						Ext.getCmp('btnConfirmar').setDisabled(true);
						Ext.MessageBox.show({
                                      title: 'Atencion',
                                      msg: 'No se pudo obtener informaci&oacute;n',
                                      buttons: Ext.MessageBox.OK,
                                      icon: Ext.MessageBox.WARNING
                                  });
					}
					});
					// gridListaFac.getEl().mask('Procesando...');
				}
			},"|",{
				text: bundle.getMsg('label.confirm'),
				id: 'btnConfirmar',
				disabled: true,
				icon: contextrootpath + '/resources/img/check.png',				
				handler: function(){
					gridListaFac.getEl().mask('Procesando...');
					var data = [];
					listaFacturaStore.each(function(record) {
						var row = {};
						record.fields.each(function(item,index,length) {
							row[item.name] = record.get(item.name);
						});
						data.push(row);
					});
					var params = {
						ebeln: Ext.getCmp('ebeln').getValue(),
						indicator: 'A',
						packingList: data
					};
					Ext.Ajax.request({
					method: 'POST',
					url: contextrootpath + '/provider/getPackingList.action',
					jsonData: params,
					waitMsg: 'Loading',
					success: function(result) {
						gridListaFac.getEl().unmask();
						var footerMessage = new Ext.Panel({
							html: lang == 'es' ? footerMsgEs: footerMsgEn,
							renderTo: 'footerMessage'
						});
						// console.log(result);
						var jsonData = Ext.util.JSON.decode(result.responseText);
						// console.log(jsonData);
						if(jsonData.success) {
							listaFacturaStore.loadData(jsonData);
							Ext.getCmp('btnConfirmar').setDisabled(true);
						} else {
							Ext.MessageBox.show({
                                      title: 'Mensaje',
                                      msg: 'Pedido no asignado a proveedor, consulte a CxP',
                                      buttons: Ext.MessageBox.OK,
                                      icon: Ext.MessageBox.WARNING
                                  });
						}
					},
					failure: function() {
						gridListaFac.getEl().unmask();
						Ext.MessageBox.show({
                                      title: 'Atencion',
                                      msg: 'No se pudo obtener informaci&oacute;n',
                                      buttons: Ext.MessageBox.OK,
                                      icon: Ext.MessageBox.WARNING
                                  });
					}
					});
				}
			}]
		});
		
		function filtrarFacturas() {
			var frm = Ext.getCmp('listaFacturasForm').getForm();	
			if(frm.isValid()){
				listaFacturaStore.removeAll();
				var params = {
					ebeln: Ext.getCmp('ebeln').getValue(),
					indicator: 'C'
				};
				Ext.Ajax.request({
					method: 'POST',
					url: contextrootpath + '/provider/getPackingList.action',
					jsonData: params,
					waitMsg: 'Loading',
					success: function(result) {
						console.log(result);
						var jsonData = Ext.util.JSON.decode(result.responseText);
						console.log(jsonData);
						if(jsonData.success) {
							listaFacturaStore.loadData(jsonData);
							Ext.getCmp('btnConfirmar').setDisabled(true);
						} else {
							var msgDisplay = "";
							jsonData.msg = jsonData.msg.substring(7);
							if(jsonData.msg == "E_ERROR3") {
								msgDisplay = lang == 'es' ? "No se permite cargar el packing list" : "It is not allowed to load packing list";
							} else if(jsonData.msg == "E_ERROR2") {
								msgDisplay = lang == 'es' ? "Pedido ya cargado" : "Purchase Order already loaded";							} else if(jsonData.msg =="E_ERROR" ) {
								msgDisplay = lang == 'es' ? "Pedido no asignado a proveedor" : "Unallocated Purchase Order to Supplier";
							} else {
								msgDisplay = jsonData.msg ? jsonData.msg : bundle.getMsg('label.nodata');
							}
							Ext.MessageBox.show({
                                      title:  bundle.getMsg('label.message'),
                                      msg: msgDisplay,
                                      buttons: Ext.MessageBox.OK,
                                      icon: Ext.MessageBox.WARNING
                            });
						}
					},
					failure: function() {
						Ext.MessageBox.show({
                                      title: 'Atencion',
                                      msg: 'No se pudo obtener informaci&aocute;n',
                                      buttons: Ext.MessageBox.OK,
                                      icon: Ext.MessageBox.WARNING
                                  });
					}
				});
				
				// listaFacturaStore.load({
					// params:{						
						// ebeln: Ext.getCmp('ebeln').getValue(),
						// indicator: 'C'
					// }
				// });
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
