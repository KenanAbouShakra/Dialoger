package com.example.dialoger;

import android.app.Application;

public class Global extends Application {
    private String minvar = "Hei og Hopp";
    public String getminvar() { return minvar;}
    public void setminvar(String someVariable) { this.minvar = someVariable; } }