package com.arturofilio.recyclerinrecycler.Interface

import com.arturofilio.recyclerinrecycler.Model.ItemGroup

interface IFirebaseLoadListener {
    fun onFirebaseLoadSuccess(itemGroupList:List<ItemGroup>)
    fun onFirebaseLoadFailed(message:String)
}