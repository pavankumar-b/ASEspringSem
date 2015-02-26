package com.example.pavan.goeasy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Environment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentTab1 extends Fragment{

	public View onCreateView(LayoutInflater inflater, ViewGroup container, 
            Bundle savedInstanceState){
		View view = inflater.inflate(R.layout.tab, container, false);

		return view;
}
}
