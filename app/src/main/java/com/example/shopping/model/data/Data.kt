package com.example.shopping.model.data

class Data {

    class Data{
        var email: String? = null
        var id: Int? = null
        var image: String? = null
        var name: String? = null
        var phone: String? = null
        var token: String? = null
        var points: Int? = null
        var credit: Int? = null
        var password: String? = null


        constructor(id:Int,email:String,image:String,name:String,phone:String,token:String){
            this.id =id
            this.email = email
            this.image = image
            this.name= name
            this.phone = phone
            this.token=token
        }
        constructor(email:String,name:String,phone:String,password:String){
            this.email = email
            this.name= name
            this.phone = phone
            this.password =password

        }
        constructor(email:String,password:String){
            this.email = email
            this.password =password
        }
    }

}