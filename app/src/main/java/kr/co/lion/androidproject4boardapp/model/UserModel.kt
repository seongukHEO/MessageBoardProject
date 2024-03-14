package kr.co.lion.androidproject4boardapp.model

// 회원번호, 아이디, 비밀번호, 닉네임, 나이, 성별 , 취미6가지, 회원상태
data class UserModel(var userIdx:Int, var userId:String,
                     var userPw:String, var userNickName:String, var userAge:Int, var userGender:Int,
                     var userHobby1:Boolean, var userHobby2:Boolean, var userHobby3: Boolean,
                     var userHobby4: Boolean, var userHobby5: Boolean, var userHobby6: Boolean,
                     var userState:Int)