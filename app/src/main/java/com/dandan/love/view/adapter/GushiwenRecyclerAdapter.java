package com.dandan.love.view.adapter;

import com.chad.library.adapter.base.BaseViewHolder;
import com.dandan.love.R;
import com.dandan.love.base.BaseRecycleAdapter;
import com.dandan.love.bean.GushiwenModel;
import com.dandan.love.bean.RecycleItemEntity;

import java.util.List;

/**
 * Created by Tanzhenxing
 * Date: 2018/9/3 下午5:21
 * Description:
 */
public class GushiwenRecyclerAdapter extends BaseRecycleAdapter<RecycleItemEntity<GushiwenModel>> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public GushiwenRecyclerAdapter(List data) {
        super(data);
    }

    @Override
    protected void addItemTypes() {
        addItemType(BaseRecycleAdapter.TYPE_DATA, R.layout.item_gushiwen);
    }

    @Override
    protected void convert(BaseViewHolder holder, RecycleItemEntity<GushiwenModel> item) {
        int currentType = holder.getItemViewType();
        if (BaseRecycleAdapter.TYPE_DATA == currentType) {
            GushiwenModel model = item.getData();
            holder.setText(R.id.gsw_title, model.getName());
            holder.setText(R.id.user_chaodai, model.getChaodai());
            holder.setText(R.id.user_name, model.getAuthor());
            holder.setText(R.id.gsw_content, model.getContent());
        }
    }
}
