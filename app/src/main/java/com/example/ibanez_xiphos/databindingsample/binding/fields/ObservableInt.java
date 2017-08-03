package com.example.ibanez_xiphos.databindingsample.binding.fields;

public class ObservableInt extends android.databinding.ObservableInt {

    public void increment() {
        set(get() + 1);
    }

    public void decrement() {
        set(get() - 1);
    }
}