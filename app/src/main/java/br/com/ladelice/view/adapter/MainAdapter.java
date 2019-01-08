package br.com.ladelice.view.adapter;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import br.com.ladelice.R;
import br.com.ladelicedomain.entity.MenuEntity;
import br.com.ladelice.view.holder.MainViewHolder;

public class MainAdapter extends RecyclerView.Adapter<MainViewHolder> {
    private List<MenuEntity> menuEntities;
    private int itemImageHeight = 0;
    private Context context;
    private MainViewHolder mainViewHolderArray[];
    private boolean isScrollFinished = false;
    private ImageLoader imageLoader;

    public MainAdapter(List<MenuEntity> menuEntities, Context context) {
        this.menuEntities = menuEntities;
        this.context = context;

        if(menuEntities != null && menuEntities.size() > 0)
            mainViewHolderArray = new MainViewHolder[menuEntities.size()];

        imageLoader = ImageLoader.getInstance();
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_menu, parent, false));
    }

    @Override
    public void onBindViewHolder(MainViewHolder mainViewHolder, int position) {
        mainViewHolder.title.setText(menuEntities.get(position).getTitleMenu());
        mainViewHolder.description.setText(menuEntities.get(position).getDescriptionMenu());
        imageLoader.displayImage(menuEntities.get(position).getImageEntities().get(0).getLink(), mainViewHolder.image);

        mainViewHolderArray[position] = mainViewHolder;

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mainViewHolder.image.getLayoutParams();
        params.height = itemImageHeight;
        mainViewHolder.image.requestLayout();

        ViewCompat.setTransitionName(mainViewHolder.title, context.getString(R.string.item_title) + position);
        ViewCompat.setTransitionName(mainViewHolder.image, context.getString(R.string.item_image) + position);
        ViewCompat.setTransitionName(mainViewHolder.description, context.getString(R.string.item_description) + position);
    }

    public void changeImageWidth(int newHeight, boolean isEnd) {
        itemImageHeight = newHeight;
        isScrollFinished = isEnd;

        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return menuEntities != null ? menuEntities.size() : 0;
    }

    public MainViewHolder getViewHolderAtPosition(int position){
        return mainViewHolderArray[position];
    }

    public boolean isScrollFinished(){
        return isScrollFinished;
    }

}
