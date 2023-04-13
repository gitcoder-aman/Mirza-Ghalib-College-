package com.tech.mirzaghalibcollegeuser.model

class ImageSliderModel {
    private var timeStamp:Long ?=null  //for sorting
    private var title:String?=null
    private var category:String?=null  //for activity gallery
    private var image:String ?= null
    private var key:String ?= null

    constructor()
    constructor(title:String,timeStamp: Long, image: String?, key:String?) {
        this.title = title
        this.timeStamp = timeStamp
        this.image = image
        this.key = key
    }
    constructor(category:String, image: String?) {
        this.category = category
        this.image = image
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
    fun setCategory(category: String?){
        this.category = category
    }
    fun getCategory():String?{
        return category
    }
}