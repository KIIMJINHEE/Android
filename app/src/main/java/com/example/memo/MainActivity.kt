package com.example.memo

import android.annotation.SuppressLint
import android.content.Context
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

@Suppress("DEPRECATION")
@SuppressLint("StaticFieldLeak")
class MainActivity : AppCompatActivity() , OnDeleteListener {

    lateinit var db : MemoDatabase
    var memoList = listOf<MemoEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = MemoDatabase.getInstance(this)!!

        button_add.setOnClickListener{
            val memo = MemoEntity(null, edittext_memo.text.toString())
            insertMemo(memo)
        }

        //LayoutManager 설정
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    //1. Insert Data
    //2. Get Data
    //3. Delete Data

    //4. Set RecyclerView
    // ㄴRecycleView = Listview에서 조금 더 진보하고, 유연한 버전

    fun insertMemo(memo : MemoEntity){
        //1. MainThread vs WorkerThread(Background Thread)
        //ㄴ 모든 UI 관련된 일은 MainThread에서 한다.
        //ㄴ 모든 데이터 통신 일은 WorkerThread에서 한다.

        val insertTask = object : AsyncTask<Unit,Unit,Unit>(){
            override fun doInBackground(vararg params: Unit?){
                db.memoDAO().insert(memo)
            }

            @SuppressLint("StaticFieldLeak")
            override fun onPostExecute(result: Unit?){
                super.onPostExecute(result)
                getAllMemo()
            }
        }

        insertTask.execute()
    }

    fun getAllMemo(){
        val getTask = object : AsyncTask<Unit,Unit,Unit>(){

            override fun doInBackground(vararg params: Unit?){
                memoList = db.memoDAO().getAll()
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                setRecyclerView(memoList)
            }
        }

        getTask.execute()
    }

    fun deleteMemo(memo : MemoEntity){
        val deleteTask = object :  AsyncTask<Unit,Unit,Unit>(){
            override fun doInBackground(vararg p0: Unit?) {
                db.memoDAO().delete(memo)
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                getAllMemo()
            }
        }

        deleteTask.execute()
    }

    fun setRecyclerView(memoList : List<MemoEntity>){
        //RecyclerView : 기존의 ListView처럼 List개수만큼 view를 생성하는게 아닌,
        //화면에 보여질만큼의 view를 생성하고, 계속 재활용함
        //ㄴ준비물 2개
        //1. Adapter : view를 생성하고, 만든 view 안에서  어떻게 data를 binding 할지 정의.
        //2. Layout Manager : RecyclerView 안에서 어떻게 아이템을 보여줄지 결정.

        recyclerView.adapter = MyAdapter(this, memoList, this)
    }

    override fun OnDeleteListener(memo: MemoEntity){
        deleteMemo(memo)
    }
}