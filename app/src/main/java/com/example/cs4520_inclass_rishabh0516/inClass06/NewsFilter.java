package com.example.cs4520_inclass_rishabh0516.inClass06;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.cs4520_inclass_rishabh0516.R;
import com.example.cs4520_inclass_rishabh0516.inClass02.EditProfileActivity;

// Rishabh Sahu
// Assignment #6
public class NewsFilter extends Fragment {
    private Button submit;
    private Spinner country_spinner;
    private Spinner category_spinner;

    private String country;
    private String category;

    public NewsFilter() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_in_class06, container, false);

        submit = rootView.findViewById(R.id.submit);
        country_spinner = rootView.findViewById(R.id.country_spinner);
        category_spinner = rootView.findViewById(R.id.category_spinner);

        ArrayAdapter<CharSequence> country_adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.country_array, android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> category_adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.category_array, android.R.layout.simple_spinner_item);

        country_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        country_spinner.setAdapter(country_adapter);
        category_spinner.setAdapter(category_adapter);

        country_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                country = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        category_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                category = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(country.equals("") && category.equals("")) {
                    Toast.makeText(getActivity(), "Need to select country or category",Toast.LENGTH_LONG).show();
                }
                else {
                    Bundle bundle = new Bundle();
                    bundle.putString("country", country);
                    bundle.putString("category", category);
                    Navigation.findNavController(rootView)
                            .navigate(R.id.action_inClass06_to_fragmentResult, bundle);
                }
            }
        });
        return rootView;
    }
}