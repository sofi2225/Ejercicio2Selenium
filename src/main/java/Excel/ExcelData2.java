package Excel;


	import java.io.FileInputStream;

	import java.io.IOException;



import org.apache.poi.ss.usermodel.CellType;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



	public class ExcelData2 {
		
		
		static int contadorF=2;
		
		public static Object[][] getDataTest(String file) throws IOException{
			
		
			
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//"+file+".xlsx");
		
			XSSFWorkbook libro = new XSSFWorkbook(fis);
				
			XSSFSheet hoja = libro.getSheetAt(0);
			
			
				Object[][] data = new Object[contadorF][hoja.getRow(0).getPhysicalNumberOfCells()];
				
				for (int i = 0; i < contadorF; i++) {
					
					for (int j = 0; j <hoja.getRow(0).getPhysicalNumberOfCells() ; j++) {
						
						XSSFCell value = hoja.getRow(i).getCell(j);
						
							if (value != null) { value.setCellType(CellType.STRING);
						
								data[i][j] = value.toString();
						
							}else {
							
								data[i][j] = "";
							}

		
				}
					libro.close();	
			}
				
				
				return data;

		
			
		}	
	}
		
	
	
	

