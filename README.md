# 🏃[Android] Jetpack cameraX demo

## ✏️ Study

- https://yotdark.tistory.com/43
- [[Android] ViewPager2 사용해 이미지 슬라이드 구현 (1)](https://github.com/Junnnnnnnnnnn/android_study/blob/master/Jetpack_viewPager2/%5BAndroid%5D%20ViewPager2%20%EC%82%AC%EC%9A%A9%ED%95%B4%20%EC%9D%B4%EB%AF%B8%EC%A7%80%20%EC%8A%AC%EB%9D%BC%EC%9D%B4%EB%93%9C%20%EA%B5%AC%ED%98%84%20(1).md)
- [[Android] ViewPager2 사용해 이미지 슬라이드 구현 (2)](https://github.com/Junnnnnnnnnnn/android_study/blob/master/Jetpack_viewPager2/%5BAndroid%5D%20ViewPager2%20%EC%82%AC%EC%9A%A9%ED%95%B4%20%EC%9D%B4%EB%AF%B8%EC%A7%80%20%EC%8A%AC%EB%9D%BC%EC%9D%B4%EB%93%9C%20%EA%B5%AC%ED%98%84%20(2).md)

## 🌍 Setting

- CompileSdk = 30
- Minsdk = 24
- TargetSdk = 30

## 🤨 Why

- 이미지 슬라이드와 미리보기 옵션 등 다양한 기능을 Jetpack 을 통해 쉽게 구현 하기 위해서

## 🙋 Try 

- Jetpack viewPager2 사용해보기
- 다음 사진 미리보기 설정해보기
- 데이터 슬라이드 할 때 이벤트 처리 해보기

## ✏️ Gradle 설정

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

## ✏️ ViewPager Class 만들기

```kotlin
/**
 * RecyclerView Adapter 를 상속 받는다
 * 제네릭 타입으론 UI에 보여줄 View 를 담는 ViewHolder 이다.
 */
class ViewPager2Adepter(private val context: Context, private val imageList: MutableList<Int> ): RecyclerView.Adapter<ViewPager2Adepter.PagerViewHolder>() {

    /**
     * View 를 담을 ViewHolder class 를 정의 한다.
     */
    inner class PagerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val item: ImageView = itemView.findViewById(R.id.imageView1)
    }

    /**
     * ViewHolder 를 인스턴스화 하고 return
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
     * 뷰와 데이터를 바인딩 하는 메서드
     */
    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.item.setImageDrawable(ContextCompat.getDrawable(context, imageList[position]))
    }

    override fun getItemCount() = imageList.size

}
```

- ### ✏️ 뷰 홀더 만들어 자원 낭비 방지 하기

  ```kotlin
  inner class PagerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
  		val item: ImageView = itemView.findViewById(R.id.imageView1)
  }
  ```

- ### ✏️ 뷰 홀더 인스턴스화 하기

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

- ### ✏️ 뷰와 데이터 바인딩 하기

  ```kotlin
  override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
      holder.item.setImageDrawable(ContextCompat.getDrawable(context, imageList[position]))
  }
  ```

## ✏️ MainActivity Class 만들기

```kotlin
class MainActivity : AppCompatActivity() {

    private val imageList = mutableListOf<Int>().apply {
        add(R.drawable.download_1)
        add(R.drawable.download_2)
        add(R.drawable.download_3)
        add(R.drawable.download_4)
    }

    private val textList = mutableListOf<String>().apply {
        add("대한민국")
        add("미국")
        add("영국")
        add("중국")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainInitViewPager2()
    }

    private fun mainInitViewPager2(){
      content_text.text = "대한민국"
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

- ### ✏️ ViewPager2 설정 및 데이터 슬라이드 할 때 다음 데이터 미리 보는 것 구현 해 보기

  ```kotlin
  private fun mainInitViewPager2(){
    content_text.text = "대한민국"
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

- ### ✏️ 데이터 슬라이드 할때 이벤트 처리 해보기

  ```kotlin
  private fun mainViewChangeEvent(){
    viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
      override fun onPageSelected(position: Int) {
        content_text.text = textList[position]
      }
    })
  }
  ```

  
