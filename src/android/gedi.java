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
public class gedi extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("print")) {
            String texto = args.getString(0);
            this.print(texto, callbackContext);
            return true;
        }
        return false;
    }

    private void print(String texto, CallbackContext callbackContext) {
        if (texto != null && texto.length() > 0) {
            callbackContext.success(texto);
        } else {
            callbackContext.error( "Texto invalido para impressao." );
        }
    }
}
