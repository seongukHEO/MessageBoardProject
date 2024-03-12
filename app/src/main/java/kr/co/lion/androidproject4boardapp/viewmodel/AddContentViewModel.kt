package kr.co.lion.androidproject4boardapp.viewmodel

import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.button.MaterialButtonToggleGroup
import kr.co.lion.androidproject4boardapp.ContentType
import kr.co.lion.androidproject4boardapp.R

class AddContentViewModel : ViewModel() {
    // 제목
    val textFieldAddContentSubject = MutableLiveData<String>()
    // 내용
    val textFieldAddContentText = MutableLiveData<String>()
    //게시판
    // 게시판 종류
    val toggleAddContentType = MutableLiveData<Int>()

    // 게시판 종류를 받아 MutableLiveData에 설정하는 메서드
    fun settingContentType(contentType:ContentType){
        toggleAddContentType.value = when(contentType){
            ContentType.TYPE_ALL -> 0
            ContentType.TYPE_FREE -> R.id.buttonAddContentType1
            ContentType.TYPE_HUMOR -> R.id.buttonAddContentType2
            ContentType.TYPE_SOCIETY -> R.id.buttonAddContentType3
            ContentType.TYPE_SPORTS -> R.id.buttonAddContentType4

        }
    }
    companion object {
        // toggleAddContentType 에 값을 설정했을 때 checkedButtonId 속성에 값을 반영해 줄 때 호출(순방향)
        @BindingAdapter("android:checkedButtonId")
        @JvmStatic
        fun setCheckedButtonId(group: MaterialButtonToggleGroup, buttonId:Int){
            group.check(buttonId)
        }

        // 안드로이드 OS가 현재 화면을 구성하고자 할 때 InverseBindingAdapter에 등록되어 있는 event 값을 보고
        // event에 등록되어 있는 이름과 동일한 BindingAdapter를 찾아 메서드를 호출해준다.
        // 리스너 설정을 해준다.
        @BindingAdapter("checkedButtonChangeListener")
        @JvmStatic
        fun checkedButtonChangeListener(group:MaterialButtonToggleGroup, inverseBindingListener: InverseBindingListener){
            group.addOnButtonCheckedListener { group, checkedId, isChecked ->
                inverseBindingListener.onChange()
            }
        }

        // 안드로이드 OS가 현재 화면을 구성하고자 할 때 속성에 설정된 MutableLiveData가 양방향(@=)으로 되어 있을 경우
        // 참고할 데이터가 셋팅되어 있는 메서드
        // inverseBindingListener.onChange() 호출시 동작할 메서드
        @InverseBindingAdapter(attribute = "android:checkedButtonId", event="checkedButtonChangeListener")
        @JvmStatic
        fun getCheckedButtonId(group:MaterialButtonToggleGroup):Int{
            return group.checkedButtonId
        }
    }
}