package com.keelim.valueation.ui

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.snackbar.Snackbar
import com.kakao.sdk.talk.TalkApi
import com.kakao.sdk.talk.TalkApiClient
import com.kakao.sdk.template.model.Link
import com.kakao.sdk.template.model.TextTemplate
import com.keelim.valueation.R
import com.keelim.valueation.databinding.ActivityMainBinding
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            sendMessage(makingMessage())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    private fun makingMessage(): TextTemplate {
        return  TextTemplate(
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

    private fun sendMessage(text: TextTemplate){
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