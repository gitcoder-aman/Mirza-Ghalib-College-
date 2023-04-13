package com.tech.mirzaghalibcollegeuser.model

class NoticeModel {

    private var title:String ?=null

    private var image:String ?= null

    private var timestamp:Long ?= null

    private var key:String ?= null


    constructor()
    constructor(title: String?, image: String?, timestamp: Long, key: String?) {
        this.title = title
        this.image = image
        this.timestamp = timestamp
        this.key = key
    }
    fun setTitle(title: String?){
        this.title = title
    }
    fun getTitle():String?{
        return title
    }
    fun setImage(image: String?){
        this.image = image
    }
    fun getImage():String?{
        return image
    }
    fun setTimestamp(timestamp: Long?){
        this.timestamp = timestamp
    }
    fun getTimestamp():Long?{
        return timestamp
    }
    fun setKey(key: String?){
        this.key = key
    }
    fun getKey():String?{
        return key
    }

}