package com.tech.mirzaghalibcollegeuser.model

class ImageSliderModel {
    private var timeStamp:Long ?=null  //for sorting
    private var title:String?=null
    private var image:String ?= null
    private var key:String ?= null

    constructor()
    constructor(title:String,timeStamp: Long, image: String?, key:String?) {
        this.title = title
        this.timeStamp = timeStamp
        this.image = image
        this.key = key
    }
    fun setTimeStamp(timeStamp: Long?){
        this.timeStamp = timeStamp
    }
    fun getTimeStamp():Long?{
        return timeStamp
    }
    fun setImage(image: String?){
        this.image = image
    }
    fun getImage():String?{
        return image
    }
    fun setKey(key: String?){
        this.key = key
    }
    fun getKey():String?{
        return key
    }
    fun setTitle(title: String?){
        this.title = title
    }
    fun getTitle():String?{
        return title
    }
}