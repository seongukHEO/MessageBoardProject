package kr.co.lion.androidproject4boardapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.checkbox.MaterialCheckBox
import kr.co.lion.androidproject4boardapp.ContentActivity
import kr.co.lion.androidproject4boardapp.Gender
import kr.co.lion.androidproject4boardapp.R
import kr.co.lion.androidproject4boardapp.Tools
import kr.co.lion.androidproject4boardapp.databinding.FragmentModifyContentBinding
import kr.co.lion.androidproject4boardapp.databinding.FragmentModifyUserBinding
import kr.co.lion.androidproject4boardapp.viewmodel.ModifyUserViewModel


class ModifyUserFragment : Fragment() {

    lateinit var fragmentModifyUserBinding: FragmentModifyUserBinding
    lateinit var contentActivity: ContentActivity

    lateinit var modifyUserViewModel: ModifyUserViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        //fragmentModifyUserBinding = FragmentModifyUserBinding.inflate(inflater)
        fragmentModifyUserBinding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_modify_user, container, false)
        modifyUserViewModel = ModifyUserViewModel()
        fragmentModifyUserBinding.modifyUserViewModel = modifyUserViewModel
        fragmentModifyUserBinding.lifecycleOwner = this

        contentActivity = activity as ContentActivity

        settingToolbarModifyUser()
        settingInputForm()

        return fragmentModifyUserBinding.root
    }

    // 툴바 설정
    fun settingToolbarModifyUser(){
        fragmentModifyUserBinding.apply {
            toolbarModifyUser.apply {
                // 타이틀
                title = "회원 정보 수정"

                setNavigationIcon(R.drawable.menu_24px)
                setNavigationOnClickListener {
                    contentActivity.activityContentBinding.drawerLayoutContent.open()
                }

                // 메뉴
                inflateMenu(R.menu.menu_modify_user)
                setOnMenuItemClickListener {
                    when(it.itemId){
                        R.id.menuItemModifyUserDone -> {
                            val chk = checkInputForm()
                            if (chk == true){
                               Tools.hideSoftInput(contentActivity)
                            }
                        }
                        R.id.menuItemModifyUserReset -> {
                            settingInputForm()
                        }
                    }
                    true
                }
            }
        }
    }

    //입력요소 초기화
    fun settingInputForm(){
        modifyUserViewModel.textFieldModifyUserInfoNickName.value = "홍길동"
        modifyUserViewModel.textFieldModifyUserInfoAge.value = "100"
        modifyUserViewModel.textFieldModifyUserPw.value = ""
        modifyUserViewModel.textFieldModifyUserPw2.value = ""
        modifyUserViewModel.settingGender(Gender.FEMALE)

        modifyUserViewModel.checkBoxModifyUserInfoHobby1.value = true
        modifyUserViewModel.checkBoxModifyUserInfoHobby1.value = true
        modifyUserViewModel.checkBoxModifyUserInfoHobby1.value = false
        modifyUserViewModel.checkBoxModifyUserInfoHobby1.value = true
        modifyUserViewModel.checkBoxModifyUserInfoHobby1.value = true
        modifyUserViewModel.checkBoxModifyUserInfoHobby1.value = false

        modifyUserViewModel.onCheckBoxChanged()
    }
    //유효성 검사!
    fun checkInputForm():Boolean{
        val nickname = modifyUserViewModel.textFieldModifyUserInfoNickName.value!!
        val age = modifyUserViewModel.textFieldModifyUserInfoAge.value!!
        val pw = modifyUserViewModel.textFieldModifyUserPw.value!!
        val pw2 = modifyUserViewModel.textFieldModifyUserPw2.value!!

        if (nickname.isEmpty()){
            Tools.showErrorDialog(contentActivity, fragmentModifyUserBinding.textFieldModifyUserInfoNickName,
                "닉네임 입력 오류", "닉네임을 입력해주세요")
            return false
        }
        if (age.isEmpty()){
            Tools.showErrorDialog(contentActivity, fragmentModifyUserBinding.textFieldModifyUserInfoAge,
                "나이 입력 오류", "나이를 입력해주세요")
            return false
        }
        if ((pw.isNotEmpty() || pw2.isNotEmpty()) && pw != pw2){
            modifyUserViewModel.textFieldModifyUserPw.value = ""
            modifyUserViewModel.textFieldModifyUserPw2.value = ""

            Tools.showErrorDialog(contentActivity, fragmentModifyUserBinding.textFieldModifyUserPw,
                "비밀번호 입력 오류", "비밀번호가 다릅니다")
            return false
        }
        return true
    }
}

































