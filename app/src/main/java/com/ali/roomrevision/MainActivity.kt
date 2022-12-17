package com.ali.roomrevision

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.ali.roomrevision.databinding.ActivityMainBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.CompletableObserver
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private val db by lazy { PostsDB.getDatabase(this).postsDao() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var adapter=RecAdapter()
        binding.mainRv.adapter=adapter
        binding.mainRv.layoutManager=LinearLayoutManager(this)

        binding.mainBtnSave.setOnClickListener(View.OnClickListener {
          db.insertPost(Post(0,User(56,"alio"),binding.mainEtTitle.text.toString(),binding.mainEtBody.text.toString()))
              .subscribeOn(Schedulers.computation())
              .subscribe(object :CompletableObserver{
                  override fun onSubscribe(d: Disposable) {

                  }

                  override fun onComplete() {
                  }

                  override fun onError(e: Throwable) {
                  }

              })
        })

        binding.mainBtnGet.setOnClickListener(View.OnClickListener {
            db.getPosts().subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object :SingleObserver<List<Post>>{
                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onSuccess(t: List<Post>) {
                        adapter.setlist(t)
                        Log.i("aly",t.toString())
                        adapter.notifyDataSetChanged()
                    }

                    override fun onError(e: Throwable) {
                    }

                })
        })

    }
}