package com.wise.viewResolver;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.wise.model.EstadoCuentaDto;

@Component(value = "accountStatusView")
public class AccountStatusView extends AbstractExcelView {
	
	private static final Logger LOGGER = Logger.getLogger(AccountStatusView.class);
	private Map<String, Integer> headers = new HashMap<String, Integer>() {
		private static final long serialVersionUID = 1L;		
		{
			put("No. Doc.",0);
			put("Clase doc.", 1);
			put("Fe.contab.",2);
			put("Venc.neto",3);
			put("Referencia",4);
			put("Importe en MD",5);
			put("Mon.",6);
			put("Doc. comp.",7);
			put("Fecha compensación",8);			
		}
	};
	
	private static Map<String, HSSFCellStyle> styles;

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if(model != null) {
			List<EstadoCuentaDto> dataOpen = (List<EstadoCuentaDto>) model.get("dataOpen");
			List<EstadoCuentaDto> dataComp = (List<EstadoCuentaDto>) model.get("dataComp");
			styles = AccountStatusView.createdStyles(workbook);
			String titleName = "Estado de Cuenta";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String fileName = titleName.toUpperCase() + "_" + sdf.format(new Date());
			fileName = fileName.replace("/","").replace(" ","_");
			response.setHeader("Content-Disposition", "attachment; filename="+ fileName + ".xls");
			
			HSSFSheet sheet = workbook.createSheet(titleName);
			sheet.setDefaultColumnWidth(18);		
			
			int rowcount = 0;
			
			HSSFRow infoRow = sheet.createRow(rowcount++);
			infoRow = sheet.createRow(rowcount++);
			HSSFCell infoCell = infoRow.createCell(0);
			infoCell.setCellValue("Reporte:");
			
			infoCell = infoRow.createCell(1);
			infoCell.setCellValue(titleName);
			
			infoRow = sheet.createRow(rowcount++);
			infoCell = infoRow.createCell(0);
			infoCell.setCellValue("Creado:");
			infoCell = infoRow.createCell(1);
			infoCell.setCellValue(sdf.format(new Date()));
			
			infoRow = sheet.createRow(rowcount++);
			infoCell = infoRow.createCell(0);
			infoCell.setCellValue("Partidas Abiertas");			
			
			HSSFRow row = sheet.createRow(rowcount++);
			HSSFCellStyle headerStyle = workbook.createCellStyle();
			headerStyle.setFillForegroundColor(HSSFColor.INDIGO.index);
			headerStyle.setFillPattern((short)1);
			headerStyle.setWrapText(true);			
			
			HSSFFont font = workbook.createFont();
			font.setColor(HSSFColor.WHITE.index);
			font.setFontHeightInPoints((short)9);
			font.setBoldweight((short)700);
			
			headerStyle.setFont(font);
			for(String headerValue : headers.keySet()) {
				HSSFCell headerCell = row.createCell(headers.get(headerValue));
				headerCell.setCellValue(headerValue);
				headerCell.setCellStyle(headerStyle);								
			}
			
			if(dataOpen != null && !dataOpen.isEmpty()) {
				for(EstadoCuentaDto dataDto: dataOpen) {
					HSSFRow dataRow = sheet.createRow(rowcount++);
					addRow(dataRow,dataDto,rowcount,workbook);
				}
			}
			
			infoRow = sheet.createRow(rowcount++);
			infoCell = infoRow.createCell(0);
			infoCell.setCellValue("Partidas compensadas");
			
			row = sheet.createRow(rowcount++);
			headerStyle = workbook.createCellStyle();
			headerStyle.setFillForegroundColor(HSSFColor.INDIGO.index);
			headerStyle.setFillPattern((short)1);
			headerStyle.setWrapText(true);	
			
			font = workbook.createFont();
			font.setColor(HSSFColor.WHITE.index);
			font.setFontHeightInPoints((short)9);
			font.setBoldweight((short)700);
			
			headerStyle.setFont(font);
			for(String headerValue : headers.keySet()) {
				HSSFCell headerCell = row.createCell(headers.get(headerValue));
				headerCell.setCellValue(headerValue);
				headerCell.setCellStyle(headerStyle);								
			}
			
			if(dataComp != null && !dataComp.isEmpty()) {
				for(EstadoCuentaDto dataDto: dataComp) {
					HSSFRow dataRow = sheet.createRow(rowcount++);
					addRow(dataRow,dataDto,rowcount,workbook);
				}
			}
		}
		
		
		
	}
	private void addRow(HSSFRow dataRow, EstadoCuentaDto dataDto, int rowcount, HSSFWorkbook workbook) {
		try {
		HSSFCell cell = this.createCell(dataRow, "No. Doc.", rowcount);
		cell.setCellValue(dataDto.getBelnr());
		
		cell = this.createCell(dataRow, "Clase doc.", rowcount);
		cell.setCellValue(dataDto.getBlart());
		
		cell = this.createCell(dataRow, "Fe.contab.", rowcount);
		cell.setCellValue(dataDto.getBudat());
		
		cell = this.createCell(dataRow, "Venc.neto", rowcount);
		cell.setCellValue(dataDto.getZfbdt());
		
		cell = this.createCell(dataRow, "Referencia", rowcount);
		cell.setCellValue(dataDto.getXblnr());
				
		cell = this.createCell(dataRow, "Importe en MD", rowcount);
		cell.setCellValue(dataDto.getWrbtr());
		
		cell = this.createCell(dataRow, "Mon.", rowcount);
		cell.setCellValue(dataDto.getWaers());
		
		cell = this.createCell(dataRow, "Doc. comp.", rowcount);
		cell.setCellValue(dataDto.getAugbl());
		
		cell = this.createCell(dataRow, "Fecha compensación", rowcount);
		cell.setCellValue(dataDto.getAugdt());		
		
		} catch (Exception e) {
			LOGGER.error("ERROR PurchaseWOInvoiceView:",e);
		}
		
	}
	
	private HSSFCell createCell(HSSFRow hssfRow ,String header,int rowCount){
		HSSFCellStyle cellStyle = rowCount % 2 == 0 ? styles.get("evenCellStyle") : styles.get("oddCellStyle");
		HSSFCell hssfCell =  hssfRow.createCell(headers.get(header));
		hssfCell.setCellStyle(cellStyle);
		return  hssfCell;
	}
	
	private static Map<String, HSSFCellStyle> createdStyles(HSSFWorkbook wb){
        Map<String, HSSFCellStyle> styles = new HashMap<String, HSSFCellStyle>();
        HSSFCellStyle cellStyle = wb.createCellStyle();
        
        short evenRowColorIndex = IndexedColors.GREY_25_PERCENT.getIndex();         
        cellStyle.setFillForegroundColor(evenRowColorIndex);
        cellStyle.setBorderRight(CellStyle.BORDER_THIN);
        cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
        cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        styles.put("evenCellStyle", cellStyle);
        
        cellStyle = wb.createCellStyle();                 
        short oddRowColorIndex = IndexedColors.WHITE.getIndex();
        cellStyle.setFillForegroundColor( oddRowColorIndex);
        cellStyle.setBorderRight(CellStyle.BORDER_THIN);
        cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
        cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        styles.put("oddCellStyle", cellStyle);
        
        CreationHelper createHelper = wb.getCreationHelper();
        cellStyle = wb.createCellStyle();
        cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("mm-dd-yyyy"));
        evenRowColorIndex = IndexedColors.GREY_25_PERCENT.getIndex();         
        cellStyle.setFillForegroundColor(evenRowColorIndex);
        cellStyle.setWrapText(true);
        cellStyle.setBorderRight(CellStyle.BORDER_THIN);
        cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
        cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);            
        styles.put("evenDateCellStyle", cellStyle);
                 
        cellStyle = wb.createCellStyle();
        cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("mm-dd-yyyy"));                  
        cellStyle.setFillForegroundColor(oddRowColorIndex);
        cellStyle.setWrapText(true);
        cellStyle.setBorderRight(CellStyle.BORDER_THIN);
        cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
        cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);            
        styles.put("oddDateCellStyle", cellStyle);                           
        
        return styles;
    }

}
