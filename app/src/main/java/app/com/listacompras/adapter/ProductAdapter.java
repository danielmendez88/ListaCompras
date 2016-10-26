package app.com.listacompras.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import app.com.listacompras.R;
import app.com.listacompras.clases.Product;

/**
 * Created by daniel on 25/10/2016.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyProductHolder> {
    //add context and productlist
    private List<Product> productList;
    private Context context;

    //recyclerview
    public class MyProductHolder extends RecyclerView.ViewHolder{
        public TextView title, description;
        public ImageView thumbnail, overflow;
        public MyProductHolder(View itemView) {
            super(itemView);
            //declaring and setting ids with values
            title = (TextView) itemView.findViewById(R.id.title_txt);
            description = (TextView) itemView.findViewById(R.id.article_description);
            thumbnail = (ImageView) itemView.findViewById(R.id.image_item);
            overflow = (ImageView) itemView.findViewById(R.id.overflow);
        }
    }

    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }
    //here we create a viewHolder that enable to connect layout with Adapter
    @Override
    public MyProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View ItemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.carditem, parent, false);
        return new MyProductHolder(ItemView);
    }

    @Override
    public void onBindViewHolder(final MyProductHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}
