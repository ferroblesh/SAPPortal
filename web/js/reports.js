Ext.onReady(function(){
	var lang;
	var params = Ext.urlDecode(window.location.search.substring(1));
	lang = params.language ? params.language : 'es'; 
	Ext.QuickTips.init();
	getUnreadNotificationsNumber();
	Ext.form.Field.prototype.msgTarget = 'side';
	
	bundle = new Ext.i18n.Bundle({bundle:'messages', path: contextrootpath + '/resources/language', lang: lang});
	bundle.onReady(function(){		
		
		// var gridReports = new Ext.grid.GridPanel({
			// store: reportsStore,
			// columns: [
			          // {header: bundle.getMsg('label.invoicereports'), width: 100, dataIndex: 'nombrerepo', sortable : false}
			// ],
            // viewConfig: {
              // forceFit: true,
              // scrollOffset: 0
            // },		
			// border: false,
			// stripeRows: true,
			// anchor: '100% 100%',
			// id: 'gridReports',
			// title: 'Reporte de Compras',
			// columnLines: true,
			// autoScroll: true,
			// loadMask: true,
			// selModel: new Ext.grid.RowSelectionModel({singleSelect: true}),
			// listeners: {
				// rowclick: function(t,rowIndex,e) {
					// var record = gridReports.getStore().getAt(rowIndex);
					// var reportPanel = Ext.getCmp('reportPanel');
					// reportPanel.removeAll();
					// Ext.Ajax.request({
						// url: contextrootpath + '/provider/existReport.action',
						// method: 'GET',
						// params: {
							// idrepo: record.data.idrepo,
							// idgrurepo : record.data.idgrurepo,
							// nombrerepo: record.data.nombrerepo
						// },
						// success: function(result, request) {
							// var fileBox = new Ext.BoxComponent({
								// autoEl: {
										// tag: 'iframe',
										// style: 'height: 100%; width: 100%',
										// scrolling: 'no',
										// src: contextrootpath + '/provider/getReport.pdf?idrepo=' + record.data.idrepo + '&idgrurepo=' + record.data.idgrurepo + '&nombrerepo=' + record.data.nombrerepo
									// }
							// });
							// reportPanel.add(fileBox);
							// reportPanel.doLayout();
						// },
						// failure: function(result, request) {
							// Ext.MessageBox.show({
								// title: 'Mensaje',
								// msg: Ext.util.JSON.decode(result.responseText).msg,
								// buttons: Ext.MessageBox.OK,
								// icon: Ext.MessageBox.WARNING
							// })
							// reportPanel.doLayout();
						// }
					// });
					
				// }
			// }
		// });
		
		var mainPanel = new Ext.Panel({
			renderTo: 'reportsMainPanel',
			title: bundle.getMsg('label.managementreports'),
			plugins: [new Ext.ux.FitToParent("reportsMainPanel")],
			layout: 'fit',
			margins: '0 0 0 0',
			tbar: [{
				text:bundle.getMsg('label.downloadall'),
				handler: function() {
					document.location.href = contextrootpath + '/provider/getAllReports.action';
				}
			},'->',{
				xtype: 'box',
				hidden: Ext.isEmpty(userLink),
				autoEl: {tag: 'a', href: userLink, html: 'Dashboard Proveedor'}
			}],
            anchor: '100% 100%',
            // height: 400,
			// autoHeight: true,
            items: [{
            	layout: 'border',
            	items: [{
            		region: 'west',
            		id: 'westPanel',
            		collapsible: false,
            		collapseMode: 'mini',
            		layout: 'anchor',
            		split: true,
                    width: 200,
            		margins: '0 0 0 0',
            		items: []
            	},{
            		region: 'center',
            		layout: 'fit',
            		border: true,
            		id: 'centerPanel',
            		items: {
            			title: bundle.getMsg('label.report'),
						id: 'reportPanel',
            			xtype: 'panel',	
            			layout: 'fit'
            		} 
            	}]
            }]
            
		});
		
		Ext.Ajax.request({
			url: contextrootpath + '/provider/getReports.action', 
			method: 'GET',
			success: function(result, request) {
				if(Ext.util.JSON.decode(result.responseText).success == false){
				Ext.MessageBox.show({
					title: 'WARNING',
					msg: Ext.util.JSON.decode(result.responseText).msg,
					buttons: Ext.MessageBox.OK,
					icon: Ext.MessageBox.WARNING
				});				
				}else {
					var data = Ext.util.JSON.decode(result.responseText).data;
					console.log(data);
					var Tree = Ext.tree;
					
					var root = new Tree.AsyncTreeNode({
						text: 'Reportes',
						draggable: false,
						id: 'source',
						children: data
					});
					
					var tree = new Tree.TreePanel({
						animate:true,
						enableDD:false,
						loader: new Tree.TreeLoader(), // Note: no dataurl, register a TreeLoader to make use of createNode()
						lines: true,
						root: root,
						rootVisible: false,
						selModel: new Ext.tree.DefaultSelectionModel(),
						containerScroll: false,
						listeners: {
							click : function(node,e) {
								var reportPanel = Ext.getCmp('reportPanel');
								reportPanel.removeAll();
								console.log('node' + node);
								if(node.leaf) {
									 Ext.Ajax.request({
										url: contextrootpath + '/provider/existReport.action',
										method: 'GET',
										params: {
											idrepo: node.attributes.idrepo,
											idgrurepo : node.attributes.idgrurepo,
											nombrerepo: node.attributes.nombrerepo
										},
										success: function(result, request) {
											if(Ext.decode(result.responseText).success){
												var doc = contextrootpath + '/provider/getReport.pdf?idrepo=' + node.attributes.idrepo + '&idgrurepo=' + node.attributes.idgrurepo + '&nombrerepo=' + node.attributes.nombrerepo;
												
												var doc = encodeURIComponent(doc);
												var url = "http://" + document.domain + doc 
												
												var fileBox = new Ext.BoxComponent({
													id: 'container',
													autoEl: {
															tag: 'iframe',
															style: 'height: 100%; width: 100%',
															scrolling: 'no',
															// src: contextrootpath + '/provider/getReport.pdf?idrepo=' + record.data.idrepo + '&idgrurepo=' + record.data.idgrurepo + '&nombrerepo=' + record.data.nombrerepo
															src: contextrootpath + '/resources/web/viewer.html?file=' + url
														}
														
												});
												
												reportPanel.add(fileBox);
												reportPanel.doLayout();
											} else {
												Ext.MessageBox.show({
												title: 'Mensaje',
												msg: Ext.util.JSON.decode(result.responseText).msg,
												buttons: Ext.MessageBox.OK,
												icon: Ext.MessageBox.WARNING
											})
											}	
										},
										failure: function(result, request) {
											Ext.MessageBox.show({
												title: 'Mensaje',
												msg: Ext.util.JSON.decode(result.responseText).msg,
												buttons: Ext.MessageBox.OK,
												icon: Ext.MessageBox.WARNING
											})
										}
									});
								}
							}
						}
					});
					
					Ext.getCmp('westPanel').add(tree);
					Ext.getCmp('westPanel').doLayout();
				}
			}
		});
		
	}); // end bundle
				
});

