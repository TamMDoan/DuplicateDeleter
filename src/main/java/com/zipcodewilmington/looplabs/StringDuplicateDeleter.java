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
        // you gotta sort the strings!
        String comparing = this.array[0];
        int count = 1;

        for(int i = 1; i < this.array.length; i++){
            if(Objects.equals(comparing, this.array[i])){
                count++;
            }
            else{
                if(count >= maxNumberOfDuplications && comparing != this.array[i+1]){
                    this.array = removeAndGetNewArray(this.array, count, comparing);
                    i = i - count; // made new array, so starting i at new index
                }
                count = 1;
                comparing = this.array[i];
            }

        }
        if(count >= maxNumberOfDuplications){
            this.array = removeAndGetNewArray(this.array, count, comparing);
        }
        return this.array;
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

    @Override
    public String[] removeDuplicatesExactly(int exactNumberOfDuplications) {

        String comparing = this.array[0];
        int count = 1;

        for(int i = 1; i < this.array.length; i++){
            if(count == exactNumberOfDuplications && !this.array[i].equals(comparing)){
                this.array = removeAndGetNewArray(this.array, count, comparing);
                i = i - count;
                count = 1;
                comparing = this.array[i];
            }
            else if(comparing == this.array[i]){
                count++;
            }
            else{
                count = 1;
                comparing = this.array[i];
            }
        }
        // if the last few indexes are duplicates
        if(count == exactNumberOfDuplications){
            this.array = removeAndGetNewArray(this.array, count, comparing);
        }

        return this.array;
    }
}
