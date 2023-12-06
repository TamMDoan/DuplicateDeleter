package com.zipcodewilmington.looplabs;

import java.util.Arrays;
import java.util.Objects;

/**
 * Created by leon on 1/28/18.
 * @ATTENTION_TO_STUDENTS You are forbidden from modifying the signature of this class.
 */
public final class StringDuplicateDeleter extends DuplicateDeleter<String> {
    public StringDuplicateDeleter(String[] intArray) {
        super(intArray);
    }

    @Override
    public String[] removeDuplicates(int maxNumberOfDuplications) {
        String[] arrToUse = this.array;
        String comparing = arrToUse[0];
        int count = 1;

        for(int i = 1; i < arrToUse.length; i++){
            if(Objects.equals(comparing, arrToUse[i])){
                count++;
            }
            else{
                if(count >= maxNumberOfDuplications && !arrToUse[i].equals(comparing)){
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
    public String[] removeDuplicatesExactly(int exactNumberOfDuplications) {
        String[] arrToUse = this.array;
        String comparing = arrToUse[0];
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

    private String[] removeAndGetNewArray(String[] arr, int count, String duplicate) {
        String[] newArr = new String[arr.length - count];
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
