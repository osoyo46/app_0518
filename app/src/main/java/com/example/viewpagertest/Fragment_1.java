package com.example.viewpagertest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;




public class Fragment_1 extends Fragment {
    int idx = 0;

    public Fragment_1(int index) { // int info_index,
        idx = index;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.frame_1, container, false);

        TextView textView = (TextView) rootView.findViewById(R.id.frame_1_text);
        textView.setText(Integer.toString(idx));

//         bundle
        String data = "초기값";
        if (getArguments() != null) {
            data = getArguments().getString("data2");
        }
        textView.setText(data);



        return rootView;
    }
}