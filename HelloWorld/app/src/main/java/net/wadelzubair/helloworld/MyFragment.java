package net.wadelzubair.helloworld;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MyFragment extends Fragment {

    private Button btnFragment;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        btnFragment = view.findViewById(R.id.btnFragment);

        btnFragment.setOnClickListener(view1 -> {
            Toast.makeText(getActivity(),"Btn clicked from fragment",Toast.LENGTH_LONG).show();
        });

        return view;
    }

}