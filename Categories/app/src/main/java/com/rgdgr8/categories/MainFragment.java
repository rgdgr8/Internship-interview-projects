package com.rgdgr8.categories;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
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
        adapter = new MainAdapter(Categories.getInstance(getActivity()).getCategoriesList());
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new MyScrollListener(greetingLayout));

        return view;
    }

    private class MyScrollListener extends RecyclerView.OnScrollListener {
        private static final String TAG = "ScrollListener";
        private final ViewGroup.LayoutParams layoutParams;
        private final LinearLayout linearLayout;
        private final Animation fadeAnimation;
        private final Animation unFadeAnimation;
        private boolean hidden = false;


        public MyScrollListener(LinearLayout linearLayout) {
            this.layoutParams = linearLayout.getLayoutParams();
            this.linearLayout = linearLayout;

            fadeAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.greeting_fade);
            fadeAnimation.setAnimationListener(new FadeAnimation());

            unFadeAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.greeting_unfade);
            unFadeAnimation.setAnimationListener(new UnFadeAnimation());
        }

        @Override
        public void onScrollStateChanged(@NonNull @NotNull RecyclerView recyclerView, int newState) {
            if (!recyclerView.canScrollVertically(-1)) {
                if (hidden) {
                    Log.d(TAG, "onScrollStateChanged: unfade");
                    linearLayout.startAnimation(unFadeAnimation);
                }
            } else {
                if (!hidden)
                    linearLayout.startAnimation(fadeAnimation);
            }
        }

        private class FadeAnimation implements Animation.AnimationListener {
            @Override
            public void onAnimationStart(Animation animation) {
                hidden = true;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                linearLayout.setLayoutParams(new LinearLayout.LayoutParams(0, 0));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        }

        private class UnFadeAnimation implements Animation.AnimationListener {
            @Override
            public void onAnimationStart(Animation animation) {
                hidden = false;
                linearLayout.setLayoutParams(layoutParams);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        }

    }

    private class CategoryHolder extends RecyclerView.ViewHolder {
        private static final String TAG = "Holder";
        private final TextView subjectTv;
        private final TextView courseTv;
        private final ImageView backgroundImage;

        public CategoryHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            subjectTv = itemView.findViewById(R.id.subject_tv);
            courseTv = itemView.findViewById(R.id.course_tv);
            backgroundImage = itemView.findViewById(R.id.background);
        }

        public void bind(Category category) {
            subjectTv.setText(category.getSubject());
            courseTv.setText(category.getCourses() + " courses");
            if (category.getCover() != null) {
                Log.d(TAG, "bind: " + category.getCover());
                backgroundImage.setImageDrawable(category.getCover());
            }
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
