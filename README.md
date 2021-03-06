# ๐[Android] Jetpack cameraX demo

## โ๏ธ Study

- https://yotdark.tistory.com/43
- [[Android] ViewPager2 ์ฌ์ฉํด ์ด๋ฏธ์ง ์ฌ๋ผ์ด๋ ๊ตฌํ (1)](https://github.com/Junnnnnnnnnnn/android_study/blob/master/Jetpack_viewPager2/%5BAndroid%5D%20ViewPager2%20%EC%82%AC%EC%9A%A9%ED%95%B4%20%EC%9D%B4%EB%AF%B8%EC%A7%80%20%EC%8A%AC%EB%9D%BC%EC%9D%B4%EB%93%9C%20%EA%B5%AC%ED%98%84%20(1).md)
- [[Android] ViewPager2 ์ฌ์ฉํด ์ด๋ฏธ์ง ์ฌ๋ผ์ด๋ ๊ตฌํ (2)](https://github.com/Junnnnnnnnnnn/android_study/blob/master/Jetpack_viewPager2/%5BAndroid%5D%20ViewPager2%20%EC%82%AC%EC%9A%A9%ED%95%B4%20%EC%9D%B4%EB%AF%B8%EC%A7%80%20%EC%8A%AC%EB%9D%BC%EC%9D%B4%EB%93%9C%20%EA%B5%AC%ED%98%84%20(2).md)

## ๐ Setting

- CompileSdk = 30
- Minsdk = 24
- TargetSdk = 30

## ๐คจ Why

- ์ด๋ฏธ์ง ์ฌ๋ผ์ด๋์ ๋ฏธ๋ฆฌ๋ณด๊ธฐ ์ต์ ๋ฑ ๋ค์ํ ๊ธฐ๋ฅ์ Jetpack ์ ํตํด ์ฝ๊ฒ ๊ตฌํ ํ๊ธฐ ์ํด์

## ๐ Try 

- Jetpack viewPager2 ์ฌ์ฉํด๋ณด๊ธฐ
- ๋ค์ ์ฌ์ง ๋ฏธ๋ฆฌ๋ณด๊ธฐ ์ค์ ํด๋ณด๊ธฐ
- ๋ฐ์ดํฐ ์ฌ๋ผ์ด๋ ํ  ๋ ์ด๋ฒคํธ ์ฒ๋ฆฌ ํด๋ณด๊ธฐ

## โ๏ธ Gradle ์ค์ 

```groovy
dependencies {
					.
					.
					.
    // view Pager2
    implementation "androidx.viewpager2:viewpager2:1.0.0"
    			.
    			.
    			.
}
```

## โ๏ธ ViewPager Class ๋ง๋ค๊ธฐ

```kotlin
/**
 * RecyclerView Adapter ๋ฅผ ์์ ๋ฐ๋๋ค
 * ์ ๋ค๋ฆญ ํ์์ผ๋ก  UI์ ๋ณด์ฌ์ค View ๋ฅผ ๋ด๋ ViewHolder ์ด๋ค.
 */
class ViewPager2Adepter(private val context: Context, private val imageList: MutableList<Int> ): RecyclerView.Adapter<ViewPager2Adepter.PagerViewHolder>() {

    /**
     * View ๋ฅผ ๋ด์ ViewHolder class ๋ฅผ ์ ์ ํ๋ค.
     */
    inner class PagerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val item: ImageView = itemView.findViewById(R.id.imageView1)
    }

    /**
     * ViewHolder ๋ฅผ ์ธ์คํด์คํ ํ๊ณ  return
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val view = LayoutInflater.from(context).inflate(
            R.layout.viewholder_main,
            parent,
            false
        )
        return PagerViewHolder(view)
    }

    /**
     * ๋ทฐ์ ๋ฐ์ดํฐ๋ฅผ ๋ฐ์ธ๋ฉ ํ๋ ๋ฉ์๋
     */
    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.item.setImageDrawable(ContextCompat.getDrawable(context, imageList[position]))
    }

    override fun getItemCount() = imageList.size

}
```

- ### โ๏ธ ๋ทฐ ํ๋ ๋ง๋ค์ด ์์ ๋ญ๋น ๋ฐฉ์ง ํ๊ธฐ

  ```kotlin
  inner class PagerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
  		val item: ImageView = itemView.findViewById(R.id.imageView1)
  }
  ```

- ### โ๏ธ ๋ทฐ ํ๋ ์ธ์คํด์คํ ํ๊ธฐ

  ```kotlin
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
      val view = LayoutInflater.from(context).inflate(
          R.layout.viewholder_main,
          parent,
          false
      )
      return PagerViewHolder(view)
  }
  ```

- ### โ๏ธ ๋ทฐ์ ๋ฐ์ดํฐ ๋ฐ์ธ๋ฉ ํ๊ธฐ

  ```kotlin
  override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
      holder.item.setImageDrawable(ContextCompat.getDrawable(context, imageList[position]))
  }
  ```

## โ๏ธ MainActivity Class ๋ง๋ค๊ธฐ

```kotlin
class MainActivity : AppCompatActivity() {

    private val imageList = mutableListOf<Int>().apply {
        add(R.drawable.download_1)
        add(R.drawable.download_2)
        add(R.drawable.download_3)
        add(R.drawable.download_4)
    }

    private val textList = mutableListOf<String>().apply {
        add("๋ํ๋ฏผ๊ตญ")
        add("๋ฏธ๊ตญ")
        add("์๊ตญ")
        add("์ค๊ตญ")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainInitViewPager2()
    }

    private fun mainInitViewPager2(){
      content_text.text = "๋ํ๋ฏผ๊ตญ"
      viewPager.apply {
        clipToPadding= false
        clipChildren= false
        offscreenPageLimit = 1
        adapter = ViewPager2Adepter(this@MainActivity, imageList)
      }
      viewPager.setPageTransformer(MarginPageTransformer(100))
      viewPager.setPadding(200,0,200,0)
      mainViewChangeEvent()
    }

    private fun mainViewChangeEvent(){
        viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                content_text.text = textList[position]
            }
        })
    }
}
```

- ### โ๏ธ ViewPager2 ์ค์  ๋ฐ ๋ฐ์ดํฐ ์ฌ๋ผ์ด๋ ํ  ๋ ๋ค์ ๋ฐ์ดํฐ ๋ฏธ๋ฆฌ ๋ณด๋ ๊ฒ ๊ตฌํ ํด ๋ณด๊ธฐ

  ```kotlin
  private fun mainInitViewPager2(){
    content_text.text = "๋ํ๋ฏผ๊ตญ"
    viewPager.apply {
      clipToPadding= false
      clipChildren= false
      offscreenPageLimit = 1
      adapter = ViewPager2Adepter(this@MainActivity, imageList)
    }
    viewPager.setPageTransformer(MarginPageTransformer(100))
    viewPager.setPadding(200,0,200,0)
    mainViewChangeEvent()
  }
  ```

- ### โ๏ธ ๋ฐ์ดํฐ ์ฌ๋ผ์ด๋ ํ ๋ ์ด๋ฒคํธ ์ฒ๋ฆฌ ํด๋ณด๊ธฐ

  ```kotlin
  private fun mainViewChangeEvent(){
    viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
      override fun onPageSelected(position: Int) {
        content_text.text = textList[position]
      }
    })
  }
  ```

  
