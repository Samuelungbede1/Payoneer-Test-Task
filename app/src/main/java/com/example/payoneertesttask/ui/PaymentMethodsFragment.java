package com.example.payoneertesttask.ui;

import android.Manifest;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.payoneertesttask.Connectivity.MainViewModel;
import com.example.payoneertesttask.adapter.RecyclerAdapter;
import com.example.payoneertesttask.data.ApiResponse;
import com.example.payoneertesttask.databinding.FragmentPaymentMethodsBinding;
import com.example.payoneertesttask.utils.Resource;
import com.example.payoneertesttask.viewmodel.MethodsOfPaymentViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class PaymentMethodsFragment extends Fragment  {
    private static final int INTERNET_STATE = 0;
    private FragmentPaymentMethodsBinding binding;
    private RecyclerAdapter recyclerViewAdapter;
    private MethodsOfPaymentViewModel paymentMethodViewModel;
    private MainViewModel viewModel;

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
        paymentMethodViewModel = new ViewModelProvider(this).get(MethodsOfPaymentViewModel.class);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        initRecyclerView();
        initViewModel();
        observeNetWorkConnectivity();
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
                                case N0_CONNECTION:
                                    Toast.makeText(requireContext(), "No connection", Toast.LENGTH_LONG).show();
                                    break;

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




    private void observeNetWorkConnectivity() {
        viewModel.observeLiveInternet().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean value) {
                requestPermissions(viewModel.checkPermission());
                if (value) {
                    binding.connectionMessage.setText("Network Connection is available");
                } else {
                    binding.connectionMessage.setVisibility(View.VISIBLE);
                    binding.connectionMessage.setText("Network Connection is not available");
                    Toast.makeText(requireContext(),"No Internet connection", Toast.LENGTH_LONG).show();
                }
            }
        });

    }



    private void requestPermissions(boolean permissionGranted) {
        if (!permissionGranted) {
            ActivityCompat.requestPermissions(
                    requireActivity(),
                    new String[]{
                            Manifest.permission.INTERNET,
                            Manifest.permission.ACCESS_WIFI_STATE,
                            Manifest.permission.ACCESS_NETWORK_STATE,
                    },
                    INTERNET_STATE
            );
        }
    }
}

