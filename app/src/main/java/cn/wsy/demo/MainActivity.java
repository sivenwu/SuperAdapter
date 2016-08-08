package cn.wsy.demo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import java.util.ArrayList;
import java.util.List;

import cn.wsy.adapter.base.SuperRefreshLayout;
import cn.wsy.adapter.interfaces.OnItemClickListener;
import cn.wsy.demo.widgets.TestAdapter;
import cn.wsy.generallib.httplib.utils.JsonParseUtil;
import cn.wsy.generallib.photolib.utils.PhotoUtils;
import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListView listView;
    private Button deleteBtn;
    private SuperRefreshLayout refreshLayout;
    private SuperRefreshLayout lvRefreshLayout;

    private TestAdapter recyeAdapter, listAdapter;//listview 和recycview都支持的adapter
//    private LvAdapter listAdapter;
    //    private List<String> re_data;
//    private List<String> lv_data;

    private List<ResponseNew.NewInfo> re_data;
    private List<ResponseNew.NewInfo> lv_data;

    private int lv_page = 1;
    private int re_page = 1;

    int cout = 0;
    int lv_cout = 0;
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            //-----------------------------------------> recyclerView
            if (msg.what == 0) {
                Log.i("wusy", "loading more");
                if (cout > 2) {
                    recyeAdapter.stopLoadMore();
                    return;
                }
//                initData();
                cout++;
            } else if (msg.what == 1) {
                cout = 0;
                refreshLayout.setRefreshing(false);
                recyeAdapter.removeAll(re_data);
//                initData();
            }

            //-----------------------------------------> listview
            if (msg.what == 2) { // listview 记载更多
                if (lv_cout > 2) {
                    listAdapter.stopLoadMore();
                    return;
                }
//                initDataFoeList();
                lv_cout++;
            } else if (msg.what == 3) {
                lv_cout = 0;
                lvRefreshLayout.setRefreshing(false);
                listAdapter.removeAll(lv_data);
//                initDataFoeList();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
//        initData();
//        initDataFoeList();

        getNewFromServer(lv_page, true);
        getNewFromServer(re_page, false);
    }

    private void initView() {
        PhotoUtils.init(this);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        listView = (ListView) findViewById(R.id.listview);
        refreshLayout = (SuperRefreshLayout) findViewById(R.id.refreshLayout);
        lvRefreshLayout = (SuperRefreshLayout) findViewById(R.id.lv_refreshLayout);
        deleteBtn = (Button) findViewById(R.id.delete);
        deleteBtn.setVisibility(View.GONE);

        lv_data = new ArrayList<>();
        re_data = new ArrayList<>();

        recyeAdapter = new TestAdapter(this, re_data);
        listAdapter = new TestAdapter(this, lv_data);

//        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyeAdapter);
        listView.setAdapter(listAdapter);

    }

    int i = 0;

    private void initListener() {

        recyeAdapter.setClickListener(new OnItemClickListener() {
            @Override
            public void onclick(View view, int position) {
                Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_LONG).show();
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                listAdapter.remove(10);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_LONG).show();
            }
        });

        lvRefreshLayout.setOnRefreshListener(new SuperRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                handler.sendEmptyMessageDelayed(3, 2000);
                lv_page = 1;
                getNewFromServer(lv_page, true);
            }

            @Override
            public void onLoadMore() {
//                handler.sendEmptyMessageDelayed(2, 2000);
                lv_page++;
                getNewFromServer(lv_page, true);
            }
        });


        refreshLayout.setOnRefreshListener(new SuperRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                handler.sendEmptyMessageDelayed(1, 2000);
                re_page = 1;
                getNewFromServer(re_page, false);
            }

            @Override
            public void onLoadMore() {
//                handler.sendEmptyMessageDelayed(0, 2000);
                re_page++;
                getNewFromServer(re_page, false);
            }
        });
//
    }

//    private void initData() {
//        for (int i = 0; i < 30; i++) {
//            re_data.add("Recycview adapter " + i);
//        }
//        recyeAdapter.notifyDataSetChanged();
//    }
//
//    private void initDataFoeList() {
//        List<String> tmp = new ArrayList<>();
//        for (int i = 0; i < 30; i++) {
//            tmp.add("Listview adapter ");
//        }
//        listAdapter.addAll(tmp);
//}

    public void getNewFromServer(final int page, final boolean isLv) {
        try {
            //  http://apis.baidu.com/txapi/tiyu/tiyu
            AsyncHttpClient client = new AsyncHttpClient();
            client.addHeader("apikey", "");
            RequestParams enetity = new RequestParams();
            enetity.add("num", String.valueOf(10));
            enetity.add("page", String.valueOf(page));
            client.get(this, "http://apis.baidu.com/txapi/tiyu/tiyu", enetity, new TextHttpResponseHandler() {
                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//                    Log.i("wusy", responseString);
                    listAdapter.stopLoadMore();
                    recyeAdapter.stopLoadMore();
                    Toast.makeText(MainActivity.this,"请稍后重新加载!", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {
                    refreshLayout.setRefreshing(false);
                    lvRefreshLayout.setRefreshing(false);
                    ResponseNew rsp = JsonParseUtil.jsonToMode(responseString, ResponseNew.class);
                    List<ResponseNew.NewInfo> tmp = rsp.getNewslist();
                    if (tmp.size() > 0) {
                        if (isLv) {
                            if (page == 1) listAdapter.removeAll(lv_data);
                            listAdapter.addAll(tmp);
//                        lv_data.addAll(tmp);
//                        listAdapter.notifyDataSetHasChanged();
                        } else {
                            if (page == 1) recyeAdapter.removeAll(re_data);
                            recyeAdapter.addAll(tmp);
                        }
                    } else {
                        if (isLv) {
                            listAdapter.stopLoadMore();
                        } else {
                            re_data = tmp;
                            recyeAdapter.stopLoadMore();
                        }
                    }
                }
            });

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_LONG).show();
        }
    }
}
