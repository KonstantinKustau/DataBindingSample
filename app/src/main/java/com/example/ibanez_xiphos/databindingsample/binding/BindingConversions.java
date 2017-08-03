package com.example.ibanez_xiphos.databindingsample.binding;

import android.databinding.BindingConversion;
import android.view.View;

public class BindingConversions {

    @BindingConversion
    public static int convertBooleanToVisibility(boolean visible) {
        return visible ? View.VISIBLE : View.GONE;
    }
}
