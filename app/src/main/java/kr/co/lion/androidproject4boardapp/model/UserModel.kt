package kr.co.lion.androidproject4boardapp.model

// 회원번호, 아이디, 비밀번호, 닉네임, 나이, 성별 , 취미6가지, 회원상태
data class UserModel(var userIdx:Int, var userId:String,
                     var userPw:String, var userNickName:String, var userAge:Int, var userGender:Int,
                     var userHobby1:Boolean, var userHobby2:Boolean, var userHobby3: Boolean,
                     var userHobby4: Boolean, var userHobby5: Boolean, var userHobby6: Boolean,
                     var userState:Int){

    // 매개 변수가 없는 생성자
    // fireStore 를 사용할 때 데이터를 담을 클래스 타입을 지정하게 되면
    // 매개 변수가 없는 생성자를 사용해 객체 생성해주기 때문에 만들어줘야 한다.
    constructor() : this(0, "", "", "", 0, 0, false, false, false, false, false, false, 0)

}