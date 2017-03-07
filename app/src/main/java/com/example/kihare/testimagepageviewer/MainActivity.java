package com.example.kihare.testimagepageviewer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import uk.co.senab.photoview.PhotoView;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment fragment = new ViewPagerFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.activity_main, fragment).commit();
    }

    public static class ViewPagerFragment extends Fragment {
        private ViewPager viewPager;
        private ImageAdapter adapter;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_view_pager, null);

            viewPager = (ViewPager) view.findViewById(R.id.view_pager);
            adapter = new ImageAdapter();
            viewPager.setAdapter(adapter);

            return view;
        }

        private class ImageAdapter extends PagerAdapter {

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT);

                PhotoView photoView = new PhotoView(getContext());
                ViewCompat.setBackground(photoView, null);

                String url = "https://dummyimage.com/600x400/000/fff&text=" + (position + 1);
                Picasso.with(getContext()).load(url).into(photoView);
                container.addView(photoView, layoutParams);
                return photoView;
            }

            @Override
            public int getCount() {
                return 10;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view.equals(object);
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        }
    }
}
