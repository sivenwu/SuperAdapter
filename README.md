# SuperAdapter
集成下拉刷新与上拉加载的通用适配器，适合RecycerView以及Listview

首先使用方法：
一、在application中的build.gradle引入:
````
repositories {
    maven { url = 'https://dl.bintray.com/yuancloud/maven/' }
    ...
}

compile 'cn.yuancloud.app:superadapter:1.0'
````

二、引入下拉刷新，必须在RecycerView或者ListView添加父控件SuperRefreshLayout（这个跟google自带的下拉刷新用法类同）,举例：

````
    <cn.wsy.adapter.base.SuperRefreshLayout

        android:id="@+id/refreshLayout"
        android:layout_width="match_parent"
        android:layout_height="250dp"
        android:layout_below="@id/tip_two">

        <android.support.v7.widget.RecyclerView
            android:id="@+id/recyclerView"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"></android.support.v7.widget.RecyclerView>
    </cn.wsy.adapter.base.SuperRefreshLayout>
````

三、引入上拉加载，必须注入SuperRefreshLayout的动态加载监听，举例：
````
  refreshLayout.setOnRefreshListener(new SuperRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
              // refreshing...
            }

            @Override
            public void onLoadMore() {
              // loading more...
            }
        });
````

有什么issue，可以报给我，感谢各位读者！
简书：http://www.jianshu.com/users/d388bcf9c4d3/latest_articles

####傻小孩b mark
####共勉，写给在成长路上奋斗的你。
