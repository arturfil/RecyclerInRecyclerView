package com.arturofilio.recyclerinrecycler

import android.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.arturofilio.recyclerinrecycler.Adapters.MyGroupAdapter
import com.arturofilio.recyclerinrecycler.Adapters.MyItemAdapter
import com.arturofilio.recyclerinrecycler.Interface.IFirebaseLoadListener
import com.arturofilio.recyclerinrecycler.Model.ItemData
import com.arturofilio.recyclerinrecycler.Model.ItemGroup
import com.google.firebase.database.*
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), IFirebaseLoadListener {

    override fun onFirebaseLoadFailed(message: String) {
        dialog.dismiss()
        Toast.makeText(this@MainActivity,message,Toast.LENGTH_SHORT).show()
    }

    override fun onFirebaseLoadSuccess(itemGroupList: List<ItemGroup>) {
        val adapter = MyGroupAdapter(this@MainActivity,itemGroupList)
        my_recycler_view.adapter = adapter
        dialog.dismiss()

    }

    lateinit var dialog: AlertDialog
    lateinit var iFirebaseLoadListener:IFirebaseLoadListener
    lateinit var myData: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Init Dialog Alert
        dialog = SpotsDialog.Builder().setContext(this).build()
        myData = FirebaseDatabase.getInstance().getReference("MyData")
        iFirebaseLoadListener = this

        //View
        my_recycler_view.setHasFixedSize(true)
        my_recycler_view.layoutManager = LinearLayoutManager(this)

        getFirebaseData()
    }

    private fun getFirebaseData() {

        dialog.show()

        myData.addListenerForSingleValueEvent(object:ValueEventListener{



            override fun onCancelled(p0: DatabaseError) {
                iFirebaseLoadListener.onFirebaseLoadFailed(p0.message)
            }

            override fun onDataChange(p0: DataSnapshot) {
                val itemGroups = ArrayList<ItemGroup>()
                for(myDataSnapShot in p0.children) {
                    val itemGroup = ItemGroup()
                    itemGroup.headerTitle = myDataSnapShot.child("headerTitle")
                        .getValue(true).toString()
                    val t = object:GenericTypeIndicator<ArrayList<ItemData>>() {}
                    itemGroup.listItem = myDataSnapShot.child("listItem").getValue(t)
                    itemGroups.add(itemGroup)
                }

                iFirebaseLoadListener.onFirebaseLoadSuccess(itemGroups)
            }

        })
    }
}
