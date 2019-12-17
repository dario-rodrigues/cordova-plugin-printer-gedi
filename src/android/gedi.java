package cordova.plugin.printer.gedi;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;

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
public class gedi extends Activity 
{
	private IGEDI iGedi = null;

    @Override
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
					try 
					{
					   iGedi = GEDI.getInstance( getApplicationContext( ) );
					   
					   IPRNTR iPrntr = iGedi.getPRNTR( );

					   tPRNTR.DrawString( getApplicationContext( ), iPrntr, "CENTER", 0, 0, "NORMAL", false, false, false, 17, texto );

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
					   callbackContext.success( texto );
					} catch ( Exception ex )
					{
						ex.printStackTrace( );
						callback.error( ex.getMessage( ) );
					}
				}
			};
			
			t.start( );            
        } else 
		{
            callbackContext.error( "Texto invalido para impressao." );
        }
    }
}
