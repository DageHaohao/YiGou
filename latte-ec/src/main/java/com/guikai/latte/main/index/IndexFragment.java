package com.guikai.latte.main.index;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.guikai.latte.app.Latte;
import com.guikai.latte.fragments.bottom.BottomItemFragment;
import com.guikai.latte.net.RestClient;
import com.guikai.latte.net.callback.ISuccess;
import com.guikai.latte.ui.recycler.MultipleFields;
import com.guikai.latte.ui.recycler.MultipleItemEntity;
import com.guikai.latte.ui.refresh.PagingBean;
import com.guikai.latte.ui.refresh.RefreshHandler;
import com.guikai.latteec.R;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;

/**
 * Created by Anding on 2019/1/27 18:22
 * Note:
 */

public class IndexFragment extends BottomItemFragment {

    private RecyclerView mRecyclerView = null;
    private SwipeRefreshLayout mSwipeRefreshLayout = null;
    private RefreshHandler mRefreshHandler = null;

    @Override
    public Object setLayout() {
        return R.layout.fragment_index;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View root) {
        mRecyclerView = $(R.id.rv_index);
        mSwipeRefreshLayout = $(R.id.srl_index);

        final IconTextView mIconScan = $(R.id.icon_index_scan);
        final AppCompatEditText mSearch = $(R.id.et_search_view);

        mRefreshHandler = RefreshHandler.create(mSwipeRefreshLayout,mRecyclerView,
                new IndexDataConverter());

    }

    private void initRefreshLayout() {
        mSwipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
        mSwipeRefreshLayout.setProgressViewOffset(true, 120, 260);
    }

    private void initRecyclerView() {
        final GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
        final Context context = getContext();
        mRecyclerView.setLayoutManager(manager);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefreshLayout();
        initRecyclerView();
        mRefreshHandler.firstPage("index.php");
    }
}
