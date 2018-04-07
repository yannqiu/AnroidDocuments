package com.example.peiyu_wang.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class BaseRecycleViewAdapter<T, K extends ViewDataBinding> extends RecyclerView.Adapter<BaseViewHolder<K>> {
    protected List<T> lists;    //数据源
    protected int resouceId;    //布局ID
    protected int variableId;  //布局内VariableId，就是使用BR类自动生成的常量int，只想layout中使用的data变量

    public BaseRecycleViewAdapter(List<T> lists, int resouceId, int variableId) {
        this.lists = lists;
        this.resouceId = resouceId;
        this.variableId = variableId;
    }

    /**
     * 创建绑定数据的ViewHolder(实际上就相当于初始化出来界面)
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public BaseViewHolder<K> onCreateViewHolder(ViewGroup parent, int viewType) {
        K itemBing = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), resouceId, parent, false);  //获取DataBing相当于获取View
        BaseViewHolder<K> holder = new BaseViewHolder<K>(itemBing.getRoot());//初始化ViewHolder存放View
        holder.setDataBing(itemBing);
        return holder;
    }

    @Override
    public void onBindViewHolder(final BaseViewHolder<K> holder, final int position) {
        T data = lists.get(position);//获取数据
        holder.getDataBing().setVariable(variableId, data);//赋值
        if (listener != null) {//设置单击事件
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(holder.getDataBing(), position);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    listener.onLongItemClick(holder.getDataBing(), position);
                    return false;
                }
            });
        }
        holder.getDataBing().executePendingBindings();//刷新界面
    }

    @Override
    public int getItemCount() {
        return lists == null ? 0 : lists.size();
    }


    //自定义item单击事件
    protected OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {

        public void onItemClick(ViewDataBinding dataBinding, int position);

        public void onLongItemClick(ViewDataBinding dataBinding, int position);
    }
}