package com.example.aaaa;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

//import com.example.reoutines.R;




import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView.FindListener;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
	    try{
	    	String day;
	    	String[] subject,teacher,room;
	    	char classType;
	    	int period;
	    	Time[] startTime,endTime;
	    	TextView Day= (TextView) findViewById(R.id.Day);
	    	
	    	TableLayout table=(TableLayout) findViewById(R.id.tableDisplay);
            // Creating Input Stream
            File file = new File("/storage/sdcard0/asd.xls");
            
            FileInputStream myInput = new FileInputStream(file);
            
            // Create a POIFSFileSystem object
            POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);
            
            // Create a workbook using the File System
            HSSFWorkbook myWorkBook = new HSSFWorkbook(myFileSystem);
 
            // Get the first sheet from workbook
            HSSFSheet mySheet = myWorkBook.getSheetAt(0);
            
            Row row=mySheet.getRow(6);
            Cell cell= row.getCell(0);
            Day.setText(cell.toString());
            
            
            
            int minr= 6;
            int rdiff=3;
            for (int i=0; i<9;i++){
            	TableRow tableRow= new TableRow(this);
            	
            	table.addView(tableRow);
            	row=mySheet.getRow(minr);
            	//for (int j=0; j<3;j++){
            		cell=row.getCell(1);
            		TextView Period = new TextView(this);
            		Period.setText(cell.toString());
            		tableRow.addView(Period);
            		//Toast.makeText(this, "cell Value: " + cell.toString(), Toast.LENGTH_SHORT).show();
            		cell=row.getCell(2);
            		TextView Time = new TextView(this);
            		Time.setText(cell.toString());
            		tableRow.addView(Time);
            	//}
            	minr+=rdiff;
            }
            myWorkBook.close();
 
            
            /** We now need something to iterate through the cells.**/
            //Iterator<Row> rowIter = mySheet.rowIterator();
            /*for (int i=0; i<3;i++) 
            //while(rowIter.hasNext())
            	{
                HSSFRow myRow = (HSSFRow) rowIter.next();
                Iterator<Cell> cellIter = myRow.cellIterator();
                for (int j=0; j<3;j++)
                	//while(cellIter.hasNext())
                	{
                    HSSFCell myCell = (HSSFCell) cellIter.next();
                    Log.w("FileUtils", "Cell Value: " +  myCell.toString());
                    Toast.makeText(this, "cell Value: " + myCell.toString(), Toast.LENGTH_SHORT).show();
                }
            }*/
        }catch (Exception e){e.printStackTrace(); }


		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private static void readExcelFile(Context context) {
		 
            
        return;
    }
 
}
