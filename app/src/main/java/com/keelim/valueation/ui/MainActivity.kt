package com.keelim.valueation.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kakao.sdk.talk.TalkApiClient
import com.kakao.sdk.template.model.Link
import com.kakao.sdk.template.model.TextTemplate
import com.keelim.valueation.R
import com.keelim.valueation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private lateinit var binding: ActivityMainBinding
    private val data
        get() = arrayListOf<Float>(
            binding.edit1.text.toString().toFloat(),
            binding.edit2.text.toString().toFloat(),
            binding.edit3.text.toString().toFloat()
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setSupportActionBar(binding.toolbar)


        binding.edit1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val list = data
                if (list.size != 3)
                    Toast.makeText(this@MainActivity, "데이터가 충분하지 않습니다.", Toast.LENGTH_SHORT).show()

                binding.result.text = (list[0] / list[1] * list[2]).toString()
            }
        })

        binding.kakao.setOnClickListener { sendMessage(makingMessage()) }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun makingMessage(): TextTemplate {
        return TextTemplate(
            text = """
        카카오링크는 카카오 플랫폼 서비스의 대표 기능으로써 사용자의 모바일 기기에 설치된 카카오 플랫폼과 연동하여 다양한 기능을 실행할 수 있습니다.
        현재 이용할 수 있는 카카오링크는 다음과 같습니다.
        카카오톡링크
        카카오톡을 실행하여 사용자가 선택한 채팅방으로 메시지를 전송합니다.
        카카오스토리링크
        카카오스토리 글쓰기 화면으로 연결합니다.
    """.trimIndent(),
            link = Link(
                webUrl = "",
                mobileWebUrl = ""
            )
        )
    }

    private fun sendMessage(text: TextTemplate) {
        TalkApiClient.instance.sendDefaultMemo(text) { error ->
            if (error != null) {
                Log.e(TAG, "나에게 보내기 실패", error)
            } else {
                Log.i(TAG, "나에게 보내기 성공")
            }
        }
    }

    companion object {
        val TAG = "MainActivity"
    }

}