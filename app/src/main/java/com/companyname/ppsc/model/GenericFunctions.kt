package com.companyname.ppsc.model

import android.app.Application

class GenericFunctions {



    fun getMarks(eType: String,className: String,eduType: String,percentage: Int): List<Int>{
        var list= arrayListOf<Int>()

        if(eType=="examtype1")
        {
            if(eduType=="Semester"){
                if(1<=percentage && percentage<=44){
                    list.add(0,24)
                }else if(45<=percentage && percentage<=49){
                    list.add(0,25)
                }else if(50<=percentage && percentage<=54){
                    list.add(0,26)
                }else if(55<=percentage && percentage<=59){
                    list.add(0,28)
                }else if(60<=percentage && percentage<=64){
                    list.add(0,30)
                }else if(65<=percentage && percentage<=69){
                    list.add(0,33)
                }else if(70<=percentage && percentage<=74){
                    list.add(0,34)
                }else if(75<=percentage && percentage<=79){
                    list.add(0,36)
                }else if(80<=percentage && percentage<=89){
                    list.add(0,38)
                }else if(90<=percentage && percentage<=100){
                    list.add(0,40)
                }
            }else if(eduType=="Annual"){
                if(1<=percentage && percentage<=39){
                    list.add(0,24)
                }else if(40<=percentage && percentage<=44){
                    list.add(0,25)
                }else if(45<=percentage && percentage<=49){
                    list.add(0,26)
                }else if(50<=percentage && percentage<=54){
                    list.add(0,28)
                }else if(55<=percentage && percentage<=59){
                    list.add(0,30)
                }else if(60<=percentage && percentage<=64){
                    list.add(0,33)
                }else if(65<=percentage && percentage<=69){
                    list.add(0,34)
                }else if(70<=percentage && percentage<=74){
                    list.add(0,36)
                }else if(75<=percentage && percentage<=79){
                    list.add(0,38)
                }else if(80<=percentage && percentage<=100){
                    list.add(0,40)
                }
            }
            list.add(1,40)
        }else if(eType=="examtype2"){

            // MATRIC MARKS
            if(eduType=="Semester" && className=="matric"){
                if(1<=percentage && percentage<=44){
                    list.add(0,7)
                }else if(45<=percentage && percentage<=49){
                    list.add(0,8)
                }else if(50<=percentage && percentage<=54){
                    list.add(0,8)
                }else if(55<=percentage && percentage<=59){
                    list.add(0,9)
                }else if(60<=percentage && percentage<=64){
                    list.add(0,10)
                }else if(65<=percentage && percentage<=69){
                    list.add(0,11)
                }else if(70<=percentage && percentage<=74){
                    list.add(0,12)
                }else if(75<=percentage && percentage<=79){
                    list.add(0,13)
                }else if(80<=percentage && percentage<=89){
                    list.add(0,14)
                }else if(90<=percentage && percentage<=100){
                    list.add(0,15)
                }
            }else if(eduType=="Annual" && className=="matric"){
                if(1<=percentage && percentage<=39){
                    list.add(0,7)
                }else if(40<=percentage && percentage<=44){
                    list.add(0,8)
                }else if(45<=percentage && percentage<=49){
                    list.add(0,8)
                }else if(50<=percentage && percentage<=54){
                    list.add(0,9)
                }else if(55<=percentage && percentage<=59){
                    list.add(0,10)
                }else if(60<=percentage && percentage<=64){
                    list.add(0,11)
                }else if(65<=percentage && percentage<=69){
                    list.add(0,12)
                }else if(70<=percentage && percentage<=74){
                    list.add(0,13)
                }else if(75<=percentage && percentage<=79){
                    list.add(0,14)
                }else if(80<=percentage && percentage<=100){
                    list.add(0,15)
                }
            }

            // Inter MARKS
            if(eduType=="Semester" && className=="inter"){
                if(1<=percentage && percentage<=44){
                    list.add(0,17)
                }else if(45<=percentage && percentage<=49){
                    list.add(0,17)
                }else if(50<=percentage && percentage<=54){
                    list.add(0,18)
                }else if(55<=percentage && percentage<=59){
                    list.add(0,19)
                }else if(60<=percentage && percentage<=64){
                    list.add(0,20)
                }else if(65<=percentage && percentage<=69){
                    list.add(0,22)
                }else if(70<=percentage && percentage<=74){
                    list.add(0,22)
                }else if(75<=percentage && percentage<=79){
                    list.add(0,23)
                }else if(80<=percentage && percentage<=89){
                    list.add(0,24)
                }else if(90<=percentage && percentage<=100){
                    list.add(0,25)
                }
            }else if(eduType=="Annual" && className=="inter"){
                if(1<=percentage && percentage<=39){
                    list.add(0,17)
                }else if(40<=percentage && percentage<=44){
                    list.add(0,17)
                }else if(45<=percentage && percentage<=49){
                    list.add(0,18)
                }else if(50<=percentage && percentage<=54){
                    list.add(0,19)
                }else if(55<=percentage && percentage<=59){
                    list.add(0,20)
                }else if(60<=percentage && percentage<=64){
                    list.add(0,22)
                }else if(65<=percentage && percentage<=69){
                    list.add(0,22)
                }else if(70<=percentage && percentage<=74){
                    list.add(0,23)
                }else if(75<=percentage && percentage<=79){
                    list.add(0,24)
                }else if(80<=percentage && percentage<=100){
                    list.add(0,25)
                }
            }

            list.add(1,15)
            list.add(2,25)
        }else if(eType=="examtype3"){
            // MATRIC MARKS
            if(eduType=="Semester" && className=="matric"){
                if(1<=percentage && percentage<=44){
                    list.add(0,5)
                }else if(45<=percentage && percentage<=49){
                    list.add(0,5)
                }else if(50<=percentage && percentage<=54){
                    list.add(0,5)
                }else if(55<=percentage && percentage<=59){
                    list.add(0,6)
                }else if(60<=percentage && percentage<=64){
                    list.add(0,6)
                }else if(65<=percentage && percentage<=69){
                    list.add(0,7)
                }else if(70<=percentage && percentage<=74){
                    list.add(0,8)
                }else if(75<=percentage && percentage<=79){
                    list.add(0,8)
                }else if(80<=percentage && percentage<=89){
                    list.add(0,9)
                }else if(90<=percentage && percentage<=100){
                    list.add(0,9)
                }
            }else if(eduType=="Annual" && className=="matric"){
                if(1<=percentage && percentage<=39){
                    list.add(0,5)
                }else if(40<=percentage && percentage<=44){
                    list.add(0,5)
                }else if(45<=percentage && percentage<=49){
                    list.add(0,5)
                }else if(50<=percentage && percentage<=54){
                    list.add(0,6)
                }else if(55<=percentage && percentage<=59){
                    list.add(0,6)
                }else if(60<=percentage && percentage<=64){
                    list.add(0,7)
                }else if(65<=percentage && percentage<=69){
                    list.add(0,8)
                }else if(70<=percentage && percentage<=74){
                    list.add(0,8)
                }else if(75<=percentage && percentage<=79){
                    list.add(0,9)
                }else if(80<=percentage && percentage<=100){
                    list.add(0,9)
                }
            }

            // Inter MARKS
            if(eduType=="Semester" && className=="inter"){
                if(1<=percentage && percentage<=44){
                    list.add(0,8)
                }else if(45<=percentage && percentage<=49){
                    list.add(0,8)
                }else if(50<=percentage && percentage<=54){
                    list.add(0,8)
                }else if(55<=percentage && percentage<=59){
                    list.add(0,9)
                }else if(60<=percentage && percentage<=64){
                    list.add(0,9)
                }else if(65<=percentage && percentage<=69){
                    list.add(0,10)
                }else if(70<=percentage && percentage<=74){
                    list.add(0,10)
                }else if(75<=percentage && percentage<=79){
                    list.add(0,11)
                }else if(80<=percentage && percentage<=89){
                    list.add(0,11)
                }else if(90<=percentage && percentage<=100){
                    list.add(0,12)
                }
            }else if(eduType=="Annual" && className=="inter"){
                if(1<=percentage && percentage<=39){
                    list.add(0,8)
                }else if(40<=percentage && percentage<=44){
                    list.add(0,8)
                }else if(45<=percentage && percentage<=49){
                    list.add(0,8)
                }else if(50<=percentage && percentage<=54){
                    list.add(0,9)
                }else if(55<=percentage && percentage<=59){
                    list.add(0,9)
                }else if(60<=percentage && percentage<=64){
                    list.add(0,10)
                }else if(65<=percentage && percentage<=69){
                    list.add(0,10)
                }else if(70<=percentage && percentage<=74){
                    list.add(0,11)
                }else if(75<=percentage && percentage<=79){
                    list.add(0,11)
                }else if(80<=percentage && percentage<=100){
                    list.add(0,12)
                }
            }

            // Bachelor MARKS
            if(eduType=="Semester" && className=="bachelor"){
                if(1<=percentage && percentage<=44){
                    list.add(0,11)
                }else if(45<=percentage && percentage<=49){
                    list.add(0,12)
                }else if(50<=percentage && percentage<=54){
                    list.add(0,13)
                }else if(55<=percentage && percentage<=59){
                    list.add(0,13)
                }else if(60<=percentage && percentage<=64){
                    list.add(0,15)
                }else if(65<=percentage && percentage<=69){
                    list.add(0,16)
                }else if(70<=percentage && percentage<=74){
                    list.add(0,16)
                }else if(75<=percentage && percentage<=79){
                    list.add(0,17)
                }else if(80<=percentage && percentage<=89){
                    list.add(0,18)
                }else if(90<=percentage && percentage<=100){
                    list.add(0,19)
                }
            }else if(eduType=="Annual" && className=="bachelor"){
                if(1<=percentage && percentage<=39){
                    list.add(0,11)
                }else if(40<=percentage && percentage<=44){
                    list.add(0,12)
                }else if(45<=percentage && percentage<=49){
                    list.add(0,13)
                }else if(50<=percentage && percentage<=54){
                    list.add(0,13)
                }else if(55<=percentage && percentage<=59){
                    list.add(0,15)
                }else if(60<=percentage && percentage<=64){
                    list.add(0,16)
                }else if(65<=percentage && percentage<=69){
                    list.add(0,16)
                }else if(70<=percentage && percentage<=74){
                    list.add(0,17)
                }else if(75<=percentage && percentage<=79){
                    list.add(0,18)
                }else if(80<=percentage && percentage<=100){
                    list.add(0,19)
                }
            }

            list.add(9)
            list.add(12)
            list.add(19)
        }else if(eType=="examtype4"){
            // MATRIC MARKS
            if(eduType=="Semester" && className=="matric"){
                if(1<=percentage && percentage<=44){
                    list.add(0,3)
                }else if(45<=percentage && percentage<=49){
                    list.add(0,3)
                }else if(50<=percentage && percentage<=54){
                    list.add(0,3)
                }else if(55<=percentage && percentage<=59){
                    list.add(0,3)
                }else if(60<=percentage && percentage<=64){
                    list.add(0,3)
                }else if(65<=percentage && percentage<=69){
                    list.add(0,4)
                }else if(70<=percentage && percentage<=74){
                    list.add(0,4)
                }else if(75<=percentage && percentage<=79){
                    list.add(0,5)
                }else if(80<=percentage && percentage<=89){
                    list.add(0,5)
                }else if(90<=percentage && percentage<=100){
                    list.add(0,5)
                }
            }else if(eduType=="Annual" && className=="matric"){
                if(1<=percentage && percentage<=39){
                    list.add(0,3)
                }else if(40<=percentage && percentage<=44){
                    list.add(0,3)
                }else if(45<=percentage && percentage<=49){
                    list.add(0,3)
                }else if(50<=percentage && percentage<=54){
                    list.add(0,3)
                }else if(55<=percentage && percentage<=59){
                    list.add(0,3)
                }else if(60<=percentage && percentage<=64){
                    list.add(0,4)
                }else if(65<=percentage && percentage<=69){
                    list.add(0,4)
                }else if(70<=percentage && percentage<=74){
                    list.add(0,5)
                }else if(75<=percentage && percentage<=79){
                    list.add(0,5)
                }else if(80<=percentage && percentage<=100){
                    list.add(0,5)
                }
            }

            // Inter MARKS
            if(eduType=="Semester" && className=="inter"){
                if(1<=percentage && percentage<=44){
                    list.add(0,5)
                }else if(45<=percentage && percentage<=49){
                    list.add(0,5)
                }else if(50<=percentage && percentage<=54){
                    list.add(0,5)
                }else if(55<=percentage && percentage<=59){
                    list.add(0,5)
                }else if(60<=percentage && percentage<=64){
                    list.add(0,5)
                }else if(65<=percentage && percentage<=69){
                    list.add(0,6)
                }else if(70<=percentage && percentage<=74){
                    list.add(0,6)
                }else if(75<=percentage && percentage<=79){
                    list.add(0,6)
                }else if(80<=percentage && percentage<=89){
                    list.add(0,7)
                }else if(90<=percentage && percentage<=100){
                    list.add(0,7)
                }
            }else if(eduType=="Annual" && className=="inter"){
                if(1<=percentage && percentage<=39){
                    list.add(0,5)
                }else if(40<=percentage && percentage<=44){
                    list.add(0,5)
                }else if(45<=percentage && percentage<=49){
                    list.add(0,5)
                }else if(50<=percentage && percentage<=54){
                    list.add(0,5)
                }else if(55<=percentage && percentage<=59){
                    list.add(0,5)
                }else if(60<=percentage && percentage<=64){
                    list.add(0,6)
                }else if(65<=percentage && percentage<=69){
                    list.add(0,6)
                }else if(70<=percentage && percentage<=74){
                    list.add(0,6)
                }else if(75<=percentage && percentage<=79){
                    list.add(0,7)
                }else if(80<=percentage && percentage<=100){
                    list.add(0,7)
                }
            }

            // Bachelor MARKS
            if(eduType=="Semester" && className=="bachelor"){
                if(1<=percentage && percentage<=44){
                    list.add(0,7)
                }else if(45<=percentage && percentage<=49){
                    list.add(0,8)
                }else if(50<=percentage && percentage<=54){
                    list.add(0,8)
                }else if(55<=percentage && percentage<=59){
                    list.add(0,9)
                }else if(60<=percentage && percentage<=64){
                    list.add(0,9)
                }else if(65<=percentage && percentage<=69){
                    list.add(0,9)
                }else if(70<=percentage && percentage<=74){
                    list.add(0,10)
                }else if(75<=percentage && percentage<=79){
                    list.add(0,10)
                }else if(80<=percentage && percentage<=89){
                    list.add(0,10)
                }else if(90<=percentage && percentage<=100){
                    list.add(0,11)
                }
            }else if(eduType=="Annual" && className=="bachelor"){
                if(1<=percentage && percentage<=39){
                    list.add(0,7)
                }else if(40<=percentage && percentage<=44){
                    list.add(0,8)
                }else if(45<=percentage && percentage<=49){
                    list.add(0,8)
                }else if(50<=percentage && percentage<=54){
                    list.add(0,9)
                }else if(55<=percentage && percentage<=59){
                    list.add(0,9)
                }else if(60<=percentage && percentage<=64){
                    list.add(0,9)
                }else if(65<=percentage && percentage<=69){
                    list.add(0,10)
                }else if(70<=percentage && percentage<=74){
                    list.add(0,10)
                }else if(75<=percentage && percentage<=79){
                    list.add(0,10)
                }else if(80<=percentage && percentage<=100){
                    list.add(0,11)
                }
            }

            // Master MARKS
            if(eduType=="Semester" && className=="master"){
                if(1<=percentage && percentage<=44){
                    list.add(0,9)
                }else if(45<=percentage && percentage<=49){
                    list.add(0,9)
                }else if(50<=percentage && percentage<=54){
                    list.add(0,10)
                }else if(55<=percentage && percentage<=59){
                    list.add(0,11)
                }else if(60<=percentage && percentage<=64){
                    list.add(0,13)
                }else if(65<=percentage && percentage<=69){
                    list.add(0,14)
                }else if(70<=percentage && percentage<=74){
                    list.add(0,14)
                }else if(75<=percentage && percentage<=79){
                    list.add(0,15)
                }else if(80<=percentage && percentage<=89){
                    list.add(0,16)
                }else if(90<=percentage && percentage<=100){
                    list.add(0,17)
                }
            }else if(eduType=="Annual" && className=="master"){
                if(1<=percentage && percentage<=39){
                    list.add(0,9)
                }else if(40<=percentage && percentage<=44){
                    list.add(0,9)
                }else if(45<=percentage && percentage<=49){
                    list.add(0,10)
                }else if(50<=percentage && percentage<=54){
                    list.add(0,11)
                }else if(55<=percentage && percentage<=59){
                    list.add(0,13)
                }else if(60<=percentage && percentage<=64){
                    list.add(0,14)
                }else if(65<=percentage && percentage<=69){
                    list.add(0,14)
                }else if(70<=percentage && percentage<=74){
                    list.add(0,15)
                }else if(75<=percentage && percentage<=79){
                    list.add(0,16)
                }else if(80<=percentage && percentage<=100){
                    list.add(0,17)
                }
            }

            list.add(5)
            list.add(7)
            list.add(11)
            list.add(17)
        }

        return list
    }


}