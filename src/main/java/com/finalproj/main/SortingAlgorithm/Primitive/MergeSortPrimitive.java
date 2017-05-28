package com.finalproj.main.SortingAlgorithm.Primitive;

public class MergeSortPrimitive {
	
	  private Integer[] numbers;
      private Integer[] helper;
      
      private Float[] numFloat;
      private Float[] helpFloat;
      
      private String[] numStr;
      private String[] helpStr;

      private int number;

      /*****************************INTEGER
     * @return ******************************/
      public Integer[] sort(Integer[] values) {
              this.numbers = values;
              number = values.length;
              this.helper = new Integer[number];
              mergesort(0, number - 1);
              
              return numbers;
      }

      private void mergesort(int low, int high) {
              // check if low is smaller than high, if not then the array is sorted
              if (low < high) {
                      // Get the index of the element which is in the middle
                      int middle = low + (high - low) / 2;
                      // Sort the left side of the array
                      mergesort(low, middle);
                      // Sort the right side of the array
                      mergesort(middle + 1, high);
                      // Combine them both
                      merge(low, middle, high);
              }
      }

      private void merge(int low, int middle, int high) {

              // Copy both parts into the helper array
              for (int i = low; i <= high; i++) {
                      helper[i] = numbers[i];
              }

              int i = low;
              int j = middle + 1;
              int k = low;
              // Copy the smallest values from either the left or the right side back
              // to the original array
              while (i <= middle && j <= high) {
                      if (helper[i] <= helper[j]) {
                              numbers[k] = helper[i];
                              i++;
                      } else {
                              numbers[k] = helper[j];
                              j++;
                      }
                      k++;
              }
              // Copy the rest of the left side of the array into the target array
              while (i <= middle) {
                      numbers[k] = helper[i];
                      k++;
                      i++;
              }

      }
      
  /********************FLOAT***********************/    
      public Float[] sort(Float[] values) {
          this.numFloat = values;
          number = values.length;
          this.helpFloat = new Float[number];
          mergesortfloat(0, number - 1);
          
          return numFloat;
  }

  private void mergesortfloat(int low, int high) {
          // check if low is smaller than high, if not then the array is sorted
          if (low < high) {
                  // Get the index of the element which is in the middle
                  int middle = low + (high - low) / 2;
                  // Sort the left side of the array
                  mergesortfloat(low, middle);
                  // Sort the right side of the array
                  mergesortfloat(middle + 1, high);
                  // Combine them both
                  mergefloat(low, middle, high);
          }
  }

  private void mergefloat(int low, int middle, int high) {

          // Copy both parts into the helpFloat array
          for (int i = low; i <= high; i++) {
                  helpFloat[i] = numFloat[i];
          }

          int i = low;
          int j = middle + 1;
          int k = low;
          // Copy the smallest values from either the left or the right side back
          // to the original array
          while (i <= middle && j <= high) {
                  if (helpFloat[i] <= helpFloat[j]) {
                          numFloat[k] = helpFloat[i];
                          i++;
                  } else {
                          numFloat[k] = helpFloat[j];
                          j++;
                  }
                  k++;
          }
          // Copy the rest of the left side of the array into the target array
          while (i <= middle) {
                  numFloat[k] = helpFloat[i];
                  k++;
                  i++;
          }

  }
  
  /**************************STRING
 * @return ***************************/
  public String[] sort(String[] values) {
      this.numStr = values;
      number = values.length;
      this.helpStr = new String[number];
      mergesortstring(0, number - 1);
      
      return numStr;
}

private void mergesortstring(int low, int high) {
      // check if low is smaller than high, if not then the array is sorted
      if (low < high) {
              // Get the index of the element which is in the middle
              int middle = low + (high - low) / 2;
              // Sort the left side of the array
              mergesortstring(low, middle);
              // Sort the right side of the array
              mergesortstring(middle + 1, high);
              // Combine them both
              mergestring(low, middle, high);
      }
}

private void mergestring(int low, int middle, int high) {

      // Copy both parts into the helpStr array
      for (int i = low; i <= high; i++) {
              helpStr[i] = numStr[i];
      }

      int i = low;
      int j = middle + 1;
      int k = low;
      // Copy the smallest values from either the left or the right side back
      // to the original array
      while (i <= middle && j <= high) {
              if (helpStr[i].compareTo(helpStr[j]) <=0 ) {
                    numStr[k] = helpStr[i];
                      i++;
              } else {
                      numStr[k] = helpStr[j];
                      j++;
              }
              k++;
      }
      // Copy the rest of the left side of the array into the target array
      while (i <= middle) {
              numStr[k] = helpStr[i];
              k++;
              i++;
      }

}
      
      

}
