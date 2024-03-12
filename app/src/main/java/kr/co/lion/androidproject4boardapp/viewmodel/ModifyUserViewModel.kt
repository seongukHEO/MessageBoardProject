package kr.co.lion.androidproject4boardapp.viewmodel

import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.button.MaterialButtonToggleGroup
import com.google.android.material.checkbox.MaterialCheckBox
import kr.co.lion.androidproject4boardapp.Gender
import kr.co.lion.androidproject4boardapp.R

class ModifyUserViewModel : ViewModel(){
    // 닉네임
    val textFieldModifyUserInfoNickName = MutableLiveData<String>()
    // 나이
    val textFieldModifyUserInfoAge = MutableLiveData<String>()
    // 비밀번호
    val textFieldModifyUserPw = MutableLiveData<String>()
    // 비멀번호 확인
    val textFieldModifyUserPw2 = MutableLiveData<String>()

    // 성별
    val toggleModifyUserInfoGender = MutableLiveData<Int>()


    // 취미들
    val checkBoxModifyUserInfoHobby1 = MutableLiveData<Boolean>()
    val checkBoxModifyUserInfoHobby2 = MutableLiveData<Boolean>()
    val checkBoxModifyUserInfoHobby3 = MutableLiveData<Boolean>()
    val checkBoxModifyUserInfoHobby4 = MutableLiveData<Boolean>()
    val checkBoxModifyUserInfoHobby5 = MutableLiveData<Boolean>()
    val checkBoxModifyUserInfoHobby6 = MutableLiveData<Boolean>()

    // 취미 전체
    val checkBoxModifyUserInfoAllState = MutableLiveData<Int>()
    val checkBoxModifyUserInfoAll = MutableLiveData<Boolean>()

    // 성별을 셋팅하는 메서드
    fun settingGender(gender: Gender){
        // 성별로 분기한다.
        when(gender){
            Gender.MALE -> {
                toggleModifyUserInfoGender.value = R.id.buttonModifyUserInfoMale
            }
            Gender.FEMALE -> {
                toggleModifyUserInfoGender.value = R.id.buttonModifyUserInfoFemale
            }
        }
    }

    companion object {
        // ViewModel에 값을 설정하여 화면에 반영하는 작업을 할 때 호출된다.
        // () 안에는 속성의 이름을 넣어준다. 속성의 이름은 자유롭게 해주면 되지만 기존의 속성 이름과 중복되지 않게
        // 해줘야 한다.
        // 매개변수 : 값이 설정된 View 객체, ViewModel을 통해 설정되는 값
        @BindingAdapter("android:checkedButtonId")
        @JvmStatic
        fun setCheckedButtonId(group: MaterialButtonToggleGroup, buttonId:Int){
            group.check(buttonId)
        }

        // 값을 속성에 넣어주는 것을 순방향이라고 부른다. 반대로 속성의 값이 변경되어 MutableLive 데이터로
        // 전달하는 것을 역방햐이라고 한다.
        // 순방향만 구현해주면 단방향이 되는 것이고 순방향과 역방향을 모두 구현해주면 양방향
        // 화면 요소가 가진 속성애 새로운 값이 설정되면 ViewModel의 변수에 값이 설정될 때 호출된다.
        // 리스너 역할을 할 속성을 만들어준다.
        @BindingAdapter("checkedButtonChangeListener")
        @JvmStatic
        fun checkedButtonChangeListener(group:MaterialButtonToggleGroup, inverseBindingListener: InverseBindingListener){
            group.addOnButtonCheckedListener { group, checkedId, isChecked ->
                inverseBindingListener.onChange()
            }
        }
        // 역방향 바인딩이 벌어질 때 호출된다.
        @InverseBindingAdapter(attribute = "android:checkedButtonId", event="checkedButtonChangeListener")
        @JvmStatic
        fun getCheckedButtonId(group:MaterialButtonToggleGroup):Int{
            return group.checkedButtonId
        }
    }
    // 체크박스 전체 상태를 설정하는 메서드
    fun setCheckAll(checked:Boolean){
        checkBoxModifyUserInfoHobby1.value = checked
        checkBoxModifyUserInfoHobby2.value = checked
        checkBoxModifyUserInfoHobby3.value = checked
        checkBoxModifyUserInfoHobby4.value = checked
        checkBoxModifyUserInfoHobby5.value = checked
        checkBoxModifyUserInfoHobby6.value = checked
    }
    // 전체 취미 체크박스를 누르면...
    fun onCheckBoxAllChanged(){
        // 전체 취미 체크박스의 체크 여부를 모든 체크박스에 설정해준다.
        setCheckAll(checkBoxModifyUserInfoAll.value!!)
    }

    // 각 체크박스를 누르면...
    fun onCheckBoxChanged(){
        // 체크되어 있는 체크박스 개수를 담을 변수
        var checkedCnt = 0

        // 체크되어 있다면 체크되어 있는 체크박스의 개수를 1 증가시킨다.
        if(checkBoxModifyUserInfoHobby1.value == true){
            checkedCnt++
        }
        if(checkBoxModifyUserInfoHobby2.value == true){
            checkedCnt++
        }
        if(checkBoxModifyUserInfoHobby3.value == true){
            checkedCnt++
        }
        if(checkBoxModifyUserInfoHobby4.value == true){
            checkedCnt++
        }
        if(checkBoxModifyUserInfoHobby5.value == true){
            checkedCnt++
        }
        if(checkBoxModifyUserInfoHobby6.value == true){
            checkedCnt++
        }

        // 체크되어 있는 것이 없다면
        if(checkedCnt == 0){
            checkBoxModifyUserInfoAll.value = false
            checkBoxModifyUserInfoAllState.value = MaterialCheckBox.STATE_UNCHECKED
        }
        // 모두 체크되어 있다면
        else if(checkedCnt == 6){
            checkBoxModifyUserInfoAll.value = true
            checkBoxModifyUserInfoAllState.value = MaterialCheckBox.STATE_CHECKED
        }
        // 일부만 체크되어 있다면
        else {
            checkBoxModifyUserInfoAllState.value = MaterialCheckBox.STATE_INDETERMINATE
        }
    }
}