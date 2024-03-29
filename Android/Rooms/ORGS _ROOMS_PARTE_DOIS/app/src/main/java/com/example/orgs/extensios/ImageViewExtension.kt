import android.widget.ImageView
import coil.load
import com.example.orgs.R

fun ImageView.loadWithLoading(url: String? = null) {
    load(url) {
        fallback(R.drawable.erro)
        error(R.drawable.erro)
        placeholder(R.drawable.placeholder)
    }
}