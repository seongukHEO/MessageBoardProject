package kr.co.lion.androidproject4boardapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import kr.co.lion.androidproject4boardapp.MainActivity
import kr.co.lion.androidproject4boardapp.MainFragmentName
import kr.co.lion.androidproject4boardapp.R
import kr.co.lion.androidproject4boardapp.Tools
import kr.co.lion.androidproject4boardapp.databinding.FragmentJoinBinding
import kr.co.lion.androidproject4boardapp.viewmodel.JoinViewModel


class JoinFragment : Fragment() {

    lateinit var fragmentJoinBinding: FragmentJoinBinding
    lateinit var mainActivity: MainActivity
    lateinit var joinViewModel: JoinViewModel

    // 아이디 중복 확인 검사를 했는지..
    // true면 아이디 중복 확인 검사를 완료한 것으로 취급한다.
    var checkUserIdExist = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        // fragmentJoinBinding = FragmentJoinBinding.inflate(inflater)
        // 바인딩 객체를 생성한다. ViewBinding의 기능을 포함한다
        // 첫 번째 : LayoutInflater
        // 두 번째 : 화면을 만들 때 사용할 layout폴더의 xml 파일
        // 세 번째 : xml 을 통해서 만들어진 화면을 누가 관리하게 할 것인가. 여기서는 Fragment를 의미한다.
        // 네 번째 : Fragment 상태에 영향을 받을 것인가...
        fragmentJoinBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_join, container, false)
        // ViewModel 객체를 생성한다.
        joinViewModel = JoinViewModel()
        // 생성한 ViewModel 객체를 layout 파일에 설정해준다.
        fragmentJoinBinding.joinViewModel = joinViewModel
        // ViewModel의 생명 주기를 Fragment와 일치시킨다. Fragment가 살아 있을 때 ViewModel 객체도 살아 있겠금 해준다.
        fragmentJoinBinding.lifecycleOwner = this

        mainActivity = activity as MainActivity

        settingToolbar()
        settingButtonJoinNext()
        settingTextField()
        settingButtonJoinCheckId()

        return fragmentJoinBinding.root
    }

    // 툴바 설정
    fun settingToolbar(){
        
        // 타이틀에 설정해준다.
        joinViewModel.toolbarJoinTitle.value = "회원가입"
        joinViewModel.toolbarJoinNavigationIcon.value = R.drawable.arrow_back_24px
        
        fragmentJoinBinding.apply {
            toolbarJoin.apply {
                // 타이틀
                // title = "회원가입"
                // Back
                // setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    // 이전 화면으로 간다.
                    mainActivity.removeFragment(MainFragmentName.JOIN_FRAGMENT)
                }
            }
        }
    }

    // 다음 버튼
    fun settingButtonJoinNext(){
        fragmentJoinBinding.apply {
            buttonJoinNext.apply {
                // 버튼을 눌렀을 때
                setOnClickListener  {
                    // 입력을 검사한다.
                    val chk = checkTextInput()

                    // 입력이 모두 잘 되어 있다면..
                    if(chk == true) {
                        // 키보를 내려준다.
                        Tools.hideSoftInput(mainActivity)
                        // AddUserInfoFragment를 보여준다.
                        mainActivity.replaceFragment(MainFragmentName.ADD_USER_INFO_FRAGMENT, true, true, null)
                    }
                }
            }
        }
    }

    // 입력요소 초기설정
    fun settingTextField(){
        // 입력 요소들을 초기화 한다.
        joinViewModel.textFieldJoinUserId.value = ""
        joinViewModel.textFieldJoinUserPw.value = ""
        joinViewModel.textFieldJoinUserPw2.value = ""
        // 첫 번째 입력 요소에 포커스를 준다.
        Tools.showSoftInput(mainActivity, fragmentJoinBinding.textFieldJoinUserId)
        // 아이디 입력요소의 값을 변경하면 중복확인 여부 변수값을 false로 설정한다.
        fragmentJoinBinding.textFieldJoinUserId.addTextChangedListener {
            checkUserIdExist = false
        }
    }

    // 입력요소 유효성 검사 메서드
    fun checkTextInput():Boolean{

        // 사용자가 입력한 내용을 가져온다
        val userId = joinViewModel.textFieldJoinUserId.value!!
        val userPw = joinViewModel.textFieldJoinUserPw.value!!
        val userPw2 = joinViewModel.textFieldJoinUserPw2.value!!

        // 아이디를 입력하지 않았다면
        if(userId.isEmpty()){
            Tools.showErrorDialog(mainActivity, fragmentJoinBinding.textFieldJoinUserId, "아이디 입력 오류",
                "아이디를 입력해주세요")
            return false
        }

        // 비밀번호를 입력하지 않았다면
        if(userPw.isEmpty()){
            Tools.showErrorDialog(mainActivity, fragmentJoinBinding.textFieldJoinUserPw, "비밀번호 입력 오류",
                "비밀번호를 입력해주세요")
            return false
        }

        // 비밀번호 확인을 입력하지 않았다면
        if(userPw2.isEmpty()){
            Tools.showErrorDialog(mainActivity, fragmentJoinBinding.textFieldJoinUserPw2, "비밀번호 입력 오류",
                "비밀번호를 입력해주세요")
            return false
        }

        // 입력한 비밀번호가 서로 다르다면
        if(userPw != userPw2){
            joinViewModel.textFieldJoinUserPw.value = ""
            joinViewModel.textFieldJoinUserPw2.value = ""
            Tools.showErrorDialog(mainActivity, fragmentJoinBinding.textFieldJoinUserPw, "비밀번호 입력 오류",
                "비밀번호가 다릅니다")
            return false
        }

        // 아이디 중복확인을 하지 않았다면..
        if(checkUserIdExist == false){
            Tools.showErrorDialog(mainActivity, fragmentJoinBinding.textFieldJoinUserId, "아이디 중복 확인 오류",
                "아이디 중복확인을 해주세요")
            return false
        }

        return true
    }

    // 중복확인 버튼
    fun settingButtonJoinCheckId(){
        fragmentJoinBinding.apply {
            buttonJoinCheckId.apply {
                setOnClickListener {
                    checkUserIdExist = true
                }
            }
        }
    }
}








