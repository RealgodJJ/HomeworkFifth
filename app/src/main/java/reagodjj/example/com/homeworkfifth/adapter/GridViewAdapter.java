package reagodjj.example.com.homeworkfifth.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import reagodjj.example.com.homeworkfifth.R;
import reagodjj.example.com.homeworkfifth.entity.GridViewCard;

public class GridViewAdapter extends BaseAdapter {
    private Context context;
    private List<GridViewCard> gridViewCardList;

    public GridViewAdapter(Context context, List<GridViewCard> gridViewCardList) {
        this.context = context;
        this.gridViewCardList = gridViewCardList;
    }

    @Override
    public int getCount() {
        return gridViewCardList == null ? 0 : gridViewCardList.size();
    }

    @Override
    public Object getItem(int position) {
        return gridViewCardList == null ? null : gridViewCardList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.first_menu_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.ivCard.setImageResource(gridViewCardList.get(position).getCardId());
        viewHolder.tvTitle.setText(gridViewCardList.get(position).getTitle());
        return convertView;
    }

    class ViewHolder {
        private ImageView ivCard;
        private TextView tvTitle;

        ViewHolder(View view) {
            ivCard = view.findViewById(R.id.iv_card);
            tvTitle = view.findViewById(R.id.tv_title);
        }
    }
}
