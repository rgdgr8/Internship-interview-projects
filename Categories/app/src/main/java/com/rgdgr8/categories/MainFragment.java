package com.rgdgr8.categories;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MainFragment extends Fragment {
    private MainAdapter adapter;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @org.jetbrains.annotations.NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        LinearLayout greetingLayout = view.findViewById(R.id.greeting_layout);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        adapter = new MainAdapter(Categories.getInstance().getCategoriesList());
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull @NotNull RecyclerView recyclerView, int newState) {
                if(!recyclerView.canScrollVertically(-1)){
                    greetingLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                }else if(newState == RecyclerView.SCROLL_STATE_DRAGGING){
                    greetingLayout.setLayoutParams(new LinearLayout.LayoutParams(0,0));
                }
            }
        });

        return view;
    }

    private class CategoryHolder extends RecyclerView.ViewHolder {
        private final TextView subjectTv;
        private final TextView courseTv;

        public CategoryHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            subjectTv = itemView.findViewById(R.id.subject_tv);
            courseTv = itemView.findViewById(R.id.course_tv);
        }

        public void bind(Category category){
            subjectTv.setText(category.getSubject());
            courseTv.setText(category.getCourses());
        }
    }

    private class MainAdapter extends RecyclerView.Adapter<CategoryHolder> {
        private final List<Category> categoryList;

        public MainAdapter(List<Category> categoryList) {
            this.categoryList = categoryList;
        }

        @NonNull
        @NotNull
        @Override
        public CategoryHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.list_item, parent, false);
            return new CategoryHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull @NotNull MainFragment.CategoryHolder holder, int position) {
            holder.bind(categoryList.get(position));
        }

        @Override
        public int getItemCount() {
            return categoryList.size();
        }
    }
}
