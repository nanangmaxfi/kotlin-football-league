package id.web.nanangmaxfi.footballeague

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import org.jetbrains.anko.*

class DetailActivity : AppCompatActivity() {
    lateinit var name: TextView
    lateinit var image: ImageView
    lateinit var description: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        scrollView {
            verticalLayout {
                image = imageView {
                    scaleType = ImageView.ScaleType.CENTER_CROP
                }.lparams {
                    height = dip(100)
                    width = dip(100)
                    gravity = Gravity.CENTER_HORIZONTAL
                }

                name = textView {
                    textSize = 16f
                    typeface = Typeface.DEFAULT_BOLD

                }.lparams {
                    margin = dip(16)
                    gravity = Gravity.CENTER
                }

                description = textView {

                }.lparams {
                    margin = dip(10)
                }

            }
        }


        val league: League = intent.getParcelableExtra("league")
        name.text = league.name
        description.text = league.description
        Glide.with(applicationContext)
            .load(league.image)
            .into(image)
    }
}
