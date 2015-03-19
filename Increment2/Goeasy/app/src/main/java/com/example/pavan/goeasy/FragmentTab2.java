package com.example.pavan.goeasy;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.TextView;

public class FragmentTab2 extends Fragment{
	private String name;
	/**
	 * @return the name
	 */ 
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	public View onCreateView(LayoutInflater inflater, ViewGroup container, 
            Bundle savedInstanceState){
		View view = inflater.inflate(R.layout.tab2, container, false);
        return null;
}
}
