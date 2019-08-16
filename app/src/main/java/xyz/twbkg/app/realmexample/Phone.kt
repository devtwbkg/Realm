package xyz.twbkg.app.realmexample

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Phone : RealmObject(){

    @PrimaryKey
    var id : Int =0
    var name :String = ""
    var color:String = ""

}