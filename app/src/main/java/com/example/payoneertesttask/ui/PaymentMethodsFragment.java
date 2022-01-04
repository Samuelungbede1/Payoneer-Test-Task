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
import com.example.payoneertesttask.data.ApiResponse;
import com.example.payoneertesttask.data.Applicable;
import com.example.payoneertesttask.databinding.FragmentPaymentMethodsBinding;
import com.example.payoneertesttask.databinding.PaymentMethodItemBinding;
import com.example.payoneertesttask.utils.Resource;
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
        paymentMethodViewModel.getPaymentMethods();
        paymentMethodViewModel.getLiveData().observe(getViewLifecycleOwner(), new Observer<Resource<ApiResponse>>() {
                    @Override
                    public void onChanged(Resource<ApiResponse> apiResponseResource) {
                        if (apiResponseResource !=null){

                            switch (apiResponseResource.status){
                                case LOADING:{
                                    binding.progressCircular.setVisibility(View.VISIBLE);
                                    break;
                                }

                                case ERROR:{
                                    binding.progressCircular.setVisibility(View.INVISIBLE);
                                    break;
                                }

                                case SUCCESS:{
                                    if (apiResponseResource.data!=null) {
                                                recyclerViewAdapter.setListItems(apiResponseResource.data.getNetworks().getApplicable());
                                                recyclerViewAdapter.notifyDataSetChanged();
                                    }
                                    binding.progressCircular.setVisibility(View.INVISIBLE);
                                    break;
                                }
                            }
                        }

                    }
        });



                    }
                }

