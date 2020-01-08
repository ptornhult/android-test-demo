package com.sqisland.android.test_demo

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.sqisland.android.test_demo.db.Counter
import com.sqisland.android.test_demo.db.CounterDao
import com.sqisland.android.test_demo.db.CounterDatabase
import kotlinx.coroutines.launch
import org.joda.time.DateTime
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var clock: Clock

    @Inject
    lateinit var counterDatabase: CounterDatabase

    private val countDao: CounterDao by lazy {
        counterDatabase.countDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as DemoApplication).component().inject(this)

        val todayView = findViewById<TextView>(R.id.date)

        val millis = intent.getLongExtra(KEY_MILLIS, -1)
        val dateTime = if (millis > 0) DateTime(millis) else clock.getNow()
        todayView.text = DateUtils.format(dateTime)

        val counterView = findViewById<TextView>(R.id.counter)

        counterView.setOnClickListener {
            lifecycleScope.launch {
                val currentCounter = countDao.getCounter()
                val updatedCounter = currentCounter?.copy(count = currentCounter.count + 1)
                        ?: Counter(count = 1)
                countDao.save(updatedCounter)
            }
        }

        countDao.liveCounter().observe(this, Observer { counter ->
            counterView.text = "Current count: ${counter?.count ?: 0}"
        })
    }

    companion object {
        const val KEY_MILLIS = "millis"
    }
}