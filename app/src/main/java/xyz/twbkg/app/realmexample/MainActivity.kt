package xyz.twbkg.app.realmexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import io.realm.Realm
import io.realm.kotlin.createObject
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // initialize ream
        Realm.init(this)



        // get realm
        var realm = Realm.getDefaultInstance()

        // get old object
        var phoneAll = realm.where(Phone::class.java).findAll()

        // add event
        phoneAll.addChangeListener{phones, order->
            // print all object
            Log.i("MainActivity ", "$phones")

            // set text
            result_tv.text = phones.toString()
        }



        // add event button add
        button_add.setOnClickListener {



            realm.executeTransaction { inRealm->


                // get last id
                var lastId = inRealm.where(Phone::class.java).max("id")?.let { it.toInt() } ?: 1

                // create new object
                var phone = inRealm.createObject(Phone::class.java, ++lastId) // id +=1
                phone.apply {
                    name = input_edt.text.toString()
                    color = color_edt.text.toString()
                }

                Toast.makeText(this, "object created", Toast.LENGTH_SHORT).show()
            }

        }


    }
}
