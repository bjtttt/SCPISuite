package com.instrument.scpisuite;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

public class ConnectInstrumentDialog extends Dialog {
    private Context context;

    public ConnectInstrumentDialog(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        context = context;
    }
    
    public ConnectInstrumentDialog(Context context, int theme){
        super(context, theme);
        context = context;
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_conninst);
    }

}
