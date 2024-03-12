package kr.co.lion.androidproject4boardapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class JoinViewModel : ViewModel(){
    // 아이디
    val textFieldJoinUserId = MutableLiveData<String>()
    // 비밀번호
    val textFieldJoinUserPw = MutableLiveData<String>()
    // 비밀번호2
    val textFieldJoinUserPw2 = MutableLiveData<String>()
    // 툴바의 타이틀
    val toolbarJoinTitle = MutableLiveData<String>()
    val toolbarJoinNavigationIcon = MutableLiveData<Int>()
}