package com.sincle.cho.gwangjubus;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.sincle.cho.gwangjubus.fragment.FragmentBookmark;
import com.sincle.cho.gwangjubus.fragment.FragmentInfo;
import com.sincle.cho.gwangjubus.fragment.FragmentRoute;
import com.sincle.cho.gwangjubus.fragment.FragmentStation;


public class StartActivity extends AppCompatActivity implements View.OnClickListener {

    Button tab01;
    Button tab02;
    Button tab03;
    Button tab04;
    Drawable img01;
    Drawable img02;
    Drawable img03;
    Drawable img04;
    Drawable img01_sel;
    Drawable img02_sel;
    Drawable img03_sel;
    Drawable img04_sel;
    private final static String COLOR = "#FF9D00";
    private final static int FRAGMENT_FAVORITE   = 1;
    private final static int FRAGMENT_STATION    = 2;
    private final static int FRAGMENT_ROUTE      = 3;
    private final static int FRAGMENT_INFO       = 4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);
        initView();
        img01 = getApplicationContext().getResources().getDrawable( R.drawable.tab01 );
        img02 = getApplicationContext().getResources().getDrawable( R.drawable.tab02 );
        img03 = getApplicationContext().getResources().getDrawable( R.drawable.tab03 );
        img04 = getApplicationContext().getResources().getDrawable( R.drawable.tab04 );
        img01_sel = getApplicationContext().getResources().getDrawable( R.drawable.tab01_sel );
        img02_sel = getApplicationContext().getResources().getDrawable( R.drawable.tab02_sel );
        img03_sel = getApplicationContext().getResources().getDrawable( R.drawable.tab03_sel );
        img04_sel = getApplicationContext().getResources().getDrawable( R.drawable.tab04_sel );
        tab01.setOnClickListener(this);
        tab02.setOnClickListener(this);
        tab03.setOnClickListener(this);
        tab04.setOnClickListener(this);
        callFragment(FRAGMENT_FAVORITE);
        clickImg(FRAGMENT_FAVORITE);
    }



    private void initView(){
        //뷰세팅
        tab01 = (Button) findViewById(R.id.bt_tab1);
        tab02 = (Button) findViewById(R.id.bt_tab2);
        tab03 = (Button) findViewById(R.id.bt_tab3);
        tab04 = (Button) findViewById(R.id.bt_tab4);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_tab1 :
                callFragment(FRAGMENT_FAVORITE);
                clickImg(FRAGMENT_FAVORITE);

                break;
            case R.id.bt_tab2 :
                callFragment(FRAGMENT_STATION);
                clickImg(FRAGMENT_STATION);


                break;
            case R.id.bt_tab3 :
                callFragment(FRAGMENT_ROUTE);
                clickImg(FRAGMENT_ROUTE);

                break;
            case R.id.bt_tab4 :
                callFragment(FRAGMENT_INFO);
                clickImg(FRAGMENT_INFO);

                break;
            default:
                Toast.makeText(this, "sincle582@naver.com로 문의해주세요", Toast.LENGTH_SHORT).show();
        }
    }
    private void callFragment(int fragId) {
        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (fragId) {
            case 1:
                FragmentBookmark frg1 = new FragmentBookmark();
                transaction.replace(R.id.fragment_container, frg1);
                transaction.commit();
            break;

            case 2:
                FragmentStation frg2 = new FragmentStation();
                transaction.replace(R.id.fragment_container, frg2);
                transaction.commit();
                break;

            case 3:
                FragmentRoute frg3 = new FragmentRoute();
                transaction.replace(R.id.fragment_container, frg3);
                transaction.commit();
                break;
            case 4:
                FragmentInfo frg4 = new FragmentInfo();
                transaction.replace(R.id.fragment_container, frg4);
                transaction.commit();
                break;
            default:
                Toast.makeText(this, "문의해주세요", Toast.LENGTH_SHORT).show();
        }

    }

    private void clickImg(int imgId){
        switch (imgId) {
            case 1:
                tab01.setTextColor(Color.parseColor(COLOR));
                tab01.setCompoundDrawablesWithIntrinsicBounds(null, img01_sel, null, null);
                tab02.setTextColor(Color.BLACK);
                tab02.setCompoundDrawablesWithIntrinsicBounds(null, img02, null, null);
                tab03.setTextColor(Color.BLACK);
                tab03.setCompoundDrawablesWithIntrinsicBounds(null, img03, null, null);
                tab04.setTextColor(Color.BLACK);
                tab04.setCompoundDrawablesWithIntrinsicBounds(null, img04, null, null);
                break;
            case 2:
                tab01.setTextColor(Color.BLACK);
                tab01.setCompoundDrawablesWithIntrinsicBounds(null, img01, null, null);
                tab02.setTextColor(Color.parseColor(COLOR));
                tab02.setCompoundDrawablesWithIntrinsicBounds(null, img02_sel, null, null);
                tab03.setTextColor(Color.BLACK);
                tab03.setCompoundDrawablesWithIntrinsicBounds(null, img03, null, null);
                tab04.setTextColor(Color.BLACK);
                tab04.setCompoundDrawablesWithIntrinsicBounds(null, img04, null, null);

                break;
            case 3:
                callFragment(FRAGMENT_ROUTE);
                tab01.setTextColor(Color.BLACK);
                tab01.setCompoundDrawablesWithIntrinsicBounds(null, img01, null, null);
                tab02.setTextColor(Color.BLACK);
                tab02.setCompoundDrawablesWithIntrinsicBounds(null, img02, null, null);
                tab03.setTextColor(Color.parseColor(COLOR));
                tab03.setCompoundDrawablesWithIntrinsicBounds(null, img03_sel, null, null);
                tab04.setTextColor(Color.BLACK);
                tab04.setCompoundDrawablesWithIntrinsicBounds(null, img04, null, null);
                break;
            case 4:
                callFragment(FRAGMENT_INFO);
                tab01.setTextColor(Color.BLACK);
                tab01.setCompoundDrawablesWithIntrinsicBounds(null, img01, null, null);
                tab02.setTextColor(Color.BLACK);
                tab02.setCompoundDrawablesWithIntrinsicBounds(null, img02, null, null);
                tab03.setTextColor(Color.BLACK);
                tab03.setCompoundDrawablesWithIntrinsicBounds(null, img03, null, null);
                tab04.setTextColor(Color.parseColor(COLOR));
                tab04.setCompoundDrawablesWithIntrinsicBounds(null, img04_sel, null, null);
                break;
            default:
                Toast.makeText(this, "말도 안돼", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
