package lk.allianz.emotor.utilities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelReader {

	private static Workbook workbook;
	private static String path;
	private Iterator<Row> rowIterator;
	private static Sheet sheet;
	private static int sheet_index=0;
	
	public ExcelReader (String path, int sheetIndex) throws EncryptedDocumentException, InvalidFormatException, IOException {
		ExcelReader.path = path;
		//path="src\\main\\java\\Resources\\test_data_sheet.xlsx";
		workbook=WorkbookFactory.create(new File(path));
		sheet=workbook.getSheetAt(sheetIndex);
		rowIterator = sheet.rowIterator();
		sheet_index=sheetIndex;

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
	
	
	
	public static String excelData(String key, int row) {
		

		return workbook.getSheetAt(sheet_index).getRow(row).getCell(getColumnId(key)).toString(); 
		
	}
	
	
	
	
	public static void writeData(String column, int row) throws IOException {

		Cell cell = null;
		cell = sheet.getRow(row).getCell(getColumnId(column));
		cell.setCellValue("Pass");

		FileOutputStream outFile = new FileOutputStream(path);
		workbook.write(outFile);
		outFile.close();
	}

	
	
}
