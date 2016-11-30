/*
*@file_Name: UnOrderedNodeCreation.java
*@Author: Shwetali
*@Date: 07-09-2016
*@purpose:  Generic type node Creation for clinic management.
*/

package com.bridgelabz.clinic;

public class UnOrderedNodeCreation<T>{
	T data;
	UnOrderedNodeCreation next;
	//Assigning the data value
	public void setData(T value){
		data = value;
	}
	//getting data value
	public T getData(){
		return data;
	}
	//Assigning next node
	public void setNext(UnOrderedNodeCreation node){
		next = node;
	}
	//getting next node
	public UnOrderedNodeCreation getNext(){
		return next;
	}
}