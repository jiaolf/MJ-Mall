package com.mijing.mide.mall.ui.home.subject;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mijing.mide.mall.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SubjectFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SubjectFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private int mParam1;
    private String mParam2;


    public SubjectFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment SubjectFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SubjectFragment newInstance(int index, String title) {
        SubjectFragment fragment = new SubjectFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, index);
        args.putString(ARG_PARAM2, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_subject, container, false);
        TextView tv = (TextView) view.findViewById(R.id.text_title);
        tv.setText(mParam1 + "  " + mParam2);
        return view;
    }

}
