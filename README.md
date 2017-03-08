# SuperAdapter
集成下拉刷新与上拉加载的通用适配器，适合RecycerView以及Listview

### 1.0 版本 使用方法：
####一、在application中的build.gradle引入:
````
repositories {
    maven { url = 'https://dl.bintray.com/yuancloud/maven/' }
    ...
}

compile 'cn.yuancloud.app:superadapter:1.0'
````

####二、引入下拉刷新，必须在RecycerView或者ListView添加父控件SuperRefreshLayout（这个跟google自带的下拉刷新用法类同）,举例：

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

####三、引入上拉加载，必须注入SuperRefreshLayout的动态加载监听，举例：
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

### V1.1版本 2016年8月12日

2016年8月12日 ，首先感谢读者的支持！部分读者反馈有时候需要下拉刷新、不需要上拉加载的情况。希望可以接口可以分离出来。呼之欲出，修改下1.1版本，提供了三个接口，可以根据你需求进行初始化，下面是举例代码：

一、引入下拉刷新（不需要上拉加载）
````
refreshLayout.setOnRefreshListener(new RefreshListener() {
            @Override
            public void onRefresh() {
                // refreshing...
            }
        });
````

二、引入上拉加载（不需要下拉刷新）
````
refreshLayout.setOnLoadMoreListener(new LoadMoreListener() {
            @Override
            public void onLoadMore() {
              // loading more...
            }
        });

````

三、下拉刷新与上拉加载都引入
````
    refreshLayout.setLoadingListener(new LoadingListener() {
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

####傻小孩b 
####共勉，写给在成长路上奋斗的你。
