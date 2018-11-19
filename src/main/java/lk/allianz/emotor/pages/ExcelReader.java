package lk.allianz.emotor.pages;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellReference;

public class ExcelReader {

	private static Workbook workbook;
	private String path;
	private Iterator<Row> rowIterator;
	private static Sheet sheet;
	
	public ExcelReader (String path, int sheetIndex) throws EncryptedDocumentException, InvalidFormatException, IOException {
		this.path = path;
		//path="src\\main\\java\\Resources\\test_data_sheet.xlsx";
		workbook=WorkbookFactory.create(new File(path));
		sheet=workbook.getSheetAt(sheetIndex);
		rowIterator = sheet.rowIterator();

	}
	
	public static String excelData(String key, int row) {
		

		return workbook.getSheetAt(0).getRow(row).getCell(getColumnId(key)).toString(); 
		
	}
	
	public static int getColumnId(String column) {
		int index=0;
		
		Iterator<Row> rowIt = sheet.iterator();

	    while(rowIt.hasNext()) {
	      Row row = rowIt.next();

	      Iterator<Cell> cellIterator = row.cellIterator();

	      while (cellIterator.hasNext()) {
	        Cell cell = cellIterator.next();
	        if(cell.toString().equalsIgnoreCase(column)) {
	        	index=cell.getColumnIndex();
	        }
	      }

	    }

		
		return index;
	}
	
	

	
	
}
