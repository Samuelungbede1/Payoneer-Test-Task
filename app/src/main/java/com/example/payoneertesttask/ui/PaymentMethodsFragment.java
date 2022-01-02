package com.example.payoneertesttask.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.Toast;

import com.example.payoneertesttask.R;
import com.example.payoneertesttask.adapter.RecyclerAdapter;
import com.example.payoneertesttask.data.Applicable;
import com.example.payoneertesttask.databinding.FragmentPaymentMethodsBinding;
import com.example.payoneertesttask.databinding.PaymentMethodItemBinding;
import com.example.payoneertesttask.viewmodel.MainViewModel;

import java.util.Arrays;
import java.util.List;
import java.util.function.ToDoubleBiFunction;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class PaymentMethodsFragment extends Fragment {
    private FragmentPaymentMethodsBinding binding;
    private RecyclerAdapter recyclerViewAdapter;
    private MainViewModel paymentMethodViewModel;
    private static final String TAG = "MyActivity";


    public PaymentMethodsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPaymentMethodsBinding.inflate(getLayoutInflater(),container,false);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }



    @Override
    public void onViewCreated(@NonNull View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        paymentMethodViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        initRecyclerView();
        initViewModel();
    }


    private void initRecyclerView() {
        binding.rvPaymentList.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerViewAdapter = new RecyclerAdapter();
        binding.rvPaymentList.setAdapter(recyclerViewAdapter);
    }


    public void initViewModel (){
        paymentMethodViewModel.getLiveData().observe(getViewLifecycleOwner(), new Observer<List<Applicable>>() {
            @Override
            public void onChanged(List<Applicable> applicable) {
                if (applicable !=null) {
                    recyclerViewAdapter.setListItems(applicable);
                    recyclerViewAdapter.notifyDataSetChanged();
                }
            }
        });
        paymentMethodViewModel.getPaymentMethods();
    }


}