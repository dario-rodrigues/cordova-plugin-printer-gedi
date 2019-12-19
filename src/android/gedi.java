package cordova.plugin.printer.gedi;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import br.com.gertec.gedi.GEDI;
import br.com.gertec.gedi.enums.GEDI_PRNTR_e_Alignment;
import br.com.gertec.gedi.enums.GEDI_PRNTR_e_BarCodeType;
import br.com.gertec.gedi.enums.GEDI_PRNTR_e_Status;
import br.com.gertec.gedi.exceptions.GediException;
import br.com.gertec.gedi.interfaces.IGEDI;
import br.com.gertec.gedi.interfaces.IPRNTR;
import br.com.gertec.gedi.structs.GEDI_PRNTR_st_BarCodeConfig;
import br.com.gertec.gedi.structs.GEDI_PRNTR_st_PictureConfig;
import br.com.gertec.gedi.structs.GEDI_PRNTR_st_StringConfig;

/**
 * This class echoes a string called from JavaScript.
 */
public class gedi extends CordovaPlugin 
{
	IGEDI iGedi = null;	
	IPRNTR iprntr;
	
	public boolean execute( String action, JSONArray args, CallbackContext callbackContext ) throws JSONException 
	{
        if ( action.equals( "printText" ) ) 
		{            
            this.printText( args, callbackContext );
            return true;
        }
		
		if ( action.equals( "printBarcode" ) ) 
		{            
            this.printText( args, callbackContext );
            return true;
        }
		
        return false;
    }

    private void printText( JSONArray args, CallbackContext callbackContext ) throws JSONException
	{
		JSONObject jsonObject = new JSONObject( );
		jsonObject = args.getJSONObject( 0 );
		
		String text     = jsonObject.getString( "text"     );
		String position = jsonObject.getString( "position" );
		String font     = jsonObject.getString( "font"     );
		
		int blankLines = Integer.parseInt( jsonObject.getString( "blankLines" ) );
		int size       = Integer.parseInt( jsonObject.getString( "size"       ) );
		
		boolean bold      = Boolean.parseBoolean( jsonObject.getString( "bold"      ) ); 
		boolean italic    = Boolean.parseBoolean( jsonObject.getString( "italic"    ) );
		boolean underline = Boolean.parseBoolean( jsonObject.getString( "underline" ) );
		
		String OK = "ok";
		
        if ( text != null && text.length( ) > 0 ) 
		{
			Thread t = new Thread( ) 
			{
				@Override
				public void run( ) 
				{
				   GEDI.init( cordova.getActivity( ).getApplicationContext( ) );				   
				   iGedi = GEDI.getInstance( cordova.getActivity( ).getApplicationContext( ) );				   
				   IPRNTR iPrntr = iGedi.getPRNTR( );
				   tPRNTR.DrawString( cordova.getActivity( ).getApplicationContext( ), iPrntr, position, 0, blankLines, font, bold, italic, underline, size, text );
				}
			};
			
			try 
			{
				t.start( );
				callbackContext.success( OK );
			} catch ( Exception ex )
			{
				ex.printStackTrace( );
				callbackContext.error( ex.getMessage( ) );
			}		
        } else 
		{
            callbackContext.error( "Texto invalido para impressao." );
        }
    }
	
	private void printBarcode( JSONArray args, CallbackContext callbackContext ) throws JSONException
	{
		JSONObject jsonObject = new JSONObject( );
		jsonObject = args.getJSONObject( 0 );
		
		String text   = jsonObject.getString( "text"   );
		String type   = jsonObject.getString( "type"   );
		int    height = Integer.parseInt( jsonObject.getString( "height" ) );
		int    width  = Integer.parseInt( jsonObject.getString( "width"  ) ); 
		
		String OK = "ok";
		
        if ( text != null && text.length( ) > 0 ) 
		{
			Thread t = new Thread( ) 
			{
				@Override
				public void run( ) 
				{
				   GEDI.init( cordova.getActivity( ).getApplicationContext( ) );				   
				   iGedi = GEDI.getInstance( cordova.getActivity( ).getApplicationContext( ) );				   
				   IPRNTR iPrntr = iGedi.getPRNTR( );
				   
				   switch ( type )
				   {
					    case "AZTEC":
							tPRNTR.DrawBarCode( iPrntr, GEDI_PRNTR_e_BarCodeType.AZTEC, height, width, text );
							break;
						  
						case "CODABAR":
							tPRNTR.DrawBarCode( iPrntr, GEDI_PRNTR_e_BarCodeType.CODABAR, height, width, text );
							break;
						  
						case "CODE_128":
							tPRNTR.DrawBarCode( iPrntr, GEDI_PRNTR_e_BarCodeType.CODE_128, height, width, text );
							break;
						  
						case "CODE_39":
							tPRNTR.DrawBarCode( iPrntr, GEDI_PRNTR_e_BarCodeType.CODE_39, height, width, text );
							break;
						  
						case "CODE_93":
							tPRNTR.DrawBarCode( iPrntr, GEDI_PRNTR_e_BarCodeType.CODE_93, height, width, text );
							break;
						  
						case "DATA_MATRIX":
							tPRNTR.DrawBarCode( iPrntr, GEDI_PRNTR_e_BarCodeType.DATA_MATRIX, height, width, text );
							break;
						  
						case "EAN_13":
							tPRNTR.DrawBarCode( iPrntr, GEDI_PRNTR_e_BarCodeType.EAN_13, height, width, text );
							break;
						  
						case "EAN_8":
							tPRNTR.DrawBarCode( iPrntr, GEDI_PRNTR_e_BarCodeType.EAN_8, height, width, text );
							break;
						  
						case "ITF":
							tPRNTR.DrawBarCode( iPrntr, GEDI_PRNTR_e_BarCodeType.ITF, height, width, text );
							break;
						  
						case "MAXICODE":
							tPRNTR.DrawBarCode( iPrntr, GEDI_PRNTR_e_BarCodeType.MAXICODE, height, width, text );
							break;
						  
						case "PDF_417":						
							tPRNTR.DrawBarCode( iPrntr, GEDI_PRNTR_e_BarCodeType.PDF_417, height, width, text );
							break;
						  
						case "RSS_14":
							tPRNTR.DrawBarCode( iPrntr, GEDI_PRNTR_e_BarCodeType.RSS_14, height, width, text );
							break;
						  
						case "RSS_EXPANDED":
							tPRNTR.DrawBarCode( iPrntr, GEDI_PRNTR_e_BarCodeType.RSS_EXPANDED, height, width, text );
							break;
						  
						case "UPC_A":
							tPRNTR.DrawBarCode( iPrntr, GEDI_PRNTR_e_BarCodeType.UPC_A, height, width, text );
							break;
						  
						case "UPC_E":
							tPRNTR.DrawBarCode( iPrntr, GEDI_PRNTR_e_BarCodeType.UPC_E, height, width, text );
							break;
						  
						case "UPC_EAN_EXTENSION":
							tPRNTR.DrawBarCode( iPrntr, GEDI_PRNTR_e_BarCodeType.UPC_EAN_EXTENSION, height, width, text );
							break;
						  
						default:
							tPRNTR.DrawBarCode( iPrntr, GEDI_PRNTR_e_BarCodeType.QR_CODE, height, width, text );
							break;
						  
				   }
				}
			};
			
			try 
			{
				t.start( );
				callbackContext.success( OK );
			} catch ( Exception ex )
			{
				ex.printStackTrace( );
				callbackContext.error( ex.getMessage( ) );
			}		
        } else 
		{
            callbackContext.error( "Texto invalido para impressao." );
        }
    }
}
