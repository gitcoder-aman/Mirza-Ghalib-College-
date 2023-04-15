package com.tech.mirzaghalibcollegeuser.model

class TeacherModel {

    private var teacherName:String ?=null

    private var teacherImage:String ?= null

    private var teacherPost:String ?= null

    private var department:String ?= null  //like category

    private var teacherKey:String ?= null
    private var teacherEmail:String ?= null
    private var teacherQualification: String? = null
    private var teacherSpecialize: String? = null
    private var teacherAddress: String? = null
    private var teacherMobileNo: String? = null
    private var teacherCVPdf: String? = null
    private var teacherCVPdfTitle: String? = null

    private var isExpandable:Boolean = true

    constructor()
    constructor(
        teacherName: String?,
        teacherImage: String?,
        teacherPost: String?,
        department: String?,
        teacherKey: String?,
        teacherEmail: String?,
        teacherMobileNo:String?,
        teacherAddress:String?,
        teacherSpecialize:String?,
        teacherQualification:String?,
        teacherCVPdf:String?,
        teacherCVPdfTitle:String?
    ) {
        this.teacherName = teacherName
        this.teacherImage = teacherImage
        this.teacherPost = teacherPost
        this.department = department
        this.teacherKey = teacherKey
        this.teacherEmail = teacherEmail
        this.teacherMobileNo = teacherMobileNo
        this.teacherAddress = teacherAddress
        this.teacherQualification = teacherQualification
        this.teacherSpecialize = teacherSpecialize
        this.teacherCVPdf = teacherCVPdf
        this.teacherCVPdfTitle = teacherCVPdfTitle
  }


    fun setTeacherName(teacherName: String?){
        this.teacherName = teacherName
    }
    fun getTeacherName():String?{
        return teacherName
    }
    fun setTeacherImage(teacherImage: String?){
        this.teacherImage = teacherImage
    }
    fun getTeacherImage():String?{
        return teacherImage
    }
    fun setTeacherPost(teacherPost: String?){
        this.teacherPost = teacherPost
    }
    fun getTeacherPost():String?{
        return teacherPost
    }
    fun setDepartment(department: String?){
        this.department = department
    }
    fun getDepartment():String?{
        return department
    }
    fun setTeacherKey(teacherKey: String?){
        this.teacherKey = teacherKey
    }
    fun getTeacherKey():String?{
        return teacherKey
    }
    fun setTeacherEmail(teacherEmail: String?){
        this.teacherEmail = teacherEmail
    }
    fun getTeacherEmail():String?{
        return teacherEmail
    }
    fun setTeacherMobileNo(teacherMobileNo: String?){
        this.teacherMobileNo = teacherMobileNo
    }
    fun getTeacherMobileNo():String?{
        return teacherMobileNo
    }
    fun setTeacherAddress(teacherAddress: String?){
        this.teacherAddress = teacherAddress
    }
    fun getTeacherAddress():String?{
        return teacherAddress
    }
    fun setTeacherSpecialize(teacherSpecialize: String?){
        this.teacherSpecialize = teacherSpecialize
    }
    fun getTeacherSpecialize():String?{
        return teacherSpecialize
    }
    fun setTeacherQualification(teacherQualification: String?){
        this.teacherQualification = teacherQualification
    }
    fun getTeacherQualification():String?{
        return teacherQualification
    }
    fun setTeacherCVPdf(teacherCVPdf: String?){
        this.teacherCVPdf = teacherCVPdf
    }
    fun getTeacherCVPdf():String?{
        return teacherCVPdf
    }
    fun setTeacherCVPdfTitle(teacherCVPdfTitle: String?){
        this.teacherCVPdfTitle = teacherCVPdfTitle
    }
    fun getTeacherCVPdfTitle():String?{
        return teacherCVPdfTitle
    }
    fun getIsExpandable():Boolean{
        return isExpandable
    }
    fun setIsExpandable(isExpandable:Boolean){
       this.isExpandable = isExpandable
    }


}