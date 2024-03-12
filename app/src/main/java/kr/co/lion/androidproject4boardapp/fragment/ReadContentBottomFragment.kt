package kr.co.lion.androidproject4boardapp.fragment

import android.app.Dialog
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.androidproject4boardapp.ContentActivity
import kr.co.lion.androidproject4boardapp.R
import kr.co.lion.androidproject4boardapp.databinding.FragmentReadContentBinding
import kr.co.lion.androidproject4boardapp.databinding.FragmentReadContentBottomBinding
import kr.co.lion.androidproject4boardapp.databinding.RowReadContentReplayBinding


class ReadContentBottomFragment : BottomSheetDialogFragment() {

    lateinit var fragmentReadContentBottomBinding: FragmentReadContentBottomBinding
    lateinit var contentActivity: ContentActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        fragmentReadContentBottomBinding = FragmentReadContentBottomBinding.inflate(inflater)
        contentActivity = activity as ContentActivity

        settingRecyclerViewAddContentReply()


        return fragmentReadContentBottomBinding.root
    }

    // RecyclerView 구성 메서드
    fun settingRecyclerViewAddContentReply(){
        fragmentReadContentBottomBinding.apply {
            recyclerViewAddContentReply.apply {
                // 어뎁터
                adapter = BottomRecyclerViewAdapter()
                // 레이아웃 매니저
                layoutManager = LinearLayoutManager(contentActivity)
                // 데코레이션
                val deco = MaterialDividerItemDecoration(contentActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }
    }

    // 댓글 목록을 보여줄 RecyclerView의 어뎁터
    inner class BottomRecyclerViewAdapter : RecyclerView.Adapter<BottomRecyclerViewAdapter.BottomViewHolder>(){

        inner class BottomViewHolder(rowReadContentReplayBinding: RowReadContentReplayBinding) : RecyclerView.ViewHolder(rowReadContentReplayBinding.root){
            val rowReadContentReplayBinding : RowReadContentReplayBinding

            init {
                this.rowReadContentReplayBinding = rowReadContentReplayBinding

                rowReadContentReplayBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BottomViewHolder {
            val rowReadContentReplayBinding = RowReadContentReplayBinding.inflate(layoutInflater)
            val bottomViewHolder = BottomViewHolder(rowReadContentReplayBinding)
            return bottomViewHolder
        }

        override fun getItemCount(): Int {
            return 100
        }

        override fun onBindViewHolder(holder: BottomViewHolder, position: Int) {
            holder.rowReadContentReplayBinding.textViewRowReplyText.text = "댓글입니다 $position"
            holder.rowReadContentReplayBinding.textViewRowReplyNickName.text = "작성자 $position"
            holder.rowReadContentReplayBinding.textViewRowReplyDate.text = "2024-03-07"
        }
    }

    // 다이얼로그가 만들어질 때 자동으로 호출되는 메서드
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // 다이얼로그를 받는다.
        val dialog = super.onCreateDialog(savedInstanceState)
        // 다이얼로그가 보일때 동작하는 리스너
        dialog.setOnShowListener {

            val bottomSheetDialog = it as BottomSheetDialog
            // 높이를 설정한다.
            setBottomSheetHeight(bottomSheetDialog)
        }

        return dialog
    }

    // BottomSheet의 높이를 설정해준다.
    fun setBottomSheetHeight(bottomSheetDialog:BottomSheetDialog){
        // BottomSheet의 기본 뷰 객체를 가져온다
        val bottomSheet = bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)!!
        // BottomSheet 높이를 설정할 수 있는 객체를 생성한다.
        val behavior = BottomSheetBehavior.from(bottomSheet)
        // 높이를 설정한다.
        val layoutParams = bottomSheet.layoutParams
        layoutParams.height = getBottomSheetDialogHeight()
        bottomSheet.layoutParams = layoutParams
        behavior.state = BottomSheetBehavior.STATE_EXPANDED

    }

    // BottomSheet의 높이를 구한다(화면 액정의 85% 크기)
    fun getBottomSheetDialogHeight() : Int {
        return (getWindowHeight() * 0.85).toInt()
    }

    // 사용자 단말기 액정의 길이를 구해 반환하는 메서드
    fun getWindowHeight() : Int {
        // 화면 크기 정보를 담을 배열 객체
        val displayMetrics = DisplayMetrics()
        // 액정의 가로 세로 길이 정보를 담아준다.
        contentActivity.windowManager.defaultDisplay.getMetrics(displayMetrics)
        // 세로길이를 반환해준다.
        return displayMetrics.heightPixels
    }

}