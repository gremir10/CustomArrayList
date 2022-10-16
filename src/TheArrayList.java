import java.util.*;
public class TheArrayList
{
    private int[] array; //keeps track of array

    //variable that keeps track of how many indices were used in array
    private int usedIndicies;

    TheArrayList() //constructor- as soon as you create an an object
    //with TheArrayList it will create an array of 10
    {
        //declare fixed size array inside constructor
        array = new int[10];
        usedIndicies = 0;
    }

    TheArrayList(int size)  //constructor overload- programmer
    //can either create an empty arraylist with default 10 slots, or define other size of //their choice
    {     //if user input is negative, default array of 10 will be used
        if (size < 0)
        {
            array = new int[10];
        }
        else
        {
            array = new int[size];
        }
        usedIndicies = 0;
    }

    public void appendElement(int element)
    {
//check if there's enough room in array for new element
        if(usedIndicies >= array.length)
//usedindices will start at 0 and go up to 9, when = 10, no room
        {
            doubleArray(); //if usedIndices > array.length, double array size
        }
        //add element at first unused element (0)^
        array[usedIndicies] = element;
        usedIndicies++;
    }

    public void insertElement(int value, int index)
    {
        if(usedIndicies >= array.length)
        {   //double array if more size needed
            doubleArray();
        }
        //create temp array
        int[] temp = new int[array.length];
        //iterate until right before index
        for(int i = 0; i < index; ++i)
        {
            temp[i] = array[i];
        }

        temp[index] = value;
        //i< array.length so it doesn't go out of bounds
        for(int i = index + 1; i < array.length - 1; ++i)
        {      //i-1 so you don’t accidentally delete an extra element
            temp[i] = array[i - 1];
        }
        array = temp;
        //increase size
        usedIndicies++;

    }

    public void replaceElementByIndex(int value, int index)
    {
        array[index] = value;
    }

    public void deleteElementByIndex(int index) //delete based on index instead of value
    {
//create temp array of size array length
        int[] temp = new int[array.length];
        //iterate until right before index is reached
        for(int i = 0; i < index; ++i)
        {
            temp[i] = array[i];
        } //then start at the value after
        for(int i = index + 1; i < array.length; ++i)
        {          //-1 from temp to prevent from going out of bounds
            temp[i - 1] = array[i];
        }
        array = temp;
        //reduce size
        usedIndicies--;

        //if usage drops below a quarter, reduce length by half
        if(usedIndicies <= array.length / 4 )
        {
            halfArray();
        }
    }

    public void deleteElementByValue(int value)
    {         //pass value into findElement and set = element
        int element = findElementByValue(value);
        //if not negative, delete
        if (element >= 0)
        {
            deleteElementByIndex(element);
        }
        else
        {
            System.out.println("Value not found.");
        }
    }

    public void printArray()
    {        //iterate through array and print used indices
        for(int i = 0; i < usedIndicies; ++i)
        {
            System.out.println(array[i]);
        }
    }

    public int findElementByValue(int value)
    {      //iterate through array until value is found
        for(int i = 0; i < array.length; ++i)
        {
            if(array[i] == value)
            {
                return i;
            }
        }
        //if value isnt found in array:
        return -1;

    }

    public double currentUsage() //check how many indices you’ve used
    {
        return usedIndicies / array.length;
    }

    public int capacity() //check upper limit
    {
        return array.length;
    }

    private void doubleArray() //more efficient to double array each time
    {                      //you need space than +1 for each element
        //new array int[] temp is local variable, once done with method
//it’s deleted automatically
        int[] temp = new int[array.length * 2];

        for(int i = 0; i < array.length; ++i)
        {
            temp[i] = array[i];
        }
//copy temp back to array, it’s now bigger array with all values //stored
        array = temp;
    }

    private void halfArray() //for deleting extra capacity to avoid unnecessarily doubling array
    {
        int[] temp = new int[array.length / 2];

        for(int i = 0; i < array.length; ++i)
        {
            temp[i] = array[i];
        }
        array = temp;
    }
}
