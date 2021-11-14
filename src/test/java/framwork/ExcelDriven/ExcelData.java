package framwork.ExcelDriven;

import java.io.FileInputStream;
	
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelData {

	
	public ArrayList<String> getData(String Testcase) throws IOException
	{

		ArrayList<String> a=new ArrayList<String>();
		
	FileInputStream fis=new FileInputStream("/Users/abhinavtyagi/Downloads/TestData.xlsx");
	XSSFWorkbook workbook=new XSSFWorkbook(fis);
	
	int sheets=workbook.getNumberOfSheets();
	for(int i=0;i<sheets;i++)
	{
		if(workbook.getSheetName(i).equalsIgnoreCase("testdata"))
		{
			XSSFSheet sheet=workbook.getSheetAt(i);
			Iterator<Row> rows=sheet.iterator();
			Row firstrow=rows.next();
			Iterator<Cell> ce=firstrow.cellIterator();
			int k=0;
			int column=0;
			while(ce.hasNext())
			{
				Cell value=ce.next();
				if(value.getStringCellValue().equalsIgnoreCase("TestCase"))
				{
					column=k;
				}
				k++;
				
			}
			System.out.println(column);
			while(rows.hasNext())
			{
				Row r=rows.next();
				if(r.getCell(column).getStringCellValue().equalsIgnoreCase(Testcase))
				{
					Iterator<Cell>cv=r.cellIterator();
					while(cv.hasNext())
					{
						Cell c=cv.next();
						if(c.getCellTypeEnum()==CellType.STRING)
						{
						
						a.add(c.getStringCellValue());
						}
						else
						{
							if(c.getCellTypeEnum()==CellType.NUMERIC)
							{
								a.add(NumberToTextConverter.toText(c.getNumericCellValue()));
								
							}
						}
					}
				}
			}
			
		}
	}
	return a;
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		

	}

}
