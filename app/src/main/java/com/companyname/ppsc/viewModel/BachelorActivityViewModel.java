package com.companyname.ppsc.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.companyname.ppsc.model.GenericFunctions;

import java.util.ArrayList;
import java.util.List;

public class BachelorActivityViewModel extends ViewModel {
    private GenericFunctions genericFunctions;
    private List<Integer> arrayList;
    private int percentage;

    public BachelorActivityViewModel() {
        genericFunctions= new GenericFunctions();
    }

    public List<Integer> getMarks(String examType, String s, String matricEduType, int matricPercentage){
        arrayList=genericFunctions.getMarks(examType, s, matricEduType, matricPercentage);
        return arrayList;
    }

    public int calculatePercentage(double obtained,double total){
        percentage=(int) ((obtained/total) * 100);
        return percentage;
    }

}
