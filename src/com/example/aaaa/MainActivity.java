package com.example.aaaa;

import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import android.graphics.Color;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

//import com.example.reoutines.R;









import android.R.color;
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
	DateFormat formatter = new SimpleDateFormat("hh:mma");
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		TableRow[] Trow= new TableRow[9];
		Date[] startTime=new Date[9], 	endTime=new Date[9];
		TextView Day= (TextView) findViewById(R.id.Day);
	    try{
	    	String day,timeRange;
	    	String[] subject,teacher,room;
	    	char classType;
	    	int period;
	    	String temp;
	    	
	    	  	
	    	
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
            
            Calendar calendar = Calendar.getInstance();
            int today = calendar.get(Calendar.DAY_OF_WEEK); 
            int minr= 6+((today-1)*39);
            int rdiff=3;         
            
           
            row=mySheet.getRow(minr);
            cell= row.getCell(0);
            Day.setText(cell.toString());
            
            
            
            for (int i=0; i<9;i++){
            	TableRow tableRow= new TableRow(this);
            	if (i%2==0){
            	tableRow.setBackgroundColor(Color.LTGRAY);
            	}
            	tableRow.setLayoutParams(new TableLayout.LayoutParams(
            			TableLayout.LayoutParams.MATCH_PARENT,
            			TableLayout.LayoutParams.MATCH_PARENT,1.0f
            			));
            	table.addView(tableRow);
            	row=mySheet.getRow(minr);
            		cell=row.getCell(1);
            		TextView Period = new TextView(this);
            		Period.setLayoutParams(new TableRow.LayoutParams(
                			TableRow.LayoutParams.MATCH_PARENT,
                			TableRow.LayoutParams.MATCH_PARENT,1.0f
                			));
            		//Period.setTextSize(3, 10);
            		Period.setText(cell.toString().substring(0,1)+". ");
            		tableRow.addView(Period);
            		//Toast.makeText(this, "cell Value: " + cell.toString(), Toast.LENGTH_SHORT).show();
            		cell=row.getCell(2);
            		timeRange =cell.toString();
            		//startTime[i]cell=timeRange.substring(0,9);
            		TextView Time1 = new TextView(this);
            		Time1.setLayoutParams(new TableRow.LayoutParams(
                			TableRow.LayoutParams.MATCH_PARENT,
                			TableRow.LayoutParams.MATCH_PARENT,1.0f
                			));
            		//Time1.setTextSize(3, 10);
            		temp=timeRange.substring(0,9);            		
            		Time1.setText(temp);
            		temp=temp.replace(".", "");
            		startTime[i] = (Date)formatter.parse(temp.trim());

            		tableRow.addView(Time1);
            		TextView Time2 = new TextView(this);
            		Time2.setLayoutParams(new TableRow.LayoutParams(
                			TableRow.LayoutParams.MATCH_PARENT,
                			TableRow.LayoutParams.MATCH_PARENT,1.0f
                			));
            		//Time2.setTextSize(3, 10);
            		temp=timeRange.substring(11);            		
            		Time2.setText(temp);
            		temp=temp.replace(".", "");
            		endTime[i] = (Date)formatter.parse(temp.trim());
            		tableRow.addView(Time2);  		
            		cell=row.getCell(26*4+1);
            		TextView Subject2 = new TextView(this);
            		Subject2.setLayoutParams(new TableRow.LayoutParams(
                			TableRow.LayoutParams.MATCH_PARENT,
                			TableRow.LayoutParams.MATCH_PARENT,1.0f
                			));
            		//Subject2.setTextSize(3, 10);            	
            		try{
            			Subject2.setText(cell.toString());
            			tableRow.addView(Subject2);
            			row=mySheet.getRow(minr+1);
            			cell=row.getCell(26*4+1);
            			TextView Teacher = new TextView(this);
            			Teacher.setLayoutParams(new TableRow.LayoutParams(
                    			TableRow.LayoutParams.MATCH_PARENT,
                    			TableRow.LayoutParams.MATCH_PARENT,1.0f
                    			));
            			//Teacher.setTextSize(3, 10);            			
                		Teacher.setText(cell.toString().trim());
                		tableRow.addView(Teacher);
                		
                		
                		row=mySheet.getRow(minr+2);
                		cell=row.getCell(26*4+1);
            			TextView ClassType = new TextView(this);
            			ClassType.setLayoutParams(new TableRow.LayoutParams(
                    			TableRow.LayoutParams.MATCH_PARENT,
                    			TableRow.LayoutParams.MATCH_PARENT,1.0f
                    			));
            			//ClassType.setTextSize(3, 10);            			
                		ClassType.setText(cell.toString().trim());
                		tableRow.addView(ClassType);
                		
                		try{
                			cell=row.getCell(26*4+2);
                			TextView Room = new TextView(this);
                			Room.setLayoutParams(new TableRow.LayoutParams(
                        			TableRow.LayoutParams.MATCH_PARENT,
                        			TableRow.LayoutParams.MATCH_PARENT,1.0f
                        			));
                			//Room.setTextSize(3, 10);                			
                			Room.setText(cell.toString().trim());
                			tableRow.addView(Room);
                		}
                		catch (Exception e){
                			TextView Room = new TextView(this);
                			Room.setText("No Room");
                			tableRow.addView(Room);
                		}
            			
            		}
            		catch(Exception e){
            			Subject2.setText("no Period");
            			tableRow.addView(Subject2);
            		}    		
            	Trow[i]=tableRow;	
            	minr+=rdiff;
            	
            }
            myWorkBook.close();   
    	    
        }catch (Exception e){}

	    try{getCurrentClass(Trow, startTime, endTime);}
	    catch(Exception e){
	    	Day.setText(e.toString());
	    }

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
	private  void getCurrentClass(TableRow[] row, Date[] Stime,Date[] Etime ){
		Date date=  new Date();
		String s=formatter.format(date);
		try{
		date=formatter.parse(s);
		
		for (int i=0;i<9;i++){
			if (date.after(Stime[i])&& date.before(Etime[i])){
				row[i].setBackgroundColor(Color.GREEN);
			}
		}
		}
		catch(Exception e){
			
		}
	}
}
