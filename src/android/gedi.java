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
        if ( action.equals( "print" ) ) 
		{
            String texto = args.getString( 0 );
            this.print( texto, callbackContext );
            return true;
        }
		
        return false;
    }

    private void print( String texto, CallbackContext callbackContext ) 
	{
        if ( texto != null && texto.length( ) > 0 ) 
		{
			Thread t = new Thread( ) 
			{
				@Override
				public void run( ) 
				{
					   //Atividade = new Activity( );
					   
					   GEDI.init( Activity.getApplicationContext( ) );
					   
					   //iGedi = GEDI.getInstance( Atividade.getApplicationContext( ) );
					   
					   //IPRNTR iPrntr = iGedi.getPRNTR( );

					   //tPRNTR.DrawString( Atividade.getApplicationContext( ), iPrntr, "CENTER", 0, 0, "NORMAL", false, false, false, 17, texto );

					   /*tPRNTR.DrawString(getApplicationContext(), iPrntr, "CENTER", 0, 0, "NORMAL",
							   true, false, false, 17, "______________________________________");

					   tPRNTR.DrawBlankLine(10, iPrntr);

					   tPRNTR.DrawString(getApplicationContext(), iPrntr, "CENTER", 0, 0, "NORMAL",
							   true, false, false, 17, pedidoComprovante);

					   tPRNTR.DrawString(getApplicationContext(), iPrntr, "CENTER", 0, 0, "NORMAL",
							   true, false, false, 17, getResources().getString(R.string.nota));

					   tPRNTR.DrawBarCode( iPrntr, GEDI_PRNTR_e_BarCodeType.QR_CODE, 300, 300, "www.ger7.com.br" );

					   Random rn = new Random();
					   int senha = rn.nextInt(1000) + 1;

					   tPRNTR.DrawString(getApplicationContext(), iPrntr, "CENTER", 0, 0, "NORMAL",
							   true, false, false, 20, "SENHA: " + Integer.toString(senha) );

					   tPRNTR.DrawBlankLine(140, iPrntr);*/
					   //this.callbackContext.success( texto );
					
				}
			};
			
			try 
			{
				t.start( );
				callbackContext.success( texto );
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
