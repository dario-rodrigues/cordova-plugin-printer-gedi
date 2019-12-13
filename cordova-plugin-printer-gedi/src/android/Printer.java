/*
    Copyright 2013-2014 appPlant UG

    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
 */

package cordova.plugin.printer.gedi;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintJob;
import android.print.PrintManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import br.com.gertec.gedi.GEDI;
import br.com.gertec.gedi.enums.GEDI_PRNTR_e_BarCodeType;
import br.com.gertec.gedi.interfaces.IGEDI;
import br.com.gertec.gedi.interfaces.IPRNTR;
import ger7.com.br.pos7api.POS7API;
import ger7.com.br.pos7api.ParamIn;
import ger7.com.br.pos7api.ParamOut;

public class Printer extends CordovaPlugin {

    private WebView view;

    private CallbackContext command;

    private static final String DEFAULT_DOC_NAME = "unknown";

    /**
     * Executes the request.
     *
     * This method is called from the WebView thread.
     * To do a non-trivial amount of work, use:
     *     cordova.getThreadPool().execute(runnable);
     *
     * To run on the UI thread, use:
     *     cordova.getActivity().runOnUiThread(runnable);
     *
     * @param action   The action to execute.
     * @param args     The exec() arguments in JSON form.
     * @param callback The callback context used when calling back into JavaScript.
     * @return         Whether the action was valid.
     */
    @Override
    public boolean execute (String action, String via,
            CallbackContext callback) throws JSONException {

        command = callback;

        if (action.equalsIgnoreCase("print")) {
            print(via);

            return true;
        }

        // Returning false results in a "MethodNotFound" error.
        return false;
    }

    /**
     * Loads the HTML content into the web view and invokes the print manager.
     *
     * @param args
     *      The exec arguments as JSON
     */
    private void print (final String viaCliente) {
        Thread t = new Thread(){
         @Override
         public void run() {
            try {
               IPRNTR iPrntr = iGedi.getPRNTR();

               tPRNTR.DrawPicture(getApplicationContext(), iPrntr,"CENTER",0,50,160,
                       "logo");

               tPRNTR.DrawBlankLine(20, iPrntr);

               tPRNTR.DrawString(getApplicationContext(), iPrntr, "CENTER", 0, 0, "NORMAL",
                       false, false, false, 17, viaCliente);

               tPRNTR.DrawString(getApplicationContext(), iPrntr, "CENTER", 0, 0, "NORMAL",
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

               tPRNTR.DrawBlankLine(140, iPrntr);
            }catch (Exception e){
            }
         }
      };
      t.start();
    }
}
