package reagodjj.example.com.homeworkfifth.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import reagodjj.example.com.homeworkfifth.R;
import reagodjj.example.com.homeworkfifth.entity.FoodInfo;

public class ListViewAdapter extends BaseAdapter {
    private Context context;
    private List<FoodInfo> foodInfoList;

    public ListViewAdapter(Context context, List<FoodInfo> foodInfoList) {
        this.context = context;
        this.foodInfoList = foodInfoList;
    }

    @Override
    public int getCount() {
        return foodInfoList == null ? 0 : foodInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return foodInfoList == null ? null : foodInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.second_menu_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvTitle.setText(foodInfoList.get(position).getName());
        viewHolder.tvIntroduction.setText(foodInfoList.get(position).getDescription());
        viewHolder.tvPrice.setText(String.format(context.getResources().getString(R.string.price),
                foodInfoList.get(position).getPrice()));
        viewHolder.tvSale.setText(foodInfoList.get(position).getAction());
        viewHolder.tvSell.setText(String.format(context.getResources().getString(R.string.num_sold_out),
                foodInfoList.get(position).getCount()));

        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.centerCrop().placeholder(R.mipmap.ic_launcher);

        Glide.with(context).load(foodInfoList.get(position).getImage()).apply(requestOptions).into(viewHolder.ivFood);

        return convertView;
    }

    class ViewHolder {
        ImageView ivFood;
        TextView tvTitle;
        TextView tvIntroduction;
        TextView tvPrice;
        TextView tvSale;
        TextView tvSell;

        ViewHolder(View view) {
            ivFood = view.findViewById(R.id.iv_food);
            tvTitle = view.findViewById(R.id.tv_title);
            tvIntroduction = view.findViewById(R.id.tv_introduction);
            tvPrice = view.findViewById(R.id.tv_price);
            tvSale = view.findViewById(R.id.tv_sale);
            tvSell = view.findViewById(R.id.tv_sell);
        }

    }
}
