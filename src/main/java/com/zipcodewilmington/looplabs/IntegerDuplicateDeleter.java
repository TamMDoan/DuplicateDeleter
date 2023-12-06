package com.zipcodewilmington.looplabs;

import java.util.Objects;
import java.util.Scanner;

/**
 * Created by leon on 1/29/18.
 * @ATTENTION_TO_STUDENTS You are forbidden from modifying the signature of this class.
 */
public final class IntegerDuplicateDeleter extends DuplicateDeleter<Integer> {
    public IntegerDuplicateDeleter(Integer[] intArr){
        super(intArr);
    }
    @Override
    public Integer[] removeDuplicates(int maxNumberOfDuplications) {
        Integer[] arrToUse = sort(this.array);
        Integer comparing = arrToUse[0];
        int count = 1;

        for(int i = 1; i < arrToUse.length; i++){
            if(Objects.equals(comparing, arrToUse[i])){
                count++;
            }
            else{
                if(count >= maxNumberOfDuplications){
                    arrToUse = removeAndGetNewArray(arrToUse, count, comparing);
                    i = i - count; // made new array, so starting i at new index
                }
                count = 1;
                comparing = arrToUse[i];
            }

        }
        if(count >= maxNumberOfDuplications){
            arrToUse = removeAndGetNewArray(arrToUse, count, comparing);
        }
        return arrToUse;
    }

    @Override
    public Integer[] removeDuplicatesExactly(int exactNumberOfDuplications) {
        Integer[] arrToUse = sort(this.array);
        Integer comparing = arrToUse[0];
        int count = 1;

        for(int i = 1; i < arrToUse.length; i++){
            if(count == exactNumberOfDuplications && !arrToUse[i].equals(comparing)){
                arrToUse = removeAndGetNewArray(arrToUse, count, comparing);
                i = i - count;
                count = 1;
                comparing = arrToUse[i];
            }
            else if(comparing == arrToUse[i]){
                count++;
            }
            else{
                count = 1;
                comparing = arrToUse[i];
            }
        }
        // if the last few indexes are duplicates
        if(count == exactNumberOfDuplications){
            arrToUse = removeAndGetNewArray(arrToUse, count, comparing);
        }

        return arrToUse;
    }

    public Integer[] sort(Integer[] arr){
        for(int i = 0; i < arr.length - 1; i++){
            for(int j = 0; j < arr.length - i - 1; j++){
                if(arr[j] > arr[j+1]){
                    int temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }

    public Integer[] removeAndGetNewArray(Integer[] arr, int count, int duplicate){
        Integer[] newArr = new Integer[arr.length - count];
        int j = 0;
        int i = 0;
        while( j < arr.length){
            if(arr[j] != duplicate){
                newArr[i] = arr[j];
                j++;
                i++;
            }
            else{
                j+=count; //skipping duplicates
            }
        }
        return newArr;
    }

}
